package com.example.yakap.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.yakap.data.PreferenceManager
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [35])
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNavigation_startsAtSplash() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val preferenceManager = PreferenceManager(context)
        
        composeTestRule.setContent {
            val navController = rememberNavController()
            AppNavigation(
                navController = navController,
                preferenceManager = preferenceManager
            )
        }

        composeTestRule.onNodeWithText("YAKAP").assertExists()
    }
}
