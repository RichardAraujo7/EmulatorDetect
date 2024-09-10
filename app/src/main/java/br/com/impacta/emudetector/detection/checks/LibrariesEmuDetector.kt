package br.com.impacta.emudetector.detection.checks

import android.util.Log
import java.io.File

class LibrariesEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        val emuLibraries = listOf(
            "/system/lib/libhoudini.so",
            "/system/lib/libdroid4x.so", "/system/lib/libbluestacks.so",
            "/system/lib/libnox.so", "/system/lib/libgenymotion.so",
            "/system/lib/libandyroid.so", "/system/lib/libemulator.so",
            "/system/lib/libOpenglSystemCommon.so"
        )
        val detectedLibraries = emuLibraries.filter { File(it).exists() }
        return if (detectedLibraries.isNotEmpty()) {
            Log.d("Classes Detectadas", "Bibliotecas suspeitas detectadas: $detectedLibraries")
            true
        } else {
            false
        }
    }
}