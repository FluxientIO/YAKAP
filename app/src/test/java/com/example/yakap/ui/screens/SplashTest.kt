package com.example.yakap.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import com.example.yakap.data.PreferenceManager
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class SplashTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun splashScreen_displaysAppName() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val preferenceManager = PreferenceManager(context)

        composeTestRule.setContent {
            SplashScreen(
                preferenceManager = preferenceManager,
                onSplashFinished = {}
            )
        }

        composeTestRule.onNodeWithText("YAKAP").assertExists()
    }
}
