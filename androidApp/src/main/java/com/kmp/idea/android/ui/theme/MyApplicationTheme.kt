package com.kmp.idea.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors =
        if (darkTheme) {
            darkColorScheme(
                primary = lightBackground,
                background = darkBackground,
                surface = lightBackground,
                onPrimary = darkForeground,
                onSecondary = Color.White,
                onBackground = darkForeground,
                onSurface = darkForeground,
            )
        } else {
            lightColorScheme(
                primary = darkBackground,
                background = lightBackground,
                surface = darkBackground,
                onPrimary = lightBackground,
                onSecondary = Color.Black,
                onBackground = lightForeground,
                onSurface = lightForeground,
            )
        }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = Shapes,
        content = content,
    )
}
