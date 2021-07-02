package com.composezabanzad.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.composezabanzad.data.model.Proverb

@Dao
interface ProverbDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertProverb(proverb: Proverb)

    @Query("SELECT * FROM PROVERB WHERE id=:id")
    suspend fun getProverb(id: Int): Proverb

    @Query("SELECT * FROM PROVERB")
    suspend fun getAllProverbs(): List<Proverb>
}