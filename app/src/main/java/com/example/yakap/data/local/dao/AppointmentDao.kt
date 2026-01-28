package com.example.yakap.data.local.dao

import androidx.room.*
import com.example.yakap.data.models.Appointment
import com.example.yakap.data.models.AppointmentSlot
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {
    // Slots
    @Query("SELECT * FROM appointment_slots WHERE professionalId = :professionalId AND isBooked = 0")
    fun getAvailableSlotsForProfessional(professionalId: String): Flow<List<AppointmentSlot>>

    @Query("SELECT * FROM appointment_slots WHERE professionalId = :professionalId")
    fun getAllSlotsForProfessional(professionalId: String): Flow<List<AppointmentSlot>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSlot(slot: AppointmentSlot)

    @Update
    suspend fun updateSlot(slot: AppointmentSlot)

    @Delete
    suspend fun deleteSlot(slot: AppointmentSlot)

    // Appointments
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: Appointment)

    @Query("SELECT * FROM appointments WHERE patientId = :patientId ORDER BY timestamp DESC")
    fun getAppointmentsForPatient(patientId: String): Flow<List<Appointment>>

    @Query("SELECT * FROM appointments WHERE professionalId = :professionalId ORDER BY timestamp DESC")
    fun getAppointmentsForProfessional(professionalId: String): Flow<List<Appointment>>

    @Query("SELECT * FROM appointment_slots WHERE id = :slotId")
    suspend fun getSlotById(slotId: String): AppointmentSlot?
}
