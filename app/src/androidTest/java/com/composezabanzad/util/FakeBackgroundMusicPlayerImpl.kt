package com.composezabanzad.util


class FakeBackgroundMusicPlayerImpl : BackgroundMusicPlayer {

    var isPlaying = false
        private set

    override suspend fun play(resId: Int) {
        isPlaying = true
    }

    override suspend fun stop() {
        isPlaying = false
    }
}