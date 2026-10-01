import React, { useState } from 'react';
import { motion } from 'motion/react';
import { ArrowLeft, User, Lock, ShieldAlert, Sparkles, ShieldCheck, Smartphone, Mail } from 'lucide-react';
import { YemenEmblem } from './YemenEmblem';
import { attemptGovernmentLogin, logoutGovernmentUser } from '../utils/governmentAuthService';
import { DeviceApprovalPendingModal } from './DeviceApprovalPendingModal';
import { DirectorApprovalDashboard } from './DirectorApprovalDashboard';
import { GovernmentUser } from '../types/governmentAuth';

interface SplashLoginScreenProps {
  onLoginSuccess: () => void;
}

export const SplashLoginScreen: React.FC<SplashLoginScreenProps> = ({ onLoginSuccess }) => {
  const [loginInput, setLoginInput] = useState('');
  const [passwordInput, setPasswordInput] = useState('');
  const [errorMsg, setErrorMsg] = useState('');
  const [loading, setLoading] = useState(false);
  const [pendingUser, setPendingUser] = useState<GovernmentUser | null>(null);
  const [showDirectorModal, setShowDirectorModal] = useState(false);
  const [authenticatedUser, setAuthenticatedUser] = useState<GovernmentUser | null>(null);

  const handleLoginSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!loginInput.trim()) {
      setErrorMsg('الرجاء إدخال البريد الإلكتروني أو اسم المستخدم.');
      return;
    }

    setLoading(true);
    setErrorMsg('');
    const result = await attemptGovernmentLogin(loginInput.trim(), passwordInput.trim());
    setLoading(false);

    if (result.success && result.user) {
      setAuthenticatedUser(result.user);
      if (result.isDirector || result.user.role === 'SYSTEM_ADMIN') {
        setShowDirectorModal(true);
      } else {
        onLoginSuccess();
      }
    } else if (result.requiresApproval && result.user) {
      setPendingUser(result.user);
    } else {
      setErrorMsg(result.message || 'فشل تسجيل الدخول عبر النظام الحكومي.');
    }
  };

  return (
    <div className="fixed inset-0 z-50 bg-slate-950 flex flex-col items-center justify-center p-4 overflow-y-auto select-none rtl text-right">
      <div className="absolute inset-0 bg-gradient-to-br from-slate-900 via-slate-950 to-emerald-950 opacity-90 pointer-events-none"></div>

      <motion.div
        initial={{ opacity: 0, scale: 0.95, y: 20 }}
        animate={{ opacity: 1, scale: 1, y: 0 }}
        transition={{ duration: 0.6, ease: [0.16, 1, 0.3, 1] }}
        className="relative w-full max-w-lg bg-white rounded-3xl shadow-2xl overflow-hidden border border-slate-200 flex flex-col my-auto"
      >
        <div className="absolute top-0 left-0 right-0 h-2 bg-gradient-to-r from-amber-600 via-yellow-400 to-amber-600"></div>

        <div className="pt-8 px-6 text-center space-y-3">
          <div className="w-20 h-20 mx-auto flex items-center justify-center bg-emerald-50 rounded-2xl border border-emerald-100 shadow-inner">
            <YemenEmblem className="w-14 h-14" />
          </div>
          <div>
            <h1 className="text-xl font-black text-slate-900 tracking-tight font-serif">
              النظام المالي الحكومي الموحد (Firebase FCM + Auth)
            </h1>
            <p className="text-xs font-bold text-amber-700 mt-1">
              صندوق النظافة والتحسين م/إب - بوابة إدارة الصلاحيات والأجهزة
            </p>
          </div>
        </div>

        <div className="p-6 space-y-5">
          {errorMsg && (
            <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded-2xl text-sm flex items-center gap-2 font-medium">
              <ShieldAlert className="w-5 h-5 flex-shrink-0" />
              <span>{errorMsg}</span>
            </div>
          )}

          <form onSubmit={handleLoginSubmit} className="space-y-4">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">البريد الإلكتروني أو اسم المستخدم:</label>
              <div className="relative">
                <div className="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none text-slate-400">
                  <Mail className="w-5 h-5" />
                </div>
                <input
                  type="text"
                  value={loginInput}
                  onChange={e => setLoginInput(e.target.value)}
              placeholder="name@example.com"
                  className="w-full pr-10 pl-4 py-3.5 bg-slate-50 border border-slate-300 rounded-2xl text-sm focus:ring-2 focus:ring-emerald-600 outline-none font-medium"
                />
              </div>
            </div>

            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">كلمة المرور:</label>
              <div className="relative">
                <div className="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none text-slate-400">
                  <Lock className="w-5 h-5" />
                </div>
                <input
                  type="password"
                  value={passwordInput}
                  onChange={e => setPasswordInput(e.target.value)}
                  placeholder="••••••••••••"
                  className="w-full pr-10 pl-4 py-3.5 bg-slate-50 border border-slate-300 rounded-2xl text-sm focus:ring-2 focus:ring-emerald-600 outline-none font-medium"
                />
              </div>
            </div>

            <button
              type="submit"
              disabled={loading}
              className="w-full py-3.5 bg-gradient-to-r from-emerald-700 to-teal-700 hover:from-emerald-800 hover:to-teal-800 text-white rounded-2xl text-sm font-bold shadow-lg transition flex items-center justify-center gap-2 cursor-pointer disabled:opacity-50"
            >
              <span>{loading ? 'جاري التحقق عبر السحابة...' : 'تسجيل الدخول والتحقق الأمني'}</span>
              <ArrowLeft className="w-4 h-4" />
            </button>
          </form>

        </div>

        <div className="bg-slate-900 text-slate-300 py-3.5 px-6 text-center border-t border-slate-800 space-y-1">
          <p className="text-[11px] font-bold text-amber-400">
            تصميم وتطوير المطور محمد الحزمي © 2026 (Firebase FCM + Auth)
          </p>
          <p className="text-[10px] text-slate-400">
            جميع الحقوق محفوظة لصندوق النظافة والتحسين م/إب
          </p>
        </div>
      </motion.div>

      {/* Pending Approval Modal */}
      {pendingUser && (
        <DeviceApprovalPendingModal
          user={pendingUser}
          onRefreshStatus={async () => {
            const res = await attemptGovernmentLogin(loginInput, passwordInput);
            if (res.success) {
              setPendingUser(null);
              onLoginSuccess();
            } else {
              alert(res.message || 'الجهاز لا يزال قيد الانتظار لموافقة المدير العام عبر إشعارات FCM.');
            }
          }}
          onLogout={() => setPendingUser(null)}
        />
      )}

      {/* Director Approval Dashboard Modal */}
      {showDirectorModal && (
        <DirectorApprovalDashboard
          currentUser={authenticatedUser!}
          onLogout={() => {
            setShowDirectorModal(false);
            logoutGovernmentUser();
          }}
          onClose={() => {
            setShowDirectorModal(false);
            onLoginSuccess();
          }}
        />
      )}
    </div>
  );
};
