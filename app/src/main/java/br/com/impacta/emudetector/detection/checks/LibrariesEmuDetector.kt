package br.com.impacta.emudetector.detection.checks

import java.io.File

class LibrariesEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        val emuLibraries = listOf(
            "/system/lib/libhoudini.so", "/system/lib/libc_malloc_debug.so",
            "/system/lib/libdroid4x.so", "/system/lib/libbluestacks.so",
            "/system/lib/libnox.so", "/system/lib/libgenymotion.so",
            "/system/lib/libandyroid.so", "/system/lib/libemulator.so",
            "/system/lib/libOpenglSystemCommon.so", "/system/lib/libGLESv1_CM.so",
            "/system/lib/libGLESv2.so", "/system/lib/libEGL.so"
        )
        return emuLibraries.any { File(it).exists() }
    }
}