package com.radzhabov.moodtracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.radzhabov.moodtracker.data.db.dao.WeatherDao
import com.radzhabov.moodtracker.data.db.entities.WeatherEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [WeatherEntity::class]
)
abstract class AppDatabase: RoomDatabase(){

    abstract fun weatherDao(): WeatherDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build().also { instance = it }
        }
    }

}