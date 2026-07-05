package com.ebdz.features.template.presentation

import com.ebdz.domain.model.TemplateItem
import com.ebdz.libraries.core.UiEvent

sealed interface TemplateEvent : UiEvent {
    data object LoadingStarted : TemplateEvent
    data class ItemsLoaded(val items: List<TemplateItem>) : TemplateEvent
    data class LoadFailed(val error: String) : TemplateEvent
}
