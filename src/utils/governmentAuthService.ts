import {
  fbAddAuditLog,
  fbApproveDevice,
  fbCurrentUser,
  fbGetAccessRequests,
  fbGetAuditLogs,
  fbGetDevices,
  fbGetUserProfile,
  fbGetUsers,
  fbRejectDevice,
  fbRevokeDevice,
  fbSignIn,
  fbSignOut,
} from './firebaseService';
import { GovernmentUser, DeviceRegistration, AccessRequest, GovernmentAuditLog } from '../types/governmentAuth';

const STORAGE_KEYS = { CURRENT_USER: 'masrof_current_user_v5', CURRENT_DEVICE_ID: 'masrof_device_id_v5' };

export function getOrCreateInstallationId(): string {
  let id = localStorage.getItem(STORAGE_KEYS.CURRENT_DEVICE_ID);
  if (!id) {
    id = `web_${crypto.randomUUID?.() || `${Date.now()}_${Math.random().toString(36).slice(2)}`}`;
    localStorage.setItem(STORAGE_KEYS.CURRENT_DEVICE_ID, id);
  }
  return id;
}

export function getDeviceInfoString() { return `${navigator.platform || 'Web'} - ${navigator.userAgent}`.slice(0, 240); }
export async function getAllUsers(): Promise<GovernmentUser[]> { return (await fbGetUsers()) as GovernmentUser[]; }
export async function getAllDevices(): Promise<DeviceRegistration[]> { return (await fbGetDevices()) as DeviceRegistration[]; }
export async function getAllAccessRequests(): Promise<AccessRequest[]> { return (await fbGetAccessRequests()) as AccessRequest[]; }
export async function getAllAuditLogs(): Promise<GovernmentAuditLog[]> { return (await fbGetAuditLogs()) as GovernmentAuditLog[]; }

export interface LoginAttemptResult { success: boolean; requiresApproval?: boolean; user?: GovernmentUser; message?: string; isDirector?: boolean; }

function normalizeProfile(profile: any, uid: string, email: string): GovernmentUser {
  return {
    id: uid, username: profile?.username || email.split('@')[0], email,
    fullName: profile?.fullName || email, role: profile?.role || 'ADMIN_USER',
    active: profile?.active !== false, approvalRequired: profile?.approvalRequired !== false,
    createdAt: profile?.createdAt || Date.now(),
  } as GovernmentUser;
}

export async function attemptGovernmentLogin(loginInput: string, passwordInput = ''): Promise<LoginAttemptResult> {
  const email = loginInput.trim().toLowerCase();
  if (!email.includes('@')) return { success: false, message: 'استخدم البريد الإلكتروني المرتبط بحساب Firebase.' };
  if (!passwordInput) return { success: false, message: 'كلمة المرور مطلوبة.' };
  try {
    const firebaseUser = await fbSignIn(email, passwordInput);
    const profile = await fbGetUserProfile(firebaseUser.uid);
    const user = normalizeProfile(profile, firebaseUser.uid, firebaseUser.email || email);
    if (!user.active) { await fbSignOut(); return { success: false, message: 'هذا الحساب موقوف.' }; }
    localStorage.setItem(STORAGE_KEYS.CURRENT_USER, JSON.stringify(user));
    await fbAddAuditLog('LOGIN_SUCCESS', 'تسجيل دخول ناجح عبر Firebase Authentication', user.username, getOrCreateInstallationId());
    return { success: true, user, isDirector: user.role === 'SYSTEM_ADMIN' };
  } catch (error: any) {
    console.error('Firebase login failed:', error);
    const code = error?.code || '';
    const message = code.includes('invalid-credential') || code.includes('wrong-password') ? 'البريد الإلكتروني أو كلمة المرور غير صحيحة.' : code.includes('user-not-found') ? 'لا يوجد حساب Firebase بهذا البريد.' : 'تعذر تسجيل الدخول. تحقق من إعداد Firebase Authentication واتصال الشبكة.';
    return { success: false, message };
  }
}

export function getCurrentLoggedInUser(): GovernmentUser | null {
  try { const data = localStorage.getItem(STORAGE_KEYS.CURRENT_USER); return data ? JSON.parse(data) : null; } catch { return null; }
}
export function logoutGovernmentUser() { void fbSignOut().catch(console.error); localStorage.removeItem(STORAGE_KEYS.CURRENT_USER); localStorage.removeItem('masrof_logged_in'); }
export function isFirebaseSessionActive() { return Boolean(fbCurrentUser()); }

export async function directorApproveDevice(deviceId: string, adminUsername: string) { await fbApproveDevice(deviceId, adminUsername); await fbAddAuditLog('DEVICE_APPROVED', `تم اعتماد الجهاز ${deviceId}`, adminUsername, 'admin_panel'); }
export async function directorRejectDevice(deviceId: string, adminUsername: string, reason: string) { await fbRejectDevice(deviceId, adminUsername, reason); await fbAddAuditLog('DEVICE_REJECTED', `تم رفض الجهاز ${deviceId}: ${reason}`, adminUsername, 'admin_panel'); }
export async function directorRevokeDevice(deviceId: string, adminUsername: string) { await fbRevokeDevice(deviceId, adminUsername); await fbAddAuditLog('DEVICE_REVOKED', `تم سحب صلاحية الجهاز ${deviceId}`, adminUsername, 'admin_panel'); }
