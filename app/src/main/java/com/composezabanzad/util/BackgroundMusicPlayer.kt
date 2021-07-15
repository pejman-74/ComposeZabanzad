package com.composezabanzad.util

import androidx.annotation.RawRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface BackgroundMusicPlayer {
    suspend fun play(@RawRes resId: Int)
    suspend fun playLastTrack()
    fun isPlaying(): Flow<Boolean>
    fun stop()
}