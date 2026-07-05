package com.ebdz.features.template.presentation

import androidx.lifecycle.viewModelScope
import com.ebdz.domain.usecase.GetTemplateItemsUseCase
import com.ebdz.libraries.core.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Skeleton feature ViewModel. Copy this shape (Action/Event/Effect/State/Reducer/ViewModel)
 * when starting a real feature from this template.
 */
class TemplateViewModel(
    private val getTemplateItemsUseCase: GetTemplateItemsUseCase,
    reducer: TemplateReducer
) : BaseViewModel<TemplateState, TemplateEvent, TemplateAction, TemplateEffect>(
    initialState = TemplateState(),
    reducer = reducer
) {

    override fun onAction(action: TemplateAction) {
        when (action) {
            TemplateAction.LoadItems -> loadItems()
            is TemplateAction.SelectItem -> selectItem(action.itemId)
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            sendEvent(TemplateEvent.LoadingStarted)

            getTemplateItemsUseCase().fold(
                onSuccess = { items -> sendEvent(TemplateEvent.ItemsLoaded(items)) },
                onFailure = { error ->
                    sendEvent(TemplateEvent.LoadFailed(error.message ?: "Failed to load items"))
                }
            )
        }
    }

    private fun selectItem(itemId: String) {
        sendEffect(TemplateEffect.ShowMessage("Selected item $itemId"))
    }
}
