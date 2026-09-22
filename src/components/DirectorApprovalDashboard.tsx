import React, { useState, useEffect } from 'react';
import { 
  ShieldCheck, ShieldAlert, Users, Smartphone, CheckCircle2, 
  XCircle, AlertTriangle, RefreshCw, LogOut, Trash2, History, Bell, FileText, Layers, Calendar 
} from 'lucide-react';
import { 
  getAllAccessRequests, getAllDevices, getAllUsers, getAllAuditLogs, 
  directorApproveDevice, directorRejectDevice, directorRevokeDevice, 
  logoutGovernmentUser 
} from '../utils/governmentAuthService';
import { fbGetNotifications, fbGetDocuments } from '../utils/firebaseService';
import { GovernmentUser, AccessRequest, DeviceRegistration, GovernmentAuditLog } from '../types/governmentAuth';

interface Props {
  currentUser: GovernmentUser;
  onLogout: () => void;
  onClose?: () => void;
}

export const DirectorApprovalDashboard: React.FC<Props> = ({ currentUser, onLogout, onClose }) => {
  const [activeTab, setActiveTab] = useState<'requests' | 'devices' | 'users' | 'audit' | 'notifications' | 'documents'>('requests');
  const [requests, setRequests] = useState<AccessRequest[]>([]);
  const [devices, setDevices] = useState<DeviceRegistration[]>([]);
  const [users, setUsers] = useState<GovernmentUser[]>([]);
  const [auditLogs, setAuditLogs] = useState<GovernmentAuditLog[]>([]);
  const [notifications, setNotifications] = useState<any[]>([]);
  const [documents, setDocuments] = useState<any[]>([]);
  
  const [rejectModalReqId, setRejectModalReqId] = useState<string | null>(null);
  const [rejectionReason, setRejectionReason] = useState('');

  const refreshData = async () => {
    const [reqs, devs, usrs, logs, notifs, docs] = await Promise.all([
      getAllAccessRequests(),
      getAllDevices(),
      getAllUsers(),
      getAllAuditLogs(),
      fbGetNotifications(),
      fbGetDocuments()
    ]);
    setRequests(reqs);
    setDevices(devs);
    setUsers(usrs);
    setAuditLogs(logs);
    setNotifications(notifs);
    setDocuments(docs);
  };

  useEffect(() => {
    refreshData();
    const interval = setInterval(refreshData, 5000); // Poll Firebase every 5s for FCM updates & new docs
    return () => clearInterval(interval);
  }, []);

  const handleApprove = async (reqId: string) => {
    await directorApproveDevice(reqId, currentUser.username);
    await refreshData();
  };

  const handleRejectSubmit = async (reqId: string) => {
    if (!rejectionReason.trim()) return;
    await directorRejectDevice(reqId, currentUser.username, rejectionReason);
    setRejectModalReqId(null);
    setRejectionReason('');
    await refreshData();
  };

  const handleRevoke = async (deviceId: string) => {
    if (confirm('هل أنت متأكد من إلغاء وتجميد صلاحية هذا الجهاز نهائياً عن بعد؟')) {
      await directorRevokeDevice(deviceId, currentUser.username);
      await refreshData();
    }
  };

  const pendingRequests = requests.filter(r => r.status === 'PENDING');
  const unreadNotifsCount = notifications.filter(n => !n.read).length;

  return (
    <div className="fixed inset-0 z-50 bg-slate-950/90 backdrop-blur-md flex flex-col overflow-hidden text-right rtl animate-fadeIn">
      {/* Top Navbar */}
      <header className="bg-gradient-to-r from-emerald-950 via-emerald-900 to-slate-950 text-white px-6 py-4 flex items-center justify-between shadow-xl border-b border-emerald-700/50">
        <div className="flex items-center gap-3">
          <div className="w-12 h-12 bg-emerald-800 rounded-2xl flex items-center justify-center border border-emerald-500 shadow-inner">
            <ShieldCheck className="w-7 h-7 text-emerald-200" />
          </div>
          <div>
            <h1 className="text-lg font-black">لوحة تحكم المدير العام والأمن الرقمي (Firebase FCM)</h1>
            <p className="text-xs text-emerald-300">صندوق النظافة والتحسين م/إب - البريد المعتمد: {currentUser.email || 'alhzmim57@gmail.com'}</p>
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
          className={`py-3.5 px-5 font-bold text-sm border-b-2 transition flex items-center gap-2 relative ${
            activeTab === 'requests' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <ShieldAlert className="w-4 h-4" />
          <span>طلبات اعتماد الأجهزة</span>
          {pendingRequests.length > 0 && (
            <span className="bg-red-600 text-white text-xs font-black px-2 py-0.5 rounded-full animate-bounce shadow">
              {pendingRequests.length}
            </span>
          )}
        </button>

        <button
          onClick={() => setActiveTab('notifications')}
          className={`py-3.5 px-5 font-bold text-sm border-b-2 transition flex items-center gap-2 relative ${
            activeTab === 'notifications' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <Bell className="w-4 h-4" />
          <span>إشعارات FCM الفورية</span>
          {notifications.length > 0 && (
            <span className="bg-emerald-600 text-white text-xs font-bold px-2 py-0.5 rounded-full">
              {notifications.length}
            </span>
          )}
        </button>

        <button
          onClick={() => setActiveTab('documents')}
          className={`py-3.5 px-5 font-bold text-sm border-b-2 transition flex items-center gap-2 ${
            activeTab === 'documents' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <FileText className="w-4 h-4" />
          <span>إجمالي المستندات والسيريال ({documents.length})</span>
        </button>

        <button
          onClick={() => setActiveTab('devices')}
          className={`py-3.5 px-5 font-bold text-sm border-b-2 transition flex items-center gap-2 ${
            activeTab === 'devices' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <Smartphone className="w-4 h-4" />
          <span>الأجهزة النشطة ({devices.length})</span>
        </button>

        <button
          onClick={() => setActiveTab('users')}
          className={`py-3.5 px-5 font-bold text-sm border-b-2 transition flex items-center gap-2 ${
            activeTab === 'users' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <Users className="w-4 h-4" />
          <span>حسابات الإداريين ({users.length})</span>
        </button>

        <button
          onClick={() => setActiveTab('audit')}
          className={`py-3.5 px-5 font-bold text-sm border-b-2 transition flex items-center gap-2 ${
            activeTab === 'audit' 
              ? 'border-emerald-600 text-emerald-800 bg-emerald-50/70' 
              : 'border-transparent text-slate-600 hover:text-slate-900'
          }`}
        >
          <History className="w-4 h-4" />
          <span>سجل التدقيق</span>
        </button>
      </nav>

      {/* Main Content */}
      <main className="flex-1 overflow-y-auto p-6 bg-slate-100">
        <div className="max-w-7xl mx-auto space-y-6">

          {/* FCM NOTIFICATIONS TAB */}
          {activeTab === 'notifications' && (
            <div className="space-y-4">
              <div className="flex items-center justify-between">
                <div>
                  <h2 className="text-xl font-black text-slate-900">سجل إشعارات Firebase Cloud Messaging (FCM) الفورية</h2>
                  <p className="text-sm text-slate-500">تنبيهات فورية للمدير العام عند قيام الإداريين بإنشاء أوامر صرف، دفاتر صرف، أو طلبات اعتماد أجهزة.</p>
                </div>
                <button
                  onClick={refreshData}
                  className="bg-white border border-slate-300 hover:bg-slate-50 text-slate-700 px-4 py-2.5 rounded-2xl text-sm font-bold flex items-center gap-2 shadow-sm cursor-pointer"
                >
                  <RefreshCw className="w-4 h-4" />
                  <span>تحديث الإشعارات</span>
                </button>
              </div>

              {notifications.length === 0 ? (
                <div className="bg-white rounded-3xl p-12 text-center border border-slate-200 shadow-sm space-y-3">
                  <Bell className="w-16 h-16 text-slate-400 mx-auto" />
                  <h3 className="text-lg font-bold text-slate-800">لا توجد إشعارات FCM جديدة</h3>
                  <p className="text-slate-500 text-sm">ستظهر هنا الإشعارات فوراً عند إنشاء الإداريين لأي مستند مالي جديد.</p>
                </div>
              ) : (
                <div className="space-y-3">
                  {notifications.map(notif => (
                    <div key={notif.id} className="bg-white rounded-2xl p-5 border border-emerald-200 shadow-sm flex items-start gap-4">
                      <div className="w-12 h-12 bg-emerald-100 text-emerald-800 rounded-2xl flex items-center justify-center font-black flex-shrink-0">
                        <Bell className="w-6 h-6" />
                      </div>
                      <div className="flex-1 space-y-1">
                        <div className="flex items-center justify-between">
                          <h4 className="font-black text-slate-900">{notif.title}</h4>
                          <span className="text-xs bg-emerald-50 text-emerald-800 font-mono px-3 py-1 rounded-full border border-emerald-200">
                            {notif.dayName} - {notif.dateString}
                          </span>
                        </div>
                        <p className="text-sm text-slate-600">{notif.body}</p>
                        <div className="text-xs text-slate-400 font-mono pt-1">
                          المُرسل: @{notif.sender} | النوع: {notif.docType}
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          )}

          {/* DOCUMENTS & SERIAL NUMBERS TAB */}
          {activeTab === 'documents' && (
            <div className="space-y-4">
              <div className="flex items-center justify-between">
                <div>
                  <h2 className="text-xl font-black text-slate-900">إجمالي المستندات المنشأة والسيريال والأيام والتواريخ</h2>
                  <p className="text-sm text-slate-500">حصر كامل ومجلد لكل المستندات وأوامر الصرف ودفاتر الصرف الصادرة من الإداريين.</p>
                </div>
                <button
                  onClick={refreshData}
                  className="bg-white border border-slate-300 hover:bg-slate-50 text-slate-700 px-4 py-2.5 rounded-2xl text-sm font-bold flex items-center gap-2 shadow-sm cursor-pointer"
                >
                  <RefreshCw className="w-4 h-4" />
                  <span>تحديث البيانات</span>
                </button>
              </div>

              {documents.length === 0 ? (
                <div className="bg-white rounded-3xl p-12 text-center border border-slate-200 shadow-sm space-y-3">
                  <FileText className="w-16 h-16 text-slate-400 mx-auto" />
                  <h3 className="text-lg font-bold text-slate-800">لا توجد مستندات مسجلة في قاعدة البيانات حالياً</h3>
                  <p className="text-slate-500 text-sm">سيتم عرض جميع المستندات وأوامر الصرف هنا فور إنشائها من قِبل الإداريين والمحاسبين.</p>
                </div>
              ) : (
                <div className="bg-white rounded-3xl shadow-sm border border-slate-200 overflow-hidden">
                  <div className="overflow-x-auto">
                    <table className="w-full text-right border-collapse">
                      <thead>
                        <tr className="bg-slate-50 border-b border-slate-200 text-slate-700 text-xs font-black">
                          <th className="p-4">نوع المستند</th>
                          <th className="p-4">الرقم التسلسلي (Serial)</th>
                          <th className="p-4">عنوان المستند / المستفيد</th>
                          <th className="p-4">المبلغ (ريال)</th>
                          <th className="p-4">اليوم والتاريخ</th>
                          <th className="p-4">بواسطة الإداري</th>
                        </tr>
                      </thead>
                      <tbody className="divide-y divide-slate-100 text-sm">
                        {documents.map((docItem: any) => (
                          <tr key={docItem.id} className="hover:bg-slate-50/50">
                            <td className="p-4">
                              <span className="bg-emerald-100 text-emerald-900 text-xs font-black px-3 py-1 rounded-full">
                                {docItem.type}
                              </span>
                            </td>
                            <td className="p-4 font-mono font-bold text-slate-900">{docItem.serialNumber}</td>
                            <td className="p-4">
                              <div className="font-bold text-slate-800">{docItem.title}</div>
                              <div className="text-xs text-slate-500">المستفيد: {docItem.beneficiary}</div>
                            </td>
                            <td className="p-4 font-black text-emerald-700">
                              {Number(docItem.amount || 0).toLocaleString()} ر.ي
                            </td>
                            <td className="p-4 text-xs font-mono text-slate-600">
                              <div>{docItem.dayName}</div>
                              <div className="text-slate-400">{docItem.dateString}</div>
                            </td>
                            <td className="p-4 text-xs font-bold text-slate-700">@{docItem.createdBy}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              )}
            </div>
          )}

          {/* PENDING REQUESTS */}
          {activeTab === 'requests' && (
            <div className="space-y-4">
              <div className="flex items-center justify-between">
                <div>
                  <h2 className="text-xl font-black text-slate-900">طلبات اعتماد الأجهزة المعلقة (FCM Sync)</h2>
                  <p className="text-sm text-slate-500">مراجعة واعتماد دخول الإداريين والمحاسبين من أجهزة أندرويد جديدة.</p>
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
                    جميع الأجهزة الحالية معتمدة. ستصلك إشعارات FCM فورية هنا عند محاولة أي موظف تسجيل الدخول من جهاز جديد.
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
                            طلب جهاز جديد عبر FCM
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
                          onClick={() => handleApprove(req.deviceId)}
                          className="flex-1 bg-emerald-700 hover:bg-emerald-800 text-white font-bold py-3 px-4 rounded-2xl transition flex items-center justify-center gap-2 shadow cursor-pointer"
                        >
                          <CheckCircle2 className="w-5 h-5" />
                          <span>موافقة واعتماد الجهاز</span>
                        </button>
                        <button
                          onClick={() => setRejectModalReqId(req.deviceId)}
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
                <h2 className="text-xl font-black text-slate-900">الأجهزة المسجلة والمصرحة</h2>
              </div>
              <div className="bg-white rounded-3xl shadow-sm border border-slate-200 overflow-hidden">
                <table className="w-full text-right border-collapse">
                  <thead>
                    <tr className="bg-slate-50 border-b border-slate-200 text-slate-700 text-xs font-black">
                      <th className="p-4">الجهاز والتعريف</th>
                      <th className="p-4">المستخدم المرتبط</th>
                      <th className="p-4">الحالة</th>
                      <th className="p-4">الإجراء</th>
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
                            <div className="font-bold text-slate-800">{usr?.fullName || 'مستخدم'}</div>
                            <div className="text-xs text-slate-500 font-mono">@{usr?.username}</div>
                          </td>
                          <td className="p-4">
                            <span className="bg-emerald-100 text-emerald-800 text-xs font-bold px-3 py-1 rounded-full">معتمد</span>
                          </td>
                          <td className="p-4">
                            <button
                              onClick={() => handleRevoke(d.id)}
                              className="bg-red-50 hover:bg-red-100 text-red-700 px-3 py-2 rounded-xl text-xs font-bold transition flex items-center gap-1 border border-red-200 cursor-pointer"
                            >
                              <Trash2 className="w-3.5 h-3.5" />
                              <span>إلغاء الصلاحية</span>
                            </button>
                          </td>
                        </tr>
                      );
                    })}
                  </tbody>
                </table>
              </div>
            </div>
          )}

          {/* USERS TAB */}
          {activeTab === 'users' && (
            <div className="space-y-4">
              <div>
                <h2 className="text-xl font-black text-slate-900">حسابات الإداريين والمحاسبين</h2>
              </div>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                {users.map(u => (
                  <div key={u.id} className="bg-white rounded-3xl p-6 border border-slate-200 shadow-sm space-y-3">
                    <div className="flex items-center justify-between">
                      <span className="text-xs bg-emerald-100 text-emerald-800 font-bold px-3 py-1 rounded-full font-mono">
                        {u.role}
                      </span>
                      <span className="text-xs text-slate-400 font-mono">@{u.username}</span>
                    </div>
                    <div>
                      <h3 className="text-lg font-black text-slate-900">{u.fullName}</h3>
                      <p className="text-sm text-slate-500 font-mono">{u.email || 'بدون بريد'}</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          )}

          {/* AUDIT TAB */}
          {activeTab === 'audit' && (
            <div className="space-y-4">
              <div>
                <h2 className="text-xl font-black text-slate-900">سجل التدقيق الأمني</h2>
              </div>
              <div className="bg-white rounded-3xl shadow-sm border border-slate-200 overflow-hidden">
                <table className="w-full text-right border-collapse">
                  <thead>
                    <tr className="bg-slate-50 border-b border-slate-200 text-slate-700 text-xs font-black">
                      <th className="p-4">التوقيت</th>
                      <th className="p-4">الحدث</th>
                      <th className="p-4">المستخدم</th>
                      <th className="p-4">التفاصيل</th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-100 text-sm">
                    {auditLogs.map(log => (
                      <tr key={log.id} className="hover:bg-slate-50/50">
                        <td className="p-4 text-xs font-mono text-slate-500">
                          {new Date(log.timestamp).toLocaleString('ar-SA')}
                        </td>
                        <td className="p-4 font-mono text-xs font-bold">{log.action}</td>
                        <td className="p-4 font-bold text-slate-800">@{log.username}</td>
                        <td className="p-4 text-slate-600">{log.details}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
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
            <textarea
              value={rejectionReason}
              onChange={e => setRejectionReason(e.target.value)}
              placeholder="اكتب سبب الرفض..."
              className="w-full p-3.5 border border-slate-300 rounded-2xl text-sm focus:ring-2 focus:ring-red-600 outline-none h-32"
            ></textarea>
            <div className="flex gap-3 pt-2">
              <button
                onClick={async () => {
                  if (!rejectionReason.trim()) return;
                  await directorRejectDevice(rejectModalReqId, currentUser.username, rejectionReason);
                  setRejectModalReqId(null);
                  setRejectionReason('');
                  await refreshData();
                }}
                className="flex-1 bg-red-600 hover:bg-red-700 text-white font-bold py-3 rounded-2xl transition shadow cursor-pointer"
              >
                تأكيد الرفض
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
