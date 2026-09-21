import { Document, CanvasElement } from '../types';
import { convertNumberToWords } from './numberToWords';
import { getCurrentHijriDate, getCurrentGregorianDate } from './dateHelper';

export interface AssistantResponse {
  success: boolean;
  message: string;
  updatedDoc: Document;
  actionTaken: string;
  newElement?: CanvasElement;
  suggestedPrompts?: string[];
}

/**
 * Extracts numbers from Arabic or Eastern Arabic numerals or Arabic words (ألف، مليون، مائة...)
 */
export function extractAmountFromArabicText(text: string): number | null {
  // 1. Normalize eastern arabic digits ٠-٩ to 0-9
  const normalized = text
    .replace(/[٠-٩]/g, (d) => '٠١٢٣٤٥٦٧٨٩'.indexOf(d).toString())
    .replace(/,/g, '')
    .replace(/،/g, '');

  // Look for explicit digits, e.g. "350000" or "350,000"
  const digitMatch = normalized.match(/(\d[\d\s]*\d|\d+)/);
  if (digitMatch) {
    const rawNumber = parseInt(digitMatch[0].replace(/\s+/g, ''), 10);
    // Check if followed by "ألف" or "الف" or "مليون"
    if (/ألف|الف|الاف|آلاف/.test(text) && rawNumber < 1000) {
      return rawNumber * 1000;
    }
    if (/مليون|ملايين/.test(text) && rawNumber < 1000) {
      return rawNumber * 1000000;
    }
    return rawNumber;
  }

  // Word-based amounts
  if (/مائة ألف|مية الف|مائه الف/.test(text)) return 100000;
  if (/مائتان ألف|مائتين ألف|ميتين الف/.test(text)) return 200000;
  if (/ثلاثمائة ألف|ثلاثمية الف/.test(text)) return 300000;
  if (/أربعمائة ألف|اربعمية الف/.test(text)) return 400000;
  if (/خمسمائة ألف|خمسمية الف/.test(text)) return 500000;
  if (/مليون/.test(text)) return 1000000;
  if (/نصف مليون|نص مليون/.test(text)) return 500000;

  return null;
}

/**
 * The Offline Smart Document Assistant Engine
 * Executes 100% locally and instantaneously with zero internet required.
 */
export function processOfflineAssistantCommand(
  prompt: string,
  currentDoc: Document
): AssistantResponse {
  const cleanPrompt = prompt.trim();
  if (!cleanPrompt) {
    return {
      success: false,
      message: 'يرجى كتابة طلبك أو اختيار أحد الأوامر المقترحة السريعة أدناه.',
      updatedDoc: currentDoc,
      actionTaken: 'NONE',
    };
  }

  const updated: Document = JSON.parse(JSON.stringify(currentDoc));
  const lower = cleanPrompt.toLowerCase();

  // 1. Amount Command (تعديل المبلغ والتفقيط)
  if (
    /مبلغ|المبلغ|ريال|فلوس|قيمة|صرف مبلغ|ادخل مبلغ|غير المبلغ|اجعل المبلغ|عدل المبلغ/.test(
      lower
    ) ||
    /(\d{3,})/.test(cleanPrompt)
  ) {
    const amount = extractAmountFromArabicText(cleanPrompt);
    if (amount !== null && !isNaN(amount)) {
      updated.amount = amount;
      updated.amountWords = convertNumberToWords(amount);
      return {
        success: true,
        message: `تم تعديل المبلغ بنجاح إلى (${amount.toLocaleString(
          'ar-YE'
        )} ر.ي) وتحديث التفقيط اللغوي تلقائياً: «${updated.amountWords}».`,
        updatedDoc: updated,
        actionTaken: 'UPDATE_AMOUNT',
        suggestedPrompts: [
          'اجعل الكتابة عريضة جداً',
          'أضف وسم تصنيف للمصروف',
          'ضبط الهوامش إلى ضيقة',
        ],
      };
    }
  }

  // 2. Beneficiary Command (المستفيد / الأخ / وه)
  if (/مستفيد|المستفيد|للأخ|للاخ|للأخت|للاخت|لصالح|اسم الشخص|ادفع ل|اصرف ل/.test(lower)) {
    let name = cleanPrompt
      .replace(
        /^(غير|اجعل|عدل|بدل|ضع|اكتب|اسم)?\s*(المستفيد|مستفيد|للأخ|للاخ|للأخت|للاخت|لصالح|اصرف لـ?|ادفع لـ?)\s*(هو|إلى|الى|:)?\s*/i,
        ''
      )
      .trim();
    if (name) {
      updated.beneficiaryName = name;
      return {
        success: true,
        message: `تم تحديث اسم المستفيد رسمياً إلى: «${name}».`,
        updatedDoc: updated,
        actionTaken: 'UPDATE_BENEFICIARY',
        suggestedPrompts: ['تعديل الغرض من الصرف', 'تغيير المبلغ والتفقيط'],
      };
    }
  }

  // 3. Purpose / Reason Command (الغرض / المقابل)
  if (/غرض|الغرض|مقابل|السبب|وذالك مقابل|وذلك مقابل|بشأن|شراء|قيمة/.test(lower)) {
    let purpose = cleanPrompt
      .replace(
        /^(غير|اجعل|عدل|بدل|ضع|اكتب)?\s*(الغرض|غرض|مقابل|وذالك مقابل|وذلك مقابل|السبب)\s*(هو|إلى|الى|:)?\s*/i,
        ''
      )
      .trim();
    if (purpose) {
      updated.purpose = purpose;
      return {
        success: true,
        message: `تم تحديث الغرض ومبرر الصرف إلى: «${purpose}».`,
        updatedDoc: updated,
        actionTaken: 'UPDATE_PURPOSE',
        suggestedPrompts: ['أضف تفاصيل إضافية', 'أضف وسم مناسب لهذا الغرض'],
      };
    }
  }

  // 4. Details Command (التفاصيل)
  if (/تفاصيل|التفاصيل|بيان|البيان|شرح/.test(lower)) {
    let details = cleanPrompt
      .replace(/^(غير|اجعل|عدل|أضف|اضف|اكتب)?\s*(التفاصيل|تفاصيل|البيان|شرح)\s*(هو|إلى|الى|:)?\s*/i, '')
      .trim();
    if (details) {
      updated.details = details;
      return {
        success: true,
        message: `تم إدراج التفاصيل الميدانية: «${details}».`,
        updatedDoc: updated,
        actionTaken: 'UPDATE_DETAILS',
      };
    }
  }

  // 5. Notes Command (الملاحظات)
  if (/ملاحظة|ملاحظات|الملاحظات|تنبيه|ملحوظة/.test(lower)) {
    if (/امسح|احذف|تفريغ|ازالة/.test(lower)) {
      updated.notes = '';
      return {
        success: true,
        message: 'تم تفريغ حقل الملاحظات من المستند.',
        updatedDoc: updated,
        actionTaken: 'CLEAR_NOTES',
      };
    }
    let notes = cleanPrompt
      .replace(
        /^(غير|اجعل|عدل|أضف|اضف|اكتب)?\s*(الملاحظات|ملاحظات|ملاحظة|ملحوظة)\s*(هو|إلى|الى|:)?\s*/i,
        ''
      )
      .trim();
    if (notes) {
      updated.notes = notes;
      return {
        success: true,
        message: `تم حفظ الملاحظة الإدارية: «${notes}».`,
        updatedDoc: updated,
        actionTaken: 'UPDATE_NOTES',
      };
    }
  }

  // 6. Bold Typography Command (الخط العريض والكتابة العريضة)
  if (/عريض|الخط عريض|الكتابة عريض|تغميق|بولد|bold|تكبير الخط|خط عريض/.test(lower)) {
    updated.customContentHtml = 'extra-bold';
    return {
      success: true,
      message:
        'تم تفعيل نمط الخط العريض الحكومي الرسمي لجميع نصوص المستند، الجداول، المبالغ والتفقيط بأعلى درجة وضوح وتباين!',
      updatedDoc: updated,
      actionTaken: 'SET_BOLD_TYPOGRAPHY',
      suggestedPrompts: ['هوامش ضيقة', 'إضافة ختم رسمي', 'حفظ وطباعة'],
    };
  }

  // 7. Margins Command (الهوامش)
  if (/هامش|هوامش|الهوامش|هوامش ضيقة|هوامش عريضة|هوامش عادية/.test(lower)) {
    if (/ضيق|صغير|ضيقة/.test(lower)) {
      updated.pageMargins = 'narrow';
      updated.customMarginPx = 12;
      return {
        success: true,
        message: 'تم ضبط هوامش الورقة إلى (ضيقة - 12px) لتوسيع مساحة الكتابة إلى أقصى حد.',
        updatedDoc: updated,
        actionTaken: 'SET_MARGINS_NARROW',
      };
    }
    if (/عريض|كبير|واسع|عريضة/.test(lower)) {
      updated.pageMargins = 'wide';
      updated.customMarginPx = 36;
      return {
        success: true,
        message: 'تم ضبط هوامش الورقة إلى (عريضة - 36px) لتنسيق رسمي رحب.',
        updatedDoc: updated,
        actionTaken: 'SET_MARGINS_WIDE',
      };
    }
    updated.pageMargins = 'normal';
    updated.customMarginPx = 24;
    return {
      success: true,
      message: 'تم إعادة ضبط هوامش الورقة إلى النمط القياسي المتوازن (عادي - 24px).',
      updatedDoc: updated,
      actionTaken: 'SET_MARGINS_NORMAL',
    };
  }

  // 8. Tags Command (الوسوم والتصنيفات)
  if (/وسم|وسوم|تاق|tag|تصنيف/.test(lower)) {
    if (/احذف|ازالة|مسح|حذف/.test(lower)) {
      // remove tag
      const tagMatch = cleanPrompt.match(/(?:احذف|حذف|ازالة|مسح)\s*(?:وسم|الوسم)?\s*([^\s,]+)/);
      const tagToRemove = tagMatch ? tagMatch[1].replace(/^#+/, '') : '';
      if (tagToRemove && updated.tags) {
        updated.tags = updated.tags.filter((t) => t !== tagToRemove);
        return {
          success: true,
          message: `تم حذف الوسم #${tagToRemove} من المستند بنجاح.`,
          updatedDoc: updated,
          actionTaken: 'REMOVE_TAG',
        };
      }
    }
    // Add tag
    const tagMatch = cleanPrompt.match(
      /(?:أضف|اضف|ضع|وسم|تصنيف)\s*(?:وسم|الوسم)?\s*([^\s,]+(?:\s+[^\s,]+)?)/
    );
    let newTag = tagMatch ? tagMatch[1].replace(/^(وسم|الوسم)\s*/, '').replace(/^#+/, '').trim() : '';
    if (!newTag && /كهرباء/.test(cleanPrompt)) newTag = 'كهرباء';
    if (!newTag && /صيانة/.test(cleanPrompt)) newTag = 'صيانة';
    if (!newTag && /رواتب/.test(cleanPrompt)) newTag = 'رواتب';
    if (!newTag && /نظافة/.test(cleanPrompt)) newTag = 'نظافة';
    if (!newTag && /وقود|محروقات/.test(cleanPrompt)) newTag = 'وقود ومحروقات';

    if (newTag) {
      const currentTags = updated.tags || [];
      if (!currentTags.includes(newTag)) {
        updated.tags = [...currentTags, newTag];
      }
      return {
        success: true,
        message: `تمت إضافة الوسم #${newTag} لتصنيف هذا المصروف وتسهيل أرشفته وبحثه.`,
        updatedDoc: updated,
        actionTaken: 'ADD_TAG',
      };
    }
  }

  // 9. Document Number Command (رقم المستند)
  if (/رقم|رقم المستند|الرقم|no|كود/.test(lower)) {
    const numMatch = cleanPrompt.match(/(\d+)/);
    if (numMatch) {
      updated.documentNumber = numMatch[1].padStart(4, '0');
      return {
        success: true,
        message: `تم تعيين رقم المستند المالي إلى: «${updated.documentNumber}».`,
        updatedDoc: updated,
        actionTaken: 'UPDATE_DOC_NUMBER',
      };
    }
  }

  // 10. Dates Command (التواريخ)
  if (/تاريخ|اليوم|تاريخ اليوم|الهجري|الميلادي/.test(lower)) {
    if (/اليوم|الآن|الحالي/.test(lower)) {
      updated.dateHijri = getCurrentHijriDate();
      updated.dateGregorian = getCurrentGregorianDate();
      return {
        success: true,
        message: `تم تحديث التاريخين تلقائياً إلى تاريخ اليوم: الهجري (${updated.dateHijri}) والميلادي (${updated.dateGregorian}).`,
        updatedDoc: updated,
        actionTaken: 'SET_TODAY_DATES',
      };
    }
  }

  // 11. Attachments Command (المرفقات)
  if (/مرفق|مرفقات|عدد المرفقات/.test(lower)) {
    const countMatch = cleanPrompt.match(/(\d+)/);
    if (countMatch) {
      updated.attachmentsCount = parseInt(countMatch[1], 10);
      return {
        success: true,
        message: `تم تعديل عدد المرفقات إلى (${updated.attachmentsCount}) مرفقات رسمية.`,
        updatedDoc: updated,
        actionTaken: 'UPDATE_ATTACHMENTS',
      };
    }
  }

  // 12. Add Text Box Command (إدراج مربع نص)
  if (/مربع نص|مربع|صندوق نص|textbox|اضف نص|ملاحظة إدارية/.test(lower)) {
    const newBox: CanvasElement = {
      id: `elem-${Date.now()}`,
      type: 'textbox',
      x: 80,
      y: 180,
      width: 240,
      height: 90,
      content: 'ملاحظة إدارية: يتم المراجعة والتنفيذ وفقاً للوائح الصرف المعتمدة.',
      layer: 'foreground',
      opacity: 1,
      fontSize: 13,
      isBold: true,
      color: '#0f172a',
      backgroundColor: '#f8fafc',
      borderColor: '#0284c7',
      borderWidth: 1.5,
      borderStyle: 'solid',
      borderRadius: 8,
    };
    const elements = updated.canvasElements || [];
    updated.canvasElements = [...elements, newBox];
    return {
      success: true,
      message: 'تم إدراج مربع نص جديد على الورقة! يمكنك سحبه، تغيير مكانه، وتعديل نصه بحرية.',
      updatedDoc: updated,
      newElement: newBox,
      actionTaken: 'ADD_TEXTBOX',
    };
  }

  // 13. Add Watermark / Background Image Command (علامة مائية خلف النص)
  if (/علامة مائية|خلف النص|watermark|ختم مائي/.test(lower)) {
    const watermark: CanvasElement = {
      id: `elem-${Date.now()}`,
      type: 'shape',
      shapeType: 'circle',
      x: 320,
      y: 200,
      width: 200,
      height: 200,
      content: 'صندوق النظافة - معتمد',
      layer: 'background',
      opacity: 0.15,
      fontSize: 16,
      isBold: true,
      color: '#0369a1',
      backgroundColor: 'transparent',
      borderColor: '#0284c7',
      borderWidth: 3,
      borderStyle: 'dashed',
      borderRadius: 100,
    };
    const elements = updated.canvasElements || [];
    updated.canvasElements = [...elements, watermark];
    return {
      success: true,
      message: 'تمت إضافة علامة مائية رسمية خلف النص بشفافية مريحة لا تعيق قراءة المستند.',
      updatedDoc: updated,
      newElement: watermark,
      actionTaken: 'ADD_WATERMARK',
    };
  }

  // 14. Add Currency Symbol ﷼ (رمز الريال)
  if (/رمز|ريال|رمز الريال|علامة/.test(lower)) {
    const symbolElem: CanvasElement = {
      id: `elem-${Date.now()}`,
      type: 'symbol',
      x: 550,
      y: 195,
      width: 50,
      height: 40,
      content: '﷼',
      layer: 'foreground',
      opacity: 1,
      fontSize: 26,
      isBold: true,
      color: '#15803d',
      backgroundColor: 'transparent',
      borderWidth: 0,
      borderStyle: 'none',
    };
    const elements = updated.canvasElements || [];
    updated.canvasElements = [...elements, symbolElem];
    return {
      success: true,
      message: 'تم إدراج رمز العملة الرسمية (﷼) على الورقة بنجاح.',
      updatedDoc: updated,
      newElement: symbolElem,
      actionTaken: 'ADD_SYMBOL',
    };
  }

  // 15. Status / Approval Command (الاعتماد والصرف)
  if (/اعتمد|اعتماد|موافقة|صرف|تم الصرف|مدفوع/.test(lower)) {
    if (/مدفوع|تم الصرف|تم الدفع/.test(lower)) {
      updated.status = 'PAID';
      return {
        success: true,
        message: 'تم تحديث حالة المستند رسمياً إلى: «مدفوع / تم الصرف والتسليم» ✅',
        updatedDoc: updated,
        actionTaken: 'SET_STATUS_PAID',
      };
    }
    updated.status = 'APPROVED';
    return {
      success: true,
      message: 'تم اعتماد المستند المالي رسمياً وتوثيقه كـ «معتمد» ✅',
      updatedDoc: updated,
      actionTaken: 'SET_STATUS_APPROVED',
    };
  }

  // Fallback intelligent response: If input has text without keyword, apply as purpose or notes
  if (cleanPrompt.length > 5) {
    updated.purpose = cleanPrompt;
    return {
      success: true,
      message: `تم تطبيق النص كغرض ومبرر للصرف: «${cleanPrompt}».`,
      updatedDoc: updated,
      actionTaken: 'GENERIC_UPDATE',
      suggestedPrompts: ['تعديل المبلغ', 'جعل الخط عريضاً', 'إضافة وسم'],
    };
  }

  return {
    success: false,
    message: 'لم يتم التعرف على الأمر بدقة. جرب كتابة: «عدل المبلغ إلى 200 ألف» أو «غير المستفيد إلى فلان» أو «اجعل الخط عريضاً».',
    updatedDoc: currentDoc,
    actionTaken: 'UNKNOWN',
  };
}
