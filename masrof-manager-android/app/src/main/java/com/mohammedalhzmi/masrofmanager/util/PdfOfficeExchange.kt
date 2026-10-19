package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper
import java.io.InputStream
import java.util.UUID

object PdfOfficeExchange {
    data class ImportResult(val document: LocalOfficeDocument, val notices: List<String>)

    fun importToWord(context: Context, input: InputStream, title: String): ImportResult {
        PDFBoxResourceLoader.init(context.applicationContext)
        val html = input.use { stream ->
            PDDocument.load(stream).use { pdf ->
                require(!pdf.isEncrypted) { "ملف PDF مشفر أو محمي بكلمة مرور." }
                val stripper = PDFTextStripper().apply { sortByPosition = true; addMoreFormatting = true }
                val pages = stripper.getText(pdf).split('\u000C')
                pages.mapIndexedNotNull { index, page ->
                    val text = page.replace('\u0000', ' ').trim()
                    if (text.isBlank()) null else "<h3>صفحة ${index + 1}</h3>" + text.lineSequence().joinToString("") { line -> "<p>${escapeHtml(line)}</p>" }
                }.joinToString("")
            }
        }
        val safeHtml = html.ifBlank { "<p>لم يتم العثور على نص قابل للاستخراج. قد يكون الملف صورة ممسوحة ضوئيًا ويحتاج إلى OCR.</p>" }
        val notices = mutableListOf("تم تحويل نص PDF إلى Word وحفظه محليًا للتحرير.")
        if (html.isBlank()) notices += "الملف لا يحتوي على طبقة نصية؛ يلزم OCR للملفات الممسوحة ضوئيًا، لذلك حُفظت صفحة إرشادية بدل نص وهمي."
        notices += "قد تختلف مواضع الصور والجداول والتنسيقات الدقيقة؛ النص المستخرج قابل للتحرير دون قص الأسطر."
        return ImportResult(LocalOfficeDocument(UUID.randomUUID().toString(), title.substringBeforeLast('.').trim().ifBlank { "مستند PDF محول" }, OfficeDocumentKind.WORD, wordHtml = safeHtml), notices)
    }

    private fun escapeHtml(value: String): String = value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")
}
