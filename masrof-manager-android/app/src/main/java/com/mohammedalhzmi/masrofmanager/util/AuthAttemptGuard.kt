package com.mohammedalhzmi.masrofmanager.util

import android.content.Context

object AuthAttemptGuard {
    private const val FILE = "auth_guard"
    private const val WINDOW_MS = 15 * 60 * 1000L
    private const val MAX_ATTEMPTS = 5
    private fun prefs(context: Context) = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    fun remainingLockout(context: Context, identifier: String): Long {
        val until = prefs(context).getLong("lock:${identifier.trim().lowercase()}", 0L)
        return (until - System.currentTimeMillis()).coerceAtLeast(0L)
    }
    fun canAttempt(context: Context, identifier: String) = remainingLockout(context, identifier) == 0L
    fun recordFailure(context: Context, identifier: String) {
        val key = identifier.trim().lowercase()
        val p = prefs(context)
        val now = System.currentTimeMillis()
        val window = p.getLong("window:$key", 0L)
        val count = if (now - window > WINDOW_MS) 1 else p.getInt("count:$key", 0) + 1
        p.edit().putLong("window:$key", if (now - window > WINDOW_MS) now else window).putInt("count:$key", count)
            .apply { if (count >= MAX_ATTEMPTS) putLong("lock:$key", now + WINDOW_MS) }.apply()
    }
    fun clear(context: Context, identifier: String) {
        val key = identifier.trim().lowercase()
        prefs(context).edit().remove("window:$key").remove("count:$key").remove("lock:$key").apply()
    }
}
