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
import android.graphics.Color
import android.graphics.BitmapFactory
import android.net.Uri

@Composable
fun PrintPreviewScreen(viewModel: MasrofViewModel, documentIds: String, onNavigateBack: () -> Unit) {
    val documents by viewModel.allDocuments.collectAsState()
    val ids = remember(documentIds) { documentIds.split(",").mapNotNull { it.toLongOrNull() }.toSet() }
    val selectedDocs = documents.filter { it.id in ids }
    val context = LocalContext.current
    var showEditor by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("معاينة النماذج الرسمية", style = MaterialTheme.typography.headlineMedium)
        Text("عدد الصفحات: ${selectedDocs.size} — كل مستند محفوظ محليًا ويمكن تصديره منفردًا أو كمجموعة", style = MaterialTheme.typography.bodySmall)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            OutlinedButton(onClick = { showEditor = true }, modifier = Modifier.weight(1f)) { Text("تحرير الصفحة") }
            OutlinedButton(enabled = selectedDocs.isNotEmpty(), onClick = { sharePdf(context, selectedDocs) }, modifier = Modifier.weight(1f)) { Text("حفظ / مشاركة") }
        }
        if (showEditor) DocumentPageEditor(selectedDocs, context) { showEditor = false }
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
                    ), mapOf(
                        DocumentType.ORDER to AppPreferences.pageSize(context, DocumentType.ORDER),
                        DocumentType.REQUEST to AppPreferences.pageSize(context, DocumentType.REQUEST),
                        DocumentType.RECEIPT to AppPreferences.pageSize(context, DocumentType.RECEIPT)
                    ), mapOf(
                        DocumentType.ORDER to runCatching { Color.parseColor(AppPreferences.backgroundColor(context, DocumentType.ORDER)) }.getOrDefault(Color.WHITE),
                        DocumentType.REQUEST to runCatching { Color.parseColor(AppPreferences.backgroundColor(context, DocumentType.REQUEST)) }.getOrDefault(Color.WHITE),
                        DocumentType.RECEIPT to runCatching { Color.parseColor(AppPreferences.backgroundColor(context, DocumentType.RECEIPT)) }.getOrDefault(Color.WHITE)
                    ), mapOf(
                        DocumentType.ORDER to AppPreferences.backgroundImageUri(context, DocumentType.ORDER)?.let { runCatching { context.contentResolver.openInputStream(Uri.parse(it)).use(BitmapFactory::decodeStream) }.getOrNull() },
                        DocumentType.REQUEST to AppPreferences.backgroundImageUri(context, DocumentType.REQUEST)?.let { runCatching { context.contentResolver.openInputStream(Uri.parse(it)).use(BitmapFactory::decodeStream) }.getOrNull() },
                        DocumentType.RECEIPT to AppPreferences.backgroundImageUri(context, DocumentType.RECEIPT)?.let { runCatching { context.contentResolver.openInputStream(Uri.parse(it)).use(BitmapFactory::decodeStream) }.getOrNull() }
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
private fun DocumentPageEditor(documents: List<Document>, context: Context, onClose: () -> Unit) {
    var selectedType by remember { mutableStateOf(documents.firstOrNull()?.type ?: DocumentType.ORDER) }
    var color by remember(selectedType) { mutableStateOf(AppPreferences.backgroundColor(context, selectedType)) }
    var opacity by remember(selectedType) { mutableStateOf(AppPreferences.backgroundOpacity(context, selectedType)) }
    var scale by remember(selectedType) { mutableStateOf(AppPreferences.backgroundScale(context, selectedType)) }
    val backgroundPicker = androidx.activity.compose.rememberLauncherForActivityResult(androidx.activity.result.contract.ActivityResultContracts.GetContent()) { uri -> uri?.let { AppPreferences.saveBackgroundImage(context, selectedType, it) } }
    AlertDialog(onDismissRequest = onClose, title = { Text("محرر الصفحة") }, text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("اختر النوع ثم غيّر المقاس والخلفية. الشعارات تدار من الإعدادات لكل مستند.")
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { DocumentType.values().forEach { type -> if (type == selectedType) Button(onClick = { selectedType = type }) { Text(typeLabel(type)) } else OutlinedButton(onClick = { selectedType = type }) { Text(typeLabel(type)) } } }
            Text("مقاس الصفحة")
            val size = AppPreferences.pageSize(context, selectedType)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) { if (size == "A4") Button(onClick = { AppPreferences.setPageSize(context, selectedType, "A4") }) { Text("A4") } else OutlinedButton(onClick = { AppPreferences.setPageSize(context, selectedType, "A4") }) { Text("A4") }; if (size == "HALF_A4") Button(onClick = { AppPreferences.setPageSize(context, selectedType, "HALF_A4") }) { Text("نصف A4") } else OutlinedButton(onClick = { AppPreferences.setPageSize(context, selectedType, "HALF_A4") }) { Text("نصف A4") } }
            OutlinedTextField(color, { color = it }, label = { Text("لون الخلفية مثل #FFFFFF") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            OutlinedButton(onClick = { backgroundPicker.launch("image/*") }, modifier = Modifier.fillMaxWidth()) { Text(if (AppPreferences.backgroundImageUri(context, selectedType) == null) "إضافة صورة خلفية" else "تغيير صورة الخلفية") }
            Text("شفافية الصورة: ${(opacity * 100).toInt()}%")
            Slider(value = opacity, onValueChange = { opacity = it }, valueRange = 0f..1f)
            Text("حجم الصورة: ${(scale * 100).toInt()}%")
            Slider(value = scale, onValueChange = { scale = it }, valueRange = 0.2f..3f)
        }
    }, confirmButton = { Button(onClick = { if (runCatching { Color.parseColor(color) }.isSuccess) AppPreferences.setBackgroundColor(context, selectedType, color); AppPreferences.setBackgroundOpacity(context, selectedType, opacity); AppPreferences.setBackgroundScale(context, selectedType, scale); onClose() }) { Text("حفظ التعديلات") } }, dismissButton = { TextButton(onClick = onClose) { Text("إلغاء") } })
}

private fun typeLabel(type: DocumentType) = when (type) { DocumentType.ORDER -> "أمر صرف"; DocumentType.REQUEST -> "تقديم"; DocumentType.RECEIPT -> "استلام" }

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
