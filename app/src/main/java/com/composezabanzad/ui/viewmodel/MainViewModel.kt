package com.composezabanzad.ui.viewmodel

import com.composezabanzad.util.BackgroundMusicPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    @Named("main") private val mainDispatcher: CoroutineDispatcher,
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    backgroundMusicPlayer: BackgroundMusicPlayer
) : BaseViewModel(mainDispatcher, ioDispatcher, backgroundMusicPlayer) {


}