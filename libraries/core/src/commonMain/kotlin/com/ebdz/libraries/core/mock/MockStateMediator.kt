package com.ebdz.libraries.core.mock

import com.ebdz.libraries.core.StateMediator
import com.ebdz.libraries.core.UiAction
import com.ebdz.libraries.core.UiEffect
import com.ebdz.libraries.core.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

class MockStateMediator<S : UiState, A : UiAction, F : UiEffect>(
    initialState: S
) : StateMediator<S, A, F> {

    override val uiState: StateFlow<S> = MutableStateFlow(initialState)
    override val effectsFlow: SharedFlow<F> = MutableSharedFlow(replay = 0)

    override fun onAction(action: A) {
        // no-op
    }
}
