package com.mohammedalhzmi.masrofmanager.util

import org.junit.Assert.assertEquals
import org.junit.Test

class SpreadsheetFormulaEvaluatorTest {
    @Test
    fun arithmeticUsesStandardOperatorPrecedence() {
        assertEquals("14", SpreadsheetFormulaEvaluator.evaluate("=2+3*4", emptyMap()))
        assertEquals("20", SpreadsheetFormulaEvaluator.evaluate("=(2+3)*4", emptyMap()))
    }

    @Test
    fun evaluatesCellsAndRanges() {
        val cells = mapOf("A1" to "10", "A2" to "20", "B1" to "3")
        assertEquals("13", SpreadsheetFormulaEvaluator.evaluate("=A1+B1", cells))
        assertEquals("30", SpreadsheetFormulaEvaluator.evaluate("=SUM(A1:A2)", cells))
        assertEquals("15", SpreadsheetFormulaEvaluator.evaluate("=AVERAGE(A1:A2)", cells))
        assertEquals("3", SpreadsheetFormulaEvaluator.evaluate("=MIN(A1:B1)", cells))
        assertEquals("20", SpreadsheetFormulaEvaluator.evaluate("=MAX(A1:A2)", cells))
    }

    @Test
    fun formulasCanReferenceOtherFormulaCellsAndArabicDigits() {
        val cells = mapOf("A1" to "=SUM(B1:B2)", "B1" to "٥", "B2" to "7")
        assertEquals("24", SpreadsheetFormulaEvaluator.evaluate("=A1*2", cells))
        assertEquals("12", SpreadsheetFormulaEvaluator.evaluate("=٦+٦", emptyMap()))
        assertEquals("8", SpreadsheetFormulaEvaluator.evaluate("=A1+3", mapOf("A1" to "۵")))
    }

    @Test
    fun reportsDivisionByZeroAndCircularReferences() {
        assertEquals("#DIV/0!", SpreadsheetFormulaEvaluator.evaluate("=5/0", emptyMap()))
        assertEquals("#VALUE!", SpreadsheetFormulaEvaluator.evaluate("=A1+1", mapOf("A1" to "=A1+2")))
    }
}
