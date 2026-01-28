package com.example.yakap.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.yakap.ui.models.UserRole
import com.example.yakap.ui.viewmodels.AuthUiState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun login_displaysFieldsAndButton() {
        composeTestRule.setContent {
            LoginScreen(
                role = UserRole.PATIENT,
                uiState = AuthUiState(),
                onLoginSubmitted = { _, _ -> },
                onSignUpClick = {}
            )
        }

        composeTestRule.onNodeWithText("Email Address").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Log In").assertExists()
    }
}
