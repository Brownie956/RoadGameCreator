package com.cbmedia.roadgamecreator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cbmedia.roadgamecreator.models.GameRun
import com.cbmedia.roadgamecreator.ui.EndScreen
import com.cbmedia.roadgamecreator.ui.GameScreen
import com.cbmedia.roadgamecreator.ui.HighScoresScreen
import com.cbmedia.roadgamecreator.ui.RunDetailScreen
import com.cbmedia.roadgamecreator.ui.StartScreen
import com.cbmedia.roadgamecreator.viewmodels.GameViewModel

@Composable
fun AdventureApp(vm: GameViewModel) {
    val navController = rememberNavController()
    var selectedRun by remember { mutableStateOf<GameRun?>(null) }


    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartScreen(
            vm = vm,
            onStartGame = { navController.navigate("game") },
            onClickHighScores = { navController.navigate("highscores") }
        ) }
        composable("game") { GameScreen(vm, navController) }
        composable("end") { EndScreen(vm, navController) }
        composable("highscores") { HighScoresScreen(navController) { run ->
            selectedRun = run
            navController.navigate("rundetail")
        } }
        composable("rundetail") {
            selectedRun?.let { run ->
                RunDetailScreen(run, navController)
            }
        }
    }
}
