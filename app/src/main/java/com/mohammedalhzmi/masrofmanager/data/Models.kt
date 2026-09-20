package com.mohammedalhzmi.masrofmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "documents")
data class Document(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: DocumentType, // REQUEST, ORDER, RECEIPT
    val documentNumber: String,
    val dateHijri: String,
    val dateGregorian: String,
    val amount: Double?,
    val amountWords: String?,
    val beneficiaryName: String?,
    val purpose: String?, // "وذلك مقابل"
    val details: String?, // "تفاصيل" or "بيان"
    val notes: String?,
    val status: DocumentStatus,
    val attachmentsCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "organization_profile")
data class OrganizationProfile(
    @PrimaryKey val id: Int = 1, // Only one profile allowed
    val ministryName: String,
    val administrationName: String,
    val branchName: String,
    val address: String,
    val phone: String,
    val logoPath: String?, // Path to saved image
    val managerName: String,
    val financeManagerName: String,
    val auditorName: String,
    val treasurerName: String,
    val signatureManagerPath: String?, // Path to image
    val signatureFinancePath: String?,
    val signatureTreasurerPath: String?
)

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: ContactType // BENEFICIARY, OFFICIAL
)

enum class ContactType { BENEFICIARY, OFFICIAL }

enum class DocumentType {
    REQUEST, ORDER, RECEIPT
}

enum class DocumentStatus {
    DRAFT, SUBMITTED, APPROVED, PAID, RECEIVED, CANCELLED
}
