package com.example.yakap.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yakap.data.models.ConsultationNote
import com.example.yakap.data.models.MoodEntry
import com.example.yakap.data.models.Patient
import com.example.yakap.data.repository.MoodRepository
import com.example.yakap.data.repository.ProfessionalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class ProfessionalViewModel(
    private val professionalRepository: ProfessionalRepository,
    private val moodRepository: MoodRepository
) : ViewModel() {

    fun getPatients(professionalId: String): StateFlow<List<Patient>> = 
        professionalRepository.getPatients(professionalId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun searchPatients(query: String): StateFlow<List<Patient>> = 
        professionalRepository.searchPatients(query)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    suspend fun getPatient(patientId: String): Patient? = 
        professionalRepository.getPatientById(patientId)

    fun getPatientMoodHistory(patientId: String): StateFlow<List<MoodEntry>> = 
        moodRepository.getMoodHistory()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun saveNote(patientId: String, professionalId: String, content: String) {
        viewModelScope.launch {
            val note = ConsultationNote(
                id = UUID.randomUUID().toString(),
                patientId = patientId,
                professionalId = professionalId,
                content = content,
                timestamp = System.currentTimeMillis()
            )
            professionalRepository.saveNote(note)
        }
    }

    fun getNotesForPatient(patientId: String): StateFlow<List<ConsultationNote>> = 
        professionalRepository.getNotesForPatient(patientId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    class Factory(
        private val repository: ProfessionalRepository,
        private val moodRepository: MoodRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProfessionalViewModel(repository, moodRepository) as T
        }
    }
}
