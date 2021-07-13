package com.composezabanzad.util

import androidx.annotation.RawRes
import kotlinx.coroutines.flow.StateFlow

interface BackgroundMusicPlayer {
    suspend fun play(@RawRes resId: Int)
    suspend fun playLastTrack()
    fun isPlaying(): StateFlow<Boolean>
    fun stop()
}