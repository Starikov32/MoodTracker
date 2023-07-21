package com.radzhabov.moodtracker.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.radzhabov.moodtracker.domain.weather.WeatherType
import org.threeten.bp.LocalDateTime

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "time") val time: LocalDateTime,
    @ColumnInfo(name = "temperatureCelsius") val temperatureCelsius: Double,
    @ColumnInfo(name = "pressure") val pressure: Double,
    @ColumnInfo(name = "windSpeed") val windSpeed: Double,
    @ColumnInfo(name = "humidity") val humidity: Double,
    @ColumnInfo(name = "weatherType") val weatherType: WeatherType,
)
