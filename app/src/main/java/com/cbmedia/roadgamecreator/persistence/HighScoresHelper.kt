package com.cbmedia.roadgamecreator.persistence

import android.content.Context
import androidx.core.content.edit
import com.cbmedia.roadgamecreator.models.GameRun
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HighScoresHelper {
    private const val PREFS_NAME = "high_scores"
    private const val KEY_HIGH_SCORES = "runs"
    private val gson = Gson()

    fun saveRun(context: Context, run: GameRun) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Load existing runs
        val json = prefs.getString(KEY_HIGH_SCORES, "[]")
        val type = object : TypeToken<MutableList<GameRun>>() {}.type
        val runs: MutableList<GameRun> = gson.fromJson(json, type) ?: mutableListOf()

        // Add new run
        runs.add(run)

        // Save back to SharedPreferences
        prefs.edit { putString(KEY_HIGH_SCORES, gson.toJson(runs)) }
    }

    fun loadRuns(context: Context): List<GameRun> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_HIGH_SCORES, "[]")
        val type = object : TypeToken<List<GameRun>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun clearRuns(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit { remove(KEY_HIGH_SCORES) }
    }
}
