package com.ebdz.features.template.presentation

import com.ebdz.domain.model.TemplateItem
import com.ebdz.libraries.core.UiState

data class TemplateState(
    val items: List<TemplateItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState
