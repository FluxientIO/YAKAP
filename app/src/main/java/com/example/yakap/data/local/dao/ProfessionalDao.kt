package com.example.yakap.data.local.dao

import androidx.room.*
import com.example.yakap.data.models.ConsultationNote
import com.example.yakap.data.models.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfessionalDao {
    @Query("SELECT * FROM patients WHERE assignedProfessionalId = :professionalId")
    fun getPatientsForProfessional(professionalId: String): Flow<List<Patient>>

    @Query("SELECT * FROM patients WHERE id = :patientId")
    suspend fun getPatientById(patientId: String): Patient?

    @Query("SELECT * FROM patients WHERE name LIKE '%' || :query || '%'")
    fun searchPatients(query: String): Flow<List<Patient>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatient(patient: Patient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: ConsultationNote)

    @Query("SELECT * FROM consultation_notes WHERE patientId = :patientId ORDER BY timestamp DESC")
    fun getNotesForPatient(patientId: String): Flow<List<ConsultationNote>>
}
