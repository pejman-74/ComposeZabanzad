package com.composezabanzad.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorPalette = lightColors(
    primary = lightOrange,
    primaryVariant = orange,
    secondary = lightPurple,
    secondaryVariant = purple,
    background = lightYellow,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.Black,
)

@Composable
fun ComposeZabanzadTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        content = content
    )
}