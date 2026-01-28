package com.example.yakap.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.yakap.data.models.MoodType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class MoodSelectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun moodSelection_displaysAllMoods() {
        composeTestRule.setContent {
            MoodSelection(selectedMood = null, onMoodSelected = {})
        }

        // We'll use text labels for now to simplify testing
        composeTestRule.onNodeWithText("Great").assertExists()
        composeTestRule.onNodeWithText("Good").assertExists()
        composeTestRule.onNodeWithText("Neutral").assertExists()
        composeTestRule.onNodeWithText("Low").assertExists()
        composeTestRule.onNodeWithText("Bad").assertExists()
    }
}
