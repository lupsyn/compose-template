package com.ebdz.libraries.core

sealed class StubUiEvent : UiEvent {
    data class ShowData(val data: List<String>) : StubUiEvent()

    data class AddNewItem(val text: String) : StubUiEvent()

    data object ShowDialog : StubUiEvent()
}
