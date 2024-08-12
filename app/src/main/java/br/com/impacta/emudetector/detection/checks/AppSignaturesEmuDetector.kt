package br.com.impacta.emudetector.detection.checks

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi

class AppSignaturesEmuDetector(private val context: Context) : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    @RequiresApi(Build.VERSION_CODES.P)
    override fun detect(): Boolean {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(
                context.packageName, PackageManager.GET_SIGNING_CERTIFICATES
            )
            val knownEmuSignatures = listOf(
                "com.genymotion", "com.bluestacks", "com.nox", "com.droid4x",
                "com.andyroid", "com.vmware", "com.qemu", "com.xen", "com.microsoft",
                "google", "android", "intel"
            )
            packageInfo.signingInfo.apkContentsSigners.any { signature ->
                knownEmuSignatures.any { signature.toCharsString().contains(it, ignoreCase = true) }
            }
        } catch (e: Exception) {
            false
        }
    }
}