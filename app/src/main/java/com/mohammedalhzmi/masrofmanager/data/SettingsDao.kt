package com.mohammedalhzmi.masrofmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Query("SELECT * FROM organization_profile WHERE id = 1")
    fun getOrganizationProfile(): Flow<OrganizationProfile?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrganizationProfile(profile: OrganizationProfile)
}
