package com.composezabanzad.util

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import com.composezabanzad.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.annotation.concurrent.ThreadSafe

@ThreadSafe
class BackgroundMusicPlayerImpl(private val context: Context) : BackgroundMusicPlayer {

    @Volatile
    private var mediaPlayer: MediaPlayer? = null

    @Volatile
    private var lastPlayedResId = 0

    private val isPlaying = MutableStateFlow(false)

    override suspend fun play(@RawRes resId: Int) {
        if (resId == lastPlayedResId && mediaPlayer?.isPlaying == true)
            return

        stop()

        synchronized(this) {
            runCatching {
                mediaPlayer = MediaPlayer.create(context, resId)
                mediaPlayer?.isLooping = true
                mediaPlayer?.start()
                lastPlayedResId = resId
                isPlaying.value = true
            }
        }
    }

    override fun stop() {
        synchronized(this) {
            mediaPlayer?.let { mediaPlayer ->
                runCatching {
                    mediaPlayer.stop()
                    mediaPlayer.release()
                    this.mediaPlayer = null
                    isPlaying.value = false
                }
            }
        }
    }

    override suspend fun playLastTrack() {
        val resId = if (lastPlayedResId == 0) R.raw.app_background_music else lastPlayedResId
        play(resId)
    }

    override fun isPlaying(): StateFlow<Boolean> = isPlaying

}

