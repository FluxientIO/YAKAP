package com.example.yakap.data.repository

import com.example.yakap.ui.models.UserRole
import kotlinx.coroutines.delay

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun signUp(name: String, email: String, password: String, role: UserRole, licenseNumber: String?): Result<Boolean>
}

class MockAuthRepository : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Boolean> {
        delay(1000) // Simulate network delay
        return if (email.isNotEmpty() && password.length >= 8) {
            Result.success(true)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        role: UserRole,
        licenseNumber: String?
    ): Result<Boolean> {
        delay(1000) // Simulate network delay
        return if (email.isNotEmpty() && password.length >= 8 && name.isNotEmpty()) {
             if (role == UserRole.PROFESSIONAL && licenseNumber.isNullOrEmpty()) {
                 Result.failure(Exception("License number required"))
             } else {
                 Result.success(true)
             }
        } else {
            Result.failure(Exception("Invalid input"))
        }
    }
}
