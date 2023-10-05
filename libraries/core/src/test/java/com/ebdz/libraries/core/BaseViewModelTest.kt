package com.ebdz.libraries.core

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class BaseViewModelTest {
    private lateinit var underTest: StubBaseViewModel

    @Before
    fun setup() {
        underTest = StubBaseViewModel()
    }

    @Test
    fun `should emit initial data with empty data set`() {
        assertEquals(underTest.getUiState().value, getStubInitialUiState())
    }

    @Test
    fun `once load data is called should emit data set with one item`() {
        underTest.onLoadData()

        assertEquals(
            underTest.getUiState().value,
            getStubInitialUiState().copy(
                isShowingData = true,
                data = listOf("first")
            )
        )
    }

    @Test
    fun `once load data is called data should be emitted, and item should be added in case of add action`() {
        underTest.onLoadData()

        assertEquals(
            underTest.getUiState().value,
            getStubInitialUiState().copy(
                isShowingData = true,
                data = listOf("first")
            )
        )

        underTest.onAction(StubUiAction.AddItem("second"))

        assertEquals(
            underTest.getUiState().value,

            getStubInitialUiState().copy(
                isShowingData = true,
                data = listOf("first", "second")
            )
        )
    }

    @Test
    fun `if show data has empty element an error should be emitted `() {
        underTest.onStubLoadData { emptyList() }

        assertEquals(
            underTest.getUiState().value,

            getStubInitialUiState().copy(
                isShowingData = false,
                isShowErrorScreen = true
            )
        )
    }

    @Test
    fun `on user interaction should show dialog`() {
        underTest.onAction(StubUiAction.ShowDialog)

        assertEquals(
            underTest.getUiState().value,

            getStubInitialUiState().copy(
                isShowingAddDialog = true,
            )
        )
    }

    @Test
    fun `on user interaction should dismiss dialog`() {
        underTest.onAction(StubUiAction.ShowDialog)

        assertEquals(
            underTest.getUiState().value,

            getStubInitialUiState().copy(
                isShowingAddDialog = true
            )
        )
        underTest.onAction(StubUiAction.AddItem("second"))

        assertEquals(
            underTest.getUiState().value,

            getStubInitialUiState().copy(
                data = listOf("second"),
                isShowingAddDialog = false,
                isShowErrorScreen = false
            )
        )
    }
}
