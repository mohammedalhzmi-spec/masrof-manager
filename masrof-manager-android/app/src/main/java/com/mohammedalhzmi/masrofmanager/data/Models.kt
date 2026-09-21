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
    val updatedAt: Long = createdAt, val tags: String = ""
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
enum class DocumentType { REQUEST, ORDER, RECEIPT }
enum class DocumentStatus { DRAFT, SUBMITTED, APPROVED, PAID, RECEIVED, CANCELLED }
