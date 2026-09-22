import { Document, OrganizationProfile } from '../types';
import { getCurrentGregorianDate, getCurrentHijriDate } from './dateHelper';

export const initialOrganizationProfile: OrganizationProfile = {
  id: 1,
  ministryName: 'الجمهورية اليمنية',
  administrationName: 'صندوق النظافة والتحسين م/إب',
  branchName: 'فرع مديرية الحزم',
  address: 'محافظة إب - مديرية الحزم - الجمهورية اليمنية',
  phone: '04-456789 / 771234567',
  logoPath: '',
  managerName: 'رياض احمد محمد',
  managerTitle: 'مدير فرع صندوق النظافة',
  financeManagerName: 'أ. المدير المالي التنفيذي',
  financeManagerTitle: 'المدير المالي للفرع',
  auditorName: 'أ. المحاسب الرئيسي',
  treasurerName: 'أمين الصندوق',
  treasurerTitle: 'أمين الصندوق',
  systemFooterNote: 'طبع بواسطة النظام المالي الحكومي الموحد - صندوق النظافة والتحسين مديرية الحزم',
};

export const DEFAULT_TAG_SUGGESTIONS: string[] = [
  'كهرباء',
  'صيانة',
  'رواتب',
  'وقود ومحروقات',
  'أجور عمال',
  'مستلزمات مكتبية',
  'قطع غيار',
  'نظافة',
  'ضيافة',
  'إيجار',
  'معدات',
  'تسوية عهدة',
];

export const sampleDocuments: Document[] = [
  {
    id: 'doc-1',
    type: 'ORDER',
    documentNumber: '1448/NO-0100',
    dateHijri: getCurrentHijriDate(),
    dateGregorian: getCurrentGregorianDate(),
    amount: 150000,
    amountWords: 'مائة وخمسون ألف ريال يمني فقط لا غير',
    beneficiaryName: 'محمد احمد علي وشركاه للتجارة العامة',
    beneficiaryId: '1029384756',
    purpose: 'قيمة شراء قطع غيار وزيوت وفلاتر لآليات ومعدات النظافة الميدانية الخاصة بقطاع النظافة العامة',
    expenseItem: 'صيانة الآليات والمعدات',
    costCenter: 'قطاع النظافة والتحسين',
    fundingSource: 'إيرادات الصندوق المحلية',
    branchName: 'فرع مديرية الحزم',
    notes: 'مرفق أصل فاتورة الشراء وسند الاستلام الفني للقطع',
    status: 'APPROVED',
    attachmentsCount: 2,
    tags: ['صيانة', 'قطع غيار', 'نظافة'],
    managerName: 'رياض احمد محمد',
    financeManagerName: 'المدير المالي التنفيذي',
    treasurerName: 'أمين الصندوق',
    createdAt: Date.now() - 86400000 * 2,
    auditTrail: [
      { action: 'CREATE', username: 'accountant', timestamp: Date.now() - 86400000 * 2, details: 'إنشاء أمر الصرف' },
      { action: 'APPROVE', username: 'director', timestamp: Date.now() - 86400000, details: 'اعتماد المدير العام' }
    ]
  },
  {
    id: 'doc-2',
    type: 'DISBURSEMENT_REQUEST',
    documentNumber: '1448/NO-0101',
    dateHijri: getCurrentHijriDate(),
    dateGregorian: getCurrentGregorianDate(),
    amount: 95000,
    amountWords: 'خمسة وتسعون ألف ريال يمني فقط لا غير',
    beneficiaryName: 'إدارة الحركة وصيانة المعدات',
    purpose: 'طلب شراء أدوات ومستلزمات وقائية وكمامات لعمال النظافة الميدانيين لشهر سبتمبر',
    expenseItem: 'مستلزمات تشغيلية',
    costCenter: 'الورشة المركزية',
    fundingSource: 'الموارد العامة',
    branchName: 'فرع مديرية الحزم',
    notes: 'مرفق كشف الاحتياج المعتمد من مشرفي القطاعات الميدانية',
    status: 'SUBMITTED',
    attachmentsCount: 1,
    tags: ['نظافة', 'معدات'],
    managerName: 'رياض احمد محمد',
    createdAt: Date.now() - 86400000,
    auditTrail: [
      { action: 'CREATE', username: 'staff', timestamp: Date.now() - 86400000, details: 'إنشاء طلب الصرف' }
    ]
  },
  {
    id: 'doc-3',
    type: 'RECEIPT',
    documentNumber: '1448/NO-0102',
    dateHijri: getCurrentHijriDate(),
    dateGregorian: getCurrentGregorianDate(),
    amount: 120000,
    amountWords: 'مائة وعشرون ألف ريال يمني فقط لا غير',
    beneficiaryName: 'عبده صالح محمد القادري',
    beneficiaryId: '01020304050',
    jobTitle: 'سائق معدات وآليات نظافة ميدانية',
    purpose: 'استلام كامل مستحقات أجور صيانة ومكافأة النوبة الإضافية لرفع المخلفات',
    expenseItem: 'أجور ومرتبات',
    costCenter: 'قطاع النظافة',
    fundingSource: 'إيرادات محلية',
    branchName: 'فرع مديرية الحزم',
    notes: 'بطاقة شخصية رقم: 01020304050 - هاتف: 771234567',
    status: 'PAID',
    attachmentsCount: 1,
    tags: ['رواتب', 'أجور عمال'],
    managerName: 'رياض احمد محمد',
    financeManagerName: 'المدير المالي التنفيذي',
    treasurerName: 'أمين الصندوق',
    createdAt: Date.now() - 3600000 * 5,
    auditTrail: [
      { action: 'PAID', username: 'accountant', timestamp: Date.now() - 3600000 * 5, details: 'تم الصرف النقدي' }
    ]
  }
];
