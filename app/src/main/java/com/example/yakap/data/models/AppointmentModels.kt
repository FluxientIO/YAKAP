package com.example.yakap.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class AppointmentStatus {
    PENDING,
    CONFIRMED,
    COMPLETED,
    CANCELLED
}

@Entity(tableName = "appointment_slots")
data class AppointmentSlot(
    @PrimaryKey val id: String,
    val professionalId: String,
    val startTime: Long,
    val endTime: Long,
    val isBooked: Boolean = false
)

@Entity(tableName = "appointments")
data class Appointment(
    @PrimaryKey val id: String,
    val patientId: String,
    val professionalId: String,
    val slotId: String,
    val status: AppointmentStatus,
    val timestamp: Long
)
