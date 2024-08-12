package br.com.impacta.emudetector.detection.checks

import android.content.Context

class ScreenResolutionEmuDetector(private val context: Context) : EmuDetectorStrategy {
    override fun getScoreWeight() = 15
    override fun detect(): Boolean {
        val displayMetrics = context.resources.displayMetrics
        val currentResolution = "${displayMetrics.widthPixels}x${displayMetrics.heightPixels}"
        val knownEmulatorResolutions = listOf(
            "1280x800", "1920x1080", "1600x900", "720x1280", "1080x1920", "1440x2560",
            "480x800", "480x854", "540x960", "640x960", "640x1136", "720x1280",
            "768x1024", "768x1280", "800x1280", "1080x1920", "1200x1920", "1440x2560"
        )
        return knownEmulatorResolutions.contains(currentResolution)
    }
}