package com.ebdz.libraries.core

data class StubUiState(
    val data: List<String> = emptyList(),
    val isShowingData: Boolean = false,
    val isShowingAddDialog: Boolean = false,
    val isShowErrorScreen: Boolean = false
) : UiState

sealed class StubUiEvent : UiEvent {
    data class ShowData(val data: List<String>) : StubUiEvent()
    data class AddNewItem(val text: String) : StubUiEvent()
    data object ShowDialog : StubUiEvent()
}

sealed class StubUiAction : UiAction {
    data object ShowDialog : StubUiAction()
    data class AddItem(val item: String) : StubUiAction()
    data object Fire : StubUiAction()
}

sealed class StubUiEffect : UiEffect {
    data object Fired : StubUiEffect()
}

class StubReducer : Reducer<StubUiState, StubUiEvent> {
    override fun redux(
        oldState: StubUiState,
        event: StubUiEvent,
        onNewState: (StubUiState) -> Unit
    ) {
        when (event) {
            is StubUiEvent.AddNewItem -> onNewState(
                oldState.copy(
                    data = oldState.data + event.text,
                    isShowingAddDialog = false,
                    isShowErrorScreen = false
                )
            )

            is StubUiEvent.ShowData -> onNewState(
                if (event.data.isEmpty()) {
                    oldState.copy(isShowingData = false, isShowErrorScreen = true)
                } else {
                    oldState.copy(isShowingData = true, isShowErrorScreen = false, data = event.data)
                }
            )

            StubUiEvent.ShowDialog -> onNewState(oldState.copy(isShowingAddDialog = true))
        }
    }
}

class StubViewModel :
    BaseViewModel<StubUiState, StubUiEvent, StubUiAction, StubUiEffect>(
        initialState = StubUiState(),
        reducer = StubReducer()
    ) {

    fun loadData(stubbingData: () -> List<String> = { listOf("first") }) {
        sendEvent(StubUiEvent.ShowData(stubbingData()))
    }

    override fun onAction(action: StubUiAction) {
        when (action) {
            StubUiAction.ShowDialog -> sendEvent(StubUiEvent.ShowDialog)
            is StubUiAction.AddItem -> sendEvent(StubUiEvent.AddNewItem(action.item))
            StubUiAction.Fire -> sendEffect(StubUiEffect.Fired)
        }
    }
}
