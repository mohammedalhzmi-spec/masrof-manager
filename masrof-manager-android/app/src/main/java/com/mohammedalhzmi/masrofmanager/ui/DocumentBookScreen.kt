package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentBookScreen(viewModel: MasrofViewModel, onNavigateBack: () -> Unit) {
    var type by remember { mutableStateOf(DocumentType.ORDER) }
    var expanded by remember { mutableStateOf(false) }
    var countText by remember { mutableStateOf("10") }
    var startText by remember { mutableStateOf("") }
    var bookName by remember { mutableStateOf("دفتر مستندات") }
    var message by remember { mutableStateOf("") }
    val context = androidx.compose.ui.platform.LocalContext.current
    val defaultStart = DocumentNumbering.next(context, type)

    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("إنشاء دفتر مستندات مرقّم", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)
        Text("ينشئ صفحات محفوظة محليًا. افتح أي صفحة من القائمة لتعبئة بياناتها، ثم حدد صفحات الدفتر لتصديرها أو طباعتها.", style = androidx.compose.material3.MaterialTheme.typography.bodySmall)
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(value = typeTitle(type), onValueChange = {}, readOnly = true, label = { Text("نوع الدفتر") }, trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) }, modifier = Modifier.fillMaxWidth().menuAnchor())
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DocumentType.values().forEach { option -> DropdownMenuItem(text = { Text(typeTitle(option)) }, onClick = { type = option; expanded = false }) }
            }
        }
        OutlinedTextField(bookName, { bookName = it }, label = { Text("اسم الدفتر / المرجع") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(countText, { countText = it.filter(Char::isDigit).take(3) }, label = { Text("عدد الصفحات") }, modifier = Modifier.weight(1f), singleLine = true)
            OutlinedTextField(startText, { startText = it.filter(Char::isDigit).take(9) }, label = { Text("يبدأ من (فارغ = تلقائي)") }, modifier = Modifier.weight(1f), singleLine = true)
        }
        Text("الرقم التلقائي الحالي لهذا النوع: ${defaultStart.toString().padStart(4, '0')}", style = androidx.compose.material3.MaterialTheme.typography.labelSmall)
        Button(onClick = {
            val count = countText.toIntOrNull()?.coerceIn(1, 200) ?: 10
            val start = startText.toIntOrNull()?.coerceAtLeast(1) ?: defaultStart
            val tag = "دفتر:${bookName.trim().ifBlank { "دفتر مستندات" }}"
            repeat(count) { index ->
                val number = (start + index).toString().padStart(4, '0')
                viewModel.addDocument(Document(type = type, documentNumber = number, dateHijri = "", dateGregorian = "", amount = null, amountWords = null, beneficiaryName = "", purpose = "", details = "", notes = "", status = DocumentStatus.DRAFT, tags = tag))
            }
            DocumentNumbering.setStart(context, type, start + count)
            message = "تم إنشاء $count صفحة مرقمة من ${start.toString().padStart(4, '0')} إلى ${(start + count - 1).toString().padStart(4, '0')}"
        }, modifier = Modifier.fillMaxWidth()) { Text("إنشاء الدفتر وحفظ الصفحات") }
        if (message.isNotBlank()) Text(message, color = androidx.compose.material3.MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(6.dp))
        Button(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) { Text("العودة إلى المستندات لفتح الصفحات") }
    }
}

private fun typeTitle(type: DocumentType) = when (type) {
    DocumentType.ORDER -> "أمر صرف"
    DocumentType.REQUEST -> "ورقة تقديم طلب"
    DocumentType.RECEIPT -> "ورقة استلام"
}
