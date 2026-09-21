package com.mohammedalhzmi.masrofmanager.util

import android.content.Context
import android.net.Uri
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

object AppBackupManager {
    fun createBackup(context: Context): File {
        val output = File(context.filesDir, "masrof-backup-${System.currentTimeMillis()}.zip")
        ZipOutputStream(FileOutputStream(output)).use { zip ->
            addFile(zip, context.getDatabasePath("masrof-db"), "databases/masrof-db")
            addFile(zip, File(context.getDatabasePath("masrof-db").parentFile, "masrof-db-wal"), "databases/masrof-db-wal")
            addFile(zip, File(context.getDatabasePath("masrof-db").parentFile, "masrof-db-shm"), "databases/masrof-db-shm")
            addFile(zip, File(context.applicationInfo.dataDir, "shared_prefs/masrof_preferences.xml"), "shared_prefs/masrof_preferences.xml")
            zip.putNextEntry(ZipEntry("backup-info.txt")); zip.write("Masrof Manager portable backup\n".toByteArray()); zip.closeEntry()
        }
        return output
    }

    fun exportToUri(context: Context, uri: Uri) { FileInputStream(createBackup(context)).use { input -> context.contentResolver.openOutputStream(uri).use { output -> input.copyTo(output!!) } } }

    fun shareBackup(context: Context) {
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.files", createBackup(context))
        context.startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply { type = "application/zip"; putExtra(Intent.EXTRA_STREAM, uri); addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) }, "مشاركة النسخة الاحتياطية ZIP"))
    }

    fun restoreFromUri(context: Context, uri: Uri) {
        val temp = File(context.cacheDir, "restore.zip")
        context.contentResolver.openInputStream(uri).use { input -> FileOutputStream(temp).use { output -> input!!.copyTo(output) } }
        ZipInputStream(FileInputStream(temp)).use { zip ->
            var entry = zip.nextEntry
            while (entry != null) {
                val destination = when {
                    entry.name.startsWith("databases/") -> File(context.getDatabasePath("masrof-db").parentFile, entry.name.removePrefix("databases/"))
                    entry.name.startsWith("shared_prefs/") -> File(context.applicationInfo.dataDir, entry.name)
                    else -> null
                }
                if (destination != null) { destination.parentFile?.mkdirs(); FileOutputStream(destination).use { zip.copyTo(it) } }
                zip.closeEntry(); entry = zip.nextEntry
            }
        }
        temp.delete()
    }

    private fun addFile(zip: ZipOutputStream, file: File, name: String) {
        if (!file.exists()) return
        zip.putNextEntry(ZipEntry(name)); FileInputStream(file).use { it.copyTo(zip) }; zip.closeEntry()
    }
}
