import React from 'react';
import {
  FileText,
  PlusCircle,
  Settings,
  GitBranch,
  Database,
  Printer,
  ShieldCheck,
  Building2,
} from 'lucide-react';
import { DocumentType } from '../types';

interface HeaderProps {
  onNewDocument: (type: DocumentType) => void;
  onOpenGitSync: () => void;
  onOpenSettings: () => void;
  onOpenBackup: () => void;
  selectedCount: number;
  onPrintSelected: () => void;
}

export const Header: React.FC<HeaderProps> = ({
  onNewDocument,
  onOpenGitSync,
  onOpenSettings,
  onOpenBackup,
  selectedCount,
  onPrintSelected,
}) => {
  return (
    <header className="bg-slate-900 text-white shadow-xl border-b border-slate-800 sticky top-0 z-40">
      {/* Top Bar: Official Branding */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex flex-col md:flex-row md:items-center md:justify-between py-3 gap-3 border-b border-slate-800/80">
          <div className="flex items-center gap-3">
            <div className="w-12 h-12 rounded-xl bg-gradient-to-br from-emerald-500 to-teal-700 flex items-center justify-center shadow-lg shadow-emerald-500/20 text-white font-black text-xl border border-emerald-400/30">
              <Building2 className="w-6 h-6" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <h1 className="text-lg font-bold text-slate-100 tracking-tight">
                  نظام مالية صندوق النظافة والتحسين
                </h1>
                <span className="bg-emerald-500/20 text-emerald-300 text-xs px-2.5 py-0.5 rounded-full border border-emerald-500/30 font-semibold">
                  النسخة الرسمية 2026
                </span>
              </div>
              <p className="text-xs text-slate-400 font-medium">
                الجمهورية اليمنية • صندوق النظافة والتحسين م/إب • فرع مديرية الحزم
              </p>
            </div>
          </div>

          <div className="flex items-center gap-2 flex-wrap">
            {/* Git Sync Indicator & Button */}
            <button
              id="git-sync-btn"
              onClick={onOpenGitSync}
              className="flex items-center gap-1.5 px-3 py-1.5 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold border border-slate-700 hover:border-slate-600 transition shadow-sm"
              title="مركز مزامنة ورفع مستودع GitHub"
            >
              <GitBranch className="w-3.5 h-3.5 text-emerald-400" />
              <span>مستودع GitHub</span>
              <span className="w-2 h-2 rounded-full bg-emerald-400 animate-pulse"></span>
            </button>

            {/* Backup & Database */}
            <button
              id="backup-btn"
              onClick={onOpenBackup}
              className="flex items-center gap-1.5 px-3 py-1.5 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold border border-slate-700 hover:border-slate-600 transition"
              title="النسخ الاحتياطي وقاعدة البيانات"
            >
              <Database className="w-3.5 h-3.5 text-blue-400" />
              <span>النسخ الاحتياطي</span>
            </button>

            {/* Settings */}
            <button
              id="settings-btn"
              onClick={onOpenSettings}
              className="flex items-center gap-1.5 px-3 py-1.5 rounded-lg bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-semibold border border-slate-700 hover:border-slate-600 transition"
              title="إعدادات المنظمة والتواقيع"
            >
              <Settings className="w-3.5 h-3.5 text-amber-400" />
              <span>إعدادات الصندوق</span>
            </button>
          </div>
        </div>

        {/* Action Controls & Fast Navigation */}
        <div className="py-2.5 flex items-center justify-between flex-wrap gap-2">
          <div className="flex items-center gap-2 flex-wrap">
            <span className="text-xs text-slate-400 font-medium ml-1">إصدار مستند رسمي:</span>

            {/* Create Order */}
            <button
              id="new-order-btn"
              onClick={() => onNewDocument('ORDER')}
              className="flex items-center gap-1.5 px-3.5 py-1.5 rounded-lg bg-emerald-600 hover:bg-emerald-500 text-white text-xs font-bold transition shadow-md shadow-emerald-700/20 active:scale-95"
            >
              <PlusCircle className="w-3.5 h-3.5" />
              <span>+ أمر صرف</span>
            </button>

            {/* Create Request */}
            <button
              id="new-request-btn"
              onClick={() => onNewDocument('DISBURSEMENT_REQUEST')}
              className="flex items-center gap-1.5 px-3.5 py-1.5 rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-xs font-bold transition shadow-md shadow-blue-700/20 active:scale-95"
            >
              <PlusCircle className="w-3.5 h-3.5" />
              <span>+ طلب صرف</span>
            </button>

            {/* Create Receipt */}
            <button
              id="new-receipt-btn"
              onClick={() => onNewDocument('RECEIPT')}
              className="flex items-center gap-1.5 px-3.5 py-1.5 rounded-lg bg-amber-600 hover:bg-amber-500 text-white text-xs font-bold transition shadow-md shadow-amber-700/20 active:scale-95"
            >
              <PlusCircle className="w-3.5 h-3.5" />
              <span>+ سند استلام / قبض</span>
            </button>
          </div>

          {/* Batch Print Button if items selected */}
          {selectedCount > 0 && (
            <div className="flex items-center gap-2">
              <span className="text-xs text-amber-300 font-semibold">
                تم تحديد ({selectedCount}) مستند
              </span>
              <button
                id="batch-print-btn"
                onClick={onPrintSelected}
                className="flex items-center gap-1.5 px-4 py-1.5 rounded-lg bg-amber-500 hover:bg-amber-400 text-slate-950 text-xs font-extrabold transition shadow-lg animate-bounce"
              >
                <Printer className="w-3.5 h-3.5" />
                <span>طباعة وتصدير المحدد ({selectedCount})</span>
              </button>
            </div>
          )}
        </div>
      </div>
    </header>
  );
};
