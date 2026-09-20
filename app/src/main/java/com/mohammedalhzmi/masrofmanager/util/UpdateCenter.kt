package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

data class UpdateInfo(val versionCode: Int, val versionName: String, val apkUrl: String, val notes: String)

object UpdateCenter {
    private const val MANIFEST_URL = "https://raw.githubusercontent.com/mohammedalhzmi-spec/masrof-manager1/main/update.json"
    suspend fun check(): UpdateInfo? = withContext(Dispatchers.IO) {
        runCatching {
            val connection = URL(MANIFEST_URL).openConnection() as HttpURLConnection
            connection.connectTimeout = 7000; connection.readTimeout = 7000
            JSONObject(connection.inputStream.bufferedReader().use { it.readText() }).let { UpdateInfo(it.getInt("versionCode"), it.getString("versionName"), it.getString("apkUrl"), it.optString("notes")) }
        }.getOrNull()
    }
    fun openRelease(context: Context, url: String) { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
}
