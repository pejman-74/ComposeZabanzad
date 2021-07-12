package com.composezabanzad.di

import com.composezabanzad.data.database.AppDatabase
import com.composezabanzad.data.datastore.AppDatastore
import com.composezabanzad.data.repository.MainRepository
import com.composezabanzad.data.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        appDatabase: AppDatabase,
        appDatastore: AppDatastore
    ): MainRepository = MainRepositoryImpl(appDatabase, appDatastore)
}