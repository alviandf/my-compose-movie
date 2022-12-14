package com.alviandf.mycomposemovie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Black100,
    primaryVariant = Black100,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = Black100,
    primaryVariant = Black100,
    secondary = Teal200,
)

@Composable
fun MyComposeMovieTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = DarkColorPalette.primaryVariant
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = LightColorPalette.primaryVariant
        )
    }
}