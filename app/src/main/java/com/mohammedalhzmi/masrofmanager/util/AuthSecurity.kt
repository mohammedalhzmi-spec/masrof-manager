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
