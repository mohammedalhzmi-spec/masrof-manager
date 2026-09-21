import React, { useState } from 'react';
import {
  Printer,
  Edit,
  Trash2,
  Search,
  CheckSquare,
  Square,
  FileSpreadsheet,
  FileCheck2,
  Receipt,
  Eye,
  Calendar,
  User,
  Paperclip,
  FileText,
  Edit3,
  Tag,
  X,
} from 'lucide-react';
import { Document, DocumentType } from '../types';

interface DocumentListProps {
  documents: Document[];
  selectedIds: Set<string>;
  selectedTag?: string | null;
  onSelectTag?: (tag: string | null) => void;
  onToggleSelect: (id: string) => void;
  onSelectAll: () => void;
  onClearSelection: () => void;
  onPrintDocument: (doc: Document) => void;
  onEditDocument: (doc: Document) => void;
  onOpenWordEditor?: (doc: Document) => void;
  onDeleteDocument: (id: string) => void;
  onNewDocument: (type: DocumentType) => void;
}

export const DocumentList: React.FC<DocumentListProps> = ({
  documents,
  selectedIds,
  selectedTag,
  onSelectTag,
  onToggleSelect,
  onSelectAll,
  onClearSelection,
  onPrintDocument,
  onEditDocument,
  onOpenWordEditor,
  onDeleteDocument,
  onNewDocument,
}) => {
  const [filterType, setFilterType] = useState<'ALL' | DocumentType>('ALL');
  const [searchQuery, setSearchQuery] = useState('');
  const [internalSelectedTag, setInternalSelectedTag] = useState<string | null>(null);

  const activeTag = selectedTag !== undefined ? selectedTag : internalSelectedTag;
  const setActiveTag = (tag: string | null) => {
    if (onSelectTag) onSelectTag(tag);
    setInternalSelectedTag(tag);
  };

  const allTags = Array.from(
    new Set(documents.flatMap((d) => d.tags || []).filter(Boolean))
  );

  const filteredDocuments = documents.filter((doc) => {
    if (filterType !== 'ALL' && doc.type !== filterType) {
      return false;
    }
    if (activeTag && !(doc.tags || []).includes(activeTag)) {
      return false;
    }
    if (searchQuery.trim()) {
      const q = searchQuery.toLowerCase();
      const matchNum = doc.documentNumber.toLowerCase().includes(q);
      const matchBeneficiary = (doc.beneficiaryName || '').toLowerCase().includes(q);
      const matchPurpose = (doc.purpose || '').toLowerCase().includes(q);
      const matchTags = (doc.tags || []).some((t) => t.toLowerCase().includes(q));
      return matchNum || matchBeneficiary || matchPurpose || matchTags;
    }
    return true;
  });

  const allSelected =
    filteredDocuments.length > 0 &&
    filteredDocuments.every((d) => selectedIds.has(d.id));

  const getTypeBadge = (type: DocumentType) => {
    switch (type) {
      case 'ORDER':
        return (
          <span className="inline-flex items-center gap-1 px-2.5 py-1 rounded-md text-xs font-bold bg-emerald-100 text-emerald-800 border border-emerald-200">
            <FileSpreadsheet className="w-3.5 h-3.5" />
            أمر صرف مالي
          </span>
        );
      case 'REQUEST':
        return (
          <span className="inline-flex items-center gap-1 px-2.5 py-1 rounded-md text-xs font-bold bg-blue-100 text-blue-800 border border-blue-200">
            <FileCheck2 className="w-3.5 h-3.5" />
            ورقة تقديم طلب
          </span>
        );
      case 'RECEIPT':
        return (
          <span className="inline-flex items-center gap-1 px-2.5 py-1 rounded-md text-xs font-bold bg-amber-100 text-amber-800 border border-amber-200">
            <Receipt className="w-3.5 h-3.5" />
            سند استلام / قبض
          </span>
        );
    }
  };

  return (
    <div className="bg-white rounded-2xl border border-slate-200 shadow-sm overflow-hidden">
      {/* Search & Filter Toolbar */}
      <div className="p-4 sm:p-5 border-b border-slate-200 bg-slate-50/70 flex flex-col md:flex-row md:items-center justify-between gap-3">
        {/* Type Tabs */}
        <div className="flex items-center gap-1.5 flex-wrap">
          <button
            onClick={() => setFilterType('ALL')}
            className={`px-3.5 py-1.5 rounded-lg text-xs font-bold transition ${
              filterType === 'ALL'
                ? 'bg-slate-900 text-white shadow'
                : 'bg-white text-slate-600 hover:bg-slate-200 border border-slate-200'
            }`}
          >
            الكل ({documents.length})
          </button>
          <button
            onClick={() => setFilterType('ORDER')}
            className={`px-3.5 py-1.5 rounded-lg text-xs font-bold transition ${
              filterType === 'ORDER'
                ? 'bg-emerald-700 text-white shadow'
                : 'bg-white text-emerald-800 hover:bg-emerald-50 border border-emerald-200'
            }`}
          >
            أوامر الصرف ({documents.filter((d) => d.type === 'ORDER').length})
          </button>
          <button
            onClick={() => setFilterType('REQUEST')}
            className={`px-3.5 py-1.5 rounded-lg text-xs font-bold transition ${
              filterType === 'REQUEST'
                ? 'bg-blue-700 text-white shadow'
                : 'bg-white text-blue-800 hover:bg-blue-50 border border-blue-200'
            }`}
          >
            طلبات الصرف ({documents.filter((d) => d.type === 'REQUEST').length})
          </button>
          <button
            onClick={() => setFilterType('RECEIPT')}
            className={`px-3.5 py-1.5 rounded-lg text-xs font-bold transition ${
              filterType === 'RECEIPT'
                ? 'bg-amber-700 text-white shadow'
                : 'bg-white text-amber-800 hover:bg-amber-50 border border-amber-200'
            }`}
          >
            سندات القبض ({documents.filter((d) => d.type === 'RECEIPT').length})
          </button>
        </div>

        {/* Search input */}
        <div className="relative w-full md:w-72">
          <input
            type="text"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            placeholder="بحث برقم المستند، المستفيد، البيان..."
            className="w-full pr-9 pl-4 py-2 bg-white border border-slate-300 rounded-lg text-xs font-medium focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500 shadow-sm"
          />
          <Search className="w-4 h-4 text-slate-400 absolute right-3 top-2.5 pointer-events-none" />
        </div>
      </div>

      {/* Tags Filter Bar */}
      {allTags.length > 0 && (
        <div className="px-4 sm:px-5 py-2.5 bg-slate-50 border-b border-slate-200/80 flex items-center gap-1.5 flex-wrap">
          <span className="text-xs font-bold text-slate-500 flex items-center gap-1 ml-1">
            <Tag className="w-3.5 h-3.5 text-blue-600" />
            <span>تصفية بالوسوم:</span>
          </span>

          <button
            onClick={() => setActiveTag(null)}
            className={`px-2.5 py-1 rounded-md text-xs font-bold transition ${
              activeTag === null
                ? 'bg-blue-800 text-white shadow-xs'
                : 'bg-white text-slate-600 hover:bg-slate-200 border border-slate-200'
            }`}
          >
            الكل
          </button>

          {allTags.map((t) => {
            const count = documents.filter((d) => (d.tags || []).includes(t)).length;
            const isCurrent = activeTag === t;
            return (
              <button
                key={t}
                onClick={() => setActiveTag(isCurrent ? null : t)}
                className={`flex items-center gap-1 px-2.5 py-1 rounded-md text-xs font-bold transition ${
                  isCurrent
                    ? 'bg-blue-600 text-white shadow-xs'
                    : 'bg-white text-slate-700 hover:bg-blue-50 hover:text-blue-800 border border-slate-200'
                }`}
              >
                <span>#{t}</span>
                <span
                  className={`text-[10px] px-1.5 py-0.2 rounded-full font-mono font-bold ${
                    isCurrent ? 'bg-blue-700 text-blue-100' : 'bg-slate-100 text-slate-500'
                  }`}
                >
                  {count}
                </span>
              </button>
            );
          })}

          {activeTag && (
            <button
              onClick={() => setActiveTag(null)}
              className="flex items-center gap-1 px-2.5 py-1 rounded-md text-xs font-bold text-red-600 hover:bg-red-50 transition border border-red-200 mr-auto"
            >
              <X className="w-3 h-3" />
              <span>إلغاء تصفية الوسم ({activeTag})</span>
            </button>
          )}
        </div>
      )}

      {/* Selection Summary bar */}
      <div className="px-5 py-2.5 bg-slate-100/70 border-b border-slate-200 flex items-center justify-between text-xs text-slate-600">
        <div className="flex items-center gap-2">
          <button
            onClick={() => (allSelected ? onClearSelection() : onSelectAll())}
            className="flex items-center gap-1.5 font-bold hover:text-slate-900 transition"
          >
            {allSelected ? (
              <CheckSquare className="w-4 h-4 text-emerald-600" />
            ) : (
              <Square className="w-4 h-4 text-slate-400" />
            )}
            <span>{allSelected ? 'إلغاء تحديد الكل' : 'تحديد جميع المستندات'}</span>
          </button>
          {selectedIds.size > 0 && (
            <span className="font-semibold text-emerald-700">
              (تم تحديد {selectedIds.size} من أصل {filteredDocuments.length})
            </span>
          )}
        </div>
        <span className="text-slate-500">
          إجمالي المستندات المعروضة: <strong>{filteredDocuments.length}</strong>
        </span>
      </div>

      {/* Document Items List */}
      {filteredDocuments.length === 0 ? (
        <div className="p-12 text-center">
          <FileSpreadsheet className="w-12 h-12 text-slate-300 mx-auto mb-3" />
          <h4 className="text-base font-bold text-slate-700 mb-1">لا توجد مستندات مطابقة</h4>
          <p className="text-xs text-slate-500 mb-4">
            لم يتم العثور على أي مستندات تطابق معايير البحث أو التصفية الحالية.
          </p>
          <div className="flex justify-center gap-2">
            <button
              onClick={() => onNewDocument('ORDER')}
              className="px-4 py-2 rounded-lg bg-emerald-600 hover:bg-emerald-500 text-white text-xs font-bold transition shadow"
            >
              + إضافة أمر صرف جديد
            </button>
          </div>
        </div>
      ) : (
        <div className="divide-y divide-slate-100">
          {filteredDocuments.map((doc) => {
            const isSelected = selectedIds.has(doc.id);
            return (
              <div
                key={doc.id}
                id={`doc-card-${doc.id}`}
                className={`p-4 sm:p-5 transition hover:bg-slate-50/80 flex flex-col md:flex-row md:items-center justify-between gap-4 ${
                  isSelected ? 'bg-emerald-50/40 border-r-4 border-r-emerald-500' : ''
                }`}
              >
                {/* Left Side: Checkbox & Main Info */}
                <div className="flex items-start gap-3 flex-1 min-w-0">
                  <button
                    onClick={() => onToggleSelect(doc.id)}
                    className="mt-1 flex-shrink-0 text-slate-400 hover:text-emerald-600 transition"
                  >
                    {isSelected ? (
                      <CheckSquare className="w-5 h-5 text-emerald-600" />
                    ) : (
                      <Square className="w-5 h-5 text-slate-300" />
                    )}
                  </button>

                  <div className="flex-1 min-w-0">
                    <div className="flex items-center gap-2 flex-wrap mb-1.5">
                      {getTypeBadge(doc.type)}
                      <span className="font-mono font-black text-slate-800 text-sm bg-slate-100 px-2 py-0.5 rounded border border-slate-200">
                        #{doc.documentNumber}
                      </span>
                      <div className="flex items-center gap-1 text-xs text-slate-500 font-medium">
                        <Calendar className="w-3.5 h-3.5 text-slate-400" />
                        <span>{doc.dateHijri}</span>
                        <span>•</span>
                        <span>{doc.dateGregorian}</span>
                      </div>
                    </div>

                    {/* Beneficiary */}
                    <div className="flex items-center gap-1.5 text-slate-900 font-bold text-base mb-1">
                      <User className="w-4 h-4 text-slate-500 flex-shrink-0" />
                      <span className="truncate">{doc.beneficiaryName}</span>
                    </div>

                    {/* Purpose / Bayan */}
                    <p className="text-xs text-slate-600 line-clamp-2 leading-relaxed mb-2 font-medium">
                      <strong className="text-slate-700">مقابل: </strong>
                      {doc.purpose}
                    </p>

                    {/* Notes / Attachments */}
                    {doc.notes && (
                      <div className="flex items-center gap-1 text-[11px] text-slate-500 bg-slate-50 inline-flex px-2 py-0.5 rounded border border-slate-200/60">
                        <Paperclip className="w-3 h-3 text-slate-400" />
                        <span className="truncate">{doc.notes}</span>
                      </div>
                    )}

                    {/* Document Tags Chips */}
                    {doc.tags && doc.tags.length > 0 && (
                      <div className="flex items-center gap-1.5 flex-wrap mt-2">
                        {doc.tags.map((t) => (
                          <button
                            key={t}
                            type="button"
                            onClick={(e) => {
                              e.stopPropagation();
                              setActiveTag(activeTag === t ? null : t);
                            }}
                            className={`inline-flex items-center gap-1 px-2 py-0.5 rounded-md text-[11px] font-bold transition ${
                              activeTag === t
                                ? 'bg-blue-600 text-white shadow-2xs'
                                : 'bg-blue-50 text-blue-800 hover:bg-blue-100 border border-blue-200/80'
                            }`}
                            title={`تصفية المستندات بوسم #${t}`}
                          >
                            <Tag className="w-2.5 h-2.5" />
                            <span>#{t}</span>
                          </button>
                        ))}
                      </div>
                    )}
                  </div>
                </div>

                {/* Right Side: Financial Amount & Actions */}
                <div className="flex flex-row md:flex-col items-end justify-between md:justify-center gap-3 border-t md:border-t-0 pt-3 md:pt-0 border-slate-100 flex-shrink-0">
                  <div className="text-right">
                    <span className="text-xs text-slate-400 font-semibold block">المبلغ الصافي:</span>
                    <span className="text-xl font-black text-slate-900 font-mono tracking-tight">
                      {doc.amount.toLocaleString('ar-YE')}{' '}
                      <span className="text-xs font-bold text-emerald-600">ريال</span>
                    </span>
                    <span className="text-[11px] text-slate-500 block truncate max-w-xs font-medium">
                      {doc.amountWords}
                    </span>
                  </div>

                  {/* Actions Group */}
                  <div className="flex items-center gap-1.5">
                    {/* Word Editor Button */}
                    {onOpenWordEditor && (
                      <button
                        id={`word-edit-btn-${doc.id}`}
                        onClick={() => onOpenWordEditor(doc)}
                        className="flex items-center gap-1 px-3 py-1.5 rounded-lg bg-blue-50 hover:bg-blue-100 text-blue-800 text-xs font-bold border border-blue-200 transition"
                        title="فتح في محرر وورد لإضافة البيانات وتعديل الورقة"
                      >
                        <Edit3 className="w-3.5 h-3.5 text-blue-600" />
                        <span>محرر وورد</span>
                      </button>
                    )}

                    {/* Print Preview Button */}
                    <button
                      id={`print-btn-${doc.id}`}
                      onClick={() => onPrintDocument(doc)}
                      className="flex items-center gap-1 px-3 py-1.5 rounded-lg bg-emerald-50 hover:bg-emerald-100 text-emerald-800 text-xs font-bold border border-emerald-200 transition"
                      title="معاينة وطباعة المستند الرسمي"
                    >
                      <Printer className="w-3.5 h-3.5" />
                      <span>طباعة رسمية</span>
                    </button>

                    {/* Edit Button */}
                    <button
                      id={`edit-btn-${doc.id}`}
                      onClick={() => onEditDocument(doc)}
                      className="p-1.5 rounded-lg bg-slate-100 hover:bg-slate-200 text-slate-700 text-xs transition"
                      title="تعديل المستند"
                    >
                      <Edit className="w-4 h-4" />
                    </button>

                    {/* Delete Button */}
                    <button
                      id={`delete-btn-${doc.id}`}
                      onClick={() => onDeleteDocument(doc.id)}
                      className="p-1.5 rounded-lg bg-red-50 hover:bg-red-100 text-red-600 text-xs transition"
                      title="حذف المستند"
                    >
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </div>
                </div>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
};
