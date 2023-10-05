package com.ebdz.libraries.core

class StubBaseViewModel : BaseViewModel<StubUiState, StubUiEvent, StubUiAction>(
    initialState = getStubInitialUiState(),
    reducer = StubReducer()
) {

    // Just make public to test the first emission
    fun onStubLoadData(stubbingData: () -> List<String>) {
        sendEvent(StubUiEvent.ShowData(stubbingData.invoke()))
    }

    public override fun onLoadData() {
        super.onLoadData()

        sendEvent(StubUiEvent.ShowData(listOf("first")))
    }

    override fun onAction(action: StubUiAction) {
        when (action) {
            StubUiAction.ShowDialog -> showDialog()
            is StubUiAction.AddItem -> addEvent(action.item)
        }
    }

    private fun addEvent(item: String) = sendEvent(StubUiEvent.AddNewItem(item))

    private fun showDialog() = sendEvent(StubUiEvent.ShowDialog)
}
