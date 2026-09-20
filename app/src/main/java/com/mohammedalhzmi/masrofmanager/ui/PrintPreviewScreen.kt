package com.mohammedalhzmi.masrofmanager.ui

import android.content.Context
import android.print.PrintAttributes
import android.print.PrintManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.components.PrintFooter
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentExporter
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentPrintAdapter
import com.mohammedalhzmi.masrofmanager.util.AppPreferences
import com.mohammedalhzmi.masrofmanager.util.DocumentHeader

@Composable
fun PrintPreviewScreen(viewModel: MasrofViewModel, documentIds: String, onNavigateBack: () -> Unit) {
    val documents by viewModel.allDocuments.collectAsState()
    val ids = remember(documentIds) { documentIds.split(",").mapNotNull { it.toLongOrNull() }.toSet() }
    val selectedDocs = documents.filter { it.id in ids }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        Text("معاينة النماذج الرسمية", style = MaterialTheme.typography.headlineMedium)
        Text("عدد الصفحات: ${selectedDocs.size} — كل مستند محفوظ محليًا ويمكن تصديره منفردًا أو كمجموعة", style = MaterialTheme.typography.bodySmall)
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(selectedDocs) { doc -> OfficialDocumentCard(doc) }
            item { PrintFooter() }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(enabled = selectedDocs.isNotEmpty(), onClick = { sharePdf(context, selectedDocs) }, modifier = Modifier.weight(1f)) { Text("PDF ومشاركة") }
            OutlinedButton(enabled = selectedDocs.size == 1, onClick = { shareImage(context, selectedDocs.first()) }, modifier = Modifier.weight(1f)) { Text("صورة PNG") }
        }
        Button(
            onClick = {
                val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
                val header = DocumentHeader(
                    AppPreferences.ministry(context), AppPreferences.administration(context), AppPreferences.branch(context),
                    mapOf(
                        DocumentType.ORDER to AppPreferences.loadLogo(context, DocumentType.ORDER),
                        DocumentType.REQUEST to AppPreferences.loadLogo(context, DocumentType.REQUEST),
                        DocumentType.RECEIPT to AppPreferences.loadLogo(context, DocumentType.RECEIPT)
                    )
                )
                printManager.print("مستندات مالية رسمية", OfficialDocumentPrintAdapter(selectedDocs, header), PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build())
            }, enabled = selectedDocs.isNotEmpty(), modifier = Modifier.fillMaxWidth()
        ) { Text("طباعة مباشرة") }
    }
}

private fun sharePdf(context: Context, docs: List<Document>) {
    val uri = OfficialDocumentExporter.exportPdf(context, docs)
    OfficialDocumentExporter.share(context, uri, "مشاركة المستندات الرسمية PDF")
}

private fun shareImage(context: Context, doc: Document) {
    val uri = OfficialDocumentExporter.exportPng(context, doc)
    OfficialDocumentExporter.share(context, uri, "مشاركة المستند كصورة")
}

@Composable
private fun OfficialDocumentCard(doc: Document) {
    Card(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(when (doc.type) { DocumentType.REQUEST -> "ورقة تقديم طلب"; DocumentType.ORDER -> "أمر صرف"; DocumentType.RECEIPT -> "ورقة استلام" }, style = MaterialTheme.typography.titleMedium)
            Text("رقم المستند: ${doc.documentNumber}")
            Text("الاسم: ${doc.beneficiaryName.orEmpty()}")
            if (doc.amount != null) Text("المبلغ: ${doc.amount} ريال — ${doc.amountWords.orEmpty()}")
            Text("التاريخ: ${doc.dateHijri} / ${doc.dateGregorian}")
        }
    }
}
