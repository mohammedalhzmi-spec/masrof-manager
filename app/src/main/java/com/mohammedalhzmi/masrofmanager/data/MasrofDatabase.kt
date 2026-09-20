package com.mohammedalhzmi.masrofmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Document::class, OrganizationProfile::class, ContactEntity::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MasrofDatabase : RoomDatabase() {
    abstract fun documentDao(): DocumentDao
    abstract fun settingsDao(): SettingsDao
    abstract fun contactDao(): ContactDao
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
