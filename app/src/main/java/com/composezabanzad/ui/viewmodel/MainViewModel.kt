package com.composezabanzad.ui.viewmodel

import androidx.annotation.RawRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.composezabanzad.data.model.Proverb
import com.composezabanzad.data.repository.MainRepository
import com.composezabanzad.util.BackgroundMusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    @Named("main") private val mainDispatcher: CoroutineDispatcher,
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val mainRepository: MainRepository,
    private val backgroundMusicPlayer: BackgroundMusicPlayer
) : BaseViewModel(mainDispatcher, ioDispatcher) {

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

    val isMuteAudio: Flow<Boolean> = mainRepository.isMuteAudio()

    val isPlayingBackgroundMusic = backgroundMusicPlayer.isPlaying()

    fun setIsMuteAudio(isMute: Boolean) = doInMain {
        mainRepository.setIsMuteAudio(isMute)
    }

    fun playBackgroundMusic(@RawRes resId: Int) =
        doInMain {
            doInIO { backgroundMusicPlayer.play(resId) }
        }

    fun playLastTack() = doInMain {
        doInIO { backgroundMusicPlayer.playLastTrack() }
    }

    fun stopBackgroundMusic() {
        backgroundMusicPlayer.stop()
    }

    private val appLifecycleObserver = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onMoveToForeground() {
            doInMain {
                if (!isMuteAudio.first())
                    playLastTack()
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onMoveToBackground() {
            stopBackgroundMusic()
        }
    }

    init {
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
    }

    override fun onCleared() {
        super.onCleared()
        ProcessLifecycleOwner.get().lifecycle.removeObserver(appLifecycleObserver)
    }

}