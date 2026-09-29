package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.test.core.app.ApplicationProvider
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class ExpenseBookRenderSmokeTest {
    @Test
    fun rendersAllFourPagesAndDrawsTheDebtPageTable() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val document = Document(
            type = DocumentType.EXPENSE_STATEMENT,
            documentNumber = "0001",
            dateHijri = "1448",
            dateGregorian = "2026",
            amount = 1_000_000.0,
            amountWords = "مليون",
            beneficiaryName = null,
            purpose = "مارس 2026",
            details = ExpenseBookData(
                chapterOneTotal = 400_000,
                chapterTwoTotal = 352_000,
                chapterThreeTotal = 248_000,
                debtFebruary = 230_000,
                debtMarch = 83_000,
                debtPrevious = 617_000
            ).encode(),
            notes = null,
            status = DocumentStatus.SUBMITTED
        )
        val header = DocumentHeaderFactory.create(context)
        var debtPage: Bitmap? = null
        for (pageIndex in 0..3) {
            val bitmap = Bitmap.createBitmap(595, 842, Bitmap.Config.ARGB_8888)
            bitmap.eraseColor(Color.WHITE)
            OfficialDocumentRenderer.render(Canvas(bitmap), document, header, emptyList(), context, null, pageIndex)
            if (pageIndex == 3) {
                assertNotEquals(Color.WHITE, bitmap.getPixel(297, 440))
                debtPage = bitmap
            } else bitmap.recycle()
        }
        assertNotEquals(Color.WHITE, checkNotNull(debtPage).getPixel(30, 55))
        debtPage?.recycle()
    }
}
