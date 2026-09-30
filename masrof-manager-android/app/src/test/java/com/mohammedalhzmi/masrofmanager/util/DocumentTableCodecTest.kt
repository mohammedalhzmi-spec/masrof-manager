package com.mohammedalhzmi.masrofmanager.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DocumentTableCodecTest {
    @Test
    fun tableDataRoundTripsAndPadsShortRows() {
        val rows = listOf(
            listOf("البند", "الكمية", "المبلغ"),
            listOf("نظافة", "2", "1500"),
            listOf("إجمالي", "3000")
        )
        val decoded = DocumentTableCodec.decode(DocumentTableCodec.encode(rows))
        assertEquals(3, decoded.size)
        assertEquals(3, decoded.first().size)
        assertEquals("البند", decoded[0][0])
        assertEquals("1500", decoded[1][2])
        assertEquals("", decoded[2][2])
    }

    @Test
    fun editorTextSplitsRowsAndCellsOnDocumentedSeparators() {
        assertEquals(
            listOf(listOf("البيان", "الكمية"), listOf("قرطاسية", "3")),
            DocumentTableCodec.parseEditorText("البيان | الكمية\nقرطاسية | 3")
        )
    }

    @Test
    fun unrelatedLegacyTextIsNotTreatedAsATable() {
        assertTrue(DocumentTableCodec.decode("تفاصيل قديمة للمستند").isEmpty())
    }
}
