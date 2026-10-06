package com.mohammedalhzmi.masrofmanager.util

import android.graphics.Bitmap
import android.text.Html
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.UUID
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

@RunWith(RobolectricTestRunner::class)
class OoxmlOfficeExchangeTest {
    @Test
    fun docxRoundTripKeepsArabicTextAndBasicFormatting() {
        val original = LocalOfficeDocument(
            id = UUID.randomUUID().toString(),
            title = "مراسلة عربية",
            kind = OfficeDocumentKind.WORD,
            wordHtml = "<p><b>مرحباً بالعالم</b></p><p><u>رقم ١٢٣</u></p>",
            fontFamily = "amiri_regular"
        )
        val bytes = ByteArrayOutputStream().also { OoxmlOfficeExchange.exportDocx(original, it) }.toByteArray()
        writeValidationFixture("arabic-roundtrip.docx", bytes)
        val entries = readZipEntryNames(bytes)
        assertTrue(entries.contains("[Content_Types].xml"))
        assertTrue(entries.contains("word/document.xml"))

        val reopened = OoxmlOfficeExchange.importDocx(ByteArrayInputStream(bytes), "مراسلة عربية.docx").document
        val plainText = Html.fromHtml(reopened.wordHtml, Html.FROM_HTML_MODE_LEGACY).toString()
        assertTrue(plainText.contains("مرحباً بالعالم"))
        assertTrue(plainText.contains("رقم ١٢٣"))
        assertTrue(reopened.wordHtml.contains("font-weight:bold"))
        assertTrue(reopened.wordHtml.contains("text-decoration:underline"))
        assertEquals("amiri_regular", reopened.fontFamily)
        assertEquals(OfficeDocumentKind.WORD, reopened.kind)

        val detected = OoxmlOfficeExchange.importOffice(ByteArrayInputStream(bytes), "مرفق بلا امتداد", "application/octet-stream")
        assertEquals(OfficeDocumentKind.WORD, detected.document.kind)
    }

    @Test
    fun docxImportKeepsEmbeddedImagesAndTableCellsEditable() {
        val base = ByteArrayOutputStream().also {
            OoxmlOfficeExchange.exportDocx(LocalOfficeDocument(UUID.randomUUID().toString(), "مصدر", OfficeDocumentKind.WORD, wordHtml = "<p>قبل الجدول</p>"), it)
        }.toByteArray()
        val png = ByteArrayOutputStream().also { output ->
            Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888).apply { compress(Bitmap.CompressFormat.PNG, 100, output); recycle() }
        }.toByteArray()
        val externalDocx = addWordTableAndImage(base, png)
        val imported = OoxmlOfficeExchange.importOffice(ByteArrayInputStream(externalDocx), "خارجي", "application/octet-stream")
        val html = imported.document.wordHtml
        assertTrue(html.contains("<table"))
        assertTrue(html.contains("colspan=\"2\""))
        assertTrue(html.contains("رقم المعدة"))
        assertTrue(html.contains("اسم المعدة"))
        assertTrue(html.contains("data:image/png;base64,"))
        assertTrue(imported.notices.any { it.contains("الجداول") })
        assertTrue(imported.notices.any { it.contains("صورة") })

        val reexported = ByteArrayOutputStream()
        OoxmlOfficeExchange.exportDocx(imported.document, reexported)
        writeValidationFixture("table-image-roundtrip.docx", reexported.toByteArray())
        val exportedParts = readZipEntries(reexported.toByteArray())
        assertTrue(exportedParts.containsKey("word/media/office-image-1.png"))
        val exportedXml = exportedParts["word/document.xml"]?.toString(Charsets.UTF_8).orEmpty()
        assertTrue(exportedXml.contains("<w:tbl>"))
        assertTrue(exportedXml.contains("<w:drawing>"))
        assertTrue(exportedParts.containsKey("word/_rels/document.xml.rels"))
        val roundTrip = OoxmlOfficeExchange.importDocx(ByteArrayInputStream(reexported.toByteArray()), "عاد.docx").document
        assertTrue(roundTrip.wordHtml.contains("<table"))
        assertTrue(roundTrip.wordHtml.contains("data:image/png;base64,"))
    }

    @Test
    fun xlsxRoundTripKeepsArabicTextNumbersAndFormula() {
        val original = LocalOfficeDocument(
            id = UUID.randomUUID().toString(),
            title = "كشف مصروفات",
            kind = OfficeDocumentKind.EXCEL,
            cells = mapOf(
                "A1" to "البيان",
                "B1" to "١٢٬٣٤٥٫٥",
                "A2" to "مجموع <الباب>",
                "B2" to "=SUM(B1:B1)",
                "C1" to "=HYPERLINK(\"https://example.invalid\",\"open\")"
            )
        )
        val output = ByteArrayOutputStream()
        val unsafeFormulaCount = OoxmlOfficeExchange.exportXlsx(original, output)
        val bytes = output.toByteArray()
        writeValidationFixture("arabic-roundtrip.xlsx", bytes)
        assertEquals(1, unsafeFormulaCount)
        val detected = OoxmlOfficeExchange.importOffice(ByteArrayInputStream(bytes), "مرفق مجهول", "application/octet-stream")
        assertEquals(OfficeDocumentKind.EXCEL, detected.document.kind)
        val entries = readZipEntryNames(bytes)
        assertTrue(entries.contains("xl/workbook.xml"))
        assertTrue(entries.contains("xl/worksheets/sheet1.xml"))

        val reopened = OoxmlOfficeExchange.importXlsx(ByteArrayInputStream(bytes), "كشف مصروفات.xlsx").document
        assertEquals("البيان", reopened.cells["A1"])
        assertEquals("12345.5", reopened.cells["B1"])
        assertEquals("مجموع <الباب>", reopened.cells["A2"])
        assertEquals("=SUM(B1:B1)", reopened.cells["B2"])
        assertEquals("=HYPERLINK(\"https://example.invalid\",\"open\")", reopened.cells["C1"])
        assertEquals(OfficeDocumentKind.EXCEL, reopened.kind)

        val externalFormulaFile = addUnsupportedFormulaToCell(bytes)
        val importedWithFormula = OoxmlOfficeExchange.importXlsx(ByteArrayInputStream(externalFormulaFile), "خارجي.xlsx")
        assertTrue(importedWithFormula.notices.any { it.contains("1 صيغة") })
        assertEquals("=HYPERLINK(\"https://example.invalid\",\"open\")", importedWithFormula.document.cells["C1"])
        val sanitized = ByteArrayOutputStream()
        assertEquals(1, OoxmlOfficeExchange.exportXlsx(importedWithFormula.document, sanitized))
    }

    @Test
    fun importsThirdPartyOfficeFilesWhenValidationFixturesAreProvided() {
        val docxPath = System.getenv("OOXML_EXTERNAL_DOCX_FIXTURE")
        val xlsxPath = System.getenv("OOXML_EXTERNAL_XLSX_FIXTURE")
        if (docxPath.isNullOrBlank() || xlsxPath.isNullOrBlank()) return

        val word = File(docxPath).inputStream().use { OoxmlOfficeExchange.importDocx(it, "مصدر خارجي.docx").document }
        val wordText = Html.fromHtml(word.wordHtml, Html.FROM_HTML_MODE_LEGACY).toString()
        assertTrue(wordText.contains("بيان خارجي"))
        assertTrue(wordText.contains("نص عريض"))

        val workbook = File(xlsxPath).inputStream().use { OoxmlOfficeExchange.importXlsx(it, "كشف خارجي.xlsx").document }
        assertEquals("البيان", workbook.cells["A1"])
        assertEquals("123.5", workbook.cells["B1"])
        assertEquals("=SUM(B1:B1)", workbook.cells["B2"])
    }

    private fun readZipEntryNames(bytes: ByteArray): Set<String> = buildSet {
        ZipInputStream(ByteArrayInputStream(bytes)).use { zip ->
            while (true) add(zip.nextEntry?.name ?: break)
        }
    }

    private fun readZipEntries(bytes: ByteArray): Map<String, ByteArray> = buildMap {
        ZipInputStream(ByteArrayInputStream(bytes)).use { zip ->
            while (true) {
                val entry = zip.nextEntry ?: break
                put(entry.name, zip.readBytes())
            }
        }
    }

    private fun writeValidationFixture(name: String, bytes: ByteArray) {
        val directory = System.getenv("OOXML_EXCHANGE_TEST_OUTPUT_DIR") ?: return
        File(directory).apply { mkdirs() }.resolve(name).writeBytes(bytes)
    }

    private fun addUnsupportedFormulaToCell(bytes: ByteArray): ByteArray {
        val result = ByteArrayOutputStream()
        ZipInputStream(ByteArrayInputStream(bytes)).use { source ->
            ZipOutputStream(result).use { target ->
                while (true) {
                    val entry = source.nextEntry ?: break
                    val content = source.readBytes()
                    val rewritten = if (entry.name == "xl/worksheets/sheet1.xml") {
                        String(content, Charsets.UTF_8).replace(
                            """<c r="C1" t="inlineStr"><is><t xml:space="preserve">=HYPERLINK(&quot;https://example.invalid&quot;,&quot;open&quot;)</t></is></c>""",
                            """<c r="C1"><f>HYPERLINK(&quot;https://example.invalid&quot;,&quot;open&quot;)</f></c>"""
                        ).toByteArray(Charsets.UTF_8)
                    } else content
                    target.putNextEntry(ZipEntry(entry.name))
                    target.write(rewritten)
                    target.closeEntry()
                }
            }
        }
        return result.toByteArray()
    }

    private fun addWordTableAndImage(bytes: ByteArray, png: ByteArray): ByteArray {
        val result = ByteArrayOutputStream()
        ZipInputStream(ByteArrayInputStream(bytes)).use { source ->
            ZipOutputStream(result).use { target ->
                while (true) {
                    val entry = source.nextEntry ?: break
                    var content = source.readBytes()
                    if (entry.name == "[Content_Types].xml") {
                        content = String(content, Charsets.UTF_8).replace("</Types>", "<Default Extension=\"png\" ContentType=\"image/png\"/></Types>").toByteArray(Charsets.UTF_8)
                    } else if (entry.name == "word/document.xml") {
                        var xml = String(content, Charsets.UTF_8)
                        val table = """<w:tbl><w:tblPr/><w:tblGrid><w:gridCol w:w="2400"/><w:gridCol w:w="2400"/></w:tblGrid><w:tr><w:tc><w:tcPr><w:gridSpan w:val="2"/></w:tcPr><w:p><w:r><w:t>رقم المعدة</w:t></w:r></w:p></w:tc></w:tr><w:tr><w:tc><w:p><w:r><w:t>اسم المعدة</w:t></w:r></w:p></w:tc><w:tc><w:p><w:r><w:t>مولد كهربائي</w:t></w:r></w:p></w:tc></w:tr></w:tbl>"""
                        val drawing = """<w:p><w:r><w:drawing><wp:inline><wp:extent cx="190500" cy="190500"/><wp:docPr id="1" name="image1.png"/><a:graphic><a:graphicData uri="http://schemas.openxmlformats.org/drawingml/2006/picture"><pic:pic><pic:nvPicPr><pic:cNvPr id="0" name="image1.png"/><pic:cNvPicPr/></pic:nvPicPr><pic:blipFill><a:blip r:embed="rIdImage1"/><a:stretch><a:fillRect/></a:stretch></pic:blipFill><pic:spPr><a:xfrm><a:off x="0" y="0"/><a:ext cx="190500" cy="190500"/></a:xfrm><a:prstGeom prst="rect"><a:avLst/></a:prstGeom></pic:spPr></pic:pic></a:graphicData></a:graphic></wp:inline></w:drawing></w:r></w:p>"""
                        xml = xml.replace("<w:sectPr", "$table$drawing<w:sectPr")
                        content = xml.toByteArray(Charsets.UTF_8)
                    }
                    target.putNextEntry(ZipEntry(entry.name))
                    target.write(content)
                    target.closeEntry()
                }
                target.putNextEntry(ZipEntry("word/_rels/document.xml.rels"))
                target.write("""<?xml version="1.0" encoding="UTF-8"?><Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"><Relationship Id="rIdImage1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/image" Target="media/image1.png"/></Relationships>""".toByteArray(Charsets.UTF_8))
                target.closeEntry()
                target.putNextEntry(ZipEntry("word/media/image1.png"))
                target.write(png)
                target.closeEntry()
            }
        }
        return result.toByteArray()
    }
}
