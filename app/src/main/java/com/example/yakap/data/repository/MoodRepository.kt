package com.example.yakap.data.repository

import com.example.yakap.data.models.MoodEntry
import kotlinx.coroutines.flow.Flow

interface MoodRepository {
    suspend fun saveMood(entry: MoodEntry)
    fun getMoodHistory(): Flow<List<MoodEntry>>
}
