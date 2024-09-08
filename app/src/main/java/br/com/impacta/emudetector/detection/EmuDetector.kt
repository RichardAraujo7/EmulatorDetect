package br.com.impacta.emudetector.detection

import android.content.Context
import android.util.Log
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
        val results = detectionStrategies.associate { strategy ->
            strategy::class.simpleName to strategy.detect()
        }

        var totalScore = 0
        val contributingStrategies = mutableListOf<String>()

        detectionStrategies.filter { strategy ->
            results[strategy::class.simpleName] == true
        }.forEach { strategy ->
            totalScore += strategy.getScoreWeight()
            contributingStrategies.add(strategy::class.simpleName ?: "Unknown")
        }

        Log.d("Classes Detectadas", "Classes que aumentaram o totalScore: ${contributingStrategies.joinToString(", ")}")

        return EmuDetectionResult(
            hasEmuName = results[BuildConfigEmuDetector::class.simpleName] ?: false,
            hasEmuFiles = results[FilesEmuDetector::class.simpleName] ?: false,
            isVirtualizationPresent = results[VirtualizationEmuDetector::class.simpleName] ?: false,
            isEmuNetwork = results[NetworkEmuDetector::class.simpleName] ?: false,
            hasFakeSensors = results[SensorEmuDetector::class.simpleName] ?: false,
            hasUnsupportedFeatures = results[SystemPropertiesEmuDetector::class.simpleName] ?: false,
            isCpuEmu = results[CpuModelEmuDetector::class.simpleName] ?: false,
            isScreenResolutionUncommon = results[ScreenResolutionEmuDetector::class.simpleName] ?: false,
            hasSuspiciousProcesses = results[BackgroundProcessesEmuDetector::class.simpleName] ?: false,
            hasSuspiciousAppSignatures = results[AppSignaturesEmuDetector::class.simpleName] ?: false,
            hasLibraries = results[LibrariesEmuDetector::class.simpleName] ?: false,
            score = totalScore
        )
    }
}
