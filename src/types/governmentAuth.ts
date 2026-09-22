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
  androidVersion?: string;
  appVersion?: string;
  status: DeviceStatus;
  lastSeenAt?: number;
  requestedAt?: number;
  approvedBy?: string;
  approvedAt?: number;
  revokedAt?: number;
  rejectionReason?: string;
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
  action: string;
  details: string;
  username: string;
  deviceId: string;
}
