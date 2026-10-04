package com.mohammedalhzmi.masrofmanager.util

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
}
