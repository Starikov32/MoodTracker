package com.radzhabov.moodtracker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.radzhabov.moodtracker.data.db.dao.MoodDao
import com.radzhabov.moodtracker.data.db.entities.MoodEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [MoodEntity::class]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun moodDao(): MoodDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).fallbackToDestructiveMigration().build()
        }

    }
}