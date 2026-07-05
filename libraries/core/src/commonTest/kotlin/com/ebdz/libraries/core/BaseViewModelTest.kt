package com.ebdz.libraries.core

import app.cash.turbine.test
import com.ebdz.libraries.test.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {

    private val mainDispatcherRule = MainDispatcherRule()
    private lateinit var underTest: StubViewModel

    @BeforeTest
    fun setup() {
        mainDispatcherRule.setUp()
        underTest = StubViewModel()
    }

    @AfterTest
    fun tearDown() {
        mainDispatcherRule.tearDown()
    }

    @Test
    fun `initial state has no data`() {
        assertEquals(StubUiState(), underTest.uiState.value)
    }

    @Test
    fun `loading data updates state via the reducer`() {
        underTest.loadData()

        assertEquals(
            StubUiState(isShowingData = true, data = listOf("first")),
            underTest.uiState.value
        )
    }

    @Test
    fun `action flows through onAction into the reducer`() {
        underTest.loadData()
        underTest.onAction(StubUiAction.AddItem("second"))

        assertEquals(
            StubUiState(isShowingData = true, data = listOf("first", "second")),
            underTest.uiState.value
        )
    }

    @Test
    fun `empty data set surfaces an error state`() {
        underTest.loadData { emptyList() }

        assertEquals(
            StubUiState(isShowingData = false, isShowErrorScreen = true),
            underTest.uiState.value
        )
    }

    @Test
    fun `action can emit a one-off effect`() = runTest {
        underTest.effectsFlow.test {
            underTest.onAction(StubUiAction.Fire)

            assertEquals(StubUiEffect.Fired, awaitItem())
        }
    }
}
