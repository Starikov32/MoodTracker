package com.radzhabov.moodtracker.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.radzhabov.moodtracker.data.db.entities.MoodEntity

@Dao
interface MoodDao {

    @Insert
    suspend fun insertMoodCriteria(moodEntity: MoodEntity)

    @Transaction
    @Query("SELECT * FROM mood WHERE name = :name")
    suspend fun getMood(name: String): MoodEntity?
}