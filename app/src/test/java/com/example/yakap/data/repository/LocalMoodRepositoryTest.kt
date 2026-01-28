package com.example.yakap.data.repository

import com.example.yakap.data.local.dao.MoodDao
import com.example.yakap.data.local.entities.MoodEntity
import com.example.yakap.data.models.MoodEntry
import com.example.yakap.data.models.MoodType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LocalMoodRepositoryTest {

    private val moodDao = mockk<MoodDao>()
    private val repository = LocalMoodRepository(moodDao)

    @Test
    fun saveMood_callsDaoInsert() = runBlocking {
        val entry = MoodEntry("1", MoodType.GREAT, "Feeling good", 12345L)
        coEvery { moodDao.insertMood(any()) } returns Unit

        repository.saveMood(entry)

        coVerify { moodDao.insertMood(match { 
            it.id == "1" && it.moodType == "GREAT" && it.note == "Feeling good"
        }) }
    }

    @Test
    fun getMoodHistory_returnsMappedFlow() = runBlocking {
        val entity = MoodEntity("1", "GOOD", "Test note", 12345L)
        every { moodDao.getAllMoods() } returns flowOf(listOf(entity))

        val result = repository.getMoodHistory().first()

        assertEquals(1, result.size)
        assertEquals(MoodType.GOOD, result[0].moodType)
        assertEquals("1", result[0].id)
    }
}
