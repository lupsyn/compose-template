package com.ebdz.preference

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.ebdz.designsystem.Theme
import com.ebdz.preference.presentation.PreferenceSection
import org.junit.Rule
import org.junit.Test

internal class PreferenceSectionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun test_allItemsAreDisplayed() {
        // When the view is loaded
        loadView()

        // Then the items are shown
        val itemAbout = context.getString(R.string.preference_title_about)
        val itemVersion = context.getString(R.string.preference_title_version)

        composeTestRule.onNodeWithText(text = itemAbout, useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText(text = itemVersion, useUnmergedTree = true).assertExists()
    }

    private fun loadView() {
        composeTestRule.setContent {
            Theme {
                PreferenceSection(onAboutClick = { })
            }
        }
    }
}
