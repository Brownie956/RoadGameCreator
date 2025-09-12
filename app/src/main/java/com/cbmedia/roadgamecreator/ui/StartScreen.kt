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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(onStart: () -> Unit, onViewHighScores: () -> Unit) {
    Column(
        modifier = Modifier.Companion.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        Text(
            "Create-Your-Own-Adventure",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Companion.Bold
        )
        Spacer(Modifier.Companion.height(24.dp))
        Button(onClick = onStart) { Text("Start Game") }
        Spacer(Modifier.Companion.height(8.dp))
        Button(onClick = onViewHighScores) { Text("High Scores") }
    }
}