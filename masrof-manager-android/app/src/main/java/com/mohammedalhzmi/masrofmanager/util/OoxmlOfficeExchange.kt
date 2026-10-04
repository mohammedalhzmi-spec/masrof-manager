package com.mohammedalhzmi.masrofmanager.util

import android.text.Html
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.graphics.Typeface
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.StringReader
import java.nio.charset.StandardCharsets
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Small, offline OOXML interchange for the native editor.
 * It intentionally handles editable Word paragraphs/runs and the first Excel worksheet,
 * not the complete Microsoft Office feature set.
 */
object OoxmlOfficeExchange {
    const val DOCX_MIME = "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    const val XLSX_MIME = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"

    private const val WORD_NS = "http://schemas.openxmlformats.org/wordprocessingml/2006/main"
    private const val SHEET_NS = "http://schemas.openxmlformats.org/spreadsheetml/2006/main"
    private const val REL_NS = "http://schemas.openxmlformats.org/officeDocument/2006/relationships"
    private const val PACKAGE_REL_NS = "http://schemas.openxmlformats.org/package/2006/relationships"
    private const val MAX_ENTRIES = 512
    private const val MAX_ENTRY_BYTES = 8 * 1024 * 1024
    private const val MAX_TOTAL_UNCOMPRESSED_BYTES = 24 * 1024 * 1024
    private const val MAX_CELLS = 10_000

    data class ImportResult(val document: LocalOfficeDocument, val notices: List<String> = emptyList())

    fun importDocx(input: InputStream, title: String): ImportResult {
        val parts = readZip(input)
        val wordXml = parts["word/document.xml"] ?: error("الملف لا يحتوي على مستند Word صالح.")
        val root = parseXml(wordXml)
        val body = root.getElementsByTagNameNS(WORD_NS, "body").item(0) as? Element ?: error("ملف Word فارغ أو غير صالح.")
        val paragraphs = body.getElementsByTagNameNS(WORD_NS, "p")
        val html = StringBuilder()
        var detectedFont = "cairo_regular"
        var hasDetectedFont = false
        for (index in 0 until paragraphs.length) {
            val paragraph = paragraphs.item(index) as? Element ?: continue
            val alignment = paragraph.getElementsByTagNameNS(WORD_NS, "jc").item(0) as? Element
            val alignmentCss = when (attributeByLocalName(alignment, "val")) {
                "center" -> "text-align:center;"
                "right", "end" -> "text-align:right;"
                "left", "start" -> "text-align:left;"
                "both", "distribute" -> "text-align:justify;"
                else -> ""
            }
            html.append("<p style=\"").append(alignmentCss).append("\">")
            val runs = paragraph.getElementsByTagNameNS(WORD_NS, "r")
            for (runIndex in 0 until runs.length) {
                val run = runs.item(runIndex) as? Element ?: continue
                val text = extractWordRunText(run)
                if (text.isEmpty()) continue
                val properties = run.getElementsByTagNameNS(WORD_NS, "rPr").item(0) as? Element
                if (!hasDetectedFont) {
                    val fonts = properties?.getElementsByTagNameNS(WORD_NS, "rFonts")?.item(0) as? Element
                    val fontName = attributeByLocalName(fonts, "cs") ?: attributeByLocalName(fonts, "eastAsia") ?: attributeByLocalName(fonts, "ascii")
                    if (!fontName.isNullOrBlank()) {
                        detectedFont = officeFontKey(fontName)
                        hasDetectedFont = true
                    }
                }
                val css = buildString {
                    if (hasActiveWordFlag(properties, "b")) append("font-weight:bold;")
                    if (hasActiveWordFlag(properties, "i")) append("font-style:italic;")
                    if (hasActiveWordFlag(properties, "u")) append("text-decoration:underline;")
                    val color = properties?.getElementsByTagNameNS(WORD_NS, "color")?.item(0) as? Element
                    attributeByLocalName(color, "val")?.takeIf { it.matches(Regex("[0-9A-Fa-f]{6}")) }?.let { append("color:#$it;") }
                    val size = properties?.getElementsByTagNameNS(WORD_NS, "sz")?.item(0) as? Element
                    attributeByLocalName(size, "val")?.toIntOrNull()?.let { append("font-size:${(it / 2).coerceIn(6, 72)}pt;") }
                }
                val escaped = escapeHtml(text).replace("\n", "<br>").replace("\t", "&#9;")
                if (css.isNotEmpty()) html.append("<span style=\"").append(css).append("\">").append(escaped).append("</span>")
                else html.append(escaped)
            }
            if (runs.length == 0) html.append("<br>")
            html.append("</p>")
        }
        val notices = mutableListOf("تم استيراد النص والفقرات والتنسيق الأساسي. راجع المستند؛ قد تختلف بعض تفاصيل التخطيط بين التطبيقات.")
        if (root.getElementsByTagNameNS(WORD_NS, "tbl").length > 0) notices += "الجداول في DOCX تُحوّل إلى فقرات نصية؛ بنية الجدول لا تُحفظ حاليًا."
        if (root.getElementsByTagNameNS(WORD_NS, "drawing").length > 0 || root.getElementsByTagNameNS(WORD_NS, "pict").length > 0) notices += "الصور/الرسومات داخل DOCX لا تُستورد في هذه المرحلة."
        if (parts.keys.any { it.startsWith("word/header") || it.startsWith("word/footer") }) notices += "الترويسات والتذييلات ليست ضمن نطاق الاستيراد الحالي."
        return ImportResult(
            LocalOfficeDocument(UUID.randomUUID().toString(), title.cleanOfficeTitle(), OfficeDocumentKind.WORD, wordHtml = html.toString(), fontFamily = detectedFont),
            notices
        )
    }

    fun exportDocx(document: LocalOfficeDocument, output: OutputStream) {
        require(document.kind == OfficeDocumentKind.WORD) { "اختر مستند Word للتصدير بصيغة DOCX." }
        val richText = Html.fromHtml(document.wordHtml, Html.FROM_HTML_MODE_LEGACY)
        val xml = buildWordDocument(richText, document.fontFamily)
        ZipOutputStream(output).use { zip ->
            zip.addText("[Content_Types].xml", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types"><Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/><Default Extension="xml" ContentType="application/xml"/><Override PartName="/word/document.xml" ContentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml"/></Types>""")
            zip.addText("_rels/.rels", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Relationships xmlns="$PACKAGE_REL_NS"><Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="word/document.xml"/></Relationships>""")
            zip.addText("word/document.xml", xml)
        }
    }

    fun importXlsx(input: InputStream, title: String): ImportResult {
        val parts = readZip(input)
        val workbook = parts["xl/workbook.xml"] ?: error("الملف لا يحتوي على مصنف Excel صالح.")
        val workbookXml = parseXml(workbook)
        val sheets = workbookXml.getElementsByTagNameNS(SHEET_NS, "sheet")
        require(sheets.length > 0) { "مصنف Excel لا يحتوي على أوراق عمل." }
        val firstSheet = sheets.item(0) as Element
        val relationshipId = firstSheet.getAttributeNS(REL_NS, "id").ifBlank { firstSheet.getAttribute("r:id") }
        val relationshipBytes = parts["xl/_rels/workbook.xml.rels"] ?: error("علاقات أوراق العمل غير موجودة.")
        val relationships = parseXml(relationshipBytes).getElementsByTagNameNS(PACKAGE_REL_NS, "Relationship")
        var target = ""
        for (index in 0 until relationships.length) {
            val relationship = relationships.item(index) as? Element ?: continue
            if (relationship.getAttribute("Id") == relationshipId) {
                target = relationship.getAttribute("Target")
                break
            }
        }
        require(target.isNotBlank()) { "تعذر تحديد ورقة العمل الأولى." }
        val sheetPath = if (target.startsWith("/")) target.removePrefix("/") else "xl/$target"
        require(!sheetPath.split('/').contains("..")) { "مسار ورقة العمل غير صالح." }
        val sheetBytes = parts[sheetPath] ?: error("بيانات ورقة العمل الأولى غير موجودة.")
        val sharedStrings = parts["xl/sharedStrings.xml"]?.let(::readSharedStrings).orEmpty()
        val sheet = parseXml(sheetBytes)
        val cellElements = sheet.getElementsByTagNameNS(SHEET_NS, "c")
        require(cellElements.length <= MAX_CELLS) { "يحتوي الملف على خلايا كثيرة جدًا للاستيراد الآمن." }
        val cells = linkedMapOf<String, String>()
        var unsupportedFormulaCount = 0
        for (index in 0 until cellElements.length) {
            val cell = cellElements.item(index) as? Element ?: continue
            val address = cell.getAttribute("r").uppercase()
            if (!address.matches(Regex("[A-Z]{1,3}[1-9][0-9]{0,6}"))) continue
            if (cellColumn(address) > 16384 || cellRow(address) > 1_048_576) continue
            val formulaNode = cell.getElementsByTagNameNS(SHEET_NS, "f").item(0)
            val formula = formulaNode?.textContent.orEmpty()
            val valueNode = cell.getElementsByTagNameNS(SHEET_NS, "v").item(0)
            val type = cell.getAttribute("t")
            val value = when (type) {
                "s" -> valueNode?.textContent?.toIntOrNull()?.let(sharedStrings::getOrNull).orEmpty()
                "inlineStr" -> cell.getElementsByTagNameNS(SHEET_NS, "t").let { texts -> (0 until texts.length).joinToString("") { texts.item(it).textContent.orEmpty() } }
                "b" -> if (valueNode?.textContent == "1") "TRUE" else "FALSE"
                "str" -> valueNode?.textContent.orEmpty()
                else -> valueNode?.textContent.orEmpty()
            }
            val stored = if (formula.isNotBlank()) "=$formula" else value
            if (formulaNode != null && (formula.isBlank() || !isSafeFormula(stored))) unsupportedFormulaCount++
            if (stored.isNotEmpty()) cells[address] = stored
        }
        val notices = mutableListOf("تم استيراد الخلايا والقيم والصيغ من الورقة الأولى؛ قد لا تنتقل التنسيقات والمخططات والتحقق من البيانات.")
        if (sheets.length > 1) notices += "تم استيراد الورقة الأولى فقط من المصنف متعدد الأوراق."
        if (unsupportedFormulaCount > 0) notices += "$unsupportedFormulaCount صيغة لا يدعمها المحرك المحلي محفوظة كما هي؛ ستُصدّر كنص لمنع تشغيل وظائف أو روابط غير مدعومة."
        val outsideGrid = cells.keys.count { cellRow(it) > 1000 || cellColumn(it) > 40 }
        if (outsideGrid > 0) notices += "توجد $outsideGrid خلية خارج مساحة التحرير الحالية (1000 صف × 40 عمود)؛ تبقى محفوظة عند التصدير لكنها غير قابلة للتحرير داخل الشبكة حاليًا."
        return ImportResult(
            LocalOfficeDocument(UUID.randomUUID().toString(), title.cleanOfficeTitle(), OfficeDocumentKind.EXCEL, cells = cells),
            notices
        )
    }

    fun exportXlsx(document: LocalOfficeDocument, output: OutputStream): Int {
        require(document.kind == OfficeDocumentKind.EXCEL) { "اختر جدول Excel للتصدير بصيغة XLSX." }
        require(document.cells.size <= MAX_CELLS) { "عدد الخلايا يتجاوز الحد الآمن للتصدير." }
        val ordered = document.cells.entries.filter { it.key.matches(Regex("[A-Z]{1,3}[1-9][0-9]{0,6}")) && cellColumn(it.key) <= 16384 && cellRow(it.key) <= 1_048_576 }
            .sortedWith(compareBy({ cellRow(it.key) }, { cellColumn(it.key) }))
        val rows = ordered.groupBy { cellRow(it.key) }
        val fontName = officeFontName(document.fontFamily)
        var unsupportedFormulaCount = 0
        val sheetXml = buildString {
            append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><worksheet xmlns=\"$SHEET_NS\"><sheetData>")
            rows.forEach { (row, entries) ->
                append("<row r=\"").append(row).append("\">")
                entries.forEach { (address, raw) ->
                    val value = raw.trim()
                    val isFormula = value.startsWith("=")
                    val exportFormula = isFormula && isSafeFormula(value)
                    if (isFormula && !exportFormula) unsupportedFormulaCount++
                    val numeric = if (isFormula) null else normalizeOfficeNumber(value)
                    append("<c r=\"").append(address).append("\"")
                    if (!exportFormula && numeric == null) append(" t=\"inlineStr\"")
                    append(">")
                    if (exportFormula) {
                        append("<f>").append(escapeXml(value.drop(1))).append("</f>")
                    } else {
                        if (numeric != null) append("<v>").append(escapeXml(numeric)).append("</v>")
                        else append("<is><t xml:space=\"preserve\">").append(escapeXml(raw)).append("</t></is>")
                    }
                    append("</c>")
                }
                append("</row>")
            }
            append("</sheetData></worksheet>")
        }
        val styles = """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><styleSheet xmlns="$SHEET_NS"><fonts count="1"><font><sz val="11"/><name val="${escapeXml(fontName)}"/><family val="2"/></font></fonts><fills count="2"><fill><patternFill patternType="none"/></fill><fill><patternFill patternType="gray125"/></fill></fills><borders count="1"><border><left/><right/><top/><bottom/><diagonal/></border></borders><cellStyleXfs count="1"><xf numFmtId="0" fontId="0" fillId="0" borderId="0"/></cellStyleXfs><cellXfs count="1"><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0"/></cellXfs><cellStyles count="1"><cellStyle name="Normal" xfId="0" builtinId="0"/></cellStyles></styleSheet>"""
        ZipOutputStream(output).use { zip ->
            zip.addText("[Content_Types].xml", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types"><Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/><Default Extension="xml" ContentType="application/xml"/><Override PartName="/xl/workbook.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml"/><Override PartName="/xl/worksheets/sheet1.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml"/><Override PartName="/xl/styles.xml" ContentType="application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml"/></Types>""")
            zip.addText("_rels/.rels", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Relationships xmlns="$PACKAGE_REL_NS"><Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="xl/workbook.xml"/></Relationships>""")
            zip.addText("xl/workbook.xml", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><workbook xmlns="$SHEET_NS" xmlns:r="$REL_NS"><sheets><sheet name="${escapeXml(document.title.take(31).ifBlank { "Sheet1" })}" sheetId="1" r:id="rId1"/></sheets><calcPr calcMode="auto" fullCalcOnLoad="1" forceFullCalc="1"/></workbook>""")
            zip.addText("xl/_rels/workbook.xml.rels", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Relationships xmlns="$PACKAGE_REL_NS"><Relationship Id="rId1" Type="$REL_NS/worksheet" Target="worksheets/sheet1.xml"/><Relationship Id="rId2" Type="$REL_NS/styles" Target="styles.xml"/></Relationships>""")
            zip.addText("xl/styles.xml", styles)
            zip.addText("xl/worksheets/sheet1.xml", sheetXml)
        }
        return unsupportedFormulaCount
    }

    private fun buildWordDocument(text: Spanned, fontKey: String): String {
        val font = escapeXml(officeFontName(fontKey))
        val value = text.toString()
        return buildString {
            append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><w:document xmlns:w=\"$WORD_NS\"><w:body>")
            var start = 0
            while (start <= value.length) {
                val newline = value.indexOf('\n', start)
                val end = if (newline < 0) value.length else newline
                val alignment = text.getSpans(start.coerceAtMost((text.length - 1).coerceAtLeast(0)), end.coerceAtLeast(start).coerceAtMost(text.length), AlignmentSpan::class.java).firstOrNull()?.alignment
                append("<w:p>")
                val alignValue = when (alignment) {
                    android.text.Layout.Alignment.ALIGN_CENTER -> "center"
                    android.text.Layout.Alignment.ALIGN_NORMAL -> "left"
                    android.text.Layout.Alignment.ALIGN_OPPOSITE -> "right"
                    else -> null
                }
                if (alignValue != null) append("<w:pPr><w:jc w:val=\"").append(alignValue).append("\"/></w:pPr>")
                var cursor = start
                while (cursor < end) {
                    val style = wordStyleAt(text, cursor, font)
                    var next = cursor + 1
                    while (next < end && wordStyleAt(text, next, font) == style) next++
                    append("<w:r><w:rPr>")
                    if (style.bold) append("<w:b/>")
                    if (style.italic) append("<w:i/>")
                    if (style.underline) append("<w:u w:val=\"single\"/>")
                    if (style.color != null) append("<w:color w:val=\"").append(style.color).append("\"/>")
                    append("<w:rFonts w:ascii=\"").append(font).append("\" w:hAnsi=\"").append(font).append("\" w:eastAsia=\"").append(font).append("\" w:cs=\"").append(font).append("\"/>")
                    append("<w:sz w:val=\"").append(style.halfPoints).append("\"/><w:szCs w:val=\"").append(style.halfPoints).append("\"/></w:rPr><w:t xml:space=\"preserve\">")
                    append(escapeXml(value.substring(cursor, next))).append("</w:t></w:r>")
                    cursor = next
                }
                if (end == start) append("<w:r><w:rPr><w:rFonts w:ascii=\"").append(font).append("\" w:cs=\"").append(font).append("\"/></w:rPr><w:t></w:t></w:r>")
                append("</w:p>")
                if (newline < 0) break
                start = newline + 1
            }
            append("<w:sectPr><w:pgSz w:w=\"11906\" w:h=\"16838\"/><w:pgMar w:top=\"1440\" w:right=\"1440\" w:bottom=\"1440\" w:left=\"1440\" w:header=\"720\" w:footer=\"720\" w:gutter=\"0\"/></w:sectPr></w:body></w:document>")
        }
    }

    private data class WordRunStyle(val bold: Boolean, val italic: Boolean, val underline: Boolean, val color: String?, val halfPoints: Int)

    private fun wordStyleAt(text: Spanned, position: Int, font: String): WordRunStyle {
        val styleSpans = text.getSpans(position, position + 1, StyleSpan::class.java)
        val type = styleSpans.fold(0) { acc, span -> acc or span.style }
        val foreground = text.getSpans(position, position + 1, ForegroundColorSpan::class.java).lastOrNull()?.foregroundColor
        val color = foreground?.let { String.format(java.util.Locale.ROOT, "%06X", it and 0xFFFFFF) }
        val size = text.getSpans(position, position + 1, AbsoluteSizeSpan::class.java).lastOrNull()?.size ?: 11
        return WordRunStyle(type and Typeface.BOLD != 0, type and Typeface.ITALIC != 0,
            text.getSpans(position, position + 1, UnderlineSpan::class.java).isNotEmpty(), color, (size.coerceIn(6, 72) * 2))
    }

    private fun extractWordRunText(run: Element): String = buildString {
        fun visit(node: Node) {
            when (node.nodeType) {
                Node.ELEMENT_NODE -> when (node.localName ?: node.nodeName.substringAfter(':')) {
                    "t" -> append(node.textContent.orEmpty())
                    "tab" -> append('\t')
                    "br", "cr" -> append('\n')
                    else -> {
                        val children = node.childNodes
                        for (index in 0 until children.length) visit(children.item(index))
                    }
                }
                else -> Unit
            }
        }
        visit(run)
    }

    private fun readSharedStrings(bytes: ByteArray): List<String> {
        val root = parseXml(bytes)
        val items = root.getElementsByTagNameNS(SHEET_NS, "si")
        return (0 until items.length).map { index ->
            val item = items.item(index)
            val texts = (item as Element).getElementsByTagNameNS(SHEET_NS, "t")
            (0 until texts.length).joinToString("") { texts.item(it).textContent.orEmpty() }
        }
    }

    private fun readZip(input: InputStream): Map<String, ByteArray> {
        val relevant = mutableMapOf<String, ByteArray>()
        var totalBytes = 0L
        var count = 0
        ZipInputStream(input).use { zip ->
            while (true) {
                val entry = zip.nextEntry ?: break
                require(++count <= MAX_ENTRIES) { "يحتوي ملف Office على عناصر كثيرة جدًا." }
                if (entry.isDirectory) continue
                val buffer = ByteArray(8192)
                val captured = ByteArrayOutputStream()
                var entryBytes = 0
                while (true) {
                    val read = zip.read(buffer)
                    if (read < 0) break
                    entryBytes += read
                    totalBytes += read
                    require(entryBytes <= MAX_ENTRY_BYTES && totalBytes <= MAX_TOTAL_UNCOMPRESSED_BYTES) {
                        "حجم الملف المضغوط بعد فكّه يتجاوز الحد الآمن للاستيراد."
                    }
                    val name = entry.name
                    if (name.endsWith(".xml") || name.endsWith(".rels")) captured.write(buffer, 0, read)
                }
                if (entry.name.endsWith(".xml") || entry.name.endsWith(".rels")) relevant[entry.name] = captured.toByteArray()
                zip.closeEntry()
            }
        }
        return relevant
    }

    private fun parseXml(bytes: ByteArray): org.w3c.dom.Document {
        val factory = DocumentBuilderFactory.newInstance().apply {
            isNamespaceAware = true
            isXIncludeAware = false
            isExpandEntityReferences = false
            runCatching { setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true) }
            runCatching { setFeature("http://apache.org/xml/features/disallow-doctype-decl", true) }
            runCatching { setFeature("http://xml.org/sax/features/external-general-entities", false) }
            runCatching { setFeature("http://xml.org/sax/features/external-parameter-entities", false) }
            runCatching { setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "") }
            runCatching { setAttribute("http://javax.xml.XMLConstants/property/accessExternalSchema", "") }
        }
        val builder = factory.newDocumentBuilder().apply { setEntityResolver { _, _ -> InputSource(StringReader("")) } }
        return builder.parse(ByteArrayInputStream(bytes))
    }

    private fun ZipOutputStream.addText(path: String, value: String) {
        putNextEntry(ZipEntry(path))
        write(value.toByteArray(StandardCharsets.UTF_8))
        closeEntry()
    }

    private fun attributeByLocalName(element: Element?, name: String): String? {
        if (element == null) return null
        val attributes = element.attributes
        for (index in 0 until attributes.length) {
            val attribute = attributes.item(index)
            if ((attribute.localName ?: attribute.nodeName.substringAfter(':')) == name) return attribute.nodeValue
        }
        return null
    }

    private fun hasActiveWordFlag(properties: Element?, flag: String): Boolean {
        if (properties == null) return false
        val nodes = properties.getElementsByTagNameNS(WORD_NS, flag)
        for (index in 0 until nodes.length) {
            val value = attributeByLocalName(nodes.item(index) as? Element, "val")
            if (value == null || value !in setOf("0", "false", "off", "none")) return true
        }
        return false
    }

    private fun escapeXml(value: String): String = value
        .replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
        .replace("\"", "&quot;").replace("'", "&apos;")

    private fun escapeHtml(value: String): String = value
        .replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;")

    private fun normalizeOfficeNumber(value: String): String? {
        if (value.isBlank()) return null
        val normalized = value
            .replace('٠', '0').replace('١', '1').replace('٢', '2').replace('٣', '3').replace('٤', '4')
            .replace('٥', '5').replace('٦', '6').replace('٧', '7').replace('٨', '8').replace('٩', '9')
            .replace('۰', '0').replace('۱', '1').replace('۲', '2').replace('۳', '3').replace('۴', '4')
            .replace('۵', '5').replace('۶', '6').replace('۷', '7').replace('۸', '8').replace('۹', '9')
            .replace("٬", "").replace('٫', '.')
        return normalized.takeIf { it.matches(Regex("[-+]?(?:[0-9]+(?:\\.[0-9]*)?|\\.[0-9]+)(?:[eE][-+]?[0-9]+)?")) }
    }

    private fun isSafeFormula(formula: String): Boolean = SpreadsheetFormulaEvaluator.evaluate(formula, emptyMap()) != "#VALUE!"

    private fun cellRow(address: String): Int = address.takeLastWhile(Char::isDigit).toIntOrNull() ?: Int.MAX_VALUE
    private fun cellColumn(address: String): Int = address.dropLastWhile(Char::isDigit).fold(0) { sum, c -> sum * 26 + (c - 'A' + 1) }

    private fun officeFontName(key: String): String = when (key.lowercase()) {
        "amiri_regular" -> "Amiri"
        "tajawal_regular" -> "Tajawal"
        "noto_naskh_regular" -> "Noto Naskh Arabic"
        "scheherazade_regular" -> "Scheherazade New"
        "el_messiri_regular" -> "El Messiri"
        else -> "Cairo"
    }

    private fun officeFontKey(name: String): String = when {
        name.contains("amiri", ignoreCase = true) -> "amiri_regular"
        name.contains("tajawal", ignoreCase = true) -> "tajawal_regular"
        name.contains("naskh", ignoreCase = true) -> "noto_naskh_regular"
        name.contains("scheherazade", ignoreCase = true) -> "scheherazade_regular"
        name.contains("messiri", ignoreCase = true) -> "el_messiri_regular"
        else -> "cairo_regular"
    }

    private fun String.cleanOfficeTitle(): String {
        val fileName = substringAfterLast('/').substringAfterLast('\\')
        val extension = fileName.substringAfterLast('.', "").lowercase()
        val title = if (extension == "docx" || extension == "xlsx") fileName.dropLast(extension.length + 1) else fileName
        return title.trim().ifBlank { "مستند مستورد" }
    }
}
