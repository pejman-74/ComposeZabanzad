package com.composezabanzad.di

import com.composezabanzad.util.BackgroundMusicPlayer
import com.composezabanzad.util.FakeBackgroundMusicPlayerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [MusicPlayerModule::class])
@Module
object MusicPlayerModuleTest {
    @Singleton
    @Provides
    fun provideBackgroundMusicPlayer(): BackgroundMusicPlayer = FakeBackgroundMusicPlayerImpl()
}