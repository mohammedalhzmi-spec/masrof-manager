import React, { useState } from 'react';
import {
  X,
  Building2,
  Save,
  RotateCcw,
  UserCheck,
  CheckCircle2,
} from 'lucide-react';
import { OrganizationProfile } from '../types';
import { initialOrganizationProfile } from '../utils/initialData';

interface SettingsModalProps {
  isOpen: boolean;
  onClose: () => void;
  organization: OrganizationProfile;
  onSave: (profile: OrganizationProfile) => void;
}

export const SettingsModal: React.FC<SettingsModalProps> = ({
  isOpen,
  onClose,
  organization,
  onSave,
}) => {
  const [formData, setFormData] = useState<OrganizationProfile>(organization);
  const [savedSuccess, setSavedSuccess] = useState(false);

  if (!isOpen) return null;

  const handleChange = (field: keyof OrganizationProfile, value: any) => {
    setFormData((prev) => ({ ...prev, [field]: value }));
  };

  const handleReset = () => {
    if (window.confirm('هل تريد استعادة بيانات الصندوق الرسمية الافتراضية؟')) {
      setFormData(initialOrganizationProfile);
    }
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave(formData);
    setSavedSuccess(true);
    setTimeout(() => {
      setSavedSuccess(false);
      onClose();
    }, 1200);
  };

  return (
    <div className="fixed inset-0 z-50 overflow-y-auto bg-slate-900/60 backdrop-blur-xs flex items-center justify-center p-4">
      <div className="bg-white rounded-3xl border border-slate-200 shadow-2xl w-full max-w-2xl overflow-hidden">
        {/* Modal Header */}
        <div className="bg-slate-900 text-white p-5 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-amber-500/20 border border-amber-500/30 flex items-center justify-center text-amber-400">
              <Building2 className="w-5 h-5" />
            </div>
            <div>
              <h3 className="font-bold text-base text-slate-100">
                إعدادات صندوق النظافة والتواقيع الرسمية
              </h3>
              <p className="text-xs text-slate-400">
                البيانات المعتمدة التي تظهر في ترويسة وتذييل المطبوعات الرسمية
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

        {/* Form Body */}
        <form onSubmit={handleSubmit} className="p-6 space-y-5 max-h-[75vh] overflow-y-auto">
          {/* Section 1: Official Entity Details */}
          <div className="space-y-3">
            <h4 className="text-xs font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
              <Building2 className="w-4 h-4 text-emerald-600" />
              <span>بيانات الجهة والمؤسسة الرسمية</span>
            </h4>

            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">
                اسم الوزارة
              </label>
              <input
                type="text"
                value={formData.ministryName}
                onChange={(e) => handleChange('ministryName', e.target.value)}
                className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
              />
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">
                  اسم الإدارة / الصندوق
                </label>
                <input
                  type="text"
                  value={formData.administrationName}
                  onChange={(e) => handleChange('administrationName', e.target.value)}
                  className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">
                  الفرع / الإدارة العامة
                </label>
                <input
                  type="text"
                  value={formData.branchName}
                  onChange={(e) => handleChange('branchName', e.target.value)}
                  className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                />
              </div>
            </div>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">
                  العنوان الرسمي
                </label>
                <input
                  type="text"
                  value={formData.address}
                  onChange={(e) => handleChange('address', e.target.value)}
                  className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">
                  أرقام الهاتف / التواصل
                </label>
                <input
                  type="text"
                  value={formData.phone}
                  onChange={(e) => handleChange('phone', e.target.value)}
                  className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                />
              </div>
            </div>
          </div>

          {/* Section 2: Official Signatories */}
          <div className="space-y-3 pt-2">
            <h4 className="text-xs font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
              <UserCheck className="w-4 h-4 text-emerald-600" />
              <span>المعتمدون الرسميون والتواقيع (تذييل المستندات)</span>
            </h4>

            <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">
                  مدير عام الصندوق
                </label>
                <input
                  type="text"
                  value={formData.managerName}
                  onChange={(e) => handleChange('managerName', e.target.value)}
                  className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">
                  المدير المالي
                </label>
                <input
                  type="text"
                  value={formData.financeManagerName}
                  onChange={(e) => handleChange('financeManagerName', e.target.value)}
                  className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">
                  المراجع الداخلي
                </label>
                <input
                  type="text"
                  value={formData.auditorName}
                  onChange={(e) => handleChange('auditorName', e.target.value)}
                  className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-700 mb-1">
                  أمين الصندوق
                </label>
                <input
                  type="text"
                  value={formData.treasurerName}
                  onChange={(e) => handleChange('treasurerName', e.target.value)}
                  className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                />
              </div>
            </div>

            <div className="pt-2">
              <label className="block text-xs font-bold text-slate-700 mb-1">
                نص تذييل المستند أسفل التوقيعات
              </label>
              <input
                type="text"
                value={formData.systemFooterNote}
                onChange={(e) => handleChange('systemFooterNote', e.target.value)}
                className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
              />
            </div>
          </div>

          {savedSuccess && (
            <div className="p-3 bg-emerald-50 text-emerald-800 rounded-xl border border-emerald-200 text-xs font-bold flex items-center gap-2">
              <CheckCircle2 className="w-4 h-4 text-emerald-600" />
              <span>تم حفظ الإعدادات والتواقيع بنجاح!</span>
            </div>
          )}

          {/* Form Actions */}
          <div className="bg-slate-50 -mx-6 -mb-6 px-6 py-4 border-t border-slate-200 flex items-center justify-between">
            <button
              type="button"
              onClick={handleReset}
              className="flex items-center gap-1.5 px-3 py-2 text-xs font-bold text-slate-600 hover:text-slate-800 transition"
            >
              <RotateCcw className="w-3.5 h-3.5" />
              <span>استعادة الافتراضي</span>
            </button>

            <div className="flex items-center gap-2">
              <button
                type="button"
                onClick={onClose}
                className="px-4 py-2 text-xs font-bold text-slate-600 hover:text-slate-800 transition"
              >
                إلغاء
              </button>
              <button
                type="submit"
                className="flex items-center gap-1.5 px-5 py-2 bg-emerald-600 hover:bg-emerald-500 text-white text-xs font-bold rounded-xl shadow-md transition active:scale-95"
              >
                <Save className="w-3.5 h-3.5" />
                <span>حفظ التغييرات</span>
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};
