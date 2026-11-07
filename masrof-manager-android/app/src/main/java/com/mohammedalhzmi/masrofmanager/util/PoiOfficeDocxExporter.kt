package com.mohammedalhzmi.masrofmanager.util

import android.util.Base64
import org.apache.poi.util.Units
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy
import org.apache.poi.xwpf.usermodel.BodyElementType
import org.apache.poi.xwpf.usermodel.BreakType
import org.apache.poi.xwpf.usermodel.ParagraphAlignment
import org.apache.poi.xwpf.usermodel.UnderlinePatterns
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.nodes.Node
import org.jsoup.nodes.TextNode
import java.io.ByteArrayInputStream
import java.io.OutputStream
import java.math.BigInteger

/** Apache POI DOCX export path used by the embedded REMOOFFICE editor. */
object PoiOfficeDocxExporter {
    private const val WORD_NS = "http://schemas.openxmlformats.org/wordprocessingml/2006/main"

    fun export(document: LocalOfficeDocument, output: OutputStream) {
        require(document.kind == OfficeDocumentKind.WORD) { "اختر مستند Word للتصدير بصيغة DOCX." }
        val doc = XWPFDocument()
        configurePage(doc, document)
        if (document.headerHtml.isNotBlank() || document.footerHtml.isNotBlank()) {
            val policy = XWPFHeaderFooterPolicy(doc)
            if (document.headerHtml.isNotBlank()) {
                val header = policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT)
                appendText(header.createParagraph(), document.headerHtml)
            }
            if (document.footerHtml.isNotBlank()) {
                val footer = policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT)
                appendText(footer.createParagraph(), document.footerHtml)
            }
        }
        val body = Jsoup.parseBodyFragment(HtmlSanitizer.sanitizeHtml(document.wordHtml)).body()
        for (element in body.children()) appendBlock(doc, element)
        if (doc.paragraphs.isEmpty() && doc.tables.isEmpty()) doc.createParagraph()
        doc.write(output)
        doc.close()
    }

    private fun configurePage(doc: XWPFDocument, source: LocalOfficeDocument) {
        val sect = doc.document.body.addNewSectPr()
        val size = sect.addNewPgSz()
        val landscape = source.orientation == "LANDSCAPE"
        val (w, h) = when (source.pageSize.uppercase()) {
            "A5" -> 148f to 210f
            "A3" -> 297f to 420f
            "LETTER" -> 216f to 279f
            else -> 210f to 297f
        }
        size.w = twips(if (landscape) h else w)
        size.h = twips(if (landscape) w else h)
        if (landscape) size.orient = org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation.LANDSCAPE
        val margin = sect.addNewPgMar()
        val mm = 25.4f
        margin.top = twips(mm); margin.bottom = twips(mm); margin.left = twips(mm); margin.right = twips(mm)
    }

    private fun appendBlock(doc: XWPFDocument, element: Element) {
        when (element.tagName().lowercase()) {
            "table" -> {
                val rows = element.select("tr")
                if (rows.isEmpty()) return
                val columns = rows.maxOfOrNull { it.select("td,th").size }?.coerceAtLeast(1) ?: 1
                val table = doc.createTable(rows.size, columns)
                rows.forEachIndexed { ri, rowElement ->
                    rowElement.select("td,th").forEachIndexed { ci, cellElement ->
                        if (ci < table.getRow(ri).tableCells.size) table.getRow(ri).getCell(ci).text = cellElement.text()
                    }
                }
            }
            "ul", "ol" -> element.select("> li").forEachIndexed { index, item ->
                val p = doc.createParagraph()
                p.setIndentationLeft(360)
                val prefix = if (element.tagName().equals("ol", true)) "${index + 1}. " else "• "
                p.createRun().setText(prefix)
                appendInline(p, item.childNodes(), Style())
            }
            "div", "p", "h1", "h2", "h3", "blockquote", "pre" -> {
                val p = doc.createParagraph()
                applyAlignment(p, element)
                appendInline(p, element.childNodes(), Style())
            }
            "br" -> doc.createParagraph().createRun().addBreak(BreakType.PAGE)
            else -> {
                val p = doc.createParagraph()
                appendInline(p, element.childNodes(), Style())
            }
        }
    }

    private fun appendText(paragraph: XWPFParagraph, html: String) {
        val body = Jsoup.parseBodyFragment(HtmlSanitizer.sanitizeHtml(html)).body()
        appendInline(paragraph, body.childNodes(), Style())
    }

    private data class Style(val bold: Boolean = false, val italic: Boolean = false, val underline: Boolean = false, val strike: Boolean = false, val color: String? = null, val font: String? = null, val size: Int? = null)

    private fun appendInline(paragraph: XWPFParagraph, nodes: List<Node>, inherited: Style) {
        nodes.forEach { node ->
            when (node) {
                is TextNode -> if (node.wholeText.isNotEmpty()) {
                    val run = paragraph.createRun(); applyStyle(run, inherited); run.setText(node.wholeText)
                }
                is Element -> {
                    if (node.tagName().equals("br", true)) { paragraph.createRun().addBreak(); return@forEach }
                    if (node.tagName().equals("img", true)) { appendImage(paragraph, node.attr("src")); return@forEach }
                    var style = inherited
                    when (node.tagName().lowercase()) {
                        "b", "strong" -> style = style.copy(bold = true)
                        "i", "em" -> style = style.copy(italic = true)
                        "u" -> style = style.copy(underline = true)
                        "s", "strike", "del" -> style = style.copy(strike = true)
                    }
                    parseCss(node.attr("style"), style).let { appendInline(paragraph, node.childNodes(), it) }
                }
            }
        }
    }

    private fun parseCss(raw: String, base: Style): Style {
        var result = base
        raw.split(';').forEach { declaration ->
            val parts = declaration.split(':', limit = 2)
            if (parts.size != 2) return@forEach
            val value = parts[1].trim(); when (parts[0].trim().lowercase()) {
                "font-weight" -> if (value.contains("bold") || (value.toIntOrNull() ?: 0) >= 600) result = result.copy(bold = true)
                "font-style" -> if (value.contains("italic")) result = result.copy(italic = true)
                "text-decoration" -> result = result.copy(underline = result.underline || value.contains("underline"), strike = result.strike || value.contains("line-through"))
                "color" -> if (value.matches(Regex("#?[0-9a-fA-F]{6}"))) result = result.copy(color = value.removePrefix("#").uppercase())
                "font-family" -> result = result.copy(font = value.replace("'", "").replace("\"", "").substringBefore(','))
                "font-size" -> value.replace("pt", "").replace("px", "").trim().toFloatOrNull()?.toInt()?.let { result = result.copy(size = it) }
            }
        }
        return result
    }

    private fun applyStyle(run: XWPFRun, style: Style) {
        run.isBold = style.bold; run.isItalic = style.italic; run.isStrikeThrough = style.strike
        if (style.underline) run.underline = UnderlinePatterns.SINGLE
        style.color?.let { run.color = it }; style.font?.let { run.fontFamily = it }; style.size?.let { run.fontSize = it }
    }

    private fun applyAlignment(paragraph: XWPFParagraph, element: Element) {
        val value = element.attr("align").ifBlank { Regex("text-align\\s*:\\s*(right|center|justify|left)", RegexOption.IGNORE_CASE).find(element.attr("style"))?.groupValues?.get(1) }.orEmpty().lowercase()
        paragraph.alignment = when (value) { "right" -> ParagraphAlignment.RIGHT; "center" -> ParagraphAlignment.CENTER; "justify" -> ParagraphAlignment.BOTH; else -> ParagraphAlignment.LEFT }
    }

    private fun appendImage(paragraph: XWPFParagraph, source: String) {
        if (!source.startsWith("data:image/")) return
        runCatching {
            val bytes = Base64.decode(source.substringAfter("base64,"), Base64.DEFAULT)
            val type = if (source.contains("image/png")) XWPFDocument.PICTURE_TYPE_PNG else XWPFDocument.PICTURE_TYPE_JPEG
            paragraph.createRun().addPicture(ByteArrayInputStream(bytes), type, "image", Units.toEMU(320.0), Units.toEMU(220.0))
        }
    }

    private fun twips(mm: Float): BigInteger = BigInteger.valueOf((mm * 56.692913f).toLong())
}
