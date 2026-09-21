package com.mohammedalhzmi.masrofmanager.util

object NumberToWordsConverter {
    private val ones = arrayOf("", "واحد", "اثنان", "ثلاثة", "أربعة", "خمسة", "ستة", "سبعة", "ثمانية", "تسعة")
    private val tens = arrayOf("", "", "عشرون", "ثلاثون", "أربعون", "خمسون", "ستون", "سبعون", "ثمانون", "تسعون")
    private val teens = arrayOf("عشرة", "أحد عشر", "اثنا عشر", "ثلاثة عشر", "أربعة عشر", "خمسة عشر", "ستة عشر", "سبعة عشر", "ثمانية عشر", "تسعة عشر")

    fun convert(amount: Double): String {
        val integer = amount.toLong().coerceAtLeast(0)
        val fraction = ((amount - integer) * 100).toInt().coerceIn(0, 99)
        val result = if (integer == 0L) "صفر" else groupToWords(integer)
        return if (fraction == 0) "$result ريال يمني فقط لا غير" else "$result ريال و${groupToWords(fraction.toLong())} فلس يمني فقط لا غير"
    }

    private fun groupToWords(value: Long): String = when {
        value < 10 -> ones[value.toInt()]
        value < 20 -> teens[(value - 10).toInt()]
        value < 100 -> if (value % 10 == 0L) tens[(value / 10).toInt()] else "${ones[(value % 10).toInt()]} و${tens[(value / 10).toInt()]}"
        value < 1000 -> if (value % 100 == 0L) "${ones[(value / 100).toInt()]} مائة" else "${ones[(value / 100).toInt()]} مائة و${groupToWords(value % 100)}"
        value < 1_000_000 -> groupWithScale(value, 1_000, "ألف", "آلاف")
        value < 1_000_000_000 -> groupWithScale(value, 1_000_000, "مليون", "ملايين")
        else -> groupWithScale(value, 1_000_000_000, "مليار", "مليارات")
    }

    private fun groupWithScale(value: Long, scale: Long, singular: String, plural: String): String {
        val major = value / scale
        val rest = value % scale
        val majorText = when (major) { 1L -> singular; 2L -> "اثنان $singular"; in 3L..10L -> "${groupToWords(major)} $plural"; else -> "${groupToWords(major)} $singular" }
        return if (rest == 0L) majorText else "$majorText و${groupToWords(rest)}"
    }
}
