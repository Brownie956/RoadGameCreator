package com.cbmedia.roadgamecreator.data

import com.cbmedia.roadgamecreator.Utils.formatTime
import com.cbmedia.roadgamecreator.models.Minigame

val EXAMPLE_MINIGAMES = listOf(
    Minigame(
        id = "initial",
        title = "Start",
        description = "Pick your first minigame",
        linkedMinigames = listOf("l1-a", "l1-b")
    ),
    Minigame(
        id = "l1-a",
        title = "Level 1 - A",
        description = "This is Level 1 - A",
        maxScore = 1,
        length = formatTime(250),
        cost = 0,
        isFinal = false,
        linkedMinigames = listOf("l1-b", "l2-a", "l2-b")
    ),
    Minigame(
        id = "l1-b",
        title = "Level 1 - B",
        description = "This is Level 1 - B",
        maxScore = 4,
        length = formatTime(340),
        cost = 0,
        isFinal = false,
        linkedMinigames = listOf("l1-b", "l2-a", "l2-b")
    ),
    Minigame(
        id = "l2-a",
        title = "Level 2 - A",
        description = "This is Level 2 - A",
        maxScore = 6,
        length = formatTime(317),
        cost = 0,
        isFinal = false,
        linkedMinigames = listOf("l2-a", "l3-a", "l3-b")
    ),
    Minigame(
        id = "l2-b",
        title = "Level 2 - B",
        description = "This is Level 2 - B",
        maxScore = 6,
        length = formatTime(475),
        cost = 0,
        isFinal = false,
        linkedMinigames = listOf("l2-a", "l3-a", "l3-b")
    ),
    Minigame(
        id = "l3-a",
        title = "Level 3 - A",
        description = "This is Level 3 - A",
        maxScore = 4,
        length = formatTime(257),
        cost = 0,
        isFinal = false,
        linkedMinigames = listOf("l2-b", "l3-a", "l4-a")
    ),
    Minigame(
        id = "l3-b",
        title = "Level 3 - B",
        description = "This is Level 3 - B",
        maxScore = 15,
        length = formatTime(740),
        cost = 20,
        isFinal = false,
        linkedMinigames = listOf("l2-b", "l3-a", "l4-a")
    ),
    Minigame(
        id = "l4-a",
        title = "Level 4 - A",
        description = "This is Level 4 - A",
        maxScore = 10,
        length = formatTime(350),
        cost = 30,
        isFinal = false,
        linkedMinigames = listOf("l5-a")
    ),
    Minigame(
        id = "l5-a",
        title = "Level 5 - Final",
        description = "This is Level 5 - Final level",
        maxScore = 10,
        length = formatTime(555),
        cost = 0,
        isFinal = true,
        linkedMinigames = emptyList()
    ),
).associateBy { it.id }
