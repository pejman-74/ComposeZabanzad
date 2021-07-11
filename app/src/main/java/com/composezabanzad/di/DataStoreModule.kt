package com.composezabanzad.di

import android.content.Context
import com.composezabanzad.data.datastore.AppDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {
    @Singleton
    @Provides
    fun provideAppDataStore(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope
    ): AppDatastore =
        AppDatastore(context, "AppDatastore", coroutineScope)
}