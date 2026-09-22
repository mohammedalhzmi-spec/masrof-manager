package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import androidx.room.Room
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity
import com.mohammedalhzmi.masrofmanager.data.DocumentType
import com.mohammedalhzmi.masrofmanager.data.MasrofDatabase
import kotlinx.coroutines.runBlocking

object DesignRenderLoader {
    fun design(context: Context, type: DocumentType): DocumentDesignEntity? = runBlocking {
        val db = Room.databaseBuilder(context.applicationContext, MasrofDatabase::class.java, "masrof-db")
            .addMigrations(MasrofDatabase.MIGRATION_3_4, MasrofDatabase.MIGRATION_4_5, MasrofDatabase.MIGRATION_5_6, MasrofDatabase.MIGRATION_6_7, MasrofDatabase.MIGRATION_7_8, MasrofDatabase.MIGRATION_8_9, MasrofDatabase.MIGRATION_9_10).build()
        try { db.designDao().getDesign(type.name) } finally { db.close() }
    }
    fun elements(context: Context, type: DocumentType): List<DesignElementEntity> = runBlocking {
        val db = Room.databaseBuilder(context.applicationContext, MasrofDatabase::class.java, "masrof-db")
            .addMigrations(MasrofDatabase.MIGRATION_3_4, MasrofDatabase.MIGRATION_4_5, MasrofDatabase.MIGRATION_5_6, MasrofDatabase.MIGRATION_6_7, MasrofDatabase.MIGRATION_7_8, MasrofDatabase.MIGRATION_8_9, MasrofDatabase.MIGRATION_9_10)
            .build()
        try {
            val design = db.designDao().getDesign(type.name) ?: return@runBlocking emptyList()
            db.designDao().getElements(design.id)
        } finally { db.close() }
    }
}
