package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.ui.components.PrintFooter
import android.print.PrintManager
import android.content.Context

@Composable
fun PrintPreviewScreen(viewModel: MasrofViewModel, documentIds: String, onNavigateBack: () -> Unit) {
    val documents by viewModel.allDocuments.collectAsState()
    val idList = documentIds.split(",").mapNotNull { it.toLongOrNull() }
    val selectedDocs = documents.filter { idList.contains(it.id) }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "معاينة الطباعة", style = MaterialTheme.typography.headlineMedium)
        
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(selectedDocs) { doc ->
                Card(modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "مستند: ${doc.documentNumber}", style = MaterialTheme.typography.titleMedium)
                        Text(text = "المستفيد: ${doc.beneficiaryName ?: ""}")
                        Text(text = "المبلغ: ${doc.amount ?: 0}")
                    }
                }
            }
            item {
                PrintFooter()
            }
        }
        
        Button(
            onClick = {
                val printManager = context.getSystemService(Context.PRINT_SERVICE) as PrintManager
                // TODO: Actual implementation of PrintDocumentAdapter to convert content to PDF
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("طباعة")
        }
    }
}
