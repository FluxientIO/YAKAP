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
class RoleSelectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun roleSelection_displaysAllRoles() {
        composeTestRule.setContent {
            RoleSelectionScreen(onRoleSelected = {})
        }

        composeTestRule.onNodeWithText("Patient/User").assertExists()
        composeTestRule.onNodeWithText("Mental Health Professional").assertExists()
        composeTestRule.onNodeWithText("Administrator").assertExists()
    }
}
