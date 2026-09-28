package com.mohammedalhzmi.masrofmanager.cloud

import android.content.Context
import android.os.Build
import android.provider.Settings
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.Source
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository
import java.util.Locale
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine

enum class CloudGate { SIGNED_OUT, PROFILE_MISSING, ACCOUNT_INACTIVE, DEVICE_PENDING, READY }

data class CloudAccessState(
    val uid: String? = null,
    val email: String? = null,
    val role: String? = null,
    val gate: CloudGate = CloudGate.SIGNED_OUT,
    val message: String = "لم يتم تسجيل الدخول إلى حساب السحابة"
) {
    val signedIn: Boolean get() = uid != null
    val canSync: Boolean get() = gate == CloudGate.READY
    val canManageDevices: Boolean get() = signedIn && role == "SYSTEM_ADMIN" && gate in setOf(CloudGate.READY, CloudGate.DEVICE_PENDING)
}

data class CloudDeviceRequest(
    val id: String,
    val userId: String,
    val email: String,
    val deviceId: String,
    val deviceName: String,
    val requestedAt: Long
)

data class CloudSyncReport(
    val downloaded: Int,
    val uploaded: Int,
    val skipped: Int,
    val conflicts: Int
)

/** Firebase bridge for the Android app. Local Room data remains authoritative and is never cleared. */
class FirebaseCloudSyncService(context: Context) {
    private val appContext = context.applicationContext

    private fun app(): FirebaseApp =
        FirebaseApp.getApps(appContext).firstOrNull()
            ?: FirebaseApp.initializeApp(appContext)
            ?: throw IllegalStateException("إعداد Firebase غير موجود. أضف google-services.json الخاص بالمشروع ثم أعد البناء.")

    private fun auth(): FirebaseAuth = FirebaseAuth.getInstance(app())
    private fun firestore(): FirebaseFirestore = FirebaseFirestore.getInstance(app())

    suspend fun signIn(identifier: String, password: String): CloudAccessState {
        require(identifier.isNotBlank()) { "أدخل البريد الإلكتروني أو اسم المستخدم." }
        require(password.isNotEmpty()) { "أدخل كلمة المرور." }
        val db = firestore()
        val email = resolveEmail(identifier, db)
        auth().signInWithEmailAndPassword(email, password).awaitResult()
        return loadAccessState(createDeviceRequestIfMissing = true)
    }

    suspend fun currentAccessState(): CloudAccessState {
        if (FirebaseApp.getApps(appContext).isEmpty() && FirebaseApp.initializeApp(appContext) == null) {
            return CloudAccessState(message = "إعداد Firebase غير موجود. أضف google-services.json الخاص بالمشروع.")
        }
        if (auth().currentUser == null) return CloudAccessState()
        return loadAccessState(createDeviceRequestIfMissing = false)
    }

    suspend fun requestDeviceApproval(): CloudAccessState =
        loadAccessState(createDeviceRequestIfMissing = true)

    fun signOut() {
        if (FirebaseApp.getApps(appContext).isNotEmpty()) auth().signOut()
    }

    private suspend fun loadAccessState(createDeviceRequestIfMissing: Boolean): CloudAccessState {
        val user = auth().currentUser ?: return CloudAccessState()
        val uid = user.uid
        val email = user.email.orEmpty()
        val profileSnapshot = firestore().collection("users").document(uid).get(Source.SERVER).awaitResult()
        if (!profileSnapshot.exists()) {
            return CloudAccessState(uid, email, gate = CloudGate.PROFILE_MISSING, message = "لا يوجد ملف مستخدم سحابي لهذا الحساب.")
        }
        val role = profileSnapshot.getString("role") ?: "ADMIN_USER"
        if (profileSnapshot.getBoolean("active") != true) {
            return CloudAccessState(uid, email, role, CloudGate.ACCOUNT_INACTIVE, "الحساب غير معتمد أو غير مفعل في Firebase.")
        }

        val deviceId = androidDeviceId(uid)
        val deviceRef = firestore().collection("devices").document("${uid}_$deviceId")
        var deviceSnapshot = deviceRef.get(Source.SERVER).awaitResult()
        if (!deviceSnapshot.exists()) {
            if (createDeviceRequestIfMissing) {
                val now = System.currentTimeMillis()
                deviceRef.set(
                    mapOf(
                        "userId" to uid,
                        "deviceId" to deviceId,
                        "email" to email,
                        "deviceName" to "Android ${Build.MODEL.orEmpty()}",
                        "status" to "PENDING",
                        "approved" to false,
                        "createdAt" to now,
                        "lastSeenAt" to now
                    )
                ).awaitResult()
                ensureAccessRequest(uid, email, deviceId, now)
                deviceSnapshot = deviceRef.get(Source.SERVER).awaitResult()
            } else {
                return CloudAccessState(uid, email, role, CloudGate.DEVICE_PENDING, "هذا الجهاز غير مسجل للاعتماد بعد.")
            }
        } else if (createDeviceRequestIfMissing && !isApprovedDevice(deviceSnapshot)) {
            val now = System.currentTimeMillis()
            deviceRef.set(mapOf("lastSeenAt" to now), SetOptions.merge()).awaitResult()
            ensureAccessRequest(uid, email, deviceId, now)
        }

        val approved = isApprovedDevice(deviceSnapshot)
        if (!approved) {
            return CloudAccessState(uid, email, role, CloudGate.DEVICE_PENDING, "تم إرسال طلب اعتماد الجهاز أو ما زال بانتظار موافقة المدير.")
        }
        deviceRef.set(mapOf("lastSeenAt" to System.currentTimeMillis()), SetOptions.merge()).awaitResult()
        return CloudAccessState(uid, email, role, CloudGate.READY, "الحساب والجهاز معتمدان؛ يمكنك مزامنة المستندات يدويًا.")
    }

    private fun isApprovedDevice(snapshot: DocumentSnapshot): Boolean =
        snapshot.getString("status") == "APPROVED"
            && (snapshot.getBoolean("approved") == true || !snapshot.contains("approved"))

    private suspend fun ensureAccessRequest(uid: String, email: String, deviceId: String, now: Long) {
        val requestId = "${uid}_$deviceId"
        val requestRef = firestore().collection("accessRequests").document(requestId)
        val existing = requestRef.get(Source.SERVER).awaitResult()
        if (!existing.exists()) {
            requestRef.set(
                mapOf(
                    "userId" to uid,
                    "email" to email,
                    "deviceId" to deviceId,
                    "deviceName" to "Android ${Build.MODEL.orEmpty()}",
                    "status" to "PENDING",
                    "requestedAt" to now
                )
            ).awaitResult()
        }
    }

    suspend fun pendingDeviceRequests(): List<CloudDeviceRequest> {
        val state = loadAccessState(createDeviceRequestIfMissing = false)
        require(state.canManageDevices) { "هذه العملية متاحة لمدير النظام فقط." }
        val snapshot = firestore().collection("accessRequests")
            .whereEqualTo("status", "PENDING")
            .get(Source.SERVER)
            .awaitResult()
        return snapshot.documents.map { doc ->
            CloudDeviceRequest(
                id = doc.id,
                userId = doc.getString("userId").orEmpty(),
                email = doc.getString("email").orEmpty(),
                deviceId = doc.getString("deviceId").orEmpty(),
                deviceName = doc.getString("deviceName") ?: "جهاز Android",
                requestedAt = doc.getLong("requestedAt") ?: 0L
            )
        }
    }

    suspend fun approveDevice(request: CloudDeviceRequest) {
        val state = loadAccessState(createDeviceRequestIfMissing = false)
        require(state.canManageDevices) { "هذه العملية متاحة لمدير النظام فقط." }
        val adminUid = requireNotNull(state.uid)
        val now = System.currentTimeMillis()
        firestore().collection("devices").document("${request.userId}_${request.deviceId}")
            .set(
                mapOf(
                    "status" to "APPROVED",
                    "approved" to true,
                    "approvedBy" to adminUid,
                    "approvedAt" to now
                ),
                SetOptions.merge()
            ).awaitResult()
        firestore().collection("accessRequests").document(request.id)
            .set(mapOf("status" to "APPROVED", "reviewedBy" to adminUid, "reviewedAt" to now), SetOptions.merge())
            .awaitResult()
        firestore().collection("notifications").add(
            mapOf(
                "userId" to request.userId,
                "title" to "اعتماد الجهاز",
                "body" to "تم اعتماد جهازك من المدير",
                "read" to false,
                "createdAt" to now
            )
        ).awaitResult()
    }

    suspend fun syncDocuments(repository: MasrofRepository): CloudSyncReport {
        val state = loadAccessState(createDeviceRequestIfMissing = false)
        require(state.canSync) { state.message }
        val uid = requireNotNull(state.uid)
        val role = state.role.orEmpty()
        val db = firestore()

        // Read the complete shared ledger from the server before making any local or remote changes.
        val snapshots = fetchAllDocuments(db)
        val remoteById = snapshots.associateBy(DocumentSnapshot::getId)
        var downloaded = 0
        var uploaded = 0
        var skipped = 0
        var conflicts = 0

        for (snapshot in snapshots) {
            val data = snapshot.data ?: run { skipped++; continue }
            val remote = CloudDocumentMapper.fromMap(snapshot.id, data)
                ?: run { skipped++; continue }
            var local = repository.documentByCloudId(snapshot.id)
            if (local == null) {
                val legacyId = CloudDocumentMapper.legacyLocalId(snapshot.id)
                if (legacyId != null) {
                    val candidate = repository.documentById(legacyId)
                    if (candidate != null
                        && candidate.cloudId.isBlank()
                        && (remote.createdByUid.isBlank() || remote.createdByUid == uid)
                        && CloudDocumentMapper.matchesLegacyRecord(candidate, remote)
                    ) {
                        local = candidate
                    }
                }
            }

            if (local == null) {
                repository.insert(remote.copy(id = 0L, cloudId = snapshot.id))
                downloaded++
                continue
            }

            val mergedRemote = CloudDocumentMapper.fromMap(snapshot.id, data, local)
                ?: run { skipped++; continue }
            val linkedLocal = local.copy(
                cloudId = snapshot.id,
                createdByUid = local.createdByUid.ifBlank { remote.createdByUid }
            )
            when {
                remote.updatedAt > local.updatedAt -> {
                    repository.update(mergedRemote.copy(id = local.id, createdByUid = remote.createdByUid.ifBlank { local.createdByUid }))
                    downloaded++
                }
                remote.updatedAt == local.updatedAt && mergedRemote.copy(id = local.id) != linkedLocal -> {
                    // Same-timestamp edits are ambiguous: preserve local data and report a conflict.
                    if (linkedLocal != local) repository.update(linkedLocal)
                    conflicts++
                }
                else -> {
                    if (linkedLocal != local) repository.update(linkedLocal)
                }
            }
        }

        val localDocuments = repository.allDocumentsForSync()
        for (original in localDocuments) {
            var document = original
            var cloudId = document.cloudId
            if (cloudId.isBlank()) {
                val legacyId = "android_${document.id}"
                val legacySnapshot = remoteById[legacyId]
                val legacyData = legacySnapshot?.data
                val legacyRemote = if (legacySnapshot != null && legacyData != null) {
                    CloudDocumentMapper.fromMap(legacySnapshot.id, legacyData)
                } else null
                if (legacyRemote != null
                    && (legacyRemote.createdByUid.isBlank() || legacyRemote.createdByUid == uid)
                    && CloudDocumentMapper.matchesLegacyRecord(document, legacyRemote)
                ) {
                    cloudId = legacyId
                } else {
                    cloudId = UUID.randomUUID().toString()
                }
            }
            val creatorUid = document.createdByUid.ifBlank { uid }
            val prepared = document.copy(cloudId = cloudId, createdByUid = creatorUid)
            if (prepared != document) repository.update(prepared)

            val remoteSnapshot = remoteById[cloudId]
            if (remoteSnapshot == null) {
                if (creatorUid != uid) {
                    skipped++
                    continue
                }
                try {
                    db.collection("documents").document(cloudId)
                        .set(CloudDocumentMapper.toMap(prepared, cloudId, uid, prepared.updatedAt))
                        .awaitResult()
                    uploaded++
                } catch (error: FirebaseFirestoreException) {
                    if (error.code == FirebaseFirestoreException.Code.PERMISSION_DENIED) skipped++ else throw error
                }
                continue
            }

            val remoteData = remoteSnapshot.data ?: run { skipped++; continue }
            val remoteDocument = CloudDocumentMapper.fromMap(cloudId, remoteData)
                ?: run { skipped++; continue }
            if (prepared.updatedAt <= remoteDocument.updatedAt) continue

            val canUpdateShared = role == "SYSTEM_ADMIN" || role == "FINANCE_DIRECTOR"
            val canUpdateOwnDraft = remoteDocument.createdByUid == uid
                && remoteDocument.status.name in setOf("DRAFT", "SUBMITTED")
            if (!canUpdateShared && !canUpdateOwnDraft) {
                skipped++
                continue
            }
            try {
                db.collection("documents").document(cloudId)
                    .set(CloudDocumentMapper.toMap(prepared, cloudId, uid, prepared.updatedAt), SetOptions.merge())
                    .awaitResult()
                uploaded++
            } catch (error: FirebaseFirestoreException) {
                if (error.code == FirebaseFirestoreException.Code.PERMISSION_DENIED) skipped++ else throw error
            }
        }
        return CloudSyncReport(downloaded, uploaded, skipped, conflicts)
    }

    private suspend fun fetchAllDocuments(db: FirebaseFirestore): List<DocumentSnapshot> {
        val documents = mutableListOf<DocumentSnapshot>()
        var query: Query = db.collection("documents")
            .orderBy(FieldPath.documentId())
            .limit(PAGE_SIZE.toLong())
        while (true) {
            val page = query.get(Source.SERVER).awaitResult()
            documents += page.documents
            if (page.documents.size < PAGE_SIZE) break
            query = db.collection("documents")
                .orderBy(FieldPath.documentId())
                .startAfter(page.documents.last())
                .limit(PAGE_SIZE.toLong())
        }
        return documents
    }

    private suspend fun resolveEmail(identifier: String, db: FirebaseFirestore): String {
        val normalized = identifier.trim().lowercase(Locale.ROOT)
        if ('@' in normalized) return normalized
        val candidates = listOf(
            normalized.replace('/', '_'),
            normalized.replace(':', '_'),
            normalized.replace('@', '_'),
            normalized
        ).distinct()
        for (candidate in candidates) {
            val alias = db.collection("authAliases").document(candidate).get(Source.SERVER).awaitResult()
            val authEmail = alias.getString("authEmail")
            if (!authEmail.isNullOrBlank()) return authEmail.trim().lowercase(Locale.ROOT)
        }
        return "$normalized@accounts.masrof-manager.local"
    }

    private fun androidDeviceId(uid: String): String {
        val raw = Settings.Secure.getString(appContext.contentResolver, Settings.Secure.ANDROID_ID)
        return raw?.takeIf(String::isNotBlank) ?: "unknown-${uid.take(8)}"
    }

    private companion object {
        const val PAGE_SIZE = 200
    }
}

private suspend fun <T> Task<T>.awaitResult(): T = suspendCancellableCoroutine { continuation ->
    addOnCompleteListener { task ->
        if (!continuation.isActive) return@addOnCompleteListener
        if (task.isSuccessful) {
            continuation.resume(task.result)
        } else {
            continuation.resumeWithException(task.exception ?: IllegalStateException("تعذر إكمال طلب Firebase."))
        }
    }
}
