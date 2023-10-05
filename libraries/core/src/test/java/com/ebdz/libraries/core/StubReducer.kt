package com.ebdz.libraries.core

class StubReducer : Reducer<StubUiState, StubUiEvent> {
    override fun redux(
        oldState: StubUiState,
        event: StubUiEvent,
        onNewState: (StubUiState) -> Unit
    ) = when (event) {

        is StubUiEvent.AddNewItem -> {
            val newData = arrayListOf<String>().also {
                it.addAll(oldState.data)
                it.add(event.text)
            }

            onNewState(
                oldState.copy(
                    data = newData,
                    isShowingAddDialog = false,
                    isShowErrorScreen = false
                )
            )
        }
        is StubUiEvent.ShowData -> {
            if (event.data.isEmpty()) {
                onNewState(
                    oldState.copy(
                        isShowingData = false,
                        isShowErrorScreen = true
                    )
                )
            } else {
                onNewState(
                    oldState.copy(
                        isShowErrorScreen = false,
                        isShowingData = true,
                        data = event.data
                    )
                )
            }
        }
        StubUiEvent.ShowDialog -> onNewState(oldState.copy(isShowingAddDialog = true))
    }
}
