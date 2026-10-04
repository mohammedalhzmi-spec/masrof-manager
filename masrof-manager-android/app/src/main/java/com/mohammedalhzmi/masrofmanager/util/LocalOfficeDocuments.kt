package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.system.Os
import org.json.JSONObject
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import java.io.File
import java.util.UUID

enum class OfficeDocumentKind(val title: String, val extensionLabel: String) {
    WORD("Word", "مستند Word"),
    EXCEL("Excel", "جدول Excel")
}

data class LocalOfficeDocument(
    val id: String,
    val title: String,
    val kind: OfficeDocumentKind,
    val wordHtml: String = "",
    val cells: Map<String, String> = emptyMap(),
    val fontFamily: String = "cairo_regular",
    val updatedAt: Long = System.currentTimeMillis(),
    val roomDocumentId: Long? = null,
    val bookTag: String = ""
)

object OfficeDocumentRecord {
    const val MARKER = "MASROF_OFFICE_V1"

    fun isOfficeDocument(document: Document): Boolean =
        document.type == DocumentType.BOOK && document.structuredFields == MARKER && !document.details.isNullOrBlank()

    fun decode(document: Document): LocalOfficeDocument? =
        if (!isOfficeDocument(document)) null else runCatching {
            LocalOfficeDocumentStore.decode(document.details!!).copy(roomDocumentId = document.id, updatedAt = document.updatedAt)
        }.getOrNull()
}

/** Stores editor drafts inside app-private files; this is intentionally offline and separate from Firebase. */
object LocalOfficeDocumentStore {
    private const val DIRECTORY = "office_documents"
    private const val FORMAT_VERSION = 1

    fun list(context: Context): List<LocalOfficeDocument> = directory(context)
        .listFiles { file -> file.isFile && file.extension == "json" }
        .orEmpty()
        .mapNotNull { file -> runCatching { decode(file.readText()) }.getOrNull() }
        .sortedByDescending(LocalOfficeDocument::updatedAt)

    fun create(context: Context, kind: OfficeDocumentKind, title: String): LocalOfficeDocument {
        val document = LocalOfficeDocument(
            id = UUID.randomUUID().toString(),
            title = title.trim().ifBlank { if (kind == OfficeDocumentKind.WORD) "مستند جديد" else "جدول بيانات جديد" },
            kind = kind
        )
        save(context, document)
        return document
    }

    fun save(context: Context, document: LocalOfficeDocument) {
        require(document.id.matches(Regex("[A-Fa-f0-9-]{36}"))) { "معرّف المستند غير صالح." }
        val folder = directory(context)
        check(folder.exists() || folder.mkdirs()) { "تعذر إنشاء مجلد المستندات المحلية." }
        val payload = encode(document)
        val target = File(folder, "${document.id}.json")
        val temporary = File(folder, "${document.id}.tmp")
        temporary.writeText(payload, Charsets.UTF_8)
        val renamed = runCatching {
            // Same-directory POSIX rename atomically replaces the previous file on Android.
            Os.rename(temporary.absolutePath, target.absolutePath)
            target.isFile && target.readText(Charsets.UTF_8) == payload
        }.getOrDefault(false)
        if (!renamed) {
            val replacement = File(folder, "${document.id}.replacement")
            val backup = File(folder, "${document.id}.backup")
            temporary.copyTo(replacement, overwrite = true)
            val hadPrevious = target.exists()
            if (hadPrevious && !target.renameTo(backup)) {
                temporary.delete(); replacement.delete()
                error("تعذر حفظ المستند محليًا؛ بقيت النسخة السابقة دون تغيير.")
            }
            if (!replacement.renameTo(target)) {
                if (hadPrevious) backup.renameTo(target)
                temporary.delete(); replacement.delete()
                error("تعذر حفظ المستند محليًا؛ أُعيدت النسخة السابقة.")
            }
            if (hadPrevious) backup.delete()
        }
        temporary.delete()
    }

    fun encode(document: LocalOfficeDocument): String = JSONObject().apply {
            put("formatVersion", FORMAT_VERSION)
            put("id", document.id)
            put("title", document.title)
            put("kind", document.kind.name)
            put("wordHtml", document.wordHtml)
            put("fontFamily", document.fontFamily)
            put("updatedAt", document.updatedAt)
            put("roomDocumentId", document.roomDocumentId)
            put("bookTag", document.bookTag)
            put("cells", JSONObject().apply { document.cells.toSortedMap().forEach { (key, value) -> put(key, value) } })
        }.toString()

    fun delete(context: Context, id: String): Boolean {
        if (!id.matches(Regex("[A-Fa-f0-9-]{36}"))) return false
        return File(directory(context), "$id.json").delete()
    }

    private fun directory(context: Context): File = File(context.filesDir, DIRECTORY)

    fun decode(raw: String): LocalOfficeDocument {
        val json = JSONObject(raw)
        require(json.optInt("formatVersion") in 1..FORMAT_VERSION)
        val cellsObject = json.optJSONObject("cells") ?: JSONObject()
        val cells = buildMap {
            val keys = cellsObject.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                put(key, cellsObject.optString(key, ""))
            }
        }
        return LocalOfficeDocument(
            id = json.getString("id"),
            title = json.optString("title", "مستند محلي"),
            kind = OfficeDocumentKind.valueOf(json.getString("kind")),
            wordHtml = json.optString("wordHtml", ""),
            cells = cells,
            fontFamily = json.optString("fontFamily", "cairo_regular"),
            updatedAt = json.optLong("updatedAt", 0L),
            roomDocumentId = json.optLong("roomDocumentId").takeIf { it > 0L },
            bookTag = json.optString("bookTag", "")
        )
    }
}
