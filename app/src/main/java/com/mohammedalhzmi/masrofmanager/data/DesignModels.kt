package com.mohammedalhzmi.masrofmanager.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "document_designs", indices = [Index(value = ["documentType"], unique = true)])
data class DocumentDesignEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val documentType: String,
    val name: String,
    val pageWidth: Float = 595f,
    val pageHeight: Float = 842f,
    val backgroundColor: String = "#FFFFFF",
    val backgroundImageUri: String? = null,
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "design_elements", indices = [Index(value = ["designId"]), Index(value = ["designId", "zIndex"])])
data class DesignElementEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val designId: Long,
    val type: String,
    val content: String = "",
    val x: Float = 40f,
    val y: Float = 40f,
    val width: Float = 180f,
    val height: Float = 48f,
    val rotation: Float = 0f,
    val opacity: Float = 1f,
    val zIndex: Int = 0,
    val fontFamily: String = "SANS",
    val fontSize: Float = 18f,
    val textColor: String = "#000000",
    val bold: Boolean = false,
    val italic: Boolean = false,
    val underline: Boolean = false,
    val fillColor: String = "#FFFFFF",
    val strokeColor: String = "#123B5D",
    val strokeWidth: Float = 2f,
    val locked: Boolean = false,
    val visible: Boolean = true
)
