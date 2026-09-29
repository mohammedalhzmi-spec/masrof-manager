'use strict';

const crypto = require('node:crypto');
const { initializeApp, getApps } = require('firebase-admin/app');
const { getAuth } = require('firebase-admin/auth');
const { FieldPath, FieldValue, Timestamp, getFirestore } = require('firebase-admin/firestore');
const { defineString } = require('firebase-functions/params');
const { HttpsError, onCall } = require('firebase-functions/v2/https');
const {
  aliasDocumentIds,
  buildChallenge,
  isValidDeviceId,
  normalizeIdentifier,
  publicKeyInfo,
  verifyChallengeSignature,
} = require('./security');

if (getApps().length === 0) initializeApp();
const db = getFirestore();
const auth = getAuth();
const webApiKey = defineString('FIREBASE_WEB_API_KEY', {
  description: 'Web API key from the matching Android google-services.json (public client key; not an Admin key).',
});

const REGION = 'us-central1';
const CHALLENGE_LIFETIME_MS = 5 * 60 * 1000;
const RATE_WINDOW_MS = 15 * 60 * 1000;
const LOGIN_CHALLENGES = '_androidLoginChallenges';
const LOGIN_LIMITS = '_androidLoginAttempts';
const callableOptions = { region: REGION, maxInstances: 10, cors: false };

const invalidCredentials = () => new HttpsError('unauthenticated', 'بيانات الدخول غير صحيحة.');

function requireString(value, field, maxLength) {
  if (typeof value !== 'string' || value.length === 0 || value.length > maxLength) {
    throw new HttpsError('invalid-argument', `القيمة ${field} غير صالحة.`);
  }
  return value;
}

function requireSignedIn(request) {
  if (!request.auth?.uid) throw new HttpsError('unauthenticated', 'يلزم تسجيل الدخول إلى حساب السحابة.');
  return request.auth;
}

function sha256Hex(value) {
  return crypto.createHash('sha256').update(value).digest('hex');
}

function requestIp(request) {
  // Cloud Functions provides the peer IP; do not trust a caller-controlled forwarded header.
  return String(request.rawRequest?.ip || 'unknown').trim().slice(0, 120);
}

async function reserveRateLimit(key, maximum) {
  const ref = db.collection(LOGIN_LIMITS).doc(sha256Hex(key));
  const now = Date.now();
  let allowed = false;
  await db.runTransaction(async (transaction) => {
    const snapshot = await transaction.get(ref);
    const startedAt = snapshot.exists ? Number(snapshot.get('windowStartedAt') || 0) : 0;
    const attempts = snapshot.exists ? Number(snapshot.get('attempts') || 0) : 0;
    if (!snapshot.exists || now - startedAt >= RATE_WINDOW_MS) {
      transaction.set(ref, {
        attempts: 1,
        windowStartedAt: now,
        expiresAt: Timestamp.fromMillis(now + RATE_WINDOW_MS * 2),
      });
      allowed = true;
      return;
    }
    if (attempts >= maximum) {
      allowed = false;
      return;
    }
    transaction.set(ref, {
      attempts: attempts + 1,
      expiresAt: Timestamp.fromMillis(now + RATE_WINDOW_MS * 2),
    }, { merge: true });
    allowed = true;
  });
  if (!allowed) throw new HttpsError('resource-exhausted', 'تجاوزت محاولات الدخول المسموح بها مؤقتًا. حاول لاحقًا.');
}

async function enforceLoginRateLimit(request, identifier) {
  const ip = requestIp(request);
  const normalized = normalizeIdentifier(identifier);
  await reserveRateLimit(`ip:${ip}`, 60);
  await reserveRateLimit(`login:${ip}:${normalized}`, 10);
}

async function findAlias(identifier) {
  const normalized = normalizeIdentifier(identifier);
  for (const candidate of aliasDocumentIds(normalized)) {
    const snapshot = await db.collection('authAliases').doc(candidate).get();
    if (snapshot.exists) {
      const data = snapshot.data() || {};
      const email = typeof data.authEmail === 'string' ? data.authEmail.trim().toLowerCase() : '';
      if (email) return { email, uid: typeof data.uid === 'string' ? data.uid : null };
    }
  }

  // The original release stores the display username in the alias document too.
  // Querying this field preserves old IDs without ever exposing the mapped email to Android.
  const raw = String(identifier).trim();
  for (const probe of [...new Set([raw, normalized])]) {
    const matches = await db.collection('authAliases').where('username', '==', probe).limit(3).get();
    const valid = matches.docs
      .map((doc) => doc.data())
      .filter((data) => typeof data.authEmail === 'string' && data.authEmail.trim());
    if (valid.length === 1) {
      return {
        email: valid[0].authEmail.trim().toLowerCase(),
        uid: typeof valid[0].uid === 'string' ? valid[0].uid : null,
      };
    }
    if (valid.length > 1) throw invalidCredentials();
  }
  return null;
}

async function resolveAuthEmail(identifier) {
  const normalized = normalizeIdentifier(identifier);
  if (normalized.includes('@')) return { email: normalized, uid: null };
  const alias = await findAlias(identifier);
  return alias || { email: `${normalized}@accounts.masrof-manager.local`, uid: null };
}

async function verifyPassword(identifier, password) {
  const apiKey = webApiKey.value();
  if (!apiKey) throw new HttpsError('failed-precondition', 'إعداد تسجيل الدخول الخادمي غير مكتمل.');
  let credential;
  try {
    credential = await resolveAuthEmail(identifier);
  } catch (_error) {
    throw invalidCredentials();
  }

  let response;
  try {
    response = await fetch(`https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=${encodeURIComponent(apiKey)}`, {
      method: 'POST',
      headers: { 'content-type': 'application/json' },
      body: JSON.stringify({ email: credential.email, password, returnSecureToken: true }),
    });
  } catch (_error) {
    throw new HttpsError('unavailable', 'تعذر الاتصال بخدمة تسجيل الدخول. تحقق من الإنترنت وحاول مرة أخرى.');
  }
  if (!response.ok) throw invalidCredentials();
  const result = await response.json().catch(() => ({}));
  if (typeof result.localId !== 'string' || !result.localId) throw invalidCredentials();
  if (credential.uid && credential.uid !== result.localId) throw invalidCredentials();
  // The REST ID/refresh token and the mapped email are deliberately discarded here.
  return result.localId;
}

async function activeProfile(uid) {
  const snapshot = await db.collection('users').doc(uid).get();
  if (!snapshot.exists || snapshot.get('active') !== true) {
    throw new HttpsError('permission-denied', 'الحساب غير معتمد أو غير مفعل في السحابة.');
  }
  const data = snapshot.data() || {};
  return { uid, role: String(data.role || 'ADMIN_USER'), data };
}

function validatedDeviceIdentity(data) {
  const deviceId = requireString(data.deviceId, 'deviceId', 128);
  if (!isValidDeviceId(deviceId)) throw new HttpsError('invalid-argument', 'معرّف الجهاز غير صالح.');
  const publicKey = requireString(data.publicKey, 'publicKey', 4096);
  let keyInfo;
  try {
    keyInfo = publicKeyInfo(publicKey);
  } catch (_error) {
    throw new HttpsError('invalid-argument', 'مفتاح الجهاز غير صالح.');
  }
  const deviceName = String(data.deviceName || 'جهاز Android')
    .replace(/[\u0000-\u001f\u007f]/g, ' ')
    .trim()
    .slice(0, 100) || 'جهاز Android';
  return { deviceId, publicKey, publicKeyHash: keyInfo.hash, deviceName };
}

async function createChallenge({ mode, uid, device, credentialsVerifiedAt }) {
  const challengeId = crypto.randomUUID();
  const expiresAtMs = Date.now() + CHALLENGE_LIFETIME_MS;
  const payload = buildChallenge({
    uid,
    deviceId: device.deviceId,
    publicKeyHash: device.publicKeyHash,
    expiresAtMs,
    nonce: crypto.randomBytes(32).toString('hex'),
  });
  const ref = db.collection(LOGIN_CHALLENGES).doc(challengeId);
  await ref.set({
    mode,
    uid,
    deviceId: device.deviceId,
    publicKey: device.publicKey,
    publicKeyHash: device.publicKeyHash,
    deviceName: device.deviceName,
    challenge: payload.toString('base64'),
    credentialsVerifiedAt: credentialsVerifiedAt ?? null,
    createdAt: Date.now(),
    expiresAt: Timestamp.fromMillis(expiresAtMs),
    attempts: 0,
    used: false,
  });
  return { challengeId, challenge: payload.toString('base64') };
}

async function consumeChallenge(challengeId, signature, expectedMode, expectedUid = null) {
  if (typeof challengeId !== 'string' || !/^[0-9a-f-]{36}$/i.test(challengeId)) {
    throw new HttpsError('unauthenticated', 'تعذر إثبات هوية الجهاز. أعد المحاولة.');
  }
  const ref = db.collection(LOGIN_CHALLENGES).doc(challengeId);
  const snapshot = await ref.get();
  if (!snapshot.exists) throw new HttpsError('unauthenticated', 'انتهت صلاحية طلب الدخول. أعد المحاولة.');
  const data = snapshot.data() || {};
  if (data.mode !== expectedMode || data.used === true || (expectedUid && data.uid !== expectedUid)) {
    throw new HttpsError('unauthenticated', 'تعذر إثبات هوية الجهاز. أعد المحاولة.');
  }
  if (!(data.expiresAt instanceof Timestamp) || data.expiresAt.toMillis() <= Date.now()) {
    throw new HttpsError('unauthenticated', 'انتهت صلاحية طلب الدخول. أعد المحاولة.');
  }
  if (!verifyChallengeSignature(data.publicKey, data.challenge, signature)) {
    await db.runTransaction(async (transaction) => {
      const current = await transaction.get(ref);
      if (!current.exists || current.get('used') === true) return;
      const attempts = Number(current.get('attempts') || 0) + 1;
      transaction.set(ref, { attempts, used: attempts >= 3 }, { merge: true });
    });
    throw new HttpsError('unauthenticated', 'تعذر إثبات هوية الجهاز. أعد المحاولة.');
  }

  await db.runTransaction(async (transaction) => {
    const current = await transaction.get(ref);
    if (!current.exists || current.get('used') === true) {
      throw new HttpsError('unauthenticated', 'استُخدم طلب الدخول مسبقًا. أعد المحاولة.');
    }
    const expiry = current.get('expiresAt');
    if (!(expiry instanceof Timestamp) || expiry.toMillis() <= Date.now()) {
      throw new HttpsError('unauthenticated', 'انتهت صلاحية طلب الدخول. أعد المحاولة.');
    }
    transaction.set(ref, { used: true, usedAt: Date.now() }, { merge: true });
  });
  return data;
}

function isApprovedDevice(data, expectedKeyHash) {
  return data?.status === 'APPROVED'
    && (data.approved === true || !Object.prototype.hasOwnProperty.call(data, 'approved'))
    && typeof expectedKeyHash === 'string'
    && data.publicKeyHash === expectedKeyHash;
}

async function privateEmail(uid, profile) {
  if (typeof profile.data.email === 'string' && profile.data.email.trim()) return profile.data.email.trim();
  try {
    return (await auth.getUser(uid)).email || '';
  } catch (_error) {
    return '';
  }
}

async function recordDeviceAndRequest(uid, profile, challenge) {
  const now = Date.now();
  const email = await privateEmail(uid, profile);
  const deviceRef = db.collection('devices').doc(`${uid}_${challenge.deviceId}`);
  const requestRef = db.collection('accessRequests').doc(`${uid}_${challenge.deviceId}`);
  let approved = false;
  await db.runTransaction(async (transaction) => {
    const [deviceSnapshot, requestSnapshot] = await Promise.all([
      transaction.get(deviceRef),
      transaction.get(requestRef),
    ]);
    const existing = deviceSnapshot.data() || {};
    if (existing.status === 'REVOKED' || existing.status === 'BLOCKED') {
      throw new HttpsError('permission-denied', 'تم إيقاف اعتماد هذا الجهاز. تواصل مع مدير النظام.');
    }
    approved = deviceSnapshot.exists && isApprovedDevice(existing, challenge.publicKeyHash);
    if (approved) {
      transaction.set(deviceRef, { lastSeenAt: now }, { merge: true });
      if (requestSnapshot.exists && requestSnapshot.get('status') === 'PENDING') {
        transaction.set(requestRef, { status: 'APPROVED', reviewedAt: now }, { merge: true });
      }
      return;
    }
    const device = {
      userId: uid,
      deviceId: challenge.deviceId,
      publicKey: challenge.publicKey,
      publicKeyHash: challenge.publicKeyHash,
      email,
      deviceName: challenge.deviceName,
      status: 'PENDING',
      approved: false,
      lastSeenAt: now,
      updatedAt: now,
    };
    if (!deviceSnapshot.exists) device.createdAt = now;
    transaction.set(deviceRef, device, { merge: true });
    transaction.set(requestRef, {
      userId: uid,
      email,
      deviceId: challenge.deviceId,
      publicKeyHash: challenge.publicKeyHash,
      deviceName: challenge.deviceName,
      status: 'PENDING',
      requestedAt: now,
    }, { merge: true });
  });
  return { approved, deviceRef, requestRef };
}

function tokenClaims(profile, device, approved, credentialsVerifiedAt) {
  return {
    app_role: profile.role,
    device_id: device.deviceId,
    device_key: device.publicKeyHash,
    device_approved: approved,
    credentials_verified_at: credentialsVerifiedAt,
  };
}

exports.beginCloudLogin = onCall(callableOptions, async (request) => {
  const identifier = requireString(request.data?.identifier, 'identifier', 200);
  const password = requireString(request.data?.password, 'password', 1024);
  const device = validatedDeviceIdentity(request.data || {});
  await enforceLoginRateLimit(request, identifier);
  const uid = await verifyPassword(identifier, password);
  const profile = await activeProfile(uid);
  const challenge = await createChallenge({
    mode: 'LOGIN',
    uid,
    device,
    credentialsVerifiedAt: Math.floor(Date.now() / 1000),
  });
  return challenge;
});

exports.completeCloudLogin = onCall(callableOptions, async (request) => {
  const challengeId = requireString(request.data?.challengeId, 'challengeId', 64);
  const signature = requireString(request.data?.signature, 'signature', 2048);
  const challenge = await consumeChallenge(challengeId, signature, 'LOGIN');
  const profile = await activeProfile(challenge.uid);
  const deviceState = await recordDeviceAndRequest(challenge.uid, profile, challenge);
  const customToken = await auth.createCustomToken(
    challenge.uid,
    tokenClaims(profile, { deviceId: challenge.deviceId, publicKeyHash: challenge.publicKeyHash }, deviceState.approved, challenge.credentialsVerifiedAt)
  );
  // Never return the alias email or Firebase REST ID/refresh tokens to the client.
  return { customToken, deviceStatus: deviceState.approved ? 'APPROVED' : 'PENDING' };
});

exports.beginCloudSessionRefresh = onCall(callableOptions, async (request) => {
  const signedIn = requireSignedIn(request);
  const deviceId = signedIn.token.device_id;
  const keyHash = signedIn.token.device_key;
  if (!isValidDeviceId(deviceId) || typeof keyHash !== 'string') {
    throw new HttpsError('failed-precondition', 'أعد تسجيل الدخول لتفعيل التحقق الآمن من الجهاز.');
  }
  const deviceSnapshot = await db.collection('devices').doc(`${signedIn.uid}_${deviceId}`).get();
  const deviceData = deviceSnapshot.data() || {};
  if (!deviceSnapshot.exists || deviceData.publicKeyHash !== keyHash || typeof deviceData.publicKey !== 'string') {
    throw new HttpsError('permission-denied', 'مفتاح هذا الجهاز غير مسجل أو تغيّر. اطلب اعتماده من جديد.');
  }
  const challenge = await createChallenge({
    mode: 'REFRESH',
    uid: signedIn.uid,
    device: {
      deviceId,
      publicKey: deviceData.publicKey,
      publicKeyHash: keyHash,
      deviceName: String(deviceData.deviceName || 'جهاز Android').slice(0, 100),
    },
    credentialsVerifiedAt: Number(signedIn.token.credentials_verified_at || 0),
  });
  return challenge;
});

exports.completeCloudSessionRefresh = onCall(callableOptions, async (request) => {
  const signedIn = requireSignedIn(request);
  const challengeId = requireString(request.data?.challengeId, 'challengeId', 64);
  const signature = requireString(request.data?.signature, 'signature', 2048);
  const challenge = await consumeChallenge(challengeId, signature, 'REFRESH', signedIn.uid);
  if (challenge.deviceId !== signedIn.token.device_id || challenge.publicKeyHash !== signedIn.token.device_key) {
    throw new HttpsError('permission-denied', 'معلومات الجهاز لا تطابق جلسة الدخول الحالية.');
  }
  const profile = await activeProfile(signedIn.uid);
  const deviceSnapshot = await db.collection('devices').doc(`${signedIn.uid}_${challenge.deviceId}`).get();
  const deviceData = deviceSnapshot.data() || {};
  if (!deviceSnapshot.exists || deviceData.publicKeyHash !== challenge.publicKeyHash) {
    throw new HttpsError('permission-denied', 'مفتاح هذا الجهاز غير مسجل أو تغيّر.');
  }
  const approved = isApprovedDevice(deviceData, challenge.publicKeyHash);
  await db.collection('devices').doc(`${signedIn.uid}_${challenge.deviceId}`).set({ lastSeenAt: Date.now() }, { merge: true });
  const customToken = await auth.createCustomToken(
    signedIn.uid,
    tokenClaims(profile, { deviceId: challenge.deviceId, publicKeyHash: challenge.publicKeyHash }, approved, Number(signedIn.token.credentials_verified_at || 0))
  );
  return { customToken, deviceStatus: approved ? 'APPROVED' : 'PENDING' };
});

exports.requestCloudDeviceApproval = onCall(callableOptions, async (request) => {
  const signedIn = requireSignedIn(request);
  const deviceId = signedIn.token.device_id;
  const keyHash = signedIn.token.device_key;
  if (!isValidDeviceId(deviceId) || typeof keyHash !== 'string') {
    throw new HttpsError('failed-precondition', 'أعد تسجيل الدخول لتفعيل التحقق الآمن من الجهاز.');
  }
  const profile = await activeProfile(signedIn.uid);
  const deviceRef = db.collection('devices').doc(`${signedIn.uid}_${deviceId}`);
  const requestRef = db.collection('accessRequests').doc(`${signedIn.uid}_${deviceId}`);
  const now = Date.now();
  const email = await privateEmail(signedIn.uid, profile);
  let deviceStatus = 'PENDING';
  await db.runTransaction(async (transaction) => {
    const [snapshot, existingRequest] = await Promise.all([
      transaction.get(deviceRef),
      transaction.get(requestRef),
    ]);
    const data = snapshot.data() || {};
    if (!snapshot.exists
      || data.userId !== signedIn.uid
      || data.deviceId !== deviceId
      || data.publicKeyHash !== keyHash
    ) {
      throw new HttpsError('permission-denied', 'مفتاح هذا الجهاز غير مسجل. أعد تسجيل الدخول.');
    }
    if (data.status === 'REVOKED' || data.status === 'BLOCKED') {
      throw new HttpsError('permission-denied', 'تم إيقاف اعتماد هذا الجهاز. تواصل مع مدير النظام.');
    }
    if (isApprovedDevice(data, keyHash)) {
      deviceStatus = 'APPROVED';
      transaction.set(deviceRef, { lastSeenAt: now }, { merge: true });
      if (existingRequest.exists && existingRequest.get('status') === 'PENDING') {
        transaction.set(requestRef, { status: 'APPROVED', reviewedAt: now }, { merge: true });
      }
      return;
    }
    transaction.set(requestRef, {
      userId: signedIn.uid,
      email,
      deviceId,
      publicKeyHash: keyHash,
      deviceName: String(data.deviceName || 'جهاز Android').slice(0, 100),
      status: 'PENDING',
      requestedAt: now,
    }, { merge: true });
    transaction.set(deviceRef, { status: 'PENDING', approved: false, lastSeenAt: now }, { merge: true });
  });
  return { deviceStatus };
});

async function hasAnyTrustedAdminDevice(excludedDeviceDocId) {
  let lastAdmin = null;
  while (true) {
    let adminsQuery = db.collection('users')
      .where('role', '==', 'SYSTEM_ADMIN')
      .orderBy(FieldPath.documentId())
      .limit(100);
    if (lastAdmin) adminsQuery = adminsQuery.startAfter(lastAdmin);
    const admins = await adminsQuery.get();
    for (const adminProfile of admins.docs) {
      if (adminProfile.get('active') !== true) continue;
      let lastDevice = null;
      while (true) {
        let devicesQuery = db.collection('devices')
          .where('userId', '==', adminProfile.id)
          .where('status', '==', 'APPROVED')
          .orderBy(FieldPath.documentId())
          .limit(100);
        if (lastDevice) devicesQuery = devicesQuery.startAfter(lastDevice);
        const devices = await devicesQuery.get();
        if (devices.docs.some((device) =>
          device.id !== excludedDeviceDocId && isApprovedDevice(device.data(), device.get('publicKeyHash'))
        )) return true;
        if (devices.docs.length < 100) break;
        lastDevice = devices.docs[devices.docs.length - 1];
      }
    }
    if (admins.docs.length < 100) return false;
    lastAdmin = admins.docs[admins.docs.length - 1];
  }
}

async function requireAdmin(request) {
  const signedIn = requireSignedIn(request);
  const profile = await activeProfile(signedIn.uid);
  if (profile.role !== 'SYSTEM_ADMIN') {
    throw new HttpsError('permission-denied', 'هذه العملية متاحة لمدير النظام فقط.');
  }
  const verifiedAt = Number(signedIn.token.credentials_verified_at || 0);
  if (!verifiedAt || Math.floor(Date.now() / 1000) - verifiedAt > 10 * 60) {
    throw new HttpsError('failed-precondition', 'انتهت مهلة التحقق الإداري. سجّل الدخول مجددًا ثم أعد المحاولة.');
  }

  const deviceId = signedIn.token.device_id;
  const keyHash = signedIn.token.device_key;
  if (!isValidDeviceId(deviceId) || typeof keyHash !== 'string') {
    throw new HttpsError('failed-precondition', 'أعد تسجيل الدخول لتفعيل التحقق الآمن من الجهاز.');
  }
  const ownDeviceRef = db.collection('devices').doc(`${signedIn.uid}_${deviceId}`);
  const ownDeviceSnapshot = await ownDeviceRef.get();
  const ownDevice = ownDeviceSnapshot.data() || {};
  const ownDeviceApproved = ownDeviceSnapshot.exists && isApprovedDevice(ownDevice, keyHash);
  if (!ownDeviceApproved && await hasAnyTrustedAdminDevice(ownDeviceRef.id)) {
    throw new HttpsError('permission-denied', 'استخدم جهاز مدير معتمد لإدارة طلبات الأجهزة.');
  }
  return { signedIn, profile, deviceId, keyHash, ownDeviceApproved };
}

exports.listPendingCloudDevices = onCall(callableOptions, async (request) => {
  await requireAdmin(request);
  const snapshot = await db.collection('accessRequests').where('status', '==', 'PENDING').limit(100).get();
  return {
    requests: snapshot.docs.map((doc) => {
      const data = doc.data();
      return {
        id: doc.id,
        userId: String(data.userId || ''),
        email: String(data.email || ''),
        deviceId: String(data.deviceId || ''),
        deviceName: String(data.deviceName || 'جهاز Android'),
        requestedAt: typeof data.requestedAt === 'number' ? data.requestedAt : (data.requestedAt?.toMillis?.() || 0),
      };
    }),
  };
});

exports.approveCloudDevice = onCall(callableOptions, async (request) => {
  const { signedIn } = await requireAdmin(request);
  const requestId = requireString(request.data?.requestId, 'requestId', 300);
  if (requestId.includes('/')) throw new HttpsError('invalid-argument', 'معرّف الطلب غير صالح.');
  const requestRef = db.collection('accessRequests').doc(requestId);
  const requestSnapshot = await requestRef.get();
  if (!requestSnapshot.exists || requestSnapshot.get('status') !== 'PENDING') {
    throw new HttpsError('not-found', 'طلب اعتماد الجهاز غير موجود أو سبق مراجعته.');
  }
  const target = requestSnapshot.data() || {};
  const targetUid = String(target.userId || '');
  const targetDeviceId = String(target.deviceId || '');
  if (!targetUid || !isValidDeviceId(targetDeviceId) || requestId !== `${targetUid}_${targetDeviceId}`) {
    throw new HttpsError('failed-precondition', 'بيانات طلب الجهاز غير مكتملة.');
  }
  const deviceRef = db.collection('devices').doc(`${targetUid}_${targetDeviceId}`);
  const deviceSnapshot = await deviceRef.get();
  const device = deviceSnapshot.data() || {};
  if (!deviceSnapshot.exists || device.userId !== targetUid || device.deviceId !== targetDeviceId || !device.publicKeyHash) {
    throw new HttpsError('failed-precondition', 'سجل الجهاز غير مكتمل ولا يمكن اعتماده.');
  }

  const now = Date.now();
  await db.runTransaction(async (transaction) => {
    const [currentRequest, currentDevice] = await Promise.all([
      transaction.get(requestRef),
      transaction.get(deviceRef),
    ]);
    const currentDeviceData = currentDevice.data() || {};
    if (!currentRequest.exists
      || currentRequest.get('status') !== 'PENDING'
      || !currentDevice.exists
      || currentDeviceData.status !== 'PENDING'
      || currentDeviceData.userId !== targetUid
      || currentDeviceData.deviceId !== targetDeviceId
      || currentDeviceData.publicKeyHash !== device.publicKeyHash
      || currentRequest.get('userId') !== targetUid
      || currentRequest.get('deviceId') !== targetDeviceId
      || currentRequest.get('publicKeyHash') !== device.publicKeyHash
    ) {
      throw new HttpsError('aborted', 'تغيّر طلب الجهاز؛ أعد تحميل القائمة.');
    }
    transaction.set(deviceRef, {
      status: 'APPROVED',
      approved: true,
      approvedBy: signedIn.uid,
      approvedAt: now,
      lastSeenAt: now,
    }, { merge: true });
    transaction.set(requestRef, {
      status: 'APPROVED',
      reviewedBy: signedIn.uid,
      reviewedAt: now,
    }, { merge: true });
  });
  await db.collection('notifications').add({
    userId: targetUid,
    title: 'اعتماد الجهاز',
    body: 'تم اعتماد جهازك من مدير النظام',
    read: false,
    createdAt: now,
  });

  let customToken = null;
  if (targetUid === signedIn.uid && targetDeviceId === signedIn.token.device_id) {
    const profile = await activeProfile(signedIn.uid);
    customToken = await auth.createCustomToken(
      signedIn.uid,
      tokenClaims(profile, { deviceId: targetDeviceId, publicKeyHash: device.publicKeyHash }, true, Number(signedIn.token.credentials_verified_at || 0))
    );
  }
  return { approved: true, customToken };
});
