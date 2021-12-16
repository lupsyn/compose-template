package com.ebdz.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.ebdz.compose.model.HomeSection
import com.ebdz.compose.navigation.NavGraph
import com.ebdz.designsystem.Theme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            Theme {
                NavGraph()
            }
        }
    }

    @Test
    fun test_titleChangesWhenBottomIconIsSelected() {
        HomeSection.values().forEach { section ->
            val title = context.getString(section.title)

            composeTestRule.onNodeWithContentDescription(label = title, useUnmergedTree = true)
                .performClick()

            composeTestRule.onNodeWithText(title).assertExists()
        }
    }

    @Test
    fun test_Assert() {
        assert(false) // A dummy test to check what happens when a test fail. ;)
    }
}
