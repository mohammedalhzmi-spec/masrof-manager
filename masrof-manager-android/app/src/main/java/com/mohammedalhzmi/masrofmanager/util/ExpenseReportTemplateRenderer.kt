package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import com.example.R
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentType

/** Draws the four expense-book pages supplied by the user (printed page numbers 3–6). */
internal object ExpenseReportTemplateRenderer {
    private const val BLUE = 0xff1118dd.toInt()
    private const val GREEN = 0xff079858.toInt()
    private const val RED = 0xffc83f4d.toInt()
    private const val HEADER_BLUE = 0xffa4cdf1.toInt()
    private const val CYAN = 0xff38bed1.toInt()
    private const val PURPLE = 0xff8946c6.toInt()
    private const val GRAY = 0xffc6c6c6.toInt()
    private const val YELLOW = 0xffffc900.toInt()
    private const val PINK = 0xffff9dcf.toInt()
    private const val PALE_PINK = 0xffffe1e1.toInt()
    private const val VIOLET = 0xffa29cff.toInt()
    private const val INK = 0xff171717.toInt()

    private data class Column(val label: String, val fraction: Float)

    fun draw(canvas: Canvas, document: Document, pageIndex: Int, header: DocumentHeader, context: Context?) {
        val book = ExpenseBookData.decode(document.details)
        when (pageIndex.coerceIn(0, 3)) {
            0 -> drawOperatingExpensesPage(canvas, document, header, context, book)
            1 -> drawMonthlyEntitlementsPage(canvas, document, book)
            2 -> drawOtherExpensesPage(canvas, document, book)
            else -> drawDebtPage(canvas, document, header, context, book)
        }
    }

    private fun drawOperatingExpensesPage(c: Canvas, d: Document, header: DocumentHeader, context: Context?, book: ExpenseBookData) {
        drawInstitutionHeader(c, d, header, context)
        val month = d.purpose.orEmpty().ifBlank { "................" }
        val hijriYear = d.dateHijri.ifBlank { "144هـ" }
        val gregorianYear = d.dateGregorian.ifBlank { "202م" }
        drawBanner(c, 86f, 40f, listOf(
            "الباب الأول: المصروفات التشغيلية الشهرية لفرع صندوق النظافة والتحسين مديرية الحزم",
            "لشهر: $month     ←     $hijriYear     /     $gregorianYear"
        ), 11.5f)

        val x = 32f
        val width = 531f
        var y = 128f
        drawMergedBand(c, x, y, width, 24f, "أولاً: النفقات التشغيلية والسلعية الشهرية لفرع صندوق النظافة", Color.WHITE, INK, 11f, true)
        y += 24f
        val operating = listOf(Column("التفاصيل", .22f), Column("متبقي غير مدفوع", .17f), Column("قيمة المستهلك / مدفوع", .18f), Column("عدد", .13f), Column("المصروفات", .30f))
        drawTableRow(c, x, y, width, operating, operating.map { it.label }, 27f, HEADER_BLUE, RED, true, true); y += 27f
        listOf("الوقود الديزل للأعمال اليومية", "وقود للدمر والتكاتك", "زيت وتشحيم للمعدات").forEach { label ->
            drawTableRow(c, x, y, width, operating, listOf("", "", "", "", label), 28f); y += 28f
        }

        drawMergedBand(c, x, y, width, 23f, "حملات النظافة", Color.WHITE, CYAN, 12f, true); y += 23f
        val campaigns = listOf(Column("ملاحظات", .20f), Column("نفقات الديزل للحملة", .20f), Column("مصروفات نقدية للحملة", .20f), Column("أيام الحملة", .15f), Column("بيان الحملة", .25f))
        drawTableRow(c, x, y, width, campaigns, campaigns.map { it.label }, 27f, HEADER_BLUE, INK, true, true); y += 27f
        drawTableRow(c, x, y, width, campaigns, listOf("", "", "", "", "حملة النظافة الشهرية"), 29f); y += 29f

        drawMergedBand(c, x, y, width, 23f, "نفقات صيانة مشتريات", Color.WHITE, BLUE, 11.5f, true); y += 23f
        val maintenance = listOf(Column("ملاحظات", .20f), Column("متبقي آجل", .20f), Column("قيمتها مدفوع", .20f), Column("عدد", .15f), Column("البيان", .25f))
        drawTableRow(c, x, y, width, maintenance, maintenance.map { it.label }, 27f, HEADER_BLUE, RED, true, true); y += 27f
        repeat(3) { drawTableRow(c, x, y, width, maintenance, emptyList(), 27f); y += 27f }

        drawMergedBand(c, x, y, width, 23f, "أخرى مختلفة (صيانة لوازم - سروسه)", Color.WHITE, BLUE, 11f, true); y += 23f
        drawTableRow(c, x, y, width, maintenance, emptyList(), 27f); y += 27f
        drawTableRow(c, x, y, width, maintenance, listOf("", "", "", "", "سروسه"), 27f); y += 27f
        drawTableRow(c, x, y, width, maintenance, listOf("", "", "", "", "الإجمالي"), 27f, PALE_PINK, BLUE, true); y += 33f

        drawBanner(c, y, 29f, listOf("المصروفات الشهرية المخصصة: بالمستلزمات الأساسية للنظافة"), 12f); y += 29f
        val supplies = listOf(Column("التفاصيل", .34f), Column("متبقي لم يصرف", .20f), Column("المبلغ المصروف", .20f), Column("البيان", .26f))
        drawTableRow(c, x, y, width, supplies, supplies.map { it.label }, 27f, HEADER_BLUE, RED, true, true); y += 27f
        listOf("مكاسن + خراشات", "ملابس + أحذية", "كفوف", "أكياس قمامة").forEach { label ->
            drawTableRow(c, x, y, width, supplies, listOf("", "", "", label), 27f); y += 27f
        }
        drawTableRow(c, x, y, width, supplies, listOf("", "", "", "الإجمالي"), 27f, PALE_PINK, BLUE, true); y += 27f
        drawTableRow(c, x, y, width, supplies, listOf("", "", visibleAmount(book.chapterOneTotal), "إجمالي الباب الأول"), 27f, VIOLET, BLUE, true)
        drawFooter(c, 3)
    }

    private fun drawMonthlyEntitlementsPage(c: Canvas, d: Document, book: ExpenseBookData) {
        val month = d.purpose.orEmpty().ifBlank { "................" }
        val hijriYear = d.dateHijri.ifBlank { "144هـ" }
        val gregorianYear = d.dateGregorian.ifBlank { "202م" }
        drawBanner(c, 28f, 46f, listOf(
            "الباب الثاني: الأجور والمستحقات الشهرية وما في حكمها",
            "فرع صندوق النظافة والتحسين مديرية الحزم — لشهر: $month   ←   $hijriYear   /   $gregorianYear"
        ), 11.5f)

        val x = 40f
        val width = 515f
        var y = 83f
        drawMergedBand(c, x, y, width, 27f, "الرواتب والمستحقات الشهرية والإضافي والمكافأة", Color.WHITE, INK, 12f, true); y += 27f
        val columns = listOf(Column("التفاصيل", .30f), Column("المبلغ المتبقي لم يصرف", .17f), Column("المبلغ المصروف", .17f), Column("العدد", .12f), Column("البيان", .24f))
        drawTableRow(c, x, y, width, columns, columns.map { it.label }, 26f, HEADER_BLUE, RED, true, true); y += 26f
        listOf("مستحقات مدير المديرية", "مستحقات مدير الفرع", "مستحقات الإداريين").forEach { label ->
            drawTableRow(c, x, y, width, columns, listOf("", "", "", "", label), 22f); y += 22f
        }
        drawMergedBand(c, x, y, width, 23f, "مستحقات القوى العاملة الشهرية", GRAY, PURPLE, 12f, true); y += 23f
        listOf("مستحقات العمال", "مستحقات المشرفين", "مستحقات السائقين", "نسبة المحصلين").forEach { label ->
            drawTableRow(c, x, y, width, columns, listOf("", "", "", "", label), 22f); y += 22f
        }
        drawMergedBand(c, x, y, width, 23f, "مستحقات أخرى", GRAY, PURPLE, 12f, true); y += 23f
        listOf("بدل جلسات", "إضافي", "الحوافز والمكافآت", "إكرامية نقدية", "إكرامية عينية", "مياه وكهرباء", "مصروفات عهدة", "رسوم المقلب الشهرية", "خدمات الاستضافة والضيافة", "علاج وتداوي").forEach { label ->
            drawTableRow(c, x, y, width, columns, listOf("", "", "", "", label), 21f); y += 21f
        }
        drawTableRow(c, x, y, width, columns, listOf("", "", visibleAmount(book.chapterTwoTotal), "", "إجمالي الباب الثاني"), 27f, PALE_PINK, BLUE, true)
        drawFooter(c, 4, "فاصل صفحات: -------------")
    }

    private fun drawOtherExpensesPage(c: Canvas, d: Document, book: ExpenseBookData) {
        val x = 40f
        val width = 515f
        drawBanner(c, 28f, 33f, listOf("الباب الثالث: مصروفات أخرى متنوعة وعهدة"), 13f)
        var y = 63f
        val columns = listOf(Column("التفاصيل", .34f), Column("المبلغ", .32f), Column("البيان", .34f))
        drawTableRow(c, x, y, width, columns, columns.map { it.label }, 27f, HEADER_BLUE, RED, true, true); y += 27f
        listOf("القرطاسية والطباعة", "متأخرات مديونية متبقية من الشهر السابق").forEach { label ->
            drawTableRow(c, x, y, width, columns, listOf("", "", label), 29f); y += 29f
        }
        drawMergedBand(c, x, y, width, 34f, "المستلزمات الخدمية", YELLOW, BLUE, 15f, true); y += 34f
        listOf("تنقلات عامة", "بدل سفر", "إيجار مباني", "اتصالات والإنترنت", "استئجار معدات", "خدمات الأمن والضبط", "الفوائد والعمولات المحلية", "خدمات البنوك", "خدمات الحراسة والأمن", "أخرى مختلفة").forEach { label ->
            drawTableRow(c, x, y, width, columns, listOf("", "", label), 29f); y += 29f
        }
        drawMergedBand(c, x, y, width, 34f, "المصاريف الجارية والتحويلية ومديونية", PINK, BLUE, 13f, true); y += 34f
        listOf("ديون محلية وسابقة", "ضرائب المرتبات والدخل والمبيعات").forEach { label ->
            drawTableRow(c, x, y, width, columns, listOf("", "", label), 29f); y += 29f
        }
        drawMergedBand(c, x, y, width, 32f, "المصروفات المخصصة", GRAY, 0xff8b2424.toInt(), 13f, true); y += 32f
        drawTableRow(c, x, y, width, columns, listOf("", visibleAmount(book.chapterThreeTotal), "إجمالي الباب الثالث"), 25f, PALE_PINK, BLUE, true); y += 25f
        drawTableRow(c, x, y, width, columns, listOf("", visibleAmount(book.generalTotal), "الإجمالي العام للأبواب الثلاثة"), 25f, VIOLET, BLUE, true); y += 25f
        drawTableRow(c, x, y, width, columns, listOf("", ExpenseBookData.formatAmount(ExpenseBookData.FIXED_GENERAL_TOTAL), "حد المصروفات الثابت"), 25f, YELLOW, BLUE, true)
        drawSignature(c, 35f, 250f, "مدير عام المديرية رئيس المجلس المحلي", "العميد / زكريا المساوي", 720f)
        drawSignature(c, 310f, 250f, "مدير فرع صندوق النظافة بالمديرية", "رياض أحمد محمد ناصر", 720f)
        drawFooter(c, 5)
    }

    private fun drawDebtPage(c: Canvas, d: Document, header: DocumentHeader, context: Context?, book: ExpenseBookData) {
        context?.let { ctx ->
            BitmapFactory.decodeResource(ctx.resources, R.drawable.expense_book_watermark)?.let { watermark ->
                c.drawBitmap(
                    watermark,
                    null,
                    RectF(75f, 190f, 520f, 689f),
                    Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)
                )
                watermark.recycle()
            }
        }
        val month = d.purpose.orEmpty().ifBlank { "................" }
        val titleX = 69f
        val titleY = 11f
        val titleWidth = 333f
        val titleHeight = 24f
        val border = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = BLUE; style = Paint.Style.STROKE; strokeWidth = 2f }
        c.drawRect(titleX, titleY, titleX + titleWidth, titleY + titleHeight, border)
        val titlePaint = paint(9f, INK, true)
        drawFittedCentered(c, "الباب الرابع: مديونية فرع صندوق النظافة والتحسين مديرية الحزم", titleX + titleWidth / 2f, titleY + 10f, titlePaint, titleWidth - 12f, 6.5f)
        drawFittedCentered(c, "لشهر: $month", titleX + titleWidth / 2f, titleY + 20f, titlePaint, titleWidth - 12f, 7f)

        val x = 21f
        val width = 387f
        val columns = listOf(Column("التفاصيل", .34f), Column("المبلغ", .32f), Column("رواتب متأخرة للقوى العاملة", .34f))
        var y = 50f
        drawTableRow(c, x, y, width, columns, columns.map { it.label }, 20f, 0xff358df0.toInt(), Color.BLACK, true, true); y += 20f
        drawTableRow(c, x, y, width, columns, listOf("", visibleAmount(book.debtFebruary), "شهر فبراير"), 20f); y += 20f
        drawTableRow(c, x, y, width, columns, listOf("", visibleAmount(book.debtMarch), "شهر مارس"), 20f); y += 20f
        drawTableRow(c, x, y, width, columns, listOf("", visibleAmount(book.debtPrevious), "أخرى ماضية"), 20f); y += 20f
        if (book.excessDebt > 0) {
            drawTableRow(c, x, y, width, columns, listOf("", ExpenseBookData.formatAmount(book.excessDebt), "زيادة المصروفات عن الثابت — $month"), 21f, Color.WHITE, INK, true); y += 21f
        }
        drawTableRow(c, x, y, width, columns, listOf("", visibleAmount(book.debtTotal), "الإجمالي"), 21f, PALE_PINK, BLUE, true)
        val footer = paint(8.5f, 0xff2459ba.toInt(), false)
        val footerRed = paint(8.5f, RED, false)
        val prefix = "طبع بواسطة/"
        val suffix = " نظام المالية التابع لفرع صندوق النظافة والتحسين مديرية الحزم"
        val footerRight = 425f
        drawRight(c, prefix, footerRight, 833f, footerRed)
        drawRight(c, suffix, footerRight - footerRed.measureText(prefix) - 3f, 833f, footer)
        drawLeft(c, "صفحة رقم: 6", 16f, 833f, footer)
    }

    private fun drawInstitutionHeader(c: Canvas, d: Document, header: DocumentHeader, context: Context?) {
        val body = paint(8.5f, INK, false)
        val bold = paint(9f, INK, true)
        val rightEdge = c.width - 25f
        drawRight(c, "الجمهورية اليمنية", rightEdge, 19f, bold)
        drawRight(c, header.ministry, rightEdge, 32f, body)
        drawRight(c, "${header.administration} م/إب", rightEdge, 45f, bold)
        drawRight(c, header.branch.ifBlank { "فرع مديرية الحزم" }, rightEdge, 58f, bold)

        val hijri = d.dateHijri.ifBlank { "144" }
        val gregorian = d.dateGregorian.ifBlank { "202" }
        drawRight(c, "الرقم: ....................", 204f, 18f, body)
        drawRight(c, "التاريخ:      /      / $hijri", 204f, 34f, body)
        drawRight(c, "الموافق:      /      / $gregorian", 204f, 49f, body)
        drawRight(c, "المرفقات: ........................", 204f, 64f, body)
        drawLeft(c, "NO: ${d.documentNumber.ifBlank { "...." }}", 204f, 79f, body)

        val logo = header.logos[DocumentType.EXPENSE_STATEMENT] ?: header.logos[DocumentType.EXPENSE_REPORT]
            ?: context?.let { BitmapFactory.decodeResource(it.resources, R.drawable.expense_report_logo) }
        logo?.let { c.drawBitmap(it, null, RectF(245f, 8f, 345f, 65f), Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG)) }
    }

    private fun drawBanner(c: Canvas, y: Float, height: Float, lines: List<String>, textSize: Float) {
        val x = 32f
        val width = c.width - 64f
        val border = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = BLUE; style = Paint.Style.STROKE; strokeWidth = 2f }
        c.drawRect(x, y, x + width, y + height, border)
        val p = paint(textSize, GREEN, true)
        if (lines.size == 1) {
            drawFittedCentered(c, lines[0], x + width / 2f, y + height / 2f + textSize * .36f, p, width - 12f, textSize * .72f)
        } else {
            val gap = height / (lines.size + 1f)
            lines.forEachIndexed { index, text -> drawFittedCentered(c, text, x + width / 2f, y + gap * (index + 1) + textSize * .32f, p, width - 12f, textSize * .68f) }
        }
    }

    private fun drawMergedBand(c: Canvas, x: Float, y: Float, width: Float, height: Float, text: String, fill: Int, textColor: Int, size: Float, bold: Boolean) {
        val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = fill; style = Paint.Style.FILL }
        val border = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.DKGRAY; style = Paint.Style.STROKE; strokeWidth = .65f }
        c.drawRect(x, y, x + width, y + height, fillPaint)
        c.drawRect(x, y, x + width, y + height, border)
        drawFittedCentered(c, text, x + width / 2f, y + height / 2f + size * .34f, paint(size, textColor, bold), width - 12f, size * .68f)
    }

    private fun drawTableRow(
        c: Canvas,
        x: Float,
        y: Float,
        width: Float,
        columns: List<Column>,
        values: List<String>,
        height: Float,
        fill: Int = Color.WHITE,
        textColor: Int = INK,
        bold: Boolean = false,
        header: Boolean = false
    ) {
        val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = fill; style = Paint.Style.FILL }
        val border = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.DKGRAY; style = Paint.Style.STROKE; strokeWidth = .65f }
        val p = paint(if (header) 9f else 9.5f, textColor, bold)
        var currentX = x
        columns.forEachIndexed { index, column ->
            val cellWidth = if (index == columns.lastIndex) x + width - currentX else width * column.fraction
            c.drawRect(currentX, y, currentX + cellWidth, y + height, fillPaint)
            c.drawRect(currentX, y, currentX + cellWidth, y + height, border)
            val text = values.getOrNull(index).orEmpty()
            if (text.isNotBlank()) {
                val maxWidth = cellWidth - 8f
                if (header) {
                    drawWrapped(c, text, currentX + cellWidth / 2f, y + height / 2f, maxWidth, p, true, 2)
                } else if (index == columns.lastIndex) {
                    drawWrapped(c, text, currentX + cellWidth - 5f, y + height / 2f, maxWidth, p, false, 2)
                } else {
                    drawWrapped(c, text, currentX + cellWidth / 2f, y + height / 2f, maxWidth, p, true, 2)
                }
            }
            currentX += cellWidth
        }
    }

    private fun drawWrapped(c: Canvas, text: String, x: Float, centerY: Float, maxWidth: Float, p: Paint, centered: Boolean, maxLines: Int) {
        val words = text.split(Regex("\\s+")).filter(String::isNotBlank)
        val lines = mutableListOf<String>()
        var line = ""
        words.forEach { word ->
            val candidate = if (line.isBlank()) word else "$line $word"
            if (line.isNotBlank() && p.measureText(candidate) > maxWidth) { lines += line; line = word } else line = candidate
        }
        if (line.isNotBlank()) lines += line
        val chosen = if (lines.size <= maxLines) lines else lines.take(maxLines - 1) + lines.drop(maxLines - 1).joinToString(" ")
        val lineHeight = p.textSize * 1.12f
        val firstBaseline = centerY - (chosen.size - 1) * lineHeight / 2f + p.textSize * .34f
        val oldAlign = p.textAlign
        p.textAlign = if (centered) Paint.Align.CENTER else Paint.Align.RIGHT
        chosen.forEachIndexed { index, value ->
            val fitted = fitText(p, value, maxWidth, p.textSize * .72f)
            c.drawText(value, x, firstBaseline + index * lineHeight, fitted)
        }
        p.textAlign = oldAlign
    }

    private fun drawSignature(c: Canvas, x: Float, width: Float, title: String, name: String, topY: Float = 690f) {
        val right = x + width
        val p = paint(10f, INK, false)
        val bold = paint(10f, INK, true)
        drawFittedRight(c, title, right, topY, bold, width, 8f)
        drawFittedRight(c, name, right, topY + 26f, p, width, 8f)
        drawRight(c, "التوقيع / ........................", right, topY + 52f, p)
    }

    private fun drawFooter(c: Canvas, pageNumber: Int, extraRight: String? = null) {
        val p = paint(8.5f, 0xff2459ba.toInt(), false)
        val red = paint(8.5f, RED, false)
        val prefix = "طبع بواسطة/"
        val suffix = " نظام المالية التابع لفرع صندوق النظافة والتحسين مديرية الحزم"
        val right = c.width - 34f
        drawRight(c, prefix, right, 833f, red)
        drawRight(c, suffix, right - red.measureText(prefix) - 3f, 833f, p)
        drawLeft(c, "صفحة رقم: $pageNumber", 34f, 833f, p)
        extraRight?.let { drawRight(c, it, right, 811f, p) }
    }

    private fun visibleAmount(value: Long): String = if (value == 0L) "" else ExpenseBookData.formatAmount(value)

    private fun paint(size: Float, color: Int, bold: Boolean) = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = size
        this.color = color
        typeface = if (bold) Typeface.create(Typeface.DEFAULT, Typeface.BOLD) else Typeface.DEFAULT
    }

    private fun drawRight(c: Canvas, text: String, x: Float, y: Float, p: Paint) {
        val old = p.textAlign
        p.textAlign = Paint.Align.RIGHT
        c.drawText(text, x, y, p)
        p.textAlign = old
    }

    private fun drawLeft(c: Canvas, text: String, x: Float, y: Float, p: Paint) {
        val old = p.textAlign
        p.textAlign = Paint.Align.LEFT
        c.drawText(text, x, y, p)
        p.textAlign = old
    }

    private fun drawFittedRight(c: Canvas, text: String, x: Float, y: Float, p: Paint, maxWidth: Float, minSize: Float) {
        val fitted = fitText(p, text, maxWidth, minSize)
        drawRight(c, text, x, y, fitted)
    }

    private fun drawFittedCentered(c: Canvas, text: String, x: Float, y: Float, p: Paint, maxWidth: Float, minSize: Float) {
        val fitted = fitText(p, text, maxWidth, minSize)
        val old = fitted.textAlign
        fitted.textAlign = Paint.Align.CENTER
        c.drawText(text, x, y, fitted)
        fitted.textAlign = old
    }

    private fun fitText(p: Paint, text: String, maxWidth: Float, minSize: Float): Paint {
        val fitted = Paint(p)
        while (fitted.measureText(text) > maxWidth && fitted.textSize > minSize) fitted.textSize -= .5f
        return fitted
    }
}
