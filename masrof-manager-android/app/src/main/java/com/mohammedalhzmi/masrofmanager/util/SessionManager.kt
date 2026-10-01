package com.mohammedalhzmi.masrofmanager.util

import android.content.Context

object SessionManager {
    private const val FILE = "masrof_session"
    private const val LAST_ACTIVE = "last_active"
    private const val TIMEOUT_MS = 30 * 60 * 1000L
    private fun prefs(context: Context) = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    fun markActive(context: Context) = prefs(context).edit().putLong(LAST_ACTIVE, System.currentTimeMillis()).apply()
    fun expired(context: Context): Boolean {
        val last = prefs(context).getLong(LAST_ACTIVE, 0L)
        return last == 0L || System.currentTimeMillis() - last >= TIMEOUT_MS
    }
    fun clear(context: Context) = prefs(context).edit().remove(LAST_ACTIVE).apply()
}
