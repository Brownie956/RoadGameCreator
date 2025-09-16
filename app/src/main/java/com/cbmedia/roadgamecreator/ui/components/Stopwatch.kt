package com.cbmedia.roadgamecreator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.cbmedia.roadgamecreator.Utils

@Composable
fun Stopwatch(
    elapsedSeconds: Int,
) {
    Column {
        Text("Time Played: ${Utils.formatTime(elapsedSeconds)}")
        Text("Time Played: ${elapsedSeconds}s")
    }
}
