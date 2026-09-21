package com.mohammedalhzmi.masrofmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {
    @Query("SELECT * FROM documents WHERE isArchived = 0 ORDER BY createdAt DESC")
    fun getAllDocuments(): Flow<List<Document>>

    @Query("SELECT * FROM documents WHERE isArchived = 1 ORDER BY archivedAt DESC, updatedAt DESC")
    fun getArchivedDocuments(): Flow<List<Document>>

    @Query("UPDATE documents SET isArchived = 1, archivedAt = :timestamp, updatedAt = :timestamp WHERE id = :id")
    suspend fun archive(id: Long, timestamp: Long)

    @Query("UPDATE documents SET isArchived = 0, archivedAt = NULL, updatedAt = :timestamp WHERE id = :id")
    suspend fun restore(id: Long, timestamp: Long)

    @Query("UPDATE documents SET isArchived = 1, archivedAt = :timestamp, updatedAt = :timestamp WHERE isArchived = 0 AND createdAt < :cutoff")
    suspend fun archiveOlderThan(cutoff: Long, timestamp: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(document: Document): Long

    @Update
    suspend fun update(document: Document)

    @Delete
    suspend fun delete(document: Document)

    @Query("SELECT MAX(CAST(documentNumber AS INTEGER)) FROM documents")
    fun getLastDocumentNumber(): Flow<Int?>
}
