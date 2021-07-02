package com.composezabanzad.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proverb")
data class Proverb(
    @PrimaryKey
    val id: Int,
    val step: Int,
    val description: String,
    val mean: String,
    val hardness: Int
)
