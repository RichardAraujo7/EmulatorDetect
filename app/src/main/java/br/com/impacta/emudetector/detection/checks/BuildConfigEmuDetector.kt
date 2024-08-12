package br.com.impacta.emudetector.detection.checks

import android.os.Build

class BuildConfigEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        val indicators = listOf(
            Build.MANUFACTURER.contains("Genymotion"),
            Build.MODEL.contains("google_sdk"),
            Build.MODEL.lowercase().contains("droid4x"),
            Build.MODEL.contains("Emulator"),
            Build.MODEL.contains("Android SDK built for x86"),
            Build.HARDWARE in listOf("goldfish", "vbox86") || Build.HARDWARE.lowercase()
                .contains("nox"),
            Build.FINGERPRINT.startsWith("generic"),
            Build.PRODUCT in listOf(
                "sdk",
                "google_sdk",
                "sdk_x86",
                "vbox86p"
            ) || Build.PRODUCT.lowercase().contains("nox"),
            Build.BOARD.lowercase().contains("nox"),
            Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
        )
        return indicators.any { it }
    }
}