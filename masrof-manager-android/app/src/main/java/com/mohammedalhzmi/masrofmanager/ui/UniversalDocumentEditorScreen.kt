package com.mohammedalhzmi.masrofmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.displayName
import com.mohammedalhzmi.masrofmanager.data.hasFinancialAmountField
import com.mohammedalhzmi.masrofmanager.util.DocumentNumbering
import com.mohammedalhzmi.masrofmanager.util.NumberToWordsConverter
import com.mohammedalhzmi.masrofmanager.util.OfficialTemplateText
import com.mohammedalhzmi.masrofmanager.util.StructuredDocumentFields

/**
 * Offline-first, in-app editor for a single financial document. Existing records are
 * edited in place using the established Room columns; the four-chapter expense book
 * remains on its dedicated editor.
 */
@Composable
fun UniversalDocumentEditorScreen(
    viewModel: MasrofViewModel,
    type: DocumentType,
    existing: Document? = null,
    onBack: () -> Unit,
    onOpenTemplateDesigner: () -> Unit
) {
    val context = LocalContext.current
    val automaticNumber = remember(type, existing?.id) {
        existing?.documentNumber ?: DocumentNumbering.next(context, type).toString().padStart(4, '0')
    }
    var hijri by remember(existing?.id) { mutableStateOf(existing?.dateHijri.orEmpty()) }
    var gregorian by remember(existing?.id) { mutableStateOf(existing?.dateGregorian.orEmpty()) }
    var beneficiary by remember(existing?.id) { mutableStateOf(existing?.beneficiaryName.orEmpty()) }
    var beneficiaryId by remember(existing?.id) { mutableStateOf(existing?.beneficiaryId.orEmpty()) }
    var amountText by remember(existing?.id) { mutableStateOf(existing?.amount?.toString().orEmpty()) }
    var amountWords by remember(existing?.id) { mutableStateOf(existing?.amountWords.orEmpty()) }
    var purpose by remember(existing?.id) { mutableStateOf(existing?.purpose.orEmpty()) }
    var details by remember(existing?.id) { mutableStateOf(existing?.details.orEmpty()) }
    var notes by remember(existing?.id) { mutableStateOf(existing?.notes.orEmpty()) }
    var templateText by remember(existing?.id) { mutableStateOf(OfficialTemplateText.decode(existing?.notes)) }
    var attachmentsText by remember(existing?.id) { mutableStateOf(existing?.attachmentsCount?.toString().orEmpty()) }
    var category by remember(existing?.id) { mutableStateOf(existing?.financialCategory.orEmpty()) }
    var costCenter by remember(existing?.id) { mutableStateOf(existing?.costCenter.orEmpty()) }
    var funding by remember(existing?.id) { mutableStateOf(existing?.fundingSource.orEmpty()) }
    var tags by remember(existing?.id) { mutableStateOf(existing?.tags.orEmpty()) }
    var incidentTime by remember(existing?.id) { mutableStateOf(existing?.incidentTime.orEmpty()) }
    var incidentDay by remember(existing?.id) { mutableStateOf(existing?.incidentDay.orEmpty()) }
    var incidentLocation by remember(existing?.id) { mutableStateOf(existing?.incidentLocation.orEmpty()) }
    var violationType by remember(existing?.id) { mutableStateOf(existing?.violationType.orEmpty()) }
    var responsibleAction by remember(existing?.id) { mutableStateOf(existing?.responsibleAction.orEmpty()) }
    var lawArticle by remember(existing?.id) { mutableStateOf(existing?.lawArticle.orEmpty()) }
    var witnessOne by remember(existing?.id) { mutableStateOf(existing?.witnessOne.orEmpty()) }
    var witnessTwo by remember(existing?.id) { mutableStateOf(existing?.witnessTwo.orEmpty()) }
    var regionName by remember(existing?.id) { mutableStateOf(existing?.regionName.orEmpty()) }
    var regionOfficer by remember(existing?.id) { mutableStateOf(existing?.regionOfficerName.orEmpty()) }
    var message by remember { mutableStateOf("") }
    var structuredFields by remember(existing?.id) { mutableStateOf(StructuredDocumentFields.decode(existing?.structuredFields)) }

    val beneficiaryLabel = when (type) {
        DocumentType.REQUEST -> "اسم مقدم الطلب"
        DocumentType.RECEIPT -> "اسم المستلم"
        DocumentType.RECEIPT_PAPER -> "اسم دافع المبلغ"
        DocumentType.VIOLATION_REPORT -> "اسم المخالف / المنسوب إليه"
        DocumentType.PURCHASE_ORDER -> "المورد"
        DocumentType.SUPPLY_PERMIT, DocumentType.RECEIPT_MINUTES -> "المورد / الجهة المسلِّمة"
        DocumentType.FINANCIAL_CLAIM -> "صاحب المطالبة / المستفيد"
        DocumentType.CUSTODY_SETTLEMENT -> "مستلم العهدة / أمين العهدة"
        DocumentType.ADVANCE_PERMIT -> "مستلم السلفة"
        DocumentType.FINANCIAL_MEMO, DocumentType.OFFICIAL_FINANCIAL_LETTER -> "الجهة المخاطبة"
        else -> "المستفيد / صاحب العلاقة"
    }
    val purposeLabel = when (type) {
        DocumentType.REQUEST -> "الجهة المخاطبة (تظهر بعد إلى الأخ /)"
        DocumentType.VIOLATION_REPORT -> "وصف الإجراء أو المخالفة"
        DocumentType.RECEIPT, DocumentType.RECEIPT_PAPER -> "مقابل / سبب الاستلام"
        DocumentType.PURCHASE_ORDER -> "الغرض من الشراء"
        DocumentType.SUPPLY_PERMIT -> "جهة التوريد أو الاستلام والغرض"
        DocumentType.RECEIPT_MINUTES -> "موضوع المواد أو الأعمال المستلمة"
        DocumentType.FINANCIAL_CLAIM -> "سبب المطالبة المالية"
        DocumentType.CUSTODY_SETTLEMENT -> "موضوع تسوية العهدة"
        DocumentType.ADVANCE_PERMIT -> "غرض السلفة"
        DocumentType.OFFICIAL_FINANCIAL_LETTER -> "موضوع الخطاب المالي"
        DocumentType.FINANCIAL_MEMO -> "موضوع المذكرة المالية"
        else -> "الغرض / الموضوع"
    }
    val usesAmount = type.hasFinancialAmountField()
    val detailLabel = when (type) {
        DocumentType.PURCHASE_ORDER -> "بيان الأصناف والكميات والشروط"
        DocumentType.SUPPLY_PERMIT -> "الأصناف والكميات وبيانات التوريد / الاستلام"
        DocumentType.RECEIPT_MINUTES -> "تفاصيل المواد أو الأعمال المستلمة ومحضر الاستلام"
        DocumentType.FINANCIAL_CLAIM -> "تفاصيل المطالبة والمستندات المؤيدة"
        DocumentType.CUSTODY_SETTLEMENT -> "تفاصيل العهدة والمصروفات والتسوية"
        DocumentType.FINANCIAL_MEMO -> "نص المذكرة والبيانات والتوجيهات المالية"
        DocumentType.ADVANCE_PERMIT -> "بيان استخدام السلفة وضوابط التسوية"
        DocumentType.OFFICIAL_FINANCIAL_LETTER -> "نص الخطاب المالي والطلبات والمرفقات المؤيدة"
        DocumentType.RECEIPT_PAPER -> "بيانات سند القبض ووصف العملية"
        DocumentType.VIOLATION_REPORT -> "تفاصيل المخالفة / نص المحضر"
        else -> "متن المستند / التفاصيل"
    }

    Column(
        Modifier.fillMaxSize().imePadding().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(if (existing == null) "محرر المستندات المكتبي" else "تحرير المستندات المكتبي", style = MaterialTheme.typography.headlineSmall)
        Text("${type.displayName()} — رقم $automaticNumber — الحالة: ${existing?.status?.name ?: DocumentStatus.SUBMITTED.name}", style = MaterialTheme.typography.bodyMedium)
        Text("محرر مدمج يعمل دون تطبيق خارجي؛ تُحفظ البيانات محليًا ويمكن مزامنتها عند اعتماد إعداد السحابة.", style = MaterialTheme.typography.bodySmall)

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("الجمهورية اليمنية · وزارة الإدارة والتنمية المحلية والريفية", style = MaterialTheme.typography.titleSmall)
                Text("صندوق النظافة والتحسين · فرع مديرية الحزم")
                Text("الرقم : ............    التاريخ : ${hijri.ifBlank { "       /       /   144 هـ" }}")
                Text("الموافق : ${gregorian.ifBlank { "       /       /     20   م" }}    المرفقات : ( ${existing?.attachmentsCount ?: "        "} )")
                Text("NO: $automaticNumber", style = MaterialTheme.typography.labelLarge)
                Text(type.displayName(), style = MaterialTheme.typography.titleMedium)
            }
        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(hijri, { hijri = it }, label = { Text("التاريخ الهجري") }, modifier = Modifier.weight(1f), singleLine = true)
            OutlinedTextField(gregorian, { gregorian = it }, label = { Text("التاريخ الميلادي") }, modifier = Modifier.weight(1f), singleLine = true)
        }
        OutlinedTextField(beneficiary, { beneficiary = it }, label = { Text(beneficiaryLabel) }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(beneficiaryId, { beneficiaryId = it }, label = { Text("رقم الهوية / الحساب / رقم السجل") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(purpose, { purpose = it }, label = { Text(purposeLabel) }, modifier = Modifier.fillMaxWidth(), minLines = 2)
        if (usesAmount) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(amountText, { amountText = it.filter { ch -> ch.isDigit() || ch == '.' || ch == ',' } }, label = { Text("المبلغ بالأرقام (ريال يمني)") }, modifier = Modifier.weight(1f), singleLine = true)
                OutlinedTextField(amountWords, { amountWords = it }, label = { Text("المبلغ كتابةً") }, modifier = Modifier.weight(1f), minLines = 1)
            }
            if (amountText.isNotBlank() && amountWords.isBlank()) {
                OutlinedButton(onClick = { amountText.replace(',', '.').toDoubleOrNull()?.let { amountWords = NumberToWordsConverter.convert(it) } }) {
                    Text("تحويل المبلغ إلى كتابة")
                }
            }
        }

        if (type == DocumentType.VIOLATION_REPORT) {
            Text("بيانات محضر ضبط وقوع مخالفة", style = MaterialTheme.typography.titleMedium)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(incidentTime, { incidentTime = it }, label = { Text("الساعة") }, modifier = Modifier.weight(1f), singleLine = true)
                OutlinedTextField(incidentDay, { incidentDay = it }, label = { Text("اليوم") }, modifier = Modifier.weight(1f), singleLine = true)
            }
            OutlinedTextField(incidentLocation, { incidentLocation = it }, label = { Text("الموقع الكائن") }, modifier = Modifier.fillMaxWidth(), minLines = 1)
            OutlinedTextField(violationType, { violationType = it }, label = { Text("نوع المخالفة") }, modifier = Modifier.fillMaxWidth(), minLines = 1)
            OutlinedTextField(responsibleAction, { responsibleAction = it }, label = { Text("الفعل / المسؤولية") }, modifier = Modifier.fillMaxWidth(), minLines = 2)
            OutlinedTextField(lawArticle, { lawArticle = it }, label = { Text("المادة القانونية") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(witnessOne, { witnessOne = it }, label = { Text("الشاهد الأول") }, modifier = Modifier.weight(1f), singleLine = true)
                OutlinedTextField(witnessTwo, { witnessTwo = it }, label = { Text("الشاهد الثاني") }, modifier = Modifier.weight(1f), singleLine = true)
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(regionName, { regionName = it }, label = { Text("المنطقة") }, modifier = Modifier.weight(1f), singleLine = true)
                OutlinedTextField(regionOfficer, { regionOfficer = it }, label = { Text("مسؤول المنطقة") }, modifier = Modifier.weight(1f), singleLine = true)
            }
        }

        StructuredFieldsSection(type, structuredFields) { key, value ->
            structuredFields = structuredFields + (key to value)
        }

        Text("البيانات التفصيلية الخاصة بقالب ${type.displayName()}", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(details, { details = it }, label = { Text(detailLabel) }, modifier = Modifier.fillMaxWidth(), minLines = 7)
        if (type == DocumentType.ORDER || type == DocumentType.REQUEST || type == DocumentType.RECEIPT) {
            TemplateTextFields(type, templateText) { templateText = it }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(category, { category = it }, label = { Text("البند المالي") }, modifier = Modifier.weight(1f), singleLine = true)
            OutlinedTextField(costCenter, { costCenter = it }, label = { Text("مركز التكلفة") }, modifier = Modifier.weight(1f), singleLine = true)
        }
        OutlinedTextField(funding, { funding = it }, label = { Text("مصدر التمويل") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(attachmentsText, { attachmentsText = it.filter(Char::isDigit) }, label = { Text("عدد المرفقات") }, modifier = Modifier.weight(1f), singleLine = true)
            OutlinedTextField(notes, { notes = it }, label = { Text("رقم النموذج / الملاحظات") }, modifier = Modifier.weight(2f), minLines = 2)
        }
        OutlinedTextField(tags, { tags = it }, label = { Text("وسوم المستند (اختياري)؛ افصل بينها بفواصل") }, modifier = Modifier.fillMaxWidth(), singleLine = true)

        OutlinedButton(onClick = onOpenTemplateDesigner, modifier = Modifier.fillMaxWidth()) {
            Text("فتح مصمم الصفحة المدمج (صور، جداول، مقاس A3/A4/A5 وهوامش)")
        }
        Text("مصمم الصفحة يغيّر قالب نوع المستند، أما هذه الشاشة فتحرر بيانات هذا المستند فقط.", style = MaterialTheme.typography.bodySmall)
        if (message.isNotBlank()) Text(message, color = MaterialTheme.colorScheme.error)
        Button(
            onClick = {
                val now = System.currentTimeMillis()
                val amount = amountText.replace(',', '.').toDoubleOrNull()
                val finalAmountWords = amountWords.ifBlank { amount?.let { NumberToWordsConverter.convert(it) }.orEmpty() }
                val value = Document(
                    id = existing?.id ?: 0,
                    type = type,
                    documentNumber = automaticNumber,
                    dateHijri = hijri,
                    dateGregorian = gregorian,
                    amount = amount,
                    amountWords = finalAmountWords,
                    beneficiaryName = beneficiary,
                    purpose = purpose,
                    details = details,
                    notes = templateText.encode(notes),
                    status = existing?.status ?: DocumentStatus.SUBMITTED,
                    attachmentsCount = attachmentsText.toIntOrNull() ?: 0,
                    createdAt = existing?.createdAt ?: now,
                    isArchived = existing?.isArchived ?: false,
                    archivedAt = existing?.archivedAt,
                    updatedAt = now,
                    tags = tags,
                    financialCategory = category,
                    costCenter = costCenter,
                    fundingSource = funding,
                    beneficiaryId = beneficiaryId,
                    submittedBy = existing?.submittedBy.orEmpty(),
                    reviewedBy = existing?.reviewedBy.orEmpty(),
                    approvedBy = existing?.approvedBy.orEmpty(),
                    approvedAt = existing?.approvedAt,
                    paidAt = existing?.paidAt,
                    rejectionReason = existing?.rejectionReason.orEmpty(),
                    incidentTime = incidentTime.takeIf { type == DocumentType.VIOLATION_REPORT },
                    incidentDay = incidentDay.takeIf { type == DocumentType.VIOLATION_REPORT },
                    incidentLocation = incidentLocation.takeIf { type == DocumentType.VIOLATION_REPORT },
                    violationType = violationType.takeIf { type == DocumentType.VIOLATION_REPORT },
                    responsibleAction = responsibleAction.takeIf { type == DocumentType.VIOLATION_REPORT },
                    lawArticle = lawArticle.takeIf { type == DocumentType.VIOLATION_REPORT },
                    witnessOne = witnessOne.takeIf { type == DocumentType.VIOLATION_REPORT },
                    witnessTwo = witnessTwo.takeIf { type == DocumentType.VIOLATION_REPORT },
                    regionName = regionName.takeIf { type == DocumentType.VIOLATION_REPORT },
                    regionOfficerName = regionOfficer.takeIf { type == DocumentType.VIOLATION_REPORT },
                    cloudId = existing?.cloudId.orEmpty(),
                    createdByUid = existing?.createdByUid.orEmpty(),
                    structuredFields = StructuredDocumentFields.encode(structuredFields)
                )
                if (existing == null) {
                    viewModel.addDocument(value)
                    DocumentNumbering.consume(context, type)
                } else {
                    viewModel.updateDocument(value)
                }
                onBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text(if (existing == null) "إنشاء المستند وحفظه" else "حفظ جميع التعديلات") }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("إغلاق محرر المستندات") }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun StructuredFieldsSection(
    type: DocumentType,
    values: Map<String, String>,
    onChange: (String, String) -> Unit
) {
    val fields: List<Pair<String, String>> = when (type) {
        DocumentType.PURCHASE_ORDER -> listOf(
            "supplierName" to "اسم المورد / الجهة الموردة",
            "supplierId" to "رقم المورد / السجل التجاري",
            "supplierAddress" to "عنوان المورد ووسيلة التواصل",
            "items" to "الأصناف المطلوبة",
            "quantities" to "الكميات المطلوبة",
            "unitPrices" to "سعر الوحدة لكل صنف",
            "totalBeforeTax" to "الإجمالي قبل الضرائب",
            "taxes" to "الضرائب والرسوم",
            "totalAfterTax" to "الإجمالي النهائي",
            "deliveryDate" to "موعد ومكان التسليم",
            "purchaseTerms" to "شروط الشراء والضمان",
            "requestingDepartment" to "الإدارة / القسم الطالب"
        )
        DocumentType.SUPPLY_PERMIT -> listOf(
            "permitKind" to "نوع الإذن: توريد أو استلام",
            "supplierName" to "اسم المورد / الجهة المسلِّمة",
            "recipientName" to "اسم المستلم / الجهة المستلمة",
            "source" to "مصدر التوريد",
            "destination" to "جهة أو مكان التوريد",
            "items" to "الأصناف والمواد",
            "quantities" to "الكميات والوحدات",
            "unitValues" to "قيمة الوحدة",
            "totalValue" to "إجمالي القيمة",
            "transportDetails" to "بيانات النقل والتسليم",
            "inspectionResult" to "نتيجة الفحص والاستلام"
        )
        DocumentType.RECEIPT_MINUTES -> listOf(
            "committeeMembers" to "أسماء أعضاء لجنة الاستلام",
            "supplierName" to "الجهة أو المورد المسلِّم",
            "projectOrLocation" to "المشروع / الموقع",
            "deliveryDate" to "تاريخ الاستلام الفعلي",
            "items" to "المواد أو الأعمال المستلمة",
            "quantities" to "الكميات والوحدات",
            "condition" to "حالة المواد أو الأعمال",
            "acceptanceDecision" to "قرار اللجنة: قبول أو تحفظ أو رفض",
            "deficiencies" to "النواقص والملاحظات",
            "handoverDocuments" to "المستندات المسلّمة مع المحضر"
        )
        DocumentType.FINANCIAL_CLAIM -> listOf(
            "claimantName" to "اسم صاحب المطالبة",
            "claimantId" to "رقم الهوية / الحساب",
            "claimBasis" to "أساس المطالبة والعقد أو التكليف",
            "servicePeriod" to "الفترة أو مدة الاستحقاق",
            "claimedItems" to "بيان البنود والمبالغ المطالب بها",
            "supportingDocuments" to "المستندات المؤيدة",
            "grossAmount" to "إجمالي المطالبة",
            "deductions" to "الاستقطاعات والخصميات",
            "netAmount" to "صافي المبلغ المستحق",
            "paymentAccount" to "حساب أو وسيلة الصرف"
        )
        DocumentType.CUSTODY_SETTLEMENT -> listOf(
            "custodianName" to "اسم أمين العهدة",
            "custodianId" to "رقم الهوية / الحساب",
            "custodyNumber" to "رقم العهدة أو السلفة",
            "custodyDate" to "تاريخ تسليم العهدة",
            "settlementDate" to "تاريخ التسوية",
            "advanceAmount" to "قيمة العهدة المستلمة",
            "expenseItems" to "تفصيل المصروفات بالفواتير",
            "spentAmount" to "إجمالي المصروف",
            "returnedAmount" to "المبلغ المرتجع",
            "remainingBalance" to "الرصيد المتبقي أو العجز",
            "settlementNotes" to "ملاحظات لجنة التسوية"
        )
        DocumentType.ADVANCE_PERMIT -> listOf(
            "recipientName" to "اسم مستلم السلفة",
            "recipientId" to "رقم الهوية / الحساب",
            "advancePurpose" to "الغرض التفصيلي من السلفة",
            "requestedAmount" to "المبلغ المطلوب",
            "dueDate" to "تاريخ الاستحقاق والتسوية",
            "guarantee" to "الضمان أو التعهد",
            "settlementDocuments" to "المستندات المطلوبة للتسوية",
            "financeApproval" to "اعتماد المدير المالي",
            "managerApproval" to "اعتماد مدير الفرع"
        )
        DocumentType.FINANCIAL_MEMO -> listOf(
            "recipient" to "الجهة أو المسؤول الموجه إليه",
            "referenceNumber" to "رقم وتاريخ المرجع",
            "subject" to "موضوع المذكرة",
            "body" to "نص المذكرة والتفاصيل المالية",
            "recommendation" to "التوجيه أو التوصية المطلوبة",
            "attachments" to "المرفقات المؤيدة",
            "preparedBy" to "معد المذكرة",
            "reviewedBy" to "مراجع المذكرة"
        )
        DocumentType.OFFICIAL_FINANCIAL_LETTER -> listOf(
            "recipient" to "الجهة المخاطبة",
            "referenceNumber" to "رقم وتاريخ المرجع",
            "subject" to "موضوع الخطاب",
            "body" to "نص الخطاب المالي",
            "requestedAction" to "الإجراء المطلوب من الجهة المخاطبة",
            "attachments" to "المرفقات",
            "senderName" to "اسم وصفة مرسل الخطاب",
            "replyDeadline" to "الموعد المطلوب للرد"
        )
        else -> emptyList()
    }
    if (fields.isEmpty()) return
    Text("الحقول التفصيلية المستقلة — ${type.displayName()}", style = MaterialTheme.typography.titleMedium)
    Text("كل خانة أدناه تحفظ منفصلة داخل المستند وتظهر عند إعادة فتحه والمزامنة السحابية.", style = MaterialTheme.typography.bodySmall)
    fields.forEach { (key, label) ->
        OutlinedTextField(
            value = values[key].orEmpty(),
            onValueChange = { onChange(key, it) },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            minLines = if (key in setOf("items", "quantities", "expenseItems", "body", "supportingDocuments", "deficiencies", "purchaseTerms")) 3 else 1,
            singleLine = key !in setOf("items", "quantities", "expenseItems", "body", "supportingDocuments", "deficiencies", "purchaseTerms")
        )
    }
}

@Composable
private fun TemplateTextFields(type: DocumentType, value: OfficialTemplateText, onChange: (OfficialTemplateText) -> Unit) {
    Text("تحرير كل نصوص نموذج ${type.displayName()}", style = MaterialTheme.typography.titleMedium)
    Text("يمكن تعديل العبارات الثابتة والعناوين كما ستظهر في الصفحة المطبوعة.", style = MaterialTheme.typography.bodySmall)
    @Composable
    fun field(text: String, label: String, update: (String) -> OfficialTemplateText) {
        OutlinedTextField(text, { onChange(update(it)) }, label = { Text(label) }, modifier = Modifier.fillMaxWidth(), minLines = 1)
    }
    when (type) {
        DocumentType.ORDER -> {
            field(value.orderCashierGreeting, "عبارة مخاطبة أمين الصندوق") { value.copy(orderCashierGreeting = it) }
            field(value.orderInstruction, "نص التوجيه والصرف") { value.copy(orderInstruction = it) }
            field(value.orderBeneficiaryPrefix, "عبارة المستفيد") { value.copy(orderBeneficiaryPrefix = it) }
            field(value.orderPurposeLabel, "عنوان سبب الصرف") { value.copy(orderPurposeLabel = it) }
            field(value.orderClosing, "عبارة الختام") { value.copy(orderClosing = it) }
        }
        DocumentType.REQUEST -> {
            field(value.requestRecipient, "اسم الجهة المخاطبة") { value.copy(requestRecipient = it) }
            field(value.requestGreeting, "عبارة الاحترام") { value.copy(requestGreeting = it) }
            field(value.requestInstruction, "نص طلب التوجيه") { value.copy(requestInstruction = it) }
            field(value.requestClosing, "عبارة ختام الطلب") { value.copy(requestClosing = it) }
        }
        DocumentType.RECEIPT -> {
            field(value.receiptOpening, "عبارة بداية الإقرار") { value.copy(receiptOpening = it) }
            field(value.receiptJobLabel, "عبارة الوظيفة") { value.copy(receiptJobLabel = it) }
            field(value.receiptAmountLabel, "عنوان المبلغ") { value.copy(receiptAmountLabel = it) }
            field(value.receiptSource, "مصدر المبلغ") { value.copy(receiptSource = it) }
            field(value.receiptPurposeLabel, "عنوان سبب الاستلام") { value.copy(receiptPurposeLabel = it) }
            field(value.receiptDischarge, "نص الإخلاء") { value.copy(receiptDischarge = it) }
            field(value.receiptRecipientLabel, "عنوان المستلم") { value.copy(receiptRecipientLabel = it) }
            field(value.receiptNameLabel, "عنوان الاسم") { value.copy(receiptNameLabel = it) }
            field(value.receiptSignatureLabel, "عنوان التوقيع والإبهام") { value.copy(receiptSignatureLabel = it) }
        }
        else -> Unit
    }
}
