import React, { useState } from 'react';
import {
  Printer,
  ArrowRight,
  FileDown,
  Image as ImageIcon,
  FileText,
  Share2,
  Stamp,
  Edit3,
  CheckCircle2,
  Sliders,
  Type,
} from 'lucide-react';
import { Document, OrganizationProfile } from '../types';
import { DocumentOfficialTemplate } from './DocumentOfficialTemplate';
import { convertNumberToWords } from '../utils/numberToWords';
import { exportToPdf, exportToImage, exportToWord, shareDocument } from '../utils/exportHelper';
import { WordDocumentEditorModal } from './WordDocumentEditorModal';

interface OfficialPrintViewProps {
  documents: Document[];
  organization: OrganizationProfile;
  onBack: () => void;
  onUpdateDocument?: (doc: Document) => void;
}

export const OfficialPrintView: React.FC<OfficialPrintViewProps> = ({
  documents,
  organization,
  onBack,
  onUpdateDocument,
}) => {
  const [showStamp, setShowStamp] = useState<boolean>(true);
  const [inlineEditEnabled, setInlineEditEnabled] = useState<boolean>(true);
  const [editingDoc, setEditingDoc] = useState<Document | null>(null);
  const [activeExportingId, setActiveExportingId] = useState<string | null>(null);
  const [feedback, setFeedback] = useState<string | null>(null);

  // Live Scan & Margin Calibration state
  const [showCalibration, setShowCalibration] = useState<boolean>(false);
  const [marginTop, setMarginTop] = useState<number>(0);
  const [marginLeft, setMarginLeft] = useState<number>(0);
  const [printScale, setPrintScale] = useState<number>(1);
  const [selectedFont, setSelectedFont] = useState<string>('Amiri');

  const handleFieldChangeInPrint = (docId: string, field: keyof Document, value: any) => {
    if (!onUpdateDocument) return;
    const target = documents.find((d) => d.id === docId);
    if (!target) return;
    const updated = { ...target, [field]: value };
    if (field === 'amount') {
      const num = typeof value === 'number' ? value : parseFloat(value);
      if (!isNaN(num)) {
        updated.amount = num;
        updated.amountWords = convertNumberToWords(num);
      }
    }
    onUpdateDocument(updated);
    try {
      localStorage.setItem(`masrof_draft_${updated.id}`, JSON.stringify(updated));
    } catch (e) {
      console.error('Failed to auto-save in print view', e);
    }
  };

  const handlePrint = () => {
    window.print();
  };

  const handlePdfSingle = async (doc: Document, idx: number) => {
    setActiveExportingId(doc.id);
    const elementId = `official-print-doc-${doc.id}`;
    const orientation = doc.type === 'ORDER' ? 'landscape' : 'portrait';
    const filename = `${doc.type}_رقم_${doc.documentNumber}`;
    await exportToPdf(elementId, filename, orientation);
    setActiveExportingId(null);
  };

  const handleImageSingle = async (doc: Document) => {
    setActiveExportingId(doc.id);
    const elementId = `official-print-doc-${doc.id}`;
    const filename = `${doc.type}_رقم_${doc.documentNumber}`;
    await exportToImage(elementId, filename);
    setActiveExportingId(null);
  };

  const handleWordSingle = (doc: Document) => {
    exportToWord(doc, organization);
    setFeedback('تم إنشاء وتصدير ملف Word بنجاح!');
    setTimeout(() => setFeedback(null), 3000);
  };

  const handleShareSingle = async (doc: Document) => {
    const elementId = `official-print-doc-${doc.id}`;
    const title = `${doc.type === 'ORDER' ? 'أمر صرف' : doc.type === 'RECEIPT' ? 'ورقة إستلام' : 'ورقة تقديم طلب'} رقم ${doc.documentNumber}`;
    const res = await shareDocument(elementId, title);
    if (res.method === 'clipboard') {
      alert('تم نسخ صورة المستند إلى الحافظة بنجاح!');
    }
  };

  return (
    <div className="min-h-screen bg-slate-300/80 p-4 sm:p-8 font-['Cairo',sans-serif]">
      {/* Control Top Bar (Hidden in Print) */}
      <div className="max-w-5xl mx-auto mb-6 bg-slate-900 text-white p-4 rounded-2xl shadow-xl flex items-center justify-between flex-wrap gap-4 no-print border border-slate-800">
        <div className="flex items-center gap-3">
          <button
            onClick={onBack}
            className="flex items-center gap-1.5 px-3.5 py-1.5 rounded-xl bg-slate-800 hover:bg-slate-700 text-slate-200 text-xs font-bold transition border border-slate-700"
          >
            <ArrowRight className="w-4 h-4" />
            <span>الرجوع للوحة التحكم</span>
          </button>
          <div>
            <h2 className="font-bold text-sm text-slate-100 flex items-center gap-2">
              <span>معاينة الطباعة الرسمية المعتمدة</span>
              <span className="text-xs bg-emerald-900/60 text-emerald-300 border border-emerald-700/50 px-2 py-0.5 rounded-md font-mono">
                {documents.length} مستند
              </span>
            </h2>
            <p className="text-xs text-slate-400">
              مطابقة حرفية للنماذج الورقية لفرع صندوق النظافة والتحسين مديرية الحزم
            </p>
          </div>
        </div>

        {/* Global Action Toolbar */}
        <div className="flex items-center gap-2 flex-wrap">
          {feedback && (
            <div className="flex items-center gap-1.5 px-3 py-1 rounded-lg bg-emerald-500/20 text-emerald-300 border border-emerald-500/40 text-xs font-bold">
              <CheckCircle2 className="w-4 h-4" />
              <span>{feedback}</span>
            </div>
          )}

          {/* Toggle Direct In-Page Editing */}
          <button
            onClick={() => setInlineEditEnabled(!inlineEditEnabled)}
            className={`flex items-center gap-1.5 px-3.5 py-1.5 rounded-xl border text-xs font-bold transition shadow-xs ${
              inlineEditEnabled
                ? 'bg-blue-600 text-white border-blue-400 ring-2 ring-blue-500/30'
                : 'bg-slate-800 text-slate-300 border-slate-700 hover:text-white'
            }`}
            title="تفعيل التحرير المباشر داخل صفحة المستند بنظام وورد 100%"
          >
            <Edit3 className="w-3.5 h-3.5" />
            <span>التحرير المباشر بالورقة: {inlineEditEnabled ? 'مفعّل 100%' : 'معطّل'}</span>
            {inlineEditEnabled && (
              <span className="w-2 h-2 rounded-full bg-emerald-400 animate-pulse"></span>
            )}
          </button>

          {/* Toggle Official Stamp */}
          <button
            onClick={() => setShowStamp(!showStamp)}
            className={`flex items-center gap-1.5 px-3 py-1.5 rounded-xl border text-xs font-bold transition ${
              showStamp
                ? 'bg-blue-600/30 text-blue-200 border-blue-500/50'
                : 'bg-slate-800 text-slate-400 border-slate-700 hover:text-white'
            }`}
          >
            <Stamp className="w-4 h-4" />
            <span>الختم المعتمد</span>
          </button>

          {/* Toggle Live Scan & Margin Calibration */}
          <button
            onClick={() => setShowCalibration(!showCalibration)}
            className={`flex items-center gap-1.5 px-3.5 py-1.5 rounded-xl border text-xs font-bold transition ${
              showCalibration
                ? 'bg-amber-600 text-white border-amber-400 ring-2 ring-amber-500/30'
                : 'bg-slate-800 text-slate-300 border-slate-700 hover:text-white'
            }`}
            title="المسح الضوئي المباشر وضبط الهوامش يدوياً للطابعات المكتبية"
          >
            <Sliders className="w-3.5 h-3.5" />
            <span>المسح الضوئي والهوامش</span>
          </button>

          {/* Font Family Selector */}
          <div className="flex items-center gap-1 bg-slate-800 px-2.5 py-1 rounded-xl border border-slate-700 text-xs text-slate-300">
            <Type className="w-3.5 h-3.5 text-emerald-400" />
            <select
              value={selectedFont}
              onChange={(e) => setSelectedFont(e.target.value)}
              className="bg-transparent text-white font-bold focus:outline-none cursor-pointer"
              title="اختيار خط الطباعة العربي الرسمي"
            >
              <option value="Amiri" className="bg-slate-900 text-white">خط الأميري (Amiri)</option>
              <option value="Scheherazade New" className="bg-slate-900 text-white">شهرزاد (Scheherazade)</option>
              <option value="Cairo" className="bg-slate-900 text-white">كايرو (Cairo)</option>
              <option value="Tajawal" className="bg-slate-900 text-white">تجوال (Tajawal)</option>
            </select>
          </div>

          {/* Direct Print Button */}
          <button
            id="do-print-btn"
            onClick={handlePrint}
            className="flex items-center gap-2 px-5 py-2 rounded-xl bg-emerald-500 hover:bg-emerald-400 text-slate-950 font-black text-xs shadow-lg transition active:scale-95 cursor-pointer"
          >
            <Printer className="w-4 h-4" />
            <span>طباعة المستندات الآن (Ctrl+P)</span>
          </button>
        </div>

        {/* Live Scan & Margin Calibration Drawer */}
        {showCalibration && (
          <div className="mt-4 pt-4 border-t border-slate-800 grid grid-cols-1 sm:grid-cols-3 gap-4 bg-slate-950/50 p-4 rounded-xl">
            <div>
              <label className="block text-[11px] font-bold text-slate-300 mb-1">
                ضبط الهامش العلوي للطابعة ({marginTop}px):
              </label>
              <input
                type="range"
                min="-50"
                max="100"
                value={marginTop}
                onChange={(e) => setMarginTop(parseInt(e.target.value, 10))}
                className="w-full accent-emerald-500 cursor-pointer"
              />
            </div>
            <div>
              <label className="block text-[11px] font-bold text-slate-300 mb-1">
                ضبط الهامش الأيمن/الأيسر ({marginLeft}px):
              </label>
              <input
                type="range"
                min="-50"
                max="100"
                value={marginLeft}
                onChange={(e) => setMarginLeft(parseInt(e.target.value, 10))}
                className="w-full accent-emerald-500 cursor-pointer"
              />
            </div>
            <div>
              <label className="block text-[11px] font-bold text-slate-300 mb-1">
                تكبير وتصحيح القياس المطبوع ({Math.round(printScale * 100)}%):
              </label>
              <input
                type="range"
                min="0.8"
                max="1.2"
                step="0.02"
                value={printScale}
                onChange={(e) => setPrintScale(parseFloat(e.target.value))}
                className="w-full accent-emerald-500 cursor-pointer"
              />
            </div>
          </div>
        )}
      </div>

      {/* List of Rendered Documents */}
      <div className="max-w-5xl mx-auto space-y-10">
        {documents.map((doc, idx) => {
          const isLandscape = doc.type === 'ORDER';

          return (
            <div key={doc.id} className="relative group">
              {/* Document Actions Bar (Above each document - Hidden in Print) */}
              <div className="no-print bg-slate-800/95 border border-slate-700 text-white rounded-t-xl px-4 py-2 flex items-center justify-between flex-wrap gap-2 text-xs">
                <div className="flex items-center gap-2">
                  <span className="w-2 h-2 rounded-full bg-emerald-400 inline-block"></span>
                  <span className="font-bold text-slate-200">
                    {doc.type === 'ORDER'
                      ? 'امر صرف مالي'
                      : doc.type === 'RECEIPT'
                      ? 'ورقة إستلام'
                      : 'ورقة تقديم طلب'}
                    : <span className="font-mono text-amber-300">NO: {doc.documentNumber}</span>
                  </span>
                  <span className="text-slate-400 text-[11px]">
                    ({doc.beneficiaryName || 'بدون اسم'}) - {doc.amount ? `${doc.amount.toLocaleString('ar-YE')} ريال` : ''}
                  </span>
                </div>

                <div className="flex items-center gap-1.5">
                  <button
                    onClick={() => setEditingDoc(doc)}
                    className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-blue-600 hover:bg-blue-500 text-white font-bold transition shadow-xs"
                    title="فتح في محرر وورد لإضافة البيانات والتعديل المباشر"
                  >
                    <Edit3 className="w-3.5 h-3.5" />
                    <span>محرر وورد</span>
                  </button>

                  <button
                    disabled={activeExportingId === doc.id}
                    onClick={() => handlePdfSingle(doc, idx)}
                    className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-red-600 hover:bg-red-500 text-white font-bold transition shadow-xs disabled:opacity-50"
                    title="تصدير كملف PDF عالي الدقة"
                  >
                    <FileDown className="w-3.5 h-3.5" />
                    <span>PDF</span>
                  </button>

                  <button
                    disabled={activeExportingId === doc.id}
                    onClick={() => handleImageSingle(doc)}
                    className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-emerald-600 hover:bg-emerald-500 text-white font-bold transition shadow-xs disabled:opacity-50"
                    title="حفظ كصورة عالية الدقة PNG"
                  >
                    <ImageIcon className="w-3.5 h-3.5" />
                    <span>صورة HD</span>
                  </button>

                  <button
                    onClick={() => handleWordSingle(doc)}
                    className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-blue-800 hover:bg-blue-700 text-white font-bold transition shadow-xs"
                    title="تصدير كملف Microsoft Word (.doc) قابل للتعديل"
                  >
                    <FileText className="w-3.5 h-3.5" />
                    <span>ملف Word</span>
                  </button>

                  <button
                    onClick={() => handleShareSingle(doc)}
                    className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-indigo-600 hover:bg-indigo-500 text-white font-bold transition shadow-xs"
                    title="مشاركة أو نسخ"
                  >
                    <Share2 className="w-3.5 h-3.5" />
                    <span>مشاركة</span>
                  </button>
                </div>
              </div>

              {/* The Physical Sheet Container */}
              <div
                className="bg-white shadow-2xl overflow-x-auto flex justify-center p-4 sm:p-8 rounded-b-xl print:p-0 print:shadow-none print:rounded-none transition-all"
                style={{
                  marginTop: `${marginTop}px`,
                  marginLeft: `${marginLeft}px`,
                  pageBreakAfter: idx < documents.length - 1 ? 'always' : 'auto',
                }}
              >
                <DocumentOfficialTemplate
                  containerId={`official-print-doc-${doc.id}`}
                  document={{ ...doc, fontFamily: selectedFont }}
                  organization={organization}
                  isEditable={inlineEditEnabled}
                  onFieldChange={(field, val) => handleFieldChangeInPrint(doc.id, field, val)}
                  showStamp={showStamp}
                  scale={printScale}
                />
              </div>
            </div>
          );
        })}
      </div>

      {/* Interactive Word Document Editor Modal */}
      {editingDoc && (
        <WordDocumentEditorModal
          isOpen={true}
          document={editingDoc}
          organization={organization}
          onClose={() => setEditingDoc(null)}
          onSave={(updated) => {
            if (onUpdateDocument) onUpdateDocument(updated);
            setEditingDoc(null);
          }}
        />
      )}
    </div>
  );
};
