package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import java.io.File
import java.io.FileOutputStream
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import android.graphics.Color
import android.graphics.BitmapFactory
import com.example.R

object OfficialDocumentExporter {
    fun exportPdf(context: Context, documents: List<Document>): Uri {
        val file = File(context.filesDir, "masrof-official-${System.currentTimeMillis()}.pdf")
        val pdf = PdfDocument()
        documents.forEachIndexed { index, document ->
            val design = DesignRenderLoader.design(context, document.type)
            val half = AppPreferences.pageSize(context, document.type) == "HALF_A4" || (document.type == DocumentType.ORDER && AppPreferences.pageSize(context, document.type).isBlank())
            val width = design?.pageWidth?.toInt() ?: if (half) 842 else 595; val height = design?.pageHeight?.toInt() ?: if (half) 595 else 842
            val page = pdf.startPage(PdfDocument.PageInfo.Builder(width, height, index + 1).create())
            OfficialDocumentRenderer.render(page.canvas, document, header(context), DesignRenderLoader.elements(context, document.type), context, design)
            pdf.finishPage(page)
        }
        FileOutputStream(file).use { pdf.writeTo(it) }
        pdf.close()
        return shareUri(context, file)
    }

    fun exportPng(context: Context, document: Document): Uri {
        val file = File(context.filesDir, "masrof-${document.documentNumber}.png")
        val design = DesignRenderLoader.design(context, document.type)
        val half = AppPreferences.pageSize(context, document.type) == "HALF_A4"
        val baseWidth = design?.pageWidth?.toInt() ?: if (half) 842 else 595; val baseHeight = design?.pageHeight?.toInt() ?: if (half) 595 else 842
        val bitmap = Bitmap.createBitmap(baseWidth * 2, baseHeight * 2, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.scale(2f, 2f)
        OfficialDocumentRenderer.render(canvas, document, header(context), DesignRenderLoader.elements(context, document.type), context, design)
        FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
        return shareUri(context, file)
    }

    /** Creates a standards-compliant editable Office Open XML document. */
    fun exportDocx(context: Context, document: Document): Uri {
        val file = File(context.filesDir, "masrof-${document.documentNumber}.docx")
        ZipOutputStream(FileOutputStream(file)).use { zip ->
            fun entry(name: String, value: String) {
                zip.putNextEntry(ZipEntry(name)); zip.write(value.toByteArray(Charsets.UTF_8)); zip.closeEntry()
            }
            val logo = AppPreferences.loadLogo(context, document.type)?.let { bitmap -> ByteArrayOutputStream().also { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }.toByteArray() }
            fun bytesEntry(name: String, value: ByteArray) { zip.putNextEntry(ZipEntry(name)); zip.write(value); zip.closeEntry() }
            entry("[Content_Types].xml", """<?xml version="1.0" encoding="UTF-8"?><Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types"><Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/><Default Extension="xml" ContentType="application/xml"/><Default Extension="png" ContentType="image/png"/><Override PartName="/word/document.xml" ContentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml"/></Types>""")
            entry("_rels/.rels", """<?xml version="1.0" encoding="UTF-8"?><Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"><Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="word/document.xml"/></Relationships>""")
            entry("word/_rels/document.xml.rels", if (logo != null) """<?xml version="1.0" encoding="UTF-8"?><Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"><Relationship Id="rId2" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/image" Target="media/logo.png"/></Relationships>""" else """<?xml version="1.0" encoding="UTF-8"?><Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"/>""")
            logo?.let { bytesEntry("word/media/logo.png", it) }
            entry("word/document.xml", docxXml(document, logo != null))
        }
        return shareUri(context, file)
    }

    fun share(context: Context, uri: Uri, title: String) {
        context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
            type = when { uri.toString().endsWith(".png") -> "image/png"; uri.toString().endsWith(".docx") -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"; else -> "application/pdf" }
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }, title))
    }

    private fun shareUri(context: Context, file: File): Uri = FileProvider.getUriForFile(context, "${context.packageName}.files", file)

    private fun docxXml(d: Document, hasLogo: Boolean): String {
        fun esc(s: String) = s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;")
        fun p(label: String, value: String) = "<w:p><w:pPr><w:jc w:val=\"right\"/></w:pPr><w:r><w:rPr><w:rFonts w:ascii=\"Amiri\" w:hAnsi=\"Amiri\"/></w:rPr><w:t>${esc(label)}${esc(value)}</w:t></w:r></w:p>"
        val title = when (d.type) { DocumentType.ORDER -> "أمر صرف"; DocumentType.REQUEST -> "ورقة تقديم طلب"; DocumentType.RECEIPT -> "سند قبض" }
        val logoXml = if (hasLogo) "<w:p><w:pPr><w:jc w:val=\"center\"/></w:pPr><w:r><w:drawing><wp:inline xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><wp:extent cx=\"900000\" cy=\"900000\"/><wp:docPr id=\"1\" name=\"Official logo\"/><a:graphic><a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><pic:pic><pic:blipFill><a:blip r:embed=\"rId2\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/><a:stretch><a:fillRect/></a:stretch></pic:blipFill><pic:spPr><a:prstGeom prst=\"rect\"><a:avLst/></a:prstGeom></pic:spPr></pic:pic></a:graphicData></a:graphic></wp:inline></w:drawing></w:r></w:p>" else ""
        return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"><w:body>$logoXml<w:p><w:pPr><w:jc w:val="center"/></w:pPr><w:r><w:rPr><w:b/><w:rFonts w:ascii="Amiri" w:hAnsi="Amiri"/></w:rPr><w:t>الجمهورية اليمنية - وزارة الإدارة والتنمية المحلية والريفية - صندوق النظافة والتحسين م/إب - فرع مديرية الحزم</w:t></w:r></w:p><w:p><w:pPr><w:jc w:val="center"/></w:pPr><w:r><w:rPr><w:b/></w:rPr><w:t>${esc(title)}</w:t></w:r></w:p>${p("الرقم: ", d.documentNumber)}${p("التاريخ الهجري: ", d.dateHijri)}${p("التاريخ الميلادي: ", d.dateGregorian)}${p("المرفقات: ", d.attachmentsCount.toString())}${p("اسم المستفيد: ", d.beneficiaryName.orEmpty())}${p("المبلغ: ", d.amount?.toString().orEmpty())}${p("المبلغ كتابة: ", d.amountWords.orEmpty())}${p("الغرض: ", d.purpose.orEmpty())}${p("التفاصيل: ", d.details.orEmpty())}<w:p><w:pPr><w:jc w:val="center"/></w:pPr><w:r><w:t>طبع بواسطة نظام مالية فرع صندوق النظافةوالتحسين مديرية الحزم</w:t></w:r></w:p><w:sectPr><w:pgSz w:w="11906" w:h="16838"/><w:pgMar w:top="720" w:right="720" w:bottom="720" w:left="720"/></w:sectPr></w:body></w:document>"""
    }

    private fun header(context: Context) = DocumentHeader(
        AppPreferences.ministry(context),
        AppPreferences.administration(context),
        AppPreferences.branch(context),
        mapOf(
            com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER to AppPreferences.loadLogo(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER),
            com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST to AppPreferences.loadLogo(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST),
            com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT to AppPreferences.loadLogo(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT)
        ), mapOf(
            com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER to AppPreferences.pageSize(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER),
            com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST to AppPreferences.pageSize(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST),
            com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT to AppPreferences.pageSize(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT)
        ), mapOf(
            com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER to runCatching { Color.parseColor(AppPreferences.backgroundColor(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER)) }.getOrDefault(Color.WHITE),
            com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST to runCatching { Color.parseColor(AppPreferences.backgroundColor(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST)) }.getOrDefault(Color.WHITE),
            com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT to runCatching { Color.parseColor(AppPreferences.backgroundColor(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT)) }.getOrDefault(Color.WHITE)
        ), mapOf(
            DocumentType.ORDER to loadBackground(context, DocumentType.ORDER),
            DocumentType.REQUEST to loadBackground(context, DocumentType.REQUEST),
            DocumentType.RECEIPT to loadBackground(context, DocumentType.RECEIPT)
        ), mapOf(
            DocumentType.ORDER to AppPreferences.backgroundOpacity(context, DocumentType.ORDER),
            DocumentType.REQUEST to AppPreferences.backgroundOpacity(context, DocumentType.REQUEST),
            DocumentType.RECEIPT to AppPreferences.backgroundOpacity(context, DocumentType.RECEIPT)
        ), mapOf(
            DocumentType.ORDER to AppPreferences.backgroundScale(context, DocumentType.ORDER),
            DocumentType.REQUEST to AppPreferences.backgroundScale(context, DocumentType.REQUEST),
            DocumentType.RECEIPT to AppPreferences.backgroundScale(context, DocumentType.RECEIPT)
        ), mapOf(
            DocumentType.ORDER to (AppPreferences.backgroundOffsetX(context, DocumentType.ORDER) to AppPreferences.backgroundOffsetY(context, DocumentType.ORDER)),
            DocumentType.REQUEST to (AppPreferences.backgroundOffsetX(context, DocumentType.REQUEST) to AppPreferences.backgroundOffsetY(context, DocumentType.REQUEST)),
            DocumentType.RECEIPT to (AppPreferences.backgroundOffsetX(context, DocumentType.RECEIPT) to AppPreferences.backgroundOffsetY(context, DocumentType.RECEIPT))
        ), mapOf(
            DocumentType.ORDER to runCatching { Color.parseColor(AppPreferences.textColor(context, DocumentType.ORDER)) }.getOrDefault(Color.BLACK),
            DocumentType.REQUEST to runCatching { Color.parseColor(AppPreferences.textColor(context, DocumentType.REQUEST)) }.getOrDefault(Color.BLACK),
            DocumentType.RECEIPT to runCatching { Color.parseColor(AppPreferences.textColor(context, DocumentType.RECEIPT)) }.getOrDefault(Color.BLACK)
        ), mapOf(
            DocumentType.ORDER to AppPreferences.fontFamily(context, DocumentType.ORDER), DocumentType.REQUEST to AppPreferences.fontFamily(context, DocumentType.REQUEST), DocumentType.RECEIPT to AppPreferences.fontFamily(context, DocumentType.RECEIPT)
        ), mapOf(
            DocumentType.ORDER to AppPreferences.textBold(context, DocumentType.ORDER), DocumentType.REQUEST to AppPreferences.textBold(context, DocumentType.REQUEST), DocumentType.RECEIPT to AppPreferences.textBold(context, DocumentType.RECEIPT)
        ), mapOf(
            DocumentType.ORDER to AppPreferences.textItalic(context, DocumentType.ORDER), DocumentType.REQUEST to AppPreferences.textItalic(context, DocumentType.REQUEST), DocumentType.RECEIPT to AppPreferences.textItalic(context, DocumentType.RECEIPT)
        ), mapOf(
            DocumentType.ORDER to AppPreferences.textUnderline(context, DocumentType.ORDER), DocumentType.REQUEST to AppPreferences.textUnderline(context, DocumentType.REQUEST), DocumentType.RECEIPT to AppPreferences.textUnderline(context, DocumentType.RECEIPT)
        )
    )

    private fun loadBackground(context: Context, type: DocumentType) = AppPreferences.backgroundImageUri(context, type)?.let { runCatching { context.contentResolver.openInputStream(Uri.parse(it)).use(BitmapFactory::decodeStream) }.getOrNull() }
        ?: BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_logo_bitmap)
}
