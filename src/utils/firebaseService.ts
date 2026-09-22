import { initializeApp } from 'firebase/app';
import { getFirestore, collection, getDocs, doc, setDoc, updateDoc, deleteDoc, getDoc, query, orderBy, serverTimestamp } from 'firebase/firestore';
import config from '../../firebase-applet-config.json';

const firebaseApp = initializeApp(config);
export const db = getFirestore(firebaseApp, config.firestoreDatabaseId);

// Firebase Firestore Helpers for Government Roles & Device Approvals
export async function fbGetUsers() {
  try {
    const snap = await getDocs(collection(db, 'users'));
    if (snap.empty) return [];
    return snap.docs.map(d => ({ id: d.id, ...d.data() }));
  } catch (err) {
    console.error('FB Get Users Error:', err);
    return [];
  }
}

export async function fbGetDevices() {
  try {
    const snap = await getDocs(collection(db, 'devices'));
    if (snap.empty) return [];
    return snap.docs.map(d => ({ id: d.id, ...d.data() }));
  } catch (err) {
    console.error('FB Get Devices Error:', err);
    return [];
  }
}

export async function fbGetAccessRequests() {
  try {
    const snap = await getDocs(collection(db, 'devices'));
    if (snap.empty) return [];
    const devices = snap.docs.map(d => ({ id: d.id, ...d.data() })) as any[];
    return devices.filter(d => d.status === 'PENDING').map(d => ({
      id: 'req_' + d.id,
      deviceId: d.id,
      userId: d.userId,
      username: d.username || 'unknown',
      fullName: d.fullName || 'موظف حكومي',
      role: d.role || 'ADMIN_USER',
      deviceName: d.deviceName || 'Android Mobile Device',
      status: d.status,
      requestedAt: d.requestedAt || Date.now()
    }));
  } catch (err) {
    console.error('FB Get Requests Error:', err);
    return [];
  }
}

export async function fbApproveDevice(deviceId: string, adminName: string) {
  try {
    const cleanId = deviceId.replace('req_', '');
    const ref = doc(db, 'devices', cleanId);
    await updateDoc(ref, {
      status: 'APPROVED',
      approvedBy: adminName,
      approvedAt: Date.now()
    });
    return true;
  } catch (err) {
    console.error('FB Approve Device Error:', err);
    return false;
  }
}

export async function fbRejectDevice(deviceId: string, adminName: string, reason: string) {
  try {
    const cleanId = deviceId.replace('req_', '');
    const ref = doc(db, 'devices', cleanId);
    await updateDoc(ref, {
      status: 'REJECTED',
      rejectionReason: reason,
      rejectedBy: adminName,
      rejectedAt: Date.now()
    });
    return true;
  } catch (err) {
    console.error('FB Reject Device Error:', err);
    return false;
  }
}

export async function fbRevokeDevice(deviceId: string, adminName: string) {
  try {
    const ref = doc(db, 'devices', deviceId);
    await updateDoc(ref, {
      status: 'REVOKED',
      revokedBy: adminName,
      revokedAt: Date.now()
    });
    return true;
  } catch (err) {
    console.error('FB Revoke Device Error:', err);
    return false;
  }
}

export async function fbAddAuditLog(action: string, details: string, username: string, deviceId: string) {
  try {
    const logId = 'log_' + Date.now() + '_' + Math.random().toString(36).substring(2, 7);
    await setDoc(doc(db, 'auditLogs', logId), {
      id: logId,
      timestamp: Date.now(),
      action,
      details,
      username,
      deviceId
    });
  } catch (err) {
    console.error('FB Add Audit Log Error:', err);
  }
}

export async function fbGetAuditLogs() {
  try {
    const snap = await getDocs(collection(db, 'auditLogs'));
    if (snap.empty) return [];
    return snap.docs.map(d => d.data()).sort((a: any, b: any) => b.timestamp - a.timestamp);
  } catch (err) {
    console.error('FB Get Audit Logs Error:', err);
    return [];
  }
}
