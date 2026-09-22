package com.mohammedalhzmi.masrofmanager.data

import androidx.room.Database
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Document::class, OrganizationProfile::class, ContactEntity::class, UserEntity::class, AuditLogEntity::class, DocumentDesignEntity::class, DesignElementEntity::class], version = 10, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MasrofDatabase : RoomDatabase() {
    abstract fun documentDao(): DocumentDao
    abstract fun settingsDao(): SettingsDao
    abstract fun contactDao(): ContactDao
    abstract fun userDao(): UserDao
    abstract fun auditDao(): AuditDao
    abstract fun designDao(): DesignDao

    companion object {
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT NOT NULL, passwordHash TEXT NOT NULL, fullName TEXT NOT NULL, role TEXT NOT NULL, active INTEGER NOT NULL, createdAt INTEGER NOT NULL)")
                db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_users_username ON users(username)")
                db.execSQL("CREATE TABLE IF NOT EXISTS audit_logs (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, userId INTEGER, username TEXT NOT NULL, action TEXT NOT NULL, details TEXT NOT NULL, timestamp INTEGER NOT NULL)")
            }
        }
        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS document_designs (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, documentType TEXT NOT NULL, name TEXT NOT NULL, pageWidth REAL NOT NULL, pageHeight REAL NOT NULL, backgroundColor TEXT NOT NULL, backgroundImageUri TEXT, updatedAt INTEGER NOT NULL)")
                db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_document_designs_documentType ON document_designs(documentType)")
                db.execSQL("CREATE TABLE IF NOT EXISTS design_elements (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, designId INTEGER NOT NULL, type TEXT NOT NULL, content TEXT NOT NULL, x REAL NOT NULL, y REAL NOT NULL, width REAL NOT NULL, height REAL NOT NULL, rotation REAL NOT NULL, opacity REAL NOT NULL, zIndex INTEGER NOT NULL, fontFamily TEXT NOT NULL, fontSize REAL NOT NULL, textColor TEXT NOT NULL, bold INTEGER NOT NULL, italic INTEGER NOT NULL, underline INTEGER NOT NULL, fillColor TEXT NOT NULL, strokeColor TEXT NOT NULL, strokeWidth REAL NOT NULL, locked INTEGER NOT NULL, visible INTEGER NOT NULL)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_design_elements_designId ON design_elements(designId)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_design_elements_designId_zIndex ON design_elements(designId, zIndex)")
            }
        }
        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE design_elements ADD COLUMN textAlign TEXT NOT NULL DEFAULT 'START'")
                db.execSQL("ALTER TABLE design_elements ADD COLUMN lineSpacing REAL NOT NULL DEFAULT 1.0")
                db.execSQL("ALTER TABLE design_elements ADD COLUMN cornerRadius REAL NOT NULL DEFAULT 0.0")
            }
        }
        val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE document_designs ADD COLUMN orientation TEXT NOT NULL DEFAULT 'PORTRAIT'")
                db.execSQL("ALTER TABLE document_designs ADD COLUMN marginLeft REAL NOT NULL DEFAULT 25.0")
                db.execSQL("ALTER TABLE document_designs ADD COLUMN marginTop REAL NOT NULL DEFAULT 25.0")
                db.execSQL("ALTER TABLE document_designs ADD COLUMN marginRight REAL NOT NULL DEFAULT 25.0")
                db.execSQL("ALTER TABLE document_designs ADD COLUMN marginBottom REAL NOT NULL DEFAULT 25.0")
            }
        }
        val MIGRATION_7_8 = object : Migration(7, 8) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE documents ADD COLUMN isArchived INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE documents ADD COLUMN archivedAt INTEGER")
                db.execSQL("ALTER TABLE documents ADD COLUMN updatedAt INTEGER NOT NULL DEFAULT 0")
                db.execSQL("UPDATE documents SET updatedAt = createdAt WHERE updatedAt = 0")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_documents_isArchived ON documents(isArchived)")
            }
        }
        val MIGRATION_8_9 = object : Migration(8, 9) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE documents ADD COLUMN tags TEXT NOT NULL DEFAULT ''")
            }
        }
        val MIGRATION_9_10 = object : Migration(9, 10) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE documents ADD COLUMN financialCategory TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE documents ADD COLUMN costCenter TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE documents ADD COLUMN fundingSource TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE documents ADD COLUMN beneficiaryId TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE documents ADD COLUMN submittedBy TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE documents ADD COLUMN reviewedBy TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE documents ADD COLUMN approvedBy TEXT NOT NULL DEFAULT ''")
                db.execSQL("ALTER TABLE documents ADD COLUMN approvedAt INTEGER")
                db.execSQL("ALTER TABLE documents ADD COLUMN paidAt INTEGER")
                db.execSQL("ALTER TABLE documents ADD COLUMN rejectionReason TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}

class Converters {
    @TypeConverter
    fun fromDocumentType(value: DocumentType) = value.name
    
    @TypeConverter
    fun toDocumentType(value: String) = enumValueOf<DocumentType>(value)

    @TypeConverter
    fun fromDocumentStatus(value: DocumentStatus) = value.name
    
    @TypeConverter
    fun toDocumentStatus(value: String) = enumValueOf<DocumentStatus>(value)

    @TypeConverter
    fun fromContactType(value: ContactType) = value.name
    
    @TypeConverter
    fun toContactType(value: String) = enumValueOf<ContactType>(value)
}
