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
    var tags by remember(existing?.id) { mutableStateOf(existing?.tags.orEmpty()) }
    var category by remember(existing?.id) { mutableStateOf(existing?.financialCategory.orEmpty()) }
    var costCenter by remember(existing?.id) { mutableStateOf(existing?.costCenter.orEmpty()) }
    var fundingSource by remember(existing?.id) { mutableStateOf(existing?.fundingSource.orEmpty()) }
    var beneficiaryId by remember(existing?.id) { mutableStateOf(existing?.beneficiaryId.orEmpty()) }
    val automaticNumber = DocumentNumbering.next(context, DocumentType.ORDER).toString().padStart(4, '0')
    val amount = amountText.toDoubleOrNull()
    OfficialFormShell(if (existing == null) "أمر صرف جديد" else "تعديل أمر صرف") {
        OfficialDates(hijri, gregorian, { hijri = it }, { gregorian = it })
        OfficialField(if (existing == null) automaticNumber else existing.documentNumber, "رقم أمر الصرف (تلقائي)", { })
        OfficialField(beneficiary, "الأخ / المستفيد", { beneficiary = it })
        OfficialField(amountText, "المبلغ بالأرقام (ريال يمني)", { amountText = it })
        OfficialField(amount?.let { NumberToWordsConverter.convert(it) } ?: "سيظهر المبلغ كتابةً هنا", "المبلغ كتابةً", { })
        OfficialField(purpose, "وذلك مقابل", { purpose = it }, 3)
        OfficialField(category, "بند المصروف", { category = it })
        OfficialField(costCenter, "مركز التكلفة", { costCenter = it })
        OfficialField(fundingSource, "مصدر التمويل", { fundingSource = it })
        OfficialField(beneficiaryId, "رقم هوية / حساب المستفيد", { beneficiaryId = it })
        OfficialField(attachments, "المرفقات", { attachments = it })
        OfficialTags(tags, { tags = it })
        SaveOfficialButton(if (existing == null) "حفظ أمر الصرف الرسمي" else "حفظ التعديلات") {
            val value = Document(id = existing?.id ?: 0, type = DocumentType.ORDER, documentNumber = existing?.documentNumber ?: automaticNumber, dateHijri = hijri, dateGregorian = gregorian, amount = amount, amountWords = amount?.let { NumberToWordsConverter.convert(it) }, beneficiaryName = beneficiary, purpose = purpose, details = null, notes = attachments.ifBlank { null }, status = DocumentStatus.SUBMITTED, attachmentsCount = existing?.attachmentsCount ?: 0, createdAt = existing?.createdAt ?: System.currentTimeMillis(), isArchived = existing?.isArchived ?: false, archivedAt = existing?.archivedAt, updatedAt = System.currentTimeMillis(), financialCategory = category, costCenter = costCenter, fundingSource = fundingSource, beneficiaryId = beneficiaryId, submittedBy = existing?.submittedBy ?: "", reviewedBy = existing?.reviewedBy ?: "", approvedBy = existing?.approvedBy ?: "", approvedAt = existing?.approvedAt, paidAt = existing?.paidAt, rejectionReason = existing?.rejectionReason ?: "", tags = tags.split(",").map(String::trim).filter(String::isNotBlank).distinct().joinToString(","))
            if (existing == null) viewModel.addDocument(value) else viewModel.updateDocument(value)
            if (existing == null) DocumentNumbering.consume(context, DocumentType.ORDER)
            FormMemory.remember(context, "order", "beneficiary", beneficiary); FormMemory.remember(context, "order", "amount", amountText); FormMemory.remember(context, "order", "purpose", purpose)
            onNavigateBack()
        }
    }
}
