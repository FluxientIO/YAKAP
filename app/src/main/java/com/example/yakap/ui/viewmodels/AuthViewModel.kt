package com.example.yakap.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yakap.data.repository.AuthRepository
import com.example.yakap.ui.models.UserRole
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val error: String? = null
)

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _uiState = mutableStateOf(AuthUiState())
    val uiState: State<AuthUiState> = _uiState

    fun login(email: String, pass: String) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = authRepository.login(email, pass)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
            } else {
                _uiState.value = _uiState.value.copy(isLoading = false, error = result.exceptionOrNull()?.message)
            }
        }
    }

    fun signUp(name: String, email: String, pass: String, role: UserRole, license: String?) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            val result = authRepository.signUp(name, email, pass, role, license)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true)
            } else {
                _uiState.value = _uiState.value.copy(isLoading = false, error = result.exceptionOrNull()?.message)
            }
        }
    }

    class Factory(private val repository: AuthRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AuthViewModel(repository) as T
        }
    }
}
