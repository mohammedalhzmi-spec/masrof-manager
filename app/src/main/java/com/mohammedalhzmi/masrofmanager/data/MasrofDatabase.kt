package com.mohammedalhzmi.masrofmanager.data

import androidx.room.Database
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Document::class, OrganizationProfile::class, ContactEntity::class, UserEntity::class, AuditLogEntity::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MasrofDatabase : RoomDatabase() {
    abstract fun documentDao(): DocumentDao
    abstract fun settingsDao(): SettingsDao
    abstract fun contactDao(): ContactDao
    abstract fun userDao(): UserDao
    abstract fun auditDao(): AuditDao

    companion object {
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, username TEXT NOT NULL, passwordHash TEXT NOT NULL, fullName TEXT NOT NULL, role TEXT NOT NULL, active INTEGER NOT NULL, createdAt INTEGER NOT NULL)")
                db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_users_username ON users(username)")
                db.execSQL("CREATE TABLE IF NOT EXISTS audit_logs (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, userId INTEGER, username TEXT NOT NULL, action TEXT NOT NULL, details TEXT NOT NULL, timestamp INTEGER NOT NULL)")
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
