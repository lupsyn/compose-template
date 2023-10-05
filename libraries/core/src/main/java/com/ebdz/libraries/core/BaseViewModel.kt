package com.ebdz.libraries.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * [BaseViewModel] implementation will host our business logic.
 * It's based on the MVI-redux pattern and forced to the contract by [StateMediator].
 *
 * Internally [sendEvent] has the responsibility to call the Reducer and set the new state.
 * [setState] is private due we can't access directly but only by an [UiAction]
 *
 * [onLoadData] is not called in the constructor (init{}), it's indeed developer's choice
 * decide when to call it (and postpone emission to a stream?)
 */
abstract class BaseViewModel<S : UiState, E : UiEvent, A : UiAction>(
    initialState: S,
    private val reducer: Reducer<S, E>
) : ViewModel(), StateMediator<S, A> {

    /**
     * [state] private to force using [onAction]
     * it represents the state [UiState] of the view layer.
     *
     * Please refer to official guidelines to collect the [UiState] :
     * https://developer.android.com/topic/architecture/ui-layer/events#other-use-cases
     * https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda
     */
    private val state: StateFlow<S>
        get() = mutableStateFlow.asStateFlow() // Force to be readOnly

    /**
     * [mutableStateFlow] represents the state mutable [UiState] of the view layer.
     */
    private val mutableStateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)

    /**
     * [getUiState] just exposing the [UiState]
     */
    override fun getUiState(): StateFlow<S> = state

    /**
     * @param action this action is flowing from the view.
     * This is the only legal way to tell something to ViewModel from a View.
     */
    abstract override fun onAction(action: A)

    /**
     * [onLoadData] is the func which should be override in order load
     * initial data. We are not forcing this in the constructor in order to leave
     * developer to decide when it is better to call it.
     */
    protected open fun onLoadData() {}

    /**
     * [sendEvent] is reducing the view to a new [UiState] using the redux pattern.
     * If you need to fire one off UI event please reconsider what UI state means for you.
     * https://medium.com/androiddevelopers/viewmodel-one-off-event-antipatterns-16a1da869b95
     * @param event is the UiEvent which is happening
     */
    protected fun sendEvent(event: E) =
        reducer.redux(mutableStateFlow.value, event) { nextState -> setState(nextState) }

    /**
     * [setState] is just setting the new state to the mutable state flow
     */
    private fun setState(newState: S) = mutableStateFlow.tryEmit(newState)
}
