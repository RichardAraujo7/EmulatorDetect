package br.com.impacta.emudetector.ui.viewmodel

import android.app.Application
import android.content.Context
import android.hardware.camera2.CameraManager
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.impacta.emudetector.detection.EmuDetector
import br.com.impacta.emudetector.detection.data.EmuDetectionResult
import br.com.impacta.emudetector.sensors.CameraSensor
import br.com.impacta.emudetector.sensors.DevicesSensor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmuDetectionViewModel(application: Application) : AndroidViewModel(application) {

    private val _detectionResult = MutableStateFlow<EmuDetectionResult?>(null)
    val detectionResult: StateFlow<EmuDetectionResult?> get() = _detectionResult

    private val _sensorData = MutableStateFlow<String?>(null)
    val sensorData: StateFlow<String?> get() = _sensorData

    private val _cameraData = MutableStateFlow<String?>(null)
    val cameraData: StateFlow<String?> get() = _cameraData

    private val emuDetector = EmuDetector(getApplication())

    fun checkForEmu() {
        viewModelScope.launch {
            try {
                val result = emuDetector.detect()
                _detectionResult.value = result

                val sensorDataString = fetchSensorData()
                _sensorData.value = sensorDataString

                val cameraDataString = fetchCameraData()
                _cameraData.value = cameraDataString
            } catch (_: Exception) {
            }
        }
    }

    private fun fetchSensorData(): String {
        val sensorManager = getApplication<Application>().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        return DevicesSensor.getSensorData(sensorManager).toString()
    }

    private fun fetchCameraData(): String {
        val cameraManager = getApplication<Application>().getSystemService(Context.CAMERA_SERVICE) as CameraManager
        return CameraSensor.getCameraData(cameraManager).toString()
    }
}
