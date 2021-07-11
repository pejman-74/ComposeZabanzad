package com.composezabanzad.di


import android.content.Context
import com.composezabanzad.data.datastore.AppDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [DataStoreModule::class])
@Module
object DataStoreModuleTest {
    @Singleton
    @Provides
    fun provideAppDataStore(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope
    ): AppDatastore =
        AppDatastore(context, "TestAppDatastore", coroutineScope)
}