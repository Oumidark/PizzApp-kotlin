package com.example.pizzaapp_intelligent.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFB71C1C), // Rouge intense
    secondary = Color(0xFFFFC107), // Jaune pizza
    tertiary = Color(0xFF4CAF50),  // Vert olive
    background = Color(0xFF212121),
    surface = Color(0xFF424242),
    onPrimary = White,
    onSecondary = Color.Black,
    onTertiary = White
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFB71C1C),
    secondary = Color(0xFFFFC107),
    tertiary = Color(0xFF4CAF50),
    background = Color.White,
    surface = Color(0xFFFFF3E0),
    onPrimary = White,
    onSecondary = Color.Black,
    onTertiary = Color.Black
)


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */


@Composable
fun PizzaApp_IntelligentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // You can customize the Typography if needed se// You can customize the Shapes if needed
        content = content
    )
}