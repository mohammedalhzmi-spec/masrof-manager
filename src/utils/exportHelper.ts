import html2canvas from 'html2canvas';
import { jsPDF } from 'jspdf';
import { Document, OrganizationProfile } from '../types';
import { YEMEN_EMBLEM_BASE64 } from './emblemConstants';

/**
 * Exports a DOM element to high-resolution PDF
 */
export async function exportToPdf(
  elementId: string,
  fileName: string,
  orientation: 'portrait' | 'landscape' = 'portrait'
): Promise<boolean> {
  const element = document.getElementById(elementId);
  if (!element) {
    console.error(`Element with id ${elementId} not found`);
    return false;
  }

  try {
    const canvas = await html2canvas(element, {
      scale: 2.5,
      useCORS: true,
      logging: false,
      backgroundColor: '#ffffff',
      windowWidth: element.scrollWidth,
    });

    const imgData = canvas.toDataURL('image/jpeg', 0.98);
    const pdf = new jsPDF({
      orientation: orientation,
      unit: 'mm',
      format: 'a4',
    });

    const pageWidth = pdf.internal.pageSize.getWidth();
    const pageHeight = pdf.internal.pageSize.getHeight();

    // Calculate aspect ratio to fit page margins
    const imgWidth = pageWidth - 10; // 5mm margin on each side
    const imgHeight = (canvas.height * imgWidth) / canvas.width;

    const posY = Math.max(5, (pageHeight - imgHeight) / 2);

    pdf.addImage(imgData, 'JPEG', 5, posY, imgWidth, imgHeight);
    pdf.save(`${fileName}.pdf`);
    return true;
  } catch (error) {
    console.error('Failed to export PDF:', error);
    return false;
  }
}

/**
 * Exports a DOM element to high-resolution PNG image
 */
export async function exportToImage(
  elementId: string,
  fileName: string
): Promise<boolean> {
  const element = document.getElementById(elementId);
  if (!element) {
    console.error(`Element with id ${elementId} not found`);
    return false;
  }

  try {
    const canvas = await html2canvas(element, {
      scale: 3, // Ultra-high 3x resolution for clear printing
      useCORS: true,
      logging: false,
      backgroundColor: '#ffffff',
    });

    const dataUrl = canvas.toDataURL('image/png');
    const link = window.document.createElement('a');
    link.href = dataUrl;
    link.download = `${fileName}.png`;
    window.document.body.appendChild(link);
    link.click();
    window.document.body.removeChild(link);
    return true;
  } catch (error) {
    console.error('Failed to export image:', error);
    return false;
  }
}

/**
 * Exports the document as a Microsoft Word compatible (.doc) file
 * with full RTL, typography, tables, and borders preserved.
 */
export function exportToWord(
  doc: Document,
  org: OrganizationProfile,
  customHtml?: string
): void {
  const isOrder = doc.type === 'ORDER';
  const isReceipt = doc.type === 'RECEIPT';
  const isRequest = doc.type === 'DISBURSEMENT_REQUEST';

  const docTitle = isOrder
    ? 'امر صرف'
    : isReceipt
    ? 'سند قبض'
    : 'طلب صرف';

  let bodyContent = '';

  if (customHtml) {
    bodyContent = customHtml;
  } else if (isOrder) {
    bodyContent = `
      <div style="font-size: 16pt; margin: 15px 0 25px 0;">
        <b>الأخ / أمين الصندوق &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; المحترم</b>
      </div>

      <table style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">
        <tr>
          <td style="font-size: 15pt; vertical-align: middle;">
            يتم صرف مبلغ وقدرة: <span style="border-bottom: 2px dotted #000; display: inline-block; width: 65%; font-weight: bold; text-align: center;">${doc.amountWords || '...................................................'}</span>
          </td>
          <td style="width: 150px; text-align: left; vertical-align: middle;">
            <div style="border: 2px solid #000; border-radius: 8px; padding: 6px 12px; font-size: 15pt; font-weight: bold; text-align: center; background-color: #f8fafc;">
              ${doc.amount ? doc.amount.toLocaleString('ar-YE') + ' ر.ي' : '................'}
            </div>
          </td>
        </tr>
      </table>

      <div style="font-size: 15pt; margin-bottom: 20px; line-height: 2;">
        للإخ / وه : <span style="border-bottom: 2px dotted #000; display: inline-block; width: 85%; font-weight: bold; padding: 0 10px;">${doc.beneficiaryName || '...........................................................................................'}</span>
      </div>

      <div style="font-size: 15pt; margin-bottom: 30px; line-height: 2;">
        وذالك مقابل / <span style="border-bottom: 2px dotted #000; display: inline-block; width: 83%; font-weight: bold; padding: 0 10px;">${doc.purpose || '...........................................................................................'}</span>
      </div>

      <div style="text-align: center; font-size: 16pt; font-weight: bold; margin: 25px 0;">
        ولكم خالص الشكر والتقدير
      </div>

      <table style="width: 100%; margin-top: 40px; border-collapse: collapse;">
        <tr>
          <td style="width: 50%; text-align: center; font-size: 14pt;">
            <b>المدير المالي للفرع</b><br/><br/>
            <b>${doc.financeManagerName || '................................'}</b><br/><br/>
            ت/ ................................
          </td>
          <td style="width: 50%; text-align: center; font-size: 14pt;">
            <b>مدير فرع صندوق النظافة</b><br/><br/>
            <b>${doc.managerName || org.managerName || 'رياض احمد محمد'}</b><br/><br/>
            ت/ ................................
          </td>
        </tr>
      </table>
    `;
  } else if (isReceipt) {
    bodyContent = `
      <div style="font-size: 14pt; line-height: 2.2; margin: 20px 0;">
        <div>انا الموقع ادناه : <span style="border-bottom: 2px dotted #000; display: inline-block; width: 80%; font-weight: bold; padding: 0 10px;">${doc.beneficiaryName || '..................................................................................'}</span></div>
        <div>واعمل بوظيفة : <span style="border-bottom: 2px dotted #000; display: inline-block; width: 80%; font-weight: bold; padding: 0 10px;">${doc.jobTitle || '..................................................................................'}</span></div>
        <div>إستلمت مبلغ وقدرة: <span style="border-bottom: 2px dotted #000; display: inline-block; width: 50%; font-weight: bold; padding: 0 10px;">${doc.amountWords || '..............................................'}</span> رقماً <span style="border-bottom: 2px dotted #000; display: inline-block; width: 25%; font-weight: bold; text-align: center;">${doc.amount ? doc.amount.toLocaleString('ar-YE') + ' ريال' : '......................'}</span></div>
        <div style="font-weight: bold;">من فرع صندوق النظافة والتحسين مديرية الحزم</div>
        <div>وذالك مقابل: <span style="border-bottom: 2px dotted #000; display: inline-block; width: 83%; font-weight: bold; padding: 0 10px;">${doc.purpose || '..................................................................................'}</span></div>
        <div>لشهر: <span style="border-bottom: 2px dotted #000; display: inline-block; width: 25%; font-weight: bold; text-align: center;">${doc.monthPeriod || '................'}</span> سنة: 144${doc.yearHijri || '6'} هـ &nbsp;&nbsp;&nbsp; 202${doc.yearGregorian || '6'} م</div>
      </div>

      <div style="text-align: center; font-size: 14.5pt; font-weight: bold; margin: 25px 0; padding: 6px; background-color: #f1f5f9; border: 1px solid #000;">
        وأقر بأنني إستلمت المبلغ كاملاً دون نقص وإبهامي شاهدة على ذالك
      </div>

      <table style="width: 100%; margin: 20px 0; border-collapse: collapse;">
        <tr>
          <td style="width: 60%; font-size: 13pt;">
            المستلم: <span style="border-bottom: 2px dotted #000; display: inline-block; width: 65%; font-weight: bold;">${doc.beneficiaryName || '..............................................'}</span>
          </td>
          <td style="width: 40%; text-align: center; font-size: 13pt;">
            بصمة المستلم:<br/>
            <div style="display: inline-block; border: 1.5px dashed #000; width: 70px; height: 80px; margin-top: 5px;"></div>
          </td>
        </tr>
      </table>

      <table style="width: 100%; margin-top: 30px; border-collapse: collapse;">
        <tr>
          <td style="width: 33.3%; text-align: center; font-size: 13pt;">
            <b>أمين الصندوق</b><br/><br/>
            <b>${doc.treasurerName || '.....................'}</b><br/><br/>
            التوقيع: .....................
          </td>
          <td style="width: 33.3%; text-align: center; font-size: 13pt;">
            <b>المدير المالي للفرع</b><br/>
            <b>${doc.financeManagerName || '.....................'}</b><br/><br/>
            التوقيع: .....................
          </td>
          <td style="width: 33.3%; text-align: center; font-size: 13pt;">
            <b>مدير فرع صندوق النظافة</b><br/>
            <b>${doc.managerName || org.managerName || 'رياض احمد محمد'}</b><br/><br/>
            التوقيع: .....................
          </td>
        </tr>
      </table>
    `;
  } else {
    // REQUEST
    bodyContent = `
      <div style="font-size: 15pt; line-height: 2; margin: 20px 0;">
        <div><b>الأخ / مدير فرع صندوق النظافة والتحسين مديرية الحزم &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; المحترم</b></div>
        <div>تكرموا مشكورين التوجية بصرف <span style="border-bottom: 2px dotted #000; display: inline-block; width: 65%; font-weight: bold; padding: 0 10px;">${doc.amount ? doc.amount.toLocaleString('ar-YE') + ' ريال (' + doc.amountWords + ')' : '....................................................................'}</span></div>
        <div>وذالك لامر : <span style="border-bottom: 2px dotted #000; display: inline-block; width: 83%; font-weight: bold; padding: 0 10px;">${doc.purpose || doc.beneficiaryName || '....................................................................'}</span></div>
        <div style="margin-top: 15px;"><b>التفاصيل:</b></div>
        <div style="border-bottom: 2px dotted #000; height: 28px; line-height: 28px; font-weight: bold; padding: 0 5px;">${doc.details || doc.notes || ''}</div>
        <div style="border-bottom: 2px dotted #000; height: 28px;"></div>
        <div style="border-bottom: 2px dotted #000; height: 28px;"></div>
        <div style="border-bottom: 2px dotted #000; height: 28px;"></div>
        <div style="border-bottom: 2px dotted #000; height: 28px;"></div>
        <div style="border-bottom: 2px dotted #000; height: 28px;"></div>
      </div>

      <table style="width: 100%; margin-top: 40px; border-collapse: collapse;">
        <tr>
          <td style="width: 50%;"></td>
          <td style="width: 50%; text-align: center; font-size: 14pt;">
            <b>مقدم الطلب</b><br/><br/>
            الاسم : <span style="border-bottom: 2px dotted #000; display: inline-block; width: 60%; font-weight: bold;">${doc.requesterName || '................................'}</span><br/><br/>
            توقيع : ........................................
          </td>
        </tr>
      </table>
    `;
  }

  const wordHtml = `
    <html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'>
      <head>
        <meta charset='utf-8'>
        <title>${docTitle} - رقم ${doc.documentNumber}</title>
        <!--[if gte mso 9]>
        <xml>
          <w:WordDocument>
            <w:View>Print</w:View>
            <w:Zoom>100</w:Zoom>
            <w:DoNotOptimizeForBrowser/>
          </w:WordDocument>
        </xml>
        <![endif]-->
        <style>
          @page {
            size: ${isOrder ? 'landscape' : 'portrait'};
            margin: 1.5cm 1.5cm 1.5cm 1.5cm;
          }
          body {
            font-family: 'Amiri', 'Traditional Arabic', 'Cairo', Arial, sans-serif;
            direction: rtl;
            text-align: right;
            color: #000000;
          }
          .official-frame {
            border: 3px double #000000;
            padding: 20px;
            background-color: #ffffff;
          }
          .title-box {
            border: 2px solid #000000;
            padding: 4px 18px;
            font-size: 18pt;
            font-weight: bold;
            display: inline-block;
          }
          .footer-note {
            font-size: 9pt;
            color: #475569;
            margin-top: 30px;
            border-top: 1px solid #ccc;
            padding-top: 5px;
            text-align: center;
          }
        </style>
      </head>
      <body>
        <div class="official-frame">
          <!-- Header Table -->
          <table style="width: 100%; border-bottom: 2px solid #000; padding-bottom: 10px; margin-bottom: 15px;">
            <tr>
              <td style="width: 35%; text-align: right; font-size: 12pt; line-height: 1.4;">
                <b>الجمهورية اليمنية</b><br/>
                <b>${org.ministryName || 'وزارة الإدارة والتنمية المحلية والريفية'}</b><br/>
                <b>${org.administrationName || 'صندوق النظافة والتحسين م/إب'}</b><br/>
                <b>${org.branchName || 'فرع مديرية الحزم'}</b>
              </td>
              <td style="width: 30%; text-align: center; vertical-align: middle;">
                <div style="font-size: 14pt; font-weight: bold; margin-bottom: 5px;">بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ</div>
                <img src="${YEMEN_EMBLEM_BASE64}" alt="شعار الجمهورية اليمنية" style="width: 100px; height: auto; max-height: 58px; margin: 3px auto; display: block;" />
              </td>
              <td style="width: 35%; text-align: left; font-size: 12pt; line-height: 1.5; direction: rtl;">
                الرقم : <b>${doc.documentNumber || '............'}</b><br/>
                التاريخ : <b>${doc.dateHijri || '   /   / 144هـ'}</b><br/>
                الموافق : <b>${doc.dateGregorian || '   /   / 202م'}</b><br/>
                المرفقات : <b>( ${doc.attachmentsCount || 1} )</b>
              </td>
            </tr>
          </table>

          <!-- Title Row & Document Number Row under separator line: اسم المستند في المنتصف وترقيم المستند في اليسار تحت المرفقات -->
          <table style="width: 100%; margin: 12px 0; border-collapse: collapse;">
            <tr>
              <td style="width: 35%;"></td>
              <td style="width: 30%; text-align: center;">
                <div class="title-box" style="display: inline-block; padding: 6px 25px; border: 2px solid #000; font-weight: bold; font-size: 16pt;">
                  <span>${docTitle}</span>
                </div>
              </td>
              <td style="width: 35%; text-align: left; vertical-align: middle;">
                <span style="font-family: Arial, sans-serif; font-size: 14pt; font-weight: bold; color: #b91c1c; direction: ltr; display: inline-block;">
                  NO: ${doc.documentNumber || '0001'}
                </span>
              </td>
            </tr>
          </table>

          <!-- Main Document Content -->
          ${bodyContent}

          ${doc.tags && doc.tags.length > 0 ? `
          <div style="margin-top: 15px; font-size: 10pt; color: #475569; text-align: right; border-top: 1px dashed #cbd5e1; padding-top: 6px;">
            <b>وسوم وتصنيف المصروف:</b> &nbsp; ${doc.tags.map(t => '<span style="display: inline-block; background-color: #f1f5f9; padding: 2px 8px; border-radius: 4px; border: 1px solid #cbd5e1; margin-left: 5px; font-weight: bold; color: #1e293b;">#' + t + '</span>').join('')}
          </div>
          ` : ''}

          <!-- System Footer - نص أسفل المستند تحت توقيعات الإداريين -->
          <div class="footer-note" style="text-align: center; font-size: 11pt; font-weight: bold; color: #1e293b; margin-top: 25px; border-top: 1px solid #333; padding-top: 6px;">
            ${org.systemFooterNote || 'طبع بواسطة نظام مالية فرع صندوق النظافةوالتحسين مديرية الحزم'}
          </div>
        </div>
      </body>
    </html>
  `;

  const blob = new Blob(['\ufeff' + wordHtml], {
    type: 'application/msword;charset=utf-8',
  });
  const url = URL.createObjectURL(blob);
  const a = window.document.createElement('a');
  a.href = url;
  a.download = `${docTitle}_رقم_${doc.documentNumber}.doc`;
  window.document.body.appendChild(a);
  a.click();
  window.document.body.removeChild(a);
  URL.revokeObjectURL(url);
}

/**
 * Native Web Share with fallback to clipboard
 */
export async function shareDocument(
  elementId: string,
  title: string
): Promise<{ success: boolean; method: 'native' | 'clipboard' | 'failed' }> {
  const element = document.getElementById(elementId);
  if (!element) return { success: false, method: 'failed' };

  try {
    const canvas = await html2canvas(element, { scale: 2, backgroundColor: '#ffffff' });

    if (navigator.share && navigator.canShare) {
      const blob = await new Promise<Blob | null>((resolve) =>
        canvas.toBlob(resolve, 'image/png')
      );

      if (blob) {
        const file = new File([blob], `${title}.png`, { type: 'image/png' });
        if (navigator.canShare({ files: [file] })) {
          await navigator.share({
            title: title,
            text: `وثيقة رسمية: ${title}`,
            files: [file],
          });
          return { success: true, method: 'native' };
        }
      }
    }

    // Fallback: Copy image or link to clipboard
    const dataUrl = canvas.toDataURL('image/png');
    await navigator.clipboard.writeText(dataUrl);
    return { success: true, method: 'clipboard' };
  } catch (err) {
    console.warn('Sharing failed or cancelled', err);
    return { success: false, method: 'failed' };
  }
}
