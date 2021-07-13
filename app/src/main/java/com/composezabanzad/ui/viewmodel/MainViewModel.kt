package com.composezabanzad.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.composezabanzad.data.model.Proverb
import com.composezabanzad.data.repository.MainRepository
import com.composezabanzad.util.BackgroundMusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    @Named("main") private val mainDispatcher: CoroutineDispatcher,
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val mainRepository: MainRepository,
    backgroundMusicPlayer: BackgroundMusicPlayer
) : BaseViewModel(mainDispatcher, ioDispatcher, backgroundMusicPlayer) {

    private val _proverb: MutableState<Proverb?> = mutableStateOf(null)
    val proverb: State<Proverb?> = _proverb

    private val _proverbsCountByLevel: MutableState<Int?> = mutableStateOf(null)
    val proverbsCountByLevel: State<Int?> = _proverbsCountByLevel


    fun getProverb(id: Int) = doInMain { _proverb.value = mainRepository.getProverb(id) }


    fun getProverbsCountByLevel(level: Int) =
        doInMain { _proverbsCountByLevel.value = mainRepository.getProverbsCountByLevel(level) }


    val currentLevel: Flow<Int?> = mainRepository.currentLevel()

    suspend fun setCurrentLevel(level: Int) = doInMain { mainRepository.setCurrentLevel(level) }

    val currentStep: Flow<Int> = mainRepository.currentStep()

    suspend fun setCurrentStep(step: Int) = doInMain { mainRepository.setCurrentStep(step) }

    val lastStepSolvedWords: Flow<List<String>> = mainRepository.lastStepSolvedWords()

    suspend fun addSolvedWord(word: String) = doInMain { mainRepository.addSolvedWord(word) }

    suspend fun clearSolvedWords() = doInMain { mainRepository.clearSolvedWords() }

}