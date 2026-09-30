package com.mohammedalhzmi.masrofmanager.util

/** Versioned, dependency-free storage for table cells in a design element's existing content field. */
object DocumentTableCodec {
    private const val PREFIX = "MASROF_TABLE_V1\n"

    fun encode(rows: List<List<String>>): String {
        val normalized = rows
            .map { row -> row.map { cell -> cell.replace('\t', ' ').replace('\n', ' ').trim() } }
            .filter { row -> row.any(String::isNotBlank) }
        return PREFIX + normalized.joinToString("\n") { row -> row.joinToString("\t") }
    }

    fun decode(content: String): List<List<String>> {
        if (!content.startsWith(PREFIX)) return emptyList()
        val rows = content.removePrefix(PREFIX).lineSequence()
            .filter(String::isNotBlank)
            .map { line -> line.split('\t').map(String::trim) }
            .toList()
        val columnCount = rows.maxOfOrNull { it.size } ?: return emptyList()
        if (columnCount == 0) return emptyList()
        return rows.map { row -> row + List(columnCount - row.size) { "" } }
    }

    fun parseEditorText(text: String): List<List<String>> = text.lineSequence()
        .map { line -> line.split('|').map(String::trim) }
        .filter { row -> row.any(String::isNotBlank) }
        .toList()
}
