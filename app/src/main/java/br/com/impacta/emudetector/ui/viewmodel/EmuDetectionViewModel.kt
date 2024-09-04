package br.com.impacta.emudetector.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.impacta.emudetector.detection.EmuDetector
import br.com.impacta.emudetector.detection.data.EmuDetectionResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmuDetectionViewModel(application: Application) : AndroidViewModel(application) {

    private val _detectionResult =
        MutableStateFlow<Result<EmuDetectionResult?>>(Result.success(null))
    private val detectionResult: StateFlow<Result<EmuDetectionResult?>> get() = _detectionResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val emuDetector = EmuDetector(getApplication())

    fun checkForEmu(
        email: String,
        password: String,
        navController: NavController,
        auth: FirebaseAuth
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = emuDetector.detect()
                _detectionResult.value = Result.success(result)
                val score = detectionResult.value.getOrNull()?.score ?: 0
                if (score <= 20) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate("menu")
                            } else {
                                navController.navigate("error/authentication")
                            }
                        }
                } else {
                    navController.navigate("error/emulator")
                }
            } catch (e: Exception) {
                _detectionResult.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}