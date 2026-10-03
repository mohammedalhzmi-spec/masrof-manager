package com.mohammedalhzmi.masrofmanager.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.functions.FirebaseFunctionsException
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.io.FileOutputStream
import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CancellationException
import com.mohammedalhzmi.masrofmanager.util.AppBackupManager
import com.mohammedalhzmi.masrofmanager.data.UserEntity
import com.mohammedalhzmi.masrofmanager.data.AuditLogEntity
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.util.AppRole
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity
import com.mohammedalhzmi.masrofmanager.util.AuthSecurity
import com.mohammedalhzmi.masrofmanager.util.AuthenticatedUser
import com.mohammedalhzmi.masrofmanager.util.UserSession
import com.mohammedalhzmi.masrofmanager.util.RememberedLogin
import com.mohammedalhzmi.masrofmanager.util.AiLayoutAssistant
import kotlinx.coroutines.flow.MutableStateFlow
import com.mohammedalhzmi.masrofmanager.cloud.CloudAccessState
import com.mohammedalhzmi.masrofmanager.cloud.CloudDeviceRequest
import com.mohammedalhzmi.masrofmanager.cloud.FirebaseCloudSyncService
import com.mohammedalhzmi.masrofmanager.util.BranchAssetData

class MasrofViewModel(
    private val repository: MasrofRepository,
    private val database: MasrofDatabase,
    private val cloudSyncService: FirebaseCloudSyncService
) : ViewModel() {
    val allDocuments = repository.allDocuments
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val archivedDocuments = repository.archivedDocuments
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val lastDocumentNumber = repository.getLastDocumentNumber()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    val allUsers = repository.allUsers.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val auditLogs = repository.auditLogs.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val cloudAccessState = MutableStateFlow(CloudAccessState())
    val cloudBusy = MutableStateFlow(false)
    val cloudOperationMessage = MutableStateFlow("")
    val pendingCloudDevices = MutableStateFlow<List<CloudDeviceRequest>>(emptyList())
    val designElements = MutableStateFlow<List<DesignElementEntity>>(emptyList())
    val activeDesign = MutableStateFlow<DocumentDesignEntity?>(null)
    private var activeDesignId: Long = 0
    private val undoStack = ArrayDeque<List<DesignElementEntity>>()
    private val redoStack = ArrayDeque<List<DesignElementEntity>>()
    var clipboard: DesignElementEntity? = null

    private fun snapshot() = designElements.value.map { it.copy() }
    private fun rememberChange() { undoStack.addLast(snapshot()); redoStack.clear() }

    fun loadDesign(type: DocumentType) {
        viewModelScope.launch(Dispatchers.IO) {
            val design = repository.getDesign(type) ?: run {
                val id = repository.saveDesign(DocumentDesignEntity(documentType = type.name, name = type.name))
                repository.getDesign(type) ?: DocumentDesignEntity(id = id, documentType = type.name, name = type.name)
            }
            activeDesignId = design.id
            activeDesign.value = design
            designElements.value = repository.designElements(design.id)
        }
    }
    fun updateDesign(design: DocumentDesignEntity) {
        activeDesign.value = design
        viewModelScope.launch(Dispatchers.IO) { repository.saveDesign(design.copy(updatedAt = System.currentTimeMillis())); activeDesign.value = repository.getDesign(DocumentType.valueOf(design.documentType)) }
    }
    fun applyAiCommands(commands: List<AiLayoutAssistant.Command>) {
        viewModelScope.launch(Dispatchers.IO) {
            commands.forEach { command ->
                when (command.action) {
                    "MOVE", "RESIZE", "ROTATE", "UPDATE_TEXT", "STYLE_TEXT" -> designElements.value.firstOrNull { it.id == command.targetId }?.let { e -> repository.updateDesignElement(e.copy(x = command.x ?: e.x, y = command.y ?: e.y, width = command.width ?: e.width, height = command.height ?: e.height, rotation = command.rotation ?: e.rotation, content = command.content ?: e.content, fontSize = command.fontSize ?: e.fontSize, textColor = command.textColor ?: e.textColor, textAlign = command.textAlign ?: e.textAlign)) }
                    "DELETE" -> designElements.value.firstOrNull { it.id == command.targetId }?.let { repository.deleteDesignElement(it) }
                    "ADD_TEXT", "ADD_SHAPE", "ADD_QR" -> repository.addDesignElement(DesignElementEntity(designId = activeDesignId, type = command.type ?: if (command.action == "ADD_QR") "QR" else "TEXT", content = command.content ?: "", x = command.x ?: 40f, y = command.y ?: 40f, width = command.width ?: 180f, height = command.height ?: 60f, zIndex = (designElements.value.maxOfOrNull { it.zIndex } ?: 0) + 1, fontSize = command.fontSize ?: 18f, textColor = command.textColor ?: "#000000", textAlign = command.textAlign ?: "START"))
                    "PAGE" -> activeDesign.value?.let { d -> repository.saveDesign(d.copy(pageWidth = command.pageWidth ?: d.pageWidth, pageHeight = command.pageHeight ?: d.pageHeight, orientation = command.orientation ?: d.orientation, marginLeft = command.margin ?: d.marginLeft, marginTop = command.margin ?: d.marginTop, marginRight = command.margin ?: d.marginRight, marginBottom = command.margin ?: d.marginBottom, backgroundColor = command.backgroundColor ?: d.backgroundColor)); activeDesign.value = repository.getDesign(DocumentType.valueOf(d.documentType)) }
                }
            }
            designElements.value = repository.designElements(activeDesignId)
        }
    }
    fun addDesignElement(element: DesignElementEntity) { rememberChange(); viewModelScope.launch(Dispatchers.IO) { repository.addDesignElement(element.copy(designId = activeDesignId)); designElements.value = repository.designElements(activeDesignId) } }
    fun updateDesignElement(element: DesignElementEntity) { rememberChange(); viewModelScope.launch(Dispatchers.IO) { repository.updateDesignElement(element); designElements.value = repository.designElements(activeDesignId) } }
    fun deleteDesignElement(element: DesignElementEntity) { rememberChange(); viewModelScope.launch(Dispatchers.IO) { repository.deleteDesignElement(element); designElements.value = repository.designElements(activeDesignId) } }
    fun moveLayer(element: DesignElementEntity, delta: Int) { viewModelScope.launch(Dispatchers.IO) { repository.setDesignLayer(element.id, (element.zIndex + delta).coerceAtLeast(0)); designElements.value = repository.designElements(activeDesignId) } }
    fun copyElement(element: DesignElementEntity) { clipboard = element.copy(id = 0) }
    fun pasteElement() { clipboard?.let { addDesignElement(it.copy(x = it.x + 16f, y = it.y + 16f, zIndex = (designElements.value.maxOfOrNull { item -> item.zIndex } ?: 0) + 1)) } }
    fun undo() { if (undoStack.isNotEmpty()) { val current = snapshot(); val previous = undoStack.removeLast(); redoStack.addLast(current); viewModelScope.launch(Dispatchers.IO) { repository.replaceDesignElements(activeDesignId, previous); designElements.value = repository.designElements(activeDesignId) } } }
    fun redo() { if (redoStack.isNotEmpty()) { val current = snapshot(); val next = redoStack.removeLast(); undoStack.addLast(current); viewModelScope.launch(Dispatchers.IO) { repository.replaceDesignElements(activeDesignId, next); designElements.value = repository.designElements(activeDesignId) } } }

    suspend fun ensureDefaultAdmin() {
        if (repository.userCount() == 0) repository.insertUser(UserEntity(username = "admin", passwordHash = AuthSecurity.hash("admin1234"), fullName = "مدير النظام", role = "ADMIN"))
        repository.archiveOlderThan(System.currentTimeMillis() - 365L * 24L * 60L * 60L * 1000L, System.currentTimeMillis())
    }

    suspend fun restoreRememberedUser(context: Context): AuthenticatedUser? {
        if (!RememberedLogin.enabled(context)) return null
        val username = RememberedLogin.username(context) ?: return null
        val user = repository.findActiveUser(username) ?: return null
        return AuthenticatedUser(user.id, user.username, user.fullName, runCatching { com.mohammedalhzmi.masrofmanager.util.AppRole.valueOf(user.role) }.getOrDefault(com.mohammedalhzmi.masrofmanager.util.AppRole.USER)).also { UserSession.current = it }
    }

    suspend fun authenticate(context: Context, username: String, password: String, remember: Boolean): AuthenticatedUser? {
        val identifier = username.trim()
        val lookupIdentifier = if (identifier.contains("@")) identifier.lowercase() else identifier
        val isEmail = identifier.contains("@")
        val cloudUser = if (isEmail) {
            runCatching { cloudSyncService.signIn(identifier, password) }
                .onSuccess { cloudAccessState.value = it }
                .onFailure { cloudOperationMessage.value = cloudErrorMessage(it) }
                .getOrNull()
                ?.takeIf { it.uid != null }
        } else null

        // Email login is intentionally cloud-first. Do not silently authenticate a
        // nonexistent Firebase account from the local Room database.
        if (isEmail && cloudUser == null) return null

        var user = repository.findActiveUser(lookupIdentifier)
        if (cloudUser != null) {
            val cloudRole = when (cloudUser.role) {
                "SYSTEM_ADMIN" -> AppRole.ADMIN
                "FINANCE_DIRECTOR" -> AppRole.FINANCE_MANAGER
                "ACCOUNTANT" -> AppRole.ACCOUNTANT
                else -> AppRole.USER
            }
            val synced = (user ?: UserEntity(username = lookupIdentifier, passwordHash = "", fullName = cloudUser.email ?: identifier, role = cloudRole.name))
                .copy(
                    username = user?.username ?: lookupIdentifier,
                    passwordHash = AuthSecurity.hash(password),
                    role = cloudRole.name,
                    active = true
                )
            if (user == null) repository.insertUser(synced) else repository.updateUser(synced)
            user = repository.findActiveUser(synced.username)
        }

        if (user != null && (!isEmail || AuthSecurity.verify(password, user.passwordHash))) {
            val auth = AuthenticatedUser(user.id, user.username, user.fullName, runCatching { AppRole.valueOf(user.role) }.getOrDefault(AppRole.USER))
            UserSession.current = auth
            if (remember) RememberedLogin.save(context, user.username) else RememberedLogin.clear(context)
            repository.addAudit(AuditLogEntity(userId = user.id, username = user.username, action = "LOGIN_SUCCESS", details = "تسجيل دخول ناجح"))
            return auth
        }
        repository.addAudit(AuditLogEntity(userId = user?.id, username = identifier, action = "LOGIN_FAILED", details = "محاولة دخول فاشلة"))
        return null
    }

    suspend fun registerUser(username: String, password: String, fullName: String, role: String): Boolean {
        if (repository.findActiveUser(username.trim()) != null) return false
        repository.insertUser(UserEntity(username = username.trim(), passwordHash = AuthSecurity.hash(password), fullName = fullName.trim(), role = role, active = true))
        repository.addAudit(AuditLogEntity(userId = null, username = username.trim(), action = "REGISTER_USER", details = "تسجيل حساب جديد بالدور $role"))
        return true
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

    fun refreshCloudAccess() {
        viewModelScope.launch(Dispatchers.IO) {
            cloudBusy.value = true
            try {
                cloudAccessState.value = cloudSyncService.currentAccessState()
            } catch (error: Throwable) {
                cloudOperationMessage.value = cloudErrorMessage(error)
            } finally {
                cloudBusy.value = false
            }
        }
    }

    fun signInToCloud(identifier: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            cloudBusy.value = true
            cloudOperationMessage.value = ""
            try {
                cloudAccessState.value = cloudSyncService.signIn(identifier, password)
            } catch (error: Throwable) {
                cloudOperationMessage.value = cloudErrorMessage(error)
            } finally {
                cloudBusy.value = false
            }
        }
    }

    fun requestCloudDeviceApproval() {
        viewModelScope.launch(Dispatchers.IO) {
            cloudBusy.value = true
            cloudOperationMessage.value = ""
            try {
                cloudAccessState.value = cloudSyncService.requestDeviceApproval()
            } catch (error: Throwable) {
                cloudOperationMessage.value = cloudErrorMessage(error)
            } finally {
                cloudBusy.value = false
            }
        }
    }

    fun signOutFromCloud() {
        cloudSyncService.signOut()
        cloudAccessState.value = CloudAccessState()
        pendingCloudDevices.value = emptyList()
        cloudOperationMessage.value = "تم تسجيل الخروج من حساب السحابة."
    }

    fun syncCloudDocuments() {
        viewModelScope.launch(Dispatchers.IO) {
            cloudBusy.value = true
            cloudOperationMessage.value = ""
            try {
                val report = cloudSyncService.syncDocuments(repository)
                cloudOperationMessage.value = "اكتمل الدمج الآمن: تنزيل ${report.downloaded}، رفع ${report.uploaded}، تخطي ${report.skipped}، تعارضات ${report.conflicts}. لم تُحذف مستندات محلية."
                cloudAccessState.value = cloudSyncService.currentAccessState()
            } catch (error: Throwable) {
                cloudOperationMessage.value = cloudErrorMessage(error)
            } finally {
                cloudBusy.value = false
            }
        }
    }

    fun loadPendingCloudDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            cloudBusy.value = true
            cloudOperationMessage.value = ""
            try {
                pendingCloudDevices.value = cloudSyncService.pendingDeviceRequests()
            } catch (error: Throwable) {
                cloudOperationMessage.value = cloudErrorMessage(error)
            } finally {
                cloudBusy.value = false
            }
        }
    }

    fun approveCloudDevice(request: CloudDeviceRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            cloudBusy.value = true
            cloudOperationMessage.value = ""
            try {
                cloudSyncService.approveDevice(request)
                pendingCloudDevices.value = cloudSyncService.pendingDeviceRequests()
                cloudAccessState.value = cloudSyncService.currentAccessState()
                cloudOperationMessage.value = "تم اعتماد الجهاز."
            } catch (error: Throwable) {
                cloudOperationMessage.value = cloudErrorMessage(error)
            } finally {
                cloudBusy.value = false
            }
        }
    }

    private fun cloudErrorMessage(error: Throwable): String {
        if (error is CancellationException) throw error
        val authCode = (error as? FirebaseAuthException)?.errorCode
        if (authCode != null) return when (authCode) {
            "ERROR_INVALID_EMAIL" -> "صيغة البريد الإلكتروني غير صحيحة."
            "ERROR_USER_DISABLED" -> "حساب Firebase معطّل؛ تواصل مع مسؤول النظام."
            "ERROR_WRONG_PASSWORD", "ERROR_USER_NOT_FOUND", "ERROR_INVALID_CREDENTIAL" -> "تعذر تسجيل الدخول؛ تحقق من البريد أو اسم المستخدم وكلمة المرور."
            "ERROR_TOO_MANY_REQUESTS" -> "محاولات كثيرة. انتظر قليلًا ثم حاول مرة أخرى."
            "ERROR_NETWORK_REQUEST_FAILED" -> "تعذر الاتصال بـ Firebase. تحقق من الإنترنت وحاول مرة أخرى."
            else -> "تعذر تسجيل الدخول إلى حساب Firebase."
        }
        if (error is FirebaseFunctionsException) return when (error.code) {
            FirebaseFunctionsException.Code.UNAUTHENTICATED -> "بيانات الدخول غير صحيحة أو تعذر إثبات هوية هذا الجهاز."
            FirebaseFunctionsException.Code.RESOURCE_EXHAUSTED -> "محاولات كثيرة. انتظر قليلًا ثم حاول مرة أخرى."
            FirebaseFunctionsException.Code.PERMISSION_DENIED -> error.message?.takeIf { it.isNotBlank() } ?: "رفض الخادم هذه العملية؛ تحقق من اعتماد الحساب والجهاز."
            FirebaseFunctionsException.Code.FAILED_PRECONDITION -> error.message?.takeIf { it.isNotBlank() } ?: "يلزم تحديث إعدادات الدخول أو إعادة تسجيل الدخول."
            FirebaseFunctionsException.Code.UNAVAILABLE -> "تعذر الاتصال بخدمة المزامنة. تحقق من الإنترنت وحاول مرة أخرى."
            else -> error.message?.takeIf { it.isNotBlank() } ?: "تعذر إكمال عملية السحابة."
        }
        val firestoreCode = (error as? FirebaseFirestoreException)?.code
        if (firestoreCode != null) return when (firestoreCode) {
            FirebaseFirestoreException.Code.PERMISSION_DENIED -> "رفضت قواعد Firebase العملية؛ راجع صلاحيات الحساب وقواعد Firestore المنشورة."
            FirebaseFirestoreException.Code.UNAVAILABLE -> "خدمة Firebase غير متاحة الآن؛ تحقق من الإنترنت وحاول لاحقًا."
            else -> "تعذر إكمال طلب Firebase."
        }
        if (error.message?.contains("google-services.json", ignoreCase = true) == true) {
            return "إعداد Firebase غير موجود؛ أضف ملف google-services.json الصحيح ثم أعد البناء."
        }
        if (error is IllegalArgumentException && !error.message.isNullOrBlank()) return error.message.orEmpty()
        if (error.message?.contains("network", ignoreCase = true) == true) {
            return "تعذر الاتصال بـ Firebase. تحقق من الإنترنت وحاول مرة أخرى."
        }
        return "تعذر إكمال عملية السحابة. تحقق من إعداد Firebase وصلاحيات الحساب."
    }

    fun addDocument(document: Document) {
        viewModelScope.launch {
            val submittedBy = document.submittedBy.ifBlank { UserSession.current?.fullName.orEmpty() }
            val now = System.currentTimeMillis()
            repository.insert(document.copy(submittedBy = submittedBy, updatedAt = now))
            audit("CREATE_DOCUMENT", "${document.documentNumber} — الحالة: ${document.status.name}")
        }
    }

    /** Save branch assets locally first, then sync only this row when the cloud gate is ready. */
    fun saveBranchAsset(document: Document, onComplete: (locallySaved: Boolean, message: String) -> Unit) {
        viewModelScope.launch {
            var savedId: Long? = null
            try {
                require(BranchAssetData.isRecord(document)) { "بيانات ممتلكات الفرع غير صالحة." }
                savedId = withContext(Dispatchers.IO) {
                    val now = System.currentTimeMillis()
                    val submittedBy = document.submittedBy.ifBlank { UserSession.current?.fullName.orEmpty() }
                    if (document.id == 0L) {
                        repository.insert(document.copy(submittedBy = submittedBy, updatedAt = now))
                    } else {
                        repository.update(document.copy(submittedBy = submittedBy, updatedAt = now))
                        document.id
                    }
                }
                audit("SAVE_BRANCH_ASSET", document.documentNumber)
                val cloudResult = withContext(Dispatchers.IO) {
                    cloudSyncService.syncBranchAsset(requireNotNull(savedId), repository)
                }
                onComplete(true, cloudResult.message)
            } catch (error: Throwable) {
                if (error is CancellationException) throw error
                val message = if (savedId != null) {
                    "تم حفظ السجل محليًا؛ تعذرت المزامنة السحابية الآن. ${cloudErrorMessage(error)}"
                } else {
                    "تعذر حفظ السجل: ${cloudErrorMessage(error)}"
                }
                onComplete(savedId != null, message)
            }
        }
    }

    fun transitionDocument(document: Document, target: DocumentStatus) {
        viewModelScope.launch(Dispatchers.IO) {
            val role = UserSession.current?.role ?: AppRole.ADMIN
            val canApprove = role == AppRole.ADMIN || role == AppRole.FINANCE_MANAGER
            val canReceive = canApprove || role == AppRole.ACCOUNTANT
            val allowed = when (target) {
                DocumentStatus.APPROVED, DocumentStatus.PAID, DocumentStatus.CANCELLED -> canApprove
                DocumentStatus.RECEIVED -> canReceive
                else -> false
            }
            if (!allowed) { audit("WORKFLOW_DENIED", "${document.documentNumber} → ${target.name}"); return@launch }
            val now = System.currentTimeMillis()
            val actor = UserSession.current?.fullName.orEmpty()
            val updated = document.copy(
                status = target,
                reviewedBy = if (target == DocumentStatus.APPROVED) actor else document.reviewedBy,
                approvedBy = if (target == DocumentStatus.APPROVED || target == DocumentStatus.PAID) actor else document.approvedBy,
                approvedAt = if (target == DocumentStatus.APPROVED && document.approvedAt == null) now else document.approvedAt,
                paidAt = if (target == DocumentStatus.PAID) now else document.paidAt,
                rejectionReason = if (target == DocumentStatus.CANCELLED) "تم الإلغاء بواسطة $actor" else document.rejectionReason,
                updatedAt = now
            )
            repository.update(updated)
            audit("WORKFLOW_${target.name}", "${document.documentNumber} — بواسطة $actor")
        }
    }

    fun updateDocument(document: Document) {
        viewModelScope.launch { repository.update(document.copy(updatedAt = System.currentTimeMillis())); audit("UPDATE_DOCUMENT", document.documentNumber) }
    }

    fun deleteDocument(document: Document) {
        viewModelScope.launch { repository.delete(document); audit("DELETE_DOCUMENT", document.documentNumber) }
    }

    fun archiveDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) { repository.archive(document, System.currentTimeMillis()); audit("ARCHIVE_DOCUMENT", document.documentNumber) }
    }

    fun restoreDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) { repository.restore(document, System.currentTimeMillis()); audit("RESTORE_DOCUMENT", document.documentNumber) }
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
