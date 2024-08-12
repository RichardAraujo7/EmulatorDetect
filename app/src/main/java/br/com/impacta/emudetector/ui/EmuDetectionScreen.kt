package br.com.impacta.emudetector.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.impacta.emudetector.ui.viewmodel.EmuDetectionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmuDetectionScreen(
    viewModel: EmuDetectionViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val detectionResult by viewModel.detectionResult.collectAsState()
    val sensorData by viewModel.sensorData.collectAsState()
    val cameraData by viewModel.cameraData.collectAsState()
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        viewModel.checkForEmu()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Emu Detector") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // Detection Result Card
            val isEmu = (detectionResult?.score ?: 0) > 20
            val cardColor =
                if (isEmu) MaterialTheme.colorScheme.error.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
            val borderColor =
                if (isEmu) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                border = BorderStroke(1.dp, borderColor)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Score: ${detectionResult?.score ?: 0}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = if (isEmu) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Device Type: ${if (isEmu) "Emu" else "Physical Device"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isEmu) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    ResultItem(
                        label = "Has Emu Name",
                        value = detectionResult?.hasEmuName ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Emu Files",
                        value = detectionResult?.hasEmuFiles ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Virtualization Present",
                        value = detectionResult?.isVirtualizationPresent ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Emu Network",
                        value = detectionResult?.isEmuNetwork ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Fake Sensors",
                        value = detectionResult?.hasFakeSensors ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Unsupported Features",
                        value = detectionResult?.hasUnsupportedFeatures ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Cpu Emu",
                        value = detectionResult?.isCpuEmu ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Screen Resolution",
                        value = detectionResult?.isScreenResolutionUncommon ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Suspicious Processes",
                        value = detectionResult?.hasSuspiciousProcesses ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Suspicious App Signatures",
                        value = detectionResult?.hasSuspiciousAppSignatures ?: false,
                        isEmu = isEmu
                    )
                    ResultItem(
                        label = "Libraries Check", // New item
                        value = detectionResult?.hasLibraries ?: false,
                        isEmu = isEmu
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Sensor Data:",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = sensorData ?: "No sensor data available.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Camera Data:",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = cameraData ?: "No camera data available.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun ResultItem(
    label: String,
    value: Boolean,
    isEmu: Boolean
) {
    val textColor =
        if (value) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
    val backgroundColor =
        if (isEmu) MaterialTheme.colorScheme.error.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, textColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "$label: ${if (value) "Yes" else "No"}",
                style = MaterialTheme.typography.bodyMedium,
                color = textColor
            )
        }
    }
}