package com.radzhabov.moodtracker.main.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.radzhabov.moodtracker.authorization.data.UserPreferencesRepository
import com.radzhabov.moodtracker.main.data.db.AppDatabase
import com.radzhabov.moodtracker.main.data.db.dao.MoodDao
import com.radzhabov.moodtracker.home.data.network.api.WeatherApi
import com.radzhabov.moodtracker.home.data.network.service.NetworkService
import com.radzhabov.moodtracker.home.data.repositories.MoodRepositoryImpl
import com.radzhabov.moodtracker.home.data.repositories.WeatherApiRepositoryImpl
import com.radzhabov.moodtracker.authorization.data.datastore.UserPreferencesManager
import com.radzhabov.moodtracker.home.domain.repositories.MoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            produceFile = { context.preferencesDataStoreFile("user_preferences") })
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        userPreferencesManager: UserPreferencesManager
    ): UserPreferencesRepository = UserPreferencesRepository(userPreferencesManager)

    @Provides
    fun provideUserPreferencesManager(dataStore: DataStore<Preferences>): UserPreferencesManager {
        return UserPreferencesManager(dataStore)
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
