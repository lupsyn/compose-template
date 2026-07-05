package com.ebdz.libraries.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Default application typography.
 */
val typography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.Thin,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    )
)
