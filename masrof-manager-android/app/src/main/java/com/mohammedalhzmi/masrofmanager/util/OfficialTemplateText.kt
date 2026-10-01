package com.mohammedalhzmi.masrofmanager.util

/** Editable text blocks for the three official forms. Stored in Document.notes without changing Room schema. */
data class OfficialTemplateText(
    val orderCashierGreeting: String = "الأخ أمين الصندوق المحترم",
    val orderInstruction: String = "بعد التوجيه يتم صرف مبلغ وقدره:",
    val orderBeneficiaryPrefix: String = "وذلك للأخ /ـوه:",
    val orderPurposeLabel: String = "وذلك مقابل:",
    val orderClosing: String = "ولكم خالص الشكر والتقدير",
    val requestRecipient: String = "مدير فرع صندوق النظافة والتحسين",
    val requestGreeting: String = "المحترم",
    val requestInstruction: String = "نتكرم بالتوجيه بصرف / اعتماد الطلب الموضح أدناه:",
    val requestClosing: String = "وتكرموا مشكورين بالتوجيه",
    val receiptOpening: String = "أنا الموقع أدناه:",
    val receiptJobLabel: String = "وأعمل بوظيفة:",
    val receiptAmountLabel: String = "استلمت مبلغًا وقدره:",
    val receiptSource: String = "من فرع صندوق النظافة والتحسين",
    val receiptPurposeLabel: String = "وذلك مقابل:",
    val receiptDischarge: String = "وأقر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهدة على ذلك.",
    val receiptRecipientLabel: String = "المستلم",
    val receiptNameLabel: String = "الاسم:",
    val receiptSignatureLabel: String = "التوقيع والإبهام:"
) {
    fun encode(existingNotes: String?): String {
        val preserved = existingNotes.orEmpty().lineSequence().filterNot { it.startsWith(PREFIX) }.joinToString("\n").trim()
        val values = listOf(
            orderCashierGreeting, orderInstruction, orderBeneficiaryPrefix, orderPurposeLabel, orderClosing,
            requestRecipient, requestGreeting, requestInstruction, requestClosing,
            receiptOpening, receiptJobLabel, receiptAmountLabel, receiptSource, receiptPurposeLabel,
            receiptDischarge, receiptRecipientLabel, receiptNameLabel, receiptSignatureLabel
        ).joinToString(SEP) { java.net.URLEncoder.encode(it, Charsets.UTF_8.name()) }
        return listOf(PREFIX + values, preserved).filter(String::isNotBlank).joinToString("\n")
    }

    companion object {
        private const val PREFIX = "MASROF_TEMPLATE_TEXT_V1|"
        private const val SEP = "~"
        fun decode(notes: String?): OfficialTemplateText {
            val line = notes.orEmpty().lineSequence().firstOrNull { it.startsWith(PREFIX) } ?: return OfficialTemplateText()
            val values = line.removePrefix(PREFIX).split(SEP).map {
                runCatching { java.net.URLDecoder.decode(it, Charsets.UTF_8.name()) }.getOrDefault("")
            }
            val defaults = OfficialTemplateText()
            fun v(index: Int, fallback: String) = values.getOrNull(index)?.ifBlank { fallback } ?: fallback
            return defaults.copy(
                orderCashierGreeting = v(0, defaults.orderCashierGreeting), orderInstruction = v(1, defaults.orderInstruction),
                orderBeneficiaryPrefix = v(2, defaults.orderBeneficiaryPrefix), orderPurposeLabel = v(3, defaults.orderPurposeLabel),
                orderClosing = v(4, defaults.orderClosing), requestRecipient = v(5, defaults.requestRecipient),
                requestGreeting = v(6, defaults.requestGreeting), requestInstruction = v(7, defaults.requestInstruction),
                requestClosing = v(8, defaults.requestClosing), receiptOpening = v(9, defaults.receiptOpening),
                receiptJobLabel = v(10, defaults.receiptJobLabel), receiptAmountLabel = v(11, defaults.receiptAmountLabel),
                receiptSource = v(12, defaults.receiptSource), receiptPurposeLabel = v(13, defaults.receiptPurposeLabel),
                receiptDischarge = v(14, defaults.receiptDischarge), receiptRecipientLabel = v(15, defaults.receiptRecipientLabel),
                receiptNameLabel = v(16, defaults.receiptNameLabel), receiptSignatureLabel = v(17, defaults.receiptSignatureLabel)
            )
        }
    }
}
