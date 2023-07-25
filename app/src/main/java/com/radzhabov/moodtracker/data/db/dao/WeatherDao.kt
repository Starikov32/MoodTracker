package com.radzhabov.moodtracker.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.radzhabov.moodtracker.data.db.entities.WeatherEntity
import org.threeten.bp.LocalDateTime

@Dao
interface WeatherDao {

    @Transaction
    @Query("SELECT * FROM weather WHERE time = :time")
    suspend fun getWeather(time: LocalDateTime): WeatherEntity?

    @Insert
    suspend fun addWeather(weatherEntity: WeatherEntity)

}