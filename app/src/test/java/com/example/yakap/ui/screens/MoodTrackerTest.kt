package com.example.yakap.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.yakap.data.repository.MoodRepository
import com.example.yakap.ui.viewmodels.MoodViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class MoodTrackerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val repository = mockk<MoodRepository>()

    @Test
    fun moodTracker_displaysTitleAndButton() {
        every { repository.getMoodHistory() } returns flowOf(emptyList())
        val viewModel = MoodViewModel(repository)

        composeTestRule.setContent {
            MoodTrackerScreen(viewModel = viewModel, onSaved = {})
        }

        composeTestRule.onNodeWithText("How are you feeling?").assertExists()
        composeTestRule.onNodeWithText("Save Mood").assertExists()
    }
}
