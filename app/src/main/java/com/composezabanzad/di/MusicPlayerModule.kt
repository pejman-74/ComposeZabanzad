package com.composezabanzad.di

import android.content.Context
import com.composezabanzad.util.BackgroundMusicPlayer
import com.composezabanzad.util.BackgroundMusicPlayerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MusicPlayerModule {
    @Singleton
    @Provides
    fun provideBackgroundMusicPlayer(@ApplicationContext context: Context): BackgroundMusicPlayer =
        BackgroundMusicPlayerImpl(context)
}