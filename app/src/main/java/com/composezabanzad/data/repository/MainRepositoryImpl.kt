package com.composezabanzad.data.repository

import com.composezabanzad.data.database.AppDatabase
import com.composezabanzad.data.datastore.AppDatastore
import com.composezabanzad.data.model.Proverb
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val appDatastore: AppDatastore
) : MainRepository {

    override suspend fun getProverb(id: Int): Proverb? {
        return appDatabase.proverbDao().getProverb(id)
    }

    override suspend fun getProverbsCountByLevel(level: Int): Int {
        return appDatabase.proverbDao().getProverbsCountByLevel(level)
    }

    override fun currentLevel(): Flow<Int> {
        return appDatastore.currentLevel
    }

    override suspend fun setCurrentLevel(level: Int) {
        return appDatastore.setCurrentLevel(level)
    }

    override fun currentStep(): Flow<Int> {
        return appDatastore.currentStep
    }

    override suspend fun setCurrentStep(step: Int) {
        appDatastore.setCurrentStep(step)
    }

    override fun lastStepSolvedWords(): Flow<List<String>> {
        return appDatastore.lastStepSolvedWords
    }

    override suspend fun addSolvedWord(word: String) {
        appDatastore.addSolvedWord(word)
    }

    override suspend fun clearSolvedWords() {
        appDatastore.clearSolvedWords()
    }

}

