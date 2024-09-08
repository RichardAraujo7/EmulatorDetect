package br.com.impacta.emudetector.detection.checks

import android.util.Log
import java.net.NetworkInterface

class NetworkEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30

    override fun detect(): Boolean {
        return try {
            val suspiciousNetworkInterfaces = listOf(
                "tun0", "ppp0", "eth0", "rmnet0", "rndis0", "usb0", "wifi0" // "wlan0" removido
            )
            val detectedInterfaces = NetworkInterface.getNetworkInterfaces().asSequence()
                .filter { networkInterface -> suspiciousNetworkInterfaces.contains(networkInterface.name) }
                .map { it.name }
                .toList()

            if (detectedInterfaces.isNotEmpty()) {
                Log.d("Classes Detectadas", "Interfaces suspeitas detectadas: $detectedInterfaces")
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}