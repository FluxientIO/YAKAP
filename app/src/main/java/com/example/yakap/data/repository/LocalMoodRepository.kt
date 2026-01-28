package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.MoodDao
import com.example.yakap.data.local.entities.toDomain
import com.example.yakap.data.local.entities.toEntity
import com.example.yakap.data.models.MoodEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalMoodRepository(private val moodDao: MoodDao) : MoodRepository {
    override suspend fun saveMood(entry: MoodEntry) {
        moodDao.insertMood(entry.toEntity())
    }

    override fun getMoodHistory(): Flow<List<MoodEntry>> {
        return moodDao.getAllMoods().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}
