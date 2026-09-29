package com.mohammedalhzmi.masrofmanager.ui.forms

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.ui.MasrofViewModel
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering
import com.mohammedalhzmi.masrofmanager.util.ExpenseBookData

@Composable
fun ExpenseReportFormScreen(
    viewModel: MasrofViewModel,
    onNavigateBack: () -> Unit,
    existing: Document? = null
) {
    val context = LocalContext.current
    val initial = remember(existing?.id, existing?.details) {
        if (existing == null) ExpenseBookData.newBook() else {
            val decoded = ExpenseBookData.decode(existing.details)
            if (!ExpenseBookData.hasEncodedData(existing.details) && !decoded.hasChapterTotals) {
                decoded.copy(legacyGeneralTotal = existing.amount?.toLong()?.coerceAtLeast(0) ?: 0)
            } else decoded
        }
    }
    var month by remember(existing?.id) { mutableStateOf(existing?.purpose.orEmpty()) }
    var hijriYear by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorianYear by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    var chapterOne by remember(existing?.id, existing?.details) { mutableStateOf(initial.chapterOneTotal.takeIf { it > 0 }?.toString().orEmpty()) }
    var chapterTwo by remember(existing?.id, existing?.details) { mutableStateOf(initial.chapterTwoTotal.takeIf { it > 0 }?.toString().orEmpty()) }
    var chapterThree by remember(existing?.id, existing?.details) { mutableStateOf(initial.chapterThreeTotal.takeIf { it > 0 }?.toString().orEmpty()) }
    var debtFebruary by remember(existing?.id, existing?.details) { mutableStateOf(initial.debtFebruary.takeIf { it > 0 }?.toString().orEmpty()) }
    var debtMarch by remember(existing?.id, existing?.details) { mutableStateOf(initial.debtMarch.takeIf { it > 0 }?.toString().orEmpty()) }
    var debtPrevious by remember(existing?.id, existing?.details) { mutableStateOf(initial.debtPrevious.takeIf { it > 0 }?.toString().orEmpty()) }
    val generatedNumber = DocumentNumbering.next(context, DocumentType.EXPENSE_STATEMENT).toString().padStart(4, '0')
    val displayNumber = existing?.documentNumber ?: generatedNumber
    val book = ExpenseBookData(
        chapterOneTotal = ExpenseBookData.parseAmount(chapterOne),
        chapterTwoTotal = ExpenseBookData.parseAmount(chapterTwo),
        chapterThreeTotal = ExpenseBookData.parseAmount(chapterThree),
        debtFebruary = ExpenseBookData.parseAmount(debtFebruary),
        debtMarch = ExpenseBookData.parseAmount(debtMarch),
        debtPrevious = ExpenseBookData.parseAmount(debtPrevious),
        legacyGeneralTotal = if (chapterOne.isBlank() && chapterTwo.isBlank() && chapterThree.isBlank()) initial.legacyGeneralTotal else 0,
        legacyDetails = initial.legacyDetails
    )

    OfficialFormShell(
        if (existing == null) "دفتر المصروفات الشهري الجديد" else "تعديل دفتر المصروفات الشهري"
    ) {
        Text(
            "دفتر رسمي من أربعة أبواب للطباعة (الصفحات 3–6). أدخل إجمالي كل باب؛ يحسب النظام الإجمالي العام للأبواب الثلاثة الأولى، ويضيف الزيادة فوق 952,000 ريال كمديونية باسم شهر التقرير.",
            style = MaterialTheme.typography.bodySmall
        )
        OfficialField(displayNumber, "رقم الدفتر في النظام", {})
        OfficialField(month, "شهر التقرير (يظهر في صف الزيادة إن وجدت)", { month = it })
        OfficialField(hijriYear, "السنة الهجرية", { hijriYear = it })
        OfficialField(gregorianYear, "السنة الميلادية", { gregorianYear = it })

        Text("إجماليات الأبواب الثلاثة الأولى", style = MaterialTheme.typography.titleMedium)
        if (!book.hasChapterTotals && book.legacyGeneralTotal > 0) {
            Text("حُفظ إجمالي المستند القديم كما هو (${ExpenseBookData.formatAmount(book.legacyGeneralTotal)} ريال) لأن تفاصيل الأبواب لم تكن مسجلة. أدخل إجماليات الأبواب لاستبدال هذا الإجمالي المحفوظ بالحساب الجديد.", style = MaterialTheme.typography.bodySmall)
        }
        OfficialField(chapterOne, "إجمالي الباب الأول — المصروفات التشغيلية (ريال)", { chapterOne = ExpenseBookData.normalizeAmountInput(it) }, keyboardType = KeyboardType.Number)
        OfficialField(chapterTwo, "إجمالي الباب الثاني — الأجور والمستحقات (ريال)", { chapterTwo = ExpenseBookData.normalizeAmountInput(it) }, keyboardType = KeyboardType.Number)
        OfficialField(chapterThree, "إجمالي الباب الثالث — المصروفات الأخرى (ريال)", { chapterThree = ExpenseBookData.normalizeAmountInput(it) }, keyboardType = KeyboardType.Number)
        Text(
            if (book.hasChapterTotals || book.legacyGeneralTotal == 0L) "الإجمالي العام المحسوب للأبواب 1–3: ${ExpenseBookData.formatAmount(book.generalTotal)} ريال"
            else "الإجمالي السابق المحفوظ: ${ExpenseBookData.formatAmount(book.generalTotal)} ريال",
            style = MaterialTheme.typography.titleSmall
        )
        Text("حد المصروفات الثابت: ${ExpenseBookData.formatAmount(ExpenseBookData.FIXED_GENERAL_TOTAL)} ريال", style = MaterialTheme.typography.bodyMedium)
        Text(
            "الزيادة التي ستظهر في الباب الرابع لشهر ${month.ifBlank { "التقرير" }}: ${ExpenseBookData.formatAmount(book.excessDebt)} ريال",
            style = MaterialTheme.typography.bodyMedium,
            color = if (book.excessDebt > 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
        )

        Text("الباب الرابع — المديونية كما في النموذج", style = MaterialTheme.typography.titleMedium)
        Text("يمكن تعديل المبالغ المرجعية؛ ويضاف صف مستقل تلقائيًا عند تجاوز الإجمالي الثابت.", style = MaterialTheme.typography.bodySmall)
        OfficialField(debtFebruary, "رواتب متأخرة — شهر فبراير (ريال)", { debtFebruary = ExpenseBookData.normalizeAmountInput(it) }, keyboardType = KeyboardType.Number)
        OfficialField(debtMarch, "رواتب متأخرة — شهر مارس (ريال)", { debtMarch = ExpenseBookData.normalizeAmountInput(it) }, keyboardType = KeyboardType.Number)
        OfficialField(debtPrevious, "رواتب متأخرة — أخرى ماضية (ريال)", { debtPrevious = ExpenseBookData.normalizeAmountInput(it) }, keyboardType = KeyboardType.Number)
        Text("إجمالي صفوف المديونية: ${ExpenseBookData.formatAmount(book.debtTotal)} ريال", style = MaterialTheme.typography.titleSmall)

        SaveOfficialButton(if (existing == null) "حفظ دفتر المصروفات" else "حفظ التعديلات") {
            val savedBook = book.encode()
            val report = Document(
                id = existing?.id ?: 0,
                type = if (existing?.type == DocumentType.EXPENSE_REPORT) DocumentType.EXPENSE_REPORT else DocumentType.EXPENSE_STATEMENT,
                documentNumber = existing?.documentNumber ?: generatedNumber,
                dateHijri = hijriYear,
                dateGregorian = gregorianYear,
                amount = book.generalTotal.toDouble(),
                amountWords = existing?.amountWords,
                beneficiaryName = existing?.beneficiaryName,
                purpose = month,
                details = savedBook,
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
                rejectionReason = existing?.rejectionReason.orEmpty(),
                cloudId = existing?.cloudId.orEmpty()
            )
            if (existing == null) {
                viewModel.addDocument(report)
                DocumentNumbering.consume(context, DocumentType.EXPENSE_STATEMENT)
            } else {
                viewModel.updateDocument(report)
            }
            onNavigateBack()
        }
    }
}
