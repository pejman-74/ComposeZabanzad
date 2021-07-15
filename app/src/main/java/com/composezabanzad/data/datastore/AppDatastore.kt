package com.composezabanzad.data.datastore

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class AppDatastore(
    context: Context,
    datastoreName: String,
    coroutineScope: CoroutineScope
) {

    private val Context.appDatastore: DataStore<Preferences> by preferencesDataStore(
        name = datastoreName,
        scope = coroutineScope
    )

    private fun String.toListByComma() = if (this.isBlank()) emptyList() else this.split(",")
    private val appDatastore = context.appDatastore

    companion object {
        private val CURRENT_LEVEL = intPreferencesKey("current_level")
        private val CURRENT_STEP = intPreferencesKey("current_step")
        private val SOLVED_WORDS = stringPreferencesKey("solved_words")
        private val IS_MUTE_AUDIO = booleanPreferencesKey("is_mute_audio")
    }

    val isMuteAudio: Flow<Boolean> = appDatastore.data
        .map { preferences ->
            preferences[IS_MUTE_AUDIO] ?: false
        }

    suspend fun setIsMuteAudio(isMute: Boolean) {
        appDatastore.edit { preferences ->
            preferences[IS_MUTE_AUDIO] = isMute
        }
    }

    val currentLevel: Flow<Int> = appDatastore.data
        .map { preferences ->
            preferences[CURRENT_LEVEL] ?: 0
        }

    suspend fun setCurrentLevel(level: Int) {
        appDatastore.edit { preferences ->
            preferences[CURRENT_LEVEL] = level
        }
    }

    val currentStep: Flow<Int> = appDatastore.data
        .map { preferences ->
            preferences[CURRENT_STEP] ?: 0
        }

    suspend fun setCurrentStep(step: Int) {
        appDatastore.edit { preferences ->
            preferences[CURRENT_STEP] = step
        }
    }

    val lastStepSolvedWords: Flow<List<String>> = appDatastore.data
        .map { preferences ->
            val string = preferences[SOLVED_WORDS] ?: ""
            string.toListByComma()
        }

    suspend fun addSolvedWord(word: String) {
        appDatastore.edit { preferences ->
            val currentSolvedWords = preferences[SOLVED_WORDS] ?: ""
            if (currentSolvedWords.isBlank())
                preferences[SOLVED_WORDS] = word
            else
                preferences[SOLVED_WORDS] = "$currentSolvedWords,$word"
        }
    }

    suspend fun clearSolvedWords() {
        appDatastore.edit { preferences -> preferences[SOLVED_WORDS] = "" }
    }

    @VisibleForTesting
    suspend fun clearDataStore() {
        appDatastore.edit { it.clear() }
    }
}