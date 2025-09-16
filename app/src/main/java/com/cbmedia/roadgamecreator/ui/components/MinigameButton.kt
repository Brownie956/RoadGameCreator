package com.cbmedia.roadgamecreator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cbmedia.roadgamecreator.Utils.formatTime
import com.cbmedia.roadgamecreator.models.Minigame

@Composable
fun MinigameButton(
    minigame: Minigame,
    onClick: () -> Unit,
    enabled: Boolean
) {
    val isCostly: Boolean = minigame.cost > 0
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isCostly) Color.Yellow else MaterialTheme.colorScheme.primary,
            contentColor = if (isCostly) Color.Black else MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.DarkGray
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(minigame.title)
            if (minigame.length == null) {
                Text("Length: UNLIMITED")
            } else {
                Text("Length: ${minigame.length}")
            }

            if (minigame.maxScore == null){
                Text("Max score: UNLIMITED")
            } else {
                Text("Max score: ${minigame.maxScore}")
            }
            Text("Cost: ${minigame.cost}")
        }
    }
}

@Preview
@Composable
fun MinigameButtonPreview() {
    val minigames = listOf(
        Minigame(
            id = "free",
            title = "Free minigame",
            description = "Free minigame description",
            maxScore = 10,
            length = formatTime(750),
            cost = 0,
            isFinal = false,
        ),
        Minigame(
            id = "costly",
            title = "Costly minigame",
            description = "Costly minigame description",
            maxScore = 10,
            length = formatTime(350),
            cost = 30,
            isFinal = false,
        ),
        Minigame(
            id = "unlimited",
            title = "Unlimited minigame",
            description = "Unlimited minigame description",
        )
    )

    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        minigames.forEach { minigame ->
            MinigameButton(
                minigame = minigame,
                onClick = {},
                enabled = true
            )
        }

        MinigameButton(
            minigame = minigames.first(),
            onClick = {},
            enabled = false
        )
    }
}
