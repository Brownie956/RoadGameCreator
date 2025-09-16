package com.cbmedia.roadgamecreator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cbmedia.roadgamecreator.Utils.formatTime
import com.cbmedia.roadgamecreator.models.GameRun
import com.cbmedia.roadgamecreator.persistence.HighScoresHelper

@Composable
fun HighScoresScreen(navController: NavHostController, onRunClick: (GameRun) -> Unit) {
    val context = LocalContext.current
    var runs by remember { mutableStateOf(HighScoresHelper.loadRuns(context)) }
    var showDialog by remember { mutableStateOf(false) }


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("High Scores", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        if (runs.isEmpty()) {
            Text("No high scores yet.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(runs.sortedByDescending { it.finalScore }) { run ->
                    Button(onClick = { onRunClick(run) }, modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Text("Score: ${run.finalScore} - Total time: ${formatTime(run.totalTimeSeconds)}")
                            Text(text = "Total points scored in minigames: ${run.totalPointsScoredInMinigames}")
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { showDialog = true }) {
            Text("Clear High Scores")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { navController.navigate("start") }) {
            Text("Back")
        }
    }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    HighScoresHelper.clearRuns(context)
                    runs = emptyList()
                    showDialog = false
                }) {
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
