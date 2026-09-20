package com.mohammedalhzmi.masrofmanager.util

object NumberToWordsConverter {
    fun convert(amount: Double): String {
        val longAmount = amount.toLong()
        if (longAmount == 0L) return "صفر ريال"
        
        // This is a simplified version, should be expanded to handle complex numbers
        return "$longAmount ريال يمني فقط لا غير"
    }
}
