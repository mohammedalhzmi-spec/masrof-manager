package com.mohammedalhzmi.masrofmanager.cloud

import android.content.Context
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
import com.google.firebase.functions.FirebaseFunctions
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.MasrofRepository
import com.mohammedalhzmi.masrofmanager.util.BranchAssetData
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

data class BranchAssetCloudResult(val synced: Boolean, val message: String)

/** Android Firebase bridge. Alias resolution and device approval are server-only; local Room data is never cleared. */
class FirebaseCloudSyncService(context: Context) {
    private val appContext = context.applicationContext
    private val deviceIdentity = AndroidCloudDeviceIdentity(appContext)

    private fun app(): FirebaseApp =
        FirebaseApp.getApps(appContext).firstOrNull()
            ?: FirebaseApp.initializeApp(appContext)
            ?: throw IllegalStateException("إعداد Firebase غير موجود. أضف google-services.json الخاص بالمشروع ثم أعد البناء.")

    private fun auth(): FirebaseAuth = FirebaseAuth.getInstance(app())
    private fun firestore(): FirebaseFirestore = FirebaseFirestore.getInstance(app())
    private fun functions(): FirebaseFunctions = FirebaseFunctions.getInstance(app(), FUNCTIONS_REGION)

    suspend fun signIn(identifier: String, password: String): CloudAccessState {
        require(identifier.isNotBlank()) { "أدخل البريد الإلكتروني أو اسم المستخدم." }
        require(password.isNotEmpty()) { "أدخل كلمة المرور." }
        val identity = deviceIdentity.identity()
        val begin = callFunction(
            "beginCloudLogin",
            mapOf(
                "identifier" to identifier.trim(),
                "password" to password,
                "deviceId" to identity.deviceId,
                "publicKey" to identity.publicKeyBase64,
                "deviceName" to "Android ${android.os.Build.MODEL.orEmpty()}"
            )
        )
        val challengeId = begin.string("challengeId")
        val challenge = begin.string("challenge")
        val signature = deviceIdentity.signChallenge(challenge)
        val completed = callFunction(
            "completeCloudLogin",
            mapOf("challengeId" to challengeId, "signature" to signature)
        )
        auth().signInWithCustomToken(completed.string("customToken")).awaitResult()
        return loadAccessState(createDeviceRequestIfMissing = false)
    }

    suspend fun currentAccessState(): CloudAccessState {
        if (FirebaseApp.getApps(appContext).isEmpty() && FirebaseApp.initializeApp(appContext) == null) {
            return CloudAccessState(message = "إعداد Firebase غير موجود. أضف google-services.json الخاص بالمشروع.")
        }
        val user = auth().currentUser ?: return CloudAccessState()
        val identity = deviceIdentity.identity()
        val claims = user.getIdToken(false).awaitResult().claims
        val tokenDeviceId = claims[CLAIM_DEVICE_ID] as? String
        val tokenDeviceKey = claims[CLAIM_DEVICE_KEY] as? String
        if (tokenDeviceId == null || tokenDeviceKey == null) {
            auth().signOut()
            return CloudAccessState(message = "يتطلب تحديث حماية السحابة تسجيل الدخول مجددًا.")
        }
        if (tokenDeviceId != identity.deviceId || tokenDeviceKey != identity.publicKeyHash) {
            auth().signOut()
            return CloudAccessState(message = "تغيّر مفتاح أمان هذا الجهاز. سجّل الدخول مجددًا لطلب اعتماده.")
        }
        return loadAccessState(createDeviceRequestIfMissing = false)
    }

    suspend fun requestDeviceApproval(): CloudAccessState {
        requireNotNull(auth().currentUser) { "سجّل الدخول إلى السحابة أولاً." }
        callFunction("requestCloudDeviceApproval", emptyMap())
        return loadAccessState(createDeviceRequestIfMissing = false)
    }

    fun signOut() {
        if (FirebaseApp.getApps(appContext).isNotEmpty()) auth().signOut()
    }

    private suspend fun loadAccessState(createDeviceRequestIfMissing: Boolean): CloudAccessState {
        val user = auth().currentUser ?: return CloudAccessState()
        val uid = user.uid
        val profileSnapshot = firestore().collection("users").document(uid).get(Source.SERVER).awaitResult()
        if (!profileSnapshot.exists()) {
            return CloudAccessState(uid, user.email.orEmpty(), gate = CloudGate.PROFILE_MISSING, message = "لا يوجد ملف مستخدم سحابي لهذا الحساب.")
        }
        val email = profileSnapshot.getString("email").orEmpty().ifBlank { user.email.orEmpty() }
        val role = profileSnapshot.getString("role") ?: "ADMIN_USER"
        if (profileSnapshot.getBoolean("active") != true) {
            return CloudAccessState(uid, email, role, CloudGate.ACCOUNT_INACTIVE, "الحساب غير معتمد أو غير مفعل في Firebase.")
        }

        val identity = deviceIdentity.identity()
        val deviceRef = firestore().collection("devices").document("${uid}_${identity.deviceId}")
        var deviceSnapshot = deviceRef.get(Source.SERVER).awaitResult()
        if (!deviceSnapshot.exists()) {
            if (createDeviceRequestIfMissing) {
                callFunction("requestCloudDeviceApproval", emptyMap())
                deviceSnapshot = deviceRef.get(Source.SERVER).awaitResult()
            } else {
                return CloudAccessState(uid, email, role, CloudGate.DEVICE_PENDING, "هذا الجهاز غير مسجل للاعتماد بعد.")
            }
        }

        if (deviceSnapshot.getString("publicKeyHash") != identity.publicKeyHash) {
            return CloudAccessState(uid, email, role, CloudGate.DEVICE_PENDING, "مفتاح هذا الجهاز تغيّر؛ سجّل الخروج ثم الدخول لطلب اعتماد المفتاح الجديد.")
        }
        val approved = isApprovedDevice(deviceSnapshot)
        if (!approved) {
            if (createDeviceRequestIfMissing) callFunction("requestCloudDeviceApproval", emptyMap())
            return CloudAccessState(uid, email, role, CloudGate.DEVICE_PENDING, "تم إرسال طلب اعتماد الجهاز أو ما زال بانتظار موافقة المدير.")
        }

        val tokenClaims = user.getIdToken(false).awaitResult().claims
        if (tokenClaims[CLAIM_DEVICE_APPROVED] != true) refreshDeviceSession()
        return CloudAccessState(uid, email, role, CloudGate.READY, "الحساب والجهاز معتمدان؛ يمكنك مزامنة المستندات يدويًا.")
    }

    private fun isApprovedDevice(snapshot: DocumentSnapshot): Boolean =
        snapshot.getString("status") == "APPROVED"
            && (snapshot.getBoolean("approved") == true || !snapshot.contains("approved"))
            && snapshot.getString("publicKeyHash") == deviceIdentity.identity().publicKeyHash

    private suspend fun refreshDeviceSession() {
        val begin = callFunction("beginCloudSessionRefresh", emptyMap())
        val challengeId = begin.string("challengeId")
        val signature = deviceIdentity.signChallenge(begin.string("challenge"))
        val completed = callFunction(
            "completeCloudSessionRefresh",
            mapOf("challengeId" to challengeId, "signature" to signature)
        )
        auth().signInWithCustomToken(completed.string("customToken")).awaitResult()
    }

    suspend fun pendingDeviceRequests(): List<CloudDeviceRequest> {
        val response = callFunction("listPendingCloudDevices", emptyMap())
        val rows = response["requests"] as? List<*> ?: return emptyList()
        return rows.mapNotNull { row ->
            val data = row as? Map<*, *> ?: return@mapNotNull null
            CloudDeviceRequest(
                id = data["id"] as? String ?: return@mapNotNull null,
                userId = data["userId"] as? String ?: "",
                email = data["email"] as? String ?: "",
                deviceId = data["deviceId"] as? String ?: "",
                deviceName = data["deviceName"] as? String ?: "جهاز Android",
                requestedAt = (data["requestedAt"] as? Number)?.toLong() ?: 0L
            )
        }
    }

    suspend fun approveDevice(request: CloudDeviceRequest) {
        val response = callFunction("approveCloudDevice", mapOf("requestId" to request.id))
        val customToken = response["customToken"] as? String
        if (!customToken.isNullOrBlank()) auth().signInWithCustomToken(customToken).awaitResult()
    }

    /**
     * Push exactly one branch-asset row after checking the existing account/device gate.
     * The transaction never deletes records and refuses to overwrite a newer shared edit.
     */
    suspend fun syncBranchAsset(documentId: Long, repository: MasrofRepository): BranchAssetCloudResult {
        val access = currentAccessState()
        if (!access.canSync) return BranchAssetCloudResult(false, access.message)
        val uid = requireNotNull(access.uid) { "تعذر إثبات حساب السحابة الحالي." }
        val local = repository.documentById(documentId)
            ?: return BranchAssetCloudResult(false, "حُفظت البيانات محليًا لكن تعذر العثور على سجل الممتلكات.")
        require(BranchAssetData.isRecord(local)) { "السجل المحدد ليس من سجلات ممتلكات الفرع." }
        require(local.createdByUid.isBlank() || local.createdByUid == uid) {
            "لا يمكن لهذا الحساب تعديل سجل ممتلكات أنشأه حساب سحابي آخر."
        }

        val cloudId = local.cloudId.ifBlank { UUID.randomUUID().toString() }
        val prepared = local.copy(
            cloudId = cloudId,
            createdByUid = uid,
            updatedAt = maxOf(local.updatedAt, System.currentTimeMillis())
        )
        // Persist the cloud identity locally first so a transient network failure can retry
        // the same record instead of creating a duplicate on the next attempt.
        repository.update(prepared)
        val reference = firestore().collection("documents").document(cloudId)
        val result = firestore().runTransaction { transaction ->
            val snapshot = transaction.get(reference)
            if (!snapshot.exists()) {
                transaction.set(reference, CloudDocumentMapper.toMap(prepared, cloudId, uid, prepared.updatedAt))
                "uploaded"
            } else {
                val remote = snapshot.data?.let { CloudDocumentMapper.fromMap(cloudId, it) }
                    ?: throw IllegalStateException("يوجد سجل سحابي غير قابل للقراءة؛ لم يتم استبداله.")
                if (!BranchAssetData.isRecord(remote)) {
                    throw IllegalStateException("معرّف السحابة مرتبط بسجل آخر؛ لم يتم استبدال أي بيانات.")
                }
                if (remote.createdByUid != uid) {
                    throw IllegalStateException("هذا السجل السحابي مملوك لحساب آخر؛ لم يتم تعديله.")
                }
                val samePayload = remote.type == prepared.type
                    && remote.documentNumber == prepared.documentNumber
                    && remote.details == prepared.details
                    && remote.beneficiaryName == prepared.beneficiaryName
                    && remote.purpose == prepared.purpose
                    && remote.amount == prepared.amount
                    && remote.tags == prepared.tags
                if (remote.updatedAt > prepared.updatedAt) {
                    "conflict:${remote.updatedAt}"
                } else if (remote.updatedAt == prepared.updatedAt && samePayload) {
                    "current"
                } else if (remote.updatedAt == prepared.updatedAt) {
                    "conflict:${remote.updatedAt}"
                } else {
                    transaction.set(
                        reference,
                        CloudDocumentMapper.toMap(prepared, cloudId, uid, prepared.updatedAt),
                        SetOptions.merge()
                    )
                    "uploaded"
                }
            }
        }.awaitResult()

        if (result.startsWith("conflict:")) {
            // Equal timestamps make the existing full-ledger sync report a conflict and
            // preserve both copies rather than letting either side silently win.
            result.substringAfter(':').toLongOrNull()?.let { remoteTime ->
                repository.update(prepared.copy(updatedAt = remoteTime))
            }
            return BranchAssetCloudResult(false, "حُفظت محليًا؛ وُجد تعديل سحابي متزامن ولم تُستبدل أي نسخة. راجع السجل قبل المزامنة العامة.")
        }
        return BranchAssetCloudResult(true, if (result == "current") "السجل موجود ومحدّث في السحابة." else "تم حفظ السجل محليًا ومزامنته سحابيًا.")
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
            val hadCloudId = cloudId.isNotBlank()
            var matchedLegacyOwner: String? = null
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
                    matchedLegacyOwner = legacyRemote.createdByUid
                } else {
                    cloudId = UUID.randomUUID().toString()
                }
            }
            val remoteSnapshot = remoteById[cloudId]
            val remoteOwner = remoteSnapshot?.data?.let { data ->
                CloudDocumentMapper.fromMap(cloudId, data)?.createdByUid
            } ?: matchedLegacyOwner
            val creatorUid = CloudDocumentMapper.syncCreatorUid(
                localOwner = document.createdByUid,
                currentUid = uid,
                remoteOwner = remoteOwner,
                isNewLocalRecord = !hadCloudId && remoteSnapshot == null && matchedLegacyOwner == null
            )
            val prepared = document.copy(cloudId = cloudId, createdByUid = creatorUid)
            if (prepared != document) repository.update(prepared)

            if (remoteSnapshot == null) {
                if (creatorUid != uid) {
                    skipped++
                    continue
                }
                try {
                    val created = createCloudDocumentIfAbsent(
                        db,
                        cloudId,
                        CloudDocumentMapper.toMap(prepared, cloudId, uid, prepared.updatedAt)
                    )
                    if (created) uploaded++ else conflicts++
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
                val updated = updateCloudDocumentIfUnchanged(
                    db,
                    remoteSnapshot,
                    CloudDocumentMapper.toMap(prepared, cloudId, uid, prepared.updatedAt)
                )
                if (updated) uploaded++ else conflicts++
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

    private suspend fun createCloudDocumentIfAbsent(
        db: FirebaseFirestore,
        cloudId: String,
        data: Map<String, Any?>
    ): Boolean {
        val reference = db.collection("documents").document(cloudId)
        return db.runTransaction { transaction ->
            if (transaction.get(reference).exists()) {
                false
            } else {
                transaction.set(reference, data)
                true
            }
        }.awaitResult()
    }

    private suspend fun updateCloudDocumentIfUnchanged(
        db: FirebaseFirestore,
        expected: DocumentSnapshot,
        data: Map<String, Any?>
    ): Boolean {
        val expectedData = expected.data ?: return false
        val reference = expected.reference
        return db.runTransaction { transaction ->
            val current = transaction.get(reference)
            if (!current.exists() || current.data != expectedData) {
                false
            } else {
                transaction.set(reference, data, SetOptions.merge())
                true
            }
        }.awaitResult()
    }

    private suspend fun callFunction(name: String, data: Map<String, Any?>): Map<String, Any?> {
        val result = functions().getHttpsCallable(name).call(data).awaitResult()
        return result.data as? Map<String, Any?>
            ?: throw IllegalStateException("استجابة خدمة السحابة غير صالحة.")
    }

    private fun Map<String, Any?>.string(key: String): String =
        this[key] as? String ?: throw IllegalStateException("استجابة خدمة السحابة غير مكتملة.")

    private companion object {
        const val PAGE_SIZE = 200
        const val FUNCTIONS_REGION = "us-central1"
        const val CLAIM_DEVICE_ID = "device_id"
        const val CLAIM_DEVICE_KEY = "device_key"
        const val CLAIM_DEVICE_APPROVED = "device_approved"
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
