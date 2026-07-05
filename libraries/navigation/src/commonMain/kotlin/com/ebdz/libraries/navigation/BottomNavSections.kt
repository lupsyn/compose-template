package com.ebdz.libraries.navigation

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
    val title: String,
    val icon: ImageVector
) {
    Home(
        route = Destinations.Home,
        title = "Home",
        icon = Icons.Outlined.Home
    ),

    Settings(
        route = Destinations.Settings,
        title = "Settings",
        icon = Icons.Outlined.Settings
    )
}
