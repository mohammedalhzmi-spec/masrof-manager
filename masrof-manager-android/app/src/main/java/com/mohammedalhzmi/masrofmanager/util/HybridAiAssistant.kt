package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity

object HybridAiAssistant {
    data class Result(val commands: List<AiLayoutAssistant.Command>, val mode: String)

    suspend fun plan(context: Context, apiKey: String, endpoint: String, instruction: String, elements: List<DesignElementEntity>, design: DocumentDesignEntity?): Result {
        val online = context.getSystemService(ConnectivityManager::class.java)?.activeNetwork?.let { network -> context.getSystemService(ConnectivityManager::class.java)?.getNetworkCapabilities(network)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } == true
        if (online && apiKey.isNotBlank()) return runCatching { Result(AiLayoutAssistant.plan(apiKey, endpoint, instruction, elements, design), "سحابي") }.getOrElse { Result(LocalLayoutRules.plan(instruction, elements, design), "محلي احتياطي") }
        return Result(LocalLayoutRules.plan(instruction, elements, design), "محلي دون اتصال")
    }
}

/** Deterministic Arabic editor assistant. It never executes code or changes permissions. */
object LocalLayoutRules {
    fun plan(text: String, elements: List<DesignElementEntity>, design: DocumentDesignEntity?): List<AiLayoutAssistant.Command> {
        val s = text.lowercase()
        val out = mutableListOf<AiLayoutAssistant.Command>()
        val textElements = elements.filter { it.type == "TEXT" }
        val qr = elements.firstOrNull { it.type == "QR" }
        val alignment = when { s.contains("وسط") || s.contains("منتصف") -> "CENTER"; s.contains("يمين") -> "START"; s.contains("يسار") -> "END"; else -> null }
        if (alignment != null) textElements.forEach { out += AiLayoutAssistant.Command("STYLE_TEXT", it.id, textAlign = alignment) }
        if (s.contains("عريض") || s.contains("غامق")) textElements.forEach { out += AiLayoutAssistant.Command("STYLE_TEXT", it.id, fontSize = (it.fontSize + 2f).coerceAtMost(72f)) }
        if (s.contains("كبّر") || s.contains("تكبير") || s.contains("أكبر")) elements.filter { it.id == findTargetId(s, elements) }.forEach { out += AiLayoutAssistant.Command("RESIZE", it.id, width = it.width * 1.2f, height = it.height * 1.2f) }
        if (s.contains("صغّر") || s.contains("تصغير")) elements.filter { it.id == findTargetId(s, elements) }.forEach { out += AiLayoutAssistant.Command("RESIZE", it.id, width = it.width * .8f, height = it.height * .8f) }
        if (s.contains("أسفل") && qr != null) out += AiLayoutAssistant.Command("MOVE", qr.id, x = (design?.pageWidth ?: 595f) - qr.width - 35f, y = (design?.pageHeight ?: 842f) - qr.height - 35f)
        if (s.contains("أعلى") && qr != null) out += AiLayoutAssistant.Command("MOVE", qr.id, x = (design?.pageWidth ?: 595f) - qr.width - 35f, y = 35f)
        if ((s.contains("أفقي") || s.contains("عرضي")) && design != null) out += AiLayoutAssistant.Command("PAGE", pageWidth = 842f, pageHeight = 595f, orientation = "LANDSCAPE")
        if ((s.contains("عمودي") || s.contains("طولي")) && design != null) out += AiLayoutAssistant.Command("PAGE", pageWidth = 595f, pageHeight = 842f, orientation = "PORTRAIT")
        if (s.contains("a5") && design != null) out += AiLayoutAssistant.Command("PAGE", pageWidth = 420f, pageHeight = 595f, orientation = "PORTRAIT")
        if ((s.contains("a4") || s.contains("ورقة كاملة")) && design != null) out += AiLayoutAssistant.Command("PAGE", pageWidth = 595f, pageHeight = 842f, orientation = "PORTRAIT")
        if (s.contains("هوامش") && design != null) out += AiLayoutAssistant.Command("PAGE", margin = if (s.contains("ضيقة") || s.contains("صغيرة")) 12f else 35f)
        if (s.contains("أضف qr") || s.contains("اضف qr") || s.contains("رمز qr")) out += AiLayoutAssistant.Command("ADD_QR", type = "QR", content = "{رقم المستند}", x = (design?.pageWidth ?: 595f) - 115f, y = (design?.pageHeight ?: 842f) - 115f, width = 80f, height = 80f)
        if (s.contains("أضف نص") || s.contains("اضف نص")) out += AiLayoutAssistant.Command("ADD_TEXT", type = "TEXT", content = text.substringAfter(":" , "نص جديد").trim(), x = 40f, y = 140f, width = 300f, height = 70f)
        if (out.isEmpty() && alignment == null) textElements.take(1).forEach { out += AiLayoutAssistant.Command("STYLE_TEXT", it.id, textAlign = "CENTER") }
        return out
    }
    private fun findTargetId(text: String, elements: List<DesignElementEntity>): Long? = elements.firstOrNull { e -> e.content.contains("عنوان") && text.contains("عنوان") }?.id ?: elements.firstOrNull { it.type == "TEXT" }?.id
}
