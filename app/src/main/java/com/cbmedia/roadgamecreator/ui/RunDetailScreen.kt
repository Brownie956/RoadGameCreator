package com.cbmedia.roadgamecreator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cbmedia.roadgamecreator.Utils.formatTime
import com.cbmedia.roadgamecreator.models.GameRun

@Composable
fun RunDetailScreen(run: GameRun, navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Final Score: ${run.finalScore}", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Total points scored in minigames: ${run.totalPointsScoredInMinigames}")
        Text(text = "Total time: ${formatTime(run.totalTimeSeconds)}")
        Spacer(Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(run.minigames) { result ->
                Text("${result.title}: ${result.score}")
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}
