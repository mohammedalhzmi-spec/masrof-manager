package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.DocumentType

@Composable
fun DocumentTypeSelectionScreen(allowedTypes: Set<DocumentType> = DocumentType.values().toSet(), onTypeSelected: (DocumentType) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "اختر نوع المستند الجديد", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        if (DocumentType.REQUEST in allowedTypes) Button(onClick = { onTypeSelected(DocumentType.REQUEST) }, modifier = Modifier.fillMaxWidth()) {
            Text("ورقة تقديم طلب")
        }
        Spacer(modifier = Modifier.height(12.dp))
        if (DocumentType.ORDER in allowedTypes) Button(onClick = { onTypeSelected(DocumentType.ORDER) }, modifier = Modifier.fillMaxWidth()) {
            Text("أمر صرف")
        }
        Spacer(modifier = Modifier.height(12.dp))
        if (DocumentType.RECEIPT in allowedTypes) Button(onClick = { onTypeSelected(DocumentType.RECEIPT) }, modifier = Modifier.fillMaxWidth()) {
            Text("ورقة استلام")
        }
        Spacer(modifier = Modifier.height(12.dp))
        if (DocumentType.VIOLATION_REPORT in allowedTypes) Button(onClick = { onTypeSelected(DocumentType.VIOLATION_REPORT) }, modifier = Modifier.fillMaxWidth()) {
            Text("محضر ضبط وقوع مخالفة")
        }
        Spacer(modifier = Modifier.height(12.dp))
        if (DocumentType.EXPENSE_REPORT in allowedTypes) Button(onClick = { onTypeSelected(DocumentType.EXPENSE_REPORT) }, modifier = Modifier.fillMaxWidth()) {
            Text("كشف المصروفات الشهرية (3 صفحات)")
        }
    }
}
