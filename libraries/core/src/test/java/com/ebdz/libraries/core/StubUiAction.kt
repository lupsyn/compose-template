package com.ebdz.libraries.core

sealed class StubUiAction : UiAction {
    object ShowDialog : StubUiAction()
    class AddItem(val item: String) : StubUiAction()
}
