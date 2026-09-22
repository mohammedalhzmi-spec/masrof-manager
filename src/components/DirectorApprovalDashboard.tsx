import React, { useState } from 'react';
import { 
  ShieldCheck, ShieldAlert, Users, Smartphone, CheckCircle2, 
  XCircle, AlertTriangle, RefreshCw, LogOut, Trash2, History 
} from 'lucide-react';
import { 
  getAllAccessRequests, getAllDevices, getAllUsers, getAllAuditLogs, 
  directorApproveDevice, directorRejectDevice, directorRevokeDevice, 
  logoutGovernmentUser 
} from '../utils/governmentAuthService';
import { GovernmentUser, AccessRequest, DeviceRegistration, GovernmentAuditLog } from '../types/governmentAuth';

interface Props {
  currentUser: GovernmentUser;
  onLogout: () => void;
  onClose?: () => void;
}

export const DirectorApprovalDashboard: React.FC<Props> = ({ currentUser, onLogout, onClose }) => {
  const [activeTab, setActiveTab] = useState<'requests' | 'devices' | 'users' | 'audit'>('requests');
  const [requests, setRequests] = useState<AccessRequest[]>(getAllAccessRequests());
  const [devices, setDevices] = useState<DeviceRegistration[]>(getAllDevices());
  const [users, setUsers] = useState<GovernmentUser[]>(getAllUsers());
  const [auditLogs, setAuditLogs] = useState<GovernmentAuditLog[]>(getAllAuditLogs());
  const [rejectModalReqId, setRejectModalReqId] = useState<string | null>(null);
  const [rejectionReason, setRejectionReason] = useState('');

  const refreshData = () => {
    setRequests(getAllAccessRequests());
    setDevices(getAllDevices());
    setUsers(getAllUsers());
    setAuditLogs(getAllAuditLogs());
  };

  const handleApprove = (reqId: string) => {
    directorApproveDevice(reqId, currentUser.username);
    refreshData();
  };

  const handleRejectSubmit = (reqId: string) => {
    if (!rejectionReason.trim()) return;
    directorRejectDevice(reqId, currentUser.username, rejectionReason);
    setRejectModalReqId(null);
    setRejectionReason('');
    refreshData();
  };

  const handleRevoke = (deviceId: string) => {
    if (confirm('هل أنت متأكد من إلغاء وتجميد صلاحية هذا الجهاز نهائياً عن بعد؟')) {
      directorRevokeDevice(deviceId, currentUser.username);
      refreshData();
    }
  };

  const pendingRequests = requests.filter(r => r.status === 'PENDING');

  return (
    <div className="fixed inset-0 z-50 bg-slate-950/90 backdrop-blur-md flex flex-col overflow-hidden text-right rtl animate-fadeIn">
      {/* Top Navbar */}
      <header className="bg-gradient-to-r from-emerald-950 via-emerald-900 to-slate-950 text-white px-6 py-4 flex items-center justify-between shadow-xl border-b border-emerald-700/50">
        <div className="flex items-center gap-3">
          <div className="w-12 h-12 bg-emerald-800 rounded-2xl flex items-center justify-center border border-emerald-500 shadow-inner">
            <ShieldCheck className="w-7 h-7 text-emerald-200" />
          </div>
          <div>
            <h1 className="text-lg font-black">لوحة تحكم المدير العام والأمن الرقمي (APK Command Center)</h1>
            <p className="text-xs text-emerald-300">صندوق النظافة والتحسين م/إب - نظام الاعتماد والرقابة المركزية للأجهزة</p>
          </div>
        </div>

        <div className="flex items-center gap-4">
          <div className="text-left hidden md:block">
            <div className="text-sm font-bold text-emerald-100">{currentUser.fullName}</div>
            <div className="text-xs text-emerald-300 font-mono">سلطة الإدارة العليا (System Admin)</div>
          </div>
          <button
            onClick={() => { logoutGovernmentUser(); onLogout(); }}
            className="bg-red-600 hover:bg-red-700 text-white px-4 py-2.5 rounded-2xl text-sm font-bold transition flex items-center gap-2 shadow cursor-pointer"
          >
            <LogOut className="w-4 h-4" />
            <span>تسجيل خروج</span>
          </button>
          {onClose && (
            <button
              onClick={onClose}
              className="bg-white/10 hover:bg-white/20 text-white px-4 py-2.5 rounded-2xl text-sm font-medium transition cursor-pointer"
            >
              العودة للنظام المالي
            </button>
          )}
        </div>
      </header>

      {/* Subbar Navigation */}
      <nav className="bg-white border-b border-slate-200 px-6 flex gap-2 overflow-x-auto shadow-sm">
        <button
          onClick={() => setActiveTab('requests')}
          className={`py-3.5 px-6 font-bold text-sm border-b-2 transition flex items-center gap-2 relative ${
            activeTab === 'requests' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <ShieldAlert className="w-4 h-4" />
          <span>طلبات اعتماد الأجهزة المعلقة</span>
          {pendingRequests.length > 0 && (
            <span className="bg-red-600 text-white text-xs font-black px-2 py-0.5 rounded-full animate-bounce shadow">
              {pendingRequests.length}
            </span>
          )}
        </button>

        <button
          onClick={() => setActiveTab('devices')}
          className={`py-3.5 px-6 font-bold text-sm border-b-2 transition flex items-center gap-2 ${
            activeTab === 'devices' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <Smartphone className="w-4 h-4" />
          <span>الأجهزة المصرحة والنشطة ({devices.length})</span>
        </button>

        <button
          onClick={() => setActiveTab('users')}
          className={`py-3.5 px-6 font-bold text-sm border-b-2 transition flex items-center gap-2 ${
            activeTab === 'users' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <Users className="w-4 h-4" />
          <span>إدارة حسابات الإداريين ({users.length})</span>
        </button>

        <button
          onClick={() => setActiveTab('audit')}
          className={`py-3.5 px-6 font-bold text-sm border-b-2 transition flex items-center gap-2 ${
            activeTab === 'audit' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <History className="w-4 h-4" />
          <span>سجل التدقيق الأمني (Audit Logs)</span>
        </button>
      </nav>

      {/* Main Content */}
      <main className="flex-1 overflow-y-auto p-6 bg-slate-100">
        <div className="max-w-7xl mx-auto space-y-6">

          {/* PENDING REQUESTS */}
          {activeTab === 'requests' && (
            <div className="space-y-4">
              <div className="flex items-center justify-between">
                <div>
                  <h2 className="text-xl font-black text-slate-900">طلبات اعتماد الأجهزة الجديدة (FCM Push)</h2>
                  <p className="text-sm text-slate-500">مراجعة واعتماد دخول الإداريين من أجهزة Android جديدة أو بعد إعادة تثبيت التطبيق.</p>
                </div>
                <button
                  onClick={refreshData}
                  className="bg-white border border-slate-300 hover:bg-slate-50 text-slate-700 px-4 py-2.5 rounded-2xl text-sm font-bold flex items-center gap-2 shadow-sm cursor-pointer"
                >
                  <RefreshCw className="w-4 h-4" />
                  <span>تحديث القائمة</span>
                </button>
              </div>

              {pendingRequests.length === 0 ? (
                <div className="bg-white rounded-3xl p-12 text-center border border-slate-200 shadow-sm space-y-3">
                  <CheckCircle2 className="w-16 h-16 text-emerald-600 mx-auto" />
                  <h3 className="text-lg font-bold text-slate-800">لا توجد طلبات اعتماد معلقة حالياً</h3>
                  <p className="text-slate-500 text-sm max-w-md mx-auto">
                    جميع الأجهزة الحالية معتمدة ومصرح لها بالعمل. ستظهر هنا فوراً أي محاولة تسجيل دخول جديدة من أي جهاز APK.
                  </p>
                </div>
              ) : (
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  {pendingRequests.map(req => (
                    <div key={req.id} className="bg-white rounded-3xl p-6 border border-amber-300 shadow-lg space-y-4 relative overflow-hidden">
                      <div className="absolute top-0 right-0 w-2.5 h-full bg-amber-500"></div>
                      
                      <div className="flex items-start justify-between">
                        <div>
                          <span className="text-xs bg-amber-100 text-amber-800 font-bold px-3 py-1 rounded-full border border-amber-200">
                            طلب جهاز جديد بانتظار الموافقة
                          </span>
                          <h3 className="text-lg font-black text-slate-900 mt-2">{req.fullName}</h3>
                          <p className="text-xs text-slate-500 font-mono">اسم المستخدم: @{req.username} | الدور: {req.role}</p>
                        </div>
                        <div className="text-left text-xs text-slate-400 font-mono">
                          {new Date(req.requestedAt).toLocaleString('ar-SA')}
                        </div>
                      </div>

                      <div className="bg-slate-50 rounded-2xl p-4 border border-slate-200 text-sm space-y-2">
                        <div className="flex items-center gap-2 text-slate-700">
                          <Smartphone className="w-4 h-4 text-slate-500" />
                          <strong>بيانات الجهاز:</strong> {req.deviceName}
                        </div>
                        <div className="font-mono text-xs text-slate-500 truncate bg-white p-2 rounded-xl border border-slate-200">
                          ID: {req.deviceId}
                        </div>
                      </div>

                      <div className="flex items-center gap-3 pt-2">
                        <button
                          onClick={() => handleApprove(req.id)}
                          className="flex-1 bg-emerald-700 hover:bg-emerald-800 text-white font-bold py-3 px-4 rounded-2xl transition flex items-center justify-center gap-2 shadow cursor-pointer"
                        >
                          <CheckCircle2 className="w-5 h-5" />
                          <span>موافقة واعتماد الجهاز</span>
                        </button>
                        <button
                          onClick={() => setRejectModalReqId(req.id)}
                          className="bg-red-50 hover:bg-red-100 text-red-700 font-bold py-3 px-4 rounded-2xl transition flex items-center justify-center gap-2 border border-red-200 cursor-pointer"
                        >
                          <XCircle className="w-5 h-5" />
                          <span>رفض</span>
                        </button>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          )}

          {/* DEVICES TAB */}
          {activeTab === 'devices' && (
            <div className="space-y-4">
              <div>
                <h2 className="text-xl font-black text-slate-900">إدارة الأجهزة المسجلة والمصرحة</h2>
                <p className="text-sm text-slate-500">قائمة كاملة بالأجهزة المرتبطة بالنظام مع صلاحية إلغاء أو تجميد أي جهاز مفقود أو غير موثوق.</p>
              </div>

              <div className="bg-white rounded-3xl shadow-sm border border-slate-200 overflow-hidden">
                <div className="overflow-x-auto">
                  <table className="w-full text-right border-collapse">
                    <thead>
                      <tr className="bg-slate-50 border-b border-slate-200 text-slate-700 text-xs font-black">
                        <th className="p-4">الجهاز والتعريف</th>
                        <th className="p-4">المستخدم المرتبط</th>
                        <th className="p-4">حالة الجهاز</th>
                        <th className="p-4">تاريخ الاعتماد</th>
                        <th className="p-4">الإجراءات الأمنية</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-slate-100 text-sm">
                      {devices.map(d => {
                        const usr = users.find(u => u.id === d.userId);
                        return (
                          <tr key={d.id} className="hover:bg-slate-50/50">
                            <td className="p-4">
                              <div className="font-bold text-slate-900">{d.deviceName}</div>
                              <div className="text-xs font-mono text-slate-400">{d.installationId}</div>
                            </td>
                            <td className="p-4">
                              <div className="font-bold text-slate-800">{usr?.fullName || 'مستخدم غير معروف'}</div>
                              <div className="text-xs text-slate-500 font-mono">@{usr?.username} ({usr?.role})</div>
                            </td>
                            <td className="p-4">
                              {d.status === 'APPROVED' && (
                                <span className="bg-emerald-100 text-emerald-800 text-xs font-bold px-3 py-1 rounded-full">معتمد ونشط</span>
                              )}
                              {d.status === 'PENDING' && (
                                <span className="bg-amber-100 text-amber-800 text-xs font-bold px-3 py-1 rounded-full">قيد الانتظار</span>
                              )}
                              {d.status === 'REJECTED' && (
                                <span className="bg-red-100 text-red-800 text-xs font-bold px-3 py-1 rounded-full">مرفوض</span>
                              )}
                              {d.status === 'REVOKED' && (
                                <span className="bg-slate-200 text-slate-700 text-xs font-bold px-3 py-1 rounded-full">ملغي الصلاحية</span>
                              )}
                            </td>
                            <td className="p-4 text-xs font-mono text-slate-500">
                              {d.approvedAt ? new Date(d.approvedAt).toLocaleString('ar-SA') : 'غير معتمد'}
                            </td>
                            <td className="p-4">
                              {d.status === 'APPROVED' && (
                                <button
                                  onClick={() => handleRevoke(d.id)}
                                  className="bg-red-50 hover:bg-red-100 text-red-700 px-3 py-2 rounded-xl text-xs font-bold transition flex items-center gap-1 border border-red-200 cursor-pointer"
                                >
                                  <Trash2 className="w-3.5 h-3.5" />
                                  <span>إلغاء الصلاحية عن بعد</span>
                                </button>
                              )}
                            </td>
                          </tr>
                        );
                      })}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          )}

          {/* USERS TAB */}
          {activeTab === 'users' && (
            <div className="space-y-4">
              <div>
                <h2 className="text-xl font-black text-slate-900">حسابات الإداريين والأدوار الوظيفية الرسمية</h2>
                <p className="text-sm text-slate-500">الصلاحيات المعتمدة في النظام المالي الحكومي الموحد.</p>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {users.map(u => (
                  <div key={u.id} className="bg-white rounded-3xl p-6 border border-slate-200 shadow-sm space-y-3">
                    <div className="flex items-center justify-between">
                      <span className="text-xs bg-emerald-100 text-emerald-800 font-bold px-3 py-1 rounded-full font-mono">
                        {u.role}
                      </span>
                      <span className="text-xs text-slate-400 font-mono">ID: {u.id}</span>
                    </div>
                    <div>
                      <h3 className="text-lg font-black text-slate-900">{u.fullName}</h3>
                      <p className="text-sm text-slate-500 font-mono">اسم المستخدم: @{u.username}</p>
                    </div>
                    <div className="pt-2 border-t border-slate-100 flex items-center justify-between text-xs text-slate-500">
                      <span>الحالة: نشط ومصرح أمنياً</span>
                      <span>تاريخ الانشاء: {new Date(u.createdAt).toLocaleDateString('ar-SA')}</span>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* AUDIT LOGS TAB */}
          {activeTab === 'audit' && (
            <div className="space-y-4">
              <div>
                <h2 className="text-xl font-black text-slate-900">سجل التدقيق الأمني والحكومي (Audit Trail)</h2>
                <p className="text-sm text-slate-500">سجل مشفر غير قابل للتلاعب يوثق كافة عمليات الدخول واعتماد الأجهزة والعمليات المالية.</p>
              </div>

              <div className="bg-white rounded-3xl shadow-sm border border-slate-200 overflow-hidden">
                <div className="overflow-x-auto">
                  <table className="w-full text-right border-collapse">
                    <thead>
                      <tr className="bg-slate-50 border-b border-slate-200 text-slate-700 text-xs font-black">
                        <th className="p-4">التوقيت والتاريخ</th>
                        <th className="p-4">نوع الحدث</th>
                        <th className="p-4">المستخدم</th>
                        <th className="p-4">التفاصيل التقنية والأمنية</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-slate-100 text-sm">
                      {auditLogs.map(log => (
                        <tr key={log.id} className="hover:bg-slate-50/50">
                          <td className="p-4 text-xs font-mono text-slate-500">
                            {new Date(log.timestamp).toLocaleString('ar-SA')}
                          </td>
                          <td className="p-4">
                            <span className="bg-slate-100 text-slate-800 font-mono text-xs font-bold px-2.5 py-1 rounded-lg">
                              {log.action}
                            </span>
                          </td>
                          <td className="p-4 font-bold text-slate-800">@{log.username}</td>
                          <td className="p-4 text-slate-600">{log.details}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          )}

        </div>
      </main>

      {/* REJECTION REASON MODAL */}
      {rejectModalReqId && (
        <div className="fixed inset-0 z-50 bg-black/75 backdrop-blur-sm flex items-center justify-center p-4">
          <div className="bg-white rounded-3xl shadow-2xl max-w-md w-full p-6 space-y-4">
            <h3 className="text-lg font-black text-slate-900 flex items-center gap-2">
              <AlertTriangle className="w-6 h-6 text-red-600" />
              <span>سبب رفض اعتماد الجهاز</span>
            </h3>
            <p className="text-sm text-slate-600">
              يرجى كتابة سبب رفض اعتماد هذا الجهاز لكي يظهر للمستخدم في شاشة التطبيق:
            </p>
            <textarea
              value={rejectionReason}
              onChange={e => setRejectionReason(e.target.value)}
              placeholder="مثال: الجهاز غير مصرح به أو تم الاشتباه في محاولة دخول غير مرخصة..."
              className="w-full p-3.5 border border-slate-300 rounded-2xl text-sm focus:ring-2 focus:ring-red-600 outline-none h-32"
            ></textarea>
            <div className="flex gap-3 pt-2">
              <button
                onClick={() => handleRejectSubmit(rejectModalReqId)}
                className="flex-1 bg-red-600 hover:bg-red-700 text-white font-bold py-3 rounded-2xl transition shadow cursor-pointer"
              >
                تأكيد الرفض وتجميد الجهاز
              </button>
              <button
                onClick={() => setRejectModalReqId(null)}
                className="bg-slate-100 hover:bg-slate-200 text-slate-700 font-bold py-3 px-4 rounded-2xl transition cursor-pointer"
              >
                إلغاء
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};
