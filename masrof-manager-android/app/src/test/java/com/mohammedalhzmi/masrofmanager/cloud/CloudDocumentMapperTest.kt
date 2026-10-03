package com.mohammedalhzmi.masrofmanager.cloud

import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.util.BranchAssetData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CloudDocumentMapperTest {
    @Test
    fun readsOriginalReleaseFieldNamesAndOwnership() {
        val mapped = CloudDocumentMapper.fromMap(
            cloudId = "android_42",
            data = mapOf(
                "type" to "ORDER",
                "documentNumber" to "42",
                "dateHijri" to "1448/01/01",
                "dateGregorian" to "2026-07-07",
                "status" to "DRAFT",
                "expenseItem" to "وقود",
                "requesterName" to "المستخدم",
                "managerName" to "المدير",
                "createdByUid" to "firebase-user-1",
                "createdAt" to 100L,
                "updatedAt" to 150L
            )
        )

        assertNotNull(mapped)
        assertEquals("android_42", mapped?.cloudId)
        assertEquals("firebase-user-1", mapped?.createdByUid)
        assertEquals("وقود", mapped?.financialCategory)
        assertEquals("المستخدم", mapped?.submittedBy)
        assertEquals("المدير", mapped?.approvedBy)
        assertEquals(DocumentStatus.DRAFT, mapped?.status)
    }

    @Test
    fun missingRemoteFieldsDoNotEraseExistingViolationData() {
        val local = sample(DocumentType.VIOLATION_REPORT).copy(
            incidentTime = "10:30",
            incidentDay = "الأحد",
            regionName = "المديرية",
            regionOfficerName = "المسؤول",
            cloudId = "old-id",
            createdByUid = "owner-uid"
        )
        val mapped = CloudDocumentMapper.fromMap(
            cloudId = "remote-id",
            data = mapOf(
                "type" to "VIOLATION_REPORT",
                "documentNumber" to "V-7",
                "status" to "SUBMITTED",
                "updatedAt" to 200L
            ),
            existing = local
        )

        assertNotNull(mapped)
        assertEquals("10:30", mapped?.incidentTime)
        assertEquals("الأحد", mapped?.incidentDay)
        assertEquals("المديرية", mapped?.regionName)
        assertEquals("المسؤول", mapped?.regionOfficerName)
        assertEquals("remote-id", mapped?.cloudId)
        assertEquals("owner-uid", mapped?.createdByUid)
    }

    @Test
    fun writesCurrentAndLegacyCompatibleFields() {
        val document = sample(DocumentType.VIOLATION_REPORT).copy(
            financialCategory = "صيانة",
            submittedBy = "المستخدم",
            approvedBy = "المدير",
            incidentTime = "11:00",
            regionName = "المنطقة",
            witnessOne = "شاهد أول"
        )
        val map = CloudDocumentMapper.toMap(document, "cloud-uuid", "user-1", 500L)

        assertEquals("cloud-uuid", map["id"])
        assertEquals("user-1", map["createdByUid"])
        assertEquals("صيانة", map["expenseItem"])
        assertEquals("صيانة", map["financialCategory"])
        assertEquals("المستخدم", map["requesterName"])
        assertEquals("المدير", map["managerName"])
        assertEquals("11:00", map["incidentTime"])
        assertEquals("المنطقة", map["regionName"])
        assertEquals("شاهد أول", map["witnessOne"])
        assertEquals(500L, map["updatedAt"])
    }

    @Test
    fun branchAssetPayloadSurvivesTheExistingCloudDocumentSchema() {
        val payload = BranchAssetData.encode(
            BranchAssetData.Equipment("مولد", "GX-270", "جيدة", "ADM-7", "ENG-2", "CH-4", "2024", "أحمد")
        )
        val source = sample(DocumentType.BOOK).copy(
            documentNumber = "BA-EQ-test",
            details = payload,
            tags = "branch_asset_equipment_v1"
        )
        val cloud = CloudDocumentMapper.toMap(source, "asset-cloud-id", "owner-uid", 999L)
        val restored = CloudDocumentMapper.fromMap("asset-cloud-id", cloud)

        assertNotNull(restored)
        assertEquals(DocumentType.BOOK, restored?.type)
        assertEquals(payload, restored?.details)
        assertEquals("owner-uid", restored?.createdByUid)
        assertTrue(BranchAssetData.isRecord(requireNotNull(restored)))
        assertEquals("مولد", BranchAssetData.decodeEquipment(requireNotNull(restored))?.name)
    }

    @Test
    fun legacyIdsOnlyAcceptNumericSuffixes() {
        assertEquals(42L, CloudDocumentMapper.legacyLocalId("android_42"))
        assertNull(CloudDocumentMapper.legacyLocalId("android_user_42"))
        assertNull(CloudDocumentMapper.legacyLocalId("uuid"))
    }

    @Test
    fun syncDoesNotClaimExistingOwnerlessCloudRecords() {
        assertEquals("", CloudDocumentMapper.syncCreatorUid("", "current-user", "", false))
        assertEquals("other-owner", CloudDocumentMapper.syncCreatorUid("", "current-user", "other-owner", false))
        assertEquals("current-user", CloudDocumentMapper.syncCreatorUid("", "current-user", null, true))
        assertEquals("", CloudDocumentMapper.syncCreatorUid("", "current-user", null, false))
    }

    private fun sample(type: DocumentType): Document = Document(
        type = type,
        documentNumber = "D-1",
        dateHijri = "1448/01/01",
        dateGregorian = "2026-07-07",
        amount = 10.0,
        amountWords = "عشرة",
        beneficiaryName = "مستفيد",
        purpose = "غرض",
        details = "تفاصيل",
        notes = "ملاحظات",
        status = DocumentStatus.SUBMITTED
    )
}
