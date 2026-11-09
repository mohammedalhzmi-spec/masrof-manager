package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.rendering.PDFRenderer
import com.tom_roush.pdfbox.text.PDFTextStripper
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.UUID

object PdfOfficeExchange {
    data class ImportResult(val document: LocalOfficeDocument, val notices: List<String>)

    private const val MAX_PDF_BYTES = 30 * 1024 * 1024
    private const val MAX_RENDERED_PAGES = 20

    fun importToWord(context: Context, input: InputStream, title: String): ImportResult {
        PDFBoxResourceLoader.init(context.applicationContext)
        val bytes = readBounded(input)
        var extractedText = false
        val html = PDDocument.load(ByteArrayInputStream(bytes)).use { pdf ->
            require(!pdf.isEncrypted) { "ملف PDF مشفر أو محمي بكلمة مرور." }
            val stripper = PDFTextStripper().apply { sortByPosition = true; addMoreFormatting = true }
            val pages = stripper.getText(pdf).split('\u000C')
            pages.mapIndexedNotNull { index, page ->
                val text = page.replace('\u0000', ' ').trim()
                if (text.isBlank()) null else {
                    extractedText = true
                    "<h3>صفحة ${index + 1}</h3>" +
                        text.lineSequence().joinToString("") { line -> "<p>${escapeHtml(line)}</p>" }
                }
            }.joinToString("")
        }

        val notices = mutableListOf<String>()
        val safeHtml = if (extractedText && html.isNotBlank()) {
            notices += "تم تحويل نص PDF إلى Word وحفظه محليًا للتحرير."
            html
        } else {
            notices += "لم توجد طبقة نصية؛ أُدرجت الصفحات الممسوحة كصور للمعاينة. يلزم OCR لتحرير النص الموجود داخل الصورة."
            renderScannedPages(context, bytes)
        }
        notices += "قد تختلف مواضع الصور والجداول والتنسيقات الدقيقة بين PDF وWord."
        return ImportResult(
            LocalOfficeDocument(
                UUID.randomUUID().toString(),
                title.substringBeforeLast('.').trim().ifBlank { "مستند PDF محول" },
                OfficeDocumentKind.WORD,
                wordHtml = safeHtml
            ),
            notices
        )
    }

    private fun renderScannedPages(context: Context, bytes: ByteArray): String {
        return runCatching {
            PDFBoxResourceLoader.init(context.applicationContext)
            PDDocument.load(ByteArrayInputStream(bytes)).use { pdf ->
                val renderer = PDFRenderer(pdf)
                val count = pdf.numberOfPages.coerceAtMost(MAX_RENDERED_PAGES)
                buildString {
                    for (pageIndex in 0 until count) {
                        val bitmap = renderer.renderImage(pageIndex, 1.2f)
                        try {
                            val output = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 82, output)
                            val encoded = Base64.encodeToString(output.toByteArray(), Base64.NO_WRAP)
                            append("<div class=\"scanned-pdf-page\" style=\"text-align:center;margin:12px 0;\">")
                            append("<img src=\"data:image/jpeg;base64,")
                            append(encoded)
                            append("\" style=\"max-width:100%;height:auto;border:1px solid #E2E8F0;border-radius:4px;\" alt=\"صفحة ممسوحة ${pageIndex + 1}\" />")
                            append("<p style=\"text-align:center;color:#64748B;font-size:11px;\">[صفحة ممسوحة ضوئيًا ${pageIndex + 1} من ${pdf.numberOfPages}]</p>")
                            append("</div>")
                            if (pageIndex < count - 1) append("<div class=\"page-break\" contenteditable=\"false\"></div>")
                        } finally {
                            bitmap.recycle()
                        }
                    }
                }
            }
        }.getOrElse {
            "<p>تعذر رسم الصفحات الممسوحة. افتح الملف الأصلي أو استخدم OCR لتحرير النص.</p>"
        }
    }

    private fun readBounded(input: InputStream): ByteArray {
        val output = ByteArrayOutputStream()
        val buffer = ByteArray(8192)
        var total = 0
        input.use { stream ->
            while (true) {
                val count = stream.read(buffer)
                if (count < 0) break
                total += count
                require(total <= MAX_PDF_BYTES) { "حجم ملف PDF يتجاوز الحد المسموح به (30 ميجابايت)." }
                output.write(buffer, 0, count)
            }
        }
        return output.toByteArray()
    }

    private fun escapeHtml(value: String): String = value
        .replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;")
}
