import { GovernmentUser, DeviceRegistration, AccessRequest, GovernmentAuditLog, UserRole } from '../types/governmentAuth';

const INITIAL_USERS: GovernmentUser[] = [
  {
    id: 'usr_admin',
    username: 'director',
    fullName: 'المهندس / مدير النظام العام (السلطة العليا)',
    role: 'SYSTEM_ADMIN',
    active: true,
    approvalRequired: false,
    createdAt: Date.now() - 86400000 * 30,
  },
  {
    id: 'usr_finance',
    username: 'finance',
    fullName: 'أ. محمد الحزمي (المدير المالي التنفيذي)',
    role: 'FINANCE_DIRECTOR',
    active: true,
    approvalRequired: true,
    createdAt: Date.now() - 86400000 * 20,
  },
  {
    id: 'usr_accountant',
    username: 'accountant',
    fullName: 'أ. أحمد علي (المحاسب الرئيسي)',
    role: 'ACCOUNTANT',
    active: true,
    approvalRequired: true,
    createdAt: Date.now() - 86400000 * 10,
  },
  {
    id: 'usr_staff',
    username: 'staff',
    fullName: 'موظف إداري معتمد',
    role: 'ADMIN_USER',
    active: true,
    approvalRequired: true,
    createdAt: Date.now() - 86400000 * 5,
  }
];

const STORAGE_KEYS = {
  USERS: 'masrof_gov_users_v2',
  DEVICES: 'masrof_gov_devices_v2',
  REQUESTS: 'masrof_gov_requests_v2',
  AUDIT_LOGS: 'masrof_gov_audit_v2',
  CURRENT_USER: 'masrof_gov_current_user_v2',
  CURRENT_DEVICE_ID: 'masrof_gov_device_id_v2',
};

export function getOrCreateInstallationId(): string {
  let devId = localStorage.getItem(STORAGE_KEYS.CURRENT_DEVICE_ID);
  if (!devId) {
    devId = 'gov_dev_' + Math.random().toString(36).substring(2, 15) + '_' + Date.now().toString(36);
    localStorage.setItem(STORAGE_KEYS.CURRENT_DEVICE_ID, devId);
  }
  return devId;
}

export function getDeviceInfoString(): string {
  const ua = navigator.userAgent;
  let browser = 'تطبيق أندرويد حكومي مؤمن';
  if (ua.includes('Mobile')) browser = 'Android Official APK Client';
  return `${navigator.platform || 'Android OS 14'} - ${browser}`;
}

export function initGovernmentStorage() {
  if (!localStorage.getItem(STORAGE_KEYS.USERS)) {
    localStorage.setItem(STORAGE_KEYS.USERS, JSON.stringify(INITIAL_USERS));
  }
  if (!localStorage.getItem(STORAGE_KEYS.DEVICES)) {
    const defaultDev: DeviceRegistration = {
      id: 'dev_admin_master',
      userId: 'usr_admin',
      installationId: getOrCreateInstallationId(),
      deviceName: getDeviceInfoString(),
      androidVersion: 'Android 14.0 (سلطة الإدارة العليا)',
      appVersion: 'v3.0.0-gov-apk',
      status: 'APPROVED',
      lastSeenAt: Date.now(),
      approvedBy: 'مدير النظام العام',
      approvedAt: Date.now() - 86400000 * 10
    };
    localStorage.setItem(STORAGE_KEYS.DEVICES, JSON.stringify([defaultDev]));
  }
  if (!localStorage.getItem(STORAGE_KEYS.REQUESTS)) {
    localStorage.setItem(STORAGE_KEYS.REQUESTS, JSON.stringify([]));
  }
  if (!localStorage.getItem(STORAGE_KEYS.AUDIT_LOGS)) {
    const initialLog: GovernmentAuditLog = {
      id: 'log_init_' + Date.now(),
      timestamp: Date.now(),
      action: 'LOGIN_APPROVED',
      details: 'تهيئة النظام الحكومي الموحد وتأمين الصلاحيات والأجهزة وتشفير الاتصال.',
      username: 'director',
      deviceId: getOrCreateInstallationId()
    };
    localStorage.setItem(STORAGE_KEYS.AUDIT_LOGS, JSON.stringify([initialLog]));
  }
}

export function getAllUsers(): GovernmentUser[] {
  initGovernmentStorage();
  try {
    return JSON.parse(localStorage.getItem(STORAGE_KEYS.USERS) || '[]');
  } catch {
    return INITIAL_USERS;
  }
}

export function getAllDevices(): DeviceRegistration[] {
  initGovernmentStorage();
  try {
    return JSON.parse(localStorage.getItem(STORAGE_KEYS.DEVICES) || '[]');
  } catch {
    return [];
  }
}

export function getAllAccessRequests(): AccessRequest[] {
  initGovernmentStorage();
  try {
    return JSON.parse(localStorage.getItem(STORAGE_KEYS.REQUESTS) || '[]');
  } catch {
    return [];
  }
}

export function getAllAuditLogs(): GovernmentAuditLog[] {
  initGovernmentStorage();
  try {
    return JSON.parse(localStorage.getItem(STORAGE_KEYS.AUDIT_LOGS) || '[]');
  } catch {
    return [];
  }
}

export function addAuditLog(action: GovernmentAuditLog['action'], details: string, username: string) {
  const logs = getAllAuditLogs();
  const newLog: GovernmentAuditLog = {
    id: 'log_' + Date.now() + '_' + Math.random().toString(36).substring(2, 6),
    timestamp: Date.now(),
    action,
    details,
    username,
    deviceId: getOrCreateInstallationId()
  };
  logs.unshift(newLog);
  localStorage.setItem(STORAGE_KEYS.AUDIT_LOGS, JSON.stringify(logs.slice(0, 300)));
}

export interface LoginAttemptResult {
  success: boolean;
  requiresApproval?: boolean;
  user?: GovernmentUser;
  deviceStatus?: 'APPROVED' | 'PENDING' | 'REJECTED' | 'REVOKED';
  message?: string;
}

export function attemptGovernmentLogin(username: string): LoginAttemptResult {
  initGovernmentStorage();
  const users = getAllUsers();
  const user = users.find(u => u.username.toLowerCase() === username.toLowerCase());
  
  if (!user) {
    return { success: false, message: 'اسم المستخدم أو الرقم الوظيفي غير موجود في سجلات النظام الحكومي المعتمد.' };
  }

  if (!user.active) {
    return { success: false, message: 'هذا الحساب موقوف رسميًا من قبل إدارة النظام والأمن السيبراني.' };
  }

  const installationId = getOrCreateInstallationId();
  const devices = getAllDevices();
  
  let device = devices.find(d => d.userId === user.id && d.installationId === installationId);

  if (user.role === 'SYSTEM_ADMIN' && !device) {
    device = {
      id: 'dev_' + Date.now(),
      userId: user.id,
      installationId,
      deviceName: getDeviceInfoString(),
      androidVersion: 'Android 14.0 (Master APK)',
      appVersion: 'v3.0.0-gov',
      status: 'APPROVED',
      lastSeenAt: Date.now(),
      approvedBy: 'مدير النظام العام',
      approvedAt: Date.now()
    };
    devices.push(device);
    localStorage.setItem(STORAGE_KEYS.DEVICES, JSON.stringify(devices));
  }

  if (!device || device.status === 'PENDING') {
    if (!device) {
      device = {
        id: 'dev_' + Date.now(),
        userId: user.id,
        installationId,
        deviceName: getDeviceInfoString(),
        androidVersion: 'Android 14.0 APK',
        appVersion: 'v3.0.0-gov',
        status: 'PENDING',
        lastSeenAt: Date.now()
      };
      devices.push(device);
      localStorage.setItem(STORAGE_KEYS.DEVICES, JSON.stringify(devices));
    }

    const requests = getAllAccessRequests();
    let req = requests.find(r => r.userId === user.id && r.deviceId === device?.id);
    if (!req) {
      req = {
        id: 'req_' + Date.now(),
        userId: user.id,
        deviceId: device.id,
        username: user.username,
        fullName: user.fullName,
        role: user.role,
        deviceName: device.deviceName,
        status: 'PENDING',
        requestedAt: Date.now()
      };
      requests.unshift(req);
      localStorage.setItem(STORAGE_KEYS.REQUESTS, JSON.stringify(requests));
      addAuditLog('LOGIN_REQUESTED', `طلب دخول وجهاز جديد (${device.deviceName}) للمستخدم ${user.fullName}`, user.username);
    }

    return {
      success: false,
      requiresApproval: true,
      user,
      deviceStatus: 'PENDING',
      message: 'تم إرسال طلب اعتماد الجهاز إلى المدير العام عبر إشعار فورى. لا يمكن استخدام النظام حتى تتم الموافقة على هذا الجهاز.'
    };
  }

  if (device.status === 'REJECTED') {
    return { success: false, deviceStatus: 'REJECTED', message: 'تم رفض طلب اعتماد هذا الجهاز من قبل إدارة النظام.' };
  }

  if (device.status === 'REVOKED') {
    return { success: false, deviceStatus: 'REVOKED', message: 'تم سحب وإلغاء صلاحية هذا الجهاز عن بعد. يرجى تقديم طلب اعتماد جديد.' };
  }

  localStorage.setItem(STORAGE_KEYS.CURRENT_USER, JSON.stringify(user));
  addAuditLog('LOGIN_APPROVED', `تسجيل دخول ناجح عبر الجهاز المعتمد (${device.deviceName})`, user.username);

  return {
    success: true,
    user
  };
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

export function directorApproveDevice(requestId: string, adminUsername: string) {
  const requests = getAllAccessRequests();
  const req = requests.find(r => r.id === requestId);
  if (!req) return;

  req.status = 'APPROVED';
  req.reviewedBy = adminUsername;
  req.reviewedAt = Date.now();
  localStorage.setItem(STORAGE_KEYS.REQUESTS, JSON.stringify(requests));

  const devices = getAllDevices();
  const device = devices.find(d => d.id === req.deviceId);
  if (device) {
    device.status = 'APPROVED';
    device.approvedBy = adminUsername;
    device.approvedAt = Date.now();
    localStorage.setItem(STORAGE_KEYS.DEVICES, JSON.stringify(devices));
  }

  addAuditLog('LOGIN_APPROVED', `تم اعتماد الجهاز والصلاحية رسمياً للمستخدم: ${req.fullName} (@${req.username})`, adminUsername);
}

export function directorRejectDevice(requestId: string, adminUsername: string, reason: string) {
  const requests = getAllAccessRequests();
  const req = requests.find(r => r.id === requestId);
  if (!req) return;

  req.status = 'REJECTED';
  req.reviewedBy = adminUsername;
  req.reviewedAt = Date.now();
  req.rejectionReason = reason;
  localStorage.setItem(STORAGE_KEYS.REQUESTS, JSON.stringify(requests));

  const devices = getAllDevices();
  const device = devices.find(d => d.id === req.deviceId);
  if (device) {
    device.status = 'REJECTED';
    localStorage.setItem(STORAGE_KEYS.DEVICES, JSON.stringify(devices));
  }

  addAuditLog('LOGIN_REJECTED', `تم رفض طلب اعتماد الجهاز للمستخدم: ${req.fullName} - السبب: ${reason}`, adminUsername);
}

export function directorRevokeDevice(deviceId: string, adminUsername: string) {
  const devices = getAllDevices();
  const device = devices.find(d => d.id === deviceId);
  if (device) {
    device.status = 'REVOKED';
    device.revokedAt = Date.now();
    localStorage.setItem(STORAGE_KEYS.DEVICES, JSON.stringify(devices));
  }

  const requests = getAllAccessRequests();
  const req = requests.find(r => r.deviceId === deviceId);
  if (req) {
    req.status = 'REVOKED';
  }
  localStorage.setItem(STORAGE_KEYS.REQUESTS, JSON.stringify(requests));

  addAuditLog('DEVICE_REVOKED', `تم إلغاء وتجميد صلاحية الجهاز ID: ${deviceId} عن بعد`, adminUsername);
}
