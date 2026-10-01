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
    val legacyDetails: String = "",
    val pageOneRows: String = "",
    val pageTwoRows: String = "",
    val pageThreeRows: String = "",
    val debtRows: String = "",
    val expenseRows: String = ""
) {
    data class ExpenseLine(val label: String, val quantity: String = "", val paid: String = "", val unpaid: String = "", val details: String = "")
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
        get() = debtFebruary + debtMarch + debtPrevious + excessDebt + debtRowsTotal

    val debtRowsTotal: Long
        get() = debtRows.lineSequence().map { it.split('|').getOrNull(1)?.let(::parseAmount) ?: 0L }.sum()

    fun encode(): String {
        val legacy = URLEncoder.encode(legacyDetails, StandardCharsets.UTF_8.name())
        val fields = listOf(
            chapterOneTotal, chapterTwoTotal, chapterThreeTotal,
            debtFebruary, debtMarch, debtPrevious, legacyGeneralTotal,
            URLEncoder.encode(legacyDetails, StandardCharsets.UTF_8.name()),
            URLEncoder.encode(pageOneRows, StandardCharsets.UTF_8.name()),
            URLEncoder.encode(pageTwoRows, StandardCharsets.UTF_8.name()),
            URLEncoder.encode(pageThreeRows, StandardCharsets.UTF_8.name()),
            URLEncoder.encode(debtRows, StandardCharsets.UTF_8.name()),
            URLEncoder.encode(expenseRows, StandardCharsets.UTF_8.name())
        ).joinToString("|")
        return "$PREFIX$fields"
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
            if (fields.size !in setOf(8, 12, 13)) return ExpenseBookData(legacyDetails = raw)
            fun amount(index: Int): Long = fields[index].toLongOrNull()?.coerceIn(0, MAX_AMOUNT) ?: 0
            val legacy = decodeText(fields[7])
            return ExpenseBookData(
                chapterOneTotal = amount(0),
                chapterTwoTotal = amount(1),
                chapterThreeTotal = amount(2),
                debtFebruary = amount(3),
                debtMarch = amount(4),
                debtPrevious = amount(5),
                legacyGeneralTotal = amount(6),
                legacyDetails = legacy,
                pageOneRows = fields.getOrNull(8)?.let(::decodeText).orEmpty(),
                pageTwoRows = fields.getOrNull(9)?.let(::decodeText).orEmpty(),
                pageThreeRows = fields.getOrNull(10)?.let(::decodeText).orEmpty(),
                debtRows = fields.getOrNull(11)?.let(::decodeText).orEmpty(),
                expenseRows = fields.getOrNull(12)?.let(::decodeText).orEmpty()
            )
        }

        fun defaultRows(page: Int): List<String> = when (page) {
            1 -> listOf("الوقود الديزل للأعمال اليومية", "وقود للدمر والتكاتك", "زيت وتشحيم للمعدات", "حملة النظافة الشهرية", "صيانة مشتريات 1", "صيانة مشتريات 2", "صيانة مشتريات 3", "أخرى مختلفة", "سروسه", "مكاسن + خراشات", "ملابس + أحذية", "كفوف", "أكياس قمامة")
            2 -> listOf("مستحقات مدير المديرية", "مستحقات مدير الفرع", "مستحقات الإداريين", "مستحقات العمال", "مستحقات المشرفين", "مستحقات السائقين", "نسبة المحصلين", "بدل جلسات", "إضافي", "الحوافز والمكافآت", "إكرامية نقدية", "إكرامية عينية", "مياه وكهرباء", "مصروفات عهدة", "رسوم المقلب الشهرية", "خدمات الاستضافة والضيافة", "علاج وتداوي")
            else -> listOf("القرطاسية والطباعة", "متأخرات مديونية متبقية من الشهر السابق", "تنقلات عامة", "بدل سفر", "إيجار مباني", "اتصالات والإنترنت", "استئجار معدات", "خدمات الأمن والضبط", "الفوائد والعمولات المحلية", "خدمات البنوك", "خدمات الحراسة والأمن", "أخرى مختلفة", "ديون محلية وسابقة", "ضرائب المرتبات والدخل والمبيعات")
        }

        fun decodeRows(value: String?, page: Int): Map<String, ExpenseLine> {
            val prefix = "ROWV1|$page|"
            val raw = value.orEmpty().lineSequence().firstOrNull { it.startsWith(prefix) }?.removePrefix(prefix).orEmpty()
            return raw.split(";").asSequence().filter { it.isNotBlank() }.mapNotNull { encoded ->
                val p = encoded.split("~")
                if (p.size < 5) null else { val text = p.map(::decodeText); text[0] to ExpenseLine(text[0], text[1], text[2], text[3], text[4]) }
            }.toMap()
        }

        fun encodeRows(rows: Map<String, ExpenseLine>, page: Int): String {
            val body = rows.values.joinToString(";") { line -> listOf(line.label, line.quantity, line.paid, line.unpaid, line.details).joinToString("~") { URLEncoder.encode(it, StandardCharsets.UTF_8.name()) } }
            return "ROWV1|$page|$body"
        }

        private fun decodeText(value: String): String = runCatching {
            URLDecoder.decode(value, StandardCharsets.UTF_8.name())
        }.getOrDefault("")

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
