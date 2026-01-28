package com.example.yakap.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yakap.data.local.dao.AppointmentDao
import com.example.yakap.data.local.dao.*
import com.example.yakap.data.local.entities.MoodEntity
import com.example.yakap.data.models.*

@Database(entities = [MoodEntity::class, Patient::class, ConsultationNote::class, AppointmentSlot::class, Appointment::class, Conversation::class, ChatMessage::class, AssessmentResult::class, UserAccount::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moodDao(): MoodDao
    abstract fun professionalDao(): ProfessionalDao
    abstract fun appointmentDao(): AppointmentDao
    abstract fun chatDao(): ChatDao
    abstract fun assessmentDao(): AssessmentDao
    abstract fun adminDao(): AdminDao
}
