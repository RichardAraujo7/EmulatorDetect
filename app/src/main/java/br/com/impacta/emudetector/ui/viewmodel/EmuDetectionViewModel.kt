package br.com.impacta.emudetector.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.impacta.emudetector.detection.EmuDetector
import br.com.impacta.emudetector.detection.data.EmuDetectionResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmuDetectionViewModel(application: Application) : AndroidViewModel(application) {

    private val _detectionResult = MutableStateFlow<Result<EmuDetectionResult?>>(Result.success(null))
    val detectionResult: StateFlow<Result<EmuDetectionResult?>> get() = _detectionResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val emuDetector = EmuDetector(getApplication())

    fun checkForEmu() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = emuDetector.detect()
                _detectionResult.value = Result.success(result)
            } catch (e: Exception) {
                _detectionResult.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}