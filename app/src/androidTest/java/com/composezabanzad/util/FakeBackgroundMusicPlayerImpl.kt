package com.composezabanzad.util

import com.composezabanzad.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class FakeBackgroundMusicPlayerImpl : BackgroundMusicPlayer {

    private val isPlaying = MutableStateFlow(false)
    var lastPlayedResId = 0
        private set

    override suspend fun play(resId: Int) {
        isPlaying.value = true
        lastPlayedResId = resId
    }

    override suspend fun playLastTrack() {
        val resId = if (lastPlayedResId == 0) R.raw.app_background_music else lastPlayedResId
        play(resId)
    }

    override fun stop() {
        isPlaying.value = false
    }

    override fun isPlaying(): StateFlow<Boolean> = isPlaying
}