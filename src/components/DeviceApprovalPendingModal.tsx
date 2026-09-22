import React from 'react';
import { ShieldAlert, Clock, Smartphone, RefreshCw, LogOut } from 'lucide-react';
import { GovernmentUser } from '../types/governmentAuth';
import { getDeviceInfoString, getOrCreateInstallationId } from '../utils/governmentAuthService';

interface Props {
  user: GovernmentUser;
  onRefreshStatus: () => void;
  onLogout: () => void;
}

export const DeviceApprovalPendingModal: React.FC<Props> = ({ user, onRefreshStatus, onLogout }) => {
  const deviceName = getDeviceInfoString();
  const installationId = getOrCreateInstallationId();

  return (
    <div className="fixed inset-0 z-50 bg-slate-950/80 backdrop-blur-md flex items-center justify-center p-4 animate-fadeIn rtl text-right">
      <div className="bg-white rounded-3xl shadow-2xl max-w-lg w-full overflow-hidden border border-amber-300">
        <div className="bg-gradient-to-r from-amber-700 via-amber-800 to-slate-900 text-white p-6 text-center relative">
          <div className="absolute top-4 left-4 bg-amber-500/30 px-3 py-1 rounded-full text-xs font-mono border border-amber-400/30">
            حالة الأمان: قيد الانتظار الأمني
          </div>
          <div className="w-16 h-16 bg-white/10 rounded-2xl mx-auto flex items-center justify-center mb-3 shadow-inner border border-white/20">
            <ShieldAlert className="w-10 h-10 text-amber-200 animate-pulse" />
          </div>
          <h2 className="text-xl font-black">جهاز قيد المراجعة والموافقة الرسمية</h2>
          <p className="text-amber-100 text-sm mt-1">صندوق النظافة والتحسين - الإدارة العامة (إصدار APK)</p>
        </div>

        <div className="p-6 space-y-6">
          <div className="bg-amber-50 border border-amber-200 rounded-2xl p-4 text-amber-900 space-y-2">
            <div className="flex items-center gap-2 font-bold text-amber-800">
              <Clock className="w-5 h-5 text-amber-600" />
              <span>تم إرسال إشعار طلب الدخول إلى المدير العام</span>
            </div>
            <p className="text-sm leading-relaxed text-amber-900/80">
              لا يمكن الدخول إلى النظام أو استعراض المستندات وقاعدة البيانات إلا بعد اعتماد هذا الجهاز رسمياً من لوحة تحكم الإدارة العليا.
            </p>
          </div>

          <div className="bg-slate-50 rounded-2xl p-4 border border-slate-200 space-y-3 text-sm">
            <div className="font-bold text-slate-700 pb-2 border-b border-slate-200 flex items-center justify-between">
              <span>تفاصيل محاولة التسجيل الرسمية</span>
              <span className="text-xs bg-emerald-100 text-emerald-800 px-2 py-0.5 rounded font-mono">APK Secure Auth</span>
            </div>
            
            <div className="grid grid-cols-2 gap-2 text-slate-600 text-xs">
              <div><strong className="text-slate-800">المستخدم:</strong> {user.fullName}</div>
              <div><strong className="text-slate-800">الدور:</strong> {user.role}</div>
              <div className="col-span-2 flex items-center gap-1.5"><Smartphone className="w-4 h-4 text-slate-500" /><strong className="text-slate-800">الجهاز:</strong> {deviceName}</div>
              <div className="col-span-2 font-mono text-[11px] text-slate-500 bg-white p-2 rounded-xl border border-slate-200 truncate">
                معرف التثبيت: {installationId}
              </div>
            </div>
          </div>

          <div className="space-y-3 pt-2">
            <button
              onClick={onRefreshStatus}
              className="w-full bg-emerald-700 hover:bg-emerald-800 text-white font-bold py-3.5 px-4 rounded-2xl transition flex items-center justify-center gap-2 shadow-lg cursor-pointer"
            >
              <RefreshCw className="w-5 h-5" />
              <span>التحقق من حالة الموافقة الآن</span>
            </button>

            <button
              onClick={onLogout}
              className="w-full bg-slate-100 hover:bg-slate-200 text-slate-700 font-medium py-3 px-4 rounded-2xl transition flex items-center justify-center gap-2"
            >
              <LogOut className="w-4 h-4" />
              <span>تسجيل الخروج والرجوع لشاشة الدخول</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
