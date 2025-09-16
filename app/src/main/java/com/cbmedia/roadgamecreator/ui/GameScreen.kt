package com.cbmedia.roadgamecreator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cbmedia.roadgamecreator.persistence.HighScoresHelper
import com.cbmedia.roadgamecreator.ui.components.MinigameButton
import com.cbmedia.roadgamecreator.viewmodels.GameViewModel

@Composable
fun GameScreen(vm: GameViewModel, navController: NavHostController) {
    val context = LocalContext.current
    val current = vm.currentMinigame.value
    val score = vm.score.value
    val localScore = vm.localScore.value
    val minigameInProgress = vm.minigameInProgress.value

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = current.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = current.description)
        Text("Score: $score")

        Stopwatch(vm)


        if (minigameInProgress) {
            val scoreText: String = if (current.maxScore == null) localScore.toString() else "${localScore}/${current.maxScore}"
            Text("Minigame score: $scoreText")
            Button(
                onClick = { vm.incrementScore() },
                enabled = if (current.maxScore == null) true else localScore < current.maxScore
            ) {
                Text("Increment Score (+${current.reward})")
            }
            Spacer(Modifier.height(8.dp))
            Button(onClick = {
                vm.finishMinigame(current.isFinal)
            }) {
                Text("Finish Minigame")
            }
        } else {
            if (current.isFinal) {
                Button(onClick = {
                    HighScoresHelper.saveRun(context, vm.buildGameRun())
                    navController.navigate("end") {
                        popUpTo("game") { inclusive = true }
                    }
                }) {
                    Text("End Game")
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    current.linkedMinigames.forEach { nextId ->
                        val next = vm.getMinigame(nextId)
                        MinigameButton(
                            minigame = next,
                            onClick = { vm.goToMinigame(nextId) },
                            enabled = score >= next.cost,
                        )
                    }
                }
            }
        }
    }
}
