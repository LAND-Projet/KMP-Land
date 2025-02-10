package com.kmp.idea.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.kmp.idea.core.presentation.ColorsApp

val darkBackground = Color(ColorsApp.DarkBackground)
val darkForeground = Color(ColorsApp.DarkForeground)

val lightBackground = Color(ColorsApp.LightBackground)
val lightForeground = Color(ColorsApp.LightForeground)

val lightBottomTopBarBackground = Color(ColorsApp.LightBottomTopBackground)
val darkBottomTopBarBackground = Color(ColorsApp.DarkBottomTopBackground)

val successColor = Color(ColorsApp.SuccessGreen)
val errorColor = Color(ColorsApp.ErrorRed)
val infoColor = Color(ColorsApp.InfoBlue)
val warningColor = Color(ColorsApp.WarningYellow)
val switchTrackUnchecked = Color(ColorsApp.uncheckedSwitch)

val ColorScheme.buttonFeedbackBackground
    @Composable
    get() = infoColor

val ColorScheme.feedbackBoxBackground
    @Composable
    get() = if (isSystemInDarkTheme()) darkBackground else lightBackground

val ColorScheme.feedbackTextForeground
    @Composable
    get() = if (isSystemInDarkTheme()) darkForeground else lightForeground

val ColorScheme.buttonBackground
    @Composable
    get() = if (isSystemInDarkTheme()) darkBackground else lightBackground

val ColorScheme.buttonContent
    @Composable
    get() = if (isSystemInDarkTheme()) darkForeground else lightForeground

val ColorScheme.iconbuttonBackground
    @Composable
    get() = if (isSystemInDarkTheme()) darkBackground else lightBackground

val ColorScheme.iconbuttonContent
    @Composable
    get() = if (isSystemInDarkTheme()) darkForeground else lightForeground

val ColorScheme.clickableIconContent
    @Composable
    get() = if (isSystemInDarkTheme()) darkForeground else lightForeground

val ColorScheme.textColor
    @Composable
    get() = if (isSystemInDarkTheme()) darkForeground else lightForeground

val ColorScheme.profilContentTop
    @Composable
    get() = if (isSystemInDarkTheme()) darkBackground else lightBackground

val ColorScheme.profilContent
    @Composable
    get() = if (isSystemInDarkTheme()) darkForeground else lightForeground

val ColorScheme.textFieldBackground
    @Composable
    get() = if (isSystemInDarkTheme()) lightBackground else darkForeground

val ColorScheme.textFieldContent
    @Composable
    get() = if (isSystemInDarkTheme()) lightForeground else lightForeground

val ColorScheme.bottomBarBackground
    @Composable
    get() = if (isSystemInDarkTheme()) darkBottomTopBarBackground else lightBottomTopBarBackground

val ColorScheme.topBarBackground
    @Composable
    get() = if (isSystemInDarkTheme()) darkBottomTopBarBackground else lightBottomTopBarBackground

val ColorScheme.popUpBackground
    @Composable
    get() = if (isSystemInDarkTheme()) darkBackground else lightBackground

val ColorScheme.popUpContent
    @Composable
    get() = if (isSystemInDarkTheme()) darkForeground else lightForeground
