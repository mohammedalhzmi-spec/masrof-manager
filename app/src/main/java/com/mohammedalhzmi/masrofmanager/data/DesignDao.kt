package com.mohammedalhzmi.masrofmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DesignDao {
    @Query("SELECT * FROM document_designs WHERE documentType = :type LIMIT 1")
    suspend fun getDesign(type: String): DocumentDesignEntity?
    @Query("SELECT * FROM document_designs WHERE documentType = :type LIMIT 1")
    fun observeDesign(type: String): Flow<DocumentDesignEntity?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertDesign(design: DocumentDesignEntity): Long
    @Query("DELETE FROM document_designs WHERE documentType = :type")
    suspend fun deleteDesign(type: String)
    @Query("SELECT * FROM design_elements WHERE designId = :designId ORDER BY zIndex ASC, id ASC")
    fun observeElements(designId: Long): Flow<List<DesignElementEntity>>
    @Query("SELECT * FROM design_elements WHERE designId = :designId ORDER BY zIndex ASC, id ASC")
    suspend fun getElements(designId: Long): List<DesignElementEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElement(element: DesignElementEntity): Long
    @Update
    suspend fun updateElement(element: DesignElementEntity)
    @Delete
    suspend fun deleteElement(element: DesignElementEntity)
    @Query("UPDATE design_elements SET zIndex = :zIndex WHERE id = :elementId")
    suspend fun setLayer(elementId: Long, zIndex: Int)
}
