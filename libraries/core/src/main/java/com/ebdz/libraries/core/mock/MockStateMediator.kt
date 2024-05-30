package com.ebdz.libraries.core.mock

import com.ebdz.libraries.core.StateMediator
import com.ebdz.libraries.core.UiAction
import com.ebdz.libraries.core.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MockStateMediator<S : UiState, A : UiAction> : StateMediator<S, A> {
    @Suppress("UNCHECKED_CAST")
    override fun getUiState(): StateFlow<S> = MutableStateFlow(Unit as S)

    override fun onAction(action: A) {
        // no-opt
    }
}
