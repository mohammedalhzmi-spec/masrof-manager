package com.mohammedalhzmi.masrofmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.FileOutputStream
import android.content.Context
import android.net.Uri
import com.mohammedalhzmi.masrofmanager.util.AppBackupManager
import com.mohammedalhzmi.masrofmanager.data.UserEntity
import com.mohammedalhzmi.masrofmanager.data.AuditLogEntity
import com.mohammedalhzmi.masrofmanager.util.AuthSecurity
import com.mohammedalhzmi.masrofmanager.util.AuthenticatedUser
import com.mohammedalhzmi.masrofmanager.util.UserSession

class MasrofViewModel(
    private val repository: MasrofRepository,
    private val database: MasrofDatabase
) : ViewModel() {
    val allDocuments = repository.allDocuments
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val lastDocumentNumber = repository.getLastDocumentNumber()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    val allUsers = repository.allUsers.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val auditLogs = repository.auditLogs.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun ensureDefaultAdmin() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.userCount() == 0) repository.insertUser(UserEntity(username = "admin", passwordHash = AuthSecurity.hash("admin1234"), fullName = "مدير النظام", role = "ADMIN"))
        }
    }

    suspend fun authenticate(username: String, password: String): AuthenticatedUser? {
        val user = repository.findActiveUser(username.trim())
        if (user != null && AuthSecurity.verify(password, user.passwordHash)) {
            val auth = AuthenticatedUser(user.id, user.username, user.fullName, runCatching { com.mohammedalhzmi.masrofmanager.util.AppRole.valueOf(user.role) }.getOrDefault(com.mohammedalhzmi.masrofmanager.util.AppRole.USER))
            UserSession.current = auth
            repository.addAudit(AuditLogEntity(userId = user.id, username = user.username, action = "LOGIN_SUCCESS", details = "تسجيل دخول ناجح"))
            return auth
        }
        repository.addAudit(AuditLogEntity(userId = user?.id, username = username, action = "LOGIN_FAILED", details = "محاولة دخول فاشلة"))
        return null
    }

    fun createUser(username: String, password: String, fullName: String, role: String) {
        viewModelScope.launch(Dispatchers.IO) { repository.insertUser(UserEntity(username = username.trim(), passwordHash = AuthSecurity.hash(password), fullName = fullName, role = role)); audit("CREATE_USER", username) }
    }
    fun updateUser(user: UserEntity, password: String?, fullName: String, role: String, active: Boolean) {
        viewModelScope.launch(Dispatchers.IO) { repository.updateUser(user.copy(passwordHash = password?.takeIf { it.isNotBlank() }?.let(AuthSecurity::hash) ?: user.passwordHash, fullName = fullName, role = role, active = active)); audit("UPDATE_USER", user.username) }
    }
    fun deleteUser(user: UserEntity) { viewModelScope.launch(Dispatchers.IO) { repository.deleteUser(user); audit("DELETE_USER", user.username) } }
    fun recordAudit(action: String, details: String) { viewModelScope.launch(Dispatchers.IO) { audit(action, details) } }
    private suspend fun audit(action: String, details: String) { UserSession.current?.let { repository.addAudit(AuditLogEntity(userId = it.id, username = it.username, action = action, details = details)) } }

    fun addDocument(document: Document) {
        viewModelScope.launch {
            repository.insert(document)
            audit("CREATE_DOCUMENT", document.documentNumber)
        }
    }

    fun updateDocument(document: Document) {
        viewModelScope.launch { repository.update(document); audit("UPDATE_DOCUMENT", document.documentNumber) }
    }

    fun deleteDocument(document: Document) {
        viewModelScope.launch { repository.delete(document); audit("DELETE_DOCUMENT", document.documentNumber) }
    }

    fun exportDatabase(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            val dbFile = context.getDatabasePath("masrof-db")
            context.contentResolver.openOutputStream(uri)?.use { output ->
                FileInputStream(dbFile).use { input ->
                    input.copyTo(output)
                }
            }
        }
    }

    fun importDatabase(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            database.close()
            val dbFile = context.getDatabasePath("masrof-db")
            context.contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(dbFile).use { output ->
                    input.copyTo(output)
                }
            }
            // Room will re-open automatically
        }
    }

    fun exportFullBackup(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) { AppBackupManager.exportToUri(context, uri) }
    }

    fun importFullBackup(context: Context, uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            database.close()
            AppBackupManager.restoreFromUri(context, uri)
        }
    }
}
