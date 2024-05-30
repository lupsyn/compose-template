package com.ebdz.libraries.core

data class StubUiState(
    val data: List<String>,
    val isShowingData: Boolean,
    val isShowingAddDialog: Boolean,
    val isShowErrorScreen: Boolean
) : UiState

internal fun getStubInitialUiState() = StubUiState(
    data = emptyList(),
    isShowingData = false,
    isShowingAddDialog = false,
    isShowErrorScreen = false
)
