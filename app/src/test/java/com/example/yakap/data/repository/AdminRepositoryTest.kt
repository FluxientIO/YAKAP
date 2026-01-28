package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.AdminDao
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AdminRepositoryTest {

    private val adminDao = mockk<AdminDao>()
    private val moodDao = mockk<com.example.yakap.data.local.dao.MoodDao>()
    private val appointmentDao = mockk<com.example.yakap.data.local.dao.AppointmentDao>()
    private val repository = LocalAdminRepository(adminDao, moodDao, appointmentDao)

    @Test
    fun verifyProfessional_approve_callsDaoWithTrue() = runBlocking {
        coEvery { adminDao.updateVerificationStatus(any(), any(), any()) } returns Unit

        repository.verifyProfessional("u1", true)

        coVerify { adminDao.updateVerificationStatus("u1", true, any()) }
    }

    @Test
    fun verifyProfessional_reject_callsDaoWithFalse() = runBlocking {
        coEvery { adminDao.updateVerificationStatus(any(), any(), any()) } returns Unit

        repository.verifyProfessional("u1", false)

        coVerify { adminDao.updateVerificationStatus("u1", false, null) }
    }
}
