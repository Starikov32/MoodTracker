package com.radzhabov.moodtracker.main.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood")
data class Mood(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val stateNumber: String,
)
