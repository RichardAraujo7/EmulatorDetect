package br.com.impacta.emudetector.detection.checks

class BackgroundProcessEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        val processList = try {
            Runtime.getRuntime().exec("ps").inputStream.bufferedReader().readText()
        } catch (e: Exception) {
            ""
        }
        val suspiciousProcesses = listOf(
            "emulator", "qemu", "androVM", "vbox86", "nox", "bluestacks",
            "genymotion", "andy", "x86", "houdini", "tun0", "eth0"
        )
        return suspiciousProcesses.any { processList.contains(it, ignoreCase = true) }
    }
}