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
class OnboardingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun onboarding_displaysFirstPageAndNavigation() {
        composeTestRule.setContent {
            OnboardingScreen(onFinished = {})
        }

        // Check for the first page title (from product spec)
        composeTestRule.onNodeWithText("Welcome to YAKAP").assertExists()
        
        // Check for navigation buttons
        composeTestRule.onNodeWithText("Next").assertExists()
        composeTestRule.onNodeWithText("Skip").assertExists()
    }
}