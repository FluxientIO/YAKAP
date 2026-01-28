package com.example.yakap.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yakap.data.local.entities.MoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: MoodEntity)

    @Query("SELECT * FROM moods ORDER BY timestamp DESC")
    fun getAllMoods(): Flow<List<MoodEntity>>
}
