package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.net.Uri
import androidx.core.content.FileProvider
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.displayName
import com.mohammedalhzmi.masrofmanager.data.isExpenseStatement
import java.io.File
import java.io.FileOutputStream
import java.io.ByteArrayOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import android.graphics.Color
import android.graphics.BitmapFactory
import com.example.R

object OfficialDocumentExporter {
    fun exportPdf(context: Context, documents: List<Document>): Uri {
        val file = File(context.filesDir, "masrof-official-${System.currentTimeMillis()}.pdf")
        val pdf = PdfDocument()
        var pageNumber = 0
        documents.forEach { document ->
            val design = DesignRenderLoader.design(context, document.type)
            val selectedPageSize = AppPreferences.pageSize(context, document.type)
            val fixedA4 = document.type == DocumentType.VIOLATION_REPORT || document.type.isExpenseStatement()
            val half = !fixedA4 && (selectedPageSize == "HALF_A4" || (document.type == DocumentType.ORDER && selectedPageSize.isBlank()))
            val landscape = !fixedA4 && design?.orientation == "LANDSCAPE"
            val width = if (fixedA4) 595 else if (half) 842 else (design?.pageWidth?.toInt() ?: if (landscape) 842 else 595)
            val height = if (fixedA4) 842 else if (half) 595 else (design?.pageHeight?.toInt() ?: if (landscape) 595 else 842)
            val reportPages = if (document.type.isExpenseStatement()) 4 else 1
            repeat(reportPages) { reportPageIndex ->
                pageNumber += 1
                val page = pdf.startPage(PdfDocument.PageInfo.Builder(width, height, pageNumber).create())
                OfficialDocumentRenderer.render(page.canvas, document, DocumentHeaderFactory.create(context), DesignRenderLoader.elements(context, document.type), context, design, reportPageIndex)
                pdf.finishPage(page)
            }
        }
        FileOutputStream(file).use { pdf.writeTo(it) }
        pdf.close()
        return shareUri(context, file)
    }

    fun exportPng(context: Context, document: Document): Uri {
        val file = File(context.filesDir, "masrof-${document.documentNumber}.png")
        val design = DesignRenderLoader.design(context, document.type)
        val selectedPageSize = AppPreferences.pageSize(context, document.type)
        val fixedA4 = document.type == DocumentType.VIOLATION_REPORT || document.type.isExpenseStatement()
        val half = !fixedA4 && (selectedPageSize == "HALF_A4" || (document.type == DocumentType.ORDER && selectedPageSize.isBlank()))
        val landscape = !fixedA4 && design?.orientation == "LANDSCAPE"
        val baseWidth = if (fixedA4) 595 else if (half) 842 else (design?.pageWidth?.toInt() ?: if (landscape) 842 else 595)
        val baseHeight = if (fixedA4) 842 else if (half) 595 else (design?.pageHeight?.toInt() ?: if (landscape) 595 else 842)
        val bitmap = Bitmap.createBitmap(baseWidth * 2, baseHeight * 2, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.scale(2f, 2f)
        OfficialDocumentRenderer.render(canvas, document, DocumentHeaderFactory.create(context), DesignRenderLoader.elements(context, document.type), context, design)
        FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
        return shareUri(context, file)
    }

    /** Creates a standards-compliant editable Office Open XML document. */
    fun exportDocx(context: Context, document: Document): Uri {
        val file = File(context.filesDir, "masrof-${document.documentNumber}.docx")
        ZipOutputStream(FileOutputStream(file)).use { zip ->
            fun entry(name: String, value: String) {
                zip.putNextEntry(ZipEntry(name)); zip.write(value.toByteArray(Charsets.UTF_8)); zip.closeEntry()
            }
        val fallbackLogo = if (document.type.isExpenseStatement()) R.drawable.expense_report_logo else R.drawable.official_emblem
            val logo = ((AppPreferences.loadLogo(context, document.type) ?: BitmapFactory.decodeResource(context.resources, fallbackLogo))).let { bitmap -> ByteArrayOutputStream().also { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }.toByteArray() }
            fun bytesEntry(name: String, value: ByteArray) { zip.putNextEntry(ZipEntry(name)); zip.write(value); zip.closeEntry() }
            entry("[Content_Types].xml", """<?xml version="1.0" encoding="UTF-8"?><Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types"><Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/><Default Extension="xml" ContentType="application/xml"/><Default Extension="png" ContentType="image/png"/><Override PartName="/word/document.xml" ContentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml"/></Types>""")
            entry("_rels/.rels", """<?xml version="1.0" encoding="UTF-8"?><Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"><Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="word/document.xml"/></Relationships>""")
            entry("word/_rels/document.xml.rels", if (logo != null) """<?xml version="1.0" encoding="UTF-8"?><Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"><Relationship Id="rId2" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/image" Target="media/logo.png"/></Relationships>""" else """<?xml version="1.0" encoding="UTF-8"?><Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships"/>""")
            logo?.let { bytesEntry("word/media/logo.png", it) }
            entry("word/document.xml", docxXml(document, logo != null))
        }
        return shareUri(context, file)
    }

    fun share(context: Context, uri: Uri, title: String) {
        context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
            type = when { uri.toString().endsWith(".png") -> "image/png"; uri.toString().endsWith(".docx") -> "application/vnd.openxmlformats-officedocument.wordprocessingml.document"; else -> "application/pdf" }
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }, title))
    }

    private fun shareUri(context: Context, file: File): Uri = FileProvider.getUriForFile(context, "${context.packageName}.files", file)

    private fun docxXml(d: Document, hasLogo: Boolean): String {
        if (d.type.isExpenseStatement()) return expenseReportDocxXml(d, hasLogo)
        fun esc(s: String) = s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;")
        fun p(label: String, value: String): String {
            if (label == "الحالة: ") return ""
            val renderedValue = if (label == "التفاصيل: " && d.type == DocumentType.VIOLATION_REPORT) {
                listOf(
                    "الوقت: ${d.incidentTime.orEmpty()}", "اليوم: ${d.incidentDay.orEmpty()}",
                    "الموقع: ${d.incidentLocation.orEmpty()}", "نوع المخالفة: ${d.violationType.orEmpty()}",
                    "الفعل والمسؤولية: ${d.responsibleAction.orEmpty()}", "المادة القانونية: ${d.lawArticle.orEmpty()}",
                    "الشاهد الأول: ${d.witnessOne.orEmpty()}", "الشاهد الثاني: ${d.witnessTwo.orEmpty()}",
                    "المنطقة: ${d.regionName.orEmpty()}", "مسؤول المنطقة: ${d.regionOfficerName.orEmpty()}", value
                ).filter(String::isNotBlank).joinToString(" — ")
            } else value
            val systemLabel = if (label == "الرقم: ") "الرقم: ............    NO: $value" else label + renderedValue
            return "<w:p><w:pPr><w:jc w:val=\"right\"/></w:pPr><w:r><w:rPr><w:rFonts w:ascii=\"Amiri\" w:hAnsi=\"Amiri\"/></w:rPr><w:t>${esc(systemLabel)}</w:t></w:r></w:p>"
        }
        val title = d.type.displayName()
        val logoXml = if (hasLogo) "<w:p><w:pPr><w:jc w:val=\"center\"/></w:pPr><w:r><w:drawing><wp:inline xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><wp:extent cx=\"900000\" cy=\"510000\"/><wp:docPr id=\"1\" name=\"Official logo\"/><a:graphic><a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><pic:pic><pic:blipFill><a:blip r:embed=\"rId2\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/><a:stretch><a:fillRect/></a:stretch></pic:blipFill><pic:spPr><a:prstGeom prst=\"rect\"><a:avLst/></a:prstGeom></pic:spPr></pic:pic></a:graphicData></a:graphic></wp:inline></w:drawing></w:r></w:p>" else ""
        val basmalaXml = "<w:p><w:pPr><w:jc w:val=\"center\"/></w:pPr><w:r><w:t>بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيمِ</w:t></w:r></w:p>"
        return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"><w:body>$basmalaXml$logoXml<w:p><w:pPr><w:jc w:val="center"/></w:pPr><w:r><w:rPr><w:b/><w:rFonts w:ascii="Amiri" w:hAnsi="Amiri"/></w:rPr><w:t>الجمهورية اليمنية - وزارة الإدارة والتنمية المحلية والريفية - صندوق النظافة والتحسين م/إب - فرع مديرية الحزم</w:t></w:r></w:p><w:p><w:pPr><w:jc w:val="center"/></w:pPr><w:r><w:rPr><w:b/></w:rPr><w:t>${esc(title)}</w:t></w:r></w:p>${p("الرقم: ", d.documentNumber)}${p("التاريخ: ", d.dateHijri)}${p("الموافق: ", d.dateGregorian)}${p("المرفقات: ", d.attachmentsCount.toString())}${p("الحالة: ", d.status.name)}${p("البند المالي: ", d.financialCategory)}${p("مركز التكلفة: ", d.costCenter)}${p("مصدر التمويل: ", d.fundingSource)}${p("اسم المستفيد: ", d.beneficiaryName.orEmpty())}${p("المبلغ: ", d.amount?.toString().orEmpty())}${p("المبلغ كتابة: ", d.amountWords.orEmpty())}${p("الغرض: ", d.purpose.orEmpty())}${p("التفاصيل: ", d.details.orEmpty())}<w:p><w:pPr><w:jc w:val="center"/></w:pPr><w:r><w:t>مدير الفرع: رياض أحمد محمد — طبع بواسطة نظام مالية فرع صندوق النظافةوالتحسين مديرية الحزم</w:t></w:r></w:p><w:sectPr><w:pgSz w:w="11906" w:h="16838"/><w:pgMar w:top="720" w:right="720" w:bottom="720" w:left="720"/></w:sectPr></w:body></w:document>"""
    }

    private fun expenseReportDocxXml(d: Document, hasLogo: Boolean): String {
        fun esc(s: String) = s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&apos;")
        fun paragraph(text: String, align: String = "right", color: String = "171717", size: Int = 18, bold: Boolean = false): String {
            val weight = if (bold) "<w:b/>" else ""
            return "<w:p><w:pPr><w:jc w:val=\"$align\"/><w:bidi/></w:pPr><w:r><w:rPr>$weight<w:color w:val=\"$color\"/><w:sz w:val=\"$size\"/></w:rPr><w:t xml:space=\"preserve\">${esc(text)}</w:t></w:r></w:p>"
        }
        fun cell(text: String, fill: String = "FFFFFF", color: String = "171717", bold: Boolean = false, span: Int = 1): String {
            val shading = if (fill == "FFFFFF") "" else "<w:shd w:fill=\"$fill\"/>"
            val gridSpan = if (span > 1) "<w:gridSpan w:val=\"$span\"/>" else ""
            val weight = if (bold) "<w:b/>" else ""
            return "<w:tc><w:tcPr>$gridSpan$shading<w:tcMar><w:top w:w=\"45\" w:type=\"dxa\"/><w:bottom w:w=\"45\" w:type=\"dxa\"/></w:tcMar></w:tcPr><w:p><w:pPr><w:jc w:val=\"center\"/><w:bidi/></w:pPr><w:r><w:rPr>$weight<w:color w:val=\"$color\"/><w:sz w:val=\"16\"/></w:rPr><w:t xml:space=\"preserve\">${esc(text)}</w:t></w:r></w:p></w:tc>"
        }
        fun row(values: List<String>, fill: String = "FFFFFF", color: String = "171717", bold: Boolean = false): String = "<w:tr>" + values.joinToString("") { cell(it, fill, color, bold) } + "</w:tr>"
        fun band(text: String, columns: Int, fill: String, color: String = "1B22B2"): String = "<w:tr>${cell(text, fill, color, true, columns)}</w:tr>"
        fun table(columns: Int, content: String): String {
            val width = 9600 / columns
            val grid = (0 until columns).joinToString("") { "<w:gridCol w:w=\"$width\"/>" }
            return "<w:tbl><w:tblPr><w:tblW w:w=\"9600\" w:type=\"dxa\"/><w:tblBorders><w:top w:val=\"single\" w:sz=\"5\"/><w:left w:val=\"single\" w:sz=\"5\"/><w:bottom w:val=\"single\" w:sz=\"5\"/><w:right w:val=\"single\" w:sz=\"5\"/><w:insideH w:val=\"single\" w:sz=\"4\"/><w:insideV w:val=\"single\" w:sz=\"4\"/></w:tblBorders></w:tblPr><w:tblGrid>$grid</w:tblGrid>$content</w:tbl>"
        }
        fun dataTable(headers: List<String>, labels: List<String>, headerFill: String = "A4CDF1"): String {
            val body = StringBuilder(row(headers, headerFill, "C83F4D", true))
            labels.forEach { label -> body.append(row(List(headers.size) { i -> if (i == headers.lastIndex) label else "" })) }
            return table(headers.size, body.toString())
        }
        fun boxedBanner(vararg lines: String): String = lines.joinToString("") { paragraph(it, "center", "079858", 20, true) }
        val book = ExpenseBookData.decode(d.details)
        fun shownAmount(value: Long): String = if (value == 0L) "" else ExpenseBookData.formatAmount(value)
        val logoXml = if (hasLogo) "<w:p><w:pPr><w:jc w:val=\"center\"/></w:pPr><w:r><w:drawing><wp:inline xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><wp:extent cx=\"720000\" cy=\"640000\"/><wp:docPr id=\"2\" name=\"Expense report logo\"/><a:graphic><a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><pic:pic><pic:blipFill><a:blip r:embed=\"rId2\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/><a:stretch><a:fillRect/></a:stretch></pic:blipFill><pic:spPr><a:prstGeom prst=\"rect\"><a:avLst/></a:prstGeom></pic:spPr></pic:pic></a:graphicData></a:graphic></wp:inline></w:drawing></w:r></w:p>" else ""
        val pageBreak = "<w:p><w:r><w:br w:type=\"page\"/></w:r></w:p>"
        val footer = { number: Int -> paragraph("طبع بواسطة/ نظام المالية التابع لفرع صندوق النظافة والتحسين مديرية الحزم     صفحة رقم: $number", "center", "2459BA", 16) }
        val pageThree = buildString {
            append(paragraph("الرقم: ....................      التاريخ:   /   / ${d.dateHijri.ifBlank { "144هـ" }}      الموافق:   /   / ${d.dateGregorian.ifBlank { "202م" }}      المرفقات: ....................", "right", size = 16))
            append(logoXml)
            append(paragraph("الجمهورية اليمنية — ${esc("وزارة الإدارة والتنمية المحلية والريفية")} — صندوق النظافة والتحسين — فرع مديرية الحزم", "center", size = 17, bold = true))
            append(boxedBanner("الباب الأول: المصروفات التشغيلية الشهرية لفرع صندوق النظافة والتحسين مديرية الحزم", "لشهر: ${d.purpose.orEmpty().ifBlank { "................" }} ← ${d.dateHijri.ifBlank { "144هـ" }} / ${d.dateGregorian.ifBlank { "202م" }}"))
            append(paragraph("أولاً: النفقات التشغيلية والسلعية الشهرية لفرع صندوق النظافة", "center", size = 18, bold = true))
            append(dataTable(listOf("التفاصيل", "متبقي غير مدفوع", "قيمة المستهلك / مدفوع", "عدد", "المصروفات"), listOf("الوقود الديزل للأعمال اليومية", "وقود للدمر والتكاتك", "زيت وتشحيم للمعدات")))
            append(paragraph("حملات النظافة", "center", "38BED1", 20, true))
            append(dataTable(listOf("ملاحظات", "نفقات الديزل للحملة", "مصروفات نقدية للحملة", "أيام الحملة", "بيان الحملة"), listOf("حملة النظافة الشهرية")))
            append(paragraph("نفقات صيانة مشتريات", "center", "1118DD", 18, true))
            append(dataTable(listOf("ملاحظات", "متبقي آجل", "قيمتها مدفوع", "عدد", "البيان"), listOf("", "", "")))
            append(paragraph("أخرى مختلفة (صيانة لوازم - سروسه)", "center", "1118DD", 18, true))
            append(dataTable(listOf("ملاحظات", "متبقي آجل", "قيمتها مدفوع", "عدد", "البيان"), listOf("", "سروسه", "الإجمالي")))
            append(boxedBanner("المصروفات الشهرية المخصصة: بالمستلزمات الأساسية للنظافة"))
            append(dataTable(listOf("التفاصيل", "متبقي لم يصرف", "المبلغ المصروف", "البيان"), listOf("مكاسن + خراشات", "ملابس + أحذية", "كفوف", "أكياس قمامة", "الإجمالي")))
            append(table(4, row(listOf("", "", shownAmount(book.chapterOneTotal), "إجمالي الباب الأول"), "A29CFF", "1118DD", true)))
            append(footer(3))
        }
        val wagesColumns = listOf("التفاصيل", "المبلغ المتبقي لم يصرف", "المبلغ المصروف", "العدد", "البيان")
        val pageFourRows = StringBuilder()
            .append(row(wagesColumns, "A4CDF1", "C83F4D", true))
        listOf("مستحقات مدير المديرية", "مستحقات مدير الفرع", "مستحقات الإداريين").forEach { pageFourRows.append(row(listOf("", "", "", "", it))) }
        pageFourRows.append(band("مستحقات القوى العاملة الشهرية", 5, "C6C6C6", "8946C6"))
        listOf("مستحقات العمال", "مستحقات المشرفين", "مستحقات السائقين", "نسبة المحصلين").forEach { pageFourRows.append(row(listOf("", "", "", "", it))) }
        pageFourRows.append(band("مستحقات أخرى", 5, "C6C6C6", "8946C6"))
        listOf("بدل جلسات", "إضافي", "الحوافز والمكافآت", "إكرامية نقدية", "إكرامية عينية", "مياه وكهرباء", "مصروفات عهدة", "رسوم المقلب الشهرية", "خدمات الاستضافة والضيافة", "علاج وتداوي").forEach { pageFourRows.append(row(listOf("", "", "", "", it))) }
        pageFourRows.append(row(listOf("", "", shownAmount(book.chapterTwoTotal), "", "إجمالي الباب الثاني"), "FFE1E1", "1118DD", true))
        val pageFour = boxedBanner("الباب الثاني: الأجور والمستحقات الشهرية وما في حكمها", "فرع صندوق النظافة والتحسين مديرية الحزم — لشهر: ${d.purpose.orEmpty().ifBlank { "................" }} ← ${d.dateHijri.ifBlank { "144هـ" }} / ${d.dateGregorian.ifBlank { "202م" }}") +
            paragraph("الرواتب والمستحقات الشهرية والإضافي والمكافأة", "center", size = 18, bold = true) + table(5, pageFourRows.toString()) + footer(4)

        val threeColumns = listOf("التفاصيل", "المبلغ", "البيان")
        val pageFiveRows = StringBuilder(row(threeColumns, "A4CDF1", "C83F4D", true))
        listOf("القرطاسية والطباعة", "متأخرات مديونية متبقية من الشهر السابق").forEach { pageFiveRows.append(row(listOf("", "", it))) }
        pageFiveRows.append(band("المستلزمات الخدمية", 3, "FFC900", "1118DD"))
        listOf("تنقلات عامة", "بدل سفر", "إيجار مباني", "اتصالات والإنترنت", "استئجار معدات", "خدمات الأمن والضبط", "الفوائد والعمولات المحلية", "خدمات البنوك", "خدمات الحراسة والأمن", "أخرى مختلفة").forEach { pageFiveRows.append(row(listOf("", "", it))) }
        pageFiveRows.append(band("المصاريف الجارية والتحويلية ومديونية", 3, "FF9DCF", "1118DD"))
        listOf("ديون محلية وسابقة", "ضرائب المرتبات والدخل والمبيعات").forEach { pageFiveRows.append(row(listOf("", "", it))) }
        pageFiveRows.append(band("المصروفات المخصصة", 3, "C6C6C6", "8B2424"))
        pageFiveRows.append(row(listOf("", shownAmount(book.chapterThreeTotal), "إجمالي الباب الثالث"), "FFE1E1", "1118DD", true))
        pageFiveRows.append(row(listOf("", shownAmount(book.generalTotal), "الإجمالي العام للأبواب الثلاثة"), "A29CFF", "1118DD", true))
        pageFiveRows.append(row(listOf("", shownAmount(ExpenseBookData.FIXED_GENERAL_TOTAL), "حد المصروفات الثابت"), "FFC900", "1118DD", true))
        val pageFive = boxedBanner("الباب الثالث: مصروفات أخرى متنوعة وعهدة") + table(3, pageFiveRows.toString()) +
            paragraph("مدير عام المديرية رئيس المجلس المحلي                                      مدير فرع صندوق النظافة بالمديرية", "center", size = 18, bold = true) +
            paragraph("العميد / زكريا المساوي                                      رياض أحمد محمد ناصر", "center", size = 17) +
            paragraph("التوقيع / ..................................                                      التوقيع / ..................................", "center", size = 17) + footer(5)
        val debtColumns = listOf("التفاصيل", "المبلغ", "رواتب متأخرة للقوى العاملة")
        val pageSixRows = StringBuilder(row(debtColumns, "358DF0", "000000", true))
            .append(row(listOf("", shownAmount(book.debtFebruary), "شهر فبراير")))
            .append(row(listOf("", shownAmount(book.debtMarch), "شهر مارس")))
            .append(row(listOf("", shownAmount(book.debtPrevious), "أخرى ماضية")))
        if (book.excessDebt > 0) {
            pageSixRows.append(row(listOf("", shownAmount(book.excessDebt), "زيادة المصروفات عن الثابت — شهر ${d.purpose.orEmpty().ifBlank { "التقرير" }}")))
        }
        pageSixRows.append(row(listOf("", shownAmount(book.debtTotal), "الإجمالي"), "FFE1E1", "1118DD", true))
        val pageSix = boxedBanner("الباب الرابع: مديونية فرع صندوق النظافة والتحسين مديرية الحزم", "لشهر: ${d.purpose.orEmpty().ifBlank { "................" }}") +
            table(3, pageSixRows.toString()) + footer(6)
        return """<?xml version="1.0" encoding="UTF-8" standalone="yes"?><w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"><w:body>$pageThree$pageBreak$pageFour$pageBreak$pageFive$pageBreak$pageSix<w:sectPr><w:pgSz w:w="11906" w:h="16838"/><w:pgMar w:top="480" w:right="600" w:bottom="480" w:left="600"/></w:sectPr></w:body></w:document>"""
    }

}
