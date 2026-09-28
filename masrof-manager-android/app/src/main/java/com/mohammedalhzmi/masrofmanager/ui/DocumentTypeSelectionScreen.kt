package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.displayName
import com.mohammedalhzmi.masrofmanager.data.userSelectableDocumentTypes

@Composable
fun DocumentTypeSelectionScreen(
    allowedTypes: Set<DocumentType> = userSelectableDocumentTypes().toSet(),
    onTypeSelected: (DocumentType) -> Unit
) {
    val visibleTypes = userSelectableDocumentTypes().filter { it in allowedTypes }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)
    ) {
        Text("اختر نوع المستند الجديد", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        visibleTypes.forEach { type ->
            Button(onClick = { onTypeSelected(type) }, modifier = Modifier.fillMaxWidth()) {
                Text(type.displayName())
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        if (visibleTypes.isEmpty()) Text("لا توجد أنواع مستندات متاحة لهذا المستخدم.")
    }
}
