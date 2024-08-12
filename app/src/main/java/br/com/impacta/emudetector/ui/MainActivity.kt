package br.com.impacta.emudetector.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.impacta.emudetector.ui.theme.EmuDetectorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmuDetectorTheme {
                EmuDetectionScreen()
            }
        }
    }
}