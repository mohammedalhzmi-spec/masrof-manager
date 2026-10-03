package com.mohammedalhzmi.masrofmanager.util

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/** Versioned key/value payload for the independent fields of administrative templates. */
object StructuredDocumentFields {
    private const val PREFIX = "MASROF_STRUCTURED_FIELDS_V1|"

    fun encode(fields: Map<String, String>): String {
        val body = fields.filterValues { it.isNotBlank() }.entries.joinToString("&") {
            "${encodePart(it.key)}=${encodePart(it.value)}"
        }
        return PREFIX + body
    }

    fun decode(raw: String?): Map<String, String> {
        if (raw.isNullOrBlank() || !raw.startsWith(PREFIX)) return emptyMap()
        return raw.removePrefix(PREFIX).split('&').mapNotNull { pair ->
            val separator = pair.indexOf('=')
            if (separator <= 0) return@mapNotNull null
            val key = decodePart(pair.substring(0, separator))
            val value = decodePart(pair.substring(separator + 1))
            key to value
        }.toMap()
    }

    fun toDisplayText(raw: String?): String = decode(raw).entries.joinToString("\n") { (key, value) -> "${labels[key] ?: key}: $value" }

    private val labels = mapOf(
        "supplierName" to "اسم المورد / الجهة", "supplierId" to "رقم المورد / السجل التجاري", "supplierAddress" to "عنوان المورد ووسيلة التواصل",
        "items" to "الأصناف والمواد", "quantities" to "الكميات والوحدات", "unitPrices" to "سعر الوحدة", "unitValues" to "قيمة الوحدة",
        "totalBeforeTax" to "الإجمالي قبل الضرائب", "taxes" to "الضرائب والرسوم", "totalAfterTax" to "الإجمالي النهائي", "totalValue" to "إجمالي القيمة",
        "deliveryDate" to "تاريخ وموعد التسليم", "purchaseTerms" to "شروط الشراء والضمان", "requestingDepartment" to "الإدارة / القسم الطالب",
        "permitKind" to "نوع الإذن", "recipientName" to "اسم المستلم / الجهة المستلمة", "source" to "مصدر التوريد", "destination" to "جهة أو مكان التوريد",
        "transportDetails" to "بيانات النقل والتسليم", "inspectionResult" to "نتيجة الفحص والاستلام", "committeeMembers" to "أعضاء لجنة الاستلام",
        "projectOrLocation" to "المشروع / الموقع", "condition" to "حالة المواد أو الأعمال", "acceptanceDecision" to "قرار اللجنة",
        "deficiencies" to "النواقص والملاحظات", "handoverDocuments" to "المستندات المسلّمة", "claimantName" to "اسم صاحب المطالبة",
        "claimantId" to "رقم هوية / حساب صاحب المطالبة", "claimBasis" to "أساس المطالبة", "servicePeriod" to "فترة الاستحقاق",
        "claimedItems" to "بنود المطالبة ومبالغها", "supportingDocuments" to "المستندات المؤيدة", "grossAmount" to "إجمالي المطالبة",
        "deductions" to "الاستقطاعات والخصميات", "netAmount" to "صافي المبلغ المستحق", "paymentAccount" to "حساب أو وسيلة الصرف",
        "custodianName" to "اسم أمين العهدة", "custodianId" to "رقم هوية / حساب أمين العهدة", "custodyNumber" to "رقم العهدة أو السلفة",
        "custodyDate" to "تاريخ تسليم العهدة", "settlementDate" to "تاريخ التسوية", "advanceAmount" to "قيمة العهدة المستلمة",
        "expenseItems" to "تفصيل المصروفات بالفواتير", "spentAmount" to "إجمالي المصروف", "returnedAmount" to "المبلغ المرتجع",
        "remainingBalance" to "الرصيد المتبقي أو العجز", "settlementNotes" to "ملاحظات لجنة التسوية", "advancePurpose" to "الغرض التفصيلي من السلفة",
        "requestedAmount" to "المبلغ المطلوب", "dueDate" to "تاريخ الاستحقاق والتسوية", "guarantee" to "الضمان أو التعهد",
        "settlementDocuments" to "مستندات التسوية", "financeApproval" to "اعتماد المدير المالي", "managerApproval" to "اعتماد مدير الفرع",
        "recipient" to "الجهة أو المسؤول الموجه إليه", "referenceNumber" to "رقم وتاريخ المرجع", "subject" to "موضوع المستند",
        "body" to "نص المستند والتفاصيل المالية", "recommendation" to "التوجيه أو التوصية", "attachments" to "المرفقات",
        "preparedBy" to "معد المذكرة", "reviewedBy" to "مراجع المذكرة", "requestedAction" to "الإجراء المطلوب",
        "senderName" to "اسم وصفة المرسل", "replyDeadline" to "الموعد المطلوب للرد"
    )

    private fun encodePart(value: String) = URLEncoder.encode(value, StandardCharsets.UTF_8.name())
    private fun decodePart(value: String) = runCatching { URLDecoder.decode(value, StandardCharsets.UTF_8.name()) }.getOrDefault("")
}
