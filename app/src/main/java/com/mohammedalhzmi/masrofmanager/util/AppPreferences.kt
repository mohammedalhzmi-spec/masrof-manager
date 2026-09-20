package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.mohammedalhzmi.masrofmanager.data.DocumentType

object AppPreferences {
    private const val FILE = "masrof_preferences"
    private fun prefs(context: Context) = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)

    fun get(context: Context, key: String, fallback: String = "") = prefs(context).getString(key, fallback).orEmpty()
    fun put(context: Context, key: String, value: String) { prefs(context).edit().putString(key, value).apply() }

    fun ministry(context: Context) = get(context, "ministry", "وزارة الإدارة والتنمية المحلية والريفية")
    fun administration(context: Context) = get(context, "administration", "صندوق النظافة والتحسين")
    fun branch(context: Context) = get(context, "branch", "فرع المديرية")
    fun manager(context: Context) = get(context, "manager", "مدير الفرع")
    fun financeManager(context: Context) = get(context, "finance_manager", "المدير المالي")

    fun logoUri(context: Context, type: DocumentType): String? = get(context, "logo_${type.name.lowercase()}").ifBlank { null }
    fun saveLogo(context: Context, type: DocumentType, uri: Uri) = put(context, "logo_${type.name.lowercase()}", uri.toString())
    fun loadLogo(context: Context, type: DocumentType): Bitmap? = logoUri(context, type)?.let {
        runCatching { context.contentResolver.openInputStream(Uri.parse(it)).use(BitmapFactory::decodeStream) }.getOrNull()
    }
}

object FormMemory {
    fun read(context: Context, type: String, field: String, fallback: String = "") = AppPreferences.get(context, "last_${type}_$field", fallback)
    fun remember(context: Context, type: String, field: String, value: String) = AppPreferences.put(context, "last_${type}_$field", value)
}
