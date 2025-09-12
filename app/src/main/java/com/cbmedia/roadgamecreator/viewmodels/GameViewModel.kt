package com.cbmedia.roadgamecreator.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cbmedia.roadgamecreator.models.EXAMPLE_MINIGAMES

class GameViewModel : ViewModel() {
    var currentMinigameId by mutableStateOf("l1-a")
        private set

    var score by mutableIntStateOf(0)
        private set

    // Local per-minigame temporary score if you want separate counters per minigame
    var currentMinigameLocalScore by mutableIntStateOf(0)
        private set

    var minigameInProgress by mutableStateOf(true)
        private set

    val minigames = EXAMPLE_MINIGAMES

    fun startGame() {
        currentMinigameId = "l1-a"
        score = 0
        currentMinigameLocalScore = 0
        minigameInProgress = true
    }

    fun incrementCurrent() {
        val mg = minigames[currentMinigameId] ?: return
        currentMinigameLocalScore += mg.reward
    }

    fun finishMinigame() {
        // accumulate the local minigame score into the global score
        score += currentMinigameLocalScore
        currentMinigameLocalScore = 0
        minigameInProgress = false
    }

    fun goToMinigame(nextId: String) {
        currentMinigameId = nextId
        currentMinigameLocalScore = 0
        minigameInProgress = true
    }

    fun spendForEntering(minigameId: String): Boolean {
        val mg = minigames[minigameId] ?: return false
        return if (score >= mg.cost) {
            score -= mg.cost
            true
        } else false
    }
}