package com.example.yakap.ui.viewmodels

import com.example.yakap.data.models.MoodEntry
import com.example.yakap.data.models.MoodType
import com.example.yakap.data.repository.MoodRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoodViewModelTest {

    private lateinit var viewModel: MoodViewModel
    private val repository = mockk<MoodRepository>()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { repository.getMoodHistory() } returns flowOf(emptyList())
        viewModel = MoodViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun saveMood_callsRepository() = runTest {
        coEvery { repository.saveMood(any()) } returns Unit

        viewModel.updateMood(MoodType.GREAT)
        viewModel.updateNote("Feeling awesome")
        viewModel.saveMood()
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.saveMood(match { 
            it.moodType == MoodType.GREAT && it.note == "Feeling awesome"
        }) }
    }
}
