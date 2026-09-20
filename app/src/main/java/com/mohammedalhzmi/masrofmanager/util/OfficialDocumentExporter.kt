package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import com.mohammedalhzmi.masrofmanager.data.Document
import java.io.File
import java.io.FileOutputStream

object OfficialDocumentExporter {
    fun exportPdf(context: Context, documents: List<Document>): Uri {
        val file = File(context.filesDir, "masrof-official-${System.currentTimeMillis()}.pdf")
        val pdf = PdfDocument()
        documents.forEachIndexed { index, document ->
            val page = pdf.startPage(PdfDocument.PageInfo.Builder(595, 842, index + 1).create())
            OfficialDocumentRenderer.render(page.canvas, document, header(context))
            pdf.finishPage(page)
        }
        FileOutputStream(file).use { pdf.writeTo(it) }
        pdf.close()
        return shareUri(context, file)
    }

    fun exportPng(context: Context, document: Document): Uri {
        val file = File(context.filesDir, "masrof-${document.documentNumber}.png")
        val bitmap = Bitmap.createBitmap(1190, 1684, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.scale(2f, 2f)
        OfficialDocumentRenderer.render(canvas, document, header(context))
        FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
        return shareUri(context, file)
    }

    fun share(context: Context, uri: Uri, title: String) {
        context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
            type = if (uri.toString().endsWith(".png")) "image/png" else "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }, title))
    }

    private fun shareUri(context: Context, file: File): Uri = FileProvider.getUriForFile(context, "${context.packageName}.files", file)

    private fun header(context: Context) = DocumentHeader(
        AppPreferences.ministry(context),
        AppPreferences.administration(context),
        AppPreferences.branch(context),
        mapOf(
            com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER to AppPreferences.loadLogo(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER),
            com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST to AppPreferences.loadLogo(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST),
            com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT to AppPreferences.loadLogo(context, com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT)
        )
    )
}
