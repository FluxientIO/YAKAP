package com.example.yakap.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.yakap.data.models.MoodEntry
import com.example.yakap.data.models.MoodType
import com.example.yakap.data.repository.MoodRepository
import com.example.yakap.ui.viewmodels.MoodViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

import com.example.yakap.ui.theme.YAKAPTheme
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class DashboardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val repository = mockk<MoodRepository>()

    @Test
    fun latestMoodCard_displaysMoodInfo() {
        val entry = MoodEntry("1", MoodType.GREAT, "Fabulous", System.currentTimeMillis())

        composeTestRule.setContent {
            YAKAPTheme(dynamicColor = false) {
                LatestMoodCard(mood = entry)
            }
        }

        composeTestRule.onNodeWithText("Your Latest Mood").assertExists()
        composeTestRule.onNodeWithText("Great").assertExists()
        composeTestRule.onNodeWithText("Fabulous").assertExists()
    }

    @Test
    fun moodHistoryItem_displaysInfo() {
        val entry = MoodEntry("1", MoodType.GOOD, "Feeling better", System.currentTimeMillis())

        composeTestRule.setContent {
            YAKAPTheme(dynamicColor = false) {
                MoodHistoryItem(entry = entry)
            }
        }

        composeTestRule.onNodeWithText("Good").assertExists()
        composeTestRule.onNodeWithText("Feeling better").assertExists()
    }

    @Test
    fun dashboard_displaysTitleAndLatestMood() {
        val history = listOf(
            MoodEntry("1", MoodType.GREAT, "Fabulous", System.currentTimeMillis())
        )

        composeTestRule.setContent {
            YAKAPTheme(dynamicColor = false) {
                DashboardContent(moodHistory = history, upcomingAppointments = emptyList())
            }
        }

        composeTestRule.onRoot().printToLog("DEBUG")
        composeTestRule.onNodeWithText("Welcome", substring = true).assertExists()
        composeTestRule.onNodeWithText("Your Latest Mood").assertExists()
        composeTestRule.onNodeWithText("Great").assertExists()
        composeTestRule.onNodeWithText("Fabulous").assertExists()
    }
}
