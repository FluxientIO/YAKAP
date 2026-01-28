package com.example.yakap.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yakap.data.models.Appointment
import com.example.yakap.data.models.AppointmentSlot
import com.example.yakap.data.repository.AppointmentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class AppointmentViewModel(private val repository: AppointmentRepository) : ViewModel() {

    fun getAvailableSlots(professionalId: String): StateFlow<List<AppointmentSlot>> =
        repository.getAvailableSlots(professionalId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun getAllSlots(professionalId: String): StateFlow<List<AppointmentSlot>> =
        repository.getAllSlots(professionalId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun createSlot(professionalId: String, startTime: Long, endTime: Long) {
        viewModelScope.launch {
            val slot = AppointmentSlot(
                id = UUID.randomUUID().toString(),
                professionalId = professionalId,
                startTime = startTime,
                endTime = endTime
            )
            repository.createSlot(slot)
        }
    }

    fun deleteSlot(slot: AppointmentSlot) {
        viewModelScope.launch {
            repository.deleteSlot(slot)
        }
    }

    fun bookAppointment(patientId: String, professionalId: String, slotId: String) {
        viewModelScope.launch {
            repository.bookAppointment(patientId, professionalId, slotId)
        }
    }

    fun getPatientAppointments(patientId: String): StateFlow<List<Appointment>> =
        repository.getPatientAppointments(patientId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun getProfessionalAppointments(professionalId: String): StateFlow<List<Appointment>> =
        repository.getProfessionalAppointments(professionalId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    class Factory(private val repository: AppointmentRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AppointmentViewModel(repository) as T
        }
    }
}
