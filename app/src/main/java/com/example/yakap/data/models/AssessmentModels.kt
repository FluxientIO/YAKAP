package com.example.yakap.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class QuizType {
    GAD7,
    PHQ9
}

@Entity(tableName = "assessment_results")
data class AssessmentResult(
    @PrimaryKey val id: String,
    val userId: String,
    val quizType: QuizType,
    val score: Int,
    val interpretation: String,
    val timestamp: Long
)
