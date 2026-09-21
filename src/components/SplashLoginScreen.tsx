import React, { useState, useEffect } from 'react';
import { motion } from 'motion/react';
import { ShieldCheck, ArrowLeft, Building2, Sparkles, CheckCircle2 } from 'lucide-react';

interface SplashLoginScreenProps {
  onLoginSuccess: () => void;
}

export const SplashLoginScreen: React.FC<SplashLoginScreenProps> = ({ onLoginSuccess }) => {
  const [progress, setProgress] = useState(0);

  useEffect(() => {
    const timer = setInterval(() => {
      setProgress((prev) => {
        if (prev >= 100) {
          clearInterval(timer);
          setTimeout(() => onLoginSuccess(), 300);
          return 100;
        }
        return prev + 2;
      });
    }, 50);

    return () => clearInterval(timer);
  }, [onLoginSuccess]);

  return (
    <div className="fixed inset-0 z-50 bg-slate-950 flex flex-col items-center justify-center p-4 overflow-y-auto select-none">
      {/* Background ambient glow */}
      <div className="absolute inset-0 bg-gradient-to-br from-slate-900 via-slate-950 to-emerald-950 opacity-80 pointer-events-none"></div>

      <motion.div
        initial={{ opacity: 0, scale: 0.95, y: 20 }}
        animate={{ opacity: 1, scale: 1, y: 0 }}
        transition={{ duration: 0.7, ease: [0.16, 1, 0.3, 1] }}
        className="relative w-full max-w-md bg-white rounded-3xl shadow-2xl overflow-hidden border border-slate-200 flex flex-col"
      >
        {/* Top Gold Ribbon Accent */}
        <div className="absolute top-0 left-0 right-0 h-2 bg-gradient-to-r from-amber-600 via-yellow-400 to-amber-600"></div>
        <div className="absolute top-0 right-0 w-24 h-24 bg-gradient-to-bl from-amber-500 to-transparent opacity-30 rounded-bl-full pointer-events-none"></div>

        {/* Card Header & Title */}
        <div className="pt-8 px-6 text-center space-y-2">
          <motion.div
            initial={{ opacity: 0, y: -15 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.2, duration: 0.6 }}
          >
            <h1 className="text-2xl font-black text-slate-900 tracking-tight font-serif">
              نظام المالية لصندوق النظافة الحزم
            </h1>
          </motion.div>

          <motion.div
            initial={{ opacity: 0, scaleX: 0 }}
            animate={{ opacity: 1, scaleX: 1 }}
            transition={{ delay: 0.4, duration: 0.5 }}
            className="w-24 h-0.5 bg-amber-500 mx-auto my-2"
          ></motion.div>

          <motion.div
            initial={{ opacity: 0, y: 10 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.5, duration: 0.6 }}
            className="space-y-1"
          >
            <p className="text-sm font-bold text-amber-800">
              لفرع صندوق النظافة والتحسين
            </p>
            <p className="text-base font-black text-slate-900">
              مديرية الحزم
            </p>
          </motion.div>
        </div>

        {/* Animated Emblem / Logo Prompt */}
        <div className="py-8 px-6 flex flex-col items-center justify-center relative">
          <motion.div
            animate={{
              scale: [1, 1.03, 1],
              rotate: [0, 0.5, -0.5, 0],
            }}
            transition={{
              duration: 4,
              repeat: Infinity,
              ease: 'easeInOut',
            }}
            className="relative w-40 h-40 flex items-center justify-center bg-amber-50/80 rounded-full border-4 border-amber-400/40 shadow-inner"
          >
            {/* Eagle Emblem Icon Simulation */}
            <div className="absolute inset-0 flex items-center justify-center">
              <Building2 className="w-20 h-20 text-amber-600 opacity-90 drop-shadow-md" />
            </div>
            <div className="absolute bottom-2 bg-slate-900 text-amber-300 px-3 py-0.5 rounded-full text-[10px] font-black tracking-widest border border-amber-500/50 shadow">
              الجمهورية اليمنية
            </div>
          </motion.div>

          {/* Prompt Glow Badge */}
          <motion.div
            initial={{ opacity: 0, y: 10 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: 0.7 }}
            className="mt-4 inline-flex items-center gap-1.5 px-3 py-1 bg-emerald-50 text-emerald-800 rounded-full text-xs font-bold border border-emerald-200"
          >
            <Sparkles className="w-3.5 h-3.5 text-emerald-600 animate-spin" />
            <span>جاري تحميل وتشغيل النظام المالي...</span>
          </motion.div>
        </div>

        {/* Login Countdown & Progress Bar */}
        <div className="px-6 pb-6 space-y-3">
          <div className="flex items-center justify-between text-xs font-bold text-slate-600">
            <span>عداد الدخول الآمن</span>
            <span className="font-mono text-emerald-700">{progress}%</span>
          </div>

          <div className="w-full h-2.5 bg-slate-100 rounded-full overflow-hidden p-0.5 border border-slate-200">
            <motion.div
              className="h-full bg-gradient-to-r from-emerald-600 to-teal-500 rounded-full"
              style={{ width: `${progress}%` }}
            ></motion.div>
          </div>

          <button
            onClick={onLoginSuccess}
            className="w-full mt-2 py-3 bg-slate-900 hover:bg-slate-800 text-white rounded-xl text-xs font-bold shadow-lg transition flex items-center justify-center gap-2 cursor-pointer active:scale-95"
          >
            <span>الدخول الفوري إلى النظام</span>
            <ArrowLeft className="w-4 h-4" />
          </button>
        </div>

        {/* Bottom Decorative Wave & Developer Credit */}
        <div className="bg-slate-900 text-slate-300 py-4 px-6 text-center border-t border-slate-800 space-y-1 relative overflow-hidden">
          <div className="absolute bottom-0 left-0 right-0 h-1 bg-gradient-to-r from-amber-500 via-yellow-300 to-amber-500"></div>
          <p className="text-[11px] font-bold text-amber-400 tracking-wide">
            هذا النظام من برمجة وتطوير المطور محمد الحزمي
          </p>
          <p className="text-[10px] text-slate-400 font-mono">
            جميع الحقوق محفوظة © 2026 صندوق النظافة والتحسين م/إب
          </p>
        </div>
      </motion.div>
    </div>
  );
};
