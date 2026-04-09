package com.ucc.itmobileapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = UCCBlue,
    secondary = UCCYellow,
    tertiary = UCCLightBlue
)

private val LightColorScheme = lightColorScheme(
    primary = UCCBlue,
    onPrimary = Color.White,
    secondary = UCCYellow,
    onSecondary = Color.Black,
    tertiary = UCCLightBlue,
    surface = Color.White,
    onSurface = Color.Black
)

@Composable
fun UCCITMobileAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}