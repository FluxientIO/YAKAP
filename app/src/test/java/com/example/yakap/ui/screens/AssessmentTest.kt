package com.example.yakap.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.yakap.data.local.dao.AssessmentDao
import com.example.yakap.ui.viewmodels.AssessmentViewModel
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class AssessmentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val dao = mockk<AssessmentDao>()

    @Test
    fun assessment_displaysQuestionAndOptions() {
        val viewModel = AssessmentViewModel(dao)

        composeTestRule.setContent {
            AssessmentScreen(viewModel = viewModel, onFinished = {})
        }

        // Verify first question of GAD-7
        composeTestRule.onNodeWithText("Feeling nervous, anxious, or on edge").assertExists()
        composeTestRule.onNodeWithText("Not at all").assertExists()
    }
}
