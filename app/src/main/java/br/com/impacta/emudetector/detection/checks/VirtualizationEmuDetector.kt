package br.com.impacta.emudetector.detection.checks

import java.io.File

class VirtualizationEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        return try {
            val virtualizedIndicators = listOf(
                "vbox", "vmware", "qemu", "xen", "hyperv", "android-x86",
                "intel", "amd", "arm"
            )
            val cpuInfo = File("/proc/cpuinfo").readText().lowercase()
            virtualizedIndicators.any { cpuInfo.contains(it) }
        } catch (e: Exception) {
            false
        }
    }
}