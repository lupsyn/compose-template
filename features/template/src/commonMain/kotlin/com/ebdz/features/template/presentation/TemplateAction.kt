package com.ebdz.features.template.presentation

import com.ebdz.libraries.core.UiAction

sealed interface TemplateAction : UiAction {
    data object LoadItems : TemplateAction
    data class SelectItem(val itemId: String) : TemplateAction
}
