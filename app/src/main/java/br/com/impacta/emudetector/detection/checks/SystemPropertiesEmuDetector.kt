package br.com.impacta.emudetector.detection.checks

import br.com.impacta.emudetector.system.SystemProperties

class SystemPropertiesEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        val properties = mapOf(
            "ro.product.model" to "unknown",
            "ro.product.device" to "generic",
            "ro.product.brand" to "generic"
        )
        return properties.any { (key, value) -> SystemProperties.getProp(key) == value }
    }
}