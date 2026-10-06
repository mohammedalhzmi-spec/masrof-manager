package com.mohammedalhzmi.masrofmanager.util

import android.text.Html
import android.text.Html.ImageGetter
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.AlignmentSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.text.style.ImageSpan
import android.graphics.Typeface
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.util.Base64
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
    private const val MAX_IMAGE_BYTES = 4 * 1024 * 1024
    private const val MAX_TOTAL_UNCOMPRESSED_BYTES = 24 * 1024 * 1024
    private const val MAX_COMPRESSED_BYTES = 32 * 1024 * 1024
    private const val MAX_CELLS = 10_000

    data class ImportResult(val document: LocalOfficeDocument, val notices: List<String> = emptyList())

    /** Detect actual OOXML package content instead of trusting a provider's often-generic MIME/name. */
    fun importOffice(input: InputStream, title: String, mimeType: String? = null): ImportResult {
        val bytes = readBounded(input, MAX_COMPRESSED_BYTES)
        val packageParts = inspectOfficePackage(bytes)
        return when {
            "word/document.xml" in packageParts -> importDocx(ByteArrayInputStream(bytes), title)
            "xl/workbook.xml" in packageParts -> importXlsx(ByteArrayInputStream(bytes), title)
            else -> {
                val typeHint = mimeType.orEmpty().lowercase()
                val suffix = title.substringAfterLast('.', "").lowercase()
                when {
                    typeHint == DOCX_MIME || suffix == "docx" -> error("الملف لا يحتوي على حزمة Word DOCX صالحة. إذا كان بصيغة DOC القديمة فلن يفتحها هذا المحرر بعد.")
                    typeHint == XLSX_MIME || suffix == "xlsx" -> error("الملف لا يحتوي على حزمة Excel XLSX صالحة. إذا كان بصيغة XLS القديمة فلن يفتحها هذا المحرر بعد.")
                    else -> error("تعذر تحديد الصيغة. يدعم الاستيراد ملفات DOCX وXLSX فقط، وليس DOC أو XLS القديمين.")
                }
            }
        }
    }

    private fun inspectOfficePackage(bytes: ByteArray): Set<String> {
        require(bytes.size >= 4 && bytes[0] == 0x50.toByte() && bytes[1] == 0x4b.toByte()) {
            "الملف المحدد ليس ملف Office حديثًا بصيغة DOCX أو XLSX."
        }
        val relevant = mutableSetOf<String>()
        var total = 0L
        var count = 0
        ZipInputStream(ByteArrayInputStream(bytes)).use { zip ->
            while (true) {
                val entry = zip.nextEntry ?: break
                require(++count <= MAX_ENTRIES) { "يحتوي الملف على عناصر كثيرة جدًا." }
                if (entry.name == "word/document.xml" || entry.name == "xl/workbook.xml") relevant += entry.name
                var size = 0
                val buffer = ByteArray(8192)
                while (true) {
                    val n = zip.read(buffer)
                    if (n < 0) break
                    size += n
                    total += n
                    require(size <= MAX_ENTRY_BYTES && total <= MAX_TOTAL_UNCOMPRESSED_BYTES) {
                        "حجم الملف بعد فك الضغط يتجاوز الحد الآمن للاستيراد."
                    }
                }
                zip.closeEntry()
            }
        }
        return relevant
    }

    private fun readBounded(input: InputStream, maxBytes: Int): ByteArray {
        val output = ByteArrayOutputStream()
        val buffer = ByteArray(8192)
        var total = 0
        input.use { stream ->
            while (true) {
                val n = stream.read(buffer)
                if (n < 0) break
                total += n
                require(total <= maxBytes) { "حجم ملف Office أكبر من الحد المسموح للاستيراد (32 ميجابايت)." }
                output.write(buffer, 0, n)
            }
        }
        return output.toByteArray()
    }

    fun importDocx(input: InputStream, title: String): ImportResult {
        val parts = readZip(input)
        val wordXml = parts["word/document.xml"] ?: error("الملف لا يحتوي على مستند Word صالح.")
        val root = parseXml(wordXml)
        val body = root.getElementsByTagNameNS(WORD_NS, "body").item(0) as? Element ?: error("ملف Word فارغ أو غير صالح.")
        val imageData = wordImageData(parts)
        val usedImages = linkedSetOf<String>()
        val fontTracker = FontTracker()
        val html = StringBuilder()
        val children = body.childNodes
        for (index in 0 until children.length) {
            val element = children.item(index) as? Element ?: continue
            when (element.localName ?: element.nodeName.substringAfter(':')) {
                "p" -> appendWordParagraph(html, element, imageData, usedImages, fontTracker)
                "tbl" -> appendWordTable(html, element, imageData, usedImages, fontTracker)
            }
        }
        val notices = mutableListOf("تم استيراد النص والفقرات والتنسيق الأساسي. راجع المستند؛ قد تختلف بعض تفاصيل التخطيط بين التطبيقات.")
        if (root.getElementsByTagNameNS(WORD_NS, "tbl").length > 0) notices += "استُوردت الجداول ببنية الصفوف والخلايا ودمج الأعمدة الأساسي؛ قد تختلف بعض حدود Word والتنسيقات المركبة."
        val drawingCount = root.getElementsByTagNameNS(WORD_NS, "drawing").length + root.getElementsByTagNameNS(WORD_NS, "pict").length
        if (usedImages.isNotEmpty()) notices += "تم تضمين ${usedImages.size} صورة مرتبطة داخل المستند؛ قد يختلف حجم العرض عن Word."
        if (drawingCount > usedImages.size) notices += "بعض الرسومات أو الأشكال المتجهة لم تُحوّل إلى صور نقطية قابلة للتحرير."
        if (parts.keys.any { it.startsWith("word/header") || it.startsWith("word/footer") }) notices += "الترويسات والتذييلات ليست ضمن نطاق الاستيراد الحالي."
        return ImportResult(
            LocalOfficeDocument(UUID.randomUUID().toString(), title.cleanOfficeTitle(), OfficeDocumentKind.WORD, wordHtml = html.toString(), fontFamily = fontTracker.key),
            notices
        )
    }

    private class FontTracker(var key: String = "cairo_regular", var found: Boolean = false)

    private fun appendWordParagraph(
        html: StringBuilder,
        paragraph: Element,
        imageData: Map<String, String>,
        usedImages: MutableSet<String>,
        fontTracker: FontTracker
    ) {
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
            val content = extractWordRunHtml(run, imageData, usedImages)
            if (content.isEmpty()) continue
            val properties = run.getElementsByTagNameNS(WORD_NS, "rPr").item(0) as? Element
            if (!fontTracker.found) {
                val fonts = properties?.getElementsByTagNameNS(WORD_NS, "rFonts")?.item(0) as? Element
                val fontName = attributeByLocalName(fonts, "cs") ?: attributeByLocalName(fonts, "eastAsia") ?: attributeByLocalName(fonts, "ascii")
                if (!fontName.isNullOrBlank()) { fontTracker.key = officeFontKey(fontName); fontTracker.found = true }
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
            if (css.isNotEmpty()) html.append("<span style=\"").append(css).append("\">").append(content).append("</span>")
            else html.append(content)
        }
        if (runs.length == 0) html.append("<br>")
        html.append("</p>")
    }

    private fun appendWordTable(
        html: StringBuilder,
        table: Element,
        imageData: Map<String, String>,
        usedImages: MutableSet<String>,
        fontTracker: FontTracker
    ) {
        html.append("<table border=\"1\" style=\"border-collapse:collapse;width:100%\"><tbody>")
        directChildren(table, "tr").forEach { row ->
            html.append("<tr>")
            directChildren(row, "tc").forEach { cell ->
                val properties = directChildren(cell, "tcPr").firstOrNull()
                val span = properties?.getElementsByTagNameNS(WORD_NS, "gridSpan")?.item(0) as? Element
                val colspan = attributeByLocalName(span, "val")?.toIntOrNull()?.coerceIn(1, 20) ?: 1
                val shading = properties?.getElementsByTagNameNS(WORD_NS, "shd")?.item(0) as? Element
                val fill = attributeByLocalName(shading, "fill")?.takeIf { it.matches(Regex("[0-9A-Fa-f]{6}")) }
                val width = properties?.getElementsByTagNameNS(WORD_NS, "tcW")?.item(0) as? Element
                val widthPx = attributeByLocalName(width, "w")?.toIntOrNull()?.div(15)?.coerceIn(36, 900)
                html.append("<td colspan=\"").append(colspan).append("\" style=\"")
                if (fill != null && fill != "auto") html.append("background-color:#").append(fill).append(';')
                if (widthPx != null) html.append("width:").append(widthPx).append("px;")
                html.append("vertical-align:top\">")
                var cellHasContent = false
                val nodes = cell.childNodes
                for (nodeIndex in 0 until nodes.length) {
                    val element = nodes.item(nodeIndex) as? Element ?: continue
                    when (element.localName ?: element.nodeName.substringAfter(':')) {
                        "p" -> { appendWordParagraph(html, element, imageData, usedImages, fontTracker); cellHasContent = true }
                        "tbl" -> { appendWordTable(html, element, imageData, usedImages, fontTracker); cellHasContent = true }
                    }
                }
                if (!cellHasContent) html.append("<p><br></p>")
                html.append("</td>")
            }
            html.append("</tr>")
        }
        html.append("</tbody></table>")
    }

    private fun directChildren(parent: Element, localName: String): List<Element> {
        val output = mutableListOf<Element>()
        val children = parent.childNodes
        for (index in 0 until children.length) {
            val child = children.item(index) as? Element ?: continue
            if ((child.localName ?: child.nodeName.substringAfter(':')) == localName) output += child
        }
        return output
    }

    private fun wordImageData(parts: Map<String, ByteArray>): Map<String, String> {
        val relations = parts["word/_rels/document.xml.rels"] ?: return emptyMap()
        val entries = parseXml(relations).getElementsByTagNameNS(PACKAGE_REL_NS, "Relationship")
        var totalImageBytes = 0
        var capturedImages = 0
        return buildMap {
            for (index in 0 until entries.length) {
                val relation = entries.item(index) as? Element ?: continue
                if (!relation.getAttribute("Type").endsWith("/image")) continue
                val id = relation.getAttribute("Id")
                val target = relation.getAttribute("Target").substringBefore('#').substringBefore('?').replace('\\', '/')
                val path = if (target.startsWith("/")) target.removePrefix("/") else "word/$target"
                if (id.isBlank() || path.split('/').contains("..") || !path.startsWith("word/media/")) continue
                val image = parts[path] ?: continue
                if (capturedImages >= 32 || totalImageBytes + image.size > 12 * 1024 * 1024) continue
                val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
                BitmapFactory.decodeByteArray(image, 0, image.size, bounds)
                if (bounds.outWidth !in 1..20_000 || bounds.outHeight !in 1..20_000 || bounds.outWidth.toLong() * bounds.outHeight > 20_000_000L) continue
                val mime = when (path.substringAfterLast('.', "").lowercase()) {
                    "png" -> "image/png"
                    "jpg", "jpeg" -> "image/jpeg"
                    "gif" -> "image/gif"
                    "webp" -> "image/webp"
                    "bmp" -> "image/bmp"
                    else -> continue
                }
                put(id, "data:$mime;base64,${Base64.encodeToString(image, Base64.NO_WRAP)}")
                totalImageBytes += image.size
                capturedImages++
            }
        }
    }

    private fun extractWordRunHtml(run: Element, imageData: Map<String, String>, usedImages: MutableSet<String>): String = buildString {
        fun visit(node: Node) {
            when (node.nodeType) {
                Node.ELEMENT_NODE -> when (node.localName ?: node.nodeName.substringAfter(':')) {
                    "t" -> append(escapeHtml(node.textContent.orEmpty()))
                    "tab" -> append("&#9;")
                    "br", "cr" -> append("<br>")
                    "blip", "imagedata" -> {
                        val id = attributeByLocalName(node as? Element, "embed") ?: attributeByLocalName(node as? Element, "id")
                        val source = id?.let(imageData::get)
                        if (id != null && source != null) {
                            usedImages += id
                            append("<img src=\"").append(source).append("\" alt=\"صورة مضمّنة\" style=\"max-width:100%;height:auto\" />")
                        }
                    }
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

    fun exportDocx(document: LocalOfficeDocument, output: OutputStream) {
        require(document.kind == OfficeDocumentKind.WORD) { "اختر مستند Word للتصدير بصيغة DOCX." }
        val images = collectWordImages(document.wordHtml)
        val tables = mutableListOf<String>()
        val withoutTables = TABLE_HTML.replace(document.wordHtml) { match ->
            val index = tables.size
            tables += match.value
            "<p>OFFICE_TABLE_TOKEN_$index</p>"
        }
        val imageMap = images.associateBy(WordImageAsset::source)
        val imageGetter = ImageGetter { source ->
            val image = source?.let(imageMap::get) ?: return@ImageGetter null
            ColorDrawable(android.graphics.Color.TRANSPARENT).apply { setBounds(0, 0, image.displayWidth, image.displayHeight) }
        }
        val richText = Html.fromHtml(withoutTables, Html.FROM_HTML_MODE_LEGACY, imageGetter, null)
        val xml = buildWordDocument(richText, document.fontFamily, tables, imageMap)
        val imageDefaults = images.map { it.extension }.distinct().joinToString("") { ext ->
            val contentType = when (ext) { "jpg" -> "image/jpeg"; "png" -> "image/png"; "gif" -> "image/gif"; "bmp" -> "image/bmp"; else -> "image/png" }
            "<Default Extension=\"$ext\" ContentType=\"$contentType\"/>"
        }
        ZipOutputStream(output).use { zip ->
            zip.addText("[Content_Types].xml", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types"><Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/><Default Extension="xml" ContentType="application/xml"/>$imageDefaults<Override PartName="/word/document.xml" ContentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml"/></Types>""")
            zip.addText("_rels/.rels", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Relationships xmlns="$PACKAGE_REL_NS"><Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="word/document.xml"/></Relationships>""")
            zip.addText("word/document.xml", xml)
            if (images.isNotEmpty()) {
                val relations = images.joinToString("") { image ->
                    "<Relationship Id=\"${image.relationshipId}\" Type=\"$REL_NS/image\" Target=\"media/${image.fileName}\"/>"
                }
                zip.addText("word/_rels/document.xml.rels", """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><Relationships xmlns="$PACKAGE_REL_NS">$relations</Relationships>""")
                images.forEach { image -> zip.addBytes("word/media/${image.fileName}", image.bytes) }
            }
        }
    }

    private val TABLE_HTML = Regex("(?is)<table\\b[^>]*>.*?</table\\s*>")

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

    private data class WordImageAsset(
        val source: String,
        val relationshipId: String,
        val fileName: String,
        val extension: String,
        val bytes: ByteArray,
        val displayWidth: Int,
        val displayHeight: Int,
        val cx: Long,
        val cy: Long
    )

    private fun collectWordImages(html: String): List<WordImageAsset> {
        val images = linkedMapOf<String, WordImageAsset>()
        var totalBytes = 0
        val tags = Regex("(?is)<img\\b([^>]*)>")
        for (match in tags.findAll(html)) {
            if (images.size >= 32) break
            val attrs = match.groupValues[1]
            val source = Regex("(?i)\\bsrc\\s*=\\s*(['\"])(.*?)\\1").find(attrs)?.groupValues?.get(2) ?: continue
            if (source in images) continue
            if (!source.startsWith("data:image/")) continue
            val mime = source.substringAfter("data:").substringBefore(';').lowercase()
            if (mime !in setOf("image/png", "image/jpeg", "image/gif", "image/bmp", "image/webp")) continue
            val encoded = source.substringAfter("base64,", "")
            if (encoded.isBlank()) continue
            val decoded = runCatching { Base64.decode(encoded, Base64.DEFAULT) }.getOrNull() ?: continue
            if (decoded.isEmpty() || decoded.size > MAX_IMAGE_BYTES || totalBytes + decoded.size > 12 * 1024 * 1024) continue
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeByteArray(decoded, 0, decoded.size, bounds)
            if (bounds.outWidth !in 1..20_000 || bounds.outHeight !in 1..20_000 || bounds.outWidth.toLong() * bounds.outHeight > 20_000_000L) continue
            var bytes = decoded
            var extension = when (mime) { "image/jpeg" -> "jpg"; "image/gif" -> "gif"; "image/bmp" -> "bmp"; "image/webp" -> "webp"; else -> "png" }
            if (mime == "image/webp") {
                val bitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.size) ?: continue
                val png = ByteArrayOutputStream()
                val success = bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, png)
                bitmap.recycle()
                if (!success) continue
                bytes = png.toByteArray()
                extension = "png"
            }
            val widthHint = Regex("(?i)width\\s*[:=]\\s*['\"]?(\\d+)").find(attrs)?.groupValues?.get(1)?.toIntOrNull()
            val width = (widthHint ?: minOf(bounds.outWidth, 520)).coerceIn(48, 900)
            val height = (width.toLong() * bounds.outHeight / bounds.outWidth).coerceIn(1, 6000).toInt()
            val cx = (width.toLong() * 9525).coerceAtMost(5_800_000L)
            val cy = (cx * bounds.outHeight / bounds.outWidth).coerceIn(1, 8_000_000L)
            val id = images.size + 1
            images[source] = WordImageAsset(source, "rIdOfficeImage$id", "office-image-$id.$extension", extension, bytes, width, height, cx, cy)
            totalBytes += decoded.size
        }
        return images.values.toList()
    }

    private fun buildWordDocument(text: Spanned, fontKey: String, tables: List<String>, images: Map<String, WordImageAsset>): String {
        val font = escapeXml(officeFontName(fontKey))
        val value = text.toString()
        val tableTokens = tables.indices.associateBy { "OFFICE_TABLE_TOKEN_$it" }
        return buildString {
            append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><w:document xmlns:w=\"$WORD_NS\" xmlns:r=\"$REL_NS\" xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><w:body>")
            var start = 0
            while (start <= value.length) {
                val newline = value.indexOf('\n', start)
                val end = if (newline < 0) value.length else newline
                val line = value.substring(start, end).trim()
                val tableIndex = tableTokens[line]
                if (tableIndex != null) {
                    append(buildWordTable(tables[tableIndex], font, images))
                    if (newline < 0) break
                    start = newline + 1
                    continue
                }
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
                    val imageSpan = text.getSpans(cursor, cursor + 1, ImageSpan::class.java).firstOrNull()
                    if (imageSpan != null) {
                        val asset = imageSpan.source?.let(images::get)
                        if (asset != null) append(wordImageDrawing(asset, cursor + 1))
                        cursor++
                        continue
                    }
                    if (value[cursor] == '\t') {
                        append("<w:r><w:rPr><w:rFonts w:ascii=\"").append(font).append("\" w:cs=\"").append(font).append("\"/></w:rPr><w:tab/></w:r>")
                        cursor++
                        continue
                    }
                    val style = wordStyleAt(text, cursor, font)
                    var next = cursor + 1
                    while (next < end && text.getSpans(next, next + 1, ImageSpan::class.java).isEmpty() && value[next] != '\t' && wordStyleAt(text, next, font) == style) next++
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

    private fun wordImageDrawing(image: WordImageAsset, index: Int): String =
        "<w:r><w:drawing><wp:inline distT=\"0\" distB=\"0\" distL=\"0\" distR=\"0\"><wp:extent cx=\"${image.cx}\" cy=\"${image.cy}\"/><wp:docPr id=\"$index\" name=\"${image.fileName}\"/><wp:cNvGraphicFramePr><a:graphicFrameLocks noChangeAspect=\"1\"/></wp:cNvGraphicFramePr><a:graphic><a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><pic:pic><pic:nvPicPr><pic:cNvPr id=\"$index\" name=\"${image.fileName}\"/><pic:cNvPicPr/></pic:nvPicPr><pic:blipFill><a:blip r:embed=\"${image.relationshipId}\"/><a:stretch><a:fillRect/></a:stretch></pic:blipFill><pic:spPr><a:xfrm><a:off x=\"0\" y=\"0\"/><a:ext cx=\"${image.cx}\" cy=\"${image.cy}\"/></a:xfrm><a:prstGeom prst=\"rect\"><a:avLst/></a:prstGeom></pic:spPr></pic:pic></a:graphicData></a:graphic></wp:inline></w:drawing></w:r>"

    private fun buildWordTable(tableHtml: String, font: String, images: Map<String, WordImageAsset>): String {
        val rowMatches = Regex("(?is)<tr\\b[^>]*>(.*?)</tr\\s*>").findAll(tableHtml).take(100).toList()
        if (rowMatches.isEmpty()) return ""
        val rows = rowMatches.map { row -> Regex("(?is)<t[dh]\\b([^>]*)>(.*?)</t[dh]\\s*>").findAll(row.groupValues[1]).take(20).toList() }
        val columns = rows.maxOfOrNull { row -> row.sumOf { Regex("(?i)colspan\\s*=\\s*['\"]?(\\d+)").find(it.groupValues[1])?.groupValues?.get(1)?.toIntOrNull()?.coerceIn(1, 20) ?: 1 } }?.coerceAtLeast(1) ?: 1
        return buildString {
            append("<w:tbl><w:tblPr><w:tblW w:w=\"5000\" w:type=\"pct\"/><w:tblBorders><w:top w:val=\"single\" w:sz=\"4\"/><w:left w:val=\"single\" w:sz=\"4\"/><w:bottom w:val=\"single\" w:sz=\"4\"/><w:right w:val=\"single\" w:sz=\"4\"/><w:insideH w:val=\"single\" w:sz=\"4\"/><w:insideV w:val=\"single\" w:sz=\"4\"/></w:tblBorders></w:tblPr><w:tblGrid>")
            repeat(columns) { append("<w:gridCol w:w=\"2400\"/>") }
            append("</w:tblGrid>")
            rows.forEach { row ->
                append("<w:tr>")
                row.forEach { cell ->
                    val attrs = cell.groupValues[1]
                    val body = cell.groupValues[2]
                    val colspan = Regex("(?i)colspan\\s*=\\s*['\"]?(\\d+)").find(attrs)?.groupValues?.get(1)?.toIntOrNull()?.coerceIn(1, 20) ?: 1
                    val color = Regex("(?i)background-color\\s*:\\s*#([0-9a-f]{6})").find(attrs)?.groupValues?.get(1)
                    val imageGetter = ImageGetter { source ->
                        val image = source?.let(images::get) ?: return@ImageGetter null
                        ColorDrawable(android.graphics.Color.TRANSPARENT).apply { setBounds(0, 0, image.displayWidth, image.displayHeight) }
                    }
                    val styled = Html.fromHtml(body, Html.FROM_HTML_MODE_LEGACY, imageGetter, null)
                    val lines = styled.toString().split('\n').ifEmpty { listOf("") }
                    append("<w:tc><w:tcPr><w:tcW w:w=\"2400\" w:type=\"dxa\"/>")
                    if (colspan > 1) append("<w:gridSpan w:val=\"").append(colspan).append("\"/>")
                    if (color != null) append("<w:shd w:fill=\"").append(color.uppercase()).append("\"/>")
                    append("</w:tcPr>")
                    var offset = 0
                    lines.forEach { line ->
                        val end = (offset + line.length).coerceAtMost(styled.length)
                        append("<w:p>")
                        appendWordInlineRuns(this, styled, offset, end, font, images)
                        append("</w:p>")
                        offset = (end + 1).coerceAtMost(styled.length)
                    }
                    append("</w:tc>")
                }
                append("</w:tr>")
            }
            append("</w:tbl>")
        }
    }

    private fun appendWordInlineRuns(xml: StringBuilder, text: Spanned, start: Int, end: Int, font: String, images: Map<String, WordImageAsset>) {
        val value = text.toString()
        var cursor = start
        while (cursor < end) {
            val imageSpan = text.getSpans(cursor, cursor + 1, ImageSpan::class.java).firstOrNull()
            if (imageSpan != null) {
                imageSpan.source?.let(images::get)?.let { xml.append(wordImageDrawing(it, cursor + 1)) }
                cursor++
                continue
            }
            if (value[cursor] == '\t') { xml.append("<w:r><w:tab/></w:r>"); cursor++; continue }
            val style = wordStyleAt(text, cursor, font)
            var next = cursor + 1
            while (next < end && text.getSpans(next, next + 1, ImageSpan::class.java).isEmpty() && value[next] != '\t' && wordStyleAt(text, next, font) == style) next++
            xml.append("<w:r><w:rPr>")
            if (style.bold) xml.append("<w:b/>")
            if (style.italic) xml.append("<w:i/>")
            if (style.underline) xml.append("<w:u w:val=\"single\"/>")
            if (style.color != null) xml.append("<w:color w:val=\"").append(style.color).append("\"/>")
            xml.append("<w:rFonts w:ascii=\"").append(font).append("\" w:hAnsi=\"").append(font).append("\" w:eastAsia=\"").append(font).append("\" w:cs=\"").append(font).append("\"/><w:sz w:val=\"").append(style.halfPoints).append("\"/><w:szCs w:val=\"").append(style.halfPoints).append("\"/></w:rPr><w:t xml:space=\"preserve\">")
            xml.append(escapeXml(value.substring(cursor, next))).append("</w:t></w:r>")
            cursor = next
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
                val path = entry.name.replace('\\', '/')
                val extension = path.substringAfterLast('.', "").lowercase()
                val media = path.startsWith("word/media/") && extension in setOf("png", "jpg", "jpeg", "gif", "webp", "bmp")
                val capture = path.endsWith(".xml") || path.endsWith(".rels") || media
                val buffer = ByteArray(8192)
                val captured = ByteArrayOutputStream()
                var entryBytes = 0
                while (true) {
                    val read = zip.read(buffer)
                    if (read < 0) break
                    entryBytes += read
                    totalBytes += read
                    val entryLimit = if (media) MAX_IMAGE_BYTES else MAX_ENTRY_BYTES
                    require(entryBytes <= entryLimit && totalBytes <= MAX_TOTAL_UNCOMPRESSED_BYTES) {
                        "حجم الملف المضغوط بعد فكّه يتجاوز الحد الآمن للاستيراد."
                    }
                    if (capture) captured.write(buffer, 0, read)
                }
                if (capture) relevant[path] = captured.toByteArray()
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

    private fun ZipOutputStream.addBytes(path: String, bytes: ByteArray) {
        putNextEntry(ZipEntry(path))
        write(bytes)
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
