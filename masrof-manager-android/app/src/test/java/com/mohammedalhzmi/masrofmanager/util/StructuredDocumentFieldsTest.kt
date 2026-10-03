package com.mohammedalhzmi.masrofmanager.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class StructuredDocumentFieldsTest {
    @Test
    fun encodesAndDecodesArabicValuesAndMultilineText() {
        val fields = mapOf(
            "supplierName" to "مؤسسة النظافة",
            "items" to "وقود ديزل\nزيوت",
            "totalAfterTax" to "١٢٥٠٠٠"
        )
        val encoded = StructuredDocumentFields.encode(fields)
        assertTrue(encoded.startsWith("MASROF_STRUCTURED_FIELDS_V1|"))
        assertEquals(fields, StructuredDocumentFields.decode(encoded))
    }

    @Test
    fun legacyOrEmptyPayloadDoesNotCreateFields() {
        assertEquals(emptyMap<String, String>(), StructuredDocumentFields.decode(null))
        assertEquals(emptyMap<String, String>(), StructuredDocumentFields.decode("legacy details"))
    }
}
