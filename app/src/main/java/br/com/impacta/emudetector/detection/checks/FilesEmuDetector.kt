package br.com.impacta.emudetector.detection.checks

import java.io.File

class FilesEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        val emuFileGroups = listOf(
            arrayOf("/dev/socket/genyd", "/dev/socket/baseband_genyd"),
            arrayOf("/dev/socket/qemud", "/dev/qemu_pipe"),
            arrayOf(
                "ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc",
                "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86",
                "init.vbox86.rc", "ueventd.vbox86.rc"
            ),
            arrayOf("fstab.andy", "ueventd.andy.rc"),
            arrayOf("fstab.nox", "init.nox.rc", "ueventd.nox.rc"),
            arrayOf("fstab.bluestacks", "init.bluestacks.rc", "ueventd.bluestacks.rc"),
            arrayOf("fstab.genymotion", "init.genymotion.rc", "ueventd.genymotion.rc")
        )
        return emuFileGroups.any { checkFiles(it) }
    }

    private fun checkFiles(targets: Array<String>): Boolean {
        return targets.any { filePath -> File(filePath).exists() }
    }
}