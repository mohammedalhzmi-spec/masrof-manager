package com.mohammedalhzmi.masrofmanager.util

import org.junit.Assert.assertEquals
import org.junit.Test

class ExpenseBookDataTest {
    @Test
    fun generalTotalAddsOnlyTheFirstThreeChapters() {
        val data = ExpenseBookData(chapterOneTotal = 125_000, chapterTwoTotal = 327_000, chapterThreeTotal = 500_000)

        assertEquals(952_000L, data.generalTotal)
        assertEquals(0L, data.excessDebt)
    }

    @Test
    fun onlyTheAmountAboveTheFixedLimitBecomesAdditionalDebt() {
        val data = ExpenseBookData(
            chapterOneTotal = 400_000,
            chapterTwoTotal = 352_000,
            chapterThreeTotal = 248_000,
            debtFebruary = 230_000,
            debtMarch = 83_000,
            debtPrevious = 617_000
        )

        assertEquals(1_000_000L, data.generalTotal)
        assertEquals(48_000L, data.excessDebt)
        assertEquals(978_000L, data.debtTotal)
    }

    @Test
    fun suppliedFourthPageAmountsSumTo930000() {
        val data = ExpenseBookData.newBook()

        assertEquals(930_000L, data.debtTotal)
        assertEquals(230_000L, data.debtFebruary)
        assertEquals(83_000L, data.debtMarch)
        assertEquals(617_000L, data.debtPrevious)
    }

    @Test
    fun legacyGeneralAmountIsPreservedWithoutInventingChapterDebt() {
        val data = ExpenseBookData(legacyGeneralTotal = 1_200_000)
        val restored = ExpenseBookData.decode(data.encode())

        assertEquals(1_200_000L, restored.generalTotal)
        assertEquals(0L, restored.excessDebt)
    }

    @Test
    fun encodingPreservesLegacyTextAndArabicAmountInput() {
        val data = ExpenseBookData(
            chapterOneTotal = ExpenseBookData.parseAmount("٩٥٢٬٠٠٠"),
            debtFebruary = 230_000,
            legacyDetails = "previous details | keep + intact"
        )

        assertEquals(952_000L, data.chapterOneTotal)
        assertEquals(data, ExpenseBookData.decode(data.encode()))
        assertEquals("1 000 000", ExpenseBookData.formatAmount(1_000_000))
    }
}
