import React, { useState } from 'react';
import { motion } from 'motion/react';
import { ArrowLeft, User, Lock, ShieldAlert, Sparkles, ShieldCheck, Smartphone, Mail } from 'lucide-react';
import { YemenEmblem } from './YemenEmblem';
import { attemptGovernmentLogin, getCurrentLoggedInUser, logoutGovernmentUser, DIRECTOR_CREDENTIALS } from '../utils/governmentAuthService';
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
      if (result.isDirector || result.user.role === 'SYSTEM_ADMIN' || loginInput.trim().toLowerCase() === DIRECTOR_CREDENTIALS.email) {
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

  const handleDirectorQuickLogin = async () => {
    setLoginInput(DIRECTOR_CREDENTIALS.email);
    setPasswordInput(DIRECTOR_CREDENTIALS.password);
    setLoading(true);
    setErrorMsg('');
    const result = await attemptGovernmentLogin(DIRECTOR_CREDENTIALS.email, DIRECTOR_CREDENTIALS.password);
    setLoading(false);

    if (result.success && result.user) {
      setShowDirectorModal(true);
    } else {
      setErrorMsg(result.message || 'فشل دخول المدير العام.');
    }
  };

  const handleQuickLogin = async (username: string) => {
    setLoginInput(username);
    setPasswordInput('');
    setLoading(true);
    setErrorMsg('');
    const result = await attemptGovernmentLogin(username);
    setLoading(false);

    if (result.success && result.user) {
      if (result.user.role === 'SYSTEM_ADMIN') {
        setShowDirectorModal(true);
      } else {
        onLoginSuccess();
      }
    } else if (result.requiresApproval && result.user) {
      setPendingUser(result.user);
    } else {
      setErrorMsg(result.message || 'فشل تسجيل الدخول عبر الخادم الحكومي.');
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
                  placeholder="alhzmim57@gmail.com أو director"
                  className="w-full pr-10 pl-4 py-3.5 bg-slate-50 border border-slate-300 rounded-2xl text-sm focus:ring-2 focus:ring-emerald-600 outline-none font-medium"
                />
              </div>
            </div>

            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">كلمة المرور (خاصة بمدير النظام):</label>
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

          {/* Quick Director Login Shortcut */}
          <div className="pt-2">
            <button
              onClick={handleDirectorQuickLogin}
              disabled={loading}
              className="w-full bg-emerald-900 hover:bg-emerald-950 text-white p-3.5 rounded-2xl text-xs font-black shadow-md transition flex items-center justify-center gap-2 cursor-pointer border border-emerald-700"
            >
              <ShieldCheck className="w-4 h-4 text-emerald-300" />
              <span>دخول سريع (المدير العام: alhzmim57@gmail.com)</span>
            </button>
          </div>

          {/* Quick Role Shortcuts */}
          <div className="space-y-2.5 pt-3 border-t border-slate-200">
            <span className="text-xs font-bold text-slate-500 block">اختصارات دخول الحسابات الإدارية والمحاسبية:</span>
            <div className="grid grid-cols-2 gap-2.5">
              <button
                onClick={() => handleQuickLogin('finance')}
                disabled={loading}
                className="bg-blue-50 hover:bg-blue-100 text-blue-900 border border-blue-200 p-3 rounded-2xl text-xs font-bold text-right transition flex items-center justify-between cursor-pointer"
              >
                <span>المدير المالي</span>
                <span className="font-mono text-[10px] bg-blue-200 px-2 py-0.5 rounded-full text-blue-900">finance</span>
              </button>

              <button
                onClick={() => handleQuickLogin('accountant')}
                disabled={loading}
                className="bg-amber-50 hover:bg-amber-100 text-amber-900 border border-amber-200 p-3 rounded-2xl text-xs font-bold text-right transition flex items-center justify-between cursor-pointer"
              >
                <span>المحاسب الرئيسي</span>
                <span className="font-mono text-[10px] bg-amber-200 px-2 py-0.5 rounded-full text-amber-900">accountant</span>
              </button>
            </div>
          </div>
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
            const res = await attemptGovernmentLogin(pendingUser.username);
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
          currentUser={{
            id: 'usr_director',
            username: 'director',
            email: DIRECTOR_CREDENTIALS.email,
            fullName: DIRECTOR_CREDENTIALS.fullName,
            role: 'SYSTEM_ADMIN',
            active: true,
            approvalRequired: false,
            createdAt: Date.now()
          }}
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
