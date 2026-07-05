package com.ebdz.libraries.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * MVI base class. State lives in a [StateFlow], one-off effects in a [SharedFlow], and every
 * state change goes through the pure [reducer]. Async work runs in [viewModelScope]; subclasses
 * do their work in [onAction].
 */
abstract class BaseViewModel<S : UiState, E : UiEvent, A : UiAction, F : UiEffect>(
    initialState: S,
    private val reducer: Reducer<S, E>
) : ViewModel(), StateMediator<S, A, F> {

    private val mutableStateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    private val mutableEffectsFlow: MutableSharedFlow<F> = MutableSharedFlow(replay = 0)

    override val uiState: StateFlow<S> = mutableStateFlow.asStateFlow()
    override val effectsFlow: SharedFlow<F> = mutableEffectsFlow.asSharedFlow()

    /** Handle an action from the UI. Subclasses kick off async work here and feed the
     * results back through [sendEvent]. */
    abstract override fun onAction(action: A)

    /**
     * [sendEvent] is reducing the view to a new [UiState] using the redux pattern.
     * @param event is the UiEvent which is happening
     */
    protected fun sendEvent(event: E) =
        reducer.redux(mutableStateFlow.value, event) { nextState -> mutableStateFlow.value = nextState }

    /**
     * Emit a one-off effect (navigation, toast, etc.). Late collectors don't see it.
     */
    protected fun sendEffect(effect: F) {
        viewModelScope.launch {
            mutableEffectsFlow.emit(effect)
        }
    }
}
