package com.ebdz.libraries.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Enum to represent the sections available in the bottom app bar.
 *
 * @param title title to be shown in top app bar.
 * @param icon icon to be shown in the bottom app bar
 */
enum class BottomNavSections(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector,
) {
    Home(
        route = Destinations.Home,
        title = R.string.home_title,
        icon = Icons.Outlined.Home
    ),

    Settings(
        route = Destinations.Settings,
        title = R.string.home_title_settings,
        icon = Icons.Outlined.Settings
    )
}
