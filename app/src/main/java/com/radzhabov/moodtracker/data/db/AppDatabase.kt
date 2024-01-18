package com.radzhabov.moodtracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.radzhabov.moodtracker.data.db.dao.MoodDao
import com.radzhabov.moodtracker.data.db.entities.MoodEntity
import kotlin.properties.Delegates

@Database(
    version = 1,
    entities = [MoodEntity::class]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun moodDao(): MoodDao

    companion object {
        private var instance: AppDatabase by Delegates.notNull()

        fun getInstance(context: Context): AppDatabase {
            return instance
        }

        fun initialize(context: Context) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).fallbackToDestructiveMigration().build()
        }

    }
}