package com.ebdz.compose.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.ebdz.compose.R

/**
 * Enum to represent the sections available in the bottom app bar.
 *
 * @param title title to be shown in top app bar.
 * @param icon icon to be shown in the bottom app bar
 */
enum class HomeSection(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    Home(R.string.home_title, Icons.Outlined.Home),
    Settings(R.string.home_title_settings, Icons.Outlined.Settings)
}
