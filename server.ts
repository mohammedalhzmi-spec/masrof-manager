import express from 'express';
import cors from 'cors';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const app = express();
app.use(cors());
app.use(express.json());

// In-memory / persistent server storage for Government Authentication & Devices
interface GovUser {
  id: string;
  username: string;
  fullName: string;
  role: 'SYSTEM_ADMIN' | 'FINANCE_DIRECTOR' | 'ACCOUNTANT' | 'ADMIN_USER';
  active: boolean;
}

interface DeviceReg {
  id: string;
  userId: string;
  installationId: string;
  deviceName: string;
  status: 'PENDING' | 'APPROVED' | 'REJECTED' | 'REVOKED';
  requestedAt: number;
  approvedBy?: string;
  approvedAt?: number;
  rejectionReason?: string;
}

interface AuditLog {
  id: string;
  timestamp: number;
  action: string;
  details: string;
  username: string;
  deviceId: string;
}

let users: GovUser[] = [
  { id: 'usr_admin', username: 'director', fullName: 'المهندس / مدير النظام العام (السلطة العليا)', role: 'SYSTEM_ADMIN', active: true },
  { id: 'usr_finance', username: 'finance', fullName: 'أ. محمد الحزمي (المدير المالي التنفيذي)', role: 'FINANCE_DIRECTOR', active: true },
  { id: 'usr_accountant', username: 'accountant', fullName: 'أ. أحمد علي (المحاسب الرئيسي)', role: 'ACCOUNTANT', active: true },
  { id: 'usr_staff', username: 'staff', fullName: 'موظف إداري معتمد', role: 'ADMIN_USER', active: true }
];

let devices: DeviceReg[] = [
  {
    id: 'dev_master',
    userId: 'usr_admin',
    installationId: 'master_admin_installation_id',
    deviceName: 'Android 14.0 Official Master Client',
    status: 'APPROVED',
    requestedAt: Date.now() - 86400000 * 5,
    approvedBy: 'مدير النظام العام',
    approvedAt: Date.now() - 86400000 * 5
  }
];

let auditLogs: AuditLog[] = [
  {
    id: 'log_1',
    timestamp: Date.now(),
    action: 'SYSTEM_INIT',
    details: 'إطلاق الخادم الخلفي للنظام المالي الحكومي الموحد (صندوق النظافة والتحسين م/إب)',
    username: 'director',
    deviceId: 'master_admin_installation_id'
  }
];

// Health check
app.get('/api/health', (req, res) => {
  res.json({ status: 'healthy', app: 'Masrof Manager Government Edition API', timestamp: Date.now() });
});

// Get all users
app.get('/api/gov/users', (req, res) => {
  res.json({ success: true, users });
});

// Get all devices
app.get('/api/gov/devices', (req, res) => {
  res.json({ success: true, devices });
});

// Get all requests (PENDING devices)
app.get('/api/gov/requests', (req, res) => {
  const pendingRequests = devices.filter(d => d.status === 'PENDING').map(d => {
    const usr = users.find(u => u.id === d.userId);
    return {
      id: 'req_' + d.id,
      deviceId: d.id,
      userId: d.userId,
      username: usr?.username || 'unknown',
      fullName: usr?.fullName || 'مستخدم غير معروف',
      role: usr?.role || 'ADMIN_USER',
      deviceName: d.deviceName,
      status: d.status,
      requestedAt: d.requestedAt
    };
  });
  res.json({ success: true, requests: pendingRequests });
});

// Get audit logs
app.get('/api/gov/audit-logs', (req, res) => {
  res.json({ success: true, auditLogs });
});

// Attempt Government Login & Device Check
app.post('/api/gov/auth/login', (req, res) => {
  const { username, installationId, deviceName } = req.body;
  if (!username) {
    return res.status(400).json({ success: false, message: 'اسم المستخدم مطلوب' });
  }

  const user = users.find(u => u.username.toLowerCase() === username.toLowerCase());
  if (!user) {
    return res.status(404).json({ success: false, message: 'اسم المستخدم غير موجود في السجلات الحكومية.' });
  }

  if (!user.active) {
    return res.status(403).json({ success: false, message: 'هذا الحساب موقوف أمنياً.' });
  }

  const devId = installationId || 'default_inst_id';
  let device = devices.find(d => d.userId === user.id && d.installationId === devId);

  // System admin auto-approve
  if (user.role === 'SYSTEM_ADMIN' && !device) {
    device = {
      id: 'dev_' + Date.now(),
      userId: user.id,
      installationId: devId,
      deviceName: deviceName || 'Android Official Admin APK',
      status: 'APPROVED',
      requestedAt: Date.now(),
      approvedBy: 'مدير النظام العام',
      approvedAt: Date.now()
    };
    devices.push(device);
  }

  if (!device || device.status === 'PENDING') {
    if (!device) {
      device = {
        id: 'dev_' + Date.now(),
        userId: user.id,
        installationId: devId,
        deviceName: deviceName || 'Android Secure APK Client',
        status: 'PENDING',
        requestedAt: Date.now()
      };
      devices.push(device);
      auditLogs.unshift({
        id: 'log_' + Date.now(),
        timestamp: Date.now(),
        action: 'LOGIN_REQUESTED',
        details: `طلب جهاز جديد للمستخدم ${user.fullName} (${deviceName || 'Android Client'})`,
        username: user.username,
        deviceId: devId
      });
    }

    return res.json({
      success: false,
      requiresApproval: true,
      user,
      message: 'تم إرسال طلب اعتماد الجهاز إلى المدير العام. يرجى الانتظار للموافقة.'
    });
  }

  if (device.status === 'REJECTED') {
    return res.status(403).json({ success: false, message: 'تم رفض اعتماد هذا الجهاز من قبل الإدارة.' });
  }

  if (device.status === 'REVOKED') {
    return res.status(403).json({ success: false, message: 'تم سحب صلاحية هذا الجهاز عن بعد.' });
  }

  auditLogs.unshift({
    id: 'log_' + Date.now(),
    timestamp: Date.now(),
    action: 'LOGIN_APPROVED',
    details: `تسجيل دخول ناجح عبر الجهاز (${device.deviceName})`,
    username: user.username,
    deviceId: devId
  });

  res.json({
    success: true,
    user,
    token: 'gov_jwt_secure_token_' + user.id + '_' + Date.now()
  });
});

// Director Approve Device
app.post('/api/gov/devices/:deviceId/approve', (req, res) => {
  const { deviceId } = req.params;
  const { adminUsername } = req.body;

  const device = devices.find(d => d.id === deviceId || 'req_' + d.id === deviceId || d.id === deviceId.replace('req_', ''));
  if (!device) {
    return res.status(404).json({ success: false, message: 'الجهاز غير موجود' });
  }

  device.status = 'APPROVED';
  device.approvedBy = adminUsername || 'director';
  device.approvedAt = Date.now();

  const usr = users.find(u => u.id === device.userId);
  auditLogs.unshift({
    id: 'log_' + Date.now(),
    timestamp: Date.now(),
    action: 'LOGIN_APPROVED',
    details: `تم اعتماد الجهاز رسمياً للمستخدم ${usr?.fullName || device.userId}`,
    username: adminUsername || 'director',
    deviceId: device.installationId
  });

  res.json({ success: true, message: 'تم اعتماد الجهاز بنجاح', device });
});

// Director Reject Device
app.post('/api/gov/devices/:deviceId/reject', (req, res) => {
  const { deviceId } = req.params;
  const { adminUsername, reason } = req.body;

  const realId = deviceId.replace('req_', '');
  const device = devices.find(d => d.id === realId);
  if (!device) {
    return res.status(404).json({ success: false, message: 'الجهاز غير موجود' });
  }

  device.status = 'REJECTED';
  device.rejectionReason = reason || 'رفض إداري';

  auditLogs.unshift({
    id: 'log_' + Date.now(),
    timestamp: Date.now(),
    action: 'LOGIN_REJECTED',
    details: `تم رفض الجهاز ID: ${realId} - السبب: ${reason}`,
    username: adminUsername || 'director',
    deviceId: device.installationId
  });

  res.json({ success: true, message: 'تم رفض الجهاز', device });
});

// Director Revoke Device
app.post('/api/gov/devices/:deviceId/revoke', (req, res) => {
  const { deviceId } = req.params;
  const { adminUsername } = req.body;

  const device = devices.find(d => d.id === deviceId);
  if (!device) {
    return res.status(404).json({ success: false, message: 'الجهاز غير موجود' });
  }

  device.status = 'REVOKED';

  auditLogs.unshift({
    id: 'log_' + Date.now(),
    timestamp: Date.now(),
    action: 'DEVICE_REVOKED',
    details: `تم إلغاء صلاحية الجهاز ID: ${deviceId} عن بعد`,
    username: adminUsername || 'director',
    deviceId: device.installationId
  });

  res.json({ success: true, message: 'تم إلغاء صلاحية الجهاز بنجاح', device });
});

// Vite middleware setup for development
import { createServer as createViteServer } from 'vite';

async function startServer() {
  const vite = await createViteServer({
    server: { middlewareMode: true },
    appType: 'spa'
  });

  app.use(vite.middlewares);

  const PORT = 3000;
  app.listen(PORT, '0.0.0.0', () => {
    console.log(`Server running on http://localhost:${PORT}`);
  });
}

startServer();
