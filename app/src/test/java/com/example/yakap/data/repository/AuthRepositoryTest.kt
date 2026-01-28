package com.example.yakap.data.repository

import com.example.yakap.ui.models.UserRole
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class AuthRepositoryTest {

    private val repository = MockAuthRepository()

    @Test
    fun login_success_withValidCredentials() = runBlocking {
        val result = repository.login("test@example.com", "password123")
        assertTrue(result.isSuccess)
    }

    @Test
    fun login_failure_withShortPassword() = runBlocking {
        val result = repository.login("test@example.com", "short")
        assertTrue(result.isFailure)
    }

    @Test
    fun signUp_success_patient() = runBlocking {
        val result = repository.signUp("Test User", "test@example.com", "password123", UserRole.PATIENT, null)
        assertTrue(result.isSuccess)
    }

    @Test
    fun signUp_failure_professionalWithoutLicense() = runBlocking {
        val result = repository.signUp("Dr. Test", "doc@example.com", "password123", UserRole.PROFESSIONAL, null)
        assertTrue(result.isFailure)
    }
    
    @Test
    fun signUp_success_professionalWithLicense() = runBlocking {
        val result = repository.signUp("Dr. Test", "doc@example.com", "password123", UserRole.PROFESSIONAL, "LIC-123")
        assertTrue(result.isSuccess)
    }
}
