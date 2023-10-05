package com.ebdz.libraries.core

import kotlinx.coroutines.flow.StateFlow

/**
 * [StateMediator] is abstraction over [BaseViewModel] in order to have a more
 * tight contract between view <-> business logic
 */
interface StateMediator<S : UiState, A : UiAction> {
    fun getUiState(): StateFlow<S>

    fun onAction(action: A)
}
