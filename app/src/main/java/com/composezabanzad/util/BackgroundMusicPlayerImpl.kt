package com.composezabanzad.util

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext


class BackgroundMusicPlayerImpl(@ApplicationContext private val context: Context) :
    BackgroundMusicPlayer {

    private var mediaPlayer: MediaPlayer? = null

    override suspend fun play(@RawRes resId: Int) {
        stop()
        runCatching {
            mediaPlayer = MediaPlayer.create(context, resId)
            mediaPlayer?.start()
        }
    }

    override suspend fun stop() {
        mediaPlayer?.let { mediaPlayer ->
            runCatching {
                mediaPlayer.stop()
                mediaPlayer.release()
                this.mediaPlayer = null
            }
        }
    }
}

