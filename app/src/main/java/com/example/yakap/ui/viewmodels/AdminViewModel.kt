package com.example.yakap.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yakap.data.models.UserAccount
import com.example.yakap.data.repository.AdminRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AdminViewModel(private val repository: AdminRepository) : ViewModel() {

    val allUsers: StateFlow<List<UserAccount>> = repository.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val pendingVerifications: StateFlow<List<UserAccount>> = repository.getPendingVerifications()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val totalUserCount: StateFlow<Int> = repository.getTotalUserCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val activeProfessionalCount: StateFlow<Int> = repository.getActiveProfessionalCount()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val totalMoodEntries: StateFlow<Int> = repository.getTotalMoodEntries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val totalAppointments: StateFlow<Int> = repository.getTotalAppointments()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    fun verifyProfessional(userId: String, approve: Boolean) {
        viewModelScope.launch {
            repository.verifyProfessional(userId, approve)
        }
    }

    class Factory(private val repository: AdminRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AdminViewModel(repository) as T
        }
    }
}
