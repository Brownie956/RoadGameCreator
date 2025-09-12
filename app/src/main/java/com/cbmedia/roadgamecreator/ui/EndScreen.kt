package com.cbmedia.roadgamecreator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EndScreen(finalScore: Int, onBackToStart: () -> Unit, onViewHighScores: () -> Unit) {
    Column(
        modifier = Modifier.Companion.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Game Over", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.Companion.height(8.dp))
        Text("Final Score: $finalScore", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.Companion.height(16.dp))
        Button(onClick = onViewHighScores) { Text("View High Scores") }
        Spacer(Modifier.Companion.height(8.dp))
        Button(onClick = onBackToStart) { Text("Back to Start") }
    }
}