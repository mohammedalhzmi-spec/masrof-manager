package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class LocalOfficeDocumentStoreTest {
    private val context: Context get() = RuntimeEnvironment.getApplication()

    @Test
    fun createsAndReopensWordDocumentLocally() {
        val created = LocalOfficeDocumentStore.create(context, OfficeDocumentKind.WORD, "محضر عربي")
        val edited = created.copy(wordHtml = "<p><b>نص عربي</b></p>", fontFamily = "amiri_regular", updatedAt = created.updatedAt + 10)
        LocalOfficeDocumentStore.save(context, edited)

        val reopened = LocalOfficeDocumentStore.list(context).single { it.id == created.id }
        assertEquals("محضر عربي", reopened.title)
        assertEquals(OfficeDocumentKind.WORD, reopened.kind)
        assertEquals("<p><b>نص عربي</b></p>", reopened.wordHtml)
        assertEquals("amiri_regular", reopened.fontFamily)
    }

    @Test
    fun createsAndReopensExcelCellsAndCanRemoveItsLocalFile() {
        val created = LocalOfficeDocumentStore.create(context, OfficeDocumentKind.EXCEL, "كشف")
        val edited = created.copy(cells = mapOf("A1" to "البيان", "B1" to "=SUM(B2:B5)"))
        LocalOfficeDocumentStore.save(context, edited)

        val reopened = LocalOfficeDocumentStore.list(context).single { it.id == created.id }
        assertEquals(OfficeDocumentKind.EXCEL, reopened.kind)
        assertEquals(edited.cells, reopened.cells)
        assertTrue(LocalOfficeDocumentStore.delete(context, created.id))
        assertFalse(LocalOfficeDocumentStore.list(context).any { it.id == created.id })
    }
}
