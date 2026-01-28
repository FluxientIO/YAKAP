package com.example.yakap.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.yakap.data.local.dao.AssessmentDao
import com.example.yakap.data.models.AssessmentResult
import com.example.yakap.data.models.QuizType
import com.example.yakap.domain.AssessmentEngine
import com.example.yakap.domain.QuizQuestion
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

data class AssessmentUiState(
    val currentQuestionIndex: Int = 0,
    val totalScore: Int = 0,
    val isFinished: Boolean = false,
    val resultInterpretation: String? = null,
    val quizType: QuizType = QuizType.GAD7
)

class AssessmentViewModel(private val dao: AssessmentDao) : ViewModel() {

    private val _uiState = mutableStateOf(AssessmentUiState())
    val uiState: State<AssessmentUiState> = _uiState

    val questions: List<QuizQuestion> = AssessmentEngine.gad7Questions

    fun selectOption(score: Int) {
        val nextIndex = _uiState.value.currentQuestionIndex + 1
        val newScore = _uiState.value.totalScore + score

        if (nextIndex < questions.size) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = nextIndex,
                totalScore = newScore
            )
        } else {
            finishAssessment(newScore)
        }
    }

    private fun finishAssessment(finalScore: Int) {
        val interpretation = AssessmentEngine.calculateResult(_uiState.value.quizType, finalScore)
        _uiState.value = _uiState.value.copy(
            isFinished = true,
            totalScore = finalScore,
            resultInterpretation = interpretation
        )

        viewModelScope.launch {
            val result = AssessmentResult(
                id = UUID.randomUUID().toString(),
                userId = "u1", // Mock user ID
                quizType = _uiState.value.quizType,
                score = finalScore,
                interpretation = interpretation,
                timestamp = System.currentTimeMillis()
            )
            dao.insertResult(result)
        }
    }

    fun reset() {
        _uiState.value = AssessmentUiState()
    }

    fun getHistory(userId: String): StateFlow<List<AssessmentResult>> =
        dao.getResultsForUser(userId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    class Factory(private val dao: AssessmentDao) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AssessmentViewModel(dao) as T
        }
    }
}
