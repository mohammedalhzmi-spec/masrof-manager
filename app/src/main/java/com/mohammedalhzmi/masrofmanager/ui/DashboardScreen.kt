package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
fun DashboardScreen(viewModel: MasrofViewModel, onAddDocument: () -> Unit, onPrint: (String) -> Unit, onEdit: (String) -> Unit, onSettings: () -> Unit) {
    val documents by viewModel.allDocuments.collectAsState()
    var selectedIds by remember { mutableStateOf(setOf<Long>()) }
    val context = LocalContext.current
    val role = RolePreferences.currentRole(context)
    val canSettings = RolePreferences.can(context, AppPermission.SETTINGS)
    val canBackup = RolePreferences.can(context, AppPermission.BACKUP)
    val canEdit = RolePreferences.can(context, AppPermission.EDIT)
    val canDelete = RolePreferences.can(context, AppPermission.DELETE)
    val exportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/x-sqlite3")) { uri -> uri?.let { viewModel.exportDatabase(context, it) } }
    val importLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> uri?.let { viewModel.importDatabase(context, it) } }
    val zipExportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/zip")) { uri -> uri?.let { viewModel.exportFullBackup(context, it) } }
    val zipImportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> uri?.let { viewModel.importFullBackup(context, it) } }
    var showDeveloperNotice by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) { delay(4500); showDeveloperNotice = false }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column { Text("نظام مالية صندوق النظافة", style = MaterialTheme.typography.headlineMedium); Text("الدور الحالي: ${role.title}", style = MaterialTheme.typography.bodySmall) }
            if (canSettings) TextButton(onClick = onSettings) { Text("الإعدادات") }
        }
        Spacer(modifier = Modifier.height(12.dp))
        AnimatedVisibility(visible = showDeveloperNotice) { Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer), modifier = Modifier.fillMaxWidth()) { Text("هذا التطبيق من برمجة وتطوير المطور محمد الحزمي\nجميع الحقوق محفوظة للمطور 2026", modifier = Modifier.padding(12.dp)) } }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onAddDocument, modifier = Modifier.fillMaxWidth()) { Text("إضافة مستند جديد") }
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
            OutlinedButton(onClick = { AppBackupManager.shareBackup(context) }, modifier = Modifier.fillMaxWidth()) { Text("مشاركة النسخة الاحتياطية إلى السحابة") }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(documents, key = { it.id }) { doc ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(selectedIds.contains(doc.id), { checked -> selectedIds = if (checked) selectedIds + doc.id else selectedIds - doc.id })
                        Column(modifier = Modifier.weight(1f)) { Text("${documentTitle(doc.type)} — ${doc.documentNumber}", style = MaterialTheme.typography.titleMedium); Text(doc.beneficiaryName.orEmpty()); if (doc.amount != null) Text("${doc.amount} ريال") }
                        if (canEdit) TextButton(onClick = { onEdit("${doc.type.name.lowercase()}:${doc.id}") }) { Text("تعديل") }
                    }
                }
            }
        }
        if (selectedIds.isNotEmpty()) {
            Button(onClick = { onPrint(selectedIds.joinToString(",")) }, modifier = Modifier.fillMaxWidth()) { Text("تصدير / طباعة المحدد (${selectedIds.size})") }
            if (selectedIds.size == 1 && canDelete) TextButton(onClick = { documents.find { it.id in selectedIds }?.let { viewModel.deleteDocument(it); selectedIds = emptySet() } }, modifier = Modifier.fillMaxWidth()) { Text("حذف المستند المحدد") }
        }
    }
}

private fun documentTitle(type: com.mohammedalhzmi.masrofmanager.data.DocumentType) = when (type) {
    com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST -> "ورقة تقديم طلب"
    com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER -> "أمر صرف"
    com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT -> "ورقة استلام"
}
