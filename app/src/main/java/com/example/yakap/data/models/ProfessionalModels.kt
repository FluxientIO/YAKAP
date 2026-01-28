package com.example.yakap.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patients")
data class Patient(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val dateOfBirth: Long,
    val assignedProfessionalId: String
)

@Entity(tableName = "consultation_notes")
data class ConsultationNote(
    @PrimaryKey val id: String,
    val patientId: String,
    val professionalId: String,
    val content: String,
    val timestamp: Long
)
