package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.test.core.app.ApplicationProvider
import com.mohammedalhzmi.masrofmanager.data.Document
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.isExpenseStatement
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class OfficialDocumentTemplateSmokeTest {
    @Test
    fun everyNonExpenseTypeRendersItsOwnPrintablePage() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val header = DocumentHeaderFactory.create(context)
        DocumentType.values().filterNot { it.isExpenseStatement() }.forEach { type ->
            val width = if (type == DocumentType.ORDER) 842 else 595
            val height = if (type == DocumentType.ORDER) 595 else 842
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            bitmap.eraseColor(Color.WHITE)
            val document = Document(
                type = type,
                documentNumber = "0007",
                dateHijri = "1448/01/01",
                dateGregorian = "2026/07/01",
                amount = 1250.0,
                amountWords = "ألف ومائتان وخمسون ريالًا",
                beneficiaryName = "اسم تجريبي",
                purpose = "غرض تجريبي",
                details = "تفاصيل تجريبية للمستند",
                notes = "ملاحظات",
                status = DocumentStatus.SUBMITTED,
                incidentTime = "10:30",
                incidentDay = "الأحد",
                incidentLocation = "مديرية الحزم",
                violationType = "مخالفة تجريبية",
                responsibleAction = "ترك المخلفات",
                lawArticle = "32",
                witnessOne = "الشاهد الأول",
                witnessTwo = "الشاهد الثاني",
                regionName = "المنطقة",
                regionOfficerName = "مسؤول المنطقة"
            )
            OfficialDocumentRenderer.render(Canvas(bitmap), document, header, emptyList(), context)
            assertNotEquals("No frame rendered for $type", Color.WHITE, bitmap.getPixel(18, 42))
            bitmap.recycle()
        }
    }
}
