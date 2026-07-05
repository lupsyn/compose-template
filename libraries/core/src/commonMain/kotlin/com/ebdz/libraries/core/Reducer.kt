package com.ebdz.libraries.core

/**
 * [Reducer] represents our Redux pattern
 * {https://redux.js.org/tutorials/fundamentals/part-3-state-actions-reducers}
 *
 * The [redux] fun takes a [UiState] S and a [UiEvent] E and emits the next [UiState]
 * using the high order func onNewState.
 *
 * [UiState] class is Immutable. Reducers must stay pure - no network/database calls, no
 * coroutines, and never mutate [oldState] in place.
 */
interface Reducer<S : UiState, E : UiEvent> {
    fun redux(oldState: S, event: E, onNewState: (S) -> Unit)
}
