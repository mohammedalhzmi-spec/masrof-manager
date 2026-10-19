package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.net.Uri
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.util.UUID
import java.util.concurrent.TimeUnit

/** Offline-first conversion queue. It never blocks local editing when the server is unavailable. */
data class OfficeConversionTask(val id: String, val source: File, val targetFormat: String, val apiBase: String, val createdAt: Long = System.currentTimeMillis())

object OfficeConversionSync {
    private const val DIR = "office_sync_queue"
    private const val WORK = "office-conversion-sync"
    private val client = OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build()

    fun enqueue(context: Context, sourceUri: Uri, targetFormat: String, apiBase: String): String {
        val id = UUID.randomUUID().toString()
        val dir = File(context.filesDir, DIR).apply { mkdirs() }
        val source = File(dir, "$id-${sourceUri.lastPathSegment?.substringAfterLast('/') ?: "document"}")
        context.contentResolver.openInputStream(sourceUri)?.use { input -> source.outputStream().use { output -> input.copyTo(output) } } ?: error("تعذر نسخ الملف إلى طابور التحويل المحلي.")
        File(dir, "$id.json").writeText(JSONObject().apply { put("id", id); put("source", source.name); put("targetFormat", targetFormat); put("apiBase", apiBase.trimEnd('/')); put("createdAt", System.currentTimeMillis()) }.toString())
        schedule(context)
        return id
    }

    fun schedule(context: Context) {
        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val request = OneTimeWorkRequestBuilder<OfficeConversionWorker>().setConstraints(constraints).build()
        WorkManager.getInstance(context).enqueueUniqueWork(WORK, ExistingWorkPolicy.KEEP, request)
    }

    fun pending(context: Context): Int = File(context.filesDir, DIR).listFiles { f -> f.extension == "json" }?.size ?: 0

    class OfficeConversionWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {
        override suspend fun doWork(): Result {
            val dir = File(applicationContext.filesDir, DIR)
            val tasks = dir.listFiles { file -> file.extension == "json" }.orEmpty().sortedBy { file -> runCatching { JSONObject(file.readText()).optLong("createdAt") }.getOrDefault(Long.MAX_VALUE) }
            var retry = false
            for (meta in tasks) {
                val json = runCatching { JSONObject(meta.readText()) }.getOrNull() ?: run { meta.delete(); continue }
                val source = File(dir, json.optString("source")); if (!source.isFile) { meta.delete(); continue }
                try {
                    val result = submitAndWait(json.optString("apiBase"), source, json.optString("targetFormat"))
                    val output = File(dir, "${json.optString("id")}-result.${json.optString("targetFormat")}")
                    output.writeBytes(result)
                    source.delete(); meta.delete()
                } catch (_: Exception) { retry = true }
            }
            return if (retry) Result.retry() else Result.success()
        }

        private suspend fun submitAndWait(apiBase: String, source: File, target: String): ByteArray {
            val body = MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("targetFormat", target)
                .addFormDataPart("file", source.name, source.asRequestBody("application/octet-stream".toMediaType())).build()
            val create = Request.Builder().url("$apiBase/api/conversions").post(body).build()
            val id = client.newCall(create).execute().use { response -> if (!response.isSuccessful) error("conversion upload ${response.code}"); JSONObject(response.body?.string() ?: error("empty conversion upload response")).getString("id") }
            repeat(90) {
                delay(2000)
                val statusRequest = Request.Builder().url("$apiBase/api/conversions/$id").get().build()
                val status = client.newCall(statusRequest).execute().use { response -> if (!response.isSuccessful) error("conversion status ${response.code}"); JSONObject(response.body?.string() ?: error("empty conversion status response")) }
                when (status.optString("status")) {
                    "completed" -> return client.newCall(Request.Builder().url("$apiBase/api/conversions/$id/download").get().build()).execute().use { response -> if (!response.isSuccessful) error("conversion download ${response.code}"); response.body?.bytes() ?: error("empty conversion download response") }
                    "failed" -> error(status.optString("error", "conversion failed"))
                }
            }
            error("conversion timeout")
        }
    }
}
