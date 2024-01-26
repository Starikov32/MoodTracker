package com.radzhabov.moodtracker.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.radzhabov.moodtracker.data.db.AppDatabase
import com.radzhabov.moodtracker.data.db.dao.MoodDao
import com.radzhabov.moodtracker.data.network.api.WeatherApi
import com.radzhabov.moodtracker.data.network.service.NetworkService
import com.radzhabov.moodtracker.data.repositories.MoodRepositoryImpl
import com.radzhabov.moodtracker.data.repositories.WeatherApiRepositoryImpl
import com.radzhabov.moodtracker.data.user.UserPreferencesManager
import com.radzhabov.moodtracker.domain.repositories.MoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val USER_NAME_KEY = stringPreferencesKey("user_name")
    private val USER_PASSWORD_KEY = stringPreferencesKey("user_password")

    @Provides
    fun provideDataStore(context: Context): DataStore<Preferences> {
        return context.applicationContext.preferencesDataStore(name = "user_preferences")
    }

    @Provides
    fun provideUserPreferencesManager(context: Context): UserPreferencesManager {
        return UserPreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideNetworkService() = NetworkService.getInstance()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getInstance(appContext)

    @Provides
    @Singleton
    fun provideMoodRepository(db: AppDatabase): MoodRepository {
        return MoodRepositoryImpl(db.moodDao())
    }

    @Provides
    @Singleton
    fun provideMoodDao(appDatabase: AppDatabase): MoodDao =
        appDatabase.moodDao()

    @Provides
    @Singleton
    fun provideWeatherApi(networkService: NetworkService) =
        networkService.weatherApi

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Provides
    @Singleton
    fun provideWeatherApiRepository(weatherApi: WeatherApi) = WeatherApiRepositoryImpl(weatherApi)
}
