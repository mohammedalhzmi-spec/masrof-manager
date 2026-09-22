import React from 'react';
import { Document, OrganizationProfile, CanvasElement } from '../types';
import { YemenEmblem } from './YemenEmblem';
import { BismillahCalligraphy, RepublicHeader, OfficialRubberStamp } from './OfficialCalligraphy';
import { OfficialQrCode } from './OfficialQrCode';
import { CanvasElementsLayer } from './CanvasElementsLayer';
import { WordInlineEditable } from './WordInlineEditable';
import { convertNumberToWords } from '../utils/numberToWords';

interface DocumentOfficialTemplateProps {
  document: Document;
  organization: OrganizationProfile;
  isEditable?: boolean;
  onFieldChange?: (field: keyof Document, value: any) => void;
  showStamp?: boolean;
  scale?: number;
  containerId?: string;
  className?: string;
  activeElementId?: string | null;
  onSelectElement?: (id: string | null) => void;
  onUpdateElement?: (id: string, updates: Partial<CanvasElement>) => void;
  onDeleteElement?: (id: string) => void;
}

export const DocumentOfficialTemplate: React.FC<DocumentOfficialTemplateProps> = ({
  document: doc,
  organization: org,
  isEditable = false,
  onFieldChange,
  showStamp = true,
  scale = 1,
  containerId,
  className = '',
  activeElementId,
  onSelectElement,
  onUpdateElement,
  onDeleteElement,
}) => {
  const isOrder = doc.type === 'ORDER';
  const isReceipt = doc.type === 'RECEIPT';
  const isRequest = doc.type === 'DISBURSEMENT_REQUEST';

  const getMarginClass = () => {
    switch (doc.pageMargins) {
      case 'narrow':
        return 'p-3 sm:p-4';
      case 'wide':
        return 'p-8 sm:p-12';
      case 'normal':
      default:
        return 'p-6 sm:p-8';
    }
  };

  const getBorderClass = () => {
    switch (doc.borderStyle) {
      case 'double':
        return 'border-[5px] border-double border-black';
      case 'gold':
        return 'border-[4px] border-amber-600 shadow-[inset_0_0_0_2px_#d97706]';
      case 'islamic':
        return 'border-[5px] border-emerald-800';
      case 'simple':
        return 'border-2 border-slate-700';
      case 'classic':
      default:
        return 'border-[3px] border-black';
    }
  };

  const handleInlineChange = (field: keyof Document, value: any) => {
    if (!isEditable || !onFieldChange) return;
    onFieldChange(field, value);
  };

  const handleAmountChange = (newAmountStr: string) => {
    if (!isEditable || !onFieldChange) return;
    const num = parseFloat(newAmountStr.replace(/[^\d.]/g, ''));
    if (!isNaN(num)) {
      onFieldChange('amount', num);
      onFieldChange('amountWords', convertNumberToWords(num));
    } else if (newAmountStr.trim() === '') {
      onFieldChange('amount', 0);
      onFieldChange('amountWords', '');
    }
  };

  const qrVerificationText = `وثيقة مالية رسمية - صندوق النظافة مديرية الحزم | النوع: ${
    isOrder ? 'أمر صرف' : isReceipt ? 'ورقة إستلام' : 'ورقة تقديم طلب'
  } | رقم: ${doc.documentNumber} | المبلغ: ${doc.amount} ر.ي | المستفيد: ${
    doc.beneficiaryName
  } | التاريخ: ${doc.dateHijri || doc.dateGregorian}`;

  const isExtraBold = doc.customContentHtml === 'extra-bold';
  const boldClass = isExtraBold ? 'font-black tracking-wide' : 'font-bold';

  const getFontFamilyStyle = () => {
    if (doc.fontFamily === 'Scheherazade New') return '"Scheherazade New", serif';
    if (doc.fontFamily === 'Cairo') return '"Cairo", sans-serif';
    if (doc.fontFamily === 'Tajawal') return '"Tajawal", sans-serif';
    return '"Amiri", serif';
  };

  return (
    <div
      id={containerId}
      className={`bg-white text-slate-950 ${boldClass} mx-auto select-text relative transition-all ${className}`}
      style={{
        fontFamily: getFontFamilyStyle(),
        transform: scale !== 1 ? `scale(${scale})` : undefined,
        transformOrigin: 'top center',
        direction: 'rtl',
        textRendering: 'geometricPrecision',
      }}
    >
      {/* ========================================================================= */}
      {/* TEMPLATE 1: امر صرف (ORDER) - المطابقة الحرفية الكاملة للصورة رقم 3       */}
      {/* ========================================================================= */}
      {isOrder && (
        <div className={`w-[850px] min-h-[580px] ${getMarginClass()} bg-white ${getBorderClass()} text-black relative flex flex-col justify-between box-border ${boldClass}`}>
          {/* Background Canvas Elements (Under Text / Watermarks) */}
          <CanvasElementsLayer
            elements={doc.canvasElements || []}
            isEditable={isEditable}
            activeElementId={activeElementId || null}
            onSelectElement={onSelectElement || (() => {})}
            onUpdateElement={onUpdateElement || (() => {})}
            onDeleteElement={onDeleteElement || (() => {})}
            layer="background"
          />

          {/* Outer Decorative Double Border */}
          <div className="border border-black p-5 h-full flex flex-col justify-between relative z-10">
            {/* 1. Header (الترويسة الرسمية) */}
            <div>
              <div className="flex items-start justify-between border-b-2 border-black pb-3">
                {/* Right: الترويسة الرسمية أعلى المستند في الجهة اليمنى */}
                <div className="text-right text-sm leading-snug font-bold space-y-0.5 w-[33%]">
                  <p className="font-['Amiri',serif] font-black text-lg text-black leading-tight">
                    الجمهورية اليمنية
                  </p>
                  <p className="text-[13px] font-bold text-black">
                    {org.ministryName || 'وزارة الإدارة والتنمية المحلية والريفية'}
                  </p>
                  <p className="text-[13px] font-bold text-black">
                    {org.administrationName || 'صندوق النظافة والتحسين م/إب'}
                  </p>
                  <p className="text-[13px] font-bold text-black">
                    {org.branchName || 'فرع مديرية الحزم'}
                  </p>
                </div>

                {/* Center: Bismillah + Emblem */}
                <div className="flex flex-col items-center justify-center text-center w-[34%]">
                  <BismillahCalligraphy className="text-base text-black mb-1" />
                  <YemenEmblem className="w-18 h-18" />
                </div>

                {/* Left: Document Metadata */}
                <div className="text-left text-xs font-bold leading-relaxed space-y-1 w-[33%] pl-1">
                  <div className="flex items-center justify-end gap-1.5">
                    <span>الرقم :</span>
                    <WordInlineEditable
                      value={doc.documentNumber}
                      onChange={(val) => handleInlineChange('documentNumber', val)}
                      isEditable={isEditable}
                      className="font-mono text-sm font-black border-b border-solid border-black px-1 min-w-[60px] text-center"
                      placeholder="0001"
                      dir="ltr"
                      title="رقم المستند المالي"
                    />
                  </div>
                  <div className="flex items-center justify-end gap-1.5">
                    <span>التاريخ :</span>
                    <WordInlineEditable
                      value={doc.dateHijri}
                      onChange={(val) => handleInlineChange('dateHijri', val)}
                      isEditable={isEditable}
                      className="font-mono text-xs border-b border-solid border-black px-1 min-w-[75px] text-center"
                      placeholder="   /   / 144هـ"
                      title="التاريخ الهجري"
                    />
                  </div>
                  <div className="flex items-center justify-end gap-1.5">
                    <span>الموافق :</span>
                    <WordInlineEditable
                      value={doc.dateGregorian}
                      onChange={(val) => handleInlineChange('dateGregorian', val)}
                      isEditable={isEditable}
                      className="font-mono text-xs border-b border-solid border-black px-1 min-w-[75px] text-center"
                      placeholder="   /   / 202م"
                      title="التاريخ الميلادي"
                    />
                  </div>
                  <div className="flex items-center justify-end gap-1.5">
                    <span>المرفقات :</span>
                    <span className="font-mono text-xs border-b border-solid border-black px-2 text-center inline-flex items-center">
                      (&nbsp;
                      <WordInlineEditable
                        value={doc.attachmentsCount || 1}
                        onChange={(val) => handleInlineChange('attachmentsCount', parseInt(val, 10) || 1)}
                        isEditable={isEditable}
                        numeric
                        minWidth="20px"
                        className="text-center"
                        placeholder="1"
                        title="عدد المرفقات"
                      />
                      &nbsp;)
                    </span>
                  </div>
                </div>
              </div>

              {/* 2. الصف أسفل الخط الفاصل: اسم المستند في المنتصف، والترقيم في الجهة اليسرى تحت المرفقات مقابل اسم المستند */}
              <div className="flex items-center justify-between my-3 px-1">
                {/* موازن الجهة اليمنى */}
                <div className="w-[33%]"></div>

                {/* المنتصف: مربع اسم المستند فقط */}
                <div className="w-[34%] flex justify-center">
                  <div className="border-[2px] border-black px-8 py-1.5 bg-white text-center shadow-xs">
                    <span className="text-2xl font-black font-['Cairo',serif] text-black tracking-wider">
                      امر صرف
                    </span>
                  </div>
                </div>

                {/* الجهة اليسرى: الترقيم تحت المرفقات مقابل اسم المستند */}
                <div className="w-[33%] text-left pl-1">
                  <div className="inline-flex items-center gap-1.5 font-sans font-black text-lg text-red-700 tracking-wider" dir="ltr">
                    <span>NO:</span>
                    <WordInlineEditable
                      value={doc.documentNumber || '0001'}
                      onChange={(val) => handleInlineChange('documentNumber', val)}
                      isEditable={isEditable}
                      className="font-mono border-b border-solid border-red-700 px-1 min-w-[50px] text-center"
                      placeholder="0001"
                      dir="ltr"
                      title="رقم المستند NO"
                    />
                  </div>
                </div>
              </div>

              {/* 3. Document Body */}
              <div className="mt-5 space-y-4 text-base font-bold leading-loose text-black px-2">
                {/* Line 1: Addressee */}
                <div className="flex items-center justify-between text-base">
                  <span className="text-lg font-black">
                    الأخ / امين الصندوق
                  </span>
                  <span className="text-lg font-black pl-8">المحترم</span>
                </div>

                {/* Line 2: يتم صرف مبلغ وقدرة: + Amount in digits in rounded box */}
                <div className="flex items-center justify-between gap-4 pt-1">
                  <div className="flex-1 flex items-center gap-2">
                    <span className="whitespace-nowrap text-base font-bold">
                      يتم صرف مبلغ وقدرة:
                    </span>
                    <WordInlineEditable
                      value={doc.amountWords}
                      onChange={(val) => handleInlineChange('amountWords', val)}
                      isEditable={isEditable}
                      className="flex-1 border-b-2 border-solid border-black text-center font-bold px-2 py-0.5 text-base text-slate-900 min-h-[28px]"
                      placeholder="..........................................................................................."
                      title="تعديل المبلغ كتابة (التفقيط)"
                    />
                  </div>

                  {/* Rounded Amount Box (مستطيل المبلغ بالأرقام) */}
                  <div className="border-2 border-black rounded-lg px-3 py-1 bg-slate-50 min-w-[155px] text-center shadow-xs flex items-center justify-center gap-1">
                    <WordInlineEditable
                      value={doc.amount ? doc.amount.toLocaleString('ar-YE') : ''}
                      onChange={handleAmountChange}
                      isEditable={isEditable}
                      numeric
                      dir="ltr"
                      placeholder="0"
                      className="font-mono text-lg font-black text-black tracking-wider text-center"
                      title="انقر لتعديل المبلغ رقماً مباشرة على الورقة (يتحدث التفقيط تلقائياً)"
                    />
                    <span className="font-mono text-base font-black text-black select-none">ر.ي</span>
                  </div>
                </div>

                {/* Line 3: للإخ / وه : */}
                <div className="flex items-center gap-2 pt-1">
                  <span className="whitespace-nowrap text-base font-bold">
                    للإخ / وه :
                  </span>
                  <WordInlineEditable
                    value={doc.beneficiaryName}
                    onChange={(val) => handleInlineChange('beneficiaryName', val)}
                    isEditable={isEditable}
                    className="flex-1 border-b-2 border-solid border-black font-bold px-3 py-0.5 text-base min-h-[28px]"
                    placeholder="........................................................................................................................."
                    title="تعديل اسم المستفيد مباشرة على الورقة"
                  />
                </div>

                {/* Line 4: وذالك مقابل / */}
                <div className="flex items-center gap-2 pt-1">
                  <span className="whitespace-nowrap text-base font-bold">
                    وذالك مقابل /
                  </span>
                  <WordInlineEditable
                    value={doc.purpose}
                    onChange={(val) => handleInlineChange('purpose', val)}
                    isEditable={isEditable}
                    className="flex-1 border-b-2 border-solid border-black font-bold px-3 py-0.5 text-base min-h-[28px]"
                    placeholder="........................................................................................................................."
                    title="تعديل الغرض والمبرر مباشرة على الورقة"
                  />
                </div>

                {/* Line 5: Closing Statement */}
                <div className="text-center font-bold text-lg pt-4 pb-2 tracking-wide">
                  ولكم خالص الشكر والتقدير
                </div>

                {/* وسوم وتصنيف المصروف */}
                {doc.tags && doc.tags.length > 0 && (
                  <div className="mt-2 pt-1 border-t border-solid border-slate-300 flex items-center gap-1.5 flex-wrap text-xs text-slate-700">
                    <span className="font-bold text-slate-800">وسوم وتصنيف المصروف:</span>
                    {doc.tags.map((tag) => (
                      <span
                        key={tag}
                        className="inline-flex items-center px-2 py-0.5 rounded bg-slate-100 text-slate-800 border border-slate-300 font-bold text-[11px]"
                      >
                        #{tag}
                      </span>
                    ))}
                  </div>
                )}
              </div>
            </div>

            {/* 4. Signatures Section - دائماً أسفل المستند */}
            <div className="mt-auto pt-6 border-t border-slate-300 relative">
              {showStamp && (
                <div className="absolute top-[-10px] left-1/2 transform -translate-x-1/2 pointer-events-none">
                  <OfficialRubberStamp
                    branchName={org.branchName}
                    fundName={org.administrationName}
                    size={110}
                  />
                </div>
              )}

              <div className="grid grid-cols-2 gap-8 text-center text-sm font-bold">
                {/* Right: المدير المالي للفرع - بدون اسم افتراضي، يتم إضافته في المحرر */}
                <div className="space-y-1">
                  <p className="text-base font-black">المدير المالي للفرع</p>
                  <WordInlineEditable
                    value={doc.financeManagerName}
                    onChange={(val) => handleInlineChange('financeManagerName', val)}
                    isEditable={isEditable}
                    className="text-sm font-bold text-slate-900 py-1 min-h-[26px] block text-center"
                    placeholder={isEditable ? 'اكتب اسم المدير المالي هنا...' : '................................'}
                    title="تعديل اسم المدير المالي"
                  />
                  <p className="text-xs text-slate-800">ت/ ................................</p>
                </div>

                {/* Left: مدير فرع صندوق النظافة - اسم مدير الفرع رياض احمد محمد فقط */}
                <div className="space-y-1">
                  <p className="text-base font-black">مدير فرع صندوق النظافة</p>
                  <WordInlineEditable
                    value={doc.managerName || org.managerName || 'رياض احمد محمد'}
                    onChange={(val) => handleInlineChange('managerName', val)}
                    isEditable={isEditable}
                    className="text-sm font-bold text-slate-900 py-1 min-h-[26px] block text-center"
                    placeholder="رياض احمد محمد"
                    title="تعديل اسم مدير الفرع"
                  />
                  <p className="text-xs text-slate-800">ت/ ................................</p>
                </div>
              </div>
            </div>

            {/* 5. نص أسفل المستند تحت توقيعات الإداريين */}
            <div className="mt-3 pt-2 border-t border-black/40 text-center text-[11.5px] font-bold text-slate-800 tracking-wide">
              {org.systemFooterNote || 'طبع بواسطة نظام مالية فرع صندوق النظافةوالتحسين مديرية الحزم'}
            </div>
          </div>

          {/* Foreground Canvas Elements (Above Text / Floating text boxes, stamps, icons) */}
          <CanvasElementsLayer
            elements={doc.canvasElements || []}
            isEditable={isEditable}
            activeElementId={activeElementId || null}
            onSelectElement={onSelectElement || (() => {})}
            onUpdateElement={onUpdateElement || (() => {})}
            onDeleteElement={onDeleteElement || (() => {})}
            layer="foreground"
          />
        </div>
      )}

      {/* ========================================================================= */}
      {/* TEMPLATE 2: ورقة تقديم طلب (REQUEST) - المطابقة الحرفية للصورة رقم 2     */}
      {/* ========================================================================= */}
      {isRequest && (
        <div className={`w-[800px] min-h-[960px] ${getMarginClass()} bg-white ${getBorderClass()} text-black relative flex flex-col justify-between box-border ${boldClass}`}>
          {/* Background Canvas Elements (Under Text / Watermarks) */}
          <CanvasElementsLayer
            elements={doc.canvasElements || []}
            isEditable={isEditable}
            activeElementId={activeElementId || null}
            onSelectElement={onSelectElement || (() => {})}
            onUpdateElement={onUpdateElement || (() => {})}
            onDeleteElement={onDeleteElement || (() => {})}
            layer="background"
          />

          <div className="relative z-10">
            {/* 1. Header - الترويسة الرسمية أعلى المستند في الجهة اليمنى */}
            <div className="border-b-2 border-black pb-3">
              <div className="flex items-start justify-between">
                {/* Right: الترويسة الرسمية أعلى المستند في الجهة اليمنى */}
                <div className="text-right text-sm leading-snug font-bold space-y-0.5 w-[33%]">
                  <p className="font-['Amiri',serif] font-black text-lg text-black leading-tight">
                    الجمهورية اليمنية
                  </p>
                  <p className="text-[13px] font-bold text-black">
                    {org.ministryName || 'وزارة الإدارة والتنمية المحلية والريفية'}
                  </p>
                  <p className="text-[13px] font-bold text-black">
                    {org.administrationName || 'صندوق النظافة والتحسين م/إب'}
                  </p>
                  <p className="text-[13px] font-bold text-black">
                    {org.branchName || 'فرع مديرية الحزم'}
                  </p>
                </div>

                {/* Center: Bismillah + Emblem */}
                <div className="flex flex-col items-center justify-center text-center w-[34%]">
                  <BismillahCalligraphy className="text-base font-bold mb-1" />
                  <YemenEmblem className="w-16 h-16" />
                </div>

                {/* Left: Metadata */}
                <div className="text-left text-xs font-bold leading-relaxed space-y-1 w-[33%] pl-1">
                  <div className="flex items-center justify-end gap-1.5">
                    <span>الرقم :</span>
                    <WordInlineEditable
                      value={doc.documentNumber}
                      onChange={(val) => handleInlineChange('documentNumber', val)}
                      isEditable={isEditable}
                      className="font-mono text-sm font-bold border-b border-solid border-black px-1 min-w-[60px] text-center"
                      placeholder="0001"
                      dir="ltr"
                      title="رقم المستند المالي"
                    />
                  </div>
                  <div className="flex items-center justify-end gap-1.5">
                    <span>التاريخ :</span>
                    <WordInlineEditable
                      value={doc.dateHijri}
                      onChange={(val) => handleInlineChange('dateHijri', val)}
                      isEditable={isEditable}
                      className="font-mono text-xs border-b border-solid border-black px-1 min-w-[75px] text-center"
                      placeholder="   /   / 144هـ"
                      title="التاريخ الهجري"
                    />
                  </div>
                  <div className="flex items-center justify-end gap-1.5">
                    <span>الموافق :</span>
                    <WordInlineEditable
                      value={doc.dateGregorian}
                      onChange={(val) => handleInlineChange('dateGregorian', val)}
                      isEditable={isEditable}
                      className="font-mono text-xs border-b border-solid border-black px-1 min-w-[75px] text-center"
                      placeholder="   /   / 202م"
                      title="التاريخ الميلادي"
                    />
                  </div>
                  <div className="flex items-center justify-end gap-1.5">
                    <span>المرفقات :</span>
                    <span className="font-mono text-xs border-b border-solid border-black px-2 text-center inline-flex items-center">
                      (&nbsp;
                      <WordInlineEditable
                        value={doc.attachmentsCount || 1}
                        onChange={(val) => handleInlineChange('attachmentsCount', parseInt(val, 10) || 1)}
                        isEditable={isEditable}
                        numeric
                        minWidth="20px"
                        className="text-center"
                        placeholder="1"
                        title="عدد المرفقات"
                      />
                      &nbsp;)
                    </span>
                  </div>
                </div>
              </div>
            </div>

            {/* 2. الصف أسفل الخط الفاصل: اسم المستند في المنتصف، والترقيم في الجهة اليسرى تحت المرفقات مقابل اسم المستند */}
            <div className="flex items-center justify-between my-4 px-1">
              {/* موازن الجهة اليمنى */}
              <div className="w-[33%]"></div>

              {/* المنتصف: مربع اسم المستند فقط */}
              <div className="w-[34%] flex justify-center">
                <div className="border-[2px] border-black px-8 py-1.5 bg-white text-center shadow-xs">
                  <span className="text-2xl font-black font-['Cairo',serif] text-black tracking-wide">
                    ورقة تقديم طلب
                  </span>
                </div>
              </div>

              {/* الجهة اليسرى: الترقيم تحت المرفقات مقابل اسم المستند */}
              <div className="w-[33%] text-left pl-1">
                <div className="inline-flex items-center gap-1.5 font-sans font-black text-base text-red-700 tracking-wider" dir="ltr">
                  <span>NO:</span>
                  <WordInlineEditable
                    value={doc.documentNumber || '0001'}
                    onChange={(val) => handleInlineChange('documentNumber', val)}
                    isEditable={isEditable}
                    className="font-mono border-b border-solid border-red-700 px-1 min-w-[50px] text-center"
                    placeholder="0001"
                    dir="ltr"
                    title="رقم المستند NO"
                  />
                </div>
              </div>
            </div>

            {/* 3. Document Body with 6+ dotted lines */}
            <div className="mt-6 space-y-5 text-base font-bold leading-loose text-black">
              {/* Addressee */}
              <div className="flex items-center justify-between text-base">
                <span className="font-black text-lg">
                  الاخ / مدير فرع صندوق النظافة والتحسين مديرية الحزم
                </span>
                <span className="font-black text-lg pl-6">المحترم</span>
              </div>

              {/* Directive line */}
              <div className="flex items-center gap-2 pt-2">
                <span className="whitespace-nowrap text-base font-bold">
                  تكرموا مشكورين التوجية بصرف
                </span>
                <WordInlineEditable
                  value={
                    doc.amount
                      ? `${doc.amount.toLocaleString('ar-YE')} ريال يمني (${doc.amountWords || convertNumberToWords(doc.amount)})`
                      : doc.amountWords
                  }
                  onChange={(val) => handleInlineChange('amountWords', val)}
                  isEditable={isEditable}
                  className="flex-1 border-b-2 border-solid border-black font-bold px-2 py-0.5 text-base min-h-[28px]"
                  placeholder="........................................................................................................................."
                  title="توجيه ومبلغ الصرف"
                />
              </div>

              {/* For order of */}
              <div className="flex items-center gap-2 pt-1">
                <span className="whitespace-nowrap text-base font-bold">
                  وذالك لامر :
                </span>
                <WordInlineEditable
                  value={doc.purpose || doc.beneficiaryName}
                  onChange={(val) => {
                    handleInlineChange('purpose', val);
                    if (!doc.beneficiaryName) handleInlineChange('beneficiaryName', val);
                  }}
                  isEditable={isEditable}
                  className="flex-1 border-b-2 border-solid border-black font-bold px-2 py-0.5 text-base min-h-[28px]"
                  placeholder="........................................................................................................................."
                  title="الجهة أو الغرض لامر الصرف"
                />
              </div>

              {/* Details Header */}
              <div className="pt-2 text-base font-black">التفاصيل:</div>

              {/* 6 Full Dotted Lines for details */}
              <div className="space-y-3 font-medium text-base">
                <WordInlineEditable
                  value={doc.details || doc.notes || ''}
                  onChange={(val) => handleInlineChange('details', val)}
                  isEditable={isEditable}
                  multiline
                  className="border-b-2 border-solid border-black min-h-[28px] leading-relaxed px-2 font-bold w-full block"
                  placeholder="اكتب تفاصيل وبنود المصروفات هنا مباشرة على الأسطر..."
                  title="كتابة تفاصيل وبنود الطلب مباشرة على السطور"
                />
                <div className="border-b-2 border-solid border-black min-h-[28px]"></div>
                <div className="border-b-2 border-solid border-black min-h-[28px]"></div>
                <div className="border-b-2 border-solid border-black min-h-[28px]"></div>
                <div className="border-b-2 border-solid border-black min-h-[28px]"></div>
                <div className="border-b-2 border-solid border-black min-h-[28px]"></div>
              </div>

              {/* وسوم وتصنيف المصروف */}
              {doc.tags && doc.tags.length > 0 && (
                <div className="mt-3 pt-1 border-t border-solid border-slate-300 flex items-center gap-1.5 flex-wrap text-xs text-slate-700">
                  <span className="font-bold text-slate-800">وسوم وتصنيف المصروف:</span>
                  {doc.tags.map((tag) => (
                    <span
                      key={tag}
                      className="inline-flex items-center px-2 py-0.5 rounded bg-slate-100 text-slate-800 border border-slate-300 font-bold text-[11px]"
                    >
                      #{tag}
                    </span>
                  ))}
                </div>
              )}
            </div>
          </div>

          {/* 4. Bottom Signatures - دائماً أسفل المستند وبدون اسم مسبق ليتم إضافته بالمحرر */}
          <div className="mt-auto pt-6 border-t border-slate-300 flex items-end justify-between">
            <div>
              {showStamp && (
                <OfficialRubberStamp
                  branchName={org.branchName}
                  fundName={org.administrationName}
                  size={100}
                />
              )}
            </div>

            {/* Requester signature block - بدون اسم مسبق ليتم إضافته في المحرر */}
            <div className="text-right space-y-2 text-sm font-bold min-w-[240px]">
              <p className="text-base font-black">مقدم الطلب</p>
              <div className="flex items-center gap-1">
                <span>الاسم :</span>
                <WordInlineEditable
                  value={doc.requesterName}
                  onChange={(val) => handleInlineChange('requesterName', val)}
                  isEditable={isEditable}
                  className="border-b-2 border-solid border-black flex-1 px-2 font-bold min-h-[24px]"
                  placeholder={isEditable ? 'اكتب اسم مقدم الطلب...' : '...................................................'}
                  title="اسم مقدم الطلب"
                />
              </div>
              <div className="flex items-center gap-1">
                <span>توقيع :</span>
                <span className="border-b-2 border-solid border-black flex-1">
                  ...................................................
                </span>
              </div>
            </div>
          </div>

          {/* Foreground Canvas Elements (Above Text / Floating text boxes, stamps, icons) */}
          <CanvasElementsLayer
            elements={doc.canvasElements || []}
            isEditable={isEditable}
            activeElementId={activeElementId || null}
            onSelectElement={onSelectElement || (() => {})}
            onUpdateElement={onUpdateElement || (() => {})}
            onDeleteElement={onDeleteElement || (() => {})}
            layer="foreground"
          />
        </div>
      )}

      {/* ========================================================================= */}
      {/* TEMPLATE 3: ورقة إستلام (RECEIPT) - المطابقة الحرفية الكاملة للصورة رقم 1 */}
      {/* ========================================================================= */}
      {isReceipt && (
        <div className={`w-[820px] min-h-[920px] ${getMarginClass()} bg-white ${getBorderClass()} text-black relative flex flex-col justify-between box-border ${boldClass}`}>
          {/* Background Canvas Elements (Under Text / Watermarks) */}
          <CanvasElementsLayer
            elements={doc.canvasElements || []}
            isEditable={isEditable}
            activeElementId={activeElementId || null}
            onSelectElement={onSelectElement || (() => {})}
            onUpdateElement={onUpdateElement || (() => {})}
            onDeleteElement={onDeleteElement || (() => {})}
            layer="background"
          />

          <div className="relative z-10">
            {/* 1. Header (الترويسة الرسمية) */}
            <div className="flex items-start justify-between border-b-2 border-black pb-3">
              {/* Right: الترويسة الرسمية أعلى المستند في الجهة اليمنى */}
              <div className="text-right text-sm leading-snug font-bold space-y-0.5 w-[33%]">
                <p className="font-['Amiri',serif] font-black text-lg text-black leading-tight">
                  الجمهورية اليمنية
                </p>
                <p className="text-[13px] font-bold text-black">
                  {org.ministryName || 'وزارة الإدارة والتنمية المحلية والريفية'}
                </p>
                <p className="text-[13px] font-bold text-black">
                  {org.administrationName || 'صندوق النظافة والتحسين م/إب'}
                </p>
                <p className="text-[13px] font-bold text-black">
                  {org.branchName || 'فرع مديرية الحزم'}
                </p>
              </div>

              {/* Center: Official Coat of Arms */}
              <div className="flex flex-col items-center justify-center text-center w-[34%]">
                <YemenEmblem className="w-18 h-18" />
              </div>

              {/* Left: Metadata */}
              <div className="text-left text-xs font-bold leading-relaxed space-y-1 w-[33%]">
                <div className="flex items-center justify-end gap-1.5">
                  <span>الرقم :</span>
                  <WordInlineEditable
                    value={doc.documentNumber}
                    onChange={(val) => handleInlineChange('documentNumber', val)}
                    isEditable={isEditable}
                    className="font-mono text-sm font-bold border-b border-solid border-black px-1 min-w-[70px] text-center"
                    placeholder="0001"
                    dir="ltr"
                    title="رقم المستند"
                  />
                </div>
                <div className="flex items-center justify-end gap-1.5">
                  <span>التاريخ :</span>
                  <WordInlineEditable
                    value={doc.dateHijri}
                    onChange={(val) => handleInlineChange('dateHijri', val)}
                    isEditable={isEditable}
                    className="font-mono text-xs border-b border-solid border-black px-1 min-w-[75px] text-center"
                    placeholder="   /   / 144هـ"
                    title="التاريخ الهجري"
                  />
                </div>
                <div className="flex items-center justify-end gap-1.5">
                  <span>الموافق :</span>
                  <WordInlineEditable
                    value={doc.dateGregorian}
                    onChange={(val) => handleInlineChange('dateGregorian', val)}
                    isEditable={isEditable}
                    className="font-mono text-xs border-b border-solid border-black px-1 min-w-[75px] text-center"
                    placeholder="   /   / 202م"
                    title="التاريخ الميلادي"
                  />
                </div>
                <div className="flex items-center justify-end gap-1.5">
                  <span>المرفقات :</span>
                  <span className="font-mono text-xs border-b border-solid border-black px-2 text-center inline-flex items-center">
                    (&nbsp;
                    <WordInlineEditable
                      value={doc.attachmentsCount || 1}
                      onChange={(val) => handleInlineChange('attachmentsCount', parseInt(val, 10) || 1)}
                      isEditable={isEditable}
                      numeric
                      minWidth="20px"
                      className="text-center"
                      placeholder="1"
                      title="عدد المرفقات"
                    />
                    &nbsp;)
                  </span>
                </div>
              </div>
            </div>

            {/* 2. الصف أسفل الخط الفاصل: اسم المستند في المنتصف، والترقيم في الجهة اليسرى تحت المرفقات مقابل اسم المستند */}
            <div className="flex items-center justify-between my-4 px-1">
              {/* موازن الجهة اليمنى */}
              <div className="w-[33%]"></div>

              {/* المنتصف: مربع اسم المستند فقط */}
              <div className="w-[34%] flex justify-center">
                <div className="border-[2px] border-black px-8 py-1.5 bg-white text-center shadow-xs">
                  <span className="text-2xl font-black font-['Cairo',serif] text-black tracking-wide">
                    ورقة إستلام
                  </span>
                </div>
              </div>

              {/* الجهة اليسرى: الترقيم تحت المرفقات مقابل اسم المستند */}
              <div className="w-[33%] text-left pl-1">
                <div className="inline-flex items-center gap-1.5 font-sans font-black text-base text-red-700 tracking-wider" dir="ltr">
                  <span>NO:</span>
                  <WordInlineEditable
                    value={doc.documentNumber || '0001'}
                    onChange={(val) => handleInlineChange('documentNumber', val)}
                    isEditable={isEditable}
                    className="font-mono border-b border-solid border-red-700 px-1 min-w-[50px] text-center"
                    placeholder="0001"
                    dir="ltr"
                    title="رقم المستند NO"
                  />
                </div>
              </div>
            </div>

            {/* 3. Document Body with exact lines from Image 1 */}
            <div className="mt-6 space-y-4 text-base font-bold leading-loose text-black">
              {/* Line 1: انا الموقع ادناه */}
              <div className="flex items-center gap-2">
                <span className="whitespace-nowrap text-base font-bold">
                  انا الموقع ادناه
                </span>
                <WordInlineEditable
                  value={doc.beneficiaryName}
                  onChange={(val) => handleInlineChange('beneficiaryName', val)}
                  isEditable={isEditable}
                  className="flex-1 border-b-2 border-solid border-black font-bold px-3 py-0.5 text-base min-h-[28px]"
                  placeholder="........................................................................................................................."
                  title="اسم المستفيد الموقع أدناه"
                />
              </div>

              {/* Line 2: واعمل بوظيفة */}
              <div className="flex items-center gap-2">
                <span className="whitespace-nowrap text-base font-bold">
                  واعمل بوظيفة
                </span>
                <WordInlineEditable
                  value={doc.jobTitle}
                  onChange={(val) => handleInlineChange('jobTitle', val)}
                  isEditable={isEditable}
                  className="flex-1 border-b-2 border-solid border-black font-bold px-3 py-0.5 text-base min-h-[28px]"
                  placeholder="........................................................................................................................."
                  title="المسمى الوظيفي"
                />
              </div>

              {/* Line 3: إستلمت مبلغ وقدرة: ... رقماً ... */}
              <div className="flex items-center gap-2 flex-wrap sm:flex-nowrap">
                <span className="whitespace-nowrap text-base font-bold">
                  إستلمت مبلغ وقدرة:
                </span>
                <WordInlineEditable
                  value={doc.amountWords}
                  onChange={(val) => handleInlineChange('amountWords', val)}
                  isEditable={isEditable}
                  className="flex-1 border-b-2 border-solid border-black font-bold px-2 py-0.5 text-base min-h-[28px]"
                  placeholder=".................................................................."
                  title="المبلغ كتابة"
                />
                <span className="whitespace-nowrap text-base font-bold mr-2">
                  رقماً
                </span>
                <div className="border-b-2 border-solid border-black px-2 py-0.5 min-w-[130px] text-center flex items-center justify-center gap-1">
                  <WordInlineEditable
                    value={doc.amount ? doc.amount.toLocaleString('ar-YE') : ''}
                    onChange={handleAmountChange}
                    isEditable={isEditable}
                    numeric
                    dir="ltr"
                    className="font-mono text-base font-black text-center"
                    placeholder="0"
                    title="المبلغ رقماً (يتحدث التفقيط تلقائياً)"
                  />
                  <span className="font-mono text-xs font-bold select-none">ريال</span>
                </div>
              </div>

              {/* Line 4: من فرع صندوق النظافة والتحسين مديرية الحزم */}
              <div className="text-base font-black text-black pt-1">
                من فرع صندوق النظافة والتحسين مديرية الحزم
              </div>

              {/* Line 5: وذالك مقابل: */}
              <div className="flex items-center gap-2">
                <span className="whitespace-nowrap text-base font-bold">
                  وذالك مقابل:
                </span>
                <WordInlineEditable
                  value={doc.purpose}
                  onChange={(val) => handleInlineChange('purpose', val)}
                  isEditable={isEditable}
                  className="flex-1 border-b-2 border-solid border-black font-bold px-3 py-0.5 text-base min-h-[28px]"
                  placeholder="........................................................................................................................."
                  title="الغرض والمبرر"
                />
              </div>

              {/* Line 6: لشهر: ... سنة: 144 هـ  202 م */}
              <div className="flex items-center gap-4 text-base font-bold">
                <span className="whitespace-nowrap">لشهر:</span>
                <WordInlineEditable
                  value={doc.monthPeriod}
                  onChange={(val) => handleInlineChange('monthPeriod', val)}
                  isEditable={isEditable}
                  className="border-b-2 border-solid border-black px-3 min-w-[150px] text-center font-bold"
                  placeholder=".........................."
                  title="الفترة / الشهر"
                />
                <span className="whitespace-nowrap">سنة:</span>
                <span className="font-mono inline-flex items-center">
                  144
                  <WordInlineEditable
                    value={doc.yearHijri || '6'}
                    onChange={(val) => handleInlineChange('yearHijri', val)}
                    isEditable={isEditable}
                    numeric
                    minWidth="15px"
                    className="text-center font-bold px-0.5"
                    placeholder="6"
                    title="السنة الهجرية"
                  />
                  &nbsp;هـ
                </span>
                <span className="font-mono inline-flex items-center">
                  202
                  <WordInlineEditable
                    value={doc.yearGregorian || '6'}
                    onChange={(val) => handleInlineChange('yearGregorian', val)}
                    isEditable={isEditable}
                    numeric
                    minWidth="15px"
                    className="text-center font-bold px-0.5"
                    placeholder="6"
                    title="السنة الميلادية"
                  />
                  &nbsp;م
                </span>
              </div>

              {/* Line 7: Prominent declaration statement */}
              <div className="text-center font-black text-lg py-3 px-4 my-3 bg-slate-100/60 border border-black/80">
                وأقر بأنني إستلمت المبلغ كاملاً دون نقص وإبهامي شاهدة على ذالك
              </div>

              {/* Line 8: المستلم + بصمة المستلم */}
              <div className="flex items-start justify-between gap-6 pt-3">
                <div className="flex-1 space-y-3">
                  <div className="flex items-center gap-2">
                    <span className="text-base font-black">المستلم:</span>
                    <WordInlineEditable
                      value={doc.beneficiaryName}
                      onChange={(val) => handleInlineChange('beneficiaryName', val)}
                      isEditable={isEditable}
                      className="border-b-2 border-solid border-black flex-1 font-bold px-2 py-0.5 text-base"
                      placeholder="................................................................................"
                      title="اسم المستلم الموقع"
                    />
                  </div>
                </div>

                {/* Fingerprint container */}
                <div className="text-center border-2 border-solid border-black rounded-sm p-1 w-[100px] h-[105px] flex flex-col items-center justify-between">
                  <span className="text-[11px] font-black">بصمة المستلم</span>
                  <div className="w-12 h-14 border border-solid border-slate-400 rounded-full flex items-center justify-center opacity-40 text-[9px]">
                    (الإبهام)
                  </div>
                </div>
              </div>

              {/* وسوم وتصنيف المصروف */}
              {doc.tags && doc.tags.length > 0 && (
                <div className="mt-3 pt-1 border-t border-solid border-slate-300 flex items-center gap-1.5 flex-wrap text-xs text-slate-700">
                  <span className="font-bold text-slate-800">وسوم وتصنيف المصروف:</span>
                  {doc.tags.map((tag) => (
                    <span
                      key={tag}
                      className="inline-flex items-center px-2 py-0.5 rounded bg-slate-100 text-slate-800 border border-slate-300 font-bold text-[11px]"
                    >
                      #{tag}
                    </span>
                  ))}
                </div>
              )}
            </div>
          </div>

          {/* 4. Bottom Signatures & QR Code - دائماً أسفل المستند */}
          <div className="mt-auto pt-6 border-t-2 border-black">
            <div className="flex items-end justify-between gap-4">
              {/* QR Code */}
              <div className="shrink-0">
                <OfficialQrCode value={qrVerificationText} size={65} />
              </div>

              {/* 3 Signatures: أمين الصندوق والمدير المالي بدون اسم مسبق، ومدير الفرع رياض احمد محمد فقط */}
              <div className="flex-1 grid grid-cols-3 gap-4 text-center text-xs font-bold">
                {/* 1. أمين الصندوق - بدون اسم افتراضي ليتم إضافته في المحرر */}
                <div className="space-y-1">
                  <p className="text-sm font-black">امين الصندوق</p>
                  <WordInlineEditable
                    value={doc.treasurerName}
                    onChange={(val) => handleInlineChange('treasurerName', val)}
                    isEditable={isEditable}
                    className="text-xs font-bold text-slate-900 py-1 min-h-[22px] block text-center"
                    placeholder={isEditable ? 'اكتب اسم أمين الصندوق...' : '.....................'}
                    title="اسم أمين الصندوق"
                  />
                  <p className="text-xs">التوقيع: ....................</p>
                </div>

                {/* 2. المدير المالي للفرع - بدون اسم افتراضي ليتم إضافته في المحرر */}
                <div className="space-y-1">
                  <p className="text-sm font-black">المدير المالي للفرع</p>
                  <WordInlineEditable
                    value={doc.financeManagerName}
                    onChange={(val) => handleInlineChange('financeManagerName', val)}
                    isEditable={isEditable}
                    className="text-xs font-black text-slate-900 py-1 min-h-[22px] block text-center"
                    placeholder={isEditable ? 'اكتب اسم المدير المالي...' : '.....................'}
                    title="اسم المدير المالي"
                  />
                  <p className="text-xs">التوقيع: ....................</p>
                </div>

                {/* 3. مدير فرع صندوق النظافة - رياض احمد محمد فقط */}
                <div className="space-y-1">
                  <p className="text-sm font-black">مدير فرع صندوق النظافة</p>
                  <WordInlineEditable
                    value={doc.managerName || org.managerName || 'رياض احمد محمد'}
                    onChange={(val) => handleInlineChange('managerName', val)}
                    isEditable={isEditable}
                    className="text-sm font-black text-slate-900 py-1 min-h-[22px] block text-center"
                    placeholder="رياض احمد محمد"
                    title="اسم مدير الفرع"
                  />
                  <p className="text-xs">التوقيع: ....................</p>
                </div>
              </div>
            </div>
          </div>

          {/* 5. نص أسفل المستند تحت توقيعات الإداريين */}
          <div className="mt-3 pt-2 border-t border-black/40 text-center text-[11.5px] font-bold text-slate-800 tracking-wide">
            {org.systemFooterNote || 'طبع بواسطة نظام مالية فرع صندوق النظافةوالتحسين مديرية الحزم'}
          </div>

          {/* Foreground Canvas Elements (Above Text / Floating text boxes, stamps, icons) */}
          <CanvasElementsLayer
            elements={doc.canvasElements || []}
            isEditable={isEditable}
            activeElementId={activeElementId || null}
            onSelectElement={onSelectElement || (() => {})}
            onUpdateElement={onUpdateElement || (() => {})}
            onDeleteElement={onDeleteElement || (() => {})}
            layer="foreground"
          />
        </div>
      )}
    </div>
  );
};
