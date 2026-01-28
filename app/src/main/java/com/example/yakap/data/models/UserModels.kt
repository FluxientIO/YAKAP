package com.example.yakap.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yakap.ui.models.UserRole

@Entity(tableName = "user_accounts")
data class UserAccount(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val role: UserRole,
    val isVerified: Boolean = false,
    val verificationDate: Long? = null,
    val licenseNumber: String? = null
)
