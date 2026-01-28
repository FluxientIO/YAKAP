package com.example.yakap.data.models

enum class MoodType {
    GREAT,
    GOOD,
    NEUTRAL,
    LOW,
    BAD
}

data class MoodEntry(
    val id: String,
    val moodType: MoodType,
    val note: String,
    val timestamp: Long
)
