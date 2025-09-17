package com.cbmedia.roadgamecreator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cbmedia.roadgamecreator.models.Minigame

@Composable
fun MinigameBody(
    minigame: Minigame,
    currentMinigameScore: Int,
    onClickIncrement: () -> Unit,
    onEndMinigame: () -> Unit,
) {
    val scorePostFix = if (minigame.maxScore == null) "" else "/${minigame.maxScore}"
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Minigame score: $currentMinigameScore$scorePostFix")

        Button(
            onClick = onClickIncrement,
            enabled = if (minigame.maxScore == null) true else currentMinigameScore < minigame.maxScore,
        ) {
            Text("Increment Score (+${minigame.reward})")
        }

        Button(
            onClick = onEndMinigame
        ) {
            Text(
                text = if (minigame.isFinal) "End game" else "Finish minigame"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NonFinalMinigameBodyPreview() {
    val minigame = Minigame(
        id = "noFinalMinigame",
        title = "Example non-final minigame",
        description = "Example non-final minigame description",
        isFinal = false
    )
    MinigameBody(
        minigame = minigame,
        currentMinigameScore = 5,
        onClickIncrement = {},
        onEndMinigame = {}
    )
}

@Preview(showBackground = true)
@Composable
fun FinalMinigameBodyPreview() {
    val minigame = Minigame(
        id = "finalMinigame",
        title = "Example final minigame",
        description = "Example final minigame description",
        isFinal = true
    )
    MinigameBody(
        minigame = minigame,
        currentMinigameScore = 5,
        onClickIncrement = {},
        onEndMinigame = {}
    )
}

@Preview(showBackground = true)
@Composable
fun MaxScoreMinigameBodyPreview() {
    val minigame = Minigame(
        id = "finalMinigame",
        title = "Example final minigame",
        description = "Example final minigame description",
        isFinal = true,
        maxScore = 10
    )
    MinigameBody(
        minigame = minigame,
        currentMinigameScore = 5,
        onClickIncrement = {},
        onEndMinigame = {}
    )
}

@Preview(showBackground = true)
@Composable
fun IncrementDisabledMinigameBodyPreview() {
    val minigame = Minigame(
        id = "finalMinigame",
        title = "Example final minigame",
        description = "Example final minigame description",
        isFinal = true,
        maxScore = 10
    )
    MinigameBody(
        minigame = minigame,
        currentMinigameScore = 10,
        onClickIncrement = {},
        onEndMinigame = {}
    )
}
