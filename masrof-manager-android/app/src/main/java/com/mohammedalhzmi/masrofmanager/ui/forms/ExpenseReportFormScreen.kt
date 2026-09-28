package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering

@Composable
fun ExpenseReportFormScreen(
    viewModel: MasrofViewModel,
    onNavigateBack: () -> Unit,
    existing: Document? = null
) {
    val context = LocalContext.current
    var month by remember(existing?.id) { mutableStateOf(existing?.purpose.orEmpty()) }
    var hijriYear by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorianYear by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    val generatedNumber = DocumentNumbering.next(context, DocumentType.EXPENSE_REPORT).toString().padStart(4, '0')
    val displayNumber = existing?.documentNumber ?: generatedNumber

    OfficialFormShell(
        if (existing == null) "كشف المصروفات الشهرية الجديد" else "تعديل كشف المصروفات الشهرية"
    ) {
        Text(
            "قالب رسمي من ثلاث صفحات للطباعة (الصفحات 3–5 في النموذج المرجعي). تُترك خانات الجداول فارغة للتعبئة.",
            style = MaterialTheme.typography.bodySmall
        )
        OfficialField(displayNumber, "رقم المستند في النظام", {})
        OfficialField(month, "شهر التقرير", { month = it })
        OfficialField(hijriYear, "السنة الهجرية", { hijriYear = it })
        OfficialField(gregorianYear, "السنة الميلادية", { gregorianYear = it })
        SaveOfficialButton(if (existing == null) "حفظ كشف المصروفات" else "حفظ التعديلات") {
            val report = Document(
                id = existing?.id ?: 0,
                type = DocumentType.EXPENSE_REPORT,
                documentNumber = existing?.documentNumber ?: generatedNumber,
                dateHijri = hijriYear,
                dateGregorian = gregorianYear,
                amount = null,
                amountWords = null,
                beneficiaryName = existing?.beneficiaryName,
                purpose = month,
                details = existing?.details,
                notes = existing?.notes,
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
                rejectionReason = existing?.rejectionReason.orEmpty()
            )
            if (existing == null) {
                viewModel.addDocument(report)
                DocumentNumbering.consume(context, DocumentType.EXPENSE_REPORT)
            } else {
                viewModel.updateDocument(report)
            }
            onNavigateBack()
        }
    }
}
