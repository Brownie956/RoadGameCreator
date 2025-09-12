package com.cbmedia.roadgamecreator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cbmedia.roadgamecreator.viewmodels.GameViewModel
import com.cbmedia.roadgamecreator.persistence.saveHighScore

@Composable
fun GameScreen(viewModel: GameViewModel, onEndGame: (Int) -> Unit, onShowHighScores: () -> Unit) {
    val current = viewModel.minigames[viewModel.currentMinigameId] ?: return
    val ctx = LocalContext.current


    Column(
        modifier = Modifier.Companion.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.Companion.fillMaxWidth()
        ) {
            Text("Score: ${viewModel.score}")
            Button(onClick = onShowHighScores) { Text("High Scores") }
        }


        Card(modifier = Modifier.Companion.fillMaxWidth()) {
            Column(
                modifier = Modifier.Companion.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    current.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Companion.Bold
                )
                Text(current.description)
                Text("This minigame reward per increment: ${current.reward}")
                Text("Local minigame score: ${viewModel.currentMinigameLocalScore}")


                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { viewModel.incrementCurrent() },
                        enabled = viewModel.minigameInProgress
                    ) {
                        Text("Increment (+${current.reward})")
                    }


                    Button(
                        onClick = { viewModel.finishMinigame() },
                        enabled = viewModel.minigameInProgress
                    ) {
                        Text("Finish Minigame")
                    }
                }


                Spacer(Modifier.Companion.height(8.dp))


                if (!viewModel.minigameInProgress) {
                    if (current.isFinal) {
                        Button(onClick = {
                            // finish and end the game
                            viewModel.finishMinigame()
                            saveHighScore(ctx, viewModel.score)
                            onEndGame(viewModel.score)
                        }) {
                            Text("Congratulations! You've finished the game")
                        }
                    } else {
                        Text("Choose your next path:")
                        viewModel.minigames[current.linkedIds.firstOrNull() ?: ""] // safe-check
                        Column {
                            current.linkedIds.forEach { nextId ->
                                val nextMg = viewModel.minigames[nextId] ?: return@forEach
                                val affordable = viewModel.score >= nextMg.cost
                                Button(
                                    onClick = {
                                        // attempt to spend cost (if any) and go
                                        val allowed =
                                            if (nextMg.cost > 0) viewModel.spendForEntering(nextId) else true
                                        if (allowed) viewModel.goToMinigame(nextId)
                                    }, enabled = affordable,
                                    modifier = Modifier.Companion.fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                ) {
                                    Text("${nextMg.title} (cost ${nextMg.cost})")
                                }
                            }
                        }
                    }
                }
            }
        }


        Spacer(Modifier.Companion.height(8.dp))
        // Quick debug / access
        LazyColumn {
            items(viewModel.minigames.values.toList()) { mg ->
                Text("- ${mg.id}: ${mg.title}")
            }
        }
    }
}