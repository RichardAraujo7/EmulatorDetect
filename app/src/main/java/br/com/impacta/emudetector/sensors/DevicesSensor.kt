package br.com.impacta.emudetector.sensors

import android.hardware.Sensor
import android.hardware.SensorManager

object DevicesSensor {
    fun getSensorData(sensorManager: SensorManager): StringBuilder {
        val sb = StringBuilder()
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)
        sb.append("Available sensors:\n")
        if (sensorList.isEmpty()) {
            sb.append("No sensors detected, likely an emulator.\n")
        } else {
            for (sensor in sensorList) {
                sb.append("Sensor name: ").append(sensor.name).append("\n")
                sb.append("Type: ").append(sensor.type).append("\n")
                sb.append("Vendor: ").append(sensor.vendor).append("\n")
                sb.append("Version: ").append(sensor.version).append("\n")
                sb.append("Resolution: ").append(sensor.resolution).append("\n")
                sb.append("Max Range: ").append(sensor.maximumRange).append("\n")
                sb.append("----------\n")
            }
        }
        return sb
    }
}
