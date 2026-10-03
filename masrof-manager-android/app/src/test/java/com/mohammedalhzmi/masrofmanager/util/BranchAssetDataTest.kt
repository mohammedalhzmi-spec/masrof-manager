package com.mohammedalhzmi.masrofmanager.util

import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class BranchAssetDataTest {
    @Test
    fun equipmentRoundTripsArabicAndDelimiterCharacters() {
        val expected = BranchAssetData.Equipment(
            name = "مولد | احتياطي",
            model = "GX-270",
            condition = "جيدة: تحتاج صيانة",
            administrativeNumber = "ADM-007",
            machineNumber = "ENG-٢٥",
            chassisNumber = "CH-4",
            yearObtained = "2024",
            responsiblePerson = "أحمد محمد"
        )
        val saved = record(BranchAssetData.encode(expected))
        assertEquals(expected, BranchAssetData.decodeEquipment(saved))
        assertEquals(BranchAssetData.Kind.EQUIPMENT, BranchAssetData.kind(saved))
        assertTrue(BranchAssetData.isRecord(saved))
    }

    @Test
    fun supplyRoundTripsAndIsNotDecodedAsEquipment() {
        val expected = BranchAssetData.Supply("الثلاثاء", "2026/10/03", "حبر طابعة", "مستلزم مكتبي", "12500.50")
        val saved = record(BranchAssetData.encode(expected))
        assertEquals(expected, BranchAssetData.decodeSupply(saved))
        assertNull(BranchAssetData.decodeEquipment(saved))
        assertEquals(BranchAssetData.Kind.SUPPLY, BranchAssetData.kind(saved))
    }

    @Test
    fun ordinaryBookAndLegacyDetailsAreUntouched() {
        val ordinaryBook = record("محتوى صفحة دفتر عادي")
        assertFalse(BranchAssetData.isRecord(ordinaryBook))
        assertNull(BranchAssetData.decodeEquipment(ordinaryBook))
        assertNull(BranchAssetData.decodeSupply(ordinaryBook))
    }

    private fun record(details: String) = Document(
        type = DocumentType.BOOK,
        documentNumber = "0001",
        dateHijri = "",
        dateGregorian = "",
        amount = null,
        amountWords = null,
        beneficiaryName = "",
        purpose = "",
        details = details,
        notes = "",
        status = DocumentStatus.DRAFT
    )
}
