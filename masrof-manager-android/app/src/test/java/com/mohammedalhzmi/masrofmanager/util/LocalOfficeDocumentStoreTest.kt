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

    @Test
    fun officePayloadRoundTripsRoomLinkAndNumberedBookTag() {
        val local = LocalOfficeDocumentStore.create(context, OfficeDocumentKind.EXCEL, "كشف المصروفات")
            .copy(roomDocumentId = 37L, bookTag = "دفتر:كشف 2026", cells = mapOf("A1" to "اليوم", "B2" to "١٢٣"))
        val encoded = LocalOfficeDocumentStore.encode(local)
        val reopened = LocalOfficeDocumentStore.decode(encoded)

        assertEquals(local.id, reopened.id)
        assertEquals(37L, reopened.roomDocumentId)
        assertEquals("دفتر:كشف 2026", reopened.bookTag)
        assertEquals(local.cells, reopened.cells)

        val row = com.mohammedalhzmi.masrofmanager.data.Document(
            id = 37L,
            type = com.mohammedalhzmi.masrofmanager.data.DocumentType.BOOK,
            documentNumber = "OFFICE:${local.id}",
            dateHijri = "",
            dateGregorian = "",
            amount = null,
            amountWords = null,
            beneficiaryName = "",
            purpose = local.title,
            details = encoded,
            notes = "",
            status = com.mohammedalhzmi.masrofmanager.data.DocumentStatus.DRAFT,
            tags = "OFFICE,دفتر:كشف 2026",
            updatedAt = local.updatedAt,
            structuredFields = OfficeDocumentRecord.MARKER
        )
        val fromRoom = OfficeDocumentRecord.decode(row)
        assertTrue(OfficeDocumentRecord.isOfficeDocument(row))
        assertEquals(37L, fromRoom?.roomDocumentId)
        assertEquals(local.id, fromRoom?.id)
    }
}
