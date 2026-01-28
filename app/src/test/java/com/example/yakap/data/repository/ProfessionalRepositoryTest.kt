package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.ProfessionalDao
import com.example.yakap.data.models.ConsultationNote
import com.example.yakap.data.models.Patient
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfessionalRepositoryTest {

    private val professionalDao = mockk<ProfessionalDao>()
    private val repository = LocalProfessionalRepository(professionalDao)

    @Test
    fun getPatients_callsDao() = runBlocking {
        val patients = listOf(Patient("1", "John Doe", "john@example.com", "123", 0L, "p1"))
        every { professionalDao.getPatientsForProfessional("p1") } returns flowOf(patients)

        val result = repository.getPatients("p1").first()

        assertEquals(1, result.size)
        assertEquals("John Doe", result[0].name)
    }

    @Test
    fun saveNote_callsDao() = runBlocking {
        val note = ConsultationNote("n1", "1", "p1", "Content", 123L)
        coEvery { professionalDao.insertNote(any()) } returns Unit

        repository.saveNote(note)

        coVerify { professionalDao.insertNote(note) }
    }
}
