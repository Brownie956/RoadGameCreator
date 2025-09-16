package com.cbmedia.roadgamecreator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cbmedia.roadgamecreator.viewmodels.GameViewModel


@Composable
fun EndScreen(vm: GameViewModel, navController: NavHostController) {
    val score = vm.score.value
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Game Over! Final Score: $score")
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            vm.resetGame()
            navController.navigate("start") {
                popUpTo("end") { inclusive = true }
            }
        }) {
            Text("Back to Start")
        }
    }
}
