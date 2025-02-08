package com.kmp.idea.android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kmp.idea.android.R

val poppins_Black = FontFamily(Font(R.font.poppins_black, FontWeight.Black))
val poppins_Bold = FontFamily(Font(R.font.poppins_bold, FontWeight.Bold))
val poppins_Medium = FontFamily(Font(R.font.poppins_medium, FontWeight.Medium))
val poppins_SemiBold = FontFamily(Font(R.font.poppins_semibold, FontWeight.SemiBold))
val poppins_Regular = FontFamily(Font(R.font.poppins_regular, FontWeight.Normal))
val poppins_Italic = FontFamily(Font(R.font.poppins_italic, FontWeight.Normal))
val poppins_Thin = FontFamily(Font(R.font.poppins_thin, FontWeight.Thin))
val poppins_Light = FontFamily(Font(R.font.poppins_light, FontWeight.Light))

val typography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = poppins_Bold,
                fontSize = 25.sp,
            ),
        displayMedium =
            TextStyle(
                fontFamily = poppins_Medium,
                fontSize = 25.sp,
            ),
        displaySmall =
            TextStyle(
                fontFamily = poppins_Regular,
                fontSize = 25.sp,
            ),
        headlineLarge =
            TextStyle(
                fontFamily = poppins_SemiBold,
                fontSize = 20.sp,
            ),
        headlineMedium =
            TextStyle(
                fontFamily = poppins_Medium,
                fontSize = 20.sp,
            ),
        headlineSmall =
            TextStyle(
                fontFamily = poppins_Regular,
                fontSize = 20.sp,
            ),
        titleLarge =
            TextStyle(
                fontFamily = poppins_Bold,
                fontSize = 20.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = poppins_Regular,
                fontSize = 18.sp,
            ),
        titleSmall =
            TextStyle(
                fontFamily = poppins_Light,
                fontSize = 16.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = poppins_Medium,
                fontSize = 14.sp,
            ),
        bodyMedium =
            TextStyle(
                fontFamily = poppins_Regular,
                fontSize = 14.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = poppins_Light,
                fontSize = 12.sp,
            ),
        labelLarge =
            TextStyle(
                fontFamily = poppins_SemiBold,
                fontSize = 16.sp,
            ),
        labelMedium =
            TextStyle(
                fontFamily = poppins_Regular,
                fontSize = 14.sp,
            ),
        labelSmall =
            TextStyle(
                fontFamily = poppins_Light,
                fontSize = 12.sp,
            ),
    )
