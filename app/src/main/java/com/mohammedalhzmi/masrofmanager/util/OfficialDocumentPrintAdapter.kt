package com.mohammedalhzmi.masrofmanager.util

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import java.io.FileOutputStream

data class DocumentHeader(val ministry: String, val administration: String, val branch: String, val logos: Map<DocumentType, Bitmap?> = emptyMap())

class OfficialDocumentPrintAdapter(private val documents: List<Document>, private val header: DocumentHeader = DocumentHeader("وزارة الإدارة والتنمية المحلية والريفية", "صندوق النظافة والتحسين", "فرع المديرية")) : PrintDocumentAdapter() {
    private var attributes: PrintAttributes? = null

    override fun onLayout(
        oldAttributes: PrintAttributes?, newAttributes: PrintAttributes,
        cancellationSignal: CancellationSignal, callback: LayoutResultCallback, extras: Bundle?
    ) {
        attributes = newAttributes
        if (cancellationSignal.isCanceled) return
        callback.onLayoutFinished(
            PrintDocumentInfo.Builder("masrof-official-documents.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(documents.size.coerceAtLeast(1)).build(),
            oldAttributes == null || oldAttributes != newAttributes
        )
    }

    override fun onWrite(
        pages: Array<PageRange>, destination: ParcelFileDescriptor,
        cancellationSignal: CancellationSignal, callback: WriteResultCallback
    ) {
        val pdf = PdfDocument()
        try {
            documents.forEachIndexed { index, document ->
                if (cancellationSignal.isCanceled) return
                val page = pdf.startPage(PdfDocument.PageInfo.Builder(595, 842, index + 1).create())
                OfficialDocumentRenderer.render(page.canvas, document, header)
                pdf.finishPage(page)
            }
            FileOutputStream(destination.fileDescriptor).use { pdf.writeTo(it) }
            callback.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
        } catch (e: Exception) {
            callback.onWriteFailed(e.message)
        } finally { pdf.close() }
    }
}

object OfficialDocumentRenderer {
    private const val navy = 0xff123b5d.toInt()
    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = navy; textSize = 25f; typeface = Typeface.DEFAULT_BOLD; textAlign = Paint.Align.CENTER }
    private val bodyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK; textSize = 14f; typeface = Typeface.DEFAULT }
    private val boldPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.BLACK; textSize = 15f; typeface = Typeface.DEFAULT_BOLD }
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = navy; style = Paint.Style.STROKE; strokeWidth = 2f }

    fun render(canvas: Canvas, document: Document, header: DocumentHeader = DocumentHeader("وزارة الإدارة والتنمية المحلية والريفية", "صندوق النظافة والتحسين", "فرع المديرية")) {
        canvas.drawColor(Color.WHITE)
        canvas.drawRect(24f, 24f, 571f, 818f, linePaint)
        canvas.drawRect(32f, 32f, 563f, 810f, linePaint)
        drawHeader(canvas, header, document.type)
        canvas.drawLine(32f, 150f, 563f, 150f, linePaint)
        canvas.drawText(title(document.type), 297f, 192f, titlePaint)
        canvas.drawRect(190f, 160f, 405f, 205f, linePaint)
        drawField(canvas, "الرقم: ${document.documentNumber}", 545f, 62f, true)
        drawField(canvas, "التاريخ الهجري: ${document.dateHijri.ifBlank { "........ / ........ / ........" }}", 545f, 88f, true)
        drawField(canvas, "الموافق: ${document.dateGregorian.ifBlank { "........ / ........ / ........" }}", 545f, 114f, true)
        when (document.type) {
            DocumentType.REQUEST -> renderRequest(canvas, document)
            DocumentType.ORDER -> renderOrder(canvas, document)
            DocumentType.RECEIPT -> renderReceipt(canvas, document)
        }
        drawFooter(canvas)
    }

    private fun drawHeader(canvas: Canvas, header: DocumentHeader, type: DocumentType) {
        drawCentered(canvas, "الجمهورية اليمنية", 55f, boldPaint)
        drawCentered(canvas, header.ministry, 78f, boldPaint)
        drawCentered(canvas, header.administration, 101f, boldPaint)
        drawCentered(canvas, header.branch, 124f, bodyPaint)
        header.logos[type]?.let { bitmap ->
            val target = android.graphics.RectF(260f, 35f, 335f, 120f)
            canvas.drawBitmap(bitmap, null, target, null)
        } ?: drawCentered(canvas, "شعار الجهة", 135f, bodyPaint)
    }

    private fun renderRequest(canvas: Canvas, d: Document) {
        drawRight(canvas, "الأخ / مدير فرع صندوق النظافة والتحسين - مديرية الحزم", 240f, boldPaint)
        drawRight(canvas, "المحترم", 267f, bodyPaint)
        drawParagraph(canvas, "نرجو التكرم بالتوجيه بصرف / اعتماد الطلب الموضح أدناه:", 515f, 300f, 500f)
        drawParagraph(canvas, d.details ?: "................................................................................................", 515f, 345f, 500f)
        drawRight(canvas, "وتكرموا مشكورين بالتوجيه", 415f, boldPaint)
        drawRight(canvas, "اسم مقدم الطلب: ${d.beneficiaryName.orEmpty()}", 500f, bodyPaint)
        drawRight(canvas, "التوقيع: ................................................", 525f, bodyPaint)
    }

    private fun renderOrder(canvas: Canvas, d: Document) {
        drawRight(canvas, "الأخ / أمين الصندوق", 240f, boldPaint)
        drawRight(canvas, "المحترم", 267f, bodyPaint)
        drawParagraph(canvas, "يتم صرف مبلغ وقدره:", 515f, 305f, 500f)
        drawBoxed(canvas, d.amount?.toString() ?: "................", 70f, 278f, 230f, 318f)
        drawParagraph(canvas, d.amountWords ?: "................................................................................................", 515f, 360f, 500f)
        drawParagraph(canvas, "وذلك مقابل / ${d.purpose.orEmpty()}", 515f, 410f, 500f)
        drawCentered(canvas, "ولكم خالص الشكر والتقدير", 470f, boldPaint)
        drawRight(canvas, "مدير الفرع", 480f, bodyPaint)
        drawRight(canvas, "المدير المالي", 520f, bodyPaint)
        drawRight(canvas, "التوقيع: .........................", 480f, bodyPaint)
        drawRight(canvas, "التوقيع: .........................", 520f, bodyPaint)
    }

    private fun renderReceipt(canvas: Canvas, d: Document) {
        drawRight(canvas, "أنا الموقع أدناه: ${d.beneficiaryName.orEmpty()}", 245f, boldPaint)
        drawRight(canvas, "وأعمل بوظيفة: ................................................", 275f, bodyPaint)
        drawRight(canvas, "استلمت مبلغ وقدره:", 305f, bodyPaint)
        drawBoxed(canvas, d.amount?.toString() ?: "................", 70f, 278f, 230f, 318f)
        drawParagraph(canvas, "من فرع صندوق النظافة والتحسين - مديرية الحزم", 515f, 355f, 500f)
        drawParagraph(canvas, "وذلك مقابل: ${d.purpose.orEmpty()}", 515f, 405f, 500f)
        drawParagraph(canvas, "وأقر بأنني استلمت المبلغ كاملًا دون نقص وأصبح ذمتي خالية من ذلك.", 515f, 460f, 500f)
        drawRight(canvas, "أمين الصندوق: ........................", 510f, bodyPaint)
        drawRight(canvas, "المدير المالي للفرع: ........................", 550f, bodyPaint)
        drawRight(canvas, "المستلم: ........................", 590f, bodyPaint)
    }

    private fun drawFooter(canvas: Canvas) { drawCentered(canvas, "نظام مالية صندوق النظافة والتحسين — مستند رسمي", 790f, bodyPaint) }
    private fun title(type: DocumentType) = when (type) { DocumentType.REQUEST -> "ورقة تقديم طلب"; DocumentType.ORDER -> "أمر صرف"; DocumentType.RECEIPT -> "ورقة استلام" }
    private fun drawCentered(c: Canvas, text: String, y: Float, p: Paint) = c.drawText(text, 297f, y, p)
    private fun drawRight(c: Canvas, text: String, y: Float, p: Paint) { p.textAlign = Paint.Align.RIGHT; c.drawText(text, 540f, y, p); p.textAlign = Paint.Align.CENTER }
    private fun drawField(c: Canvas, text: String, x: Float, y: Float, right: Boolean) = drawRight(c, text, y, bodyPaint)
    private fun drawBoxed(c: Canvas, text: String, l: Float, t: Float, r: Float, b: Float) { c.drawRoundRect(l, t, r, b, 8f, 8f, linePaint); c.drawText(text, (l + r) / 2, (t + b) / 2 + 5f, bodyPaint) }
    private fun drawParagraph(c: Canvas, text: String, x: Float, y: Float, width: Float) { drawRight(c, text, y, bodyPaint) }
}
