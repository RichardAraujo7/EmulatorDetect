package br.com.impacta.emudetector.detection.checks

import java.net.NetworkInterface

class NetworkEmuDetector : EmuDetectorStrategy {
    override fun getScoreWeight() = 30
    override fun detect(): Boolean {
        return try {
            val suspiciousNetworkInterfaces = listOf(
                "tun0", "ppp0", "wlan0", "eth0", "rmnet0", "rndis0", "usb0", "wifi0"
            )
            NetworkInterface.getNetworkInterfaces().asSequence().any { networkInterface ->
                suspiciousNetworkInterfaces.contains(networkInterface.name)
            }
        } catch (e: Exception) {
            false
        }
    }
}