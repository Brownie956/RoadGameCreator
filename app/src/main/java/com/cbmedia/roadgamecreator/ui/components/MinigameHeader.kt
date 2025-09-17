package com.cbmedia.roadgamecreator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cbmedia.roadgamecreator.models.Minigame


@Composable
fun MinigameHeader(
    minigame: Minigame,
    score: Int,
    elapsedSeconds: Int
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = minigame.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = minigame.description)
        Text("Score: $score")

        Stopwatch(elapsedSeconds)
    }
}

@Preview(showBackground = true)
@Composable
fun MinigameHeaderPreview() {
    val minigame = Minigame(
        id = "example",
        title = "Example minigame",
        description = "Example minigame description",
    )

    MinigameHeader(
        minigame = minigame,
        score = 10,
        elapsedSeconds = 250
    )
}
