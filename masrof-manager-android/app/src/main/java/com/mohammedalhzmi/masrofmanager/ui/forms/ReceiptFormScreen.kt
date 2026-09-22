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
    var tags by remember(existing?.id) { mutableStateOf(existing?.tags.orEmpty()) }
    var category by remember(existing?.id) { mutableStateOf(existing?.financialCategory.orEmpty()) }
    var costCenter by remember(existing?.id) { mutableStateOf(existing?.costCenter.orEmpty()) }
    var fundingSource by remember(existing?.id) { mutableStateOf(existing?.fundingSource.orEmpty()) }
    var beneficiaryId by remember(existing?.id) { mutableStateOf(existing?.beneficiaryId.orEmpty()) }
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
        OfficialField(category, "بند الإيراد / الاستلام", { category = it })
        OfficialField(costCenter, "مركز التكلفة", { costCenter = it })
        OfficialField(fundingSource, "مصدر التمويل", { fundingSource = it })
        OfficialField(beneficiaryId, "رقم هوية / حساب المستلم", { beneficiaryId = it })
        OfficialTags(tags, { tags = it })
        SaveOfficialButton(if (existing == null) "حفظ ورقة الاستلام الرسمية" else "حفظ التعديلات") {
            val value = Document(id = existing?.id ?: 0, type = DocumentType.RECEIPT, documentNumber = existing?.documentNumber ?: automaticNumber, dateHijri = hijri, dateGregorian = gregorian, amount = amount, amountWords = amount?.let { NumberToWordsConverter.convert(it) }, beneficiaryName = recipient, purpose = reason, details = source, notes = "أقر باستلام المبلغ كاملًا دون نقص", status = DocumentStatus.RECEIVED, attachmentsCount = existing?.attachmentsCount ?: 0, createdAt = existing?.createdAt ?: System.currentTimeMillis(), isArchived = existing?.isArchived ?: false, archivedAt = existing?.archivedAt, updatedAt = System.currentTimeMillis(), financialCategory = category, costCenter = costCenter, fundingSource = fundingSource, beneficiaryId = beneficiaryId, submittedBy = existing?.submittedBy ?: "", reviewedBy = existing?.reviewedBy ?: "", approvedBy = existing?.approvedBy ?: "", approvedAt = existing?.approvedAt, paidAt = existing?.paidAt, rejectionReason = existing?.rejectionReason ?: "", tags = tags.split(",").map(String::trim).filter(String::isNotBlank).distinct().joinToString(","))
            if (existing == null) viewModel.addDocument(value) else viewModel.updateDocument(value)
            if (existing == null) DocumentNumbering.consume(context, DocumentType.RECEIPT)
            FormMemory.remember(context, "receipt", "recipient", recipient); FormMemory.remember(context, "receipt", "amount", amountText); FormMemory.remember(context, "receipt", "source", source); FormMemory.remember(context, "receipt", "reason", reason)
            onNavigateBack()
        }
    }
}
