package com.mohammedalhzmi.masrofmanager.cloud

import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType

/** Converts legacy and current Firestore document shapes without destructive defaults. */
internal object CloudDocumentMapper {
    fun fromMap(
        cloudId: String,
        data: Map<String, Any?>,
        existing: Document? = null
    ): Document? {
        val rawType = firstString(data, listOf("type"), existing?.type?.name.orEmpty())
        val type = runCatching { DocumentType.valueOf(rawType) }.getOrNull() ?: return null
        val rawStatus = firstString(data, listOf("status"), existing?.status?.name ?: DocumentStatus.SUBMITTED.name)
        val status = runCatching { DocumentStatus.valueOf(rawStatus) }.getOrDefault(existing?.status ?: DocumentStatus.SUBMITTED)

        val createdAt = longValue(data, "createdAt", existing?.createdAt ?: System.currentTimeMillis())
        return Document(
            id = existing?.id ?: 0L,
            type = type,
            documentNumber = firstString(data, listOf("documentNumber", "serialNumber"), existing?.documentNumber.orEmpty()),
            dateHijri = firstString(data, listOf("dateHijri"), existing?.dateHijri.orEmpty()),
            dateGregorian = firstString(data, listOf("dateGregorian", "dateString"), existing?.dateGregorian.orEmpty()),
            amount = nullableDouble(data, "amount", existing?.amount),
            amountWords = nullableString(data, "amountWords", existing?.amountWords),
            beneficiaryName = nullableString(data, listOf("beneficiaryName", "beneficiary"), existing?.beneficiaryName),
            purpose = nullableString(data, listOf("purpose", "title"), existing?.purpose),
            details = nullableString(data, "details", existing?.details),
            notes = nullableString(data, "notes", existing?.notes),
            status = status,
            attachmentsCount = intValue(data, "attachmentsCount", existing?.attachmentsCount ?: 0),
            createdAt = createdAt,
            isArchived = booleanValue(data, "isArchived", existing?.isArchived ?: false),
            archivedAt = nullableLong(data, "archivedAt", existing?.archivedAt),
            updatedAt = longValue(data, "updatedAt", existing?.updatedAt ?: createdAt),
            tags = tagsValue(data, existing?.tags.orEmpty()),
            financialCategory = firstString(data, listOf("expenseItem", "financialCategory"), existing?.financialCategory.orEmpty()),
            costCenter = firstString(data, listOf("costCenter"), existing?.costCenter.orEmpty()),
            fundingSource = firstString(data, listOf("fundingSource"), existing?.fundingSource.orEmpty()),
            beneficiaryId = firstString(data, listOf("beneficiaryId"), existing?.beneficiaryId.orEmpty()),
            submittedBy = firstString(data, listOf("requesterName", "submittedBy"), existing?.submittedBy.orEmpty()),
            reviewedBy = firstString(data, listOf("reviewedBy"), existing?.reviewedBy.orEmpty()),
            approvedBy = firstString(data, listOf("approvedBy", "managerName"), existing?.approvedBy.orEmpty()),
            approvedAt = nullableLong(data, "approvedAt", existing?.approvedAt),
            paidAt = nullableLong(data, "paidAt", existing?.paidAt),
            rejectionReason = firstString(data, listOf("rejectionReason"), existing?.rejectionReason.orEmpty()),
            incidentTime = nullableString(data, "incidentTime", existing?.incidentTime),
            incidentDay = nullableString(data, "incidentDay", existing?.incidentDay),
            incidentLocation = nullableString(data, "incidentLocation", existing?.incidentLocation),
            violationType = nullableString(data, "violationType", existing?.violationType),
            responsibleAction = nullableString(data, "responsibleAction", existing?.responsibleAction),
            lawArticle = nullableString(data, "lawArticle", existing?.lawArticle),
            witnessOne = nullableString(data, "witnessOne", existing?.witnessOne),
            witnessTwo = nullableString(data, "witnessTwo", existing?.witnessTwo),
            regionName = nullableString(data, "regionName", existing?.regionName),
            regionOfficerName = nullableString(data, "regionOfficerName", existing?.regionOfficerName),
            cloudId = cloudId,
            createdByUid = firstString(data, listOf("createdByUid"), existing?.createdByUid.orEmpty()),
            structuredFields = firstString(data, listOf("structuredFields"), existing?.structuredFields.orEmpty())
        )
    }

    fun toMap(document: Document, cloudId: String, creatorUid: String, updatedAt: Long): Map<String, Any?> {
        val tags = document.tags.split(',').map { it.trim() }.filter { it.isNotEmpty() }
        val ownerUid = document.createdByUid.ifBlank { creatorUid }
        return mapOf(
            "id" to cloudId,
            "type" to document.type.name,
            "documentNumber" to document.documentNumber,
            "dateHijri" to document.dateHijri,
            "dateGregorian" to document.dateGregorian,
            "amount" to document.amount,
            "amountWords" to document.amountWords,
            "beneficiaryName" to document.beneficiaryName,
            "purpose" to document.purpose,
            "details" to document.details,
            "notes" to document.notes,
            "status" to document.status.name,
            "attachmentsCount" to document.attachmentsCount,
            "createdAt" to document.createdAt,
            "isArchived" to document.isArchived,
            "archivedAt" to document.archivedAt,
            "updatedAt" to updatedAt,
            "tags" to tags,
            // Keep the original release field as well as the maintained model name.
            "expenseItem" to document.financialCategory,
            "financialCategory" to document.financialCategory,
            "costCenter" to document.costCenter,
            "fundingSource" to document.fundingSource,
            "beneficiaryId" to document.beneficiaryId,
            "requesterName" to document.submittedBy,
            "submittedBy" to document.submittedBy,
            "reviewedBy" to document.reviewedBy,
            "managerName" to document.approvedBy,
            "approvedBy" to document.approvedBy,
            "approvedAt" to document.approvedAt,
            "paidAt" to document.paidAt,
            "rejectionReason" to document.rejectionReason,
            "incidentTime" to document.incidentTime,
            "incidentDay" to document.incidentDay,
            "incidentLocation" to document.incidentLocation,
            "violationType" to document.violationType,
            "responsibleAction" to document.responsibleAction,
            "lawArticle" to document.lawArticle,
            "witnessOne" to document.witnessOne,
            "witnessTwo" to document.witnessTwo,
            "regionName" to document.regionName,
            "regionOfficerName" to document.regionOfficerName,
            "createdByUid" to ownerUid,
            "structuredFields" to document.structuredFields
        )
    }

    fun syncCreatorUid(localOwner: String, currentUid: String, remoteOwner: String?, isNewLocalRecord: Boolean): String =
        when {
            localOwner.isNotBlank() -> localOwner
            remoteOwner != null -> remoteOwner
            isNewLocalRecord -> currentUid
            else -> ""
        }

    fun legacyLocalId(cloudId: String): Long? {
        val prefix = "android_"
        if (!cloudId.startsWith(prefix)) return null
        return cloudId.removePrefix(prefix).takeIf { it.isNotEmpty() && it.all { char -> char.isDigit() } }?.toLongOrNull()
    }

    fun matchesLegacyRecord(local: Document, remote: Document): Boolean =
        local.type == remote.type
            && local.documentNumber == remote.documentNumber
            && local.dateHijri == remote.dateHijri
            && local.dateGregorian == remote.dateGregorian

    private fun firstString(data: Map<String, Any?>, keys: List<String>, fallback: String): String {
        val presentKey = keys.firstOrNull { data.containsKey(it) } ?: return fallback
        return data[presentKey] as? String ?: ""
    }

    private fun nullableString(data: Map<String, Any?>, key: String, fallback: String?): String? =
        if (data.containsKey(key)) data[key] as? String else fallback

    private fun nullableString(data: Map<String, Any?>, keys: List<String>, fallback: String?): String? {
        val presentKey = keys.firstOrNull { data.containsKey(it) } ?: return fallback
        return data[presentKey] as? String
    }

    private fun longValue(data: Map<String, Any?>, key: String, fallback: Long): Long =
        if (data.containsKey(key)) (data[key] as? Number)?.toLong() ?: fallback else fallback

    private fun nullableLong(data: Map<String, Any?>, key: String, fallback: Long?): Long? =
        if (data.containsKey(key)) (data[key] as? Number)?.toLong() else fallback

    private fun intValue(data: Map<String, Any?>, key: String, fallback: Int): Int =
        if (data.containsKey(key)) (data[key] as? Number)?.toInt() ?: fallback else fallback

    private fun nullableDouble(data: Map<String, Any?>, key: String, fallback: Double?): Double? =
        if (data.containsKey(key)) (data[key] as? Number)?.toDouble() else fallback

    private fun booleanValue(data: Map<String, Any?>, key: String, fallback: Boolean): Boolean =
        if (data.containsKey(key)) data[key] as? Boolean ?: fallback else fallback

    private fun tagsValue(data: Map<String, Any?>, fallback: String): String {
        if (!data.containsKey("tags")) return fallback
        return when (val value = data["tags"]) {
            is List<*> -> value.filterIsInstance<String>().joinToString(",")
            is String -> value
            else -> fallback
        }
    }
}
