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

    @Test
    fun allThreeChapterRowsRoundTripWithEveryEditableColumn() {
        val pageOne = ExpenseBookData.defaultRows(1).associateWith { label ->
            ExpenseBookData.ExpenseLine(label, quantity = "2", paid = "1000", unpaid = "250", details = "تفصيل الباب الأول")
        }
        val pageTwo = ExpenseBookData.defaultRows(2).associateWith { label ->
            ExpenseBookData.ExpenseLine(label, quantity = "3", paid = "2000", unpaid = "500", details = "تفصيل الباب الثاني")
        }
        val pageThree = ExpenseBookData.defaultRows(3).associateWith { label ->
            ExpenseBookData.ExpenseLine(label, quantity = "1", paid = "3000", unpaid = "750", details = "تفصيل الباب الثالث")
        }
        val encodedRows = listOf(
            ExpenseBookData.encodeRows(pageOne, 1),
            ExpenseBookData.encodeRows(pageTwo, 2),
            ExpenseBookData.encodeRows(pageThree, 3)
        ).joinToString("\n")
        val restored = ExpenseBookData.decode(
            ExpenseBookData(
                chapterOneTotal = 300_000,
                chapterTwoTotal = 350_000,
                chapterThreeTotal = 400_000,
                expenseRows = encodedRows
            ).encode()
        )

        assertEquals(pageOne, ExpenseBookData.decodeRows(restored.expenseRows, 1))
        assertEquals(pageTwo, ExpenseBookData.decodeRows(restored.expenseRows, 2))
        assertEquals(pageThree, ExpenseBookData.decodeRows(restored.expenseRows, 3))
        assertEquals("250", ExpenseBookData.decodeRows(restored.expenseRows, 1)["الوقود الديزل للأعمال اليومية"]?.unpaid)
    }

    @Test
    fun fourthChapterAddsManualRowsAndOnlyAutomaticExcess() {
        val manualDebt = "شهر 1 | 12000 | مديونية يدوية\nشهر 2 | 8000 | مديونية يدوية أخرى"
        val data = ExpenseBookData(
            chapterOneTotal = 400_000,
            chapterTwoTotal = 352_000,
            chapterThreeTotal = 300_000,
            debtFebruary = 230_000,
            debtMarch = 83_000,
            debtPrevious = 617_000,
            debtRows = manualDebt
        )

        assertEquals(1_052_000L, data.generalTotal)
        assertEquals(100_000L, data.excessDebt)
        assertEquals(20_000L, data.debtRowsTotal)
        assertEquals(1_050_000L, data.debtTotal)
        val restored = ExpenseBookData.decode(data.encode())
        assertEquals(manualDebt, restored.debtRows)
        assertEquals(data.debtTotal, restored.debtTotal)
    }
}
