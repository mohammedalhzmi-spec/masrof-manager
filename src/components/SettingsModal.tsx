import React, { useState } from 'react';
import {
  X,
  Building2,
  Save,
  RotateCcw,
  UserCheck,
  CheckCircle2,
  Printer,
  Shield,
  Tag,
  Coins,
  Database,
  Monitor,
  Sliders,
  Share2,
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
  const [activeTab, setActiveTab] = useState<
    'profile' | 'print' | 'security' | 'categories' | 'currencies' | 'backup' | 'computer' | 'other' | 'share'
  >('profile');
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
      <div className="bg-white rounded-3xl border border-slate-200 shadow-2xl w-full max-w-4xl overflow-hidden flex flex-col sm:flex-row max-h-[85vh]">
        {/* Sidebar Navigation matching the user's screenshot */}
        <div className="w-full sm:w-72 bg-slate-900 text-slate-200 p-4 flex flex-col justify-between shrink-0 border-b sm:border-b-0 sm:border-l border-slate-800">
          <div>
            <div className="flex items-center justify-between mb-4 pb-3 border-b border-slate-800">
              <div className="flex items-center gap-2.5">
                <div className="w-9 h-9 rounded-xl bg-amber-500/20 border border-amber-500/30 flex items-center justify-center text-amber-400">
                  <Building2 className="w-5 h-5" />
                </div>
                <div>
                  <h3 className="font-bold text-sm text-white">إعدادات النظام</h3>
                  <p className="text-[10px] text-slate-400">صندوق النظافة والتحسين م/إب</p>
                </div>
              </div>
              <button
                onClick={onClose}
                className="p-1 rounded-lg hover:bg-slate-800 text-slate-400 hover:text-white transition sm:hidden"
              >
                <X className="w-5 h-5" />
              </button>
            </div>

            <nav className="space-y-1 text-xs font-bold">
              <button
                onClick={() => setActiveTab('profile')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'profile' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <UserCheck className="w-4 h-4 text-amber-400" />
                <span>البيانات الشخصية</span>
              </button>

              <button
                onClick={() => setActiveTab('print')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'print' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <Printer className="w-4 h-4 text-blue-400" />
                <span>خيارات الطباعة والمسح الضوئي</span>
              </button>

              <button
                onClick={() => setActiveTab('security')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'security' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <Shield className="w-4 h-4 text-red-400" />
                <span>خيارات الأمان</span>
              </button>

              <button
                onClick={() => setActiveTab('categories')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'categories' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <Tag className="w-4 h-4 text-emerald-400" />
                <span>التصنيفات</span>
              </button>

              <button
                onClick={() => setActiveTab('currencies')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'currencies' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <Coins className="w-4 h-4 text-yellow-400" />
                <span>العملات</span>
              </button>

              <button
                onClick={() => setActiveTab('backup')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'backup' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <Database className="w-4 h-4 text-cyan-400" />
                <span>خيارات حفظ البيانات</span>
              </button>

              <button
                onClick={() => setActiveTab('computer')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'computer' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <Monitor className="w-4 h-4 text-purple-400" />
                <span>استعراض البيانات من الكمبيوتر</span>
              </button>

              <button
                onClick={() => setActiveTab('other')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'other' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <Sliders className="w-4 h-4 text-pink-400" />
                <span>خيارات أخرى</span>
              </button>

              <button
                onClick={() => setActiveTab('share')}
                className={`w-full flex items-center gap-3 px-3 py-2.5 rounded-xl transition ${
                  activeTab === 'share' ? 'bg-emerald-600 text-white shadow-md' : 'text-slate-300 hover:bg-slate-800 hover:text-white'
                }`}
              >
                <Share2 className="w-4 h-4 text-indigo-400" />
                <span>مشاركة الحسابات</span>
              </button>
            </nav>
          </div>

          <div className="pt-3 border-t border-slate-800 text-[11px] text-slate-400 text-center">
            إصدار النظام المالي المعتمد v3.2
          </div>
        </div>

        {/* Main Form Content Area */}
        <div className="flex-1 flex flex-col justify-between overflow-hidden bg-white">
          <div className="bg-slate-900 text-white p-4 hidden sm:flex items-center justify-between">
            <h4 className="font-bold text-sm">
              {activeTab === 'profile' && 'البيانات الشخصية والجهة الرسمية'}
              {activeTab === 'print' && 'خيارات الطباعة والمسح الضوئي والخطوط العربية'}
              {activeTab === 'security' && 'خيارات الأمان وحماية المستندات'}
              {activeTab === 'categories' && 'إدارة التصنيفات والبنود المالية'}
              {activeTab === 'currencies' && 'إعدادات العملات والوحدات النقدية'}
              {activeTab === 'backup' && 'خيارات حفظ البيانات والنسخ الاحتياطي'}
              {activeTab === 'computer' && 'استعراض البيانات من الكمبيوتر الشبكي'}
              {activeTab === 'other' && 'خيارات أخرى وتفضيلات النظام'}
              {activeTab === 'share' && 'مشاركة الحسابات والربط المالي'}
            </h4>
            <button
              onClick={onClose}
              className="p-1.5 rounded-lg hover:bg-slate-800 text-slate-400 hover:text-white transition"
            >
              <X className="w-5 h-5" />
            </button>
          </div>

          <form onSubmit={handleSubmit} className="p-6 space-y-4 overflow-y-auto flex-1 text-xs">
            {activeTab === 'profile' && (
              <div className="space-y-3">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Building2 className="w-4 h-4 text-emerald-600" />
                  <span>بيانات الجهة والمؤسسة الرسمية والتواقيع المعتمدة</span>
                </h4>

                <div>
                  <label className="block font-bold text-slate-700 mb-1">اسم الوزارة</label>
                  <input
                    type="text"
                    value={formData.ministryName}
                    onChange={(e) => handleChange('ministryName', e.target.value)}
                    className="w-full px-3 py-2 border border-slate-300 rounded-lg font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                  />
                </div>

                <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
                  <div>
                    <label className="block font-bold text-slate-700 mb-1">اسم الإدارة / الصندوق</label>
                    <input
                      type="text"
                      value={formData.administrationName}
                      onChange={(e) => handleChange('administrationName', e.target.value)}
                      className="w-full px-3 py-2 border border-slate-300 rounded-lg font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                    />
                  </div>
                  <div>
                    <label className="block font-bold text-slate-700 mb-1">الفرع / الإدارة العامة</label>
                    <input
                      type="text"
                      value={formData.branchName}
                      onChange={(e) => handleChange('branchName', e.target.value)}
                      className="w-full px-3 py-2 border border-slate-300 rounded-lg font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                    />
                  </div>
                </div>

                <div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
                  <div>
                    <label className="block font-bold text-slate-700 mb-1">مدير عام الصندوق</label>
                    <input
                      type="text"
                      value={formData.managerName}
                      onChange={(e) => handleChange('managerName', e.target.value)}
                      className="w-full px-3 py-2 border border-slate-300 rounded-lg font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                    />
                  </div>
                  <div>
                    <label className="block font-bold text-slate-700 mb-1">المدير المالي</label>
                    <input
                      type="text"
                      value={formData.financeManagerName}
                      onChange={(e) => handleChange('financeManagerName', e.target.value)}
                      className="w-full px-3 py-2 border border-slate-300 rounded-lg font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                    />
                  </div>
                </div>

                <div>
                  <label className="block font-bold text-slate-700 mb-1">نص تذييل المستند أسفل التوقيعات</label>
                  <input
                    type="text"
                    value={formData.systemFooterNote}
                    onChange={(e) => handleChange('systemFooterNote', e.target.value)}
                    className="w-full px-3 py-2 border border-slate-300 rounded-lg font-medium focus:ring-2 focus:ring-emerald-500 focus:outline-none"
                  />
                </div>
              </div>
            )}

            {activeTab === 'print' && (
              <div className="space-y-4">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Printer className="w-4 h-4 text-blue-600" />
                  <span>خيارات الطباعة، المسح الضوئي المباشر، والخطوط العربية</span>
                </h4>
                <div className="p-4 bg-blue-50 border border-blue-200 rounded-xl space-y-2">
                  <p className="font-bold text-blue-900">المسح الضوئي المباشر للطابعات المكتبية (Live Scan Calibration):</p>
                  <p className="text-slate-700 leading-relaxed">
                    يتيح لك النظام ضبط الهوامش يدوياً باللمس في شاشة معاينة الطباعة لضمان مطابقة تامة للمخرجات الورقية مع طابعات الأوفيس التقليدية (HP & Canon) دون أي إزاحة في الأسطر أو الحواشي.
                  </p>
                </div>
                <div className="p-4 bg-emerald-50 border border-emerald-200 rounded-xl space-y-2">
                  <p className="font-bold text-emerald-900">دعم الخطوط العربية الرسمية المدمجة:</p>
                  <ul className="list-disc list-inside text-slate-700 space-y-1">
                    <li>خط الأميري الرسمي (Amiri) - مطابق للمستندات الحكومية</li>
                    <li>خط شهرزاد الجديد (Scheherazade New) - دقة عالية للزخرفة</li>
                    <li>خط كايرو (Cairo) - عصري وواضح</li>
                    <li>خط تجوال (Tajawal) - هندسي متناسق</li>
                  </ul>
                </div>
              </div>
            )}

            {activeTab === 'security' && (
              <div className="space-y-3">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Shield className="w-4 h-4 text-red-600" />
                  <span>خيارات الأمان وحماية المستندات المالية</span>
                </h4>
                <p className="text-slate-600">تشفير قاعدة البيانات المحلية وحماية الصلاحيات الإدارية.</p>
                <div className="flex items-center gap-2 pt-2">
                  <input type="checkbox" id="sec1" defaultChecked className="rounded accent-emerald-600" />
                  <label htmlFor="sec1" className="font-bold text-slate-800">تأكيد الاعتماد المالي برمز مرور الإدارة</label>
                </div>
              </div>
            )}

            {activeTab === 'categories' && (
              <div className="space-y-3">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Tag className="w-4 h-4 text-emerald-600" />
                  <span>إدارة التصنيفات والبنود المالية</span>
                </h4>
                <p className="text-slate-600">التصنيفات المعتمدة: كهرباء، صيانة، رواتب، وقود، أجور عمال، مستلزمات مكتبية.</p>
              </div>
            )}

            {activeTab === 'currencies' && (
              <div className="space-y-3">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Coins className="w-4 h-4 text-yellow-600" />
                  <span>إعدادات العملات والوحدات النقدية</span>
                </h4>
                <p className="text-slate-600">العملة الأساسية المعتمدة: ريال يمني (ر.ي) مع التفقيط التلقائي.</p>
              </div>
            )}

            {activeTab === 'backup' && (
              <div className="space-y-3">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Database className="w-4 h-4 text-cyan-600" />
                  <span>خيارات حفظ البيانات والنسخ الاحتياطي</span>
                </h4>
                <p className="text-slate-600">حفظ تلقائي في التخزين المحلي للمتصفح (LocalStorage) مع إمكانية التصدير بصيغة JSON و CSV.</p>
              </div>
            )}

            {activeTab === 'computer' && (
              <div className="space-y-3">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Monitor className="w-4 h-4 text-purple-600" />
                  <span>استعراض البيانات من الكمبيوتر</span>
                </h4>
                <p className="text-slate-600">ربط وتزامن البيانات مع أجهزة الكمبيوتر المكتبية عبر الشبكة المحلية أو تصدير التقارير.</p>
              </div>
            )}

            {activeTab === 'other' && (
              <div className="space-y-3">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Sliders className="w-4 h-4 text-pink-600" />
                  <span>خيارات أخرى وتفضيلات الواجهة</span>
                </h4>
                <p className="text-slate-600">تخصيص الثيم والتنبيهات الصوتية وتأثيرات الطباعة.</p>
              </div>
            )}

            {activeTab === 'share' && (
              <div className="space-y-3">
                <h4 className="font-bold text-slate-800 border-b border-slate-200 pb-2 flex items-center gap-1.5">
                  <Share2 className="w-4 h-4 text-indigo-600" />
                  <span>مشاركة الحسابات والربط المالي</span>
                </h4>
                <p className="text-slate-600">مشاركة السجلات المالية والتقارير عبر وسائل التواصل أو السحابة.</p>
              </div>
            )}

            {savedSuccess && (
              <div className="p-3 bg-emerald-50 text-emerald-800 rounded-xl border border-emerald-200 font-bold flex items-center gap-2">
                <CheckCircle2 className="w-4 h-4 text-emerald-600" />
                <span>تم حفظ الإعدادات والتعديلات بنجاح!</span>
              </div>
            )}
          </form>

          {/* Form Actions */}
          <div className="bg-slate-50 px-6 py-4 border-t border-slate-200 flex items-center justify-between">
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
                type="button"
                onClick={handleSubmit}
                className="flex items-center gap-1.5 px-5 py-2 bg-emerald-600 hover:bg-emerald-500 text-white text-xs font-bold rounded-xl shadow-md transition active:scale-95"
              >
                <Save className="w-3.5 h-3.5" />
                <span>حفظ التغييرات</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
