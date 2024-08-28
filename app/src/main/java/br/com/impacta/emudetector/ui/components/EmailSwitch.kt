package br.com.impacta.emudetector.ui.components

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.edit

@Composable
fun EmailSwitch(
    rememberEmail: Boolean,
    email: String,
    sharedPreferences: SharedPreferences,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Lembrar meu e-mail", color = Color.Black)
        Spacer(Modifier.weight(1f))
        Switch(
            checked = rememberEmail,
            onCheckedChange = {
                onCheckedChange(it)
                saveEmailPreference(sharedPreferences, email, it)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF0000FF),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFB0B0B0)
            )
        )
    }
}

fun saveEmailPreference(sharedPreferences: SharedPreferences, email: String, rememberEmail: Boolean) {
    sharedPreferences.edit {
        putBoolean("rememberEmail", rememberEmail)
        if (rememberEmail) putString("email", email) else remove("email")
    }
}