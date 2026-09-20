package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel

@Composable
fun ReceiptFormScreen(viewModel: MasrofViewModel, onNavigateBack: () -> Unit) {
    var beneficiaryName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val lastNumber by viewModel.lastDocumentNumber.collectAsState()
    
    Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
        Text(text = "ورقة استلام", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(value = beneficiaryName, onValueChange = { beneficiaryName = it }, label = { Text("اسم المستلم") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("المبلغ المستلم") }, modifier = Modifier.fillMaxWidth())
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val doc = Document(
                type = DocumentType.RECEIPT,
                documentNumber = ((lastNumber ?: 0) + 1).toString().padStart(4, '0'),
                dateHijri = "", dateGregorian = "",
                amount = amount.toDoubleOrNull(), amountWords = null,
                beneficiaryName = beneficiaryName,
                purpose = null,
                details = null, notes = null,
                status = DocumentStatus.SUBMITTED
            )
            viewModel.addDocument(doc)
            onNavigateBack()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("حفظ ورقة الاستلام")
        }
    }
}
