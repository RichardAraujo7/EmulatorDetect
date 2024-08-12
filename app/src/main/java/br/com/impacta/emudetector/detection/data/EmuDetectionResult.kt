package br.com.impacta.emudetector.detection.data

data class EmuDetectionResult(
    val hasEmuName: Boolean,
    val hasEmuFiles: Boolean,
    val isVirtualizationPresent: Boolean,
    val isEmuNetwork: Boolean,
    val hasFakeSensors: Boolean,
    val hasUnsupportedFeatures: Boolean,
    val isCpuEmu: Boolean,
    val isScreenResolutionUncommon: Boolean,
    val hasSuspiciousProcesses: Boolean,
    val hasSuspiciousAppSignatures: Boolean,
    val hasLibraries: Boolean,
    val score: Int
)
