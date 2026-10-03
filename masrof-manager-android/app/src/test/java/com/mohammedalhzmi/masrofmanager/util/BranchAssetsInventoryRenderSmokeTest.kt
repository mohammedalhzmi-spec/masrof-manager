package com.mohammedalhzmi.masrofmanager.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class BranchAssetsInventoryRenderSmokeTest {
    @Test
    fun equipmentReportRendersBlueHeaderAndRowsInLandscapeA4() {
        val bitmap = Bitmap.createBitmap(842, 595, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(Color.WHITE)
        BranchAssetsInventoryRenderer.drawEquipment(
            Canvas(bitmap), "وزارة الإدارة والتنمية المحلية والريفية", "صندوق النظافة والتحسين م/أب",
            "فرع مديرية الحزم", null, "رياض أحمد محمد", "2026",
            listOf(BranchAssetData.Equipment("مولد", "GX-270", "جيدة", "ADM-007", "ENG-2", "CH-9", "2024", "أحمد")),
            page = 1, totalPages = 1
        )
        assertEquals(0xff9bc9f5.toInt(), bitmap.getPixel(40, 200))
        assertEquals(Color.BLACK, bitmap.getPixel(100, 111))
        bitmap.recycle()
    }

    @Test
    fun suppliesReportRendersAnEmptyInventoryTableWithoutCrashing() {
        val bitmap = Bitmap.createBitmap(842, 595, Bitmap.Config.ARGB_8888)
        bitmap.eraseColor(Color.WHITE)
        BranchAssetsInventoryRenderer.drawSupplies(
            Canvas(bitmap), "وزارة الإدارة والتنمية المحلية والريفية", "صندوق النظافة والتحسين م/أب",
            "فرع مديرية الحزم", null, "مدير الفرع", "2026", emptyList(), page = 1, totalPages = 1
        )
        assertEquals(0xff9bc9f5.toInt(), bitmap.getPixel(40, 200))
        bitmap.recycle()
    }
}
