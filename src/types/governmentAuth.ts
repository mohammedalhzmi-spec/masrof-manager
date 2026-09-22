export type UserRole = 'SYSTEM_ADMIN' | 'FINANCE_DIRECTOR' | 'ACCOUNTANT' | 'ADMIN_USER';

export type DeviceStatus = 'PENDING' | 'APPROVED' | 'REJECTED' | 'REVOKED';

export interface GovernmentUser {
  id: string;
  username: string;
  fullName: string;
  role: UserRole;
  active: boolean;
  approvalRequired: boolean;
  createdAt: number;
}

export interface DeviceRegistration {
  id: string;
  userId: string;
  installationId: string;
  deviceName: string;
  androidVersion: string;
  appVersion: string;
  status: DeviceStatus;
  lastSeenAt: number;
  approvedBy?: string;
  approvedAt?: number;
  revokedAt?: number;
}

export interface AccessRequest {
  id: string;
  userId: string;
  deviceId: string;
  username: string;
  fullName: string;
  role: UserRole;
  deviceName: string;
  status: DeviceStatus;
  requestedAt: number;
  reviewedBy?: string;
  reviewedAt?: number;
  rejectionReason?: string;
}

export interface GovernmentAuditLog {
  id: string;
  timestamp: number;
  action: 'LOGIN_REQUESTED' | 'LOGIN_APPROVED' | 'LOGIN_REJECTED' | 'DEVICE_REVOKED' | 'USER_SUSPENDED' | 'DOCUMENT_APPROVED' | 'DOCUMENT_PAID';
  details: string;
  username: string;
  deviceId: string;
}
