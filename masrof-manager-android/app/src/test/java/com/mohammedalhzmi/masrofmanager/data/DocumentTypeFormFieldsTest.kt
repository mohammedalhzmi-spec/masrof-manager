package com.mohammedalhzmi.masrofmanager.data

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DocumentTypeFormFieldsTest {
    @Test
    fun remainingGeneralFinancialDocumentsExposeAmountFields() {
        listOf(
            DocumentType.RECEIPT_MINUTES,
            DocumentType.FINANCIAL_CLAIM,
            DocumentType.CUSTODY_SETTLEMENT,
            DocumentType.ADVANCE_PERMIT,
            DocumentType.OFFICIAL_FINANCIAL_LETTER,
            DocumentType.FINANCIAL_MEMO,
            DocumentType.SUPPLY_PERMIT
        ).forEach { type ->
            assertTrue("${type.name} should expose an amount field", type.hasFinancialAmountField())
        }
    }

    @Test
    fun requestsAndExpenseBookTypesDoNotExposeGenericAmountFields() {
        listOf(
            DocumentType.REQUEST,
            DocumentType.EXPENSE_STATEMENT,
            DocumentType.EXPENSE_REPORT,
            DocumentType.BOOK
        ).forEach { type ->
            assertFalse("${type.name} should not expose a generic amount field", type.hasFinancialAmountField())
        }
    }
}
