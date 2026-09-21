package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel
import com.mohammedalhzmi.masrofmanager.util.FormMemory
import com.mohammedalhzmi.masrofmanager.util.NumberToWordsConverter
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering

@Composable
fun ReceiptFormScreen(viewModel: MasrofViewModel, onNavigateBack: () -> Unit, existing: Document? = null) {
    val context = LocalContext.current
    var recipient by remember(existing?.id) { mutableStateOf(existing?.beneficiaryName ?: FormMemory.read(context, "receipt", "recipient")) }
    var amountText by remember(existing?.id) { mutableStateOf(existing?.amount?.toString() ?: FormMemory.read(context, "receipt", "amount")) }
    var source by remember(existing?.id) { mutableStateOf(existing?.details ?: FormMemory.read(context, "receipt", "source", "فرع صندوق النظافة والتحسين")) }
    var reason by remember(existing?.id) { mutableStateOf(existing?.purpose ?: FormMemory.read(context, "receipt", "reason")) }
    var hijri by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorian by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    val automaticNumber = DocumentNumbering.next(context, DocumentType.RECEIPT).toString().padStart(4, '0')
    val amount = amountText.toDoubleOrNull()
    OfficialFormShell(if (existing == null) "ورقة استلام جديدة" else "تعديل ورقة استلام") {
        OfficialDates(hijri, gregorian, { hijri = it }, { gregorian = it })
        OfficialField(if (existing == null) automaticNumber else existing.documentNumber, "رقم ورقة الاستلام (تلقائي)", { })
        OfficialField(recipient, "اسم المستلم", { recipient = it })
        OfficialField(amountText, "المبلغ بالأرقام (ريال يمني)", { amountText = it })
        OfficialField(amount?.let { NumberToWordsConverter.convert(it) } ?: "سيظهر المبلغ كتابةً هنا", "المبلغ كتابةً", { })
        OfficialField(source, "مصدر المبلغ", { source = it })
        OfficialField(reason, "وذلك مقابل", { reason = it }, 3)
        SaveOfficialButton(if (existing == null) "حفظ ورقة الاستلام الرسمية" else "حفظ التعديلات") {
            val value = Document(existing?.id ?: 0, DocumentType.RECEIPT, existing?.documentNumber ?: automaticNumber, hijri, gregorian, amount, amount?.let { NumberToWordsConverter.convert(it) }, recipient, reason, source, "أقر باستلام المبلغ كاملًا دون نقص", DocumentStatus.RECEIVED, existing?.attachmentsCount ?: 0, existing?.createdAt ?: System.currentTimeMillis())
            if (existing == null) viewModel.addDocument(value) else viewModel.updateDocument(value)
            if (existing == null) DocumentNumbering.consume(context, DocumentType.RECEIPT)
            FormMemory.remember(context, "receipt", "recipient", recipient); FormMemory.remember(context, "receipt", "amount", amountText); FormMemory.remember(context, "receipt", "source", source); FormMemory.remember(context, "receipt", "reason", reason)
            onNavigateBack()
        }
    }
}
