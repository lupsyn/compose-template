package com.ebdz.features.template.presentation

import com.ebdz.libraries.core.Reducer

class TemplateReducer : Reducer<TemplateState, TemplateEvent> {

    override fun redux(
        oldState: TemplateState,
        event: TemplateEvent,
        onNewState: (TemplateState) -> Unit
    ) {
        val newState = when (event) {
            TemplateEvent.LoadingStarted -> oldState.copy(isLoading = true, error = null)

            is TemplateEvent.ItemsLoaded -> oldState.copy(
                isLoading = false,
                items = event.items,
                error = null
            )

            is TemplateEvent.LoadFailed -> oldState.copy(isLoading = false, error = event.error)
        }

        onNewState(newState)
    }
}
