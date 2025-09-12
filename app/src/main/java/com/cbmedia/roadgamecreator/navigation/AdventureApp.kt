package com.cbmedia.roadgamecreator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cbmedia.roadgamecreator.persistence.saveHighScore
import com.cbmedia.roadgamecreator.ui.EndScreen
import com.cbmedia.roadgamecreator.ui.GameScreen
import com.cbmedia.roadgamecreator.ui.HighScoresScreen
import com.cbmedia.roadgamecreator.ui.StartScreen
import com.cbmedia.roadgamecreator.viewmodels.GameViewModel

@Composable
fun AdventureApp(gameViewModel: GameViewModel = viewModel()) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(
                onStart = {
                    gameViewModel.startGame()
                    navController.navigate("game")
                },
                onViewHighScores = { navController.navigate("highscores") }
            )
        }


        composable("game") {
            GameScreen(
                viewModel = gameViewModel,
                onEndGame = { finalScore ->
                    // when an end minigame finishes, navigate to end screen with finalScore
                    navController.navigate("end")
                },
                onShowHighScores = { navController.navigate("highscores") }
            )
        }


        composable("end") {
            val ctx = LocalContext.current
            EndScreen(
                finalScore = gameViewModel.score,
                onBackToStart = {
                    navController.popBackStack("start", inclusive = false)
                },
                onViewHighScores = { navController.navigate("highscores") }
            )
        }


        composable("highscores") {
            HighScoresScreen(onBack = { navController.popBackStack() })
        }
    }
}
