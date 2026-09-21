/**
 * محول الأرقام إلى كلمات باللغة العربية (التفقيط المالي بالريال اليمني)
 * مستخرج ومطابق لنظام Masrof Manager (صندوق النظافة والتحسين)
 */

const ones = ['', 'واحد', 'اثنان', 'ثلاثة', 'أربعة', 'خمسة', 'ستة', 'سبعة', 'ثمانية', 'تسعة'];
const tens = ['', '', 'عشرون', 'ثلاثون', 'أربعون', 'خمسون', 'ستون', 'سبعون', 'ثمانون', 'تسعون'];
const teens = [
  'عشرة',
  'أحد عشر',
  'اثنا عشر',
  'ثلاثة عشر',
  'أربعة عشر',
  'خمسة عشر',
  'ستة عشر',
  'سبعة عشر',
  'ثمانية عشر',
  'تسعة عشر',
];

function groupWithScale(value: number, scale: number, singular: string, plural: string): string {
  const major = Math.floor(value / scale);
  const rest = value % scale;
  let majorText = '';

  if (major === 1) {
    majorText = singular;
  } else if (major === 2) {
    majorText = `اثنان ${singular}`;
  } else if (major >= 3 && major <= 10) {
    majorText = `${groupToWords(major)} ${plural}`;
  } else {
    majorText = `${groupToWords(major)} ${singular}`;
  }

  return rest === 0 ? majorText : `${majorText} و${groupToWords(rest)}`;
}

export function groupToWords(value: number): string {
  if (value < 10) {
    return ones[value];
  }
  if (value < 20) {
    return teens[value - 10];
  }
  if (value < 100) {
    const unit = value % 10;
    const ten = Math.floor(value / 10);
    if (unit === 0) return tens[ten];
    return `${ones[unit]} و${tens[ten]}`;
  }
  if (value < 1000) {
    const hundred = Math.floor(value / 100);
    const rest = value % 100;
    const hundredText =
      hundred === 1 ? 'مائة' : hundred === 2 ? 'مئتان' : `${ones[hundred]} مائة`;
    if (rest === 0) return hundredText;
    return `${hundredText} و${groupToWords(rest)}`;
  }
  if (value < 1_000_000) {
    return groupWithScale(value, 1_000, 'ألف', 'آلاف');
  }
  if (value < 1_000_000_000) {
    return groupWithScale(value, 1_000_000, 'مليون', 'ملايين');
  }
  return groupWithScale(value, 1_000_000_000, 'مليار', 'مليارات');
}

export function convertNumberToWords(amount: number): string {
  if (isNaN(amount) || amount === 0) {
    return 'صفر ريال يمني فقط لا غير';
  }

  const integer = Math.floor(Math.max(0, amount));
  const fraction = Math.round((amount - integer) * 100);

  const result = integer === 0 ? 'صفر' : groupToWords(integer);

  if (fraction === 0) {
    return `${result} ريال يمني فقط لا غير`;
  }
  return `${result} ريال يمني و${groupToWords(fraction)} فلس يمني فقط لا غير`;
}
