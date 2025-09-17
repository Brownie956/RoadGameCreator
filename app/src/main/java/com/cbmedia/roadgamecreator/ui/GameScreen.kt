package com.cbmedia.roadgamecreator.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cbmedia.roadgamecreator.data.EXAMPLE_MINIGAMES
import com.cbmedia.roadgamecreator.persistence.HighScoresHelper
import com.cbmedia.roadgamecreator.ui.components.MinigameBody
import com.cbmedia.roadgamecreator.ui.components.MinigameHeader
import com.cbmedia.roadgamecreator.ui.components.MinigamesButtonGroup
import com.cbmedia.roadgamecreator.viewmodels.GameViewModel

@Composable
fun GameScreen(vm: GameViewModel, navController: NavHostController) {
    val context = LocalContext.current
    val current = vm.currentMinigame.value
    val score = vm.score.value
    val localScore = vm.localScore.value
    val minigameInProgress = vm.minigameInProgress.value
    val elapsedSeconds by vm.elapsedTime

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        MinigameHeader(
            minigame = current,
            score = score,
            elapsedSeconds = elapsedSeconds
        )

        if (minigameInProgress) {
            MinigameBody(
                minigame = current,
                currentMinigameScore = localScore,
                onClickIncrement = { vm.incrementScore() },
                onEndMinigame = if (current.isFinal) {
                    {
                        vm.finishMinigame()
                        vm.stopGame()
                        HighScoresHelper.saveRun(context, vm.buildGameRun())
                        navController.navigate("end") {
                            popUpTo("game") { inclusive = true }
                        }
                    }
                } else {
                    { vm.finishMinigame() }
                }
            )
        } else {
            MinigamesButtonGroup(
                currentMinigame = current,
                minigames = EXAMPLE_MINIGAMES,
                totalScore = score,
                onMinigameSelected = { id -> vm.goToMinigame(id) }
            )
        }
    }
}
