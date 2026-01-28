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
class SignUpTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun signUp_patient_showsStandardFields() {
        composeTestRule.setContent {
            SignUpScreen(
                role = UserRole.PATIENT,
                uiState = AuthUiState(),
                onSignUpSubmitted = { _, _, _, _ -> },
                onLoginClick = {}
            )
        }

        composeTestRule.onNodeWithText("Full Name").assertExists()
        composeTestRule.onNodeWithText("Email Address").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("License Number").assertDoesNotExist()
    }

    @Test
    fun signUp_professional_showsLicenseField() {
        composeTestRule.setContent {
            SignUpScreen(
                role = UserRole.PROFESSIONAL,
                uiState = AuthUiState(),
                onSignUpSubmitted = { _, _, _, _ -> },
                onLoginClick = {}
            )
        }

        composeTestRule.onNodeWithText("License Number").assertExists()
    }
}
