package com.example.yakap.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yakap.data.models.MoodEntry
import com.example.yakap.data.models.MoodType
import com.example.yakap.data.repository.MoodRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

data class MoodUiState(
    val selectedMood: MoodType? = null,
    val note: String = "",
    val isSaving: Boolean = false,
    val saveSuccess: Boolean = false
)

class MoodViewModel(private val moodRepository: MoodRepository) : ViewModel() {

    private val _uiState = mutableStateOf(MoodUiState())
    val uiState: State<MoodUiState> = _uiState

    val moodHistory: StateFlow<List<MoodEntry>> = moodRepository.getMoodHistory()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateMood(mood: MoodType) {
        _uiState.value = _uiState.value.copy(selectedMood = mood)
    }

    fun updateNote(note: String) {
        _uiState.value = _uiState.value.copy(note = note)
    }

    fun saveMood() {
        val mood = _uiState.value.selectedMood ?: return
        val note = _uiState.value.note
        
        _uiState.value = _uiState.value.copy(isSaving = true)
        
        viewModelScope.launch {
            val entry = MoodEntry(
                id = UUID.randomUUID().toString(),
                moodType = mood,
                note = note,
                timestamp = System.currentTimeMillis()
            )
            moodRepository.saveMood(entry)
            _uiState.value = _uiState.value.copy(
                isSaving = false, 
                saveSuccess = true,
                selectedMood = null,
                note = ""
            )
        }
    }

    class Factory(private val repository: MoodRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MoodViewModel(repository) as T
        }
    }
}
