package com.mohammedalhzmi.masrofmanager.data

import kotlinx.coroutines.flow.Flow

class MasrofRepository(
    private val documentDao: DocumentDao,
    private val settingsDao: SettingsDao,
    private val contactDao: ContactDao
    , private val userDao: UserDao
    , private val auditDao: AuditDao
) {
    // Document operations
    val allDocuments: Flow<List<Document>> = documentDao.getAllDocuments()
    fun getLastDocumentNumber() = documentDao.getLastDocumentNumber()
    suspend fun insert(document: Document) = documentDao.insert(document)
    suspend fun update(document: Document) = documentDao.update(document)
    suspend fun delete(document: Document) = documentDao.delete(document)

    // Settings operations
    fun getOrganizationProfile() = settingsDao.getOrganizationProfile()
    suspend fun saveOrganizationProfile(profile: OrganizationProfile) = settingsDao.saveOrganizationProfile(profile)

    // Contact operations
    fun getAllContacts() = contactDao.getAllContacts()
    fun getContactsByType(type: ContactType) = contactDao.getContactsByType(type)
    suspend fun insertContact(contact: ContactEntity) = contactDao.insertContact(contact)
    suspend fun deleteContact(contact: ContactEntity) = contactDao.deleteContact(contact)

    val allUsers: Flow<List<UserEntity>> = userDao.observeAll()
    val auditLogs: Flow<List<AuditLogEntity>> = auditDao.observeAll()
    suspend fun findActiveUser(username: String) = userDao.findActive(username)
    suspend fun insertUser(user: UserEntity) = userDao.insert(user)
    suspend fun updateUser(user: UserEntity) = userDao.update(user)
    suspend fun deleteUser(user: UserEntity) = userDao.delete(user)
    suspend fun userCount() = userDao.count()
    suspend fun addAudit(log: AuditLogEntity) = auditDao.insert(log)
}
