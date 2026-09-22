/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import React, { useState, useEffect } from 'react';
import { Header } from './components/Header';
import { DashboardStats } from './components/DashboardStats';
import { DocumentList } from './components/DocumentList';
import { DocumentModal } from './components/DocumentModal';
import { OfficialPrintView } from './components/OfficialPrintView';
import { WordDocumentEditorModal } from './components/WordDocumentEditorModal';
import { GitSyncModal } from './components/GitSyncModal';
import { SettingsModal } from './components/SettingsModal';
import { BackupModal } from './components/BackupModal';
import { SplashLoginScreen } from './components/SplashLoginScreen';
import { Document, DocumentType, OrganizationProfile } from './types';
import { sampleDocuments, initialOrganizationProfile } from './utils/initialData';
import { GitBranch, ShieldCheck, Sparkles, Building2, CheckCircle2, Smartphone, Download } from 'lucide-react';
import { fbSaveDocument } from './utils/firebaseService';
import { getCurrentLoggedInUser } from './utils/governmentAuthService';

export default function App() {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(() => {
    return localStorage.getItem('masrof_logged_in') === 'true';
  });
  // Persistence state
  const [documents, setDocuments] = useState<Document[]>(() => {
    try {
      const saved = localStorage.getItem('masrof_documents');
      if (saved) {
        const parsed: Document[] = JSON.parse(saved);
        return parsed.map((d) => ({
          ...d,
          managerName: d.managerName || 'رياض احمد محمد',
          financeManagerName: d.financeManagerName === 'محمد الحزمي' ? '' : (d.financeManagerName || ''),
          tags: d.tags && d.tags.length > 0 ? d.tags : (
            d.id === 'doc-1' ? ['صيانة', 'قطع غيار', 'نظافة'] :
            d.id === 'doc-2' ? ['نظافة', 'معدات'] :
            d.id === 'doc-3' ? ['رواتب', 'أجور عمال'] :
            d.id === 'doc-4' ? ['كهرباء', 'صيانة'] : undefined
          ),
        }));
      }
    } catch (e) {
      console.error('Failed to load documents from localStorage', e);
    }
    return sampleDocuments;
  });

  const [organization, setOrganization] = useState<OrganizationProfile>(() => {
    try {
      const saved = localStorage.getItem('masrof_organization');
      if (saved) {
        const parsed: OrganizationProfile = JSON.parse(saved);
        return {
          ...parsed,
          ministryName: 'وزارة الإدارة والتنمية المحلية والريفية',
          administrationName: 'صندوق النظافة والتحسين م/إب',
          branchName: 'فرع مديرية الحزم',
          managerName: 'رياض احمد محمد',
          financeManagerName: parsed.financeManagerName === 'محمد الحزمي' ? '' : (parsed.financeManagerName || ''),
          systemFooterNote: 'طبع بواسطة نظام مالية فرع صندوق النظافةوالتحسين مديرية الحزم',
        };
      }
    } catch (e) {
      console.error('Failed to load organization from localStorage', e);
    }
    return initialOrganizationProfile;
  });

  // Selected document IDs for batch printing/actions
  const [selectedIds, setSelectedIds] = useState<Set<string>>(new Set());
  const [selectedTag, setSelectedTag] = useState<string | null>(null);

  // Modals state
  const [docModalOpen, setDocModalOpen] = useState(false);
  const [editingDocument, setEditingDocument] = useState<Document | null>(null);
  const [wordEditorDocument, setWordEditorDocument] = useState<Document | null>(null);
  const [defaultCreateType, setDefaultCreateType] = useState<DocumentType>('ORDER');

  const [printState, setPrintState] = useState<{
    isPrinting: boolean;
    documents: Document[];
  }>({
    isPrinting: false,
    documents: [],
  });

  const [isGitSyncOpen, setIsGitSyncOpen] = useState(false);
  const [isSettingsOpen, setIsSettingsOpen] = useState(false);
  const [isBackupOpen, setIsBackupOpen] = useState(false);
  const [showDeveloperNotice, setShowDeveloperNotice] = useState(true);

  // Sync to localStorage
  useEffect(() => {
    try {
      localStorage.setItem('masrof_documents', JSON.stringify(documents));
    } catch (e) {
      console.error('Failed to save documents to localStorage', e);
    }
  }, [documents]);

  useEffect(() => {
    try {
      localStorage.setItem('masrof_organization', JSON.stringify(organization));
    } catch (e) {
      console.error('Failed to save organization to localStorage', e);
    }
  }, [organization]);

  // Generate next automatic document number
  const getNextDocumentNumber = (type: DocumentType) => {
    const docsOfType = documents.filter((d) => d.type === type);
    if (docsOfType.length === 0) return '0001';
    const highestNum = docsOfType.reduce((max, doc) => {
      const parsed = parseInt(doc.documentNumber.replace(/\D/g, ''), 10);
      return !isNaN(parsed) && parsed > max ? parsed : max;
    }, 0);
    return String(highestNum + 1).padStart(4, '0');
  };

  // Handlers for creating/editing documents
  const handleOpenCreate = (type: DocumentType) => {
    setEditingDocument(null);
    setDefaultCreateType(type);
    setDocModalOpen(true);
  };

  const handleOpenEdit = (doc: Document) => {
    setEditingDocument(doc);
    setDefaultCreateType(doc.type);
    setDocModalOpen(true);
  };

  const handleSaveDocument = (docToSave: Document, andPrint = false) => {
    if (editingDocument) {
      setDocuments((prev) =>
        prev.map((d) => (d.id === docToSave.id ? docToSave : d))
      );
    } else {
      setDocuments((prev) => [docToSave, ...prev]);
    }
    setDocModalOpen(false);

    // Sync to Firebase & FCM notification to Director alhzmim57@gmail.com
    try {
      const user = getCurrentLoggedInUser();
      fbSaveDocument({
        id: docToSave.id,
        serialNumber: docToSave.documentNumber,
        type: docToSave.type === 'ORDER' ? 'أمر صرف' : docToSave.type === 'RECEIPT' ? 'سند قبض' : 'طلب مالي',
        title: docToSave.purpose || 'مستند مالي',
        amount: docToSave.amount,
        beneficiary: docToSave.beneficiaryName || 'مستفيد',
        createdBy: user?.username || 'admin',
        dayName: new Date(docToSave.createdAt || Date.now()).toLocaleDateString('ar-SA', { weekday: 'long' }),
        dateString: docToSave.dateGregorian || new Date(docToSave.createdAt || Date.now()).toLocaleDateString('ar-SA'),
        status: docToSave.status
      });
    } catch (e) {
      console.error('Failed to sync document to Firebase:', e);
    }

    if (andPrint) {
      setPrintState({
        isPrinting: true,
        documents: [docToSave],
      });
    }
  };

  const handleDeleteDocument = (id: string) => {
    if (window.confirm('هل أنت متأكد من رغبتك في حذف هذا المستند المالي؟')) {
      setDocuments((prev) => prev.filter((d) => d.id !== id));
      setSelectedIds((prev) => {
        const next = new Set(prev);
        next.delete(id);
        return next;
      });
    }
  };

  const handleToggleArchive = (id: string) => {
    setDocuments((prev) =>
      prev.map((d) => (d.id === id ? { ...d, isArchived: !d.isArchived } : d))
    );
  };

  const handleArchiveOldDocuments = () => {
    const oneYearAgo = Date.now() - 365 * 86400000;
    let count = 0;
    setDocuments((prev) =>
      prev.map((d) => {
        if (!d.isArchived && d.createdAt < oneYearAgo) {
          count++;
          return { ...d, isArchived: true };
        }
        return d;
      })
    );
    alert(`تمت أرشفة ${count} مستنداً مضى عليها أكثر من عام بنجاح ونقلها إلى قسم الأرشيف.`);
  };

  // Selection handlers
  const handleToggleSelect = (id: string) => {
    setSelectedIds((prev) => {
      const next = new Set(prev);
      if (next.has(id)) next.delete(id);
      else next.add(id);
      return next;
    });
  };

  const handleSelectAll = () => {
    setSelectedIds(new Set(documents.map((d) => d.id)));
  };

  const handleClearSelection = () => {
    setSelectedIds(new Set());
  };

  // Print handlers
  const handlePrintSingle = (doc: Document) => {
    setPrintState({
      isPrinting: true,
      documents: [doc],
    });
  };

  const handlePrintSelected = () => {
    const selectedDocs = documents.filter((d) => selectedIds.has(d.id));
    if (selectedDocs.length === 0) return;
    setPrintState({
      isPrinting: true,
      documents: selectedDocs,
    });
  };

  // Restore backup handler
  const handleRestore = (newDocs: Document[], newOrg: OrganizationProfile) => {
    setDocuments(newDocs);
    setOrganization(newOrg);
    setSelectedIds(new Set());
  };

  // If not logged in, show the animated splash screen
  if (!isLoggedIn) {
    return (
      <SplashLoginScreen
        onLoginSuccess={() => {
          setIsLoggedIn(true);
          localStorage.setItem('masrof_logged_in', 'true');
        }}
      />
    );
  }

  // If currently in print preview view, render the official print page directly
  if (printState.isPrinting) {
    return (
      <OfficialPrintView
        documents={printState.documents}
        organization={organization}
        onBack={() => setPrintState({ isPrinting: false, documents: [] })}
        onUpdateDocument={(updated) =>
          setDocuments((prev) => prev.map((d) => (d.id === updated.id ? updated : d)))
        }
      />
    );
  }

  return (
    <div className="min-h-screen bg-slate-100/90 text-slate-900 flex flex-col font-['Cairo',sans-serif]">
      {/* Official Header */}
      <Header
        onNewDocument={handleOpenCreate}
        onOpenGitSync={() => setIsGitSyncOpen(true)}
        onOpenSettings={() => setIsSettingsOpen(true)}
        onOpenBackup={() => setIsBackupOpen(true)}
        selectedCount={selectedIds.size}
        onPrintSelected={handlePrintSelected}
      />

      {/* Main Container */}
      <main className="flex-1 max-w-7xl w-full mx-auto px-4 sm:px-6 lg:px-8 py-6">
        {/* Android APK Download Banner */}
        <div className="mb-6 p-4 rounded-2xl bg-gradient-to-r from-emerald-600 to-teal-700 text-white shadow-xl flex flex-col sm:flex-row items-center justify-between gap-4 border border-emerald-500/40">
          <div className="flex items-center gap-3.5">
            <div className="w-12 h-12 rounded-xl bg-white/15 border border-white/20 flex items-center justify-center text-white flex-shrink-0 shadow-inner">
              <Smartphone className="w-6 h-6 animate-pulse" />
            </div>
            <div>
              <div className="flex items-center gap-2">
                <span className="px-2 py-0.5 rounded bg-white/20 text-[10px] font-bold tracking-wide uppercase">Android APK</span>
                <span className="text-xs font-bold text-emerald-100">تم اكتمال بناء تطبيق الأندرويد بنجاح</span>
              </div>
              <h2 className="text-sm font-black text-white mt-0.5">ملف التطبيق بصيغة APK (داخل ملف مضغوط ZIP) جاهز للتحميل الفوري</h2>
              <p className="text-xs text-emerald-100/90 mt-0.5">
                يمكنك تحميل حزمة التطبيق المضغوطة لتثبيتها مباشرة على هاتفك الذكي (Android).
              </p>
            </div>
          </div>
          <div className="flex items-center gap-2 flex-shrink-0 w-full sm:w-auto justify-end">
            <a
              href="/masrof-manager-android-apk.zip"
              download="masrof-manager-android-apk.zip"
              className="w-full sm:w-auto px-5 py-2.5 rounded-xl bg-white text-emerald-800 hover:bg-emerald-50 text-xs font-black transition shadow-lg flex items-center justify-center gap-2 transform hover:scale-105"
            >
              <Download className="w-4 h-4 text-emerald-700 animate-bounce" />
              <span>تحميل ملف التطبيق APK (مضغوط)</span>
            </a>
          </div>
        </div>

        {/* Developer & System Rights Banner (matching the original Android dashboard notice) */}
        {showDeveloperNotice && (
          <div className="mb-6 p-4 rounded-2xl bg-gradient-to-r from-slate-900 to-slate-800 text-white shadow-lg flex items-center justify-between gap-4 border border-slate-700 animate-in fade-in duration-300">
            <div className="flex items-center gap-3">
              <div className="w-10 h-10 rounded-xl bg-emerald-500/20 border border-emerald-500/30 flex items-center justify-center text-emerald-400 flex-shrink-0">
                <Building2 className="w-5 h-5" />
              </div>
              <div>
                <p className="text-xs font-bold text-slate-200">
                  نظام مالية صندوق النظافة والتحسين • تطبيق احترافي متكامل
                </p>
                <p className="text-[11px] text-slate-400">
                  هذا النظام والبرمجية من تطوير المطور{' '}
                  <span className="text-emerald-400 font-bold">محمد الحزمي</span> • مستودع GitHub متصل ومتاح للتعديل والرفع المباشر
                </p>
              </div>
            </div>

            <div className="flex items-center gap-2 flex-shrink-0">
              <button
                onClick={() => setIsGitSyncOpen(true)}
                className="px-3 py-1.5 rounded-lg bg-emerald-600 hover:bg-emerald-500 text-white text-xs font-bold transition flex items-center gap-1.5 shadow-xs"
              >
                <GitBranch className="w-3.5 h-3.5" />
                <span>مركز مزامنة المستودع</span>
              </button>
              <button
                onClick={() => setShowDeveloperNotice(false)}
                className="text-xs text-slate-400 hover:text-white px-2 py-1 transition"
              >
                إخفاء
              </button>
            </div>
          </div>
        )}

        {/* Dashboard Financial Statistics */}
        <DashboardStats documents={documents} />

        {/* Documents Management & List */}
        <DocumentList
          documents={documents}
          selectedIds={selectedIds}
          selectedTag={selectedTag}
          onSelectTag={setSelectedTag}
          onToggleSelect={handleToggleSelect}
          onSelectAll={handleSelectAll}
          onClearSelection={handleClearSelection}
          onPrintDocument={handlePrintSingle}
          onEditDocument={handleOpenEdit}
          onOpenWordEditor={(doc) => setWordEditorDocument(doc)}
          onDeleteDocument={handleDeleteDocument}
          onNewDocument={handleOpenCreate}
          onToggleArchive={handleToggleArchive}
          onArchiveOldDocuments={handleArchiveOldDocuments}
        />
      </main>

      {/* Footer */}
      <footer className="bg-white border-t border-slate-200 py-4 mt-12 text-center text-xs text-slate-500">
        <div className="max-w-7xl mx-auto px-4 flex flex-col sm:flex-row items-center justify-between gap-2">
          <span>
            {organization.administrationName} • {organization.branchName}
          </span>
          <span className="font-semibold text-slate-700">
            برمجة وتطوير المطور محمد الحزمي 2026 • جميع الحقوق محفوظة
          </span>
        </div>
      </footer>

      {/* Standard Document Creation / Quick Edit Modal */}
      <DocumentModal
        isOpen={docModalOpen}
        onClose={() => setDocModalOpen(false)}
        onSave={handleSaveDocument}
        onOpenWordEditor={(doc) => setWordEditorDocument(doc)}
        initialDocument={editingDocument}
        defaultType={defaultCreateType}
        nextNumber={getNextDocumentNumber(defaultCreateType)}
        organization={organization}
      />

      {/* Advanced Word-Like Document Editor & Formatter Modal */}
      {wordEditorDocument && (
        <WordDocumentEditorModal
          isOpen={true}
          document={wordEditorDocument}
          organization={organization}
          onClose={() => setWordEditorDocument(null)}
          onSave={(updated, andPrint) => {
            setDocuments((prev) =>
              prev.some((d) => d.id === updated.id)
                ? prev.map((d) => (d.id === updated.id ? updated : d))
                : [updated, ...prev]
            );
            if (andPrint) {
              setWordEditorDocument(null);
              setPrintState({
                isPrinting: true,
                documents: [updated],
              });
            }
          }}
        />
      )}

      {/* GitHub Sync & Push Modal */}
      <GitSyncModal
        isOpen={isGitSyncOpen}
        onClose={() => setIsGitSyncOpen(false)}
      />

      {/* Organization Settings & Signatures Modal */}
      <SettingsModal
        isOpen={isSettingsOpen}
        onClose={() => setIsSettingsOpen(false)}
        organization={organization}
        onSave={(newOrg) => setOrganization(newOrg)}
      />

      {/* Backup & Restore Database Modal */}
      <BackupModal
        isOpen={isBackupOpen}
        onClose={() => setIsBackupOpen(false)}
        documents={documents}
        organization={organization}
        onRestore={handleRestore}
      />
    </div>
  );
}
