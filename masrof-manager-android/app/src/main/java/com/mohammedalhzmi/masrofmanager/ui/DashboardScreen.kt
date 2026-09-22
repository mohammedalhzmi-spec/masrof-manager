package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.compose.animation.AnimatedVisibility
import kotlinx.coroutines.delay
import com.mohammedalhzmi.masrofmanager.util.AppBackupManager
import com.mohammedalhzmi.masrofmanager.util.RolePreferences
import com.mohammedalhzmi.masrofmanager.util.AppPermission
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings

@Composable
fun DashboardScreen(viewModel: MasrofViewModel, onAddDocument: () -> Unit, onPrint: (String) -> Unit, onEdit: (String) -> Unit, onSettings: () -> Unit) {
    val documents by viewModel.allDocuments.collectAsState()
    val archivedDocuments by viewModel.archivedDocuments.collectAsState()
    var selectedIds by remember { mutableStateOf(setOf<Long>()) }
    var query by remember { mutableStateOf("") }
    var showArchive by remember { mutableStateOf(false) }
    var selectedTag by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val role = RolePreferences.currentRole(context)
    val canSettings = RolePreferences.can(context, AppPermission.SETTINGS)
    val canBackup = RolePreferences.can(context, AppPermission.BACKUP)
    val canEdit = RolePreferences.can(context, AppPermission.EDIT)
    val canDelete = RolePreferences.can(context, AppPermission.DELETE)
    val exportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/x-sqlite3")) { uri -> uri?.let { viewModel.exportDatabase(context, it); viewModel.recordAudit("EXPORT_DATABASE", "نسخة DB") } }
    val importLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> uri?.let { viewModel.importDatabase(context, it); viewModel.recordAudit("IMPORT_DATABASE", "استعادة DB") } }
    val zipExportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/zip")) { uri -> uri?.let { viewModel.exportFullBackup(context, it); viewModel.recordAudit("EXPORT_BACKUP", "نسخة ZIP كاملة") } }
    val zipImportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> uri?.let { viewModel.importFullBackup(context, it); viewModel.recordAudit("IMPORT_BACKUP", "استعادة ZIP كاملة") } }
    var showDeveloperNotice by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) { delay(4500); showDeveloperNotice = false }

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) { Text("نظام المالية لصندوق النظافة الحزم", style = MaterialTheme.typography.headlineMedium, maxLines = 2, overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis); Text("الدور الحالي: ${role.title}", style = MaterialTheme.typography.bodySmall) }
            if (canSettings) IconButton(onClick = onSettings) { Icon(Icons.Default.Settings, contentDescription = "الإعدادات") }
        }
        Spacer(modifier = Modifier.height(12.dp))
        AnimatedVisibility(visible = showDeveloperNotice) { Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer), modifier = Modifier.fillMaxWidth()) { Text("هذا التطبيق من برمجة وتطوير المطور محمد الحزمي\nجميع الحقوق محفوظة للمطور 2026", modifier = Modifier.padding(12.dp)) } }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onAddDocument, modifier = Modifier.fillMaxWidth()) { Text("إضافة مستند جديد") }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = query, onValueChange = { query = it }, modifier = Modifier.fillMaxWidth(), singleLine = true, label = { Text("بحث بالنوع أو التاريخ أو الرقم أو اسم المستفيد") })
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = !showArchive, onClick = { showArchive = false; selectedIds = emptySet() }, label = { Text("المستندات الحالية (${documents.size})") })
            FilterChip(selected = showArchive, onClick = { showArchive = true; selectedIds = emptySet() }, label = { Text("الأرشيف (${archivedDocuments.size})") })
        }
        val sourceForTags = if (showArchive) archivedDocuments else documents
        val tagCounts = sourceForTags.flatMap { it.tags.split(",").map(String::trim).filter(String::isNotBlank) }.groupingBy { it }.eachCount()
        val quickTags = listOf("كهرباء", "صيانة", "رواتب", "وقود ومحروقات", "قطع غيار")
        Text("الوسوم والتصنيف", style = MaterialTheme.typography.titleSmall)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            FilterChip(selected = selectedTag == null, onClick = { selectedTag = null }, label = { Text("الكل (${sourceForTags.size})") })
            tagCounts.toList().sortedByDescending { it.second }.forEach { (tag, count) -> FilterChip(selected = selectedTag == tag, onClick = { selectedTag = if (selectedTag == tag) null else tag }, label = { Text("$tag ($count)") }) }
        }
        var customTag by remember { mutableStateOf("") }
        OutlinedTextField(value = customTag, onValueChange = { value -> if (value.endsWith("\n")) { val tag = value.trim(); if (tag.isNotBlank()) selectedTag = tag; customTag = "" } else customTag = value }, modifier = Modifier.fillMaxWidth(), singleLine = true, label = { Text("أدخل وسمًا ثم اضغط Enter") })
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) { quickTags.forEach { tag -> AssistChip(onClick = { selectedTag = tag }, label = { Text(tag) }) } }
        Spacer(modifier = Modifier.height(8.dp))
        if (canBackup) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { exportLauncher.launch("masrof-backup.db") }, modifier = Modifier.weight(1f)) { Text("نسخة DB") }
                OutlinedButton(onClick = { importLauncher.launch(arrayOf("application/x-sqlite3")) }, modifier = Modifier.weight(1f)) { Text("استعادة DB") }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { zipExportLauncher.launch("masrof-full-backup.zip") }, modifier = Modifier.weight(1f)) { Text("حفظ ZIP كامل") }
                OutlinedButton(onClick = { zipImportLauncher.launch(arrayOf("application/zip", "application/octet-stream")) }, modifier = Modifier.weight(1f)) { Text("استيراد ZIP") }
            }
            OutlinedButton(onClick = { AppBackupManager.shareBackup(context); viewModel.recordAudit("SHARE_BACKUP", "مشاركة النسخة الاحتياطية") }, modifier = Modifier.fillMaxWidth()) { Text("مشاركة النسخة الاحتياطية إلى السحابة") }
        }
        Spacer(modifier = Modifier.height(12.dp))
        val sourceDocuments = if (showArchive) archivedDocuments else documents
        val normalizedQuery = query.trim().lowercase()
        val visibleDocuments = sourceDocuments.filter { doc ->
            (selectedTag == null || doc.tags.split(",").map(String::trim).contains(selectedTag)) &&
            (normalizedQuery.isBlank() || listOf(doc.documentNumber, doc.dateHijri, doc.dateGregorian, doc.beneficiaryName.orEmpty(), documentTitle(doc.type), doc.tags).any { it.lowercase().contains(normalizedQuery) })
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            visibleDocuments.forEach { doc ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(selectedIds.contains(doc.id), { checked -> selectedIds = if (checked) selectedIds + doc.id else selectedIds - doc.id })
                        Column(modifier = Modifier.weight(1f)) { Text("${documentTitle(doc.type)} — ${doc.documentNumber}", style = MaterialTheme.typography.titleMedium); Text(doc.beneficiaryName.orEmpty()); if (doc.amount != null) Text("${doc.amount} ريال"); if (doc.tags.isNotBlank()) Text("وسوم: ${doc.tags}", style = MaterialTheme.typography.labelSmall) }
                        if (showArchive) TextButton(onClick = { viewModel.restoreDocument(doc) }) { Text("استعادة") }
                        else if (canEdit) TextButton(onClick = { onEdit("${doc.type.name.lowercase()}:${doc.id}") }) { Text("تعديل") }
                    }
                }
            }
        }
        if (selectedIds.isNotEmpty()) {
            Button(onClick = { viewModel.recordAudit("PRINT_EXPORT", "عدد المستندات: ${selectedIds.size}"); onPrint(selectedIds.joinToString(",")) }, modifier = Modifier.fillMaxWidth()) { Text("تصدير / طباعة المحدد (${selectedIds.size})") }
            if (selectedIds.size == 1 && canDelete) TextButton(onClick = { visibleDocuments.find { it.id in selectedIds }?.let { if (showArchive) viewModel.restoreDocument(it) else viewModel.archiveDocument(it); selectedIds = emptySet() } }, modifier = Modifier.fillMaxWidth()) { Text(if (showArchive) "استعادة المستند المحدد" else "أرشفة المستند المحدد") }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

private fun documentTitle(type: com.mohammedalhzmi.masrofmanager.data.DocumentType) = when (type) {
    com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST -> "ورقة تقديم طلب"
    com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER -> "أمر صرف"
    com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT -> "ورقة استلام"
}
