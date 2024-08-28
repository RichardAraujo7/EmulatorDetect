package br.com.impacta.emudetector.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF0000FF), // Azul para o tema escuro
    secondary = Color(0xFFFFFFFF), // Branco para o tema escuro
    background = Color(0xFF000000), // Preto para o fundo do tema escuro
    surface = Color(0xFF333333) // Cinza escuro para superfícies do tema escuro
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0000FF), // Azul para o tema claro
    secondary = Color(0xFFFFFFFF), // Branco para o tema claro
    background = Color(0xFFFFFFFF), // Branco para o fundo do tema claro
    surface = Color(0xFFF5F5F5) // Cinza claro para superfícies do tema claro
)

@Composable
fun EmuDetectorTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
)
