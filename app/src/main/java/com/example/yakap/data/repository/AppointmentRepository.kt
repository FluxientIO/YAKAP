package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.AppointmentDao
import com.example.yakap.data.models.Appointment
import com.example.yakap.data.models.AppointmentSlot
import com.example.yakap.data.models.AppointmentStatus
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface AppointmentRepository {
    fun getAvailableSlots(professionalId: String): Flow<List<AppointmentSlot>>
    fun getAllSlots(professionalId: String): Flow<List<AppointmentSlot>>
    suspend fun createSlot(slot: AppointmentSlot)
    suspend fun deleteSlot(slot: AppointmentSlot)
    suspend fun bookAppointment(patientId: String, professionalId: String, slotId: String): Result<Appointment>
    fun getPatientAppointments(patientId: String): Flow<List<Appointment>>
    fun getProfessionalAppointments(professionalId: String): Flow<List<Appointment>>
}

class LocalAppointmentRepository(private val appointmentDao: AppointmentDao) : AppointmentRepository {
    override fun getAvailableSlots(professionalId: String): Flow<List<AppointmentSlot>> = 
        appointmentDao.getAvailableSlotsForProfessional(professionalId)

    override fun getAllSlots(professionalId: String): Flow<List<AppointmentSlot>> = 
        appointmentDao.getAllSlotsForProfessional(professionalId)

    override suspend fun createSlot(slot: AppointmentSlot) = 
        appointmentDao.insertSlot(slot)

    override suspend fun deleteSlot(slot: AppointmentSlot) = 
        appointmentDao.deleteSlot(slot)

    override suspend fun bookAppointment(patientId: String, professionalId: String, slotId: String): Result<Appointment> {
        val slot = appointmentDao.getSlotById(slotId) ?: return Result.failure(Exception("Slot not found"))
        if (slot.isBooked) return Result.failure(Exception("Slot already booked"))

        val appointment = Appointment(
            id = UUID.randomUUID().toString(),
            patientId = patientId,
            professionalId = professionalId,
            slotId = slotId,
            status = AppointmentStatus.CONFIRMED,
            timestamp = System.currentTimeMillis()
        )

        appointmentDao.insertAppointment(appointment)
        appointmentDao.updateSlot(slot.copy(isBooked = true))
        
        return Result.success(appointment)
    }

    override fun getPatientAppointments(patientId: String): Flow<List<Appointment>> = 
        appointmentDao.getAppointmentsForPatient(patientId)

    override fun getProfessionalAppointments(professionalId: String): Flow<List<Appointment>> = 
        appointmentDao.getAppointmentsForProfessional(professionalId)
}
