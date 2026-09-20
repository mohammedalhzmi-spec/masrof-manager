package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import com.mohammedalhzmi.masrofmanager.data.DocumentType

enum class AppRole(val title: String) {
    ADMIN("مدير النظام"), FINANCE_MANAGER("المدير المالي"), ACCOUNTANT("المحاسب"), USER("مستخدم")
}

enum class AppPermission { CREATE_REQUEST, CREATE_ORDER, CREATE_RECEIPT, EDIT, DELETE, PRINT, BACKUP, SETTINGS, APPROVE, REPORTS }

object RolePreferences {
    private const val FILE = "masrof_preferences"
    private const val ROLE = "current_app_role"
    private const val USER_NAME = "current_user_name"
    private fun prefs(context: Context) = context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
    fun currentRole(context: Context): AppRole = runCatching { AppRole.valueOf(prefs(context).getString(ROLE, AppRole.ADMIN.name)!!) }.getOrDefault(AppRole.ADMIN)
    fun setRole(context: Context, role: AppRole) = prefs(context).edit().putString(ROLE, role.name).apply()
    fun userName(context: Context) = prefs(context).getString(USER_NAME, "المستخدم الرئيسي").orEmpty()
    fun setUserName(context: Context, value: String) = prefs(context).edit().putString(USER_NAME, value).apply()
    fun can(context: Context, permission: AppPermission) = currentRole(context).allows(permission)
    fun canCreate(context: Context, type: DocumentType) = can(context, when (type) { DocumentType.REQUEST -> AppPermission.CREATE_REQUEST; DocumentType.ORDER -> AppPermission.CREATE_ORDER; DocumentType.RECEIPT -> AppPermission.CREATE_RECEIPT })
}

private fun AppRole.allows(permission: AppPermission): Boolean = when (this) {
    AppRole.ADMIN -> true
    AppRole.FINANCE_MANAGER -> permission in setOf(AppPermission.CREATE_REQUEST, AppPermission.CREATE_ORDER, AppPermission.CREATE_RECEIPT, AppPermission.EDIT, AppPermission.PRINT, AppPermission.BACKUP, AppPermission.APPROVE, AppPermission.REPORTS)
    AppRole.ACCOUNTANT -> permission in setOf(AppPermission.CREATE_REQUEST, AppPermission.CREATE_ORDER, AppPermission.CREATE_RECEIPT, AppPermission.EDIT, AppPermission.PRINT, AppPermission.REPORTS)
    AppRole.USER -> permission in setOf(AppPermission.CREATE_REQUEST, AppPermission.PRINT)
}
