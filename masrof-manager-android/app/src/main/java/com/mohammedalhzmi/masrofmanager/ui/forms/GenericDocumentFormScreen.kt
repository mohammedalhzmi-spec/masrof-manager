package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.displayName
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering
import com.mohammedalhzmi.masrofmanager.util.NumberToWordsConverter

@Composable
fun GenericDocumentFormScreen(
    viewModel: MasrofViewModel,
    type: DocumentType,
    onNavigateBack: () -> Unit,
    existing: Document? = null
) {
    val context = LocalContext.current
    var hijri by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorian by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    var beneficiary by remember(existing?.id) { mutableStateOf(existing?.beneficiaryName.orEmpty()) }
    var amountText by remember(existing?.id) { mutableStateOf(existing?.amount?.toString().orEmpty()) }
    var amountWords by remember(existing?.id) { mutableStateOf(existing?.amountWords.orEmpty()) }
    var purpose by remember(existing?.id) { mutableStateOf(existing?.purpose.orEmpty()) }
    var details by remember(existing?.id) { mutableStateOf(existing?.details.orEmpty()) }
    var notes by remember(existing?.id) { mutableStateOf(existing?.notes.orEmpty()) }
    var attachmentsCount by remember(existing?.id) { mutableStateOf(existing?.attachmentsCount?.toString().orEmpty()) }
    var category by remember(existing?.id) { mutableStateOf(existing?.financialCategory.orEmpty()) }
    var costCenter by remember(existing?.id) { mutableStateOf(existing?.costCenter.orEmpty()) }
    var fundingSource by remember(existing?.id) { mutableStateOf(existing?.fundingSource.orEmpty()) }
    var tags by remember(existing?.id) { mutableStateOf(existing?.tags.orEmpty()) }
    val generatedNumber = DocumentNumbering.next(context, type).toString().padStart(4, '0')
    val amount = amountText.toDoubleOrNull()
    val resolvedAmountWords = amountWords.ifBlank { amount?.let(NumberToWordsConverter::convert).orEmpty() }

    OfficialFormShell(if (existing == null) "مستند جديد: ${type.displayName()}" else "تعديل: ${type.displayName()}") {
        OfficialDates(hijri, gregorian, { hijri = it }, { gregorian = it })
        OfficialField(existing?.documentNumber ?: generatedNumber, "رقم المستند", {})
        OfficialField(beneficiary, "الاسم / الجهة / المستفيد", { beneficiary = it })
        OfficialField(amountText, "المبلغ بالأرقام (ريال يمني)", { amountText = it })
        OfficialField(resolvedAmountWords, "المبلغ كتابةً", { amountWords = it })
        OfficialField(purpose, "الموضوع / الغرض", { purpose = it }, 2)
        OfficialField(details, "التفاصيل", { details = it }, 5)
        OfficialField(notes, "ملاحظات / المرفقات", { notes = it }, 2)
        OfficialField(attachmentsCount, "عدد المرفقات", { attachmentsCount = it.filter(Char::isDigit) })
        OfficialField(category, "البند المالي", { category = it })
        OfficialField(costCenter, "مركز التكلفة", { costCenter = it })
        OfficialField(fundingSource, "مصدر التمويل", { fundingSource = it })
        OfficialTags(tags, { tags = it })
        SaveOfficialButton(if (existing == null) "حفظ ${type.displayName()}" else "حفظ التعديلات") {
            val base = existing ?: Document(
                type = type,
                documentNumber = generatedNumber,
                dateHijri = "",
                dateGregorian = "",
                amount = null,
                amountWords = null,
                beneficiaryName = "",
                purpose = "",
                details = "",
                notes = "",
                status = DocumentStatus.SUBMITTED
            )
            val value = base.copy(
                type = type,
                documentNumber = existing?.documentNumber ?: generatedNumber,
                dateHijri = hijri,
                dateGregorian = gregorian,
                amount = amount,
                amountWords = resolvedAmountWords.takeIf(String::isNotBlank),
                beneficiaryName = beneficiary,
                purpose = purpose,
                details = details,
                notes = notes.ifBlank { null },
                status = existing?.status ?: DocumentStatus.SUBMITTED,
                attachmentsCount = attachmentsCount.toIntOrNull() ?: 0,
                updatedAt = System.currentTimeMillis(),
                financialCategory = category,
                costCenter = costCenter,
                fundingSource = fundingSource,
                tags = tags.split(",").map(String::trim).filter(String::isNotBlank).distinct().joinToString(",")
            )
            if (existing == null) {
                viewModel.addDocument(value)
                DocumentNumbering.consume(context, type)
            } else {
                viewModel.updateDocument(value)
            }
            onNavigateBack()
        }
    }
}
