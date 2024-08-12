package br.com.impacta.emudetector.detection.checks

import java.io.File

class CpuModelEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        val cpuInfo = File("/proc/cpuinfo").readText().lowercase()
        val emuCpuModels = listOf("generic", "unknown", "x86")
        return emuCpuModels.any { cpuInfo.contains(it) }
    }
}