package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentExporter
import com.mohammedalhzmi.masrofmanager.util.OfficeDocumentRecord

@Composable
fun BookEditorScreen(
    viewModel: MasrofViewModel,
    bookTag: String,
    onPreview: (String) -> Unit,
    onOpenCanvas: (DocumentType) -> Unit,
    onOpenOffice: (Long) -> Unit,
    onBack: () -> Unit
) {
    val allDocuments by viewModel.allDocuments.collectAsState()
    val pages = remember(allDocuments, bookTag) {
        allDocuments.filter { it.tags.split(',').map(String::trim).contains(bookTag) }.sortedBy { it.documentNumber }
    }
    var pageIndex by remember { mutableIntStateOf(0) }
    var showPreview by remember { mutableStateOf(false) }
    // كل صفحة من الدفتر تفتح مباشرة في المحرر المدمج المطوّر.
    var showWordEditor by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val current = pages.getOrNull(pageIndex)
    Column(Modifier.fillMaxSize().padding(12.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("تحرير دفتر المستندات بالمحرر المدمج", style = MaterialTheme.typography.titleLarge)
            TextButton(onClick = onBack) { Text("خروج") }
        }
        Text("$bookTag — ${if (pages.isEmpty()) 0 else pageIndex + 1} من ${pages.size}", style = MaterialTheme.typography.bodySmall)
        if (pages.isEmpty()) {
            Text("لم تُحمّل صفحات الدفتر بعد. ارجع للقائمة ثم افتح الدفتر مرة أخرى.", modifier = Modifier.padding(16.dp))
        } else if (current != null && OfficeDocumentRecord.isOfficeDocument(current)) {
            Column(Modifier.weight(1f).fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("هذه الصفحة محفوظة كمستند Office مرتبط بالدفتر.", style = MaterialTheme.typography.titleMedium)
                Text("يمكن متابعة تحرير النص أو خلايا الجدول في المحرر المحلي، مع بقاء السجل داخل قاعدة النظام.", style = MaterialTheme.typography.bodyMedium)
                Button(onClick = { onOpenOffice(current.id) }, modifier = Modifier.fillMaxWidth()) { Text("فتح مستند Office للتحرير") }
            }
        } else if (current != null) {
            Column(
                Modifier.weight(1f).fillMaxWidth()
                    .pointerInput(pageIndex, pages.size) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            change.consume()
                            if (dragAmount < -80f && pageIndex < pages.lastIndex) pageIndex++
                            if (dragAmount > 80f && pageIndex > 0) pageIndex--
                        }
                    }.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                    Column(Modifier.padding(12.dp)) {
                        Text("صفحة ${pageIndex + 1} — رقم ${current.documentNumber}", style = MaterialTheme.typography.titleMedium)
                        Text("حرر البيانات من الأعلى إلى الأسفل، ثم افتح محرر المستندات للتحكم بالعناصر داخل الصفحة نفسها.", style = MaterialTheme.typography.bodySmall)
                    }
                }
                var hijri by remember(current.id) { mutableStateOf(current.dateHijri) }
                var gregorian by remember(current.id) { mutableStateOf(current.dateGregorian) }
                var beneficiary by remember(current.id) { mutableStateOf(current.beneficiaryName.orEmpty()) }
                var beneficiaryId by remember(current.id) { mutableStateOf(current.beneficiaryId) }
                var purpose by remember(current.id) { mutableStateOf(current.purpose.orEmpty()) }
                var details by remember(current.id) { mutableStateOf(current.details.orEmpty()) }
                var amount by remember(current.id) { mutableStateOf(current.amount?.toString().orEmpty()) }
                var amountWords by remember(current.id) { mutableStateOf(current.amountWords.orEmpty()) }
                var category by remember(current.id) { mutableStateOf(current.financialCategory) }
                var costCenter by remember(current.id) { mutableStateOf(current.costCenter) }
                var funding by remember(current.id) { mutableStateOf(current.fundingSource) }
                var notes by remember(current.id) { mutableStateOf(current.notes.orEmpty()) }
                var tags by remember(current.id) { mutableStateOf(current.tags) }
                var attachments by remember(current.id) { mutableStateOf(current.attachmentsCount.toString()) }

                Text("بيانات المستند", style = MaterialTheme.typography.titleMedium)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = hijri, onValueChange = { hijri = it }, label = { Text("التاريخ الهجري") }, modifier = Modifier.weight(1f))
                    OutlinedTextField(value = gregorian, onValueChange = { gregorian = it }, label = { Text("التاريخ الميلادي") }, modifier = Modifier.weight(1f))
                }
                OutlinedTextField(value = beneficiary, onValueChange = { beneficiary = it }, label = { Text("اسم المستفيد / مقدم الطلب") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = beneficiaryId, onValueChange = { beneficiaryId = it }, label = { Text("رقم الهوية أو الحساب") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = purpose, onValueChange = { purpose = it }, label = { Text("الغرض والبيان") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
                OutlinedTextField(value = details, onValueChange = { details = it }, label = { Text("التفاصيل") }, modifier = Modifier.fillMaxWidth(), minLines = 4)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("المبلغ بالأرقام") }, modifier = Modifier.weight(1f))
                    OutlinedTextField(value = amountWords, onValueChange = { amountWords = it }, label = { Text("المبلغ كتابةً") }, modifier = Modifier.weight(1f))
                }
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("البند المالي") }, modifier = Modifier.fillMaxWidth())
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = costCenter, onValueChange = { costCenter = it }, label = { Text("مركز التكلفة") }, modifier = Modifier.weight(1f))
                    OutlinedTextField(value = funding, onValueChange = { funding = it }, label = { Text("مصدر التمويل") }, modifier = Modifier.weight(1f))
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = attachments, onValueChange = { attachments = it.filter(Char::isDigit) }, label = { Text("عدد المرفقات") }, modifier = Modifier.weight(1f))
                    OutlinedTextField(value = tags, onValueChange = { tags = it }, label = { Text("الوسوم والتصنيف") }, modifier = Modifier.weight(1f))
                }
                OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("الملاحظات") }, modifier = Modifier.fillMaxWidth(), minLines = 3)
                Button(onClick = {
                    viewModel.updateDocument(current.copy(
                        dateHijri = hijri, dateGregorian = gregorian,
                        beneficiaryName = beneficiary, beneficiaryId = beneficiaryId,
                        purpose = purpose, details = details, notes = notes,
                        amount = amount.toDoubleOrNull(), amountWords = amountWords,
                        financialCategory = category, costCenter = costCenter,
                        fundingSource = funding, tags = tags,
                        attachmentsCount = attachments.toIntOrNull() ?: 0,
                        updatedAt = System.currentTimeMillis()
                    ))
                }, modifier = Modifier.fillMaxWidth()) { Text("حفظ الصفحة ${current.documentNumber}") }

                OutlinedButton(onClick = { showWordEditor = !showWordEditor }, modifier = Modifier.fillMaxWidth()) {
                    Text(if (showWordEditor) "إغلاق محرر المستندات" else "تحرير الصفحة داخل المحرر المدمج — النصوص والمربعات والصور")
                }
                if (showWordEditor) {
                    Card(Modifier.fillMaxWidth().height(680.dp)) {
                        CanvasEditorScreen(viewModel, current.type, onBack = { showWordEditor = false })
                    }
                }
                OutlinedButton(onClick = { showPreview = true }, modifier = Modifier.fillMaxWidth()) { Text("فتح نافذة معاينة الدفتر") }
                OutlinedButton(onClick = {
                    val uri = OfficialDocumentExporter.exportPdf(context, pages)
                    OfficialDocumentExporter.share(context, uri, "مشاركة دفتر المستندات PDF")
                }, modifier = Modifier.fillMaxWidth()) { Text("تصدير ومشاركة الدفتر PDF") }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(enabled = pageIndex > 0, onClick = { pageIndex-- }, modifier = Modifier.weight(1f)) { Text("السابق") }
                Button(enabled = pageIndex < pages.lastIndex, onClick = { pageIndex++ }, modifier = Modifier.weight(1f)) { Text("التالي") }
            }
        }
    }
    if (showPreview) {
        AlertDialog(
            onDismissRequest = { showPreview = false },
            title = { Text("معاينة دفتر المستندات") },
            text = { Column(Modifier.verticalScroll(rememberScrollState())) { Text("عدد الصفحات: ${pages.size}"); pages.forEachIndexed { index, doc -> Text("${index + 1}. ${doc.documentNumber} — ${doc.beneficiaryName.orEmpty().ifBlank { "غير مكتمل" }}") } } },
            confirmButton = { Button(onClick = { showPreview = false; onPreview(pages.joinToString(",") { it.id.toString() }) }) { Text("فتح المعاينة الكاملة") } },
            dismissButton = { TextButton(onClick = { showPreview = false }) { Text("إغلاق") } }
        )
    }
}

private fun DocumentType.title(): String = when (this) {
    DocumentType.REQUEST -> "طلب تقديم"
    DocumentType.ORDER -> "أمر صرف"
    DocumentType.RECEIPT -> "ورقة استلام"
    else -> name
}
