package com.cbmedia.roadgamecreator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cbmedia.roadgamecreator.persistence.clearHighScores
import com.cbmedia.roadgamecreator.persistence.loadHighScores

@Composable
fun HighScoresScreen(onBack: () -> Unit) {
    val ctx = LocalContext.current
    var scores by remember { mutableStateOf(loadHighScores(ctx)) }
    var showDialog by remember { mutableStateOf(false) }


    Column(modifier = Modifier.Companion.fillMaxSize().padding(16.dp)) {
        Text("High Scores", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.Companion.height(12.dp))
        if (scores.isEmpty()) {
            Text("No scores yet")
        } else {
            LazyColumn {
                items(scores) { s ->
                    Card(modifier = Modifier.Companion.fillMaxWidth().padding(vertical = 4.dp)) {
                        Row(
                            modifier = Modifier.Companion.padding(12.dp),
                            verticalAlignment = Alignment.Companion.CenterVertically
                        ) {
                            Text("$s", fontWeight = FontWeight.Companion.Bold)
                        }
                    }
                }
            }
        }


        Spacer(Modifier.Companion.height(16.dp))
        Button(
            onClick = { showDialog = true }
        ) { Text("Clear High Scores") }

        Spacer(Modifier.Companion.height(16.dp))
        Button(onClick = onBack) { Text("Back") }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            clearHighScores(ctx)
                            scores = emptyList()
                            showDialog = false
                        }
                    ) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("No")
                    }
                },
                title = { Text("Clear High Scores") },
                text = { Text("Are you sure you want to delete all high scores?") }
            )
        }
    }
}