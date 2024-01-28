package com.radzhabov.moodtracker.domain.repositories

import com.radzhabov.moodtracker.data.db.entities.Mood
import kotlinx.coroutines.flow.Flow

interface MoodRepository {

    suspend fun insertMood(mood: Mood)

    suspend fun deleteMood(mood: Mood)

    suspend fun getMoodById(id: Int): Mood?

    fun getMoods(): Flow<List<Mood>>

}
