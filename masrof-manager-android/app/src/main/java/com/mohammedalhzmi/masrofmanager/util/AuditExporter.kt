package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.net.Uri
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.mohammedalhzmi.masrofmanager.data.AuditLogEntity
import java.text.SimpleDateFormat
import java.util.*

object AuditExporter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private fun row(log: AuditLogEntity) = listOf(log.id.toString(), dateFormat.format(Date(log.timestamp)), log.username, log.action, log.details)
    fun exportCsv(context: Context, uri: Uri, logs: List<AuditLogEntity>) {
        val text = buildString { append("ID,التاريخ,المستخدم,العملية,التفاصيل\n"); logs.forEach { append(row(it).joinToString(",") { value -> "\"${value.replace("\"", "\"\"")}\"" }).append('\n') } }
        context.contentResolver.openOutputStream(uri)?.use { it.write(byteArrayOf(0xEF.toByte(), 0xBB.toByte(), 0xBF.toByte())); it.write(text.toByteArray(Charsets.UTF_8)) }
    }
    fun exportWord(context: Context, uri: Uri, logs: List<AuditLogEntity>) {
        val html = buildString { append("<html dir=\"rtl\"><head><meta charset=\"UTF-8\"></head><body><h1>سجل العمليات والتدقيق</h1><table border=\"1\" cellspacing=\"0\" cellpadding=\"6\"><tr><th>الرقم</th><th>التاريخ</th><th>المستخدم</th><th>العملية</th><th>التفاصيل</th></tr>"); logs.forEach { val r = row(it); append("<tr>${r.joinToString("") { "<td>${it.replace("&", "&amp;").replace("<", "&lt;")}</td>" }}</tr>") }; append("</table></body></html>") }
        context.contentResolver.openOutputStream(uri)?.use { it.write(html.toByteArray(Charsets.UTF_8)) }
    }
    fun exportPdf(context: Context, uri: Uri, logs: List<AuditLogEntity>) {
        val pdf = PdfDocument(); val page = pdf.startPage(PdfDocument.PageInfo.Builder(842, 595, 1).create()); val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { textSize = 10f }
        page.canvas.drawText("Audit Log / سجل العمليات", 40f, 35f, paint); var y = 60f
        logs.take(42).forEach { page.canvas.drawText("${it.id} | ${dateFormat.format(Date(it.timestamp))} | ${it.username} | ${it.action} | ${it.details}", 40f, y, paint); y += 12f }
        pdf.finishPage(page); context.contentResolver.openOutputStream(uri)?.use { pdf.writeTo(it) }; pdf.close()
    }
}
