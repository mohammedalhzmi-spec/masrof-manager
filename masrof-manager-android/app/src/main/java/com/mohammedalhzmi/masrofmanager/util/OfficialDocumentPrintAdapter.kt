package com.mohammedalhzmi.masrofmanager.util

import android.graphics.*
import android.os.Bundle
import android.os.CancellationSignal
import android.graphics.pdf.PdfDocument
import android.content.Context
import android.net.Uri
import android.graphics.BitmapFactory
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity
import android.print.*
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.displayName
import com.mohammedalhzmi.masrofmanager.data.isExpenseStatement
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.example.R
import java.io.FileOutputStream

data class DocumentHeader(
    val ministry: String,
    val administration: String,
    val branch: String,
    val logos: Map<DocumentType, Bitmap?> = emptyMap(),
    val pageSizes: Map<DocumentType, String> = emptyMap(),
    val backgroundColors: Map<DocumentType, Int> = emptyMap(),
    val backgroundImages: Map<DocumentType, Bitmap?> = emptyMap(),
    val backgroundOpacity: Map<DocumentType, Float> = emptyMap(),
    val backgroundScale: Map<DocumentType, Float> = emptyMap(),
    val backgroundOffset: Map<DocumentType, Pair<Float, Float>> = emptyMap(),
    val textColors: Map<DocumentType, Int> = emptyMap(),
    val fontFamilies: Map<DocumentType, String> = emptyMap(),
    val textBold: Map<DocumentType, Boolean> = emptyMap(),
    val textItalic: Map<DocumentType, Boolean> = emptyMap(),
    val textUnderline: Map<DocumentType, Boolean> = emptyMap()
)

class OfficialDocumentPrintAdapter(private val documents: List<Document>, private val header: DocumentHeader = DocumentHeader("وزارة الإدارة والتنمية المحلية والريفية", "صندوق النظافة والتحسين", "فرع المديرية"), private val context: Context? = null) : PrintDocumentAdapter() {
    private var attributes: PrintAttributes? = null
    override fun onLayout(oldAttributes: PrintAttributes?, newAttributes: PrintAttributes, cancellationSignal: CancellationSignal, callback: LayoutResultCallback, extras: Bundle?) {
        attributes = newAttributes
        if (cancellationSignal.isCanceled) return
        val pageCount = documents.sumOf { if (it.type.isExpenseStatement()) 4 else 1 }
        callback.onLayoutFinished(PrintDocumentInfo.Builder("masrof-official-documents.pdf").setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).setPageCount(pageCount.coerceAtLeast(1)).build(), oldAttributes == null || oldAttributes != newAttributes)
    }
    override fun onWrite(pages: Array<PageRange>, destination: android.os.ParcelFileDescriptor, cancellationSignal: CancellationSignal, callback: WriteResultCallback) {
        val pdf = PdfDocument()
        try {
            val outputPages = documents.flatMap { document ->
                val count = if (document.type.isExpenseStatement()) 4 else 1
                (0 until count).map { pageIndex -> document to pageIndex }
            }
            outputPages.forEachIndexed { index, (document, reportPageIndex) ->
                if (cancellationSignal.isCanceled) return
                if (pages.isNotEmpty() && pages.none { index in it.start..it.end }) return@forEachIndexed
                val fixedA4 = document.type == DocumentType.VIOLATION_REPORT || document.type.isExpenseStatement()
                val half = !fixedA4 && (header.pageSizes[document.type] == "HALF_A4" || (document.type == DocumentType.ORDER && !header.pageSizes.containsKey(document.type)))
                val design = if (context != null) DesignRenderLoader.design(context, document.type) else null
                val landscape = !fixedA4 && design?.orientation == "LANDSCAPE"
                val width = if (fixedA4) 595 else if (half) 842 else (design?.pageWidth?.toInt() ?: if (landscape) 842 else 595)
                val height = if (fixedA4) 842 else if (half) 595 else (design?.pageHeight?.toInt() ?: if (landscape) 595 else 842)
                val page = pdf.startPage(PdfDocument.PageInfo.Builder(width, height, index + 1).create())
                OfficialDocumentRenderer.render(page.canvas, document, header, if (context != null) DesignRenderLoader.elements(context, document.type) else emptyList(), context, design, reportPageIndex)
                pdf.finishPage(page)
            }
            FileOutputStream(destination.fileDescriptor).use { pdf.writeTo(it) }
            callback.onWriteFinished(if (pages.isEmpty()) arrayOf(PageRange.ALL_PAGES) else pages)
        } catch (e: Exception) { callback.onWriteFailed(e.message) } finally { pdf.close() }
    }
}

object OfficialDocumentRenderer {
    private const val navy = 0xff123b5d.toInt()
    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = navy; textSize = 22f; typeface = Typeface.DEFAULT_BOLD; textAlign = Paint.Align.CENTER }
    private val bodyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK; textSize = 14f; typeface = Typeface.DEFAULT }
    private val boldPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK; textSize = 15f; typeface = Typeface.DEFAULT_BOLD }
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = navy; style = Paint.Style.STROKE; strokeWidth = 2f }

    fun render(canvas: Canvas, document: Document, header: DocumentHeader = DocumentHeader("وزارة الإدارة والتنمية المحلية والريفية", "صندوق النظافة والتحسين", "فرع المديرية"), elements: List<DesignElementEntity> = emptyList(), context: Context? = null, design: DocumentDesignEntity? = null, pageIndex: Int = 0) {
        val targetW = canvas.width.toFloat()
        val targetH = canvas.height.toFloat()
        val landscape = targetW > targetH
        val baseW = if (landscape) 842f else 595f
        val baseH = if (landscape) 595f else 842f
        canvas.save()
        canvas.scale(targetW / baseW, targetH / baseH)
        val w = baseW
        val h = baseH
        val pageBackground = if (document.type.isExpenseStatement()) Color.WHITE else design?.let { runCatching { Color.parseColor(it.backgroundColor) }.getOrDefault(Color.WHITE) } ?: (header.backgroundColors[document.type] ?: Color.WHITE)
        canvas.drawColor(pageBackground)
        val useBackgroundImage = !(document.type.isExpenseStatement() && pageIndex == 3)
        if (useBackgroundImage) header.backgroundImages[document.type]?.let { bitmap ->
            val scale = header.backgroundScale[document.type] ?: 1f
            val watermarkSize = minOf(w, h) * 0.58f * scale
            val bw = watermarkSize; val bh = watermarkSize
            val offset = header.backgroundOffset[document.type] ?: (0f to 0f)
            val target = RectF((w - bw) / 2f + offset.first, (h - bh) / 2f + offset.second, (w + bw) / 2f + offset.first, (h + bh) / 2f + offset.second)
            val imagePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { alpha = ((header.backgroundOpacity[document.type] ?: 0.18f) * 255).toInt() }
            canvas.drawBitmap(bitmap, null, target, imagePaint)
        }
        val textColor = header.textColors[document.type] ?: Color.BLACK
        val family = when (header.fontFamilies[document.type]) { "SERIF" -> Typeface.SERIF; "MONOSPACE" -> Typeface.MONOSPACE; else -> Typeface.SANS_SERIF }
        val style = if (header.textBold[document.type] == true && header.textItalic[document.type] == true) Typeface.BOLD_ITALIC else if (header.textBold[document.type] == true) Typeface.BOLD else if (header.textItalic[document.type] == true) Typeface.ITALIC else Typeface.NORMAL
        bodyPaint.color = textColor; bodyPaint.typeface = Typeface.create(family, style); bodyPaint.isUnderlineText = header.textUnderline[document.type] == true
        boldPaint.color = textColor; boldPaint.typeface = Typeface.create(family, Typeface.BOLD)
        if (document.type.isExpenseStatement()) {
            ExpenseReportTemplateRenderer.draw(canvas, document, pageIndex, header, context)
        } else {
            val framePaint = if (document.type == DocumentType.VIOLATION_REPORT) Paint(linePaint).apply { color = 0xff087fb5.toInt(); strokeWidth = 3f } else linePaint
            canvas.drawRect(18f, 18f, w - 18f, h - 18f, framePaint)
            val innerFramePaint = if (document.type == DocumentType.VIOLATION_REPORT) Paint(framePaint).apply { strokeWidth = 1.5f } else linePaint
            canvas.drawRect(25f, 25f, w - 25f, h - 25f, innerFramePaint)
            drawHeader(canvas, header, document, context)
            when (document.type) {
                DocumentType.REQUEST -> renderRequest(canvas, document)
                DocumentType.RECEIPT -> renderReceipt(canvas, document)
                DocumentType.ORDER -> renderOrderPortrait(canvas, document)
                DocumentType.VIOLATION_REPORT -> renderViolationReport(canvas, document)
                DocumentType.EXPENSE_STATEMENT, DocumentType.EXPENSE_REPORT -> Unit
                else -> renderAdministrativeTemplate(canvas, document)
            }
            renderElements(canvas, document, elements, context)
            design?.let { canvas.drawRect(it.marginLeft, it.marginTop, w - it.marginRight, h - it.marginBottom, Paint(Paint.ANTI_ALIAS_FLAG).apply { this.color = 0x55333333.toInt(); this.style = Paint.Style.STROKE; this.strokeWidth = 1f }) }
            drawCentered(canvas, "طبع بواسطة نظام مالية فرع صندوق النظافةوالتحسين مديرية الحزم", w / 2f, h - 28f, bodyPaint)
        }
        canvas.restore()
    }

    private fun drawHeader(c: Canvas, header: DocumentHeader, document: Document, context: Context?) {
        val type = document.type
        val w = c.width.toFloat(); val center = w / 2f
        val headerBody = Paint(bodyPaint).apply { textSize = 11f; textAlign = Paint.Align.LEFT }
        val headerBold = Paint(boldPaint).apply { textSize = 11.5f; textAlign = Paint.Align.RIGHT }
        val leftX = 42f
        drawLeft(c, "الرقم : ............", leftX, 48f, headerBody)
        drawLeft(c, "التاريخ : ${document.dateHijri.ifBlank { "       /       /   144 هـ" }}", leftX, 68f, headerBody)
        drawLeft(c, "الموافق : ${document.dateGregorian.ifBlank { "       /       /     2026 م" }}", leftX, 88f, headerBody)
        drawLeft(c, "المرفقات : ${if (document.attachmentsCount > 0) "( ${document.attachmentsCount} )" else "(         )"}", leftX, 108f, headerBody)
        val rightX = w - 34f
        drawFittedRight(c, "الجمهورية اليمنية", rightX, 46f, headerBold, w * .34f, 9f)
        drawFittedRight(c, "وزارة الإدارة والتنمية المحلية والريفية", rightX, 65f, headerBold, w * .34f, 8f)
        drawFittedRight(c, "صندوق النظافة والتحسين م/إب", rightX, 84f, headerBold, w * .34f, 9f)
        drawFittedRight(c, "فرع مديرية الحزم", rightX, 103f, headerBody, w * .34f, 9f)
        val basmalaPaint = Paint(headerBold).apply { textSize = 10.5f; textAlign = Paint.Align.CENTER }
        drawCentered(c, "بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيمِ", center, 24f, basmalaPaint)
        val officialLogo = header.logos[type] ?: context?.let { ctx: Context -> BitmapFactory.decodeResource(ctx.resources, R.drawable.official_emblem) }
        officialLogo?.let { bitmap ->
            val maxWidth = w * .23f
            val maxHeight = 62f
            val scale = minOf(maxWidth / bitmap.width, maxHeight / bitmap.height)
            val logoWidth = bitmap.width * scale
            val logoHeight = bitmap.height * scale
            val logoTop = 31f
            c.drawBitmap(bitmap, null, RectF(center - logoWidth / 2f, logoTop, center + logoWidth / 2f, logoTop + logoHeight), Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG))
        }
        val dividerPaint = if (type == DocumentType.VIOLATION_REPORT) Paint(linePaint).apply { color = 0xff087fb5.toInt(); strokeWidth = 3f } else linePaint
        c.drawLine(30f, 123f, w - 30f, 123f, dividerPaint)
        drawLeft(c, "NO: ${document.documentNumber.ifBlank { "...." }}", leftX, 151f, headerBold)
        if (type == DocumentType.VIOLATION_REPORT) {
            val reportTitlePaint = Paint(titlePaint).apply { color = Color.BLACK }
            drawCentered(c, title(type), center, 157f, reportTitlePaint)
            c.drawLine(center - 108f, 162f, center + 108f, 162f, Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK; strokeWidth = 1.5f })
        } else {
            drawCentered(c, title(type), center, 157f, titlePaint)
            c.drawRect(center - 112f, 134f, center + 112f, 170f, linePaint)
        }
        if (document.financialCategory.isNotBlank()) drawLeft(c, "البند: ${document.financialCategory}", leftX, 193f, headerBody)
    }

    private fun drawFittedRight(c: Canvas, text: String, x: Float, y: Float, paint: Paint, maxWidth: Float, minSize: Float) {
        val original = paint.textSize
        paint.textAlign = Paint.Align.RIGHT
        paint.textSize = original
        while (paint.measureText(text) > maxWidth && paint.textSize > minSize) paint.textSize -= .5f
        c.drawText(text, x, y, paint)
        paint.textSize = original
        paint.textAlign = Paint.Align.CENTER
    }

    private fun renderOrder(c: Canvas, d: Document) {
        val w = c.width.toFloat(); val right = w - 55f; val bottom = c.height.toFloat()
        val t = OfficialTemplateText.decode(d.notes)
        drawRight(c, t.orderCashierGreeting, right, 220f, boldPaint)
        drawRight(c, t.orderInstruction, right, 270f, bodyPaint)
        drawParagraph(c, d.amountWords.orEmpty().ifBlank { "................................................" }, right, 302f, bodyPaint)
        drawBoxed(c, d.amount?.toString() ?: "................", 70f, 324f, 285f, 365f)
        drawRight(c, "${t.orderBeneficiaryPrefix} ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 399f, bodyPaint)
        drawRight(c, t.orderPurposeLabel, right, 438f, bodyPaint)
        drawParagraph(c, d.purpose.orEmpty().ifBlank { "................................................" }, right, 465f, bodyPaint)
        drawCentered(c, t.orderClosing, w / 2f, 525f, boldPaint)
        drawManagerSignatures(c, bottom)
    }

    private fun renderOrderPortrait(c: Canvas, d: Document) = renderOrder(c, d)

    private fun renderRequest(c: Canvas, d: Document) {
        val right = c.width - 55f; val bottom = c.height.toFloat()
        val t = OfficialTemplateText.decode(d.notes)
        drawRight(c, "إلى الأخ / ${t.requestRecipient.ifBlank { d.purpose.orEmpty().ifBlank { "مدير فرع صندوق النظافة والتحسين" }} }", right, 220f, boldPaint); drawRight(c, t.requestGreeting, right, 246f, bodyPaint)
        drawRight(c, t.requestInstruction, right, 292f, bodyPaint)
        c.drawRect(55f, 310f, right, 455f, linePaint)
        drawParagraph(c, d.details ?: "................................................................................................", right - 12f, 338f, bodyPaint)
        drawRight(c, t.requestClosing, right, 510f, boldPaint)
        drawLeft(c, "اسم مقدم الطلب: ${d.beneficiaryName.orEmpty()}", 58f, bottom - 112f, bodyPaint)
        drawLeft(c, "توقيع مقدم الطلب: ........................", 58f, bottom - 84f, bodyPaint)
    }

    private fun renderReceipt(c: Canvas, d: Document) {
        val right = c.width - 55f; val bottom = c.height.toFloat()
        val t = OfficialTemplateText.decode(d.notes)
        drawRight(c, "${t.receiptOpening} ${d.beneficiaryName.orEmpty()}", right, 220f, boldPaint)
        drawRight(c, "${t.receiptJobLabel} ................................................", right, 258f, bodyPaint)
        drawRight(c, t.receiptAmountLabel, right, 300f, bodyPaint)
        drawBoxed(c, d.amount?.toString() ?: "................", 70f, 270f, 300f, 310f)
        drawRight(c, "فقط: ${d.amountWords ?: "................................................"}", right, 350f, bodyPaint)
        drawRight(c, t.receiptSource, right, 392f, bodyPaint)
        drawRight(c, "${t.receiptPurposeLabel} ${d.purpose.orEmpty()}", right, 435f, bodyPaint)
        drawParagraph(c, t.receiptDischarge, right, 485f, bodyPaint)
        drawCentered(c, t.receiptRecipientLabel, c.width / 2f, bottom - 188f, boldPaint)
        drawCentered(c, "${t.receiptNameLabel} ........................", c.width / 2f, bottom - 160f, bodyPaint)
        drawCentered(c, "${t.receiptSignatureLabel} ................", c.width / 2f, bottom - 132f, bodyPaint)
        drawLeft(c, "مدير فرع صندوق النظافة والتحسين", 42f, bottom - 92f, boldPaint)
        drawLeft(c, "الاسم: رياض أحمد محمد", 42f, bottom - 64f, bodyPaint)
        drawLeft(c, "التوقيع: .........................", 42f, bottom - 36f, bodyPaint)
        drawCentered(c, "أمين الصندوق", c.width / 2f, bottom - 92f, boldPaint)
        drawCentered(c, "الاسم والتوقيع: ................", c.width / 2f, bottom - 64f, bodyPaint)
        drawRight(c, "المدير المالي", right, bottom - 92f, boldPaint)
        drawRight(c, "الاسم: ........................", right, bottom - 64f, bodyPaint)
        drawRight(c, "التوقيع: .........................", right, bottom - 36f, bodyPaint)
    }

    private fun drawManagerSignatures(c: Canvas, bottom: Float) {
        val right = c.width - 42f
        drawLeft(c, "مدير فرع صندوق النظافة والتحسين", 42f, bottom - 112f, boldPaint)
        drawLeft(c, "الاسم: رياض أحمد محمد", 42f, bottom - 84f, bodyPaint)
        drawLeft(c, "التوقيع: ........................", 42f, bottom - 56f, bodyPaint)
        drawRight(c, "المدير المالي", right, bottom - 112f, boldPaint)
        drawRight(c, "الاسم: ........................", right, bottom - 84f, bodyPaint)
        drawRight(c, "التوقيع: ........................", right, bottom - 56f, bodyPaint)
    }

    private fun renderAdministrativeTemplate(c: Canvas, d: Document) {
        val right = c.width - 48f
        val bottom = c.height.toFloat()
        val structured = StructuredDocumentFields.toDisplayText(d.structuredFields)
        val content = listOf(d.details.orEmpty(), structured).filter(String::isNotBlank).joinToString("\n")
            .ifBlank { "........................................................................................................................" }
        when (d.type) {
            DocumentType.FINANCIAL_MEMO -> {
                drawRight(c, "إلى: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 220f, boldPaint)
                drawRight(c, "الموضوع: ${d.purpose.orEmpty().ifBlank { "................................" }}", right, 252f, boldPaint)
                c.drawRect(50f, 275f, right, 475f, linePaint)
                drawParagraph(c, content, right - 12f, 305f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.PURCHASE_ORDER -> {
                drawRight(c, "المورد: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 220f, boldPaint)
                drawRight(c, "الغرض من الشراء: ${d.purpose.orEmpty().ifBlank { "................................" }}", right, 252f, bodyPaint)
                drawBoxed(c, "الإجمالي: ${d.amount?.toString() ?: ".............."}", 55f, 275f, right, 316f)
                c.drawRect(50f, 334f, right, 510f, linePaint)
                drawRight(c, "بيان الأصناف / الشروط", right - 10f, 360f, boldPaint)
                drawParagraph(c, content, right - 12f, 390f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.SUPPLY_PERMIT -> {
                drawRight(c, "إذن توريد / استلام إلى: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 220f, boldPaint)
                drawRight(c, "الغرض / الجهة المستفيدة: ${d.purpose.orEmpty().ifBlank { "................................" }}", right, 252f, bodyPaint)
                c.drawRect(50f, 280f, right, 485f, linePaint)
                drawRight(c, "الأصناف والكميات", right - 10f, 306f, boldPaint)
                drawParagraph(c, content, right - 12f, 338f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.RECEIPT_MINUTES -> {
                drawCentered(c, "محضر استلام", c.width / 2f, 220f, boldPaint)
                drawRight(c, "تم الاستلام من: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 258f, bodyPaint)
                drawRight(c, "بشأن: ${d.purpose.orEmpty().ifBlank { "................................" }}", right, 292f, bodyPaint)
                c.drawRect(50f, 318f, right, 500f, linePaint)
                drawParagraph(c, content, right - 12f, 346f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.FINANCIAL_CLAIM -> {
                drawRight(c, "مقدم المطالبة: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 220f, boldPaint)
                drawRight(c, "سبب المطالبة: ${d.purpose.orEmpty().ifBlank { "................................" }}", right, 252f, bodyPaint)
                drawBoxed(c, "قيمة المطالبة: ${d.amount?.toString() ?: ".............."} ريال", 55f, 280f, right, 322f)
                c.drawRect(50f, 340f, right, 505f, linePaint)
                drawParagraph(c, content, right - 12f, 368f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.CUSTODY_SETTLEMENT -> {
                drawRight(c, "صاحب العهدة: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 220f, boldPaint)
                drawRight(c, "موضوع التسوية: ${d.purpose.orEmpty().ifBlank { "................................" }}", right, 252f, bodyPaint)
                drawBoxed(c, "المبلغ: ${d.amount?.toString() ?: ".............."} ريال", 55f, 280f, right, 322f)
                c.drawRect(50f, 340f, right, 505f, linePaint)
                drawParagraph(c, content, right - 12f, 368f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.ADVANCE_PERMIT -> {
                drawCentered(c, "إذن صرف سلفة", c.width / 2f, 220f, boldPaint)
                drawRight(c, "تصرف إلى: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 258f, bodyPaint)
                drawRight(c, "وذلك لغرض: ${d.purpose.orEmpty().ifBlank { "................................" }}", right, 292f, bodyPaint)
                drawBoxed(c, "مبلغ السلفة: ${d.amount?.toString() ?: ".............."} ريال", 55f, 320f, right, 362f)
                drawParagraph(c, content, right, 402f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.OFFICIAL_FINANCIAL_LETTER -> {
                drawRight(c, "إلى: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 220f, boldPaint)
                drawCentered(c, "الموضوع: ${d.purpose.orEmpty().ifBlank { "................................" }}", c.width / 2f, 260f, boldPaint)
                c.drawRect(50f, 286f, right, 510f, linePaint)
                drawParagraph(c, content, right - 12f, 315f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.RECEIPT_PAPER -> {
                drawCentered(c, "سند قبض", c.width / 2f, 220f, boldPaint)
                drawRight(c, "استلمنا من: ${d.beneficiaryName.orEmpty().ifBlank { "................................" }}", right, 258f, bodyPaint)
                drawBoxed(c, "مبلغ وقدره: ${d.amount?.toString() ?: ".............."} ريال", 55f, 282f, right, 324f)
                drawRight(c, "وذلك مقابل: ${d.purpose.orEmpty().ifBlank { "................................" }}", right, 360f, bodyPaint)
                drawParagraph(c, content, right, 396f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            DocumentType.BOOK -> {
                drawCentered(c, "دفتر المستندات", c.width / 2f, 240f, boldPaint)
                drawCentered(c, d.tags.ifBlank { d.purpose.orEmpty() }, c.width / 2f, 290f, bodyPaint)
                drawCentered(c, "النوع: ${d.type.displayName()}    رقم الصفحة: ${d.documentNumber}", c.width / 2f, 340f, bodyPaint)
                drawParagraph(c, content, right, 390f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
            else -> {
                drawRight(c, "صاحب العلاقة: ${d.beneficiaryName.orEmpty()}", right, 220f, boldPaint)
                drawRight(c, "الموضوع: ${d.purpose.orEmpty()}", right, 252f, bodyPaint)
                drawParagraph(c, content, right, 290f, bodyPaint)
                drawManagerSignatures(c, bottom)
            }
        }
    }

    private fun renderViolationReport(c: Canvas, d: Document) {
        val right = c.width - 42f
        val left = 42f
        drawRight(c, "في تمام الساعة ${d.incidentTime.orEmpty().ifBlank { "............." }} من يوم ${d.incidentDay.orEmpty().ifBlank { "............." }} الموافق: ${d.dateHijri.ifBlank { "   /   / 14هـ" }}", right, 205f, boldPaint)
        drawRight(c, "وفي الموقع الكائن: ${d.incidentLocation.orEmpty().ifBlank { "................................................" }}", right, 231f, bodyPaint)
        drawRight(c, "تم مشاهدة وضبط المخالفة الآتية ونوعها: ${d.violationType.orEmpty().ifBlank { "............................" }}", right, 257f, boldPaint)
        d.details.orEmpty().ifBlank { "........................................................................................................................" }.let { drawParagraph(c, it, right, 284f, bodyPaint) }
        val dotted = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK; style = Paint.Style.STROKE; strokeWidth = 1f; pathEffect = DashPathEffect(floatArrayOf(1.5f, 3.5f), 0f) }
        listOf(316f, 342f, 368f).forEach { y -> c.drawLine(left, y, right, y, dotted) }

        drawRight(c, "والمنسوب إليه فعل / مسؤولية ذلك هو ${d.beneficiaryName.orEmpty().ifBlank { "........................................" }}", right, 397f, boldPaint)
        drawParagraph(c, "وهو الأمر ${d.responsibleAction.orEmpty().ifBlank { "........................................" }} وفقاً لما ورد بنص المادة (${d.lawArticle.orEmpty().ifBlank { "...." }}) من قانون النظافة، والمعاقبة عليه بالحبس مدة لا تقل عن 7 أسابيع أو غرامة مالية لا تقل عن ألف ريال (1000) أو كلاهما.", right, 422f, bodyPaint)

        val legalPaint = Paint(bodyPaint).apply { textSize = 11f }
        drawParagraph(c, "واستناداً إلى نص المادة (32) من القانون تنفيذاً لذلك، فإنه يتعين تطبيق وتحصيل غرامة مالية من المذكور حددت بمبلغ ${d.amount?.toInt()?.toString() ?: "................"} ريال، تورد لحساب صندوق النظافة والتحسين فرع مديرية الحزم وفقاً للمادة (41) من قانون النظافة العامة بطرف البنك المركزي اليمني فرع المحافظة والمادة (26) والتي تنص على الآتي: يتم تحصيل الغرامة عند إشعار المخالفة بالمحضر المعتمد من المكتب لوقوع المخالفة، ويجوز أن يعطى مهلة للسداد لا تزيد عن أسبوع، فإذا تأخر عن التسديد أو لم يطع أمام القاضي المختص يضاعف أصل الغرامة كل أسبوع من تاريخ استلامه أو تسليمه المحضر.", right, 463f, legalPaint)

        drawRight(c, "الشهود", right, 610f, boldPaint)
        drawRight(c, "1- ${d.witnessOne.orEmpty().ifBlank { "...................." }}", right, 634f, bodyPaint)
        drawRight(c, "2- ${d.witnessTwo.orEmpty().ifBlank { "...................." }}", right, 658f, bodyPaint)
        drawCentered(c, "توقيع الشهود", c.width / 2f, 610f, boldPaint)
        drawLeft(c, "مسؤول المنطقة", left, 610f, boldPaint)
        drawLeft(c, "منطقة: ${d.regionName.orEmpty().ifBlank { "...................." }}", left, 634f, bodyPaint)
        drawLeft(c, "الاسم: ${d.regionOfficerName.orEmpty().ifBlank { "...................." }}", left, 658f, bodyPaint)
        drawLeft(c, "التوقيع: ........................", left, 682f, bodyPaint)
        drawLeft(c, "مدير فرع صندوق النظافة والتحسين", left, 712f, boldPaint)
        drawLeft(c, "الاسم: رياض أحمد محمد", left, 738f, bodyPaint)
        drawLeft(c, "التوقيع: ........................", left, 764f, bodyPaint)
        drawRight(c, "المدير المالي", right, 712f, boldPaint)
        drawRight(c, "الاسم: ........................", right, 738f, bodyPaint)
        drawRight(c, "التوقيع: ........................", right, 764f, bodyPaint)
    }

    private fun title(type: DocumentType) = type.displayName()
    private fun renderElements(c: Canvas, d: Document, elements: List<DesignElementEntity>, context: Context?) {
        elements.filter { it.visible }.sortedBy { it.zIndex }.forEach { e ->
            val p = Paint(Paint.ANTI_ALIAS_FLAG).apply { alpha = (e.opacity.coerceIn(0f, 1f) * 255).toInt() }
            c.save(); c.rotate(e.rotation, e.x + e.width / 2f, e.y + e.height / 2f)
            when (e.type) {
                "TEXT" -> drawRichText(c, resolve(e.content, d), e, p, context)
                "RECT" -> { p.style = Paint.Style.FILL; p.color = runCatching { Color.parseColor(e.fillColor) }.getOrDefault(Color.TRANSPARENT); c.drawRoundRect(e.x, e.y, e.x + e.width, e.y + e.height, e.cornerRadius, e.cornerRadius, p); p.style = Paint.Style.STROKE; p.strokeWidth = e.strokeWidth; p.color = runCatching { Color.parseColor(e.strokeColor) }.getOrDefault(Color.DKGRAY); c.drawRoundRect(e.x, e.y, e.x + e.width, e.y + e.height, e.cornerRadius, e.cornerRadius, p) }
                "CIRCLE" -> { p.style = Paint.Style.FILL; p.color = runCatching { Color.parseColor(e.fillColor) }.getOrDefault(Color.TRANSPARENT); c.drawOval(e.x, e.y, e.x + e.width, e.y + e.height, p); p.style = Paint.Style.STROKE; p.strokeWidth = e.strokeWidth; p.color = runCatching { Color.parseColor(e.strokeColor) }.getOrDefault(Color.DKGRAY); c.drawOval(e.x, e.y, e.x + e.width, e.y + e.height, p) }
                "LINE" -> { p.style = Paint.Style.STROKE; p.strokeWidth = e.strokeWidth; p.color = runCatching { Color.parseColor(e.strokeColor) }.getOrDefault(Color.DKGRAY); c.drawLine(e.x, e.y + e.height / 2f, e.x + e.width, e.y + e.height / 2f, p) }
                "ARROW" -> { p.style = Paint.Style.STROKE; p.strokeWidth = e.strokeWidth; p.color = runCatching { Color.parseColor(e.strokeColor) }.getOrDefault(Color.DKGRAY); val y = e.y + e.height / 2f; c.drawLine(e.x, y, e.x + e.width - 14f, y, p); c.drawLine(e.x + e.width - 28f, y - 12f, e.x + e.width, y, p); c.drawLine(e.x + e.width - 28f, y + 12f, e.x + e.width, y, p) }
                "STICKER" -> { p.color = runCatching { Color.parseColor(e.textColor) }.getOrDefault(Color.BLACK); p.textSize = e.height * .75f; p.textAlign = Paint.Align.CENTER; c.drawText(resolve(e.content, d), e.x + e.width / 2f, e.y + e.height * .75f, p); p.textAlign = Paint.Align.LEFT }
                "QR" -> drawQr(c, resolve(e.content, d), e)
                "IMAGE" -> context?.let { ctx -> runCatching { ctx.contentResolver.openInputStream(Uri.parse(e.content)).use(BitmapFactory::decodeStream) }.getOrNull()?.let { c.drawBitmap(it, null, RectF(e.x, e.y, e.x + e.width, e.y + e.height), p) } }
                "TABLE" -> drawTable(c, e)
            }; c.restore()
        }
    }
    private fun drawTable(c: Canvas, e: DesignElementEntity) {
        val rows = DocumentTableCodec.decode(e.content)
        if (rows.isEmpty()) return
        val rowHeight = e.height / rows.size
        val columnCount = rows.first().size.coerceAtLeast(1)
        val columnWidth = e.width / columnCount
        val border = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = runCatching { Color.parseColor(e.strokeColor) }.getOrDefault(Color.DKGRAY)
            style = Paint.Style.STROKE
            strokeWidth = e.strokeWidth.coerceAtLeast(0.5f)
        }
        val text = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = runCatching { Color.parseColor(e.textColor) }.getOrDefault(Color.BLACK)
            textSize = e.fontSize.coerceIn(8f, 24f)
            textAlign = Paint.Align.CENTER
            typeface = if (e.bold) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        }
        rows.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, cell ->
                val left = e.x + columnIndex * columnWidth
                val top = e.y + rowIndex * rowHeight
                c.drawRect(left, top, left + columnWidth, top + rowHeight, border)
                var fitted = cell
                while (fitted.isNotEmpty() && text.measureText(fitted) > columnWidth - 4f) fitted = fitted.dropLast(1)
                if (fitted != cell) fitted = fitted.dropLast(1) + "…"
                c.drawText(fitted, left + columnWidth / 2f, top + rowHeight / 2f + text.textSize / 3f, text)
            }
        }
    }

    private fun drawRichText(c: Canvas, value: String, e: DesignElementEntity, p: Paint, context: Context?) {
        p.color = runCatching { Color.parseColor(e.textColor) }.getOrDefault(Color.BLACK); p.textSize = e.fontSize; val style = if (e.bold && e.italic) Typeface.BOLD_ITALIC else if (e.bold) Typeface.BOLD else if (e.italic) Typeface.ITALIC else Typeface.NORMAL; p.typeface = context?.let { ctx -> runCatching { Typeface.createFromAsset(ctx.assets, "fonts/${when (e.fontFamily) { "AMIRI" -> if (e.bold) "amiri_bold.ttf" else "amiri_regular.ttf"; "CAIRO" -> if (e.bold) "cairo_bold.ttf" else "cairo_regular.ttf"; "SCHEHERAZADE" -> if (e.bold) "scheherazade_bold.ttf" else "scheherazade_regular.ttf"; "EL_MESSIRI" -> if (e.bold) "el_messiri_bold.ttf" else "el_messiri_regular.ttf"; "NOTO_KUFI" -> if (e.bold) "noto_kufi_bold.ttf" else "noto_kufi_regular.ttf"; "NOTO_NASKH" -> if (e.bold) "noto_naskh_bold.ttf" else "noto_naskh_regular.ttf"; "TAJAWAL" -> if (e.bold) "tajawal_bold.ttf" else "tajawal_regular.ttf"; else -> return@runCatching null }}") }.getOrNull() } ?: Typeface.create(when (e.fontFamily) { "SERIF" -> Typeface.SERIF; "MONOSPACE" -> Typeface.MONOSPACE; else -> Typeface.SANS_SERIF }, style); p.isUnderlineText = e.underline
        val lines = value.split("\n"); val lineHeight = e.fontSize * e.lineSpacing; val x = when (e.textAlign) { "CENTER" -> e.x + e.width / 2f; "END" -> e.x + e.width; else -> e.x }; p.textAlign = when (e.textAlign) { "CENTER" -> Paint.Align.CENTER; "END" -> Paint.Align.RIGHT; else -> Paint.Align.LEFT }
        lines.forEachIndexed { index, line -> c.drawText(line, x, e.y + e.fontSize + index * lineHeight, p) }; p.textAlign = Paint.Align.LEFT
    }
    private fun drawQr(c: Canvas, value: String, e: DesignElementEntity) {
        runCatching {
            val matrix = MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE, e.width.toInt().coerceAtLeast(64), e.height.toInt().coerceAtLeast(64))
            val bitmap = Bitmap.createBitmap(matrix.width, matrix.height, Bitmap.Config.ARGB_8888)
            for (x in 0 until matrix.width) for (y in 0 until matrix.height) bitmap.setPixel(x, y, if (matrix[x, y]) Color.BLACK else Color.WHITE)
            c.drawBitmap(bitmap, null, RectF(e.x, e.y, e.x + e.width, e.y + e.height), null)
            bitmap.recycle()
        }
    }
    private fun resolve(value: String, d: Document) = value.replace("{رقم المستند}", d.documentNumber).replace("{اسم المستفيد}", d.beneficiaryName.orEmpty()).replace("{المبلغ}", d.amount?.toString().orEmpty()).replace("{الغرض}", d.purpose.orEmpty()).replace("{المبلغ كتابة}", d.amountWords.orEmpty()).replace("{التاريخ الهجري}", d.dateHijri).replace("{التاريخ الميلادي}", d.dateGregorian)
    private fun drawCentered(c: Canvas, text: String, x: Float, y: Float, p: Paint) { p.textAlign = Paint.Align.CENTER; c.drawText(text, x, y, p) }
    private fun drawRight(c: Canvas, text: String, x: Float, y: Float, p: Paint) { p.textAlign = Paint.Align.RIGHT; c.drawText(text, x, y, p); p.textAlign = Paint.Align.CENTER }
    private fun drawLeft(c: Canvas, text: String, x: Float, y: Float, p: Paint) { p.textAlign = Paint.Align.LEFT; c.drawText(text, x, y, p); p.textAlign = Paint.Align.CENTER }
    private fun drawBoxed(c: Canvas, text: String, l: Float, t: Float, r: Float, b: Float) { c.drawRoundRect(l, t, r, b, 8f, 8f, linePaint); drawCentered(c, text, (l + r) / 2f, (t + b) / 2f + 5f, bodyPaint) }
    private fun drawParagraph(c: Canvas, text: String, x: Float, y: Float, p: Paint) {
        val maxWidth = (x - 70f).coerceAtLeast(180f)
        val words = text.trim().split(Regex("\\s+")).filter(String::isNotBlank)
        var line = ""; var lineY = y
        for (word in words) {
            val candidate = if (line.isBlank()) word else "$line $word"
            if (line.isNotBlank() && p.measureText(candidate) > maxWidth) { drawRight(c, line, x, lineY, p); line = word; lineY += p.textSize * 1.55f } else line = candidate
        }
        if (line.isNotBlank()) drawRight(c, line, x, lineY, p)
    }
}
