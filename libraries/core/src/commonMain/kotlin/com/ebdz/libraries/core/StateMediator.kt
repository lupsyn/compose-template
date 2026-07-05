package com.ebdz.libraries.core

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * The view-facing side of a ViewModel: observe [uiState], react to one-off [effectsFlow],
 * and send user actions in through [onAction].
 */
interface StateMediator<S : UiState, A : UiAction, F : UiEffect> {

    /** Current UI state; Views collect this to render. */
    val uiState: StateFlow<S>

    /** One-off effects (navigation, toasts). Not replayed to late collectors. */
    val effectsFlow: SharedFlow<F>

    /** Dispatch a user interaction to the ViewModel. */
    fun onAction(action: A)
}
