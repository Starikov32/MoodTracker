package com.radzhabov.moodtracker.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.radzhabov.moodtracker.data.db.entities.Mood
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMood(mood: Mood)

    @Delete
    suspend fun deleteMood(mood: Mood)

    @Query("SELECT * FROM mood WHERE id = :id")
    suspend fun getMoodById(id: Int): Mood?

    @Query("SELECT * FROM mood")
    fun getMoods(): Flow<List<Mood>>
}
