package br.com.impacta.emudetector.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.impacta.emudetector.detection.data.EmuDetectionResult
import br.com.impacta.emudetector.ui.components.EmailSwitch
import br.com.impacta.emudetector.ui.components.EmailTextField
import br.com.impacta.emudetector.ui.components.ForgotPasswordButton
import br.com.impacta.emudetector.ui.components.LoadingIndicator
import br.com.impacta.emudetector.ui.components.LoginButton
import br.com.impacta.emudetector.ui.components.PasswordTextField
import br.com.impacta.emudetector.ui.components.saveEmailPreference
import br.com.impacta.emudetector.ui.viewmodel.EmuDetectionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: EmuDetectionViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)

    var email by remember { mutableStateOf(sharedPreferences.getString("email", "") ?: "") }
    var password by remember { mutableStateOf("") }
    var rememberEmail by remember { mutableStateOf(sharedPreferences.getBoolean("rememberEmail", false)) }
    val isLoading by viewModel.isLoading.collectAsState()
    val detectionResultState by viewModel.detectionResult.collectAsState()

    val isLoginButtonEnabled = email.isNotBlank() && password.length == 6

    LaunchedEffect(detectionResultState) {
        val result = handleDetectionResult(detectionResultState)
        if (result != null) {
            if ((detectionResultState.getOrNull()?.score ?: 0) > 20) {
                navController.navigate("error")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Impacta", color = Color.White) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color(0xFF0000FF))
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            EmailTextField(email) { newEmail -> email = newEmail }
            EmailSwitch(
                rememberEmail,
                email,
                sharedPreferences
            ) { newRememberEmail ->
                rememberEmail = newRememberEmail
                saveEmailPreference(sharedPreferences, email, rememberEmail)
            }
            PasswordTextField(password) { newPassword -> password = newPassword }
            ForgotPasswordButton()
            Spacer(modifier = Modifier.weight(1f))
            if (isLoading) {
                LoadingIndicator()
            } else {
                LoginButton(
                    onClick = {
                        if (!isLoginButtonEnabled) {
                            Toast.makeText(context, "Dados de login inválidos.", Toast.LENGTH_LONG).show()
                            return@LoginButton
                        }
                        viewModel.checkForEmu()
                    },
                    enabled = isLoginButtonEnabled
                )
            }
        }
    }
}

private fun handleDetectionResult(detectionResultState: Result<EmuDetectionResult?>): String? {
    return when {
        detectionResultState.isFailure -> "Erro ao verificar o emulador."
        (detectionResultState.getOrNull()?.score ?: 0) > 20 -> "Emulador detectado. Não é possível prosseguir com o login."
        else -> null
    }
}