package com.ebdz.libraries.core

/**
 * [Reducer] represents our Redux pattern
 * {https://redux.js.org/tutorials/fundamentals/part-3-state-actions-reducers}
 *
 * The [redux] fun takes an [UiState] S and an [UiEvent] E and emit the next [UiState]
 * using the high order func onNewState.
 *
 * [UiState] class is Immutable.
 */
interface Reducer<S : UiState, E : UiEvent> {
    fun redux(oldState: S, event: E, onNewState: (S) -> Unit)
}
