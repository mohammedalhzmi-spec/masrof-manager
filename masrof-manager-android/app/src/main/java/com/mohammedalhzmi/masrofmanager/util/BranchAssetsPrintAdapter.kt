package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.CancellationSignal
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.text.TextPaint
import android.text.TextUtils
import com.example.R
import java.io.FileOutputStream
import kotlin.math.ceil

/** Separate report selector for the annual equipment and supply inventories. */
enum class BranchAssetReportKind(val arabicTitle: String) {
    EQUIPMENT("الجرد السنوي لمعدات الفرع"),
    SUPPLIES("الجرد السنوي لمستلزمات الفرع")
}

/** Print-only adapter; source records remain in Room and the existing Firestore documents collection. */
class BranchAssetsPrintAdapter(
    context: Context,
    private val reportKind: BranchAssetReportKind,
    private val reportYear: String,
    private val equipmentRows: List<BranchAssetData.Equipment>,
    private val supplyRows: List<BranchAssetData.Supply>
) : PrintDocumentAdapter() {
    private val appContext = context.applicationContext
    private val header = DocumentHeaderFactory.create(appContext)
    private val logo = runCatching {
        BitmapFactory.decodeResource(appContext.resources, R.drawable.official_emblem)
    }.getOrNull()
    private var pageCount = 1

    override fun onLayout(
        oldAttributes: PrintAttributes?,
        newAttributes: PrintAttributes,
        cancellationSignal: CancellationSignal,
        callback: LayoutResultCallback,
        extras: Bundle?
    ) {
        if (cancellationSignal.isCanceled) {
            callback.onLayoutCancelled()
            return
        }
        val rows = if (reportKind == BranchAssetReportKind.EQUIPMENT) equipmentRows.size else supplyRows.size
        pageCount = ceil(rows.coerceAtLeast(1) / ROWS_PER_PAGE.toDouble()).toInt().coerceAtLeast(1)
        callback.onLayoutFinished(
            PrintDocumentInfo.Builder("جرد-الفرع-${reportKind.name.lowercase()}-$reportYear.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(pageCount)
                .build(),
            oldAttributes == null || oldAttributes != newAttributes
        )
    }

    override fun onWrite(
        pages: Array<PageRange>,
        destination: android.os.ParcelFileDescriptor,
        cancellationSignal: CancellationSignal,
        callback: WriteResultCallback
    ) {
        val pdf = PdfDocument()
        try {
            for (pageIndex in 0 until pageCount) {
                if (cancellationSignal.isCanceled) {
                    callback.onWriteCancelled()
                    return
                }
                if (pages.isNotEmpty() && pages.none { pageIndex in it.start..it.end }) continue
                val info = PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, pageIndex + 1).create()
                val page = pdf.startPage(info)
                val start = pageIndex * ROWS_PER_PAGE
                if (reportKind == BranchAssetReportKind.EQUIPMENT) {
                    BranchAssetsInventoryRenderer.drawEquipment(
                        page.canvas, header.ministry, header.administration, header.branch, logo,
                        AppPreferences.manager(appContext), reportYear,
                        equipmentRows.drop(start).take(ROWS_PER_PAGE), pageIndex + 1, pageCount
                    )
                } else {
                    BranchAssetsInventoryRenderer.drawSupplies(
                        page.canvas, header.ministry, header.administration, header.branch, logo,
                        AppPreferences.manager(appContext), reportYear,
                        supplyRows.drop(start).take(ROWS_PER_PAGE), pageIndex + 1, pageCount
                    )
                }
                pdf.finishPage(page)
            }
            FileOutputStream(destination.fileDescriptor).use(pdf::writeTo)
            callback.onWriteFinished(if (pages.isEmpty()) arrayOf(PageRange.ALL_PAGES) else pages)
        } catch (error: Exception) {
            callback.onWriteFailed(error.message)
        } finally {
            pdf.close()
        }
    }

    private companion object {
        const val PAGE_WIDTH = 842
        const val PAGE_HEIGHT = 595
        const val ROWS_PER_PAGE = 10
    }
}

/** A4 landscape renderer matching the supplied official header, outlined title and blue table heading. */
internal object BranchAssetsInventoryRenderer {
    private const val WIDTH = 842f
    private const val HEIGHT = 595f
    private const val LEFT = 30f
    private const val TABLE_WIDTH = 782f
    private const val TABLE_TOP = 186f
    private const val HEADER_HEIGHT = 48f
    private const val ROW_HEIGHT = 27f
    private const val ROWS_PER_PAGE = 10
    private val grid = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 0.8f
    }
    private val fill = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = 0xff9bc9f5.toInt() }
    private val body = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 11.5f
        typeface = Typeface.create("sans-serif", Typeface.NORMAL)
        textAlign = Paint.Align.CENTER
    }
    private val bold = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 12.5f
        typeface = Typeface.create("sans-serif", Typeface.BOLD)
        textAlign = Paint.Align.CENTER
    }

    fun drawEquipment(
        canvas: Canvas,
        ministry: String,
        administration: String,
        branch: String,
        logo: android.graphics.Bitmap?,
        manager: String,
        year: String,
        rows: List<BranchAssetData.Equipment>,
        page: Int,
        totalPages: Int
    ) {
        val columns = listOf(
            Column("سنة الحصول\nعليها", 82f),
            Column("حالة المعدة", 90f),
            Column("رقم القعادة", 120f),
            Column("رقم\nالمكينة", 76f),
            Column("الرقم الإداري", 110f),
            Column("موديل المعدة", 120f),
            Column("اسم المعدة", 184f)
        )
        drawBase(canvas, ministry, administration, branch, logo,
            "الجرد السنوي لفرع صندوق النظافة والتحسين مديرية الحزم للعام: $year م", manager, page, totalPages)
        drawTable(canvas, columns, rows.map {
            listOf(it.yearObtained, it.condition, it.chassisNumber, it.machineNumber,
                it.administrativeNumber, it.model, it.name)
        })
    }

    fun drawSupplies(
        canvas: Canvas,
        ministry: String,
        administration: String,
        branch: String,
        logo: android.graphics.Bitmap?,
        manager: String,
        year: String,
        rows: List<BranchAssetData.Supply>,
        page: Int,
        totalPages: Int
    ) {
        val columns = listOf(
            Column("القيمة", 120f),
            Column("النوع", 170f),
            Column("الاسم", 240f),
            Column("التاريخ", 140f),
            Column("اليوم", 112f)
        )
        drawBase(canvas, ministry, administration, branch, logo,
            "الجرد السنوي لمستلزمات فرع صندوق النظافة والتحسين مديرية الحزم للعام: $year م", manager, page, totalPages)
        drawTable(canvas, columns, rows.map { listOf(it.valueYER, it.kind, it.name, it.date, it.day) })
    }

    private fun drawBase(
        canvas: Canvas,
        ministry: String,
        administration: String,
        branch: String,
        logo: android.graphics.Bitmap?,
        title: String,
        manager: String,
        page: Int,
        totalPages: Int
    ) {
        canvas.save()
        canvas.scale(canvas.width / WIDTH, canvas.height / HEIGHT)
        canvas.drawColor(Color.WHITE)
        val left = Paint(bold).apply { textAlign = Paint.Align.LEFT; textSize = 12f }
        val right = Paint(bold).apply { textAlign = Paint.Align.RIGHT; textSize = 12f }
        val center = Paint(bold).apply { textAlign = Paint.Align.CENTER; textSize = 11f }
        val administrationLabel = if (administration == "صندوق النظافة والتحسين") "صندوق النظافة والتحسين م/أب" else administration
        val branchLabel = if (branch == "فرع المديرية") "فرع مديرية الحزم" else branch
        canvas.drawText("الرقم : ........................", 38f, 37f, left)
        canvas.drawText("التاريخ :       /       / 144 هـ", 38f, 56f, left)
        canvas.drawText("الموافق :       /       / 20   م", 38f, 75f, left)
        canvas.drawText("المرفقات : (          )", 38f, 94f, left)
        canvas.drawText("الجمهورية اليمنية", WIDTH - 36f, 34f, right)
        canvas.drawText(ministry, WIDTH - 36f, 54f, right)
        canvas.drawText(administrationLabel, WIDTH - 36f, 74f, right)
        canvas.drawText(branchLabel, WIDTH - 36f, 94f, right)
        logo?.let { bitmap ->
            val maxW = 190f
            val maxH = 58f
            val factor = minOf(maxW / bitmap.width.coerceAtLeast(1), maxH / bitmap.height.coerceAtLeast(1))
            val w = bitmap.width * factor
            val h = bitmap.height * factor
            canvas.drawBitmap(bitmap, null, RectF(WIDTH / 2f - w / 2f, 31f, WIDTH / 2f + w / 2f, 31f + h),
                Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG))
        }
        canvas.drawLine(24f, 111f, WIDTH - 24f, 111f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.BLACK; strokeWidth = 3f
        })
        val titleRect = RectF(60f, 124f, WIDTH - 60f, 170f)
        canvas.drawRect(titleRect, grid)
        val fitted = TextUtils.ellipsize(title, TextPaint(center), titleRect.width() - 18f, TextUtils.TruncateAt.END).toString()
        canvas.drawText(fitted, WIDTH / 2f, 153f, center)
        if (totalPages > 1) {
            val pagePaint = Paint(body).apply { textSize = 9f }
            canvas.drawText("الصفحة $page من $totalPages", WIDTH - 36f, 180f, pagePaint)
        }
        drawFooter(canvas, manager)
        canvas.restore()
    }

    private fun drawTable(canvas: Canvas, columns: List<Column>, rows: List<List<String>>) {
        var x = LEFT
        columns.forEach { column ->
            canvas.drawRect(x, TABLE_TOP, x + column.width, TABLE_TOP + HEADER_HEIGHT, fill)
            canvas.drawRect(x, TABLE_TOP, x + column.width, TABLE_TOP + HEADER_HEIGHT, grid)
            drawLines(canvas, column.label.split('\n'), x + column.width / 2f, TABLE_TOP, HEADER_HEIGHT, bold)
            x += column.width
        }
        val visibleRows = rows.ifEmpty { listOf(columns.map { "" }) }.take(ROWS_PER_PAGE)
        visibleRows.forEachIndexed { rowIndex, values ->
            val top = TABLE_TOP + HEADER_HEIGHT + rowIndex * ROW_HEIGHT
            x = LEFT
            columns.forEachIndexed { columnIndex, column ->
                canvas.drawRect(x, top, x + column.width, top + ROW_HEIGHT, grid)
                val raw = values.getOrNull(columnIndex).orEmpty()
                val fitted = TextUtils.ellipsize(raw, TextPaint(body), column.width - 8f, TextUtils.TruncateAt.END).toString()
                canvas.drawText(fitted, x + column.width / 2f, top + 18f, body)
                x += column.width
            }
        }
        if (visibleRows.size < 3) {
            for (rowIndex in visibleRows.size until 3) {
                val top = TABLE_TOP + HEADER_HEIGHT + rowIndex * ROW_HEIGHT
                x = LEFT
                columns.forEach { column ->
                    canvas.drawRect(x, top, x + column.width, top + ROW_HEIGHT, grid)
                    x += column.width
                }
            }
        }
    }

    private fun drawLines(canvas: Canvas, lines: List<String>, centerX: Float, top: Float, height: Float, paint: Paint) {
        val count = lines.size.coerceAtLeast(1)
        val lineHeight = paint.textSize + 3f
        val firstBaseline = top + (height - count * lineHeight) / 2f + paint.textSize
        lines.forEachIndexed { index, line -> canvas.drawText(line, centerX, firstBaseline + index * lineHeight, paint) }
    }

    private fun drawFooter(canvas: Canvas, manager: String) {
        val footer = Paint(bold).apply { textAlign = Paint.Align.CENTER; textSize = 12f }
        canvas.drawText("مدير فرع صندوق النظافة والتحسين", 182f, 536f, footer)
        canvas.drawText(manager.ifBlank { "الاسم: __________________" }, 182f, 556f, body)
        canvas.drawText("التوقيع: /-", 182f, 576f, body)
    }

    private data class Column(val label: String, val width: Float)
}
