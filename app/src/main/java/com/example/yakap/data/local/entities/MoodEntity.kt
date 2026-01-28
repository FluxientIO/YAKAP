package com.example.yakap.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yakap.data.models.MoodEntry
import com.example.yakap.data.models.MoodType

@Entity(tableName = "moods")
data class MoodEntity(
    @PrimaryKey val id: String,
    val moodType: String,
    val note: String,
    val timestamp: Long
)

fun MoodEntity.toDomain() = MoodEntry(
    id = id,
    moodType = MoodType.valueOf(moodType),
    note = note,
    timestamp = timestamp
)

fun MoodEntry.toEntity() = MoodEntity(
    id = id,
    moodType = moodType.name,
    note = note,
    timestamp = timestamp
)
