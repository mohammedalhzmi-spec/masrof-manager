import { initializeApp } from 'firebase/app';
import { getFirestore, collection, getDocs, doc, setDoc, updateDoc, deleteDoc, getDoc, query, orderBy, serverTimestamp } from 'firebase/firestore';
import config from '../../firebase-applet-config.json';

const firebaseApp = initializeApp(config);
export const db = getFirestore(firebaseApp, config.firestoreDatabaseId);

// Government User & Device Approvals
export async function fbGetUsers() {
  try {
    const snap = await getDocs(collection(db, 'users'));
    if (snap.empty) {
      // Seed default users including Director alhzmim57@gmail.com
      const defaultUsers = [
        { id: 'usr_director', username: 'director', email: 'alhzmim57@gmail.com', fullName: 'المهندس / محمد الحزمي (مدير النظام العام - السلطة العليا)', role: 'SYSTEM_ADMIN', active: true, approvalRequired: false, createdAt: Date.now() },
        { id: 'usr_finance', username: 'finance', email: 'finance@clean-ibb.gov.ye', fullName: 'أ. المدير المالي التنفيذي', role: 'FINANCE_DIRECTOR', active: true, approvalRequired: true, createdAt: Date.now() },
        { id: 'usr_accountant', username: 'accountant', email: 'accountant@clean-ibb.gov.ye', fullName: 'أ. المحاسب الرئيسي', role: 'ACCOUNTANT', active: true, approvalRequired: true, createdAt: Date.now() },
        { id: 'usr_staff', username: 'staff', email: 'staff@clean-ibb.gov.ye', fullName: 'موظف إداري معتمد', role: 'ADMIN_USER', active: true, approvalRequired: true, createdAt: Date.now() }
      ];
      for (const u of defaultUsers) {
        await setDoc(doc(db, 'users', u.id), u);
      }
      return defaultUsers;
    }
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

// Firebase Cloud Messaging (FCM) & Notifications for Director
export async function fbSendNotification(title: string, body: string, sender: string, docType?: string) {
  try {
    const notifId = 'notif_' + Date.now() + '_' + Math.random().toString(36).substring(2, 7);
    const now = new Date();
    const notificationData = {
      id: notifId,
      title,
      body,
      sender,
      docType: docType || 'GENERAL',
      timestamp: now.getTime(),
      dayName: now.toLocaleDateString('ar-SA', { weekday: 'long' }),
      dateString: now.toLocaleDateString('ar-SA'),
      read: false
    };
    await setDoc(doc(db, 'notifications', notifId), notificationData);
  } catch (err) {
    console.error('FB Send Notification Error:', err);
  }
}

export async function fbGetNotifications() {
  try {
    const snap = await getDocs(collection(db, 'notifications'));
    if (snap.empty) return [];
    return snap.docs.map(d => d.data()).sort((a: any, b: any) => b.timestamp - a.timestamp);
  } catch (err) {
    console.error('FB Get Notifications Error:', err);
    return [];
  }
}

// Financial Documents Sync (Disbursement orders, payment books, serial numbers, day, date)
export async function fbSaveDocument(docData: {
  id: string;
  serialNumber: string;
  type: string; // 'امر صرف' | 'دفتر صرف' | 'سند قبض' | 'قيد محاسبي'
  title: string;
  amount: number;
  beneficiary: string;
  createdBy: string;
  dayName: string;
  dateString: string;
  status: string;
}) {
  try {
    await setDoc(doc(db, 'documents', docData.id), {
      ...docData,
      updatedAt: Date.now()
    });
    // Trigger FCM Notification to Director
    await fbSendNotification(
      `مستند مالي جديد: ${docData.type}`,
      `قام المستخدم (${docData.createdBy}) بإنشاء ${docData.type} رقم برقم تسلسلي (${docData.serialNumber}) بمبلغ ${docData.amount.toLocaleString()} ريال.`,
      docData.createdBy,
      docData.type
    );
  } catch (err) {
    console.error('FB Save Document Error:', err);
  }
}

export async function fbGetDocuments() {
  try {
    const snap = await getDocs(collection(db, 'documents'));
    if (snap.empty) return [];
    return snap.docs.map(d => d.data());
  } catch (err) {
    console.error('FB Get Documents Error:', err);
    return [];
  }
}
