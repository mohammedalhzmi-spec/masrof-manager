import React, { useState, useEffect } from 'react';
import {
  X,
  GitBranch,
  GitCommit,
  UploadCloud,
  DownloadCloud,
  KeyRound,
  ExternalLink,
  CheckCircle2,
  AlertCircle,
  RefreshCw,
  FolderArchive,
  Terminal,
  ShieldCheck,
} from 'lucide-react';

interface GitSyncModalProps {
  isOpen: boolean;
  onClose: () => void;
}

interface GitStatusData {
  success: boolean;
  branch?: string;
  lastCommit?: string;
  hasUncommittedChanges?: boolean;
  uncommittedDetails?: string;
  remoteUrl?: string;
  error?: string;
}

export const GitSyncModal: React.FC<GitSyncModalProps> = ({ isOpen, onClose }) => {
  const [loading, setLoading] = useState(false);
  const [gitStatus, setGitStatus] = useState<GitStatusData | null>(null);
  const [token, setToken] = useState('');
  const [commitMessage, setCommitMessage] = useState('تحديثات وإضافات نظام مالية صندوق النظافة');
  const [pushing, setPushing] = useState(false);
  const [pushResult, setPushResult] = useState<{ success: boolean; message: string } | null>(null);
  const [pulling, setPulling] = useState(false);

  const fetchStatus = async () => {
    setLoading(true);
    setPushResult(null);
    try {
      const res = await fetch('/api/git/status');
      const data = await res.json();
      setGitStatus(data);
    } catch (err: any) {
      setGitStatus({
        success: false,
        error: 'تعذر الاتصال بمركز Git المحلي: ' + err.message,
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (isOpen) {
      fetchStatus();
    }
  }, [isOpen]);

  const handlePush = async () => {
    if (!token.trim()) {
      alert('يرجى إدخال رمز الوصول الشخصي (GitHub Personal Access Token) للتمكن من الرفع.');
      return;
    }

    setPushing(true);
    setPushResult(null);
    try {
      const res = await fetch('/api/git/push', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          token: token.trim(),
          commitMessage: commitMessage.trim(),
        }),
      });
      const data = await res.json();
      if (data.success) {
        setPushResult({
          success: true,
          message: data.message || 'تم الرفع بنجاح إلى المستودع على GitHub!',
        });
        fetchStatus();
      } else {
        setPushResult({
          success: false,
          message: data.error || 'فشلت عملية الرفع. تحقق من الصلاحيات وصحة التوكن.',
        });
      }
    } catch (err: any) {
      setPushResult({
        success: false,
        message: 'حدث خطأ في الاتصال: ' + err.message,
      });
    } finally {
      setPushing(false);
    }
  };

  const handlePull = async () => {
    setPulling(true);
    try {
      const res = await fetch('/api/git/pull', { method: 'POST' });
      const data = await res.json();
      if (data.success) {
        setPushResult({ success: true, message: data.message });
        fetchStatus();
      } else {
        setPushResult({ success: false, message: data.error });
      }
    } catch (err: any) {
      setPushResult({ success: false, message: err.message });
    } finally {
      setPulling(false);
    }
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 overflow-y-auto bg-slate-900/60 backdrop-blur-xs flex items-center justify-center p-4">
      <div className="bg-white rounded-3xl border border-slate-200 shadow-2xl w-full max-w-2xl overflow-hidden">
        {/* Modal Header */}
        <div className="bg-slate-900 text-white p-5 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-slate-800 border border-slate-700 flex items-center justify-center text-emerald-400">
              <GitBranch className="w-5 h-5" />
            </div>
            <div>
              <h3 className="font-bold text-base text-slate-100">
                مركز مزامنة ورفع مستودع GitHub
              </h3>
              <p className="text-xs text-slate-400">
                mohammedalhzmi-spec / masrof-manager1
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

        {/* Content Body */}
        <div className="p-6 space-y-5 max-h-[75vh] overflow-y-auto">
          {/* Active Repo Card */}
          <div className="p-4 rounded-2xl bg-slate-50 border border-slate-200/90 space-y-2.5">
            <div className="flex items-center justify-between">
              <div className="flex items-center gap-2">
                <span className="w-2.5 h-2.5 rounded-full bg-emerald-500"></span>
                <span className="text-xs font-bold text-slate-800">
                  المستودع المرتبط:
                </span>
                <a
                  href="https://github.com/mohammedalhzmi-spec/masrof-manager1"
                  target="_blank"
                  rel="noopener noreferrer"
                  className="text-xs font-mono font-bold text-emerald-700 hover:underline flex items-center gap-1"
                >
                  <span>mohammedalhzmi-spec/masrof-manager1</span>
                  <ExternalLink className="w-3 h-3" />
                </a>
              </div>
              <button
                onClick={fetchStatus}
                disabled={loading}
                className="p-1.5 rounded-lg hover:bg-slate-200 text-slate-600 transition"
                title="تحديث الحالة"
              >
                <RefreshCw className={`w-3.5 h-3.5 ${loading ? 'animate-spin' : ''}`} />
              </button>
            </div>

            {gitStatus?.success ? (
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-2 text-xs pt-2 border-t border-slate-200/60">
                <div className="bg-white p-2.5 rounded-xl border border-slate-200/70">
                  <span className="text-slate-500 block mb-0.5 font-medium">الفرع الحالي:</span>
                  <span className="font-mono font-bold text-slate-900 bg-slate-100 px-2 py-0.5 rounded">
                    {gitStatus.branch}
                  </span>
                </div>
                <div className="bg-white p-2.5 rounded-xl border border-slate-200/70">
                  <span className="text-slate-500 block mb-0.5 font-medium">آخر Commit:</span>
                  <span className="font-mono text-[11px] text-slate-800 block truncate" title={gitStatus.lastCommit}>
                    {gitStatus.lastCommit}
                  </span>
                </div>
              </div>
            ) : (
              <div className="text-xs text-amber-700 bg-amber-50 p-3 rounded-xl border border-amber-200">
                {gitStatus?.error || 'جاري فحص حالة المستودع...'}
              </div>
            )}
          </div>

          {/* GitHub Push Form */}
          <div className="bg-emerald-50/50 p-4 sm:p-5 rounded-2xl border border-emerald-200/90 space-y-3.5">
            <div className="flex items-center gap-2 text-xs font-bold text-emerald-950">
              <UploadCloud className="w-4 h-4 text-emerald-700" />
              <span>رفع التعديلات إلى المستودع (Git Push Direct)</span>
            </div>

            <p className="text-xs text-slate-600 leading-relaxed font-medium">
              لحماية حسابك، يتطلب GitHub توكن وصول شخصي (Personal Access Token) بصلاحية{' '}
              <code className="bg-white px-1.5 py-0.5 rounded font-mono text-emerald-800 border border-emerald-200">
                repo
              </code>{' '}
              لإجراء الرفع المباشر.
            </p>

            {/* Token Input */}
            <div>
              <label className="block text-xs font-bold text-slate-800 mb-1 flex items-center justify-between">
                <span className="flex items-center gap-1">
                  <KeyRound className="w-3.5 h-3.5 text-slate-500" />
                  GitHub Personal Access Token (PAT):
                </span>
                <a
                  href="https://github.com/settings/tokens/new"
                  target="_blank"
                  rel="noopener noreferrer"
                  className="text-emerald-700 hover:underline flex items-center gap-1 font-normal text-[11px]"
                >
                  <span>إنشاء توكن في GitHub</span>
                  <ExternalLink className="w-3 h-3" />
                </a>
              </label>
              <input
                type="password"
                value={token}
                onChange={(e) => setToken(e.target.value)}
                placeholder="ghp_xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                className="w-full px-3.5 py-2.5 bg-white border border-slate-300 rounded-xl text-xs font-mono text-slate-900 focus:ring-2 focus:ring-emerald-500 focus:outline-none shadow-xs"
              />
            </div>

            {/* Commit Message Input */}
            <div>
              <label className="block text-xs font-bold text-slate-800 mb-1 flex items-center gap-1">
                <GitCommit className="w-3.5 h-3.5 text-slate-500" />
                رسالة الالتزام (Commit Message):
              </label>
              <input
                type="text"
                value={commitMessage}
                onChange={(e) => setCommitMessage(e.target.value)}
                placeholder="تحديثات وإضافات نظام المصروفات"
                className="w-full px-3.5 py-2.5 bg-white border border-slate-300 rounded-xl text-xs font-medium text-slate-900 focus:ring-2 focus:ring-emerald-500 focus:outline-none shadow-xs"
              />
            </div>

            {/* Push Button */}
            <button
              onClick={handlePush}
              disabled={pushing || !token.trim()}
              className={`w-full py-2.5 px-4 rounded-xl text-xs font-bold text-white shadow-md flex items-center justify-center gap-2 transition ${
                pushing || !token.trim()
                  ? 'bg-slate-400 cursor-not-allowed'
                  : 'bg-emerald-600 hover:bg-emerald-500 shadow-emerald-700/20 active:scale-95'
              }`}
            >
              {pushing ? (
                <>
                  <RefreshCw className="w-4 h-4 animate-spin" />
                  <span>جاري رفع التعديلات إلى GitHub...</span>
                </>
              ) : (
                <>
                  <UploadCloud className="w-4 h-4" />
                  <span>تنفيذ الرفع الآن (Push to origin main)</span>
                </>
              )}
            </button>
          </div>

          {/* Result Banner */}
          {pushResult && (
            <div
              className={`p-4 rounded-2xl text-xs flex items-start gap-2.5 border ${
                pushResult.success
                  ? 'bg-emerald-50 text-emerald-900 border-emerald-200'
                  : 'bg-red-50 text-red-900 border-red-200'
              }`}
            >
              {pushResult.success ? (
                <CheckCircle2 className="w-4 h-4 text-emerald-600 flex-shrink-0 mt-0.5" />
              ) : (
                <AlertCircle className="w-4 h-4 text-red-600 flex-shrink-0 mt-0.5" />
              )}
              <div className="leading-relaxed whitespace-pre-wrap font-medium">
                {pushResult.message}
              </div>
            </div>
          )}

          {/* Download Full Source Code Archive */}
          <div className="p-4 rounded-2xl bg-slate-100 border border-slate-200 flex items-center justify-between gap-3">
            <div className="flex items-center gap-2.5">
              <div className="w-9 h-9 rounded-lg bg-white border border-slate-300 flex items-center justify-center text-slate-700">
                <FolderArchive className="w-5 h-5" />
              </div>
              <div>
                <h4 className="text-xs font-bold text-slate-900">
                  تحميل كامل كود المشروع (Archive)
                </h4>
                <p className="text-[11px] text-slate-500">
                  حزمة مضغوطة تشمل ملفات تطبيق أندرويد بالكامل وجميع التعديلات
                </p>
              </div>
            </div>

            <a
              href="/api/git/download"
              download="masrof-manager1-source.tar.gz"
              className="px-3.5 py-2 rounded-xl bg-slate-800 hover:bg-slate-700 text-white text-xs font-bold transition flex items-center gap-1.5 shadow-xs"
            >
              <DownloadCloud className="w-3.5 h-3.5" />
              <span>تحميل الحزمة</span>
            </a>
          </div>

          {/* Developer Git Commands reference */}
          <div className="bg-slate-900 text-slate-300 p-4 rounded-2xl text-xs font-mono space-y-2 border border-slate-800">
            <div className="flex items-center gap-1.5 text-slate-400 font-bold font-sans text-xs">
              <Terminal className="w-3.5 h-3.5 text-emerald-400" />
              <span>أوامر سطر الأوامر (Git Terminal Commands):</span>
            </div>
            <p className="text-slate-500 text-[11px] font-sans">
              يمكنك أيضاً استخدام هذه الأوامر في الطرفية الخاصة بحاسوبك:
            </p>
            <div className="bg-slate-950 p-2.5 rounded-lg border border-slate-800/80 text-emerald-400 text-[11px] select-all leading-relaxed">
              git clone https://github.com/mohammedalhzmi-spec/masrof-manager1<br />
              cd masrof-manager1<br />
              git add .<br />
              git commit -m "تحديثات نظام مالية صندوق النظافة"<br />
              git push origin main
            </div>
          </div>
        </div>

        {/* Modal Footer */}
        <div className="bg-slate-50 px-6 py-4 border-t border-slate-200 flex items-center justify-between">
          <span className="text-[11px] text-slate-500 font-medium">
            جميع العمليات مؤمنة ومحفوظة في مجلد Git المحلي
          </span>
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
