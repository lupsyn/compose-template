package com.ebdz.features.template.presentation

import com.ebdz.libraries.core.UiEffect

sealed interface TemplateEffect : UiEffect {
    data class ShowMessage(val message: String) : TemplateEffect
}
