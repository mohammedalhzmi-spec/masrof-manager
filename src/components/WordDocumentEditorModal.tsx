import React, { useState, useRef, useEffect } from 'react';
import {
  X,
  Save,
  Printer,
  FileDown,
  Image as ImageIcon,
  FileText,
  Share2,
  ZoomIn,
  ZoomOut,
  RotateCcw,
  Sparkles,
  Stamp,
  Undo2,
  Redo2,
  CheckCircle,
  HelpCircle,
  Eye,
  Edit3,
  Tag,
  Plus,
  Type,
  Shapes,
  Upload,
  Bot,
  Layers,
  Trash2,
  Maximize2,
  Minimize2,
  Sliders,
  Bold,
  Sun,
  Zap,
  Move,
  LayoutTemplate,
  Square,
  Minus,
  Check,
  Shield,
  Coins,
  Building2,
  Scale,
  Calendar,
  Lock,
  PhoneCall,
  SlidersHorizontal,
} from 'lucide-react';
import { Document, DocumentType, OrganizationProfile, CanvasElement } from '../types';
import { DocumentOfficialTemplate } from './DocumentOfficialTemplate';
import { convertNumberToWords } from '../utils/numberToWords';
import { exportToPdf, exportToImage, exportToWord, shareDocument } from '../utils/exportHelper';
import { DEFAULT_TAG_SUGGESTIONS } from '../utils/initialData';
import { WordOfflineAssistantPanel } from './WordOfflineAssistantPanel';

interface WordDocumentEditorModalProps {
  isOpen: boolean;
  onClose: () => void;
  document: Document;
  organization: OrganizationProfile;
  onSave: (updatedDoc: Document, andPrint?: boolean) => void;
}

export const WordDocumentEditorModal: React.FC<WordDocumentEditorModalProps> = ({
  isOpen,
  onClose,
  document: initialDoc,
  organization,
  onSave,
}) => {
  const [doc, setDoc] = useState<Document>(initialDoc);
  const [activeTab, setActiveTab] = useState<'editor' | 'insert' | 'layout' | 'quickFields'>('editor');
  const [zoom, setZoom] = useState<number>(1);
  const [showStamp, setShowStamp] = useState<boolean>(true);
  const [isExporting, setIsExporting] = useState<boolean>(false);
  const [saveFeedback, setSaveFeedback] = useState<string | null>(null);
  const [newTagInput, setNewTagInput] = useState<string>('');

  // Canvas elements state
  const [activeElementId, setActiveElementId] = useState<string | null>(null);
  const [imageUploadLayer, setImageUploadLayer] = useState<'background' | 'foreground'>('background');

  // Assistant state
  const [isAssistantOpen, setIsAssistantOpen] = useState<boolean>(false);

  const containerRef = useRef<HTMLDivElement>(null);
  const fileInputRef = useRef<HTMLInputElement>(null);

  // Auto-Save & 100% Memory Persistence state
  const [autoSaveStatus, setAutoSaveStatus] = useState<'saved' | 'saving'>('saved');
  const [lastSavedTime, setLastSavedTime] = useState<string>('محفوظ الآن');

  // History stack for 100% Word-like Undo / Redo
  const [history, setHistory] = useState<Document[]>([initialDoc]);
  const [historyIndex, setHistoryIndex] = useState<number>(0);

  const pushToHistory = (newDoc: Document) => {
    setHistory((prev) => {
      const sliced = prev.slice(0, historyIndex + 1);
      if (sliced.length >= 30) sliced.shift();
      return [...sliced, newDoc];
    });
    setHistoryIndex((prev) => Math.min(prev + 1, 29));
  };

  const handleUndo = () => {
    if (historyIndex > 0) {
      const prevIdx = historyIndex - 1;
      setHistoryIndex(prevIdx);
      setDoc(history[prevIdx]);
    }
  };

  const handleRedo = () => {
    if (historyIndex < history.length - 1) {
      const nextIdx = historyIndex + 1;
      setHistoryIndex(nextIdx);
      setDoc(history[nextIdx]);
    }
  };

  // Keyboard shortcuts (Ctrl+Z, Ctrl+Y, Ctrl+S)
  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === 'z') {
        if (e.shiftKey) {
          e.preventDefault();
          handleRedo();
        } else {
          e.preventDefault();
          handleUndo();
        }
      } else if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === 'y') {
        e.preventDefault();
        handleRedo();
      } else if ((e.ctrlKey || e.metaKey) && e.key.toLowerCase() === 's') {
        e.preventDefault();
        handleSaveAndNotify(false);
      }
    };
    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [historyIndex, history, doc]);

  // Sync state and check draft memory if initialDoc changes
  useEffect(() => {
    try {
      const savedDraft = localStorage.getItem(`masrof_draft_${initialDoc.id}`);
      if (savedDraft) {
        const parsed = JSON.parse(savedDraft);
        if (parsed && parsed.id === initialDoc.id) {
          setDoc(parsed);
          setHistory([parsed]);
          setHistoryIndex(0);
          return;
        }
      }
    } catch (e) {
      console.error('Failed to parse draft', e);
    }
    setDoc(initialDoc);
    setHistory([initialDoc]);
    setHistoryIndex(0);
  }, [initialDoc]);

  // Debounced continuous auto-save to localStorage & parent state (حفظ البيانات وتذكرها 100%)
  useEffect(() => {
    setAutoSaveStatus('saving');
    const timer = setTimeout(() => {
      onSave(doc, false);
      try {
        localStorage.setItem(`masrof_draft_${doc.id}`, JSON.stringify(doc));
        localStorage.setItem('masrof_last_edited_doc_id', doc.id);
      } catch (e) {
        console.error('Failed to auto-save document draft', e);
      }
      setAutoSaveStatus('saved');
      const now = new Date();
      setLastSavedTime(
        `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}:${now.getSeconds().toString().padStart(2, '0')}`
      );
    }, 450);

    return () => clearTimeout(timer);
  }, [doc]);

  if (!isOpen) return null;

  // Add / Remove Tags
  const handleAddTag = (tagToAdd?: string) => {
    const raw = tagToAdd !== undefined ? tagToAdd : newTagInput;
    const cleaned = raw.trim().replace(/^#+/, '');
    if (!cleaned) return;
    const currentTags = doc.tags || [];
    if (!currentTags.includes(cleaned)) {
      const nextDoc = {
        ...doc,
        tags: [...currentTags, cleaned],
      };
      setDoc(nextDoc);
      pushToHistory(nextDoc);
    }
    if (tagToAdd === undefined) {
      setNewTagInput('');
    }
  };

  const handleRemoveTag = (tagToRemove: string) => {
    const currentTags = doc.tags || [];
    const nextDoc = {
      ...doc,
      tags: currentTags.filter((t) => t !== tagToRemove),
    };
    setDoc(nextDoc);
    pushToHistory(nextDoc);
  };

  // Handle direct field updates
  const handleFieldChange = (field: keyof Document, val: any) => {
    setDoc((prev) => {
      const next = { ...prev, [field]: val };
      // If amount changes, update amountWords automatically
      if (field === 'amount') {
        const num = typeof val === 'number' ? val : parseFloat(val);
        if (!isNaN(num)) {
          next.amount = num;
          next.amountWords = convertNumberToWords(num);
        }
      }
      pushToHistory(next);
      return next;
    });
  };

  // Quick Amount input handler
  const handleAmountInputChange = (val: string) => {
    const num = parseFloat(val);
    if (isNaN(num)) {
      setDoc((prev) => ({ ...prev, amount: 0, amountWords: '' }));
    } else {
      setDoc((prev) => ({
        ...prev,
        amount: num,
        amountWords: convertNumberToWords(num),
      }));
    }
  };

  // ==========================================
  // Canvas Elements Handlers (Real Word Editor)
  // ==========================================
  const handleTriggerImageUpload = (layer: 'background' | 'foreground') => {
    setImageUploadLayer(layer);
    fileInputRef.current?.click();
  };

  const handleImageFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = (event) => {
      const dataUrl = event.target?.result as string;
      if (!dataUrl) return;

      const isBg = imageUploadLayer === 'background';
      const newEl: CanvasElement = {
        id: `img_${Date.now()}`,
        type: 'image',
        x: isBg ? 160 : 250,
        y: isBg ? 130 : 200,
        width: isBg ? 500 : 240,
        height: isBg ? 380 : 180,
        content: dataUrl,
        layer: imageUploadLayer,
        opacity: isBg ? 0.22 : 1, // خلف النص علامة مائية شفافة أو أمام النص صورة واضحة
        rotation: 0,
      };

      setDoc((prev) => ({
        ...prev,
        canvasElements: [...(prev.canvasElements || []), newEl],
      }));
      setActiveElementId(newEl.id);
      setSaveFeedback(
        isBg
          ? 'تم رفع الصورة وجعلها خلف النص (علامة مائية) بنجاح!'
          : 'تم إدراج الصورة أمام النص بنجاح!'
      );
      setTimeout(() => setSaveFeedback(null), 3000);
    };

    reader.readAsDataURL(file);
    e.target.value = '';
  };

  const handleAddTextBox = () => {
    const newEl: CanvasElement = {
      id: `txt_${Date.now()}`,
      type: 'textbox',
      x: 180,
      y: 220,
      width: 260,
      height: 80,
      content: 'ملاحظة: تم تدقيق واعتماد هذه المعاملة مالياً وإدارياً',
      layer: 'foreground',
      opacity: 1,
      fontSize: 15,
      isBold: true,
      color: '#000000',
      backgroundColor: '#fef3c7',
      borderColor: '#d97706',
      borderWidth: 1.5,
      borderStyle: 'solid',
      borderRadius: 6,
    };
    setDoc((prev) => ({
      ...prev,
      canvasElements: [...(prev.canvasElements || []), newEl],
    }));
    setActiveElementId(newEl.id);
  };

  const handleAddShape = (shapeType: CanvasElement['shapeType'] = 'rounded') => {
    const isDivider = shapeType === 'divider';
    const newEl: CanvasElement = {
      id: `shp_${Date.now()}`,
      type: 'shape',
      shapeType,
      x: 160,
      y: 240,
      width: isDivider ? 520 : 200,
      height: isDivider ? 4 : 80,
      content: shapeType || 'rectangle',
      layer: 'foreground',
      opacity: 0.9,
      backgroundColor: isDivider ? '#000000' : '#f8fafc',
      borderColor: '#0f172a',
      borderWidth: 2,
      borderStyle: 'solid',
      borderRadius: shapeType === 'rounded' ? 8 : shapeType === 'circle' ? 9999 : 0,
    };
    setDoc((prev) => ({
      ...prev,
      canvasElements: [...(prev.canvasElements || []), newEl],
    }));
    setActiveElementId(newEl.id);
  };

  const handleAddSymbol = (symbol: string) => {
    const newEl: CanvasElement = {
      id: `sym_${Date.now()}`,
      type: 'symbol',
      x: 340,
      y: 200,
      width: 65,
      height: 65,
      content: symbol,
      layer: 'foreground',
      opacity: 1,
      fontSize: 36,
      isBold: true,
      color: '#000000',
    };
    setDoc((prev) => ({
      ...prev,
      canvasElements: [...(prev.canvasElements || []), newEl],
    }));
    setActiveElementId(newEl.id);
  };

  const handleAddIcon = (iconName: string) => {
    const newEl: CanvasElement = {
      id: `ico_${Date.now()}`,
      type: 'icon',
      iconName,
      x: 350,
      y: 220,
      width: 55,
      height: 55,
      content: iconName,
      layer: 'foreground',
      opacity: 1,
      color: '#1d4ed8',
    };
    setDoc((prev) => ({
      ...prev,
      canvasElements: [...(prev.canvasElements || []), newEl],
    }));
    setActiveElementId(newEl.id);
  };

  const handleUpdateElement = (id: string, updates: Partial<CanvasElement>) => {
    setDoc((prev) => ({
      ...prev,
      canvasElements: (prev.canvasElements || []).map((el) =>
        el.id === id ? { ...el, ...updates } : el
      ),
    }));
  };

  const handleDeleteElement = (id: string) => {
    setDoc((prev) => ({
      ...prev,
      canvasElements: (prev.canvasElements || []).filter((el) => el.id !== id),
    }));
    if (activeElementId === id) {
      setActiveElementId(null);
    }
  };

  // Assistant Update Handler
  const handleApplyAssistantUpdate = (updatedDoc: Document, message: string) => {
    setDoc(updatedDoc);
    setSaveFeedback(message);
    setTimeout(() => {
      setSaveFeedback(null);
    }, 3500);
  };

  // Export Handlers
  const handlePdfExport = async () => {
    setIsExporting(true);
    const orientation = doc.type === 'ORDER' ? 'landscape' : 'portrait';
    const filename = `${doc.type}_رقم_${doc.documentNumber}`;
    await exportToPdf('word-canvas-element', filename, orientation);
    setIsExporting(false);
  };

  const handleImageExport = async () => {
    setIsExporting(true);
    const filename = `${doc.type}_رقم_${doc.documentNumber}`;
    await exportToImage('word-canvas-element', filename);
    setIsExporting(false);
  };

  const handleWordExport = () => {
    exportToWord(doc, organization);
  };

  const handleShare = async () => {
    const title = `${doc.type === 'ORDER' ? 'امر صرف' : doc.type === 'RECEIPT' ? 'ورقة إستلام' : 'ورقة تقديم طلب'} رقم ${doc.documentNumber}`;
    const res = await shareDocument('word-canvas-element', title);
    if (res.method === 'clipboard') {
      alert('تم نسخ صورة المستند إلى الحافظة بنجاح!');
    }
  };

  const handleSaveAndNotify = (andPrint = false) => {
    onSave(doc, andPrint);
    setSaveFeedback('تم حفظ المستند بنجاح!');
    setTimeout(() => {
      setSaveFeedback(null);
    }, 2500);
  };

  const docTypeName =
    doc.type === 'ORDER'
      ? 'امر صرف مالي'
      : doc.type === 'RECEIPT'
      ? 'ورقة إستلام رسمية'
      : 'ورقة تقديم طلب صرف';

  const activeElement = doc.canvasElements?.find((el) => el.id === activeElementId);

  return (
    <div className="fixed inset-0 z-50 bg-slate-950/85 backdrop-blur-xs flex flex-col items-stretch justify-between overflow-hidden">
      {/* Hidden File Input for Real Image Uploads */}
      <input
        type="file"
        ref={fileInputRef}
        onChange={handleImageFileChange}
        accept="image/*"
        className="hidden"
      />

      {/* 1. Word Header / App Bar */}
      <div className="bg-slate-900 border-b border-slate-800 text-white px-4 py-2.5 flex items-center justify-between shrink-0 select-none shadow-md">
        <div className="flex items-center gap-3">
          <div className="w-9 h-9 rounded-xl bg-blue-600 flex items-center justify-center font-bold text-white shadow-sm font-serif text-lg">
            W
          </div>
          <div>
            <div className="flex items-center gap-2">
              <h2 className="text-sm font-bold text-slate-100 flex items-center gap-1.5">
                <span>محرر مستندات وورد المتقدم</span>
                <span className="text-xs px-2 py-0.5 rounded-md bg-blue-900/60 text-blue-200 border border-blue-700/50">
                  {docTypeName} - رقم {doc.documentNumber}
                </span>
              </h2>
            </div>
            <p className="text-[11px] text-slate-400">
              تحكم كامل بالورقة، إدراج صور خلف النص أو أمامه، أشكال، رموز، أختام، وهوامش
            </p>
          </div>
        </div>

        {/* Action Buttons */}
        <div className="flex items-center gap-2">
          {/* Real-time AutoSave Status Indicator */}
          <div className="hidden sm:flex items-center gap-1.5 px-2.5 py-1 rounded-lg bg-slate-800/90 border border-slate-700 text-[11px]">
            {autoSaveStatus === 'saving' ? (
              <span className="flex items-center gap-1.5 text-amber-300 font-bold">
                <span className="w-2 h-2 rounded-full bg-amber-400 animate-ping"></span>
                <span>جارِ الحفظ التلقائي...</span>
              </span>
            ) : (
              <span className="flex items-center gap-1.5 text-emerald-400 font-bold">
                <span className="w-2 h-2 rounded-full bg-emerald-400"></span>
                <span>محفوظ في الذاكرة (100%)</span>
              </span>
            )}
            <span className="text-slate-400 font-mono text-[10px]">
              {lastSavedTime}
            </span>
          </div>

          {saveFeedback && (
            <div className="flex items-center gap-1 px-3 py-1 rounded-lg bg-emerald-500/20 text-emerald-300 border border-emerald-500/40 text-xs font-bold animate-fade-in">
              <CheckCircle className="w-3.5 h-3.5 text-emerald-400" />
              <span>{saveFeedback}</span>
            </div>
          )}

          {/* Smart Assistant Toggle Button */}
          <button
            onClick={() => setIsAssistantOpen(!isAssistantOpen)}
            className={`flex items-center gap-1.5 px-3 py-1.5 rounded-lg text-xs font-bold transition shadow-sm border ${
              isAssistantOpen
                ? 'bg-amber-500/30 text-amber-300 border-amber-500/60 ring-1 ring-amber-400'
                : 'bg-indigo-600 hover:bg-indigo-500 text-white border-indigo-400/40'
            }`}
            title="فتح المساعد الذكي المدمج (100% يعمل بدون إنترنت)"
          >
            <Bot className="w-3.5 h-3.5" />
            <span>المساعد الذكي (بدون نت)</span>
            <span className="w-2 h-2 rounded-full bg-emerald-400 animate-pulse"></span>
          </button>

          <button
            onClick={() => handleSaveAndNotify(false)}
            className="flex items-center gap-1.5 px-4 py-1.5 rounded-lg bg-blue-600 hover:bg-blue-500 text-white text-xs font-bold transition shadow-sm"
          >
            <Save className="w-3.5 h-3.5" />
            <span>حفظ التعديلات</span>
          </button>

          <button
            onClick={() => handleSaveAndNotify(true)}
            className="flex items-center gap-1.5 px-3 py-1.5 rounded-lg bg-emerald-600 hover:bg-emerald-500 text-white text-xs font-bold transition shadow-sm"
          >
            <Printer className="w-3.5 h-3.5" />
            <span>حفظ وطباعة</span>
          </button>

          <button
            onClick={onClose}
            className="p-1.5 rounded-lg text-slate-400 hover:text-white hover:bg-slate-800 transition"
            title="إغلاق المحرر"
          >
            <X className="w-5 h-5" />
          </button>
        </div>
      </div>

      {/* 2. Word Ribbon / Tabs & Tools Toolbar */}
      <div className="bg-slate-800 border-b border-slate-700 px-4 py-2 flex items-center justify-between flex-wrap gap-2 text-xs text-slate-200 shrink-0">
        {/* Left: Ribbon Tabs */}
        <div className="flex items-center gap-1 bg-slate-900 p-0.5 rounded-xl border border-slate-700">
          <button
            onClick={() => setActiveTab('editor')}
            className={`flex items-center gap-1.5 px-3 py-1.5 rounded-lg font-bold transition ${
              activeTab === 'editor'
                ? 'bg-blue-600 text-white shadow-sm'
                : 'text-slate-300 hover:text-white hover:bg-slate-800'
            }`}
          >
            <Edit3 className="w-3.5 h-3.5" />
            <span>الورقة والمعاينة</span>
          </button>

          <button
            onClick={() => setActiveTab('insert')}
            className={`flex items-center gap-1.5 px-3 py-1.5 rounded-lg font-bold transition ${
              activeTab === 'insert'
                ? 'bg-blue-600 text-white shadow-sm'
                : 'text-slate-300 hover:text-white hover:bg-slate-800'
            }`}
          >
            <Plus className="w-3.5 h-3.5" />
            <span>إدراج (صور، أشكال، رموز، نصوص)</span>
          </button>

          <button
            onClick={() => setActiveTab('layout')}
            className={`flex items-center gap-1.5 px-3 py-1.5 rounded-lg font-bold transition ${
              activeTab === 'layout'
                ? 'bg-blue-600 text-white shadow-sm'
                : 'text-slate-300 hover:text-white hover:bg-slate-800'
            }`}
          >
            <LayoutTemplate className="w-3.5 h-3.5" />
            <span>التخطيط والهوامش والقوالب</span>
          </button>

          <button
            onClick={() => setActiveTab('quickFields')}
            className={`flex items-center gap-1.5 px-3 py-1.5 rounded-lg font-bold transition ${
              activeTab === 'quickFields'
                ? 'bg-blue-600 text-white shadow-sm'
                : 'text-slate-300 hover:text-white hover:bg-slate-800'
            }`}
          >
            <FileText className="w-3.5 h-3.5" />
            <span>شريط البيانات السريعة</span>
          </button>
        </div>

        {/* Center: Undo/Redo & Quick Export & Stamp Toggle */}
        <div className="flex items-center gap-1.5">
          {/* Undo / Redo Controls */}
          <div className="flex items-center bg-slate-900 rounded-lg p-0.5 border border-slate-700">
            <button
              onClick={handleUndo}
              disabled={historyIndex <= 0}
              className="p-1.5 rounded text-slate-300 hover:text-white disabled:opacity-30 disabled:hover:text-slate-300 transition"
              title="تراجع (Ctrl+Z)"
            >
              <Undo2 className="w-3.5 h-3.5" />
            </button>
            <button
              onClick={handleRedo}
              disabled={historyIndex >= history.length - 1}
              className="p-1.5 rounded text-slate-300 hover:text-white disabled:opacity-30 disabled:hover:text-slate-300 transition"
              title="إعادة (Ctrl+Y)"
            >
              <Redo2 className="w-3.5 h-3.5" />
            </button>
          </div>
          <button
            disabled={isExporting}
            onClick={handlePdfExport}
            className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-red-600 hover:bg-red-500 text-white font-bold transition active:scale-95 disabled:opacity-50"
            title="تصدير المستند كملف PDF عالي الدقة"
          >
            <FileDown className="w-3.5 h-3.5" />
            <span>PDF</span>
          </button>

          <button
            disabled={isExporting}
            onClick={handleImageExport}
            className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-emerald-600 hover:bg-emerald-500 text-white font-bold transition active:scale-95 disabled:opacity-50"
            title="حفظ كصورة عالية الدقة PNG"
          >
            <ImageIcon className="w-3.5 h-3.5" />
            <span>صورة HD</span>
          </button>

          <button
            onClick={handleWordExport}
            className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-blue-700 hover:bg-blue-600 text-white font-bold transition active:scale-95"
            title="تصدير كملف Microsoft Word (.doc) قابل للتعديل"
          >
            <FileText className="w-3.5 h-3.5" />
            <span>Word (.doc)</span>
          </button>

          <button
            onClick={handleShare}
            className="flex items-center gap-1 px-2.5 py-1 rounded-lg bg-indigo-600 hover:bg-indigo-500 text-white font-bold transition active:scale-95"
            title="مشاركة المستند أو نسخ الصورة"
          >
            <Share2 className="w-3.5 h-3.5" />
            <span>مشاركة</span>
          </button>

          <button
            onClick={() => setShowStamp(!showStamp)}
            className={`flex items-center gap-1 px-2.5 py-1 rounded-lg border transition font-bold ${
              showStamp
                ? 'bg-blue-600/30 text-blue-300 border-blue-500/50'
                : 'bg-slate-700/50 text-slate-400 border-slate-600 hover:text-white'
            }`}
            title="إظهار / إخفاء الختم الدائري المعتمد"
          >
            <Stamp className="w-3.5 h-3.5" />
            <span>الختم المعتمد</span>
          </button>
        </div>

        {/* Right: Zoom controls */}
        <div className="flex items-center gap-1 bg-slate-900/60 p-1 rounded-lg border border-slate-700">
          <button
            onClick={() => setZoom((z) => Math.max(0.6, z - 0.1))}
            className="p-1 hover:bg-slate-700 rounded text-slate-300"
            title="تصغير"
          >
            <ZoomOut className="w-3.5 h-3.5" />
          </button>
          <span className="text-[11px] font-mono font-bold px-1 min-w-[40px] text-center">
            {Math.round(zoom * 100)}%
          </span>
          <button
            onClick={() => setZoom((z) => Math.min(1.4, z + 0.1))}
            className="p-1 hover:bg-slate-700 rounded text-slate-300"
            title="تكبير"
          >
            <ZoomIn className="w-3.5 h-3.5" />
          </button>
          <button
            onClick={() => setZoom(1)}
            className="p-1 hover:bg-slate-700 rounded text-slate-300"
            title="إعادة ضبط 100%"
          >
            <RotateCcw className="w-3.5 h-3.5" />
          </button>
        </div>
      </div>

      {/* 2.1 Sub-Ribbon for INSERT TAB */}
      {activeTab === 'insert' && (
        <div className="bg-slate-850 border-b border-slate-700 px-4 py-2 flex items-center gap-4 flex-wrap text-xs text-slate-200 shrink-0 animate-fade-in">
          {/* Images Section */}
          <div className="flex items-center gap-1.5 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <span className="text-[11px] font-bold text-blue-400 flex items-center gap-1">
              <Upload className="w-3.5 h-3.5" />
              <span>إدراج صورة:</span>
            </span>
            <button
              onClick={() => handleTriggerImageUpload('background')}
              className="px-2.5 py-1 rounded-md bg-indigo-600/40 hover:bg-indigo-600 text-indigo-200 hover:text-white border border-indigo-500/50 font-bold transition flex items-center gap-1"
              title="رفع صورة وجعلها خلف النص كعلامة مائية شفافة"
            >
              <Layers className="w-3 h-3 text-indigo-300" />
              <span>خلف النص (علامة مائية)</span>
            </button>
            <button
              onClick={() => handleTriggerImageUpload('foreground')}
              className="px-2.5 py-1 rounded-md bg-blue-600/40 hover:bg-blue-600 text-blue-200 hover:text-white border border-blue-500/50 font-bold transition flex items-center gap-1"
              title="رفع صورة وإدراجها أمام النص مع إمكانية تحريكها وتغيير حجمها"
            >
              <ImageIcon className="w-3 h-3 text-blue-300" />
              <span>أمام النص (شعار/صورة)</span>
            </button>
          </div>

          {/* Text Box Section */}
          <div className="flex items-center gap-1.5 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <button
              onClick={handleAddTextBox}
              className="px-2.5 py-1 rounded-md bg-amber-600/40 hover:bg-amber-600 text-amber-200 hover:text-white border border-amber-500/50 font-bold transition flex items-center gap-1"
              title="إدراج مربع نص للكتابة عليه وسحبه لأي مكان"
            >
              <Type className="w-3 h-3 text-amber-300" />
              <span>+ مربع نص ملاحظات</span>
            </button>
          </div>

          {/* Shapes Section */}
          <div className="flex items-center gap-1 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <span className="text-[11px] font-bold text-emerald-400 flex items-center gap-1">
              <Shapes className="w-3.5 h-3.5" />
              <span>أشكال:</span>
            </span>
            <button
              onClick={() => handleAddShape('rectangle')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-slate-700 text-slate-300 text-[11px] font-medium"
            >
              مستطيل
            </button>
            <button
              onClick={() => handleAddShape('rounded')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-slate-700 text-slate-300 text-[11px] font-medium"
            >
              دائري الحواف
            </button>
            <button
              onClick={() => handleAddShape('banner')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-slate-700 text-slate-300 text-[11px] font-medium"
            >
              شريط عنوان
            </button>
            <button
              onClick={() => handleAddShape('divider')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-slate-700 text-slate-300 text-[11px] font-medium"
            >
              خط فاصل
            </button>
          </div>

          {/* Symbols Section */}
          <div className="flex items-center gap-1 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <span className="text-[11px] font-bold text-amber-400">رموز:</span>
            {['﷼', '★', '✪', '❖', '✦', '⚖', '✔', '✘'].map((sym) => (
              <button
                key={sym}
                onClick={() => handleAddSymbol(sym)}
                className="w-6 h-6 rounded bg-slate-800 hover:bg-blue-600 hover:text-white text-slate-200 font-bold text-xs flex items-center justify-center transition"
                title={`إدراج الرمز ${sym}`}
              >
                {sym}
              </button>
            ))}
          </div>

          {/* Icons Section */}
          <div className="flex items-center gap-1 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <span className="text-[11px] font-bold text-indigo-400">أيقونات:</span>
            <button
              onClick={() => handleAddIcon('stamp')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-indigo-600 text-slate-200 text-[11px] font-medium"
              title="إدراج أيقونة الختم"
            >
              ختم
            </button>
            <button
              onClick={() => handleAddIcon('shield')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-indigo-600 text-slate-200 text-[11px] font-medium"
              title="إدراج أيقونة الدرع الأمني"
            >
              درع
            </button>
            <button
              onClick={() => handleAddIcon('coins')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-indigo-600 text-slate-200 text-[11px] font-medium"
              title="إدراج أيقونة العملات المالية"
            >
              عملات
            </button>
            <button
              onClick={() => handleAddIcon('building')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-indigo-600 text-slate-200 text-[11px] font-medium"
              title="إدراج أيقونة الإدارة / البنك"
            >
              إدارة
            </button>
            <button
              onClick={() => handleAddIcon('phone')}
              className="px-2 py-0.5 rounded bg-slate-800 hover:bg-indigo-600 text-slate-200 text-[11px] font-medium"
              title="إدراج أيقونة هاتف"
            >
              هاتف
            </button>
          </div>
        </div>
      )}

      {/* 2.2 Sub-Ribbon for LAYOUT & MARGINS TAB */}
      {activeTab === 'layout' && (
        <div className="bg-slate-850 border-b border-slate-700 px-4 py-2 flex items-center gap-4 flex-wrap text-xs text-slate-200 shrink-0 animate-fade-in">
          {/* Page Margins */}
          <div className="flex items-center gap-1.5 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <span className="text-[11px] font-bold text-blue-400 flex items-center gap-1">
              <SlidersHorizontal className="w-3.5 h-3.5" />
              <span>هوامش الورقة:</span>
            </span>
            {(['normal', 'narrow', 'wide'] as const).map((m) => (
              <button
                key={m}
                onClick={() => handleFieldChange('pageMargins', m)}
                className={`px-2.5 py-1 rounded-md font-bold transition text-xs ${
                  doc.pageMargins === m || (!doc.pageMargins && m === 'normal')
                    ? 'bg-blue-600 text-white'
                    : 'bg-slate-800 text-slate-300 hover:text-white'
                }`}
              >
                {m === 'normal' ? 'عادية (24px)' : m === 'narrow' ? 'ضيقة (14px)' : 'عريضة (40px)'}
              </button>
            ))}
          </div>

          {/* Border Styles */}
          <div className="flex items-center gap-1 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <span className="text-[11px] font-bold text-amber-400">إطار المستند:</span>
            {(
              [
                { id: 'classic', label: 'رسمي كلاسيكي' },
                { id: 'double', label: 'مزدوج' },
                { id: 'gold', label: 'ذهبي معتمد' },
                { id: 'islamic', label: 'إسلامي' },
                { id: 'simple', label: 'بسيط' },
              ] as const
            ).map((b) => (
              <button
                key={b.id}
                onClick={() => handleFieldChange('borderStyle', b.id)}
                className={`px-2 py-0.5 rounded text-[11px] font-medium transition ${
                  doc.borderStyle === b.id || (!doc.borderStyle && b.id === 'classic')
                    ? 'bg-amber-600 text-white font-bold'
                    : 'bg-slate-800 text-slate-300 hover:text-white'
                }`}
              >
                {b.label}
              </button>
            ))}
          </div>

          {/* Template Switcher */}
          <div className="flex items-center gap-1 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <span className="text-[11px] font-bold text-emerald-400">نوع القالب:</span>
            <button
              onClick={() => handleFieldChange('type', 'ORDER')}
              className={`px-2.5 py-1 rounded-md font-bold transition text-[11px] ${
                doc.type === 'ORDER' ? 'bg-emerald-600 text-white' : 'bg-slate-800 text-slate-300'
              }`}
            >
              أمر صرف (عرضي)
            </button>
            <button
              onClick={() => handleFieldChange('type', 'DISBURSEMENT_REQUEST')}
              className={`px-2.5 py-1 rounded-md font-bold transition text-[11px] ${
                doc.type === 'DISBURSEMENT_REQUEST' ? 'bg-emerald-600 text-white' : 'bg-slate-800 text-slate-300'
              }`}
            >
              طلب صرف (طولي)
            </button>
            <button
              onClick={() => handleFieldChange('type', 'RECEIPT')}
              className={`px-2.5 py-1 rounded-md font-bold transition text-[11px] ${
                doc.type === 'RECEIPT' ? 'bg-emerald-600 text-white' : 'bg-slate-800 text-slate-300'
              }`}
            >
              ورقة إستلام (طولي)
            </button>
          </div>

          {/* Bold Writing Indicator / Toggle */}
          <div className="flex items-center gap-1 bg-slate-900/80 px-2.5 py-1.5 rounded-xl border border-slate-700">
            <button
              type="button"
              onClick={() =>
                handleFieldChange(
                  'customContentHtml',
                  doc.customContentHtml === 'extra-bold' ? 'bold' : 'extra-bold'
                )
              }
              className={`flex items-center gap-1.5 px-2.5 py-1 rounded-md text-xs font-bold transition ${
                doc.customContentHtml === 'extra-bold'
                  ? 'bg-amber-600 text-white shadow-xs'
                  : 'bg-emerald-600 text-white shadow-xs'
              }`}
              title="انقر للتبديل بين نمط الخط العريض وشديد التغميق"
            >
              <Bold className="w-3.5 h-3.5" />
              <span>
                {doc.customContentHtml === 'extra-bold'
                  ? 'الخط: شديد التغميق (Extra-Bold)'
                  : 'الخط: عريض رسمي (Bold)'}
              </span>
            </button>
          </div>
        </div>
      )}

      {/* 2.3 Selected Active Element Floating Controls Bar */}
      {activeElement && (
        <div className="bg-blue-950/90 border-b border-blue-800/80 px-4 py-1.5 flex items-center justify-between text-xs text-blue-100 shrink-0 animate-fade-in shadow-md">
          <div className="flex items-center gap-3">
            <span className="font-bold flex items-center gap-1 text-amber-300">
              <Sliders className="w-3.5 h-3.5" />
              <span>
                العنصر المحدد: {activeElement.type === 'image' ? 'صورة' : activeElement.type === 'textbox' ? 'مربع نص' : activeElement.type === 'shape' ? 'شكل' : activeElement.type === 'icon' ? 'أيقونة' : 'رمز'}
              </span>
            </span>

            {/* Layer switcher */}
            <div className="flex items-center gap-1 bg-slate-900/70 p-0.5 rounded-lg border border-blue-700/50">
              <button
                onClick={() =>
                  handleUpdateElement(activeElement.id, {
                    layer: 'background',
                    opacity: activeElement.opacity > 0.5 ? 0.25 : activeElement.opacity,
                  })
                }
                className={`px-2 py-0.5 rounded text-[11px] font-bold transition ${
                  activeElement.layer === 'background'
                    ? 'bg-blue-600 text-white'
                    : 'text-slate-300 hover:text-white'
                }`}
              >
                خلف النص (علامة مائية)
              </button>
              <button
                onClick={() =>
                  handleUpdateElement(activeElement.id, {
                    layer: 'foreground',
                    opacity: activeElement.opacity < 0.5 ? 1 : activeElement.opacity,
                  })
                }
                className={`px-2 py-0.5 rounded text-[11px] font-bold transition ${
                  activeElement.layer === 'foreground'
                    ? 'bg-blue-600 text-white'
                    : 'text-slate-300 hover:text-white'
                }`}
              >
                أمام النص
              </button>
            </div>

            {/* Size controls */}
            <div className="flex items-center gap-1">
              <button
                onClick={() =>
                  handleUpdateElement(activeElement.id, {
                    width: Math.round(activeElement.width * 1.15),
                    height: Math.round(activeElement.height * 1.15),
                  })
                }
                className="px-2 py-0.5 rounded bg-slate-800 hover:bg-slate-700 text-white text-[11px] font-bold flex items-center gap-1"
                title="تكبير الحجم"
              >
                <Maximize2 className="w-3 h-3 text-emerald-400" />
                <span>تكبير</span>
              </button>
              <button
                onClick={() =>
                  handleUpdateElement(activeElement.id, {
                    width: Math.max(20, Math.round(activeElement.width * 0.85)),
                    height: Math.max(10, Math.round(activeElement.height * 0.85)),
                  })
                }
                className="px-2 py-0.5 rounded bg-slate-800 hover:bg-slate-700 text-white text-[11px] font-bold flex items-center gap-1"
                title="تصغير الحجم"
              >
                <Minimize2 className="w-3 h-3 text-amber-400" />
                <span>تصغير</span>
              </button>
            </div>

            {/* Opacity slider */}
            <div className="flex items-center gap-1.5 text-[11px]">
              <span>الشفافية:</span>
              <input
                type="range"
                min="0.05"
                max="1"
                step="0.05"
                value={activeElement.opacity}
                onChange={(e) =>
                  handleUpdateElement(activeElement.id, { opacity: parseFloat(e.target.value) })
                }
                className="w-20 accent-blue-400"
              />
              <span className="font-mono text-[10px]">{Math.round(activeElement.opacity * 100)}%</span>
            </div>
          </div>

          {/* Delete / Deselect */}
          <div className="flex items-center gap-1.5">
            <button
              onClick={() => handleDeleteElement(activeElement.id)}
              className="px-2 py-0.5 rounded bg-red-600/80 hover:bg-red-600 text-white text-[11px] font-bold flex items-center gap-1 transition"
              title="حذف هذا العنصر من الورقة"
            >
              <Trash2 className="w-3 h-3" />
              <span>حذف</span>
            </button>
            <button
              onClick={() => setActiveElementId(null)}
              className="p-1 rounded text-slate-300 hover:text-white"
              title="إلغاء التحديد"
            >
              <X className="w-3.5 h-3.5" />
            </button>
          </div>
        </div>
      )}

      {/* 3. Main Workspace / Canvas Area & Drawers */}
      <div className="flex-1 flex overflow-hidden bg-slate-700/60 relative">
        {/* 3.1 Quick Fields Sidebar */}
        {activeTab === 'quickFields' && (
          <div className="w-80 md:w-96 bg-slate-900 border-l border-slate-800 p-4 overflow-y-auto space-y-4 shrink-0 text-slate-200">
            <div className="border-b border-slate-800 pb-3">
              <h3 className="text-sm font-bold text-white flex items-center gap-1.5">
                <Sparkles className="w-4 h-4 text-amber-400" />
                <span>إدخال وتعديل البيانات السريعة</span>
              </h3>
              <p className="text-xs text-slate-400">
                الحقول تتطابق لحظياً مع الورقة في المعاينة ومحرر وورد
              </p>
            </div>

            {/* Document Number & Dates */}
            <div className="grid grid-cols-2 gap-3">
              <div>
                <label className="block text-xs font-bold text-slate-300 mb-1">
                  رقم المستند (NO)
                </label>
                <input
                  type="text"
                  value={doc.documentNumber}
                  onChange={(e) => handleFieldChange('documentNumber', e.target.value)}
                  className="w-full bg-slate-800 border border-slate-700 rounded-lg px-3 py-1.5 text-xs text-white font-mono text-center focus:border-blue-500 focus:outline-hidden font-bold"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-300 mb-1">
                  عدد المرفقات
                </label>
                <input
                  type="number"
                  min="0"
                  value={doc.attachmentsCount || 1}
                  onChange={(e) =>
                    handleFieldChange('attachmentsCount', parseInt(e.target.value) || 0)
                  }
                  className="w-full bg-slate-800 border border-slate-700 rounded-lg px-3 py-1.5 text-xs text-white font-mono text-center focus:border-blue-500 focus:outline-hidden font-bold"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-300 mb-1">
                  التاريخ الهجري
                </label>
                <input
                  type="text"
                  value={doc.dateHijri}
                  onChange={(e) => handleFieldChange('dateHijri', e.target.value)}
                  className="w-full bg-slate-800 border border-slate-700 rounded-lg px-3 py-1.5 text-xs text-white text-center focus:border-blue-500 focus:outline-hidden font-bold"
                />
              </div>

              <div>
                <label className="block text-xs font-bold text-slate-300 mb-1">
                  التاريخ الميلادي
                </label>
                <input
                  type="text"
                  value={doc.dateGregorian}
                  onChange={(e) => handleFieldChange('dateGregorian', e.target.value)}
                  className="w-full bg-slate-800 border border-slate-700 rounded-lg px-3 py-1.5 text-xs text-white text-center focus:border-blue-500 focus:outline-hidden font-bold"
                />
              </div>
            </div>

            {/* Amount and Tafqeet */}
            <div className="bg-slate-800/80 p-3 rounded-xl border border-slate-700 space-y-2">
              <label className="block text-xs font-bold text-amber-400">
                المبلغ المالي (ريال يمني)
              </label>
              <input
                type="number"
                placeholder="أدخل المبلغ رقماً"
                value={doc.amount || ''}
                onChange={(e) => handleAmountInputChange(e.target.value)}
                className="w-full bg-slate-900 border border-slate-600 rounded-lg px-3 py-2 text-sm text-white font-mono font-bold focus:border-amber-400 focus:outline-hidden"
              />
              <div>
                <label className="block text-[11px] text-slate-400 mb-1">
                  التفقيط كتابةً (بالريال اليمني)
                </label>
                <textarea
                  rows={2}
                  value={doc.amountWords}
                  onChange={(e) => handleFieldChange('amountWords', e.target.value)}
                  className="w-full bg-slate-900 border border-slate-600 rounded-lg p-2 text-xs text-white font-bold focus:border-blue-500 focus:outline-hidden"
                />
              </div>
            </div>

            {/* Recipient / Beneficiary */}
            <div>
              <label className="block text-xs font-bold text-slate-300 mb-1">
                {doc.type === 'ORDER'
                  ? 'للإخ / وه (المستفيد)'
                  : doc.type === 'RECEIPT'
                  ? 'الموقع أدناه (المستلم)'
                  : 'وذالك لأمر / المستفيد'}
              </label>
              <input
                type="text"
                value={doc.beneficiaryName}
                onChange={(e) => handleFieldChange('beneficiaryName', e.target.value)}
                placeholder="الاسم الثلاثي أو الجهة"
                className="w-full bg-slate-800 border border-slate-700 rounded-lg px-3 py-2 text-xs text-white font-bold focus:border-blue-500 focus:outline-hidden"
              />
            </div>

            {/* Job Title if Receipt */}
            {doc.type === 'RECEIPT' && (
              <div>
                <label className="block text-xs font-bold text-slate-300 mb-1">
                  الوظيفة (واعمل بوظيفة)
                </label>
                <input
                  type="text"
                  value={doc.jobTitle || ''}
                  onChange={(e) => handleFieldChange('jobTitle', e.target.value)}
                  placeholder="مثال: عامل نظافة ميداني / سائق قلاب"
                  className="w-full bg-slate-800 border border-slate-700 rounded-lg px-3 py-1.5 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
                />
              </div>
            )}

            {/* Month Period if Receipt */}
            {doc.type === 'RECEIPT' && (
              <div>
                <label className="block text-xs font-bold text-slate-300 mb-1">
                  مستحقات لشهر
                </label>
                <input
                  type="text"
                  value={doc.monthPeriod || ''}
                  onChange={(e) => handleFieldChange('monthPeriod', e.target.value)}
                  placeholder="مثال: شهر سبتمبر / شوال"
                  className="w-full bg-slate-800 border border-slate-700 rounded-lg px-3 py-1.5 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
                />
              </div>
            )}

            {/* Purpose / البيان */}
            <div>
              <label className="block text-xs font-bold text-slate-300 mb-1">
                البيان / وذالك مقابل
              </label>
              <textarea
                rows={3}
                value={doc.purpose}
                onChange={(e) => handleFieldChange('purpose', e.target.value)}
                placeholder="شرح سبب الصرف والبنود والبيان الرسمي"
                className="w-full bg-slate-800 border border-slate-700 rounded-lg p-2.5 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
              />
            </div>

            {/* Details for Request */}
            {doc.type === 'DISBURSEMENT_REQUEST' && (
              <div>
                <label className="block text-xs font-bold text-slate-300 mb-1">
                  التفاصيل والبنود (على الأسطر المنقطة)
                </label>
                <textarea
                  rows={4}
                  value={doc.details || doc.notes || ''}
                  onChange={(e) => handleFieldChange('details', e.target.value)}
                  placeholder="التفاصيل الإضافية وأرقام الشواهد وقطع الغيار"
                  className="w-full bg-slate-800 border border-slate-700 rounded-lg p-2.5 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
                />
              </div>
            )}

            {/* Signers Names */}
            <div className="border-t border-slate-800 pt-3 space-y-2.5">
              <div className="flex items-center justify-between">
                <p className="text-xs font-bold text-slate-300">التواقيع والمسؤولين:</p>
                <span className="text-[10px] text-amber-400 bg-amber-950/60 border border-amber-800/60 px-1.5 py-0.5 rounded">
                  اسم مدير الفرع مثبت
                </span>
              </div>

              {/* 1. مدير فرع صندوق النظافة */}
              <div>
                <label className="block text-[11px] text-slate-400 mb-0.5 font-bold">
                  مدير فرع صندوق النظافة (مثبت)
                </label>
                <input
                  type="text"
                  value={doc.managerName || 'رياض احمد محمد'}
                  onChange={(e) => handleFieldChange('managerName', e.target.value)}
                  className="w-full bg-slate-800 border border-slate-700 rounded-lg px-2.5 py-1 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
                />
              </div>

              {/* 2. المدير المالي */}
              {(doc.type === 'ORDER' || doc.type === 'RECEIPT') && (
                <div>
                  <label className="block text-[11px] text-slate-400 mb-0.5 font-bold">
                    المدير المالي للفرع
                  </label>
                  <input
                    type="text"
                    value={doc.financeManagerName || ''}
                    placeholder="اكتب اسم المدير المالي هنا..."
                    onChange={(e) => handleFieldChange('financeManagerName', e.target.value)}
                    className="w-full bg-slate-800 border border-slate-700 rounded-lg px-2.5 py-1 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
                  />
                </div>
              )}

              {/* 3. أمين الصندوق */}
              {(doc.type === 'RECEIPT' || doc.type === 'ORDER') && (
                <div>
                  <label className="block text-[11px] text-slate-400 mb-0.5 font-bold">
                    أمين الصندوق
                  </label>
                  <input
                    type="text"
                    value={doc.treasurerName || ''}
                    placeholder="اكتب اسم أمين الصندوق هنا..."
                    onChange={(e) => handleFieldChange('treasurerName', e.target.value)}
                    className="w-full bg-slate-800 border border-slate-700 rounded-lg px-2.5 py-1 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
                  />
                </div>
              )}

              {/* 4. مقدم الطلب */}
              {doc.type === 'DISBURSEMENT_REQUEST' && (
                <div>
                  <label className="block text-[11px] text-slate-400 mb-0.5 font-bold">
                    مقدم الطلب
                  </label>
                  <input
                    type="text"
                    value={doc.requesterName || ''}
                    placeholder="اكتب اسم مقدم الطلب هنا..."
                    onChange={(e) => handleFieldChange('requesterName', e.target.value)}
                    className="w-full bg-slate-800 border border-slate-700 rounded-lg px-2.5 py-1 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
                  />
                </div>
              )}
            </div>

            {/* Tags */}
            <div className="border-t border-slate-800 pt-3 space-y-2">
              <div className="flex items-center justify-between">
                <span className="text-xs font-bold text-slate-300 flex items-center gap-1">
                  <Tag className="w-3.5 h-3.5 text-blue-400" />
                  <span>وسوم وتصنيف المستند:</span>
                </span>
                {doc.tags && doc.tags.length > 0 && (
                  <span className="text-[10px] text-blue-400 font-bold">
                    {doc.tags.length} وسوم
                  </span>
                )}
              </div>

              <div className="flex flex-wrap gap-1 min-h-[30px] p-1.5 bg-slate-800 border border-slate-700 rounded-lg">
                {!doc.tags || doc.tags.length === 0 ? (
                  <span className="text-[10px] text-slate-500">لا توجد وسوم مضافة</span>
                ) : (
                  doc.tags.map((t) => (
                    <span
                      key={t}
                      className="inline-flex items-center gap-1 px-2 py-0.5 rounded text-[11px] font-bold bg-blue-900/60 text-blue-200 border border-blue-700/60"
                    >
                      <span>#{t}</span>
                      <button
                        type="button"
                        onClick={() => handleRemoveTag(t)}
                        className="text-blue-400 hover:text-red-400"
                        title="حذف الوسم"
                      >
                        <X className="w-2.5 h-2.5" />
                      </button>
                    </span>
                  ))
                )}
              </div>

              <div className="flex items-center gap-1.5">
                <input
                  type="text"
                  value={newTagInput}
                  onChange={(e) => setNewTagInput(e.target.value)}
                  onKeyDown={(e) => {
                    if (e.key === 'Enter') {
                      e.preventDefault();
                      handleAddTag();
                    }
                  }}
                  placeholder="أضف وسماً مخصصاً (كهرباء، صيانة...)"
                  className="flex-1 bg-slate-800 border border-slate-700 rounded-lg px-2.5 py-1 text-xs text-white focus:border-blue-500 focus:outline-hidden font-bold"
                />
                <button
                  type="button"
                  onClick={() => handleAddTag()}
                  disabled={!newTagInput.trim()}
                  className="p-1.5 rounded-lg bg-blue-600 hover:bg-blue-500 disabled:opacity-40 text-white"
                  title="إضافة الوسم"
                >
                  <Plus className="w-3.5 h-3.5" />
                </button>
              </div>

              <div className="flex flex-wrap gap-1 pt-1">
                {DEFAULT_TAG_SUGGESTIONS.map((sug) => {
                  const isSel = (doc.tags || []).includes(sug);
                  return (
                    <button
                      key={sug}
                      type="button"
                      onClick={() => (isSel ? handleRemoveTag(sug) : handleAddTag(sug))}
                      className={`text-[10px] px-1.5 py-0.5 rounded font-medium transition ${
                        isSel
                          ? 'bg-blue-600 text-white'
                          : 'bg-slate-800 text-slate-400 hover:text-slate-200 border border-slate-700'
                      }`}
                    >
                      {isSel ? '✓ ' : '+ '}
                      {sug}
                    </button>
                  );
                })}
              </div>
            </div>
          </div>
        )}

        {/* 3.2 Word Document Canvas Page */}
        <div
          ref={containerRef}
          className="flex-1 overflow-auto p-4 sm:p-8 flex flex-col items-center justify-start"
          onClick={(e) => {
            // If clicked on canvas backdrop, deselect active element
            if (e.target === containerRef.current) {
              setActiveElementId(null);
            }
          }}
        >
          {/* Top In-Page Editing Notice & Autosave Indicator */}
          <div className="w-full max-w-[850px] mb-3 flex items-center justify-between px-3.5 py-1.5 rounded-lg bg-slate-900/90 border border-slate-700/80 text-xs text-slate-300 shadow-sm shrink-0">
            <div className="flex items-center gap-2">
              <span className="inline-flex items-center justify-center w-5 h-5 rounded bg-blue-600 text-white font-black text-[11px]">
                W
              </span>
              <span className="font-bold text-slate-100">
                التحرير المباشر داخل صفحة المستند بنظام وورد 100%:
              </span>
              <span className="text-slate-400 hidden md:inline">
                انقر على أي سطر، رقم، اسم، أو تاريخ واكتب مباشرة على الورقة مع الحفظ الفوري وتذكر البيانات.
              </span>
            </div>

            <div className="flex items-center gap-2 text-[11px]">
              <span className="flex items-center gap-1.5 px-2 py-0.5 rounded-full bg-emerald-500/20 text-emerald-400 border border-emerald-500/30 font-mono font-bold">
                <span className="w-2 h-2 rounded-full bg-emerald-400 animate-pulse"></span>
                <span>محفوظ 100%</span>
              </span>
            </div>
          </div>

          <div className="my-auto transition-transform duration-200 shadow-2xl bg-white">
            <DocumentOfficialTemplate
              containerId="word-canvas-element"
              document={doc}
              organization={organization}
              isEditable={true}
              onFieldChange={handleFieldChange}
              showStamp={showStamp}
              scale={zoom}
              activeElementId={activeElementId}
              onSelectElement={setActiveElementId}
              onUpdateElement={handleUpdateElement}
              onDeleteElement={handleDeleteElement}
            />
          </div>
        </div>

        {/* 3.3 Offline Smart Assistant Drawer */}
        <WordOfflineAssistantPanel
          isOpen={isAssistantOpen}
          onClose={() => setIsAssistantOpen(false)}
          currentDoc={doc}
          onApplyDocUpdate={handleApplyAssistantUpdate}
        />
      </div>

      {/* 4. Word Status Footer */}
      <div className="bg-slate-900 border-t border-slate-800 px-4 py-1.5 flex items-center justify-between text-[11px] text-slate-400 select-none shrink-0">
        <div className="flex items-center gap-4">
          <span className="flex items-center gap-1">
            <span className="w-2 h-2 rounded-full bg-emerald-500 inline-block"></span>
            <span>الورقة جاهزة للطباعة والتصدير</span>
          </span>
          <span>صفحة 1 من 1</span>
          <span>الخط: Amiri / Cairo الحكومي الرسمي (عريض)</span>
          <span>العناصر المضافة: {doc.canvasElements?.length || 0}</span>
        </div>

        <div className="flex items-center gap-3">
          <button
            onClick={() => setIsAssistantOpen(!isAssistantOpen)}
            className="text-amber-400 hover:text-amber-300 flex items-center gap-1 font-bold"
          >
            <Zap className="w-3.5 h-3.5" />
            <span>المساعد الذكي الفوري (يعمل بدون نت 100%)</span>
          </button>
          <span className="text-slate-300 font-bold hidden sm:inline">
            نصيحة: يمكنك النقر على أي عنصر لتحريكه، تكبيره، أو إرساله خلف النص كعلامة مائية!
          </span>
        </div>
      </div>
    </div>
  );
};
