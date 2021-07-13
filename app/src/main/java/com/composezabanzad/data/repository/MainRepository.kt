package com.composezabanzad.data.repository

import com.composezabanzad.data.model.Proverb
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getProverb(id: Int): Proverb?

    suspend fun getProverbsCountByLevel(level: Int): Int

    fun currentLevel(): Flow<Int>

    suspend fun setCurrentLevel(level: Int)

    fun currentStep(): Flow<Int>

    suspend fun setCurrentStep(step: Int)

    fun lastStepSolvedWords(): Flow<List<String>>

    suspend fun addSolvedWord(word: String)

    suspend fun clearSolvedWords()
}