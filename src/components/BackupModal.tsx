import React, { useRef, useState } from 'react';
import {
  X,
  Database,
  Download,
  Upload,
  RotateCcw,
  CheckCircle2,
  AlertCircle,
  FileJson,
} from 'lucide-react';
import { Document, OrganizationProfile } from '../types';
import { sampleDocuments, initialOrganizationProfile } from '../utils/initialData';

interface BackupModalProps {
  isOpen: boolean;
  onClose: () => void;
  documents: Document[];
  organization: OrganizationProfile;
  onRestore: (docs: Document[], org: OrganizationProfile) => void;
}

export const BackupModal: React.FC<BackupModalProps> = ({
  isOpen,
  onClose,
  documents,
  organization,
  onRestore,
}) => {
  const fileInputRef = useRef<HTMLInputElement>(null);
  const [statusMsg, setStatusMsg] = useState<{ type: 'success' | 'error'; text: string } | null>(
    null
  );

  if (!isOpen) return null;

  // Export database as JSON file
  const handleExport = () => {
    const backupData = {
      version: '1.0.0',
      system: 'Masrof Manager - صندوق النظافة والتحسين',
      exportedAt: new Date().toISOString(),
      organization,
      documents,
    };

    const blob = new Blob([JSON.stringify(backupData, null, 2)], {
      type: 'application/json',
    });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `masrof-backup-${new Date().toISOString().slice(0, 10)}.json`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);

    setStatusMsg({
      type: 'success',
      text: 'تم تصدير وحفظ نسخة النسخ الاحتياطي بنجاح!',
    });
  };

  // Import and restore from JSON file
  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = (event) => {
      try {
        const content = event.target?.result as string;
        const parsed = JSON.parse(content);

        if (Array.isArray(parsed.documents)) {
          onRestore(parsed.documents, parsed.organization || organization);
          setStatusMsg({
            type: 'success',
            text: `تمت استعادة البيانات بنجاح (${parsed.documents.length} مستند)!`,
          });
        } else {
          setStatusMsg({
            type: 'error',
            text: 'صيغة ملف النسخ الاحتياطي غير صالحة.',
          });
        }
      } catch (err: any) {
        setStatusMsg({
          type: 'error',
          text: 'تعذر قراءة الملف: ' + err.message,
        });
      }
    };
    reader.readAsText(file);
    if (fileInputRef.current) fileInputRef.current.value = '';
  };

  const handleResetSampleData = () => {
    if (window.confirm('هل تريد إعادة تعيين المستندات التجريبية الافتراضية؟')) {
      onRestore(sampleDocuments, initialOrganizationProfile);
      setStatusMsg({
        type: 'success',
        text: 'تمت إعادة تعيين البيانات التجريبية بنجاح!',
      });
    }
  };

  return (
    <div className="fixed inset-0 z-50 overflow-y-auto bg-slate-900/60 backdrop-blur-xs flex items-center justify-center p-4">
      <div className="bg-white rounded-3xl border border-slate-200 shadow-2xl w-full max-w-lg overflow-hidden">
        {/* Modal Header */}
        <div className="bg-slate-900 text-white p-5 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-blue-500/20 border border-blue-500/30 flex items-center justify-center text-blue-400">
              <Database className="w-5 h-5" />
            </div>
            <div>
              <h3 className="font-bold text-base text-slate-100">
                النسخ الاحتياطي وإدارة البيانات
              </h3>
              <p className="text-xs text-slate-400">
                حفظ واستعادة كامل المستندات وإعدادات الصندوق
              </p>
            </div>
          </div>
          <button
            onClick={onClose}
            className="p-1.5 rounded-lg hover:bg-slate-800 text-slate-400 hover:text-white transition"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Modal Body */}
        <div className="p-6 space-y-4">
          <p className="text-xs text-slate-600 leading-relaxed font-medium">
            يمكنك تصدير قاعدة بيانات النظام بالكامل إلى ملف احتياطي آمن، أو استعادة ملف احتياطي سابق في أي وقت.
          </p>

          <div className="space-y-3 pt-2">
            {/* Export Card */}
            <div className="p-4 rounded-2xl bg-emerald-50/60 border border-emerald-200/80 flex items-center justify-between gap-3">
              <div className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-xl bg-emerald-600 text-white flex items-center justify-center">
                  <Download className="w-5 h-5" />
                </div>
                <div>
                  <h4 className="text-xs font-bold text-emerald-950">
                    تصدير نسخة احتياطية (JSON)
                  </h4>
                  <p className="text-[11px] text-slate-600">
                    تحميل ملف يحتوي على جميع المستندات ({documents.length} مستند)
                  </p>
                </div>
              </div>
              <button
                onClick={handleExport}
                className="px-4 py-2 bg-emerald-700 hover:bg-emerald-600 text-white text-xs font-bold rounded-xl transition shadow-sm active:scale-95"
              >
                تصدير الآن
              </button>
            </div>

            {/* Import Card */}
            <div className="p-4 rounded-2xl bg-blue-50/60 border border-blue-200/80 flex items-center justify-between gap-3">
              <div className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-xl bg-blue-600 text-white flex items-center justify-center">
                  <Upload className="w-5 h-5" />
                </div>
                <div>
                  <h4 className="text-xs font-bold text-blue-950">
                    استيراد واستعادة نسخة سابقة
                  </h4>
                  <p className="text-[11px] text-slate-600">
                    استرجاع المستندات والإعدادات من ملف نسخة احتياطية
                  </p>
                </div>
              </div>

              <input
                type="file"
                ref={fileInputRef}
                onChange={handleFileChange}
                accept=".json"
                className="hidden"
              />
              <button
                onClick={() => fileInputRef.current?.click()}
                className="px-4 py-2 bg-blue-700 hover:bg-blue-600 text-white text-xs font-bold rounded-xl transition shadow-sm active:scale-95"
              >
                اختيار ملف
              </button>
            </div>

            {/* Reset Sample Data */}
            <div className="p-4 rounded-2xl bg-slate-50 border border-slate-200 flex items-center justify-between gap-3">
              <div className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-xl bg-slate-200 text-slate-700 flex items-center justify-center">
                  <RotateCcw className="w-5 h-5" />
                </div>
                <div>
                  <h4 className="text-xs font-bold text-slate-900">
                    إعادة تعيين البيانات الافتراضية
                  </h4>
                  <p className="text-[11px] text-slate-500">
                    استعادة نماذج المستندات الرسمية التجريبية
                  </p>
                </div>
              </div>
              <button
                onClick={handleResetSampleData}
                className="px-3.5 py-2 bg-slate-200 hover:bg-slate-300 text-slate-800 text-xs font-bold rounded-xl transition"
              >
                إعادة ضبط
              </button>
            </div>
          </div>

          {/* Status Message */}
          {statusMsg && (
            <div
              className={`p-3 rounded-xl text-xs flex items-center gap-2 border ${
                statusMsg.type === 'success'
                  ? 'bg-emerald-50 text-emerald-800 border-emerald-200 font-bold'
                  : 'bg-red-50 text-red-800 border-red-200 font-medium'
              }`}
            >
              {statusMsg.type === 'success' ? (
                <CheckCircle2 className="w-4 h-4 text-emerald-600 flex-shrink-0" />
              ) : (
                <AlertCircle className="w-4 h-4 text-red-600 flex-shrink-0" />
              )}
              <span>{statusMsg.text}</span>
            </div>
          )}
        </div>

        {/* Modal Footer */}
        <div className="bg-slate-50 px-6 py-4 border-t border-slate-200 flex items-center justify-end">
          <button
            onClick={onClose}
            className="px-4 py-2 bg-slate-200 hover:bg-slate-300 text-slate-800 text-xs font-bold rounded-xl transition"
          >
            إغلاق
          </button>
        </div>
      </div>
    </div>
  );
};
