package br.com.impacta.emudetector.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ForgotPasswordButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        TextButton(
            onClick = { },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.Black,
                disabledContentColor = Color.Gray
            ),
            modifier = Modifier
                .background(
                    color = Color.Transparent,
                    shape = MaterialTheme.shapes.small
                )
                .padding(8.dp)
        ) {
            Text("Esqueci minha senha")
        }
    }
}