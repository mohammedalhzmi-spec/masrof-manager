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
 * Normalizes Arabic text for uniform, error-free matching
 * Handles: Hamza variations (أ, إ, آ -> ا), Taa Marbuta (ة -> ه), Yaa/Alef Maksura (ى, ئ -> ي),
 * strips Tatweel (ـ) and Harakat/Tashkeel.
 */
export function normalizeArabic(text: string): string {
  return text
    .toLowerCase()
    .replace(/[أإآ]/g, 'ا')
    .replace(/ة/g, 'ه')
    .replace(/[ىئ]/g, 'ي')
    .replace(/ؤ/g, 'و')
    .replace(/[ـ\u0640]/g, '')
    .replace(/[\u064B-\u065F\u0670]/g, '')
    .trim();
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
  const norm = normalizeArabic(cleanPrompt);
  const lower = cleanPrompt.toLowerCase();

  // 1. Bold Typography Command (الخط العريض والكتابة العريضة)
  if (
    /عريض|عريضه|الخط عريض|الكتابه عريض|تغميق|تغليظ|بولد|bold|تكبير الخط|خط عريض/.test(norm) ||
    /عريض/.test(lower)
  ) {
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

  // 2. Amount Command (تعديل المبلغ والتفقيط)
  if (
    /مبلغ|المبلغ|ريال|فلوس|قيمه|صرف مبلغ|ادخل مبلغ|غير المبلغ|اجعل المبلغ|عدل المبلغ/.test(norm) ||
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

  // 3. Template Switcher (تغيير نوع القالب)
  if (/قالب|نموذج|نوع المستند|تحويل الى|حول الى/.test(norm) || /امر صرف|ورقه استلام|ورقه تقديم طلب/.test(norm)) {
    if (/طلب|تقديم طلب|ورقه طلب/.test(norm)) {
      updated.type = 'DISBURSEMENT_REQUEST';
      return {
        success: true,
        message: 'تم تحويل قالب المستند إلى «ورقة تقديم طلب (طولي)» مع التنسيقات والأسطر الرسمية.',
        updatedDoc: updated,
        actionTaken: 'SET_TEMPLATE_REQUEST',
      };
    }
    if (/استلام|سند استلام|ورقه استلام/.test(norm)) {
      updated.type = 'RECEIPT';
      return {
        success: true,
        message: 'تم تحويل قالب المستند إلى «ورقة إستلام (طولي)» مع حقول الوظيفة ومستحقات الشهر.',
        updatedDoc: updated,
        actionTaken: 'SET_TEMPLATE_RECEIPT',
      };
    }
    if (/صرف|امر صرف/.test(norm)) {
      updated.type = 'ORDER';
      return {
        success: true,
        message: 'تم تحويل قالب المستند إلى «أمر صرف (عرضي)» وفقاً للنموذج المعتمد رسمياً.',
        updatedDoc: updated,
        actionTaken: 'SET_TEMPLATE_ORDER',
      };
    }
  }

  // 4. Margins Command (الهوامش)
  if (/هامش|هوامش|الهوامش/.test(norm)) {
    if (/ضيق|صغير|ضيقه/.test(norm)) {
      updated.pageMargins = 'narrow';
      updated.customMarginPx = 12;
      return {
        success: true,
        message: 'تم ضبط هوامش الورقة إلى (ضيقة - 12px) لتوسيع مساحة الكتابة إلى أقصى حد.',
        updatedDoc: updated,
        actionTaken: 'SET_MARGINS_NARROW',
      };
    }
    if (/عريض|كبير|واسع|عريضه/.test(norm)) {
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

  // 5. Border Style Command (إطار المستند)
  if (/اطار|برواز|حدود المستند|حاشيه/.test(norm)) {
    if (/ذهبي/.test(norm)) {
      updated.borderStyle = 'gold';
      return {
        success: true,
        message: 'تم تطبيق الإطار الذهبي المعتمد على المستند.',
        updatedDoc: updated,
        actionTaken: 'SET_BORDER_GOLD',
      };
    }
    if (/مزدوج|دبل/.test(norm)) {
      updated.borderStyle = 'double';
      return {
        success: true,
        message: 'تم تطبيق الإطار المزدوج على المستند.',
        updatedDoc: updated,
        actionTaken: 'SET_BORDER_DOUBLE',
      };
    }
    if (/اسلامي|زخرفي/.test(norm)) {
      updated.borderStyle = 'islamic';
      return {
        success: true,
        message: 'تم تطبيق الإطار الإسلامي الزخرفي على المستند.',
        updatedDoc: updated,
        actionTaken: 'SET_BORDER_ISLAMIC',
      };
    }
    if (/بسيط|ناعم/.test(norm)) {
      updated.borderStyle = 'simple';
      return {
        success: true,
        message: 'تم تطبيق الإطار البسيط على المستند.',
        updatedDoc: updated,
        actionTaken: 'SET_BORDER_SIMPLE',
      };
    }
    updated.borderStyle = 'classic';
    return {
      success: true,
      message: 'تم تطبيق الإطار الرسمي الكلاسيكي على المستند.',
      updatedDoc: updated,
      actionTaken: 'SET_BORDER_CLASSIC',
    };
  }

  // 6. Beneficiary Command (المستفيد / الأخ / وه)
  if (/مستفيد|المستفيد|للاخ|للاخت|لصالح|اسم الشخص|ادفع ل|اصرف ل|المستلم/.test(norm)) {
    let name = cleanPrompt
      .replace(
        /^(غير|اجعل|عدل|بدل|ضع|اكتب|اسم)?\s*(المستفيد|مستفيد|للأخ|للاخ|للأخت|للاخت|المستلم|لصالح|اصرف لـ?|ادفع لـ?)\s*(هو|إلى|الى|:)?\s*/i,
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

  // 7. Purpose / Reason Command (الغرض / المقابل)
  if (/غرض|الغرض|مقابل|السبب|وذالك مقابل|وذلك مقابل|بشان|شراء|قيمه/.test(norm)) {
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

  // 8. Add Watermark / Background Image Command (علامة مائية خلف النص)
  if (/علامه مائيه|خلف النص|watermark|ختم مائي|صوره خلف النص/.test(norm)) {
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
      opacity: 0.18,
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
      message: 'تمت إضافة علامة مائية رسمية خلف النص بشفافية مريحة لا تعيق قراءة المستند، مع إمكانية تحريكها وتعديل حجمها بحرية.',
      updatedDoc: updated,
      newElement: watermark,
      actionTaken: 'ADD_WATERMARK',
    };
  }

  // 9. Add Text Box Command (إدراج مربع نص)
  if (/مربع نص|مربع|صندوق نص|textbox|اضف نص|ملاحظه اداريه|مربع ملاحظات/.test(norm)) {
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
      message: 'تم إدراج مربع نص جديد على الورقة! يمكنك سحبه، تغيير مكانه، تكبيره وتصغيره وتعديل نصه بحرية.',
      updatedDoc: updated,
      newElement: newBox,
      actionTaken: 'ADD_TEXTBOX',
    };
  }

  // 10. Add Shape Command (إدراج شكل هندسي)
  if (/شكل|مستطيل|دائري الحواف|شريط عنوان|خط فاصل/.test(norm)) {
    let shapeType: 'rectangle' | 'rounded' | 'banner' | 'divider' = 'rectangle';
    let w = 220;
    let h = 50;
    let content = 'شريط اعتمادي';
    if (/شريط/.test(norm)) {
      shapeType = 'banner';
      w = 300;
      h = 36;
      content = '★ وثيقة مالية رسمية معتمدة ★';
    } else if (/فاصل|خط/.test(norm)) {
      shapeType = 'divider';
      w = 350;
      h = 4;
      content = '';
    } else if (/دائري/.test(norm)) {
      shapeType = 'rounded';
      w = 180;
      h = 60;
      content = 'معتمد من الإدارة';
    }

    const shapeElem: CanvasElement = {
      id: `elem-${Date.now()}`,
      type: 'shape',
      shapeType,
      x: 250,
      y: 160,
      width: w,
      height: h,
      content,
      layer: 'foreground',
      opacity: 1,
      fontSize: 12,
      isBold: true,
      color: '#1e293b',
      backgroundColor: shapeType === 'divider' ? '#0284c7' : '#f1f5f9',
      borderColor: '#0284c7',
      borderWidth: 1.5,
      borderStyle: 'solid',
      borderRadius: shapeType === 'rounded' ? 12 : 4,
    };

    const elements = updated.canvasElements || [];
    updated.canvasElements = [...elements, shapeElem];
    return {
      success: true,
      message: `تم إدراج شكل (${shapeType === 'banner' ? 'شريط عنوان' : shapeType === 'divider' ? 'خط فاصل' : 'مستطيل'}) على الورقة بنجاح! يمكنك تحريكه وتغيير حجمه بالسحب.`,
      updatedDoc: updated,
      newElement: shapeElem,
      actionTaken: 'ADD_SHAPE',
    };
  }

  // 11. Add Icon Command (إدراج أيقونة)
  if (/ايقونه|أيقونة|ايقونة ختم|ايقونة درع|ايقونة عملات|ايقونة اداره|ايقونة بنك|ايقونة هاتف/.test(norm)) {
    let iconName = 'stamp';
    if (/درع/.test(norm)) iconName = 'shield';
    else if (/عملات|نقود/.test(norm)) iconName = 'coins';
    else if (/اداره|بنك|مبني/.test(norm)) iconName = 'building';
    else if (/هاتف/.test(norm)) iconName = 'phone';

    const iconElem: CanvasElement = {
      id: `elem-${Date.now()}`,
      type: 'icon',
      iconName,
      x: 100,
      y: 120,
      width: 55,
      height: 55,
      content: iconName,
      layer: 'foreground',
      opacity: 1,
      color: '#0284c7',
      backgroundColor: 'transparent',
    };

    const elements = updated.canvasElements || [];
    updated.canvasElements = [...elements, iconElem];
    return {
      success: true,
      message: `تم إدراج أيقونة (${iconName}) على المستند! يمكنك سحبها، تصغيرها وتكبيرها عبر مقابض التحجيم.`,
      updatedDoc: updated,
      newElement: iconElem,
      actionTaken: 'ADD_ICON',
    };
  }

  // 12. Add Currency Symbol ﷼ or other symbols (إدراج رمز)
  if (/رمز|ريال|رمز الريال|علامه|نجمه|ميزان|صح/.test(norm)) {
    let symbol = '﷼';
    let col = '#15803d';
    if (/نجمه/.test(norm)) {
      symbol = '★';
      col = '#d97706';
    } else if (/ميزان|عدل/.test(norm)) {
      symbol = '⚖';
      col = '#0284c7';
    } else if (/صح/.test(norm)) {
      symbol = '✔';
      col = '#16a34a';
    }

    const symbolElem: CanvasElement = {
      id: `elem-${Date.now()}`,
      type: 'symbol',
      x: 550,
      y: 195,
      width: 50,
      height: 40,
      content: symbol,
      layer: 'foreground',
      opacity: 1,
      fontSize: 26,
      isBold: true,
      color: col,
      backgroundColor: 'transparent',
      borderWidth: 0,
      borderStyle: 'none',
    };
    const elements = updated.canvasElements || [];
    updated.canvasElements = [...elements, symbolElem];
    return {
      success: true,
      message: `تم إدراج الرمز (${symbol}) على الورقة بنجاح.`,
      updatedDoc: updated,
      newElement: symbolElem,
      actionTaken: 'ADD_SYMBOL',
    };
  }

  // 13. Remove / Clear Canvas Elements (حذف العناصر أو العلامة المائية)
  if (/احذف العناصر|مسح العناصر|احذف العلامه المائيه|حذف الصور|تفريغ العناصر/.test(norm)) {
    updated.canvasElements = [];
    return {
      success: true,
      message: 'تم حذف وتفريغ جميع العناصر الإضافية والصور والعلامات المائية من الورقة بنجاح.',
      updatedDoc: updated,
      actionTaken: 'CLEAR_CANVAS_ELEMENTS',
    };
  }

  // 14. Details Command (التفاصيل والبنود)
  if (/تفاصيل|التفاصيل|بيان|البيان|شرح/.test(norm)) {
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

  // 15. Notes Command (الملاحظات)
  if (/ملاحظه|ملاحظات|الملاحظات|تنبيه|ملحوظه/.test(norm)) {
    if (/امسح|احذف|تفريغ|ازاله/.test(norm)) {
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

  // 16. Signers & Officials (المسؤولين والتواقيع)
  if (/مدير الفرع|رياض احمد محمد|المدير المالي|امين الصندوق|مقدم الطلب/.test(norm)) {
    if (/رياض/.test(norm) || /مدير الفرع/.test(norm)) {
      updated.managerName = 'رياض احمد محمد';
      return {
        success: true,
        message: 'تم تثبيت اسم مدير فرع صندوق النظافة: «رياض احمد محمد».',
        updatedDoc: updated,
        actionTaken: 'UPDATE_MANAGER_NAME',
      };
    }
    if (/المالي/.test(norm)) {
      const match = cleanPrompt.match(/(?:المدير المالي|المالي)\s*(?:هو|:)?\s*([^\s,]+(?:\s+[^\s,]+){1,3})/);
      if (match) {
        updated.financeManagerName = match[1].trim();
        return {
          success: true,
          message: `تم تحديث اسم المدير المالي إلى: «${updated.financeManagerName}».`,
          updatedDoc: updated,
          actionTaken: 'UPDATE_FINANCE_MANAGER',
        };
      }
    }
    if (/امين الصندوق/.test(norm)) {
      const match = cleanPrompt.match(/(?:امين الصندوق|أمين الصندوق)\s*(?:هو|:)?\s*([^\s,]+(?:\s+[^\s,]+){1,3})/);
      if (match) {
        updated.treasurerName = match[1].trim();
        return {
          success: true,
          message: `تم تحديث اسم أمين الصندوق إلى: «${updated.treasurerName}».`,
          updatedDoc: updated,
          actionTaken: 'UPDATE_TREASURER',
        };
      }
    }
  }

  // 17. Tags Command (الوسوم والتصنيفات)
  if (/وسم|وسوم|تاق|tag|تصنيف/.test(norm)) {
    if (/احذف|ازاله|مسح|حذف/.test(norm)) {
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

  // 18. Document Number Command (رقم المستند)
  if (/رقم|رقم المستند|الرقم|no|كود/.test(norm)) {
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

  // 19. Dates Command (التواريخ)
  if (/تاريخ|اليوم|تاريخ اليوم|الهجري|الميلادي/.test(norm)) {
    if (/اليوم|الان|الحالي/.test(norm)) {
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

  // 20. Attachments Command (المرفقات)
  if (/مرفق|مرفقات|عدد المرفقات/.test(norm)) {
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

  // 21. Status / Approval Command (الاعتماد والصرف)
  if (/اعتمد|اعتماد|موافقه|صرف|تم الصرف|مدفوع/.test(norm)) {
    if (/مدفوع|تم الصرف|تم الدفع/.test(norm)) {
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
    message: 'لم يتم التعرف على الأمر بدقة. جرب كتابة: «اجعل الكتابة عريض» أو «عدل المبلغ إلى 200 ألف» أو «أضف علامة مائية» أو «هوامش ضيقة».',
    updatedDoc: currentDoc,
    actionTaken: 'UNKNOWN',
  };
}
