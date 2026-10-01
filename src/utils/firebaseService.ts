import { initializeApp } from 'firebase/app';
import {
  getAuth,
  signInWithEmailAndPassword,
  signOut,
  User,
} from 'firebase/auth';
import {
  getFirestore,
  collection,
  getDocs,
  doc,
  setDoc,
  updateDoc,
  getDoc,
} from 'firebase/firestore';
import config from '../../firebase-applet-config.json';
import { Document } from '../types';

const firebaseApp = initializeApp(config);
export const auth = getAuth(firebaseApp);
export const db = getFirestore(firebaseApp, config.firestoreDatabaseId);

export async function fbSignIn(email: string, password: string): Promise<User> {
  const result = await signInWithEmailAndPassword(auth, email.trim().toLowerCase(), password);
  return result.user;
}

export async function fbSignOut() {
  await signOut(auth);
}

export function fbCurrentUser() {
  return auth.currentUser;
}

export async function fbGetUserProfile(uid: string) {
  const snapshot = await getDoc(doc(db, 'users', uid));
  return snapshot.exists() ? { id: snapshot.id, ...snapshot.data() } : null;
}

// Users and device approvals. User roles are stored by UID and protected by Firestore rules.
export async function fbGetUsers() {
  try {
    const snap = await getDocs(collection(db, 'users'));
    return snap.docs.map(d => ({ id: d.id, ...d.data() }));
  } catch (err) {
    console.error('FB Get Users Error:', err);
    return [];
  }
}

export async function fbGetDevices() {
  try {
    const snap = await getDocs(collection(db, 'devices'));
    return snap.docs.map(d => ({ id: d.id, ...d.data() }));
  } catch (err) {
    console.error('FB Get Devices Error:', err);
    return [];
  }
}

export async function fbGetAccessRequests() {
  const devices = await fbGetDevices() as any[];
  return devices.filter(d => d.status === 'PENDING').map(d => ({
    id: 'req_' + d.id,
    deviceId: d.id,
    userId: d.userId,
    username: d.username || 'unknown',
    fullName: d.fullName || 'موظف',
    role: d.role || 'ADMIN_USER',
    deviceName: d.deviceName || 'Android Mobile Device',
    status: d.status,
    requestedAt: d.requestedAt || Date.now()
  }));
}

export async function fbApproveDevice(deviceId: string, adminName: string) {
  const cleanId = deviceId.replace('req_', '');
  await updateDoc(doc(db, 'devices', cleanId), { status: 'APPROVED', approvedBy: adminName, approvedAt: Date.now() });
  return true;
}

export async function fbRejectDevice(deviceId: string, adminName: string, reason: string) {
  const cleanId = deviceId.replace('req_', '');
  await updateDoc(doc(db, 'devices', cleanId), { status: 'REJECTED', rejectionReason: reason, rejectedBy: adminName, rejectedAt: Date.now() });
  return true;
}

export async function fbRevokeDevice(deviceId: string, adminName: string) {
  await updateDoc(doc(db, 'devices', deviceId), { status: 'REVOKED', revokedBy: adminName, revokedAt: Date.now() });
  return true;
}

export async function fbAddAuditLog(action: string, details: string, username: string, deviceId: string) {
  const user = auth.currentUser;
  if (!user) return;
  const logId = 'log_' + Date.now() + '_' + Math.random().toString(36).substring(2, 7);
  await setDoc(doc(db, 'auditLogs', logId), {
    id: logId, timestamp: Date.now(), action, details, username, deviceId, actorUid: user.uid
  });
}

export async function fbGetAuditLogs() {
  try {
    const snap = await getDocs(collection(db, 'auditLogs'));
    return snap.docs.map(d => d.data()).sort((a: any, b: any) => b.timestamp - a.timestamp);
  } catch (err) {
    console.error('FB Get Audit Logs Error:', err);
    return [];
  }
}

export async function fbSendNotification(title: string, body: string, sender: string, docType?: string) {
  const user = auth.currentUser;
  if (!user) return;
  const notifId = 'notif_' + Date.now() + '_' + Math.random().toString(36).substring(2, 7);
  const now = new Date();
  await setDoc(doc(db, 'notifications', notifId), {
    id: notifId, title, body, sender, docType: docType || 'GENERAL', timestamp: now.getTime(),
    dayName: now.toLocaleDateString('ar-SA', { weekday: 'long' }), dateString: now.toLocaleDateString('ar-SA'), read: false
  });
}

export async function fbGetNotifications() {
  const user = auth.currentUser;
  if (!user) return [];
  const snap = await getDocs(collection(db, 'notifications'));
  return snap.docs.map(d => d.data()).sort((a: any, b: any) => b.timestamp - a.timestamp);
}

// Full financial document sync. The same Firestore document is consumed by Android.
export async function fbSaveDocument(docData: Document) {
  const user = auth.currentUser;
  if (!user) throw new Error('يجب تسجيل الدخول قبل حفظ المستند');
  await setDoc(doc(db, 'documents', docData.id), {
    ...docData,
    createdByUid: user.uid,
    updatedAt: Date.now()
  }, { merge: true });
  await fbSendNotification(
    `مستند مالي جديد: ${docData.type}`,
    `تم إنشاء ${docData.type} رقم ${docData.documentNumber} بمبلغ ${(docData.amount || 0).toLocaleString()} ريال.`,
    user.email || user.uid,
    docData.type
  );
}

export async function fbDeleteDocument(documentId: string) {
  // Deletion is intentionally not exposed to the UI after rules hardening.
  await updateDoc(doc(db, 'documents', documentId), { isArchived: true, updatedAt: Date.now() });
}

export async function fbGetDocuments(): Promise<Document[]> {
  try {
    const snap = await getDocs(collection(db, 'documents'));
    return snap.docs.map(d => ({ id: d.id, ...d.data() } as unknown as Document));
  } catch (err) {
    console.error('FB Get Documents Error:', err);
    return [];
  }
}

export async function fbCreateUserProfile(uid: string, email: string, fullName = '') {
  await setDoc(doc(db, 'users', uid), {
    id: uid,
    username: email.split('@')[0],
    email,
    fullName: fullName || email,
    role: 'ADMIN_USER',
    active: true,
    createdAt: Date.now()
  }, { merge: true });
}
