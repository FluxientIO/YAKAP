package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.AdminDao
import com.example.yakap.data.local.dao.AppointmentDao
import com.example.yakap.data.local.dao.MoodDao
import com.example.yakap.data.models.UserAccount
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface AdminRepository {
    fun getAllUsers(): Flow<List<UserAccount>>
    fun getPendingVerifications(): Flow<List<UserAccount>>
    suspend fun verifyProfessional(userId: String, approve: Boolean)
    fun getTotalUserCount(): Flow<Int>
    fun getActiveProfessionalCount(): Flow<Int>
    fun getTotalMoodEntries(): Flow<Int>
    fun getTotalAppointments(): Flow<Int>
    suspend fun insertUser(user: UserAccount)
}

class LocalAdminRepository(
    private val adminDao: AdminDao,
    private val moodDao: MoodDao,
    private val appointmentDao: AppointmentDao
) : AdminRepository {
    override fun getAllUsers(): Flow<List<UserAccount>> = adminDao.getAllUsers()

    override fun getPendingVerifications(): Flow<List<UserAccount>> = adminDao.getPendingVerifications()

    override suspend fun verifyProfessional(userId: String, approve: Boolean) {
        val date = if (approve) System.currentTimeMillis() else null
        adminDao.updateVerificationStatus(userId, approve, date)
    }

    override fun getTotalUserCount(): Flow<Int> = adminDao.getTotalUserCount()

    override fun getActiveProfessionalCount(): Flow<Int> = adminDao.getActiveProfessionalCount()

    override fun getTotalMoodEntries(): Flow<Int> = 
        moodDao.getAllMoods().map { it.size }

    override fun getTotalAppointments(): Flow<Int> = flowOf(0) // Mock for now

    override suspend fun insertUser(user: UserAccount) = adminDao.insertUser(user)
}