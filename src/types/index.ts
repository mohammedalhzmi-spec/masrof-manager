export type DocumentType = 'ORDER' | 'REQUEST' | 'RECEIPT';

export type DocumentStatus = 'DRAFT' | 'SUBMITTED' | 'APPROVED' | 'PAID' | 'RECEIVED' | 'CANCELLED';

export interface Document {
  id: string;
  type: DocumentType;
  documentNumber: string;
  dateHijri: string;
  dateGregorian: string;
  amount: number;
  amountWords: string;
  beneficiaryName: string;
  jobTitle?: string;
  purpose: string;
  details?: string;
  notes?: string;
  status: DocumentStatus;
  attachmentsCount: number;
  monthPeriod?: string;
  yearHijri?: string;
  yearGregorian?: string;
  requesterName?: string;
  managerName?: string;
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
  createdAt: number;
}

export interface CanvasElement {
  id: string;
  type: 'image' | 'textbox' | 'shape' | 'icon' | 'symbol';
  x: number; // px from left
  y: number; // px from top
  width: number;
  height: number;
  content: string; // text, image dataUrl, shape name, icon name, or symbol
  layer: 'background' | 'foreground'; // 'background' (خلف النص / علامة مائية) or 'foreground' (أمام النص)
  opacity: number; // 0.1 to 1.0
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
  managerName: string;
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
