package com.mohammedalhzmi.masrofmanager.cloud

import android.content.Context
import android.provider.Settings
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.FirebaseFirestoreException
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.messaging.FirebaseMessaging

object FirebaseCloudSync {
    const val MANAGER_EMAIL = "alhzmim57@gmail.com"

    data class AccessDecision(val allowed: Boolean, val isManager: Boolean, val role: String, val message: String)
    data class DeviceRequest(val id: String, val userId: String, val email: String, val deviceId: String, val deviceName: String, val requestedAt: Long)

    /** Firebase requires an email identifier; real emails remain unchanged while legacy handles use a stable namespace. */
    fun credentialsEmail(identifier: String): String {
        val value = identifier.trim().lowercase()
        return if (value.contains("@")) value else "$value@accounts.masrof-manager.local"
    }

    private fun usernameKey(username: String) = username.trim().lowercase().replace("/", "_")

    fun friendlyAuthError(error: Throwable): String = when ((error as? FirebaseAuthException)?.errorCode) {
        "ERROR_EMAIL_ALREADY_IN_USE" -> "هذا البريد أو اسم المستخدم مستخدم مسبقًا. استخدم تسجيل الدخول أو بريدًا آخر."
        "ERROR_INVALID_EMAIL" -> "البريد الإلكتروني غير صحيح."
        "ERROR_USER_NOT_FOUND", "ERROR_WRONG_PASSWORD", "ERROR_INVALID_CREDENTIAL" -> "بيانات الدخول غير صحيحة."
        "ERROR_WEAK_PASSWORD" -> "كلمة المرور ضعيفة؛ استخدم 6 أحرف أو أكثر."
        "ERROR_NETWORK_REQUEST_FAILED" -> "تعذر الاتصال بالخدمة. تحقق من الإنترنت وحاول مرة أخرى."
        "ERROR_TOO_MANY_REQUESTS" -> "تم تجاوز عدد المحاولات؛ انتظر قليلًا ثم حاول مرة أخرى."
        else -> when ((error as? FirebaseFirestoreException)?.code) {
            FirebaseFirestoreException.Code.PERMISSION_DENIED -> "تم تسجيل الدخول، لكن لا تملك صلاحية قراءة ملف المستخدم. انشر قواعد Firestore الصحيحة."
            FirebaseFirestoreException.Code.UNAVAILABLE -> "تعذر الاتصال بقاعدة Firestore. تحقق من الإنترنت ثم أعد المحاولة."
            else -> error.message ?: "تعذر إتمام عملية المصادقة."
        }
    }

    fun initialize(context: Context) {
        if (FirebaseApp.getApps(context).isEmpty()) {
            requireNotNull(FirebaseApp.initializeApp(context)) { "تعذر تهيئة Firebase. تحقق من google-services.json" }
        }
        // App Check is intentionally not enforced in the private sideload test build.
        // Play Integrity requires additional configuration for distribution outside Google Play.
    }

    private fun firestore(): FirebaseFirestore = FirebaseFirestore.getInstance().also { it.enableNetwork() }

    suspend fun signIn(email: String, password: String): String {
        val result = FirebaseAuth.getInstance().signInWithEmailAndPassword(credentialsEmail(email), password).await()
        return result.user?.uid ?: error("لم يعد Firebase مستخدمًا بعد تسجيل الدخول")
    }

    suspend fun signInByEmail(email: String, password: String): String {
        val result = FirebaseAuth.getInstance().signInWithEmailAndPassword(email.trim().lowercase(), password).await()
        return result.user?.uid ?: error("تعذر فتح الحساب")
    }

    suspend fun createFirstAccount(email: String, password: String): String {
        val cleanEmail = email.trim().lowercase()
        require(cleanEmail.contains("@") && cleanEmail.contains(".")) { "البريد الإلكتروني غير صحيح" }
        val result = FirebaseAuth.getInstance().createUserWithEmailAndPassword(cleanEmail, password).await()
        val uid = result.user?.uid ?: error("تعذر إنشاء حساب Firebase")
        val manager = cleanEmail == MANAGER_EMAIL
        firestore().collection("users").document(uid).set(
            mapOf("id" to uid, "email" to cleanEmail, "username" to cleanEmail,
                "fullName" to cleanEmail,
                "role" to if (manager) "SYSTEM_ADMIN" else "ADMIN_USER", "active" to true,
                "createdAt" to System.currentTimeMillis())
        ).await()
        return uid
    }

    suspend fun signInByUsername(username: String, password: String): String {
        val alias = firestore().collection("authAliases").document(usernameKey(username)).get().await().data
        val email = alias?.get("authEmail")?.toString() ?: credentialsEmail(username)
        val result = FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: error("تعذر فتح حساب المستخدم")
    }

    suspend fun bindUsername(username: String, email: String, fullName: String? = null) {
        val user = FirebaseAuth.getInstance().currentUser ?: error("يجب تسجيل الدخول بالبريد أولاً")
        val key = usernameKey(username)
        require(key.isNotBlank()) { "اسم المستخدم مطلوب" }
        firestore().collection("authAliases").document(key).set(
            mapOf("username" to username.trim(), "authEmail" to email.trim().lowercase(), "uid" to user.uid, "updatedAt" to System.currentTimeMillis()),
            com.google.firebase.firestore.SetOptions.merge()
        ).await()
        firestore().collection("users").document(user.uid).set(
            mapOf("username" to username.trim(), "email" to email.trim().lowercase(), "fullName" to (fullName ?: user.displayName ?: username.trim()), "updatedAt" to System.currentTimeMillis()),
            com.google.firebase.firestore.SetOptions.merge()
        ).await()
    }

    suspend fun register(email: String, password: String, fullName: String): String {
        val loginEmail = credentialsEmail(email)
        val result = FirebaseAuth.getInstance().createUserWithEmailAndPassword(loginEmail, password).await()
        val uid = result.user?.uid ?: error("تعذر إنشاء حساب Firebase")
        firestore().collection("users").document(uid).set(
            mapOf("id" to uid, "username" to email.trim(), "email" to loginEmail,
                "fullName" to fullName, "role" to "ADMIN_USER", "active" to true,
                "createdAt" to System.currentTimeMillis())
        ).await()
        return uid
    }

    suspend fun currentProfile(): Map<String, Any?> {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return emptyMap()
        return firestore().collection("users").document(uid).get().await().data ?: emptyMap()
    }

    fun signOut() { FirebaseAuth.getInstance().signOut() }

    suspend fun sendPasswordReset(identifier: String) {
        val email = if (identifier.contains("@")) identifier.trim().lowercase() else {
            firestore().collection("authAliases").document(usernameKey(identifier)).get().await().data?.get("authEmail")?.toString()
                ?: credentialsEmail(identifier)
        }
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).await()
    }

    suspend fun sendEmailVerification() {
        FirebaseAuth.getInstance().currentUser?.sendEmailVerification()?.await()
            ?: error("لا يوجد حساب مسجل")
    }

    fun isEmailVerified() = FirebaseAuth.getInstance().currentUser?.isEmailVerified == true

    suspend fun authorizeCurrentDevice(context: Context): AccessDecision {
        firestore().enableNetwork().await()
        val authUser = FirebaseAuth.getInstance().currentUser ?: return AccessDecision(false, false, "", "يجب تسجيل الدخول أولًا")
        val email = authUser.email?.trim()?.lowercase().orEmpty()
        val isManager = email == MANAGER_EMAIL
        val userRef = firestore().collection("users").document(authUser.uid)
        val existingProfile = userRef.get().await().data
        val role = if (isManager) "SYSTEM_ADMIN" else (existingProfile?.get("role")?.toString() ?: "ADMIN_USER")
        val profile = mapOf(
            "id" to authUser.uid, "email" to email,
            "username" to (existingProfile?.get("username")?.toString() ?: email),
            "fullName" to (existingProfile?.get("fullName")?.toString() ?: if (isManager) "مدير النظام" else email),
            "role" to role, "active" to (existingProfile?.get("active") as? Boolean ?: true),
            "updatedAt" to System.currentTimeMillis()
        )
        userRef.set(profile, com.google.firebase.firestore.SetOptions.merge()).await()

        val deviceId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID).orEmpty().ifBlank { "unknown-${authUser.uid.take(8)}" }
        val deviceRef = firestore().collection("devices").document("${authUser.uid}_$deviceId")
        val deviceSnapshot = deviceRef.get().await()
        val current = deviceSnapshot.data
        val approved = isManager || (current?.get("status") == "APPROVED" && current["userId"] == authUser.uid)
        val token = runCatching { FirebaseMessaging.getInstance().token.await() }.getOrNull().orEmpty()
        deviceRef.set(mapOf(
            "deviceId" to deviceId, "userId" to authUser.uid, "email" to email,
            "deviceName" to "Android ${android.os.Build.MODEL}", "fcmToken" to token,
            "status" to if (isManager) "APPROVED" else if (approved) "APPROVED" else "PENDING",
            "approved" to approved, "lastSeenAt" to System.currentTimeMillis(),
            "createdAt" to (current?.get("createdAt") ?: System.currentTimeMillis())
        ), com.google.firebase.firestore.SetOptions.merge()).await()
        if (!isManager && !approved) {
            firestore().collection("accessRequests").document("${authUser.uid}_$deviceId").set(mapOf(
                "userId" to authUser.uid, "email" to email, "deviceId" to deviceId,
                "status" to "PENDING", "requestedAt" to System.currentTimeMillis()
            ), com.google.firebase.firestore.SetOptions.merge()).await()
            return AccessDecision(false, false, role, "تم إرسال طلب اعتماد هذا الجهاز إلى المدير")
        }
        return AccessDecision(true, isManager, role, "تم اعتماد الجهاز")
    }

    suspend fun pendingDeviceRequests(): List<DeviceRequest> {
        val snapshot = firestore().collection("accessRequests").whereEqualTo("status", "PENDING").get().await()
        return snapshot.documents.map { d -> DeviceRequest(d.id, d.getString("userId").orEmpty(), d.getString("email").orEmpty(), d.getString("deviceId").orEmpty(), d.getString("deviceName") ?: "جهاز Android", d.getLong("requestedAt") ?: 0L) }
    }

    suspend fun approveDevice(request: DeviceRequest) {
        val actor = FirebaseAuth.getInstance().currentUser?.uid ?: error("يجب أن تكون جلسة المدير فعالة")
        firestore().collection("devices").document("${request.userId}_${request.deviceId}").set(mapOf("status" to "APPROVED", "approved" to true, "approvedBy" to actor, "approvedAt" to System.currentTimeMillis()), com.google.firebase.firestore.SetOptions.merge()).await()
        firestore().collection("accessRequests").document(request.id).set(mapOf("status" to "APPROVED", "approvedBy" to actor, "approvedAt" to System.currentTimeMillis()), com.google.firebase.firestore.SetOptions.merge()).await()
        firestore().collection("notifications").add(mapOf("userId" to request.userId, "title" to "اعتماد الجهاز", "body" to "تم اعتماد جهازك من المدير", "read" to false, "createdAt" to System.currentTimeMillis())).await()
        firestore().collection("auditLogs").add(mapOf("actorUid" to actor, "action" to "APPROVE_DEVICE", "targetUserId" to request.userId, "targetDeviceId" to request.deviceId, "timestamp" to System.currentTimeMillis())).await()
    }

    suspend fun syncDocuments(repository: MasrofRepository): Int {
        if (FirebaseAuth.getInstance().currentUser == null) return 0
        val snapshot = firestore().collection("documents").get().await()
        val documents = snapshot.documents.mapNotNull { remote -> remote.toLocalDocument() }
        if (documents.isNotEmpty()) repository.replaceRemoteDocuments(documents)
        return documents.size
    }

    suspend fun saveDocument(document: Document) {
        val user = FirebaseAuth.getInstance().currentUser ?: return
        val id = document.cloudId.ifBlank { "android_${document.id}" }
        firestore().collection("documents").document(id).set(mapOf(
            "id" to id, "type" to document.type.name, "documentNumber" to document.documentNumber,
            "dateHijri" to document.dateHijri, "dateGregorian" to document.dateGregorian,
            "amount" to document.amount, "amountWords" to document.amountWords,
            "beneficiaryName" to document.beneficiaryName, "purpose" to document.purpose,
            "details" to document.details, "notes" to document.notes, "status" to document.status.name,
            "attachmentsCount" to document.attachmentsCount, "createdAt" to document.createdAt,
            "isArchived" to document.isArchived, "archivedAt" to document.archivedAt,
            "updatedAt" to System.currentTimeMillis(), "tags" to document.tags.split(',').filter { it.isNotBlank() },
            "expenseItem" to document.financialCategory, "costCenter" to document.costCenter,
            "fundingSource" to document.fundingSource, "beneficiaryId" to document.beneficiaryId,
            "requesterName" to document.submittedBy, "managerName" to document.approvedBy,
            "createdByUid" to user.uid
        ), com.google.firebase.firestore.SetOptions.merge()).await()
    }

    private fun com.google.firebase.firestore.DocumentSnapshot.toLocalDocument(): Document? {
        val type = runCatching { DocumentType.valueOf(getString("type") ?: "ORDER") }.getOrDefault(DocumentType.ORDER)
        val status = runCatching { DocumentStatus.valueOf(getString("status") ?: "SUBMITTED") }.getOrDefault(DocumentStatus.SUBMITTED)
        val createdAt = getLong("createdAt") ?: System.currentTimeMillis()
        val remoteId = id.hashCode().toLong().let { if (it == 0L) 1L else kotlin.math.abs(it) }
        val tags = (get("tags") as? List<*>)?.filterNotNull()?.joinToString(",") ?: (getString("tags") ?: "")
        return Document(
            id = remoteId,
            type = type,
            documentNumber = getString("documentNumber") ?: getString("serialNumber") ?: id,
            dateHijri = getString("dateHijri") ?: "",
            dateGregorian = getString("dateGregorian") ?: getString("dateString") ?: "",
            amount = getDouble("amount"),
            amountWords = getString("amountWords"),
            beneficiaryName = getString("beneficiaryName") ?: getString("beneficiary"),
            purpose = getString("purpose") ?: getString("title"),
            details = getString("details"), notes = getString("notes"), status = status,
            attachmentsCount = (getLong("attachmentsCount") ?: 0L).toInt(),
            createdAt = createdAt, isArchived = getBoolean("isArchived") ?: false,
            archivedAt = getLong("archivedAt"), updatedAt = getLong("updatedAt") ?: createdAt,
            tags = tags, financialCategory = getString("expenseItem") ?: "",
            costCenter = getString("costCenter") ?: "", fundingSource = getString("fundingSource") ?: "",
            beneficiaryId = getString("beneficiaryId") ?: "", submittedBy = getString("requesterName") ?: "",
            reviewedBy = "", approvedBy = getString("managerName") ?: "",
            approvedAt = null, paidAt = null, rejectionReason = "", cloudId = id
        )
    }
}
