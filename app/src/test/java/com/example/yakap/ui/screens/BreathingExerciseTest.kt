package com.example.yakap.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class BreathingExerciseTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun breathingExercise_showsStartButtonInitially() {
        composeTestRule.setContent {
            BreathingExerciseScreen()
        }

        composeTestRule.onNodeWithText("Start").assertExists()
    }
}
