package com.mohammedalhzmi.masrofmanager.ui

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.displayName
import com.mohammedalhzmi.masrofmanager.util.AppBackupManager
import com.mohammedalhzmi.masrofmanager.util.AppPermission
import com.mohammedalhzmi.masrofmanager.util.BranchAssetData
import com.mohammedalhzmi.masrofmanager.util.OfficialDocumentExporter
import com.mohammedalhzmi.masrofmanager.util.OfficeDocumentRecord
import com.mohammedalhzmi.masrofmanager.util.RolePreferences

@Composable
fun DashboardScreen(
    viewModel: MasrofViewModel,
    onAddDocument: () -> Unit,
    onCreateBook: () -> Unit,
    onBranchAssets: () -> Unit,
    onOfficeEditor: () -> Unit,
    onPrint: (String) -> Unit,
    onEdit: (String) -> Unit,
    onSettings: () -> Unit
) {
    val documents by viewModel.allDocuments.collectAsState()
    val archivedDocuments by viewModel.archivedDocuments.collectAsState()
    var selectedIds by remember { mutableStateOf(setOf<Long>()) }
    var query by remember { mutableStateOf("") }
    var showArchive by remember { mutableStateOf(false) }
    var selectedTag by remember { mutableStateOf<String?>(null) }
    var customTag by remember { mutableStateOf("") }
    val context = LocalContext.current
    val role = RolePreferences.currentRole(context)
    val canSettings = RolePreferences.can(context, AppPermission.SETTINGS)
    val canBackup = RolePreferences.can(context, AppPermission.BACKUP)
    val canEdit = RolePreferences.can(context, AppPermission.EDIT)
    val canDelete = RolePreferences.can(context, AppPermission.DELETE)
    val canApprove = RolePreferences.can(context, AppPermission.APPROVE)
    val listState = rememberLazyListState()
    val exportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/x-sqlite3")) { uri -> uri?.let { viewModel.exportDatabase(context, it); viewModel.recordAudit("EXPORT_DATABASE", "نسخة DB") } }
    val importLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> uri?.let { viewModel.importDatabase(context, it); viewModel.recordAudit("IMPORT_DATABASE", "استعادة DB") } }
    val zipExportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/zip")) { uri -> uri?.let { viewModel.exportFullBackup(context, it); viewModel.recordAudit("EXPORT_BACKUP", "نسخة ZIP كاملة") } }
    val zipImportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> uri?.let { viewModel.importFullBackup(context, it); viewModel.recordAudit("IMPORT_BACKUP", "استعادة ZIP كاملة") } }

    val regularDocuments = remember(documents) { documents.filterNot(BranchAssetData::isRecord) }
    val regularArchivedDocuments = remember(archivedDocuments) { archivedDocuments.filterNot(BranchAssetData::isRecord) }
    val sourceDocuments = if (showArchive) regularArchivedDocuments else regularDocuments
    val normalizedQuery = query.trim().lowercase()
    val visibleDocuments = remember(sourceDocuments, selectedTag, normalizedQuery) {
        sourceDocuments.filter { doc ->
            (selectedTag == null || doc.tags.split(",").map(String::trim).contains(selectedTag)) &&
                (normalizedQuery.isBlank() || listOf(
                    doc.documentNumber, doc.dateHijri, doc.dateGregorian, doc.beneficiaryName.orEmpty(),
                    documentTitle(doc), doc.purpose.orEmpty(), doc.tags
                ).any { it.lowercase().contains(normalizedQuery) })
        }
    }
    val tagCounts = remember(sourceDocuments) {
        sourceDocuments.flatMap { it.tags.split(",").map(String::trim).filter(String::isNotBlank) }.groupingBy { it }.eachCount()
    }
    val quickTags = listOf("كهرباء", "صيانة", "رواتب", "وقود ومحروقات", "قطع غيار")

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color(0xffeef3f5)).padding(horizontal = 16.dp, vertical = 12.dp),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(key = "dashboard_header") {
            Column(Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(R.drawable.official_emblem),
                    contentDescription = "شعار الجمهورية اليمنية",
                    modifier = Modifier.fillMaxWidth().height(84.dp),
                    contentScale = ContentScale.Fit
                )
                Text("الجمهورية اليمنية", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleMedium,
                    color = Color(0xff16486d), textAlign = TextAlign.Center)
                Text("صندوق النظافة والتحسين — فرع مديرية الحزم", modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium, color = Color(0xff198b5b), textAlign = TextAlign.Center)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("نظام المالية لصندوق النظافة الحزم", style = MaterialTheme.typography.headlineMedium,
                            maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Text("الدور الحالي: ${role.title}", style = MaterialTheme.typography.bodySmall)
                    }
                    if (canSettings) IconButton(onClick = onSettings) { Icon(Icons.Default.Settings, contentDescription = "الإعدادات") }
                }
            }
        }
        item(key = "add_document") { Button(onClick = onAddDocument, modifier = Modifier.fillMaxWidth()) { Text("إضافة مستند جديد") } }
        item(key = "create_book") { OutlinedButton(onClick = onCreateBook, modifier = Modifier.fillMaxWidth()) { Text("إنشاء دفتر مستندات مرقّم") } }
        item(key = "branch_assets") { OutlinedButton(onClick = onBranchAssets, modifier = Modifier.fillMaxWidth()) { Text("ممتلكات الفرع") } }
        item(key = "office_editor") { OutlinedButton(onClick = onOfficeEditor, modifier = Modifier.fillMaxWidth()) { Text("محرر وورد") } }
        item(key = "search") {
            OutlinedTextField(value = query, onValueChange = { query = it }, modifier = Modifier.fillMaxWidth(), singleLine = true,
                label = { Text("بحث بالنوع أو التاريخ أو الرقم أو اسم المستفيد") })
        }
        item(key = "filters") {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(selected = !showArchive, onClick = { showArchive = false; selectedIds = emptySet() }, label = { Text("المستندات الحالية (${regularDocuments.size})") })
                FilterChip(selected = showArchive, onClick = { showArchive = true; selectedIds = emptySet() }, label = { Text("الأرشيف (${regularArchivedDocuments.size})") })
            }
        }
        item(key = "tag_title") { Text("الوسوم والتصنيف", style = MaterialTheme.typography.titleSmall) }
        item(key = "tags") {
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                FilterChip(selected = selectedTag == null, onClick = { selectedTag = null }, label = { Text("الكل (${sourceDocuments.size})") })
                tagCounts.toList().sortedByDescending { it.second }.forEach { (tag, count) ->
                    FilterChip(selected = selectedTag == tag, onClick = { selectedTag = if (selectedTag == tag) null else tag }, label = { Text("$tag ($count)") })
                }
            }
        }
        item(key = "custom_tag") {
            OutlinedTextField(
                value = customTag,
                onValueChange = { value ->
                    if (value.endsWith("\n")) {
                        value.trim().takeIf(String::isNotBlank)?.let { selectedTag = it }
                        customTag = ""
                    } else customTag = value
                },
                modifier = Modifier.fillMaxWidth(), singleLine = true, label = { Text("أدخل وسمًا ثم اضغط Enter") }
            )
        }
        item(key = "quick_tags") {
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                quickTags.forEach { tag -> AssistChip(onClick = { selectedTag = tag }, label = { Text(tag) }) }
            }
        }
        item(key = "document_section_title") {
            Text(if (showArchive) "المستندات المؤرشفة" else "المستندات المسجلة", style = MaterialTheme.typography.titleLarge)
        }
        if (visibleDocuments.isEmpty()) {
            item(key = "empty_documents") {
                Card(Modifier.fillMaxWidth()) {
                    Text(if (normalizedQuery.isBlank()) "لا توجد مستندات في هذا القسم بعد." else "لا توجد نتائج مطابقة للبحث.", modifier = Modifier.padding(16.dp))
                }
            }
        }
        items(visibleDocuments, key = { it.id }) { doc ->
            val bookTag = doc.tags.split(",").map(String::trim).firstOrNull { it.startsWith("دفتر:") }
            val exportDocs = if (bookTag == null) listOf(doc) else sourceDocuments.filter { other ->
                other.tags.split(",").map(String::trim).contains(bookTag)
            }
            val isFirstInBook = bookTag != null && sourceDocuments.firstOrNull { other ->
                other.tags.split(",").map(String::trim).contains(bookTag)
            }?.id == doc.id
            DocumentListCard(
                document = doc,
                selected = selectedIds.contains(doc.id),
                showArchive = showArchive,
                canApprove = canApprove,
                canEdit = canEdit,
                canDelete = canDelete,
                onSelected = { checked -> selectedIds = if (checked) selectedIds + doc.id else selectedIds - doc.id },
                onTransition = { target -> viewModel.transitionDocument(doc, target) },
                onRestore = { viewModel.restoreDocument(doc) },
                isOfficeDocument = OfficeDocumentRecord.isOfficeDocument(doc),
                onEdit = { onEdit(if (OfficeDocumentRecord.isOfficeDocument(doc)) "office:${doc.id}" else "${doc.type.name.lowercase()}:${doc.id}") },
                onShare = {
                    val label = if (bookTag == null) "مشاركة المستند PDF" else "مشاركة دفتر المستندات PDF"
                    OfficialDocumentExporter.share(context, OfficialDocumentExporter.exportPdf(context, exportDocs), label)
                },
                onDelete = { viewModel.deleteDocument(doc) },
                onDeleteBook = {
                    bookTag?.let { tag ->
                        sourceDocuments.filter { other -> other.tags.split(",").map(String::trim).contains(tag) }
                            .forEach(viewModel::deleteDocument)
                    }
                },
                showDeleteBook = isFirstInBook
            )
        }
        if (selectedIds.isNotEmpty()) {
            item(key = "selected_actions") {
                Column(Modifier.fillMaxWidth()) {
                    Button(onClick = { viewModel.recordAudit("PRINT_EXPORT", "عدد المستندات: ${selectedIds.size}"); onPrint(selectedIds.joinToString(",")) }, modifier = Modifier.fillMaxWidth()) {
                        Text("تصدير / طباعة المحدد (${selectedIds.size})")
                    }
                    if (selectedIds.size == 1 && canDelete) {
                        TextButton(onClick = {
                            visibleDocuments.find { it.id in selectedIds }?.let {
                                if (showArchive) viewModel.restoreDocument(it) else viewModel.archiveDocument(it)
                                selectedIds = emptySet()
                            }
                        }, modifier = Modifier.fillMaxWidth()) { Text(if (showArchive) "استعادة المستند المحدد" else "أرشفة المستند المحدد") }
                    }
                }
            }
        }
        if (canBackup) {
            item(key = "backup_title") { Text("النسخ الاحتياطي والاستعادة", style = MaterialTheme.typography.titleMedium) }
            item(key = "backup_db") {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { exportLauncher.launch("masrof-backup.db") }, modifier = Modifier.weight(1f)) { Text("نسخة DB") }
                    OutlinedButton(onClick = { importLauncher.launch(arrayOf("application/x-sqlite3")) }, modifier = Modifier.weight(1f)) { Text("استعادة DB") }
                }
            }
            item(key = "backup_zip") {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { zipExportLauncher.launch("masrof-full-backup.zip") }, modifier = Modifier.weight(1f)) { Text("حفظ ZIP كامل") }
                    OutlinedButton(onClick = { zipImportLauncher.launch(arrayOf("application/zip", "application/octet-stream")) }, modifier = Modifier.weight(1f)) { Text("استيراد ZIP") }
                }
            }
            item(key = "backup_share") {
                OutlinedButton(onClick = { AppBackupManager.shareBackup(context); viewModel.recordAudit("SHARE_BACKUP", "مشاركة النسخة الاحتياطية") }, modifier = Modifier.fillMaxWidth()) {
                    Text("مشاركة النسخة الاحتياطية إلى السحابة")
                }
            }
        }
        item(key = "dashboard_bottom_space") { Spacer(Modifier.height(12.dp)) }
    }
}

@Composable
private fun DocumentListCard(
    document: Document,
    selected: Boolean,
    showArchive: Boolean,
    canApprove: Boolean,
    canEdit: Boolean,
    canDelete: Boolean,
    onSelected: (Boolean) -> Unit,
    onTransition: (DocumentStatus) -> Unit,
    onRestore: () -> Unit,
    onEdit: () -> Unit,
    onShare: () -> Unit,
    onDelete: () -> Unit,
    onDeleteBook: () -> Unit,
    showDeleteBook: Boolean,
    isOfficeDocument: Boolean
) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.fillMaxWidth().padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(selected, onSelected)
                Column(Modifier.weight(1f)) {
                    val title = documentTitle(document)
                    val heading = if (isOfficeDocument || document.documentNumber.isBlank()) title else "$title — ${document.documentNumber}"
                    Text(heading, style = MaterialTheme.typography.titleMedium, maxLines = 2, overflow = TextOverflow.Ellipsis)
                    if (!isOfficeDocument) Text("الحالة: ${statusTitle(document.status)}", style = MaterialTheme.typography.labelMedium, color = statusColor(document.status))
                    document.beneficiaryName.orEmpty().takeIf(String::isNotBlank)?.let { Text(it, style = MaterialTheme.typography.bodyMedium, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                }
            }
            if (document.amount != null) Text("${document.amount} ريال")
            if (document.financialCategory.isNotBlank()) Text("البند: ${document.financialCategory} — مركز التكلفة: ${document.costCenter}", style = MaterialTheme.typography.labelSmall)
            if (document.tags.isNotBlank()) Text("وسوم: ${document.tags}", style = MaterialTheme.typography.labelSmall, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Row(Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                if (canApprove && !showArchive && document.status == DocumentStatus.SUBMITTED) TextButton(onClick = { onTransition(DocumentStatus.APPROVED) }) { Text("اعتماد") }
                if (canApprove && !showArchive && document.status == DocumentStatus.APPROVED) TextButton(onClick = { onTransition(DocumentStatus.PAID) }) { Text("تم الصرف") }
                if (canApprove && !showArchive && (document.status == DocumentStatus.SUBMITTED || document.status == DocumentStatus.APPROVED)) TextButton(onClick = { onTransition(DocumentStatus.CANCELLED) }) { Text("إلغاء") }
                if (showArchive) TextButton(onClick = onRestore) { Text("استعادة") }
                else if (canEdit) TextButton(onClick = onEdit) { Text(if (isOfficeDocument) "فتح Office" else "تعديل") }
                if (!isOfficeDocument) TextButton(onClick = onShare) { Text("مشاركة") }
                if (!showArchive && canDelete) {
                    TextButton(onClick = onDelete) { Text("حذف") }
                    if (showDeleteBook) TextButton(onClick = onDeleteBook) { Text("حذف الدفتر") }
                }
            }
        }
    }
}

private fun statusTitle(status: DocumentStatus) = when (status) {
    DocumentStatus.DRAFT -> "مسودة"
    DocumentStatus.SUBMITTED -> "قيد المراجعة"
    DocumentStatus.APPROVED_FINANCE -> "اعتماد المدير المالي"
    DocumentStatus.APPROVED_BRANCH -> "اعتماد مدير الفرع"
    DocumentStatus.APPROVED -> "معتمد"
    DocumentStatus.PAID -> "تم الصرف"
    DocumentStatus.RECEIVED -> "تم الاستلام"
    DocumentStatus.CANCELLED -> "ملغى"
}

private fun statusColor(status: DocumentStatus) = when (status) {
    DocumentStatus.APPROVED_FINANCE, DocumentStatus.APPROVED_BRANCH, DocumentStatus.APPROVED -> Color(0xff18794e)
    DocumentStatus.PAID, DocumentStatus.RECEIVED -> Color(0xff145da0)
    DocumentStatus.CANCELLED -> Color(0xffb42318)
    else -> Color(0xff8a651d)
}

private fun documentTitle(document: Document): String = OfficeDocumentRecord.decode(document)?.let { office ->
    "${office.kind.extensionLabel}: ${office.title}"
} ?: document.type.displayName()
