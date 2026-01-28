package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.AppointmentDao
import com.example.yakap.data.models.Appointment
import com.example.yakap.data.models.AppointmentSlot
import com.example.yakap.data.models.AppointmentStatus
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class AppointmentRepositoryTest {

    private val appointmentDao = mockk<AppointmentDao>()
    private val repository = LocalAppointmentRepository(appointmentDao)

    @Test
    fun bookAppointment_success_updatesSlotAndInsertsAppointment() = runBlocking {
        val slot = AppointmentSlot("s1", "p1", 100L, 200L, false)
        coEvery { appointmentDao.getSlotById("s1") } returns slot
        coEvery { appointmentDao.insertAppointment(any()) } returns Unit
        coEvery { appointmentDao.updateSlot(any()) } returns Unit

        val result = repository.bookAppointment("u1", "p1", "s1")

        assertTrue(result.isSuccess)
        coVerify { appointmentDao.insertAppointment(match { it.patientId == "u1" && it.slotId == "s1" }) }
        coVerify { appointmentDao.updateSlot(match { it.id == "s1" && it.isBooked }) }
    }

    @Test
    fun bookAppointment_failure_whenAlreadyBooked() = runBlocking {
        val slot = AppointmentSlot("s1", "p1", 100L, 200L, true)
        coEvery { appointmentDao.getSlotById("s1") } returns slot

        val result = repository.bookAppointment("u1", "p1", "s1")

        assertTrue(result.isFailure)
        assertEquals("Slot already booked", result.exceptionOrNull()?.message)
    }
}
