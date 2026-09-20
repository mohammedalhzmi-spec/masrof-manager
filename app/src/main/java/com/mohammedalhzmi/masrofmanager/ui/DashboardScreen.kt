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
fun DashboardScreen(viewModel: MasrofViewModel, onAddDocument: () -> Unit, onPrint: (String) -> Unit) {
    val documents by viewModel.allDocuments.collectAsState()
    var selectedIds by remember { mutableStateOf(setOf<Long>()) }
    val context = LocalContext.current

    val exportLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("application/x-sqlite3")
    ) { uri ->
        uri?.let { viewModel.exportDatabase(context, it) }
    }
    
    val importLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri ->
        uri?.let { viewModel.importDatabase(context, it) }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "نظام مالية صندوق النظافة", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onAddDocument, modifier = Modifier.fillMaxWidth()) { Text("إضافة مستند جديد") }
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { exportLauncher.launch("masrof-backup.db") }, modifier = Modifier.weight(1f)) { Text("تصدير نسخة") }
            Button(onClick = { importLauncher.launch(arrayOf("application/x-sqlite3")) }, modifier = Modifier.weight(1f)) { Text("استيراد نسخة") }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(documents) { doc ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = selectedIds.contains(doc.id),
                        onCheckedChange = { checked ->
                            selectedIds = if (checked) selectedIds + doc.id else selectedIds - doc.id
                        }
                    )
                    Text(text = "مستند ${doc.documentNumber} - ${doc.type.name}")
                }
            }
        }

        if (selectedIds.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onPrint(selectedIds.joinToString(",")) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("طباعة المستندات المحددة (${selectedIds.size})")
            }
        }
    }
}
