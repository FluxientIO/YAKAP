package com.example.yakap.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yakap.data.models.AssessmentResult
import kotlinx.coroutines.flow.Flow

@Dao
interface AssessmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: AssessmentResult)

    @Query("SELECT * FROM assessment_results WHERE userId = :userId ORDER BY timestamp DESC")
    fun getResultsForUser(userId: String): Flow<List<AssessmentResult>>
}
