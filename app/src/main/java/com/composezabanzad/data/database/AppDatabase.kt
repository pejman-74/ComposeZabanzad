package com.composezabanzad.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.composezabanzad.data.model.Proverb

@Database(entities = [Proverb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun proverbDao(): ProverbDao
}