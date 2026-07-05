package com.ebdz.libraries.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = blue700,
    secondary = pink300,
    onSecondary = blueGray400
)

private val LightColorScheme = lightColorScheme(
    primary = blue700,
    secondary = pink300,
    onSecondary = blueGray400
)

/**
 * Main theme.
 *
 * @param darkTheme indicates if the application is in dark theme mode.
 * @param content composable function
 */
@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
