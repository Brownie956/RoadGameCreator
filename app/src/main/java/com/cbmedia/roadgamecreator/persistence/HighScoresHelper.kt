package com.cbmedia.roadgamecreator.persistence

import android.content.Context
import androidx.core.content.edit

private const val PREFS_NAME = "adventure_prefs"
private const val KEY_HIGH_SCORES = "high_scores" // comma-separated


fun saveHighScore(context: Context, score: Int) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val existing = prefs.getString(KEY_HIGH_SCORES, "") ?: ""
    val list = if (existing.isBlank()) mutableListOf<Int>() else existing.split(",").mapNotNull { it.toIntOrNull() }.toMutableList()
    list.add(score)
    list.sortDescending()
// keep top 10
    val trimmed = list.take(10)
    prefs.edit { putString(KEY_HIGH_SCORES, trimmed.joinToString(",")) }
}


fun loadHighScores(context: Context): List<Int> {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    val existing = prefs.getString(KEY_HIGH_SCORES, "") ?: ""
    return if (existing.isBlank()) emptyList() else existing.split(",").mapNotNull { it.toIntOrNull() }
}

fun clearHighScores(context: Context) {
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    prefs.edit { remove(KEY_HIGH_SCORES) }
}