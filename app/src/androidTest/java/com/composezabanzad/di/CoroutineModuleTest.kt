package com.composezabanzad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@TestInstallIn(components = [SingletonComponent::class], replaces = [CoroutineModule::class])
@Module
object CoroutineModuleTest {

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = TestCoroutineDispatcher()

    @Provides
    @Singleton
    fun provideCoroutineScope(testCoroutineDispatcher: CoroutineDispatcher): CoroutineScope =
        TestCoroutineScope(testCoroutineDispatcher + SupervisorJob())
}