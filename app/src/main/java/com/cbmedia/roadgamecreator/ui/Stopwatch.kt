package com.cbmedia.roadgamecreator.ui

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.cbmedia.roadgamecreator.Utils.formatTime
import com.cbmedia.roadgamecreator.viewmodels.GameViewModel

@Composable
fun Stopwatch(vm: GameViewModel) {
    val elapsedSeconds by vm.elapsedTime
    val isRunning by vm.gameInProgress

    // Increment every second while running
    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (true) {
                kotlinx.coroutines.delay(1000L)
                vm.incrementTime()
            }
        }
    }

    Column {
        Text("Time Played: ${formatTime(elapsedSeconds)}")
        Text("Time Played: ${elapsedSeconds}s")
    }
}

