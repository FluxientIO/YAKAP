package com.example.yakap.data.local.dao

import androidx.room.*
import com.example.yakap.data.models.UserAccount
import com.example.yakap.ui.models.UserRole
import kotlinx.coroutines.flow.Flow

@Dao
interface AdminDao {
    @Query("SELECT * FROM user_accounts")
    fun getAllUsers(): Flow<List<UserAccount>>

    @Query("SELECT * FROM user_accounts WHERE role = :role")
    fun getUsersByRole(role: UserRole): Flow<List<UserAccount>>

    @Query("SELECT * FROM user_accounts WHERE role = 'PROFESSIONAL' AND isVerified = 0")
    fun getPendingVerifications(): Flow<List<UserAccount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserAccount)

    @Query("UPDATE user_accounts SET isVerified = :isVerified, verificationDate = :date WHERE id = :userId")
    suspend fun updateVerificationStatus(userId: String, isVerified: Boolean, date: Long?)

    @Query("SELECT COUNT(*) FROM user_accounts")
    fun getTotalUserCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM user_accounts WHERE role = 'PROFESSIONAL' AND isVerified = 1")
    fun getActiveProfessionalCount(): Flow<Int>
}
