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
import android.graphics.Color
import android.graphics.BitmapFactory

object OfficialDocumentExporter {
    fun exportPdf(context: Context, documents: List<Document>): Uri {
        val file = File(context.filesDir, "masrof-official-${System.currentTimeMillis()}.pdf")
        val pdf = PdfDocument()
        documents.forEachIndexed { index, document ->
            val half = AppPreferences.pageSize(context, document.type) == "HALF_A4" || (document.type == DocumentType.ORDER && AppPreferences.pageSize(context, document.type).isBlank())
            val page = pdf.startPage(PdfDocument.PageInfo.Builder(if (half) 842 else 595, if (half) 595 else 842, index + 1).create())
            OfficialDocumentRenderer.render(page.canvas, document, header(context))
            pdf.finishPage(page)
        }
        FileOutputStream(file).use { pdf.writeTo(it) }
        pdf.close()
        return shareUri(context, file)
    }

    fun exportPng(context: Context, document: Document): Uri {
        val file = File(context.filesDir, "masrof-${document.documentNumber}.png")
        val half = AppPreferences.pageSize(context, document.type) == "HALF_A4"
        val bitmap = Bitmap.createBitmap(if (half) 1684 else 1190, if (half) 1190 else 1684, Bitmap.Config.ARGB_8888)
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
        )
    )

    private fun loadBackground(context: Context, type: DocumentType) = AppPreferences.backgroundImageUri(context, type)?.let { runCatching { context.contentResolver.openInputStream(Uri.parse(it)).use(BitmapFactory::decodeStream) }.getOrNull() }
}
