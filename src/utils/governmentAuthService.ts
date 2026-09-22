import { GovernmentUser, DeviceRegistration, AccessRequest, GovernmentAuditLog, UserRole } from '../types/governmentAuth';

const STORAGE_KEYS = {
  CURRENT_USER: 'masrof_gov_current_user_v2',
  CURRENT_DEVICE_ID: 'masrof_gov_device_id_v2',
};

export function getOrCreateInstallationId(): string {
  let devId = localStorage.getItem(STORAGE_KEYS.CURRENT_DEVICE_ID);
  if (!devId) {
    devId = 'gov_apk_dev_' + Math.random().toString(36).substring(2, 15) + '_' + Date.now().toString(36);
    localStorage.setItem(STORAGE_KEYS.CURRENT_DEVICE_ID, devId);
  }
  return devId;
}

export function getDeviceInfoString(): string {
  const ua = navigator.userAgent;
  let browser = 'Android Official APK Secure Client (v3.0)';
  if (ua.includes('Mobile')) browser = 'Android Enterprise Mobile Client';
  return `${navigator.platform || 'Android OS 14.0'} - ${browser}`;
}

export async function getAllUsers(): Promise<GovernmentUser[]> {
  try {
    const res = await fetch('/api/gov/users');
    const data = await res.json();
    if (data.success) return data.users;
  } catch {}
  return [];
}

export async function getAllDevices(): Promise<DeviceRegistration[]> {
  try {
    const res = await fetch('/api/gov/devices');
    const data = await res.json();
    if (data.success) return data.devices;
  } catch {}
  return [];
}

export async function getAllAccessRequests(): Promise<AccessRequest[]> {
  try {
    const res = await fetch('/api/gov/requests');
    const data = await res.json();
    if (data.success) return data.requests;
  } catch {}
  return [];
}

export async function getAllAuditLogs(): Promise<GovernmentAuditLog[]> {
  try {
    const res = await fetch('/api/gov/audit-logs');
    const data = await res.json();
    if (data.success) return data.auditLogs;
  } catch {}
  return [];
}

export interface LoginAttemptResult {
  success: boolean;
  requiresApproval?: boolean;
  user?: GovernmentUser;
  message?: string;
}

export async function attemptGovernmentLogin(username: string): Promise<LoginAttemptResult> {
  try {
    const res = await fetch('/api/gov/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        username,
        installationId: getOrCreateInstallationId(),
        deviceName: getDeviceInfoString()
      })
    });
    const data = await res.json();
    if (data.success && data.user) {
      localStorage.setItem(STORAGE_KEYS.CURRENT_USER, JSON.stringify(data.user));
      if (data.token) {
        localStorage.setItem('masrof_gov_token', data.token);
      }
      return { success: true, user: data.user };
    } else if (data.requiresApproval) {
      return { success: false, requiresApproval: true, user: data.user, message: data.message };
    } else {
      return { success: false, message: data.message || 'فشل تسجيل الدخول عبر الخادم الحكومي.' };
    }
  } catch (err) {
    return { success: false, message: 'تعذر الاتصال بالخادم الحكومي الخلفي. تأكد من اتصال الشبكة.' };
  }
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
  localStorage.removeItem('masrof_gov_token');
}

export async function directorApproveDevice(deviceId: string, adminUsername: string) {
  try {
    await fetch(`/api/gov/devices/${deviceId}/approve`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ adminUsername })
    });
  } catch {}
}

export async function directorRejectDevice(deviceId: string, adminUsername: string, reason: string) {
  try {
    await fetch(`/api/gov/devices/${deviceId}/reject`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ adminUsername, reason })
    });
  } catch {}
}

export async function directorRevokeDevice(deviceId: string, adminUsername: string) {
  try {
    await fetch(`/api/gov/devices/${deviceId}/revoke`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ adminUsername })
    });
  } catch {}
}
