package com.example.yakap.ui.viewmodels

import com.example.yakap.data.repository.AuthRepository
import com.example.yakap.ui.models.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {

    private lateinit var viewModel: AuthViewModel
    private lateinit var repository: FakeAuthRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = FakeAuthRepository()
        viewModel = AuthViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun login_startsLoading() = runTest {
        viewModel.login("test", "pass")
        assertTrue(viewModel.uiState.value.isLoading)
    }

    @Test
    fun login_success_updatesState() = runTest {
        repository.shouldReturnError = false
        viewModel.login("test", "pass")
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(true, viewModel.uiState.value.isLoggedIn)
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals(null, viewModel.uiState.value.error)
    }

    @Test
    fun login_failure_updatesState() = runTest {
        repository.shouldReturnError = true
        viewModel.login("test", "pass")
        testDispatcher.scheduler.advanceUntilIdle()
        assertEquals(false, viewModel.uiState.value.isLoggedIn)
        assertEquals(false, viewModel.uiState.value.isLoading)
        assertEquals("Login failed", viewModel.uiState.value.error)
    }
}

class FakeAuthRepository : AuthRepository {
    var shouldReturnError = false

    override suspend fun login(email: String, password: String): Result<Boolean> {
        return if (shouldReturnError) {
            Result.failure(Exception("Login failed"))
        } else {
            Result.success(true)
        }
    }

    override suspend fun signUp(
        name: String,
        email: String,
        password: String,
        role: UserRole,
        licenseNumber: String?
    ): Result<Boolean> {
        return Result.success(true)
    }
}
