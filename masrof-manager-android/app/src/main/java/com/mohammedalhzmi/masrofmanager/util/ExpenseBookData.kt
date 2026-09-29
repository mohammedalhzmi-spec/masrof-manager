package com.mohammedalhzmi.masrofmanager.util

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/** Structured values stored inside Document.details so existing Room/cloud schemas remain compatible. */
internal data class ExpenseBookData(
    val chapterOneTotal: Long = 0,
    val chapterTwoTotal: Long = 0,
    val chapterThreeTotal: Long = 0,
    val debtFebruary: Long = 0,
    val debtMarch: Long = 0,
    val debtPrevious: Long = 0,
    val legacyGeneralTotal: Long = 0,
    val legacyDetails: String = ""
) {
    private val chapterTotal: Long
        get() = chapterOneTotal + chapterTwoTotal + chapterThreeTotal

    val hasChapterTotals: Boolean
        get() = chapterOneTotal > 0 || chapterTwoTotal > 0 || chapterThreeTotal > 0

    /** The general total is the sum of the first three chapters, as requested. */
    val generalTotal: Long
        get() = if (hasChapterTotals) chapterTotal else legacyGeneralTotal

    /** Only expenditure above the fixed 952,000 limit becomes a new debt row. */
    val excessDebt: Long
        get() = if (hasChapterTotals) (chapterTotal - FIXED_GENERAL_TOTAL).coerceAtLeast(0) else 0

    val debtTotal: Long
        get() = debtFebruary + debtMarch + debtPrevious + excessDebt

    fun encode(): String {
        val legacy = URLEncoder.encode(legacyDetails, StandardCharsets.UTF_8.name())
        val fields = listOf(
            chapterOneTotal, chapterTwoTotal, chapterThreeTotal,
            debtFebruary, debtMarch, debtPrevious, legacyGeneralTotal
        ).joinToString("|")
        return "$PREFIX$fields|$legacy"
    }

    companion object {
        const val FIXED_GENERAL_TOTAL = 952_000L
        private const val PREFIX = "MASROF_EXPENSE_BOOK_V1|"
        private const val MAX_AMOUNT = 1_000_000_000_000L

        /** New books start with the three debt lines shown on the supplied fourth reference page. */
        fun newBook(): ExpenseBookData = ExpenseBookData(
            debtFebruary = 230_000,
            debtMarch = 83_000,
            debtPrevious = 617_000
        )

        /** Legacy expense documents remain readable and their original details are preserved. */
        fun decode(value: String?): ExpenseBookData {
            val raw = value.orEmpty()
            if (!raw.startsWith(PREFIX)) return ExpenseBookData(legacyDetails = raw)
            val fields = raw.removePrefix(PREFIX).split('|')
            if (fields.size != 8) return ExpenseBookData(legacyDetails = raw)
            fun amount(index: Int): Long = fields[index].toLongOrNull()?.coerceIn(0, MAX_AMOUNT) ?: 0
            val legacy = runCatching { URLDecoder.decode(fields[7], StandardCharsets.UTF_8.name()) }.getOrDefault("")
            return ExpenseBookData(
                chapterOneTotal = amount(0),
                chapterTwoTotal = amount(1),
                chapterThreeTotal = amount(2),
                debtFebruary = amount(3),
                debtMarch = amount(4),
                debtPrevious = amount(5),
                legacyGeneralTotal = amount(6),
                legacyDetails = legacy
            )
        }

        fun hasEncodedData(value: String?): Boolean = value.orEmpty().startsWith(PREFIX)

        fun parseAmount(text: String): Long {
            val normalized = buildString(text.length) {
                text.forEach { ch ->
                    val digit = when (ch) {
                        in '٠'..'٩' -> ch.code - '٠'.code
                        in '۰'..'۹' -> ch.code - '۰'.code
                        else -> ch.digitToIntOrNull()
                    }
                    if (digit != null) append(('0'.code + digit).toChar())
                }
            }
            return normalized.toLongOrNull()?.coerceIn(0, MAX_AMOUNT) ?: 0
        }

        fun normalizeAmountInput(text: String): String = buildString(text.length) {
            text.forEach { ch ->
                val digit = when (ch) {
                    in '٠'..'٩' -> ch.code - '٠'.code
                    in '۰'..'۹' -> ch.code - '۰'.code
                    else -> ch.digitToIntOrNull()
                }
                if (digit != null) append(('0'.code + digit).toChar())
            }
        }.take(13)

        fun formatAmount(value: Long): String = value.toString().reversed()
            .chunked(3).joinToString(" ").reversed()
    }
}
