package br.com.impacta.emudetector.sensors

import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.hardware.camera2.params.BlackLevelPattern
import android.util.Range

object CameraSensor {
    private const val EOL = "\n"

    private fun blackLevelPatternToString(pattern: BlackLevelPattern): String {
        val patternArray = IntArray(4)
        pattern.copyTo(patternArray, 0)
        return patternArray.contentToString()
    }

    fun getCameraData(cameraManager: CameraManager): StringBuilder {
        val sb = StringBuilder()
        try {
            for (cameraId in cameraManager.cameraIdList) {
                val characteristics = cameraManager.getCameraCharacteristics(cameraId)
                sb.append("Camera ID: ").append(cameraId).append(EOL)
                sb.append("Lens Focal Lengths: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS))).append(EOL)
                sb.append("Lens Apertures: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES))).append(EOL)
                sb.append("Sensor Size: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE))).append(EOL)
                sb.append("Noise Reduction Modes: ").append(safeToString(characteristics.get(CameraCharacteristics.NOISE_REDUCTION_AVAILABLE_NOISE_REDUCTION_MODES))).append(EOL)
                sb.append("Available Apertures: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES))).append(EOL)
                sb.append("Available Focal Lengths: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS))).append(EOL)
                sb.append("Available Filter Densities: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FILTER_DENSITIES))).append(EOL)
                sb.append("Available Optical Stabilization: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION))).append(EOL)
                sb.append("Hyperfocal Distance: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_HYPERFOCAL_DISTANCE))).append(EOL)
                sb.append("Minimum Focus Distance: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE))).append(EOL)
                sb.append("Exposure Time Range: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_INFO_EXPOSURE_TIME_RANGE))).append(EOL)
                sb.append("ISO Range: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_INFO_SENSITIVITY_RANGE))).append(EOL)
                sb.append("Color Filter Arrangement: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT))).append(EOL)
                sb.append("Lens Intrinsic Calibration Parameters: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INTRINSIC_CALIBRATION))).append(EOL)
                sb.append("Lens Radial Distortion Parameters: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_RADIAL_DISTORTION))).append(EOL)
                sb.append("Sensor Orientation: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_ORIENTATION))).append(" degrees").append(EOL)
                sb.append("Sensor Max Readout Speed: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_MAX_ANALOG_SENSITIVITY))).append(EOL)
                sb.append("Flash Available: ").append(safeToString(characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE))).append(EOL)
                sb.append("Focal Plane X Resolution: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE)?.width)).append(EOL)
                sb.append("Focal Plane Y Resolution: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE)?.height)).append(EOL)
                sb.append("Available AF Modes: ").append(safeToString(characteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES))).append(EOL)
                sb.append("Maximum Number of Detectable Faces: ").append(safeToString(characteristics.get(CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT))).append(EOL)
                sb.append("Supported Color Spaces: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT))).append(EOL)
                sb.append("Physical Size: ").append(safeToString(characteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE))).append(EOL)
                sb.append("Optical Stabilization: ").append(safeToString(characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_OPTICAL_STABILIZATION))).append(EOL)
                sb.append("Hardware Level: ").append(translateHardwareLevel(characteristics.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL))).append(EOL)
                sb.append("----------").append(EOL)
            }
        } catch (e: CameraAccessException) {
            sb.append("Error accessing camera: ").append(e.localizedMessage).append(EOL)
        } catch (e: Exception) {
            sb.append("Unexpected error: ").append(e.localizedMessage).append(EOL)
        }
        return sb
    }

    private fun translateHardwareLevel(hardwareLevel: Int?): String {
        return when (hardwareLevel) {
            CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_3 -> "Level 3"
            CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_FULL -> "Full"
            CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY -> "Legacy"
            CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LIMITED -> "Limited"
            else -> "Unknown"
        }
    }

    private fun safeToString(value: Any?): String {
        return when (value) {
            null -> "null"
            is FloatArray -> value.joinToString(prefix = "[", postfix = "]")
            is IntArray -> value.joinToString(prefix = "[", postfix = "]")
            is Range<*> -> if (value.lower is Long || value.lower is Int) {
                "[${value.lower}, ${value.upper}]"
            } else {
                "Unsupported Range type"
            }
            is Array<*> -> value.joinToString(prefix = "[", postfix = "]")
            is BlackLevelPattern -> blackLevelPatternToString(value)
            else -> value.toString()
        }
    }
}
