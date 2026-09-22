import React, { useState, useEffect } from 'react';
import {
  X,
  Save,
  Printer,
  FileSpreadsheet,
  FileCheck2,
  Receipt,
  Sparkles,
  Calendar,
  Edit3,
  Tag,
  Plus,
} from 'lucide-react';
import { Document, DocumentType, DocumentStatus, OrganizationProfile } from '../types';
import { convertNumberToWords } from '../utils/numberToWords';
import { getCurrentGregorianDate, getCurrentHijriDate } from '../utils/dateHelper';
import { DEFAULT_TAG_SUGGESTIONS } from '../utils/initialData';

interface DocumentModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSave: (doc: Document, andPrint?: boolean) => void;
  onOpenWordEditor?: (doc: Document) => void;
  initialDocument?: Document | null;
  defaultType?: DocumentType;
  nextNumber: string;
  organization?: OrganizationProfile;
}

export const DocumentModal: React.FC<DocumentModalProps> = ({
  isOpen,
  onClose,
  onSave,
  onOpenWordEditor,
  initialDocument,
  defaultType = 'ORDER',
  nextNumber,
}) => {
  const [type, setType] = useState<DocumentType>(defaultType);
  const [documentNumber, setDocumentNumber] = useState('');
  const [dateHijri, setDateHijri] = useState('');
  const [dateGregorian, setDateGregorian] = useState('');
  const [amount, setAmount] = useState<number | ''>('');
  const [amountWords, setAmountWords] = useState('');
  const [beneficiaryName, setBeneficiaryName] = useState('');
  const [jobTitle, setJobTitle] = useState('');
  const [monthPeriod, setMonthPeriod] = useState('');
  const [requesterName, setRequesterName] = useState('');
  const [details, setDetails] = useState('');
  const [purpose, setPurpose] = useState('');
  const [notes, setNotes] = useState('');
  const [status, setStatus] = useState<DocumentStatus>('SUBMITTED');
  const [attachmentsCount, setAttachmentsCount] = useState(1);
  const [tags, setTags] = useState<string[]>([]);
  const [newTagInput, setNewTagInput] = useState('');

  useEffect(() => {
    if (initialDocument) {
      setType(initialDocument.type);
      setDocumentNumber(initialDocument.documentNumber);
      setDateHijri(initialDocument.dateHijri);
      setDateGregorian(initialDocument.dateGregorian);
      setAmount(initialDocument.amount);
      setAmountWords(initialDocument.amountWords);
      setBeneficiaryName(initialDocument.beneficiaryName);
      setJobTitle(initialDocument.jobTitle || '');
      setMonthPeriod(initialDocument.monthPeriod || '');
      setRequesterName(initialDocument.requesterName || '');
      setDetails(initialDocument.details || '');
      setPurpose(initialDocument.purpose);
      setNotes(initialDocument.notes || '');
      setStatus(initialDocument.status);
      setAttachmentsCount(initialDocument.attachmentsCount || 1);
      setTags(initialDocument.tags || []);
      setNewTagInput('');
    } else {
      setType(defaultType);
      setDocumentNumber(nextNumber);
      setDateHijri(getCurrentHijriDate());
      setDateGregorian(getCurrentGregorianDate());
      setAmount('');
      setAmountWords('');
      setBeneficiaryName('');
      setJobTitle('');
      setMonthPeriod('سبتمبر');
      setRequesterName('');
      setDetails('');
      setPurpose('');
      setNotes('');
      setStatus('SUBMITTED');
      setAttachmentsCount(1);
      setTags([]);
      setNewTagInput('');
    }
  }, [initialDocument, defaultType, nextNumber, isOpen]);

  const handleAddTag = (tagToAdd?: string) => {
    const raw = tagToAdd !== undefined ? tagToAdd : newTagInput;
    const cleaned = raw.trim().replace(/^#+/, '');
    if (!cleaned) return;
    if (!tags.includes(cleaned)) {
      setTags((prev) => [...prev, cleaned]);
    }
    if (tagToAdd === undefined) {
      setNewTagInput('');
    }
  };

  const handleRemoveTag = (tagToRemove: string) => {
    setTags((prev) => prev.filter((t) => t !== tagToRemove));
  };

  const handleTagKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter' || e.key === ',') {
      e.preventDefault();
      handleAddTag();
    }
  };

  // Handle real-time amount change & tafqeet conversion
  const handleAmountChange = (val: string) => {
    const num = parseFloat(val);
    if (isNaN(num)) {
      setAmount('');
      setAmountWords('');
    } else {
      setAmount(num);
      setAmountWords(convertNumberToWords(num));
    }
  };

  if (!isOpen) return null;

  const buildDocumentObject = (): Document => {
    return {
      id: initialDocument?.id || `doc-${Date.now()}`,
      type,
      documentNumber: documentNumber || nextNumber || '0001',
      dateHijri: dateHijri || getCurrentHijriDate(),
      dateGregorian: dateGregorian || getCurrentGregorianDate(),
      amount: Number(amount) || 0,
      amountWords: amountWords || (amount ? convertNumberToWords(Number(amount)) : ''),
      beneficiaryName: beneficiaryName.trim(),
      jobTitle: jobTitle.trim() || undefined,
      monthPeriod: monthPeriod.trim() || undefined,
      requesterName: requesterName.trim() || undefined,
      details: details.trim() || undefined,
      purpose: purpose.trim(),
      notes: notes.trim() || undefined,
      status,
      tags: tags.length > 0 ? tags : undefined,
      attachmentsCount: Number(attachmentsCount) || 1,
      managerName: initialDocument?.managerName || 'رياض احمد محمد',
      financeManagerName: initialDocument?.financeManagerName || '',
      treasurerName: initialDocument?.treasurerName || '',
      createdAt: initialDocument?.createdAt || Date.now(),
    };
  };

  const handleSubmit = (andPrint = false) => {
    if (!beneficiaryName.trim() && !requesterName.trim()) {
      alert('يرجى كتابة اسم الأخ / المستفيد أو مقدم الطلب');
      return;
    }
    if ((type === 'ORDER' || type === 'RECEIPT') && (amount === '' || amount <= 0)) {
      alert('يرجى تحديد المبلغ بالأرقام');
      return;
    }
    if (!purpose.trim()) {
      alert('يرجى كتابة البيان / وذلك مقابل');
      return;
    }

    const docToSave = buildDocumentObject();
    onSave(docToSave, andPrint);
  };

  const handleOpenWordEditorClick = () => {
    const docToEdit = buildDocumentObject();
    if (onOpenWordEditor) {
      onClose();
      onOpenWordEditor(docToEdit);
    }
  };

  const getFormTitle = () => {
    switch (type) {
      case 'ORDER':
        return 'امر صرف مالي رسمي';
      case 'DISBURSEMENT_REQUEST':
        return 'طلب صرف مالي';
      case 'RECEIPT':
        return 'سند قبض / استلام مالي';
      default:
        return 'مستند رسمي حكومي';
    }
  };

  return (
    <div className="fixed inset-0 z-50 overflow-y-auto bg-slate-900/60 backdrop-blur-xs flex items-center justify-center p-4 font-['Cairo',sans-serif]">
      <div className="bg-white rounded-3xl border border-slate-200 shadow-2xl w-full max-w-2xl overflow-hidden animate-in fade-in duration-150">
        {/* Modal Header */}
        <div className="bg-slate-900 text-white p-5 flex items-center justify-between">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 rounded-xl bg-emerald-600/30 border border-emerald-500/40 flex items-center justify-center text-emerald-400">
              {type === 'ORDER' && <FileSpreadsheet className="w-5 h-5" />}
              {type === 'DISBURSEMENT_REQUEST' && <FileCheck2 className="w-5 h-5" />}
              {type === 'RECEIPT' && <Receipt className="w-5 h-5" />}
            </div>
            <div>
              <h3 className="font-bold text-base text-slate-100">
                {initialDocument ? `تعديل: ${getFormTitle()}` : `إنشاء: ${getFormTitle()}`}
              </h3>
              <p className="text-xs text-slate-400">
                صندوق النظافة والتحسين فرع مديرية الحزم • نموذج رسمي مطابق
              </p>
            </div>
          </div>

          <div className="flex items-center gap-2">
            {onOpenWordEditor && (
              <button
                type="button"
                onClick={handleOpenWordEditorClick}
                className="flex items-center gap-1.5 px-3 py-1.5 rounded-xl bg-blue-600 hover:bg-blue-500 text-white text-xs font-bold transition shadow-sm"
                title="فتح في محرر وورد لإضافة البيانات والتعديل الحي على الورقة"
              >
                <Edit3 className="w-3.5 h-3.5" />
                <span>محرر وورد</span>
              </button>
            )}

            <button
              onClick={onClose}
              className="p-1.5 rounded-lg hover:bg-slate-800 text-slate-400 hover:text-white transition"
            >
              <X className="w-5 h-5" />
            </button>
          </div>
        </div>

        {/* Form Body */}
        <div className="p-6 space-y-4 max-h-[75vh] overflow-y-auto">
          {/* Document Type Selector (only on create) */}
          {!initialDocument && (
            <div className="grid grid-cols-3 gap-2 p-1.5 bg-slate-100 rounded-xl border border-slate-200">
              <button
                type="button"
                onClick={() => setType('ORDER')}
                className={`py-2 px-3 rounded-lg text-xs font-bold transition flex items-center justify-center gap-1.5 ${
                  type === 'ORDER'
                    ? 'bg-blue-800 text-white shadow'
                    : 'text-slate-600 hover:bg-white'
                }`}
              >
                <FileSpreadsheet className="w-3.5 h-3.5" />
                <span>امر صرف</span>
              </button>
              <button
                type="button"
                onClick={() => setType('DISBURSEMENT_REQUEST')}
                className={`py-2 px-3 rounded-lg text-xs font-bold transition flex items-center justify-center gap-1.5 ${
                  type === 'DISBURSEMENT_REQUEST'
                    ? 'bg-blue-800 text-white shadow'
                    : 'text-slate-600 hover:bg-white'
                }`}
              >
                <FileCheck2 className="w-3.5 h-3.5" />
                <span>طلب صرف</span>
              </button>
              <button
                type="button"
                onClick={() => setType('RECEIPT')}
                className={`py-2 px-3 rounded-lg text-xs font-bold transition flex items-center justify-center gap-1.5 ${
                  type === 'RECEIPT'
                    ? 'bg-blue-800 text-white shadow'
                    : 'text-slate-600 hover:bg-white'
                }`}
              >
                <Receipt className="w-3.5 h-3.5" />
                <span>ورقة إستلام</span>
              </button>
            </div>
          )}

          {/* Number & Dates Row */}
          <div className="grid grid-cols-1 sm:grid-cols-4 gap-3">
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">
                رقم المستند (NO)
              </label>
              <input
                type="text"
                value={documentNumber}
                onChange={(e) => setDocumentNumber(e.target.value)}
                placeholder="0001"
                className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-mono font-bold focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">
                التاريخ الهجري
              </label>
              <input
                type="text"
                value={dateHijri}
                onChange={(e) => setDateHijri(e.target.value)}
                placeholder="1446/03/10هـ"
                className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">
                التاريخ الميلادي
              </label>
              <input
                type="text"
                value={dateGregorian}
                onChange={(e) => setDateGregorian(e.target.value)}
                placeholder="2026/09/20م"
                className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
              />
            </div>
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">
                المرفقات
              </label>
              <input
                type="number"
                min="0"
                value={attachmentsCount}
                onChange={(e) => setAttachmentsCount(parseInt(e.target.value) || 0)}
                className="w-full px-3 py-2 border border-slate-300 rounded-lg text-xs font-mono text-center focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
              />
            </div>
          </div>

          {/* Beneficiary Name / Requester */}
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">
              {type === 'ORDER'
                ? 'للإخ / وه (المستفيد الأول / الجهة)'
                : type === 'RECEIPT'
                ? 'انا الموقع ادناه (المستلم)'
                : 'اسم مقدم الطلب / الجهة'}
            </label>
            <input
              type="text"
              value={beneficiaryName}
              onChange={(e) => setBeneficiaryName(e.target.value)}
              placeholder="الاسم الرباعي أو اسم الجهة أو التاجر"
              className="w-full px-3.5 py-2 border border-slate-300 rounded-lg text-xs font-bold focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
            />
          </div>

          {/* Receipt Specific Fields */}
          {type === 'RECEIPT' && (
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-3 bg-amber-50/60 p-3 rounded-xl border border-amber-200">
              <div>
                <label className="block text-xs font-bold text-amber-900 mb-1">
                  واعمل بوظيفة
                </label>
                <input
                  type="text"
                  value={jobTitle}
                  onChange={(e) => setJobTitle(e.target.value)}
                  placeholder="مثال: عامل نظافة ميداني / سائق قلاب"
                  className="w-full px-3 py-1.5 border border-amber-300 bg-white rounded-lg text-xs focus:ring-2 focus:ring-amber-500 focus:outline-hidden"
                />
              </div>
              <div>
                <label className="block text-xs font-bold text-amber-900 mb-1">
                  مستحقات لشهر
                </label>
                <input
                  type="text"
                  value={monthPeriod}
                  onChange={(e) => setMonthPeriod(e.target.value)}
                  placeholder="مثال: سبتمبر / رمضان"
                  className="w-full px-3 py-1.5 border border-amber-300 bg-white rounded-lg text-xs focus:ring-2 focus:ring-amber-500 focus:outline-hidden"
                />
              </div>
            </div>
          )}

          {/* Amount & Tafqeet */}
          <div className="p-4 bg-slate-50 rounded-2xl border border-slate-200 space-y-3">
            <div className="flex items-center justify-between">
              <label className="text-xs font-bold text-slate-800 flex items-center gap-1.5">
                <span>المبلغ المالي بالأرقام (ريال يمني)</span>
                {type === 'ORDER' && (
                  <span className="text-[10px] text-blue-700 bg-blue-100 px-1.5 py-0.5 rounded">
                    يظهر في المستطيل المخصص
                  </span>
                )}
              </label>
              {amount !== '' && (
                <span className="text-xs font-mono font-bold text-emerald-700">
                  {amount.toLocaleString('ar-YE')} ر.ي
                </span>
              )}
            </div>

            <div className="relative">
              <input
                type="number"
                min="0"
                step="any"
                value={amount}
                onChange={(e) => handleAmountChange(e.target.value)}
                placeholder="أدخل المبلغ مثل: 150000"
                className="w-full px-4 py-2.5 bg-white border border-slate-300 rounded-xl text-sm font-mono font-bold focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
              />
              <span className="absolute left-3 top-2.5 text-xs font-bold text-slate-400">
                ريال يمني
              </span>
            </div>

            {/* Tafqeet in Arabic Words */}
            <div>
              <div className="flex items-center justify-between mb-1">
                <label className="text-xs font-bold text-slate-700 flex items-center gap-1">
                  <Sparkles className="w-3.5 h-3.5 text-amber-500" />
                  <span>المبلغ كتابةً (التفقيط المالي التلقائي):</span>
                </label>
              </div>
              <input
                type="text"
                value={amountWords}
                onChange={(e) => setAmountWords(e.target.value)}
                placeholder="يتم توليد التفقيط المالي بالريال اليمني تلقائياً"
                className="w-full px-3.5 py-2 bg-white border border-slate-300 rounded-lg text-xs font-bold text-slate-800 focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
              />
            </div>
          </div>

          {/* Purpose / البيان (وذالك مقابل) */}
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">
              البيان الرسمي / وذالك مقابل
            </label>
            <textarea
              rows={2}
              value={purpose}
              onChange={(e) => setPurpose(e.target.value)}
              placeholder="شرح سبب ومبرر الصرف الرسمي وتفاصيل البنود..."
              className="w-full px-3.5 py-2 border border-slate-300 rounded-lg text-xs leading-relaxed focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
            />
          </div>

          {/* Details for Request */}
          {type === 'DISBURSEMENT_REQUEST' && (
            <div>
              <label className="block text-xs font-bold text-slate-700 mb-1">
                التفاصيل (تظهر على الأسطر المنقطة في ورقة الطلب)
              </label>
              <textarea
                rows={3}
                value={details}
                onChange={(e) => setDetails(e.target.value)}
                placeholder="بيان تفصيلي بالأصناف المطلوبة، الكميات، والمبررات الميدانية..."
                className="w-full px-3.5 py-2 border border-slate-300 rounded-lg text-xs leading-relaxed focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
              />
            </div>
          )}

          {/* Notes */}
          <div>
            <label className="block text-xs font-bold text-slate-700 mb-1">
              الملاحظات والوثائق المرفقة
            </label>
            <input
              type="text"
              value={notes}
              onChange={(e) => setNotes(e.target.value)}
              placeholder="مثال: مرفق أصل الفاتورة، كشف الفحص والاستلام، محضر المعاينة"
              className="w-full px-3.5 py-2 border border-slate-300 rounded-lg text-xs focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
            />
          </div>

          {/* Tags / نظام الوسوم وتصنيف المصروفات */}
          <div className="bg-slate-50/90 p-3.5 rounded-xl border border-slate-200">
            <div className="flex items-center justify-between mb-1.5">
              <label className="flex items-center gap-1.5 text-xs font-bold text-slate-800">
                <Tag className="w-3.5 h-3.5 text-blue-600" />
                <span>وسوم وتصنيف المصروف (Tags)</span>
                <span className="text-[10px] font-normal text-slate-500">
                  (لتسهيل الفلترة والبحث وإعداد تقارير المصروفات)
                </span>
              </label>
              {tags.length > 0 && (
                <span className="text-[11px] text-blue-700 font-bold bg-blue-100/70 px-2 py-0.5 rounded-full">
                  {tags.length} وسم محدد
                </span>
              )}
            </div>

            {/* Active Tags Pills */}
            <div className="flex flex-wrap items-center gap-1.5 mb-2.5 min-h-[36px] p-2 bg-white border border-slate-200 rounded-lg">
              {tags.length === 0 ? (
                <span className="text-[11px] text-slate-400 self-center px-1">
                  لا توجد وسوم مضافة حالياً. اختر من المقترحات أدناه أو اكتب وسماً مخصصاً.
                </span>
              ) : (
                tags.map((t) => (
                  <span
                    key={t}
                    className="inline-flex items-center gap-1.5 px-2.5 py-1 rounded-md text-xs font-bold bg-blue-50 text-blue-800 border border-blue-200 shadow-2xs"
                  >
                    <span>#{t}</span>
                    <button
                      type="button"
                      onClick={() => handleRemoveTag(t)}
                      className="text-blue-400 hover:text-red-600 hover:bg-blue-100 rounded-full p-0.5 transition"
                      title="حذف الوسم"
                    >
                      <X className="w-3 h-3" />
                    </button>
                  </span>
                ))
              )}
            </div>

            {/* Custom Tag Input + Quick Add Button */}
            <div className="flex items-center gap-2 mb-2">
              <div className="relative flex-1">
                <input
                  type="text"
                  value={newTagInput}
                  onChange={(e) => setNewTagInput(e.target.value)}
                  onKeyDown={handleTagKeyDown}
                  placeholder="اكتب وسماً مخصصاً (مثال: كهرباء، صيانة، رواتب، وقود)... واضغط Enter"
                  className="w-full pl-3 pr-8 py-1.5 bg-white border border-slate-300 rounded-lg text-xs font-medium focus:ring-2 focus:ring-blue-500 focus:outline-hidden"
                />
                <Tag className="w-3.5 h-3.5 text-slate-400 absolute right-2.5 top-2.5 pointer-events-none" />
              </div>
              <button
                type="button"
                onClick={() => handleAddTag()}
                disabled={!newTagInput.trim()}
                className="flex items-center gap-1 px-3 py-1.5 rounded-lg bg-blue-800 hover:bg-blue-700 disabled:opacity-40 text-white text-xs font-bold transition shadow-xs"
              >
                <Plus className="w-3.5 h-3.5" />
                <span>إضافة وسم</span>
              </button>
            </div>

            {/* Quick Suggestions Chips */}
            <div className="flex items-center gap-1.5 flex-wrap pt-1">
              <span className="text-[11px] font-bold text-slate-500">مقترحات شائعة:</span>
              {DEFAULT_TAG_SUGGESTIONS.map((sug) => {
                const isSelected = tags.includes(sug);
                return (
                  <button
                    key={sug}
                    type="button"
                    onClick={() => (isSelected ? handleRemoveTag(sug) : handleAddTag(sug))}
                    className={`text-[11px] px-2 py-0.5 rounded-md font-bold transition border ${
                      isSelected
                        ? 'bg-blue-600 text-white border-blue-600 shadow-2xs'
                        : 'bg-white text-slate-600 border-slate-200 hover:bg-slate-100 hover:border-slate-300'
                    }`}
                  >
                    {isSelected ? '✓ ' : '+ '}
                    {sug}
                  </button>
                );
              })}
            </div>
          </div>
        </div>

        {/* Modal Footer */}
        <div className="bg-slate-100 p-4 border-t border-slate-200 flex items-center justify-between flex-wrap gap-2">
          <div className="flex items-center gap-2">
            <span className="text-xs text-slate-500">الحالة:</span>
            <select
              value={status}
              onChange={(e) => setStatus(e.target.value as DocumentStatus)}
              className="bg-white border border-slate-300 rounded-lg text-xs px-2.5 py-1 font-bold text-slate-700 focus:outline-hidden"
            >
              <option value="SUBMITTED">مقدم / قيد الإجراء</option>
              <option value="APPROVED">معتمد رسمياً</option>
              <option value="PAID">تم الصرف / خالص</option>
              <option value="DRAFT">مسودة</option>
            </select>
          </div>

          <div className="flex items-center gap-2">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 rounded-xl text-xs font-bold text-slate-600 hover:bg-slate-200 transition"
            >
              إلغاء
            </button>
            <button
              type="button"
              onClick={() => handleSubmit(false)}
              className="flex items-center gap-1.5 px-4 py-2 rounded-xl bg-blue-800 hover:bg-blue-700 text-white text-xs font-bold shadow-sm transition"
            >
              <Save className="w-3.5 h-3.5" />
              <span>حفظ المستند</span>
            </button>
            <button
              type="button"
              onClick={() => handleSubmit(true)}
              className="flex items-center gap-1.5 px-4 py-2 rounded-xl bg-emerald-600 hover:bg-emerald-500 text-white text-xs font-bold shadow-sm transition"
            >
              <Printer className="w-3.5 h-3.5" />
              <span>حفظ ومعاينة الطباعة</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
