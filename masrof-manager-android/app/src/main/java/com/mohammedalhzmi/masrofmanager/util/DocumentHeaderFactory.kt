package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import com.example.R
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.isExpenseStatement

object DocumentHeaderFactory {
    fun create(context: Context): DocumentHeader {
        val types = DocumentType.values().filter { it != DocumentType.BOOK }
        return DocumentHeader(
            ministry = AppPreferences.ministry(context),
            administration = AppPreferences.administration(context),
            branch = AppPreferences.branch(context),
            logos = types.associateWith { AppPreferences.loadLogo(context, it) },
            pageSizes = types.associateWith { type ->
                if (type.isExpenseStatement() || type == DocumentType.VIOLATION_REPORT) "A4"
                else AppPreferences.pageSize(context, type)
            },
            backgroundColors = types.associateWith { type ->
                if (type.isExpenseStatement() || type == DocumentType.VIOLATION_REPORT) Color.WHITE
                else runCatching { Color.parseColor(AppPreferences.backgroundColor(context, type)) }.getOrDefault(Color.WHITE)
            },
            backgroundImages = types.associateWith { type -> background(context, type) },
            backgroundOpacity = types.associateWith { AppPreferences.backgroundOpacity(context, it) },
            backgroundScale = types.associateWith { AppPreferences.backgroundScale(context, it) },
            backgroundOffset = types.associateWith {
                AppPreferences.backgroundOffsetX(context, it) to AppPreferences.backgroundOffsetY(context, it)
            },
            textColors = types.associateWith { type ->
                if (type.isExpenseStatement() || type == DocumentType.VIOLATION_REPORT) Color.BLACK
                else runCatching { Color.parseColor(AppPreferences.textColor(context, type)) }.getOrDefault(Color.BLACK)
            },
            fontFamilies = types.associateWith { AppPreferences.fontFamily(context, it) },
            textBold = types.associateWith { AppPreferences.textBold(context, it) },
            textItalic = types.associateWith { AppPreferences.textItalic(context, it) },
            textUnderline = types.associateWith { AppPreferences.textUnderline(context, it) }
        )
    }

    private fun background(context: Context, type: DocumentType): Bitmap? {
        val saved = AppPreferences.backgroundImageUri(context, type)?.let { uri ->
            runCatching { context.contentResolver.openInputStream(Uri.parse(uri)).use(BitmapFactory::decodeStream) }.getOrNull()
        }
        if (saved != null) return saved
        val fallback = if (type.isExpenseStatement()) R.drawable.expense_report_logo else R.drawable.official_emblem
        return runCatching { BitmapFactory.decodeResource(context.resources, fallback) }.getOrNull()
    }
}
