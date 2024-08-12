package br.com.impacta.emudetector.detection.checks

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager

class SensorEmuDetector(context: Context) : EmuDetectorStrategy {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        val knownFakeSensors = listOf(
            "FAKE_ACCELEROMETER", "FAKE_GYROSCOPE", "FAKE_LIGHT",
            "FAKE_PROXIMITY", "goldfish"
        )
        return sensors.any { sensor -> knownFakeSensors.contains(sensor.name) }
    }
}