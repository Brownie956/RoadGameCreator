package com.cbmedia.roadgamecreator.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cbmedia.roadgamecreator.data.EXAMPLE_MINIGAMES
import com.cbmedia.roadgamecreator.models.GameRun
import com.cbmedia.roadgamecreator.models.Minigame
import com.cbmedia.roadgamecreator.models.MinigameResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val minigames: Map<String, Minigame> = EXAMPLE_MINIGAMES

    // --- State ---
    private val _totalPointsScoredInMinigames = mutableIntStateOf(0)
    val totalPointsScoredInMinigames: State<Int> = _totalPointsScoredInMinigames

    private val _score = mutableIntStateOf(0)
    val score: State<Int> = _score

    private val _localScore = mutableIntStateOf(0)
    val localScore: State<Int> = _localScore

    private val _currentMinigame = mutableStateOf(minigames.getValue("initial"))
    val currentMinigame: State<Minigame> = _currentMinigame

    private val _minigameInProgress = mutableStateOf(false)
    val minigameInProgress: State<Boolean> = _minigameInProgress

    private val _elapsedTime = mutableIntStateOf(0)  // seconds
    val elapsedTime: State<Int> = _elapsedTime

    private val _gameInProgress = mutableStateOf(false)
    val gameInProgress: State<Boolean> = _gameInProgress

    private val results = mutableListOf<MinigameResult>()

    // --- Game logic ---

    init {
        // launch timer loop inside ViewModelScope
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                if (_gameInProgress.value) {
                    _elapsedTime.intValue += 1
                }
            }
        }
    }

    fun incrementScore() {
        _localScore.intValue += _currentMinigame.value.reward
    }

    fun incrementTime() {
        if (_gameInProgress.value) {
            _elapsedTime.intValue++
        }
    }

    fun startGame() {
        _score.intValue = 0
        _elapsedTime.intValue = 0
        _totalPointsScoredInMinigames.intValue = 0
        results.clear()

        _currentMinigame.value = minigames.getValue("initial")
        _gameInProgress.value = true
    }

    fun stopGame() {
        _gameInProgress.value = false
    }


    fun resetGame() {
        _score.intValue = 0
        _elapsedTime.intValue = 0
        _gameInProgress.value = true
        results.clear()
        // Reset to start minigame
        _currentMinigame.value = minigames.getValue("initial")
    }

    fun finishMinigame(isFinal: Boolean) {
        results.add(
            MinigameResult(
                id = _currentMinigame.value.id,
                title = _currentMinigame.value.title,
                score = _localScore.intValue
            )
        )
        _score.intValue += _localScore.intValue
        _totalPointsScoredInMinigames.intValue += _localScore.intValue
        _score.intValue += 2
        _localScore.intValue = 0
        _minigameInProgress.value = false

        if (isFinal) stopGame()
    }

    fun goToMinigame(id: String) {
        val minigame = minigames.getValue(id)
        _currentMinigame.value = minigame
        _score.intValue -= minigame.cost
        _minigameInProgress.value = true
    }

    fun getMinigame(id: String): Minigame = minigames.getValue(id)

    fun buildGameRun(): GameRun {
        return GameRun(
            finalScore = _score.intValue,
            totalPointsScoredInMinigames = _totalPointsScoredInMinigames.intValue,
            minigames = results.toList(),
            totalTimeSeconds = _elapsedTime.intValue
        )
    }
}
