package com.cbmedia.roadgamecreator.models

data class Minigame(
    val id: String,
    val title: String,
    val description: String,
    val cost: Int = 0, // points required to enter/play
    val reward: Int = 1, // points added per "increment" button
    val isFinal: Boolean = false,
    val linkedIds: List<String> = emptyList()
)

val EXAMPLE_MINIGAMES = listOf(
    Minigame(
        id = "l1-a",
        title = "Level 1 - A",
        description = "This is Level 1 - A",
        cost = 0,
        isFinal = false,
        linkedIds = listOf("l1-b", "l2-a", "l2-b")
    ),
    Minigame(
        id = "l1-b",
        title = "Level 1 - B",
        description = "This is Level 1 - B",
        cost = 0,
        isFinal = false,
        linkedIds = listOf("l1-b", "l2-a", "l2-b")
    ),
    Minigame(
        id = "l2-a",
        title = "Level 2 - A",
        description = "This is Level 2 - A",
        cost = 0,
        isFinal = false,
        linkedIds = listOf("l2-a", "l3-a", "l3-b")
    ),
    Minigame(
        id = "l2-b",
        title = "Level 2 - B",
        description = "This is Level 2 - B",
        cost = 0,
        isFinal = false,
        linkedIds = listOf("l2-a", "l3-a", "l3-b")
    ),
    Minigame(
        id = "l3-a",
        title = "Level 3 - A",
        description = "This is Level 3 - A",
        cost = 0,
        isFinal = false,
        linkedIds = listOf("l2-b", "l3-a", "l4-a")
    ),
    Minigame(
        id = "l3-b",
        title = "Level 3 - B",
        description = "This is Level 3 - B",
        cost = 20,
        isFinal = false,
        linkedIds = listOf("l2-b", "l3-a", "l4-a")
    ),
    Minigame(
        id = "l4-a",
        title = "Level 4 - A",
        description = "This is Level 4 - A",
        cost = 30,
        isFinal = false,
        linkedIds = listOf("l5-a")
    ),
    Minigame(
        id = "l5-a",
        title = "Level 5 - Final",
        description = "This is Level 5 - Final level",
        cost = 0,
        isFinal = true,
        linkedIds = emptyList()
    ),
).associateBy { it.id }