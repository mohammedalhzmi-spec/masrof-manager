package com.mohammedalhzmi.masrofmanager.util

import java.security.MessageDigest

data class AuthenticatedUser(val id: Long, val username: String, val fullName: String, val role: AppRole)

object AuthSecurity {
    fun hash(password: String): String = MessageDigest.getInstance("SHA-256").digest(password.toByteArray()).joinToString("") { "%02x".format(it) }
    fun verify(password: String, hash: String) = hash(password) == hash
}

object UserSession {
    var current: AuthenticatedUser? = null
}

object RememberedLogin {
    private const val FILE = "masrof_preferences"
    private const val USERNAME = "remembered_username"
    private const val ENABLED = "remember_login"
    private fun prefs(context: android.content.Context) = context.getSharedPreferences(FILE, android.content.Context.MODE_PRIVATE)
    fun enabled(context: android.content.Context) = prefs(context).getBoolean(ENABLED, true)
    fun save(context: android.content.Context, username: String) = prefs(context).edit().putBoolean(ENABLED, true).putString(USERNAME, username).apply()
    fun clear(context: android.content.Context) = prefs(context).edit().remove(USERNAME).putBoolean(ENABLED, false).apply()
    fun username(context: android.content.Context) = prefs(context).getString(USERNAME, null)
}
