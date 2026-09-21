package com.mohammedalhzmi.masrofmanager.util

import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

/** Uses a user-supplied OpenAI-compatible endpoint; no secret is bundled in the APK. */
object AiLayoutAssistant {
    data class Command(
        val action: String,
        val targetId: Long? = null,
        val type: String? = null,
        val content: String? = null,
        val x: Float? = null,
        val y: Float? = null,
        val width: Float? = null,
        val height: Float? = null,
        val rotation: Float? = null,
        val fontSize: Float? = null,
        val textColor: String? = null,
        val textAlign: String? = null,
        val pageWidth: Float? = null,
        val pageHeight: Float? = null,
        val orientation: String? = null,
        val margin: Float? = null,
        val backgroundColor: String? = null
    )

    suspend fun plan(apiKey: String, endpoint: String, instruction: String, elements: List<DesignElementEntity>, design: DocumentDesignEntity?): List<Command> {
        require(apiKey.isNotBlank()) { "أدخل مفتاح الذكاء الاصطناعي" }
        val compactElements = JSONArray().apply { elements.forEach { put(JSONObject().apply { put("id", it.id); put("type", it.type); put("content", it.content.take(120)); put("x", it.x); put("y", it.y); put("width", it.width); put("height", it.height); put("zIndex", it.zIndex) }) } }
        val page = JSONObject().apply { put("width", design?.pageWidth ?: 595); put("height", design?.pageHeight ?: 842); put("orientation", design?.orientation ?: "PORTRAIT"); put("margin", design?.marginLeft ?: 25); put("backgroundColor", design?.backgroundColor ?: "#FFFFFF") }
        val system = """
أنت مساعد متخصص في تنسيق مستندات مالية عربية داخل محرر Canvas. مهمتك تحويل طلب المستخدم إلى أوامر تصميم فقط، وليس كتابة كود أو اتخاذ قرار مالي. أعد JSON صالحًا فقط بالشكل {\"commands\":[...]}. الأفعال المسموحة فقط: MOVE, RESIZE, ROTATE, UPDATE_TEXT, STYLE_TEXT, ADD_TEXT, ADD_SHAPE, ADD_QR, DELETE, PAGE. لا تغير المستخدمين أو الصلاحيات أو المبالغ أو السجلات المالية، ولا تحذف عنصرًا إلا إذا طلب المستخدم ذلك صراحة. استخدم targetId من قائمة العناصر فقط. الإحداثيات بنقاط الصفحة، والاتجاه PORTRAIT أو LANDSCAPE، والمحاذاة START أو CENTER أو END. مفاتيح الأمر المتاحة: action,targetId,type,content,x,y,width,height,rotation,fontSize,textColor,textAlign,pageWidth,pageHeight,orientation,margin,backgroundColor. عند الغموض أو عدم وجود هدف مطابق أعد commands فارغة. لا تخترع معرفات. ضع حدودًا معقولة: x/y بين 0 و10000، العرض/الارتفاع بين 10 و10000، وحجم الخط بين 6 و200. لا تعتمد على نفسك في الحسابات المالية أو التواريخ الرسمية.
""".trimIndent()
        val user = "الطلب: $instruction\nالعناصر الحالية: $compactElements\nإعدادات الصفحة: $page"
        val body = JSONObject().apply {
            put("model", "gpt-5-mini")
            put("messages", JSONArray().put(JSONObject().put("role", "system").put("content", system)).put(JSONObject().put("role", "user").put("content", user)))
            put("temperature", 0.1)
            put("response_format", JSONObject().put("type", "json_object"))
        }.toString()
        val request = Request.Builder().url(endpoint.trimEnd('/')).addHeader("Authorization", "Bearer $apiKey").addHeader("Content-Type", "application/json").post(body.toRequestBody("application/json".toMediaType())).build()
        val client = OkHttpClient.Builder().callTimeout(45, TimeUnit.SECONDS).build()
        val response = client.newCall(request).execute()
        val raw = response.body?.string().orEmpty()
        check(response.isSuccessful) { "خدمة الذكاء الاصطناعي رفضت الطلب: ${response.code}" }
        val content = JSONObject(raw).optJSONArray("choices")?.optJSONObject(0)?.optJSONObject("message")?.optString("content").orEmpty().trim().removePrefix("```json").removePrefix("```").removeSuffix("```").trim()
        val array = if (content.startsWith("[")) JSONArray(content) else JSONObject(content).optJSONArray("commands") ?: JSONArray()
        return (0 until array.length()).mapNotNull { parse(array.optJSONObject(it)) }
    }

    private fun parse(o: JSONObject?): Command? {
        if (o == null) return null
        val action = o.optString("action").uppercase()
        if (action !in setOf("MOVE", "RESIZE", "ROTATE", "UPDATE_TEXT", "STYLE_TEXT", "ADD_TEXT", "ADD_SHAPE", "ADD_QR", "DELETE", "PAGE")) return null
        return Command(action, o.optLong("targetId").takeIf { it != 0L }, o.optString("type").takeIf { it.isNotBlank() }, o.optString("content").takeIf { it.isNotBlank() }, o.floatOrNull("x"), o.floatOrNull("y"), o.floatOrNull("width"), o.floatOrNull("height"), o.floatOrNull("rotation"), o.floatOrNull("fontSize"), o.optString("textColor").takeIf { it.isNotBlank() }, o.optString("textAlign").takeIf { it.isNotBlank() }, o.floatOrNull("pageWidth"), o.floatOrNull("pageHeight"), o.optString("orientation").takeIf { it.isNotBlank() }, o.floatOrNull("margin"), o.optString("backgroundColor").takeIf { it.isNotBlank() })
    }
    private fun JSONObject.floatOrNull(key: String): Float? = if (has(key) && !isNull(key)) optDouble(key).toFloat() else null
}
