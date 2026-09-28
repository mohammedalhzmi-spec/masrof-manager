package com.mohammedalhzmi.masrofmanager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "documents")
data class Document(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: DocumentType, val documentNumber: String, val dateHijri: String, val dateGregorian: String,
    val amount: Double?, val amountWords: String?, val beneficiaryName: String?, val purpose: String?,
    val details: String?, val notes: String?, val status: DocumentStatus, val attachmentsCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis(), val isArchived: Boolean = false, val archivedAt: Long? = null,
    val updatedAt: Long = createdAt, val tags: String = "",
    val financialCategory: String = "", val costCenter: String = "", val fundingSource: String = "",
    val beneficiaryId: String = "", val submittedBy: String = "", val reviewedBy: String = "",
    val approvedBy: String = "", val approvedAt: Long? = null, val paidAt: Long? = null,
    val rejectionReason: String = "",
    val incidentTime: String? = null,
    val incidentDay: String? = null,
    val incidentLocation: String? = null,
    val violationType: String? = null,
    val responsibleAction: String? = null,
    val lawArticle: String? = null,
    val witnessOne: String? = null,
    val witnessTwo: String? = null,
    val regionName: String? = null,
    val regionOfficerName: String? = null
)

@Entity(tableName = "organization_profile")
data class OrganizationProfile(
    @PrimaryKey val id: Int = 1, val ministryName: String, val administrationName: String, val branchName: String,
    val address: String, val phone: String, val logoPath: String?, val managerName: String, val financeManagerName: String,
    val auditorName: String, val treasurerName: String, val signatureManagerPath: String?, val signatureFinancePath: String?, val signatureTreasurerPath: String?
)

@Entity(tableName = "contacts")
data class ContactEntity(@PrimaryKey(autoGenerate = true) val id: Long = 0, val name: String, val type: ContactType)

@Entity(tableName = "users", indices = [androidx.room.Index(value = ["username"], unique = true)])
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val username: String,
    val passwordHash: String,
    val fullName: String,
    val role: String,
    val active: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "audit_logs")
data class AuditLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long?, val username: String, val action: String, val details: String,
    val timestamp: Long = System.currentTimeMillis()
)

enum class ContactType { BENEFICIARY, OFFICIAL }
enum class DocumentType { REQUEST, ORDER, RECEIPT, VIOLATION_REPORT, EXPENSE_REPORT }
enum class DocumentStatus { DRAFT, SUBMITTED, APPROVED, PAID, RECEIVED, CANCELLED }
