package br.com.impacta.emudetector.ui.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.impacta.emudetector.ui.components.*
import br.com.impacta.emudetector.ui.theme.EmuDetectorTheme
import br.com.impacta.emudetector.ui.viewmodel.EmuDetectionViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmuDetectorTheme {
                LoginScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: EmuDetectionViewModel = viewModel()
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)

    // State management
    var email by remember { mutableStateOf(sharedPreferences.getString("email", "") ?: "") }
    var password by remember { mutableStateOf("") }
    var rememberEmail by remember { mutableStateOf(sharedPreferences.getBoolean("rememberEmail", false)) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()
    val detectionResultState by viewModel.detectionResult.collectAsState()

    val isLoginButtonEnabled = email.isNotBlank() && password.length == 6

    LaunchedEffect(detectionResultState) {
        errorMessage = when {
            detectionResultState.isFailure -> "Erro ao verificar o emulador."
            (detectionResultState.getOrNull()?.score ?: 0) > 20 -> "Emulador detectado. Não é possível prosseguir com o login."
            else -> null
        }
    }

    if (errorMessage != null) {
        ErrorScreen(
            errorMessage = errorMessage!!,
            onRetry = { errorMessage = null }
        )
    } else {
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
                                errorMessage = "Dados de login inválidos."
                                return@LoginButton
                            }
                            isLoading = true
                            coroutineScope.launch {
                                handleLogin(
                                    viewModel = viewModel,
                                    email = email,
                                    password = password,
                                    onError = { message ->
                                        errorMessage = message
                                        isLoading = false
                                    },
                                    onSuccess = {
                                        Toast.makeText(context, "Login bem-sucedido", Toast.LENGTH_LONG).show()
                                        isLoading = false
                                    }
                                )
                            }
                        },
                        enabled = isLoginButtonEnabled
                    )
                }
            }
        }
    }
}

private fun saveEmailPreference(
    sharedPreferences: SharedPreferences,
    email: String,
    rememberEmail: Boolean
) {
    sharedPreferences.edit {
        putBoolean("rememberEmail", rememberEmail)
        if (rememberEmail) putString("email", email) else remove("email")
    }
}

private fun handleLogin(
    viewModel: EmuDetectionViewModel,
    email: String,
    password: String,
    onError: (String) -> Unit,
    onSuccess: () -> Unit
) {
    viewModel.checkForEmu()
    val detectionResult = viewModel.detectionResult.value

    when {
        detectionResult.isFailure -> onError("Erro ao verificar o emulador.")
        (detectionResult.getOrNull()?.score ?: 0) > 20 -> onError("Emulador detectado. Não é possível prosseguir com o login.")
        else -> authenticateWithFirebase(email, password, onSuccess, onError)
    }
}

private fun authenticateWithFirebase(
    email: String,
    password: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onError("Falha na autenticação.")
            }
        }
}