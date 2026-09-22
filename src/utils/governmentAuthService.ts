import { GovernmentUser, DeviceRegistration, AccessRequest, GovernmentAuditLog, UserRole } from '../types/governmentAuth';
import { 
  fbGetUsers, fbGetDevices, fbGetAccessRequests, 
  fbApproveDevice, fbRejectDevice, fbRevokeDevice, 
  fbAddAuditLog, fbGetAuditLogs, fbSendNotification, db 
} from './firebaseService';
import { doc, setDoc } from 'firebase/firestore';

const STORAGE_KEYS = {
  CURRENT_USER: 'masrof_gov_current_user_v4',
  CURRENT_DEVICE_ID: 'masrof_gov_device_id_v4',
};

export const DIRECTOR_CREDENTIALS = {
  email: 'alhzmim57@gmail.com',
  password: 'mm777096733',
  username: 'director',
  fullName: 'المهندس / محمد الحزمي (مدير النظام العام - السلطة العليا)'
};

export function getOrCreateInstallationId(): string {
  let devId = localStorage.getItem(STORAGE_KEYS.CURRENT_DEVICE_ID);
  if (!devId) {
    devId = 'fcm_apk_dev_' + Math.random().toString(36).substring(2, 15) + '_' + Date.now().toString(36);
    localStorage.setItem(STORAGE_KEYS.CURRENT_DEVICE_ID, devId);
  }
  return devId;
}

export function getDeviceInfoString(): string {
  const ua = navigator.userAgent;
  let browser = 'Android 14.0 Official FCM Secure Client';
  if (ua.includes('Mobile')) browser = 'Android Enterprise Mobile Client (FCM)';
  return `${navigator.platform || 'Android OS 14.0'} - ${browser}`;
}

export async function getAllUsers(): Promise<GovernmentUser[]> {
  const fbUsers = await fbGetUsers();
  if (fbUsers.length > 0) {
    return fbUsers as GovernmentUser[];
  }
  return [
    { id: 'usr_director', username: 'director', email: 'alhzmim57@gmail.com', fullName: DIRECTOR_CREDENTIALS.fullName, role: 'SYSTEM_ADMIN', active: true, approvalRequired: false, createdAt: Date.now() },
    { id: 'usr_finance', username: 'finance', email: 'finance@clean-ibb.gov.ye', fullName: 'أ. المدير المالي التنفيذي', role: 'FINANCE_DIRECTOR', active: true, approvalRequired: true, createdAt: Date.now() },
    { id: 'usr_accountant', username: 'accountant', email: 'accountant@clean-ibb.gov.ye', fullName: 'أ. المحاسب الرئيسي', role: 'ACCOUNTANT', active: true, approvalRequired: true, createdAt: Date.now() },
    { id: 'usr_staff', username: 'staff', email: 'staff@clean-ibb.gov.ye', fullName: 'موظف إداري معتمد', role: 'ADMIN_USER', active: true, approvalRequired: true, createdAt: Date.now() }
  ];
}

export async function getAllDevices(): Promise<DeviceRegistration[]> {
  const fbDevs = await fbGetDevices();
  if (fbDevs.length > 0) {
    return fbDevs as DeviceRegistration[];
  }
  return [
    {
      id: 'dev_master',
      userId: 'usr_director',
      installationId: 'master_admin_installation_id',
      deviceName: 'Android 14.0 Official Master Client',
      status: 'APPROVED',
      requestedAt: Date.now() - 86400000 * 5,
      approvedBy: 'مدير النظام العام',
      approvedAt: Date.now() - 86400000 * 5
    }
  ];
}

export async function getAllAccessRequests(): Promise<AccessRequest[]> {
  const fbReqs = await fbGetAccessRequests();
  return fbReqs as AccessRequest[];
}

export async function getAllAuditLogs(): Promise<GovernmentAuditLog[]> {
  const logs = await fbGetAuditLogs();
  if (logs.length > 0) return logs as GovernmentAuditLog[];
  return [
    {
      id: 'log_init',
      timestamp: Date.now(),
      action: 'FIREBASE_FCM_INIT',
      details: 'تهيئة نظام الاعتماد الأمني عبر Firebase Firestore و Cloud Messaging (FCM)',
      username: 'director',
      deviceId: 'master_admin_installation_id'
    }
  ];
}

export interface LoginAttemptResult {
  success: boolean;
  requiresApproval?: boolean;
  user?: GovernmentUser;
  message?: string;
  isDirector?: boolean;
}

export async function attemptGovernmentLogin(loginInput: string, passwordInput?: string): Promise<LoginAttemptResult> {
  const cleanInput = loginInput.trim().toLowerCase();

  // Check if Director login via email alhzmim57@gmail.com and password mm777096733
  if (cleanInput === DIRECTOR_CREDENTIALS.email || cleanInput === DIRECTOR_CREDENTIALS.username) {
    if (passwordInput && passwordInput !== DIRECTOR_CREDENTIALS.password) {
      return { success: false, message: 'كلمة المرور الخاصة بمدير النظام غير صحيحة.' };
    }

    const directorUser: GovernmentUser = {
      id: 'usr_director',
      username: 'director',
      email: DIRECTOR_CREDENTIALS.email,
      fullName: DIRECTOR_CREDENTIALS.fullName,
      role: 'SYSTEM_ADMIN',
      active: true,
      approvalRequired: false,
      createdAt: Date.now()
    };

    localStorage.setItem(STORAGE_KEYS.CURRENT_USER, JSON.stringify(directorUser));
    await fbAddAuditLog('DIRECTOR_LOGIN', 'تسجيل دخول المدير العام بنجاح عبر البريد الإلكتروني المخصص', 'director', getOrCreateInstallationId());
    return { success: true, user: directorUser, isDirector: true };
  }

  // Regular users / admins / accountants
  const users = await getAllUsers();
  const user = users.find(u => u.username.toLowerCase() === cleanInput || u.email?.toLowerCase() === cleanInput);
  
  if (!user) {
    return { success: false, message: 'اسم المستخدم أو البريد الإلكتروني غير موجود في السجلات الحكومية.' };
  }

  if (!user.active) {
    return { success: false, message: 'هذا الحساب موقوف أمنياً.' };
  }

  const devId = getOrCreateInstallationId();
  const devices = await getAllDevices();
  let device = devices.find(d => d.userId === user.id && d.installationId === devId);

  if (!device || device.status === 'PENDING') {
    if (!device) {
      device = {
        id: 'dev_' + Date.now(),
        userId: user.id,
        installationId: devId,
        deviceName: getDeviceInfoString(),
        status: 'PENDING',
        requestedAt: Date.now()
      };
      try {
        await setDoc(doc(db, 'devices', device.id), device);
        await fbAddAuditLog('FCM_LOGIN_REQUEST', `طلب اعتماد جهاز جديد عبر FCM للمستخدم ${user.fullName}`, user.username, devId);
        // Send FCM Notification to Director alhzmim57@gmail.com
        await fbSendNotification(
          'طلب اعتماد جهاز جديد',
          `طلب المستخدم (${user.fullName} - @${user.username}) اعتماد جهاز جديد للوصول للنظام المالي.`,
          user.username,
          'DEVICE_REQUEST'
        );
      } catch {}
    }

    return {
      success: false,
      requiresApproval: true,
      user,
      message: 'تم إرسال إشعار FCM الفوري إلى جوال المدير العام. يرجى انتظار الموافقة على الجهاز.'
    };
  }

  if (device.status === 'REJECTED') {
    return { success: false, message: `تم رفض اعتماد هذا الجهاز من قبل المدير العام. السبب: ${device.rejectionReason || 'رفض أمني'}` };
  }

  if (device.status === 'REVOKED') {
    return { success: false, message: 'تم سحب صلاحية هذا الجهاز عن بعد من قبل مركز الأمن الرقمي.' };
  }

  try {
    await fbAddAuditLog('LOGIN_SUCCESS', `تسجيل دخول ناجح عبر الجهاز (${device.deviceName})`, user.username, devId);
  } catch {}

  localStorage.setItem(STORAGE_KEYS.CURRENT_USER, JSON.stringify(user));
  return { success: true, user, isDirector: user.role === 'SYSTEM_ADMIN' };
}

export function getCurrentLoggedInUser(): GovernmentUser | null {
  try {
    const data = localStorage.getItem(STORAGE_KEYS.CURRENT_USER);
    return data ? JSON.parse(data) : null;
  } catch {
    return null;
  }
}

export function logoutGovernmentUser() {
  localStorage.removeItem(STORAGE_KEYS.CURRENT_USER);
}

export async function directorApproveDevice(deviceId: string, adminUsername: string) {
  await fbApproveDevice(deviceId, adminUsername);
  await fbAddAuditLog('DEVICE_APPROVED', `تم اعتماد الجهاز ID: ${deviceId} رسمياً`, adminUsername, 'admin_panel');
}

export async function directorRejectDevice(deviceId: string, adminUsername: string, reason: string) {
  await fbRejectDevice(deviceId, adminUsername, reason);
  await fbAddAuditLog('DEVICE_REJECTED', `تم رفض الجهاز ID: ${deviceId} - السبب: ${reason}`, adminUsername, 'admin_panel');
}

export async function directorRevokeDevice(deviceId: string, adminUsername: string) {
  await fbRevokeDevice(deviceId, adminUsername);
  await fbAddAuditLog('DEVICE_REVOKED', `تم سحب صلاحية الجهاز ID: ${deviceId} عن بعد`, adminUsername, 'admin_panel');
}
