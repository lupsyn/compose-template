package com.ebdz.compose.presentation.home

import com.ebdz.compose.model.HomeSection

internal data class HomeActions(
    val onSettingsClick: () -> Unit = {},
    val onAboutClick: () -> Unit = {},
    val setCurrentSection: (HomeSection) -> Unit = {}
)
