package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering
import com.mohammedalhzmi.masrofmanager.util.NumberToWordsConverter

@Composable
fun ViolationReportFormScreen(
    viewModel: MasrofViewModel,
    onNavigateBack: () -> Unit,
    existing: Document? = null
) {
    val context = LocalContext.current
    var hijri by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorian by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    var incidentTime by remember(existing?.id) { mutableStateOf(existing?.incidentTime.orEmpty()) }
    var incidentDay by remember(existing?.id) { mutableStateOf(existing?.incidentDay.orEmpty()) }
    var location by remember(existing?.id) { mutableStateOf(existing?.incidentLocation.orEmpty()) }
    var violationType by remember(existing?.id) { mutableStateOf(existing?.violationType.orEmpty()) }
    var offenderName by remember(existing?.id) { mutableStateOf(existing?.beneficiaryName.orEmpty()) }
    var responsibleAction by remember(existing?.id) { mutableStateOf(existing?.responsibleAction.orEmpty()) }
    var details by remember(existing?.id) { mutableStateOf(existing?.details.orEmpty()) }
    var lawArticle by remember(existing?.id) { mutableStateOf(existing?.lawArticle.orEmpty()) }
    var amountText by remember(existing?.id) { mutableStateOf(existing?.amount?.toString().orEmpty()) }
    var witnessOne by remember(existing?.id) { mutableStateOf(existing?.witnessOne.orEmpty()) }
    var witnessTwo by remember(existing?.id) { mutableStateOf(existing?.witnessTwo.orEmpty()) }
    var regionName by remember(existing?.id) { mutableStateOf(existing?.regionName.orEmpty()) }
    var regionOfficer by remember(existing?.id) { mutableStateOf(existing?.regionOfficerName.orEmpty()) }
    var attachments by remember(existing?.id) { mutableStateOf(existing?.notes.orEmpty()) }
    val documentNumber = DocumentNumbering.next(context, DocumentType.VIOLATION_REPORT).toString().padStart(4, '0')
    val amount = amountText.toDoubleOrNull()

    OfficialFormShell(if (existing == null) "محضر ضبط وقوع مخالفة جديد" else "تعديل محضر مخالفة") {
        OfficialDates(hijri, gregorian, { hijri = it }, { gregorian = it })
        OfficialField(if (existing == null) documentNumber else existing.documentNumber, "رقم المستند (يظهر بجوار العنوان)", {})
        OfficialField(incidentTime, "وقت ضبط المخالفة", { incidentTime = it })
        OfficialField(incidentDay, "اليوم", { incidentDay = it })
        OfficialField(location, "موقع المخالفة", { location = it })
        OfficialField(violationType, "نوع المخالفة", { violationType = it })
        OfficialField(offenderName, "اسم المخالف / المنسوب إليه", { offenderName = it })
        OfficialField(details, "وصف المشاهدة وتفاصيل المخالفة", { details = it }, 4)
        OfficialField(responsibleAction, "الفعل المنسوب للمخالف / مسؤوليته", { responsibleAction = it }, 2)
        OfficialField(lawArticle, "رقم المادة القانونية", { lawArticle = it })
        OfficialField(amountText, "مبلغ الغرامة (ريال يمني)", { amountText = it })
        OfficialField(amount?.let { NumberToWordsConverter.convert(it) } ?: "سيظهر المبلغ كتابةً هنا", "المبلغ كتابةً", {})
        OfficialField(witnessOne, "الشاهد الأول", { witnessOne = it })
        OfficialField(witnessTwo, "الشاهد الثاني", { witnessTwo = it })
        OfficialField(regionName, "المنطقة", { regionName = it })
        OfficialField(regionOfficer, "اسم مسؤول المنطقة", { regionOfficer = it })
        OfficialField(attachments, "المرفقات", { attachments = it })
        SaveOfficialButton(if (existing == null) "حفظ محضر المخالفة" else "حفظ التعديلات") {
            val value = Document(
                id = existing?.id ?: 0,
                type = DocumentType.VIOLATION_REPORT,
                documentNumber = existing?.documentNumber ?: documentNumber,
                dateHijri = hijri,
                dateGregorian = gregorian,
                amount = amount,
                amountWords = amount?.let { NumberToWordsConverter.convert(it) },
                beneficiaryName = offenderName,
                purpose = responsibleAction,
                details = details,
                notes = attachments.ifBlank { null },
                status = existing?.status ?: DocumentStatus.SUBMITTED,
                attachmentsCount = existing?.attachmentsCount ?: 0,
                createdAt = existing?.createdAt ?: System.currentTimeMillis(),
                isArchived = existing?.isArchived ?: false,
                archivedAt = existing?.archivedAt,
                updatedAt = System.currentTimeMillis(),
                tags = existing?.tags.orEmpty(),
                financialCategory = existing?.financialCategory.orEmpty(),
                costCenter = existing?.costCenter.orEmpty(),
                fundingSource = existing?.fundingSource.orEmpty(),
                beneficiaryId = existing?.beneficiaryId.orEmpty(),
                submittedBy = existing?.submittedBy.orEmpty(),
                reviewedBy = existing?.reviewedBy.orEmpty(),
                approvedBy = existing?.approvedBy.orEmpty(),
                approvedAt = existing?.approvedAt,
                paidAt = existing?.paidAt,
                rejectionReason = existing?.rejectionReason.orEmpty(),
                incidentTime = incidentTime,
                incidentDay = incidentDay,
                incidentLocation = location,
                violationType = violationType,
                responsibleAction = responsibleAction,
                lawArticle = lawArticle,
                witnessOne = witnessOne,
                witnessTwo = witnessTwo,
                regionName = regionName,
                regionOfficerName = regionOfficer,
                cloudId = existing?.cloudId.orEmpty()
            )
            if (existing == null) {
                viewModel.addDocument(value)
                DocumentNumbering.consume(context, DocumentType.VIOLATION_REPORT)
            } else {
                viewModel.updateDocument(value)
            }
            onNavigateBack()
        }
    }
}
