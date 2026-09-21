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
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
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
        callback.onLayoutFinished(PrintDocumentInfo.Builder("masrof-official-documents.pdf").setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT).setPageCount(documents.size.coerceAtLeast(1)).build(), oldAttributes == null || oldAttributes != newAttributes)
    }
    override fun onWrite(pages: Array<PageRange>, destination: android.os.ParcelFileDescriptor, cancellationSignal: CancellationSignal, callback: WriteResultCallback) {
        val pdf = PdfDocument()
        try {
            documents.forEachIndexed { index, document ->
                if (cancellationSignal.isCanceled) return
                val half = header.pageSizes[document.type] == "HALF_A4" || (document.type == DocumentType.ORDER && !header.pageSizes.containsKey(document.type))
                val design = if (context != null) DesignRenderLoader.design(context, document.type) else null
                val width = design?.pageWidth?.toInt() ?: if (half) 842 else 595
                val height = design?.pageHeight?.toInt() ?: if (half) 595 else 842
                val page = pdf.startPage(PdfDocument.PageInfo.Builder(width, height, index + 1).create())
                OfficialDocumentRenderer.render(page.canvas, document, header, if (context != null) DesignRenderLoader.elements(context, document.type) else emptyList(), context, design)
                pdf.finishPage(page)
            }
            FileOutputStream(destination.fileDescriptor).use { pdf.writeTo(it) }
            callback.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
        } catch (e: Exception) { callback.onWriteFailed(e.message) } finally { pdf.close() }
    }
}

object OfficialDocumentRenderer {
    private const val navy = 0xff123b5d.toInt()
    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = navy; textSize = 25f; typeface = Typeface.DEFAULT_BOLD; textAlign = Paint.Align.CENTER }
    private val bodyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK; textSize = 14f; typeface = Typeface.DEFAULT }
    private val boldPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK; textSize = 15f; typeface = Typeface.DEFAULT_BOLD }
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = navy; style = Paint.Style.STROKE; strokeWidth = 2f }

    fun render(canvas: Canvas, document: Document, header: DocumentHeader = DocumentHeader("وزارة الإدارة والتنمية المحلية والريفية", "صندوق النظافة والتحسين", "فرع المديرية"), elements: List<DesignElementEntity> = emptyList(), context: Context? = null, design: DocumentDesignEntity? = null) {
        val w = canvas.width.toFloat(); val h = canvas.height.toFloat(); val half = h < w
        canvas.drawColor(design?.let { runCatching { Color.parseColor(it.backgroundColor) }.getOrDefault(Color.WHITE) } ?: (header.backgroundColors[document.type] ?: Color.WHITE))
        header.backgroundImages[document.type]?.let { bitmap ->
            val scale = header.backgroundScale[document.type] ?: 1f
            val bw = w * scale; val bh = h * scale
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
        canvas.drawRect(18f, 18f, w - 18f, h - 18f, linePaint)
        canvas.drawRect(25f, 25f, w - 25f, h - 25f, linePaint)
        drawHeader(canvas, header, document.type)
        if (half) renderOrder(canvas, document) else when (document.type) { DocumentType.REQUEST -> renderRequest(canvas, document); DocumentType.RECEIPT -> renderReceipt(canvas, document); DocumentType.ORDER -> renderOrderPortrait(canvas, document) }
        renderElements(canvas, document, elements, context)
        design?.let { canvas.drawRect(it.marginLeft, it.marginTop, w - it.marginRight, h - it.marginBottom, Paint(Paint.ANTI_ALIAS_FLAG).apply { this.color = 0x55333333.toInt(); this.style = Paint.Style.STROKE; this.strokeWidth = 1f }) }
        drawCentered(canvas, "نظام مالية صندوق النظافة والتحسين — مستند رسمي", w / 2f, h - 28f, bodyPaint)
    }

    private fun drawHeader(c: Canvas, header: DocumentHeader, type: DocumentType) {
        val w = c.width.toFloat(); val center = w / 2f
        drawLeft(c, "الرقم: ${"................"}", 55f, 52f, bodyPaint)
        drawLeft(c, "التاريخ:    /    / 144 هـ", 55f, 76f, bodyPaint)
        drawLeft(c, "الموافق:    /    / 202 م", 55f, 100f, bodyPaint)
        drawCentered(c, "الجمهورية اليمنية", center, 48f, boldPaint)
        drawCentered(c, header.ministry, center, 70f, boldPaint)
        drawCentered(c, header.administration, center, 92f, boldPaint)
        drawCentered(c, header.branch, center, 114f, bodyPaint)
        header.logos[type]?.let { c.drawBitmap(it, null, RectF(center - 38f, 32f, center + 38f, 112f), null) }
        c.drawLine(30f, 132f, w - 30f, 132f, linePaint)
        drawCentered(c, title(type), center, 166f, titlePaint)
        c.drawRect(center - 112f, 140f, center + 112f, 178f, linePaint)
    }

    private fun renderOrder(c: Canvas, d: Document) {
        val w = c.width.toFloat(); val right = w - 55f; val bottom = c.height.toFloat()
        drawRight(c, "الأخ / أمين الصندوق", right, 220f, boldPaint); drawRight(c, "المحترم", right, 246f, bodyPaint)
        drawRight(c, "يتم صرف مبلغ وقدره:", right, 292f, bodyPaint)
        drawBoxed(c, d.amount?.toString() ?: "................", 70f, 263f, 290f, 304f)
        drawRight(c, "فقط: ${d.amountWords ?: "................................................"}", right, 338f, bodyPaint)
        drawRight(c, "وذلك مقابل / ${d.purpose.orEmpty()}", right, 382f, bodyPaint)
        drawCentered(c, "ولكم خالص الشكر والتقدير", w / 2f, 422f, boldPaint)
        drawLeft(c, "المدير المالي للفرع", 70f, bottom - 76f, boldPaint)
        drawLeft(c, "التوقيع: .........................", 70f, bottom - 48f, bodyPaint)
        drawRight(c, "مدير الفرع", right, bottom - 76f, boldPaint)
        drawRight(c, "التوقيع: .........................", right, bottom - 48f, bodyPaint)
        drawLeft(c, "المرفقات: ${d.attachmentsCount}", 55f, bottom - 20f, bodyPaint)
        drawRight(c, "الاسم: ${d.beneficiaryName.orEmpty()}    رقم: ${d.documentNumber}", right, 205f, bodyPaint)
    }

    private fun renderOrderPortrait(c: Canvas, d: Document) = renderOrder(c, d)

    private fun renderRequest(c: Canvas, d: Document) {
        val right = c.width - 55f; val bottom = c.height.toFloat()
        drawRight(c, "إلى الأخ / مدير فرع صندوق النظافة والتحسين", right, 220f, boldPaint); drawRight(c, "المحترم", right, 246f, bodyPaint)
        drawRight(c, "نتكرم بالتوجيه بصرف / اعتماد الطلب الموضح أدناه:", right, 292f, bodyPaint)
        drawParagraph(c, d.details ?: "................................................................................................", right, 336f, bodyPaint)
        drawRight(c, "وتكرموا مشكورين بالتوجيه", right, 510f, boldPaint)
        drawRight(c, "اسم مقدم الطلب: ${d.beneficiaryName.orEmpty()}", right, bottom - 135f, bodyPaint)
        drawRight(c, "التوقيع: ................................................", right, bottom - 105f, bodyPaint)
        drawLeft(c, "المرفقات: ${d.attachmentsCount}", 55f, bottom - 42f, bodyPaint)
        drawRight(c, "رقم الطلب: ${d.documentNumber}", right, 195f, bodyPaint)
    }

    private fun renderReceipt(c: Canvas, d: Document) {
        val right = c.width - 55f; val bottom = c.height.toFloat()
        drawRight(c, "أنا الموقع أدناه: ${d.beneficiaryName.orEmpty()}", right, 220f, boldPaint)
        drawRight(c, "وأعمل بوظيفة: ................................................", right, 258f, bodyPaint)
        drawRight(c, "استلمت مبلغًا وقدره: ${d.amount ?: "................"}", right, 300f, bodyPaint)
        drawRight(c, "فقط: ${d.amountWords ?: "................................................"}", right, 340f, bodyPaint)
        drawRight(c, "من فرع صندوق النظافة والتحسين", right, 392f, bodyPaint)
        drawRight(c, "وذلك مقابل: ${d.purpose.orEmpty()}", right, 435f, bodyPaint)
        drawParagraph(c, "وأقر بأنني استلمت المبلغ كاملًا دون نقص وأصبحت ذمتي خالية من ذلك.", right, 485f, bodyPaint)
        drawLeft(c, "أمين الصندوق", 55f, bottom - 104f, boldPaint); drawLeft(c, "التوقيع: ................", 55f, bottom - 76f, bodyPaint)
        drawCentered(c, "المستلم", c.width / 2f, bottom - 104f, boldPaint); drawCentered(c, "التوقيع: ................", c.width / 2f, bottom - 76f, bodyPaint)
        drawRight(c, "المدير المالي للفرع", right, bottom - 104f, boldPaint); drawRight(c, "التوقيع: ................", right, bottom - 76f, bodyPaint)
        drawLeft(c, "المرفقات: ${d.attachmentsCount}", 55f, bottom - 42f, bodyPaint)
        drawRight(c, "رقم الاستلام: ${d.documentNumber}", right, 195f, bodyPaint)
    }

    private fun title(type: DocumentType) = when (type) { DocumentType.REQUEST -> "ورقة تقديم طلب"; DocumentType.ORDER -> "أمر صرف"; DocumentType.RECEIPT -> "ورقة استلام" }
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
            }; c.restore()
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
    private fun drawParagraph(c: Canvas, text: String, x: Float, y: Float, p: Paint) { drawRight(c, text, x, y, p) }
}
