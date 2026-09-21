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
fun PaymentOrderFormScreen(viewModel: MasrofViewModel, onNavigateBack: () -> Unit, existing: Document? = null) {
    val context = LocalContext.current
    var beneficiary by remember(existing?.id) { mutableStateOf(existing?.beneficiaryName ?: FormMemory.read(context, "order", "beneficiary")) }
    var amountText by remember(existing?.id) { mutableStateOf(existing?.amount?.toString() ?: FormMemory.read(context, "order", "amount")) }
    var purpose by remember(existing?.id) { mutableStateOf(existing?.purpose ?: FormMemory.read(context, "order", "purpose")) }
    var hijri by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorian by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    var attachments by remember(existing?.id) { mutableStateOf(existing?.notes.orEmpty()) }
    val automaticNumber = DocumentNumbering.next(context, DocumentType.ORDER).toString().padStart(4, '0')
    val amount = amountText.toDoubleOrNull()
    OfficialFormShell(if (existing == null) "أمر صرف جديد" else "تعديل أمر صرف") {
        OfficialDates(hijri, gregorian, { hijri = it }, { gregorian = it })
        OfficialField(if (existing == null) automaticNumber else existing.documentNumber, "رقم أمر الصرف (تلقائي)", { })
        OfficialField(beneficiary, "الأخ / المستفيد", { beneficiary = it })
        OfficialField(amountText, "المبلغ بالأرقام (ريال يمني)", { amountText = it })
        OfficialField(amount?.let { NumberToWordsConverter.convert(it) } ?: "سيظهر المبلغ كتابةً هنا", "المبلغ كتابةً", { })
        OfficialField(purpose, "وذلك مقابل", { purpose = it }, 3)
        OfficialField(attachments, "المرفقات", { attachments = it })
        SaveOfficialButton(if (existing == null) "حفظ أمر الصرف الرسمي" else "حفظ التعديلات") {
            val value = Document(existing?.id ?: 0, DocumentType.ORDER, existing?.documentNumber ?: automaticNumber, hijri, gregorian, amount, amount?.let { NumberToWordsConverter.convert(it) }, beneficiary, purpose, null, attachments.ifBlank { null }, DocumentStatus.SUBMITTED, existing?.attachmentsCount ?: 0, existing?.createdAt ?: System.currentTimeMillis())
            if (existing == null) viewModel.addDocument(value) else viewModel.updateDocument(value)
            if (existing == null) DocumentNumbering.consume(context, DocumentType.ORDER)
            FormMemory.remember(context, "order", "beneficiary", beneficiary); FormMemory.remember(context, "order", "amount", amountText); FormMemory.remember(context, "order", "purpose", purpose)
            onNavigateBack()
        }
    }
}
