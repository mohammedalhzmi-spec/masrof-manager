package com.mohammedalhzmi.masrofmanager.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY fullName") fun observeAll(): Flow<List<UserEntity>>
    @Query("SELECT * FROM users WHERE username = :username AND active = 1 LIMIT 1") suspend fun findActive(username: String): UserEntity?
    @Insert(onConflict = OnConflictStrategy.ABORT) suspend fun insert(user: UserEntity): Long
    @Update suspend fun update(user: UserEntity)
    @Delete suspend fun delete(user: UserEntity)
    @Query("SELECT COUNT(*) FROM users") suspend fun count(): Int
}

@Dao
interface AuditDao {
    @Query("SELECT * FROM audit_logs ORDER BY timestamp DESC LIMIT 500") fun observeAll(): Flow<List<AuditLogEntity>>
    @Insert suspend fun insert(log: AuditLogEntity)
}
