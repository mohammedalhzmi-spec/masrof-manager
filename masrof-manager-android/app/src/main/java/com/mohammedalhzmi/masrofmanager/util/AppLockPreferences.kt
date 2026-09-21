package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import java.security.MessageDigest

enum class LockType { PIN, PASSWORD, PATTERN }

object AppLockPreferences {
    private const val FILE = "masrof_preferences"
    private const val ENABLED = "app_lock_enabled"
    private const val TYPE = "app_lock_type"
    private const val SECRET = "app_lock_secret_digest"
    private const val BIOMETRIC = "app_lock_biometric"
    private const val TIMEOUT = "app_lock_timeout_minutes"

    private fun prefs(context: Context) = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    fun enabled(context: Context) = prefs(context).getBoolean(ENABLED, false)
    fun biometricEnabled(context: Context) = prefs(context).getBoolean(BIOMETRIC, true)
    fun setBiometricEnabled(context: Context, value: Boolean) = prefs(context).edit().putBoolean(BIOMETRIC, value).apply()
    fun type(context: Context) = runCatching { LockType.valueOf(prefs(context).getString(TYPE, LockType.PIN.name)!!) }.getOrDefault(LockType.PIN)
    fun timeoutMinutes(context: Context) = prefs(context).getInt(TIMEOUT, 5)
    fun setTimeoutMinutes(context: Context, value: Int) = prefs(context).edit().putInt(TIMEOUT, value.coerceIn(0, 60)).apply()
    fun setEnabled(context: Context, value: Boolean) = prefs(context).edit().putBoolean(ENABLED, value).apply()
    fun setType(context: Context, type: LockType) = prefs(context).edit().putString(TYPE, type.name).apply()
    fun setSecret(context: Context, secret: String) = prefs(context).edit().putString(SECRET, digest(secret)).apply()
    fun hasSecret(context: Context) = prefs(context).contains(SECRET)
    fun verify(context: Context, secret: String) = digest(secret) == prefs(context).getString(SECRET, null)
    fun shouldRelock(context: Context, now: Long = System.currentTimeMillis()): Boolean {
        val last = prefs(context).getLong("app_last_unlocked", 0L)
        val minutes = timeoutMinutes(context)
        return minutes == 0 || last == 0L || now - last >= minutes * 60_000L
    }
    fun markUnlocked(context: Context) = prefs(context).edit().putLong("app_last_unlocked", System.currentTimeMillis()).apply()
    private fun digest(value: String): String = MessageDigest.getInstance("SHA-256").digest(value.toByteArray()).joinToString("") { "%02x".format(it) }
}
