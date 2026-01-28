package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.ProfessionalDao
import com.example.yakap.data.models.ConsultationNote
import com.example.yakap.data.models.Patient
import kotlinx.coroutines.flow.Flow

interface ProfessionalRepository {
    fun getPatients(professionalId: String): Flow<List<Patient>>
    suspend fun getPatientById(patientId: String): Patient?
    fun searchPatients(query: String): Flow<List<Patient>>
    suspend fun savePatient(patient: Patient)
    suspend fun saveNote(note: ConsultationNote)
    fun getNotesForPatient(patientId: String): Flow<List<ConsultationNote>>
}

class LocalProfessionalRepository(private val professionalDao: ProfessionalDao) : ProfessionalRepository {
    override fun getPatients(professionalId: String): Flow<List<Patient>> = 
        professionalDao.getPatientsForProfessional(professionalId)

    override suspend fun getPatientById(patientId: String): Patient? = 
        professionalDao.getPatientById(patientId)

    override fun searchPatients(query: String): Flow<List<Patient>> = 
        professionalDao.searchPatients(query)

    override suspend fun savePatient(patient: Patient) = 
        professionalDao.insertPatient(patient)

    override suspend fun saveNote(note: ConsultationNote) = 
        professionalDao.insertNote(note)

    override fun getNotesForPatient(patientId: String): Flow<List<ConsultationNote>> = 
        professionalDao.getNotesForPatient(patientId)
}
