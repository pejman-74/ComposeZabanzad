package com.composezabanzad.data.database

import androidx.annotation.VisibleForTesting
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.composezabanzad.data.model.Proverb

@Dao
interface ProverbDao {
    @Insert(onConflict = REPLACE)
    @VisibleForTesting
    suspend fun insertProverb(proverb: Proverb)

    @Query("SELECT * FROM PROVERB WHERE id=:id")
    suspend fun getProverb(id: Int): Proverb?

    @Query("SELECT COUNT(DISTINCT description) FROM PROVERB WHERE level=:level")
    suspend fun getProverbsCountByLevel(level: Int): Int
}