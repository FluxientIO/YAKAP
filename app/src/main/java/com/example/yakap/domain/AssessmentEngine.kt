package com.example.yakap.domain

import com.example.yakap.data.models.QuizType

data class QuizQuestion(
    val text: String,
    val options: List<QuizOption>
)

data class QuizOption(
    val text: String,
    val score: Int
)

object AssessmentEngine {

    private val standardOptions = listOf(
        QuizOption("Not at all", 0),
        QuizOption("Several days", 1),
        QuizOption("More than half the days", 2),
        QuizOption("Nearly every day", 3)
    )

    val gad7Questions = listOf(
        QuizQuestion("Feeling nervous, anxious, or on edge", standardOptions),
        QuizQuestion("Not being able to stop or control worrying", standardOptions),
        QuizQuestion("Worrying too much about different things", standardOptions),
        QuizQuestion("Trouble relaxing", standardOptions),
        QuizQuestion("Being so restless that it is hard to sit still", standardOptions),
        QuizQuestion("Becoming easily annoyed or irritable", standardOptions),
        QuizQuestion("Feeling afraid, as if something awful might happen", standardOptions)
    )

    fun calculateResult(quizType: QuizType, totalScore: Int): String {
        return when (quizType) {
            QuizType.GAD7 -> interpretGAD7(totalScore)
            QuizType.PHQ9 -> "Result recorded" // Placeholder
        }
    }

    private fun interpretGAD7(score: Int): String {
        return when {
            score <= 4 -> "Minimal anxiety"
            score <= 9 -> "Mild anxiety"
            score <= 14 -> "Moderate anxiety"
            else -> "Severe anxiety"
        }
    }
}