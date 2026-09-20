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

@Composable
fun DashboardScreen(
    viewModel: MasrofViewModel,
    onAddDocument: () -> Unit,
    onPrint: (String) -> Unit,
    onEdit: (String) -> Unit,
    onSettings: () -> Unit
) {
    val documents by viewModel.allDocuments.collectAsState()
    var selectedIds by remember { mutableStateOf(setOf<Long>()) }
    val context = LocalContext.current
    val exportLauncher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/x-sqlite3")) { uri -> uri?.let { viewModel.exportDatabase(context, it) } }
    val importLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> uri?.let { viewModel.importDatabase(context, it) } }

    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("نظام مالية صندوق النظافة", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = onSettings) { Text("الإعدادات") }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onAddDocument, modifier = Modifier.fillMaxWidth()) { Text("إضافة مستند جديد") }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(onClick = { exportLauncher.launch("masrof-backup.db") }, modifier = Modifier.weight(1f)) { Text("نسخة احتياطية") }
            OutlinedButton(onClick = { importLauncher.launch(arrayOf("application/x-sqlite3")) }, modifier = Modifier.weight(1f)) { Text("استعادة") }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(documents, key = { it.id }) { doc ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(selectedIds.contains(doc.id), { checked -> selectedIds = if (checked) selectedIds + doc.id else selectedIds - doc.id })
                        Column(modifier = Modifier.weight(1f)) {
                            Text("${documentTitle(doc.type)} — ${doc.documentNumber}", style = MaterialTheme.typography.titleMedium)
                            Text(doc.beneficiaryName.orEmpty())
                            if (doc.amount != null) Text("${doc.amount} ريال")
                        }
                        TextButton(onClick = { onEdit("${doc.type.name.lowercase()}:${doc.id}") }) { Text("تعديل") }
                    }
                }
            }
        }
        if (selectedIds.isNotEmpty()) {
            Button(onClick = { onPrint(selectedIds.joinToString(",")) }, modifier = Modifier.fillMaxWidth()) { Text("تصدير / طباعة المحدد (${selectedIds.size})") }
            if (selectedIds.size == 1) {
                TextButton(onClick = { documents.find { it.id in selectedIds }?.let { viewModel.deleteDocument(it); selectedIds = emptySet() } }, modifier = Modifier.fillMaxWidth()) { Text("حذف المستند المحدد") }
            }
        }
    }
}

private fun documentTitle(type: com.mohammedalhzmi.masrofmanager.data.DocumentType) = when (type) {
    com.mohammedalhzmi.masrofmanager.data.DocumentType.REQUEST -> "ورقة تقديم طلب"
    com.mohammedalhzmi.masrofmanager.data.DocumentType.ORDER -> "أمر صرف"
    com.mohammedalhzmi.masrofmanager.data.DocumentType.RECEIPT -> "ورقة استلام"
}
