package com.composezabanzad.util

import androidx.annotation.RawRes

interface BackgroundMusicPlayer {
    suspend fun play(@RawRes resId: Int)
    fun stop()
}