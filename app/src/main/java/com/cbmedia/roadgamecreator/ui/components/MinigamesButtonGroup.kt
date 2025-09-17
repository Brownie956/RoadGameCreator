package com.cbmedia.roadgamecreator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cbmedia.roadgamecreator.data.EXAMPLE_MINIGAMES
import com.cbmedia.roadgamecreator.models.Minigame

@Composable
fun MinigamesButtonGroup(
    currentMinigame: Minigame,
    minigames: Map<String, Minigame>,
    totalScore: Int,
    onMinigameSelected: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        currentMinigame.linkedMinigames.forEach { minigameId ->
            val minigame = minigames.getValue(minigameId)
            MinigameButton(
                minigame = minigame,
                onClick = { onMinigameSelected(minigameId) },
                enabled = totalScore >= minigame.cost,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MinigamesButtonGroupPreview() {
    val exampleMinigames = EXAMPLE_MINIGAMES
    val start = exampleMinigames.getValue("initial")

    MinigamesButtonGroup(
        currentMinigame = start,
        minigames = exampleMinigames,
        totalScore = 5,
        onMinigameSelected = {}
    )
}
