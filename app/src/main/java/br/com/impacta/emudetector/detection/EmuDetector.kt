package br.com.impacta.emudetector.detection

import android.content.Context
import br.com.impacta.emudetector.detection.checks.AppSignaturesEmuDetector
import br.com.impacta.emudetector.detection.checks.BackgroundProcessesEmuDetector
import br.com.impacta.emudetector.detection.checks.BuildConfigEmuDetector
import br.com.impacta.emudetector.detection.checks.CpuModelEmuDetector
import br.com.impacta.emudetector.detection.checks.EmuDetectorStrategy
import br.com.impacta.emudetector.detection.checks.FilesEmuDetector
import br.com.impacta.emudetector.detection.checks.LibrariesEmuDetector
import br.com.impacta.emudetector.detection.checks.NetworkEmuDetector
import br.com.impacta.emudetector.detection.checks.ScreenResolutionEmuDetector
import br.com.impacta.emudetector.detection.checks.SensorEmuDetector
import br.com.impacta.emudetector.detection.checks.SystemPropertiesEmuDetector
import br.com.impacta.emudetector.detection.checks.VirtualizationEmuDetector
import br.com.impacta.emudetector.detection.data.EmuDetectionResult

class EmuDetector(context: Context) {
    private val detectionStrategies: List<EmuDetectorStrategy> = listOf(
        BuildConfigEmuDetector(),
        FilesEmuDetector(),
        VirtualizationEmuDetector(),
        NetworkEmuDetector(),
        SensorEmuDetector(context),
        SystemPropertiesEmuDetector(),
        CpuModelEmuDetector(),
        ScreenResolutionEmuDetector(context),
        BackgroundProcessesEmuDetector(),
        AppSignaturesEmuDetector(context),
        LibrariesEmuDetector()
    )

    fun detect(): EmuDetectionResult {
        var totalScore = 0
        val detectionResults = mutableMapOf<String, Boolean>()
        detectionStrategies.forEach { strategy ->
            val hasEmu = strategy.detect()
            totalScore += if (hasEmu) strategy.getScoreWeight() else 0
            detectionResults[strategy::class.simpleName ?: "Unknown"] = hasEmu
        }

        return EmuDetectionResult(
            hasEmuName = detectionResults[BuildConfigEmuDetector::class.simpleName] ?: false,
            hasEmuFiles = detectionResults[FilesEmuDetector::class.simpleName] ?: false,
            isVirtualizationPresent = detectionResults[VirtualizationEmuDetector::class.simpleName] ?: false,
            isEmuNetwork = detectionResults[NetworkEmuDetector::class.simpleName] ?: false,
            hasFakeSensors = detectionResults[SensorEmuDetector::class.simpleName] ?: false,
            hasUnsupportedFeatures = detectionResults[SystemPropertiesEmuDetector::class.simpleName] ?: false,
            isCpuEmu = detectionResults[CpuModelEmuDetector::class.simpleName] ?: false,
            isScreenResolutionUncommon = detectionResults[ScreenResolutionEmuDetector::class.simpleName] ?: false,
            hasSuspiciousProcesses = detectionResults[BackgroundProcessesEmuDetector::class.simpleName] ?: false,
            hasSuspiciousAppSignatures = (detectionResults[AppSignaturesEmuDetector::class.simpleName] ?: false),
            hasLibraries = detectionResults[LibrariesEmuDetector::class.simpleName] ?: false,
            score = totalScore
        )
    }
}
