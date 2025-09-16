package com.cbmedia.roadgamecreator.models

data class Minigame(
    val id: String,
    val title: String,
    val description: String,
    val maxScore: Int? = null,
    val length: String? = null,
    val cost: Int = 0,
    val reward: Int = 1,
    val isFinal: Boolean = false,
    val linkedMinigames: List<String> = emptyList()
)

data class MinigameResult(
    val id: String,
    val title: String,
    val score: Int
)

data class GameRun(
    val finalScore: Int,
    val totalPointsScoredInMinigames: Int,
    val minigames: List<MinigameResult>,
    val totalTimeSeconds: Int,
)
