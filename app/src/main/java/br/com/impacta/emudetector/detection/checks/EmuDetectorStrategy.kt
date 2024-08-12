package br.com.impacta.emudetector.detection.checks

interface EmuDetectorStrategy {
    fun getScoreWeight(): Int
    fun detect(): Boolean
}