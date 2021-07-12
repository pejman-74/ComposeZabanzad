package com.composezabanzad.util

import androidx.annotation.RawRes

interface BackgroundMusicPlayer {
    suspend fun play(@RawRes resId: Int)
    suspend fun stop()
}