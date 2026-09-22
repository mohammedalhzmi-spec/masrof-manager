export type DocumentType = 
  | 'ORDER'               // أمر صرف
  | 'DISBURSEMENT_REQUEST'// طلب صرف
  | 'RECEIPT_PAPER'       // ورقة استلام
  | 'FINANCIAL_MEMO'      // مذكرة مالية
  | 'PURCHASE_ORDER'      // طلب شراء
  | 'SUPPLY_PERMIT'       // إذن توريد أو استلام
  | 'RECEIPT_MINUTES'     // محضر استلام
  | 'FINANCIAL_CLAIM'     // مطالبة مالية
  | 'CUSTODY_SETTLEMENT'  // تسوية عهدة
  | 'ADVANCE_PERMIT'      // إذن سلفة
  | 'EXPENSE_STATEMENT'   // كشف مصروفات
  | 'OFFICIAL_FINANCIAL_LETTER' // خطاب رسمي مالي
  | 'BOOK'                // دفتر صرف
  | 'RECEIPT';            // سند قبض

export type DocumentStatus = 
  | 'DRAFT'               // مسودة
  | 'SUBMITTED'           // قيد المراجعة
  | 'APPROVED_FINANCE'    // اعتماد المدير المالي
  | 'APPROVED_BRANCH'     // اعتماد مدير الفرع (رياض أحمد محمد)
  | 'APPROVED'            // معتمد نهائياً
  | 'PAID'                // تم الصرف
  | 'RECEIVED'            // تم الاستلام
  | 'CANCELLED';          // ملغي

export interface Document {
  id: string;
  type: DocumentType;
  documentNumber: string; // مثل: 1448/NO-0100
  dateHijri: string;
  dateGregorian: string;
  amount: number;
  amountWords: string;
  beneficiaryName: string;
  beneficiaryId?: string; // رقم الهوية أو الحساب
  jobTitle?: string;
  purpose: string;        // سبب الصرف أو الاستلام
  expenseItem?: string;   // بند المصروف
  costCenter?: string;    // مركز التكلفة
  fundingSource?: string; // مصدر التمويل
  branchName?: string;    // الفرع / الإدارة
  details?: string;
  notes?: string;
  status: DocumentStatus;
  attachmentsCount: number;
  monthPeriod?: string;
  yearHijri?: string;
  yearGregorian?: string;
  requesterName?: string;
  managerName?: string;         // رياضي أحمد محمد
  financeManagerName?: string;
  treasurerName?: string;
  tags?: string[];
  customDetailsLines?: string[];
  customContentHtml?: string;
  canvasElements?: CanvasElement[];
  pageMargins?: 'normal' | 'narrow' | 'wide' | 'custom';
  customMarginPx?: number;
  fontFamily?: string;
  borderStyle?: 'classic' | 'double' | 'bold' | 'decorative' | 'gold' | 'islamic' | 'simple';
  isArchived?: boolean;
  createdAt: number;
  updatedAt?: number;
  auditTrail?: {
    action: string;
    username: string;
    timestamp: number;
    details: string;
  }[];
}

export interface CanvasElement {
  id: string;
  type: 'image' | 'textbox' | 'shape' | 'icon' | 'symbol';
  x: number; // px from left
  y: number; // px from top
  width: number;
  height: number;
  content: string;
  layer: 'background' | 'foreground';
  opacity: number;
  fontSize?: number;
  isBold?: boolean;
  color?: string;
  backgroundColor?: string;
  borderColor?: string;
  borderWidth?: number;
  borderStyle?: 'solid' | 'dashed' | 'dotted' | 'none';
  borderRadius?: number;
  rotation?: number;
  shapeType?: 'rectangle' | 'rounded' | 'divider' | 'banner' | 'circle' | 'callout';
  iconName?: string;
}

export interface OrganizationProfile {
  id: number;
  ministryName: string;
  administrationName: string;
  branchName: string;
  address: string;
  phone: string;
  logoPath?: string;
  managerName: string;         // رياض أحمد محمد
  managerTitle?: string;
  financeManagerName: string;
  financeManagerTitle?: string;
  auditorName: string;
  treasurerName: string;
  treasurerTitle?: string;
  signatureManagerPath?: string;
  signatureFinancePath?: string;
  signatureTreasurerPath?: string;
  systemFooterNote?: string;
}

export interface AuditLog {
  id: string;
  timestamp: number;
  action: string;
  details: string;
  username: string;
}

export interface GitRepoStatus {
  branch: string;
  latestCommit: string;
  isClean: boolean;
  repoUrl: string;
}
