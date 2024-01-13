package com.radzhabov.moodtracker.di

import android.app.Application
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.radzhabov.moodtracker.data.db.AppDatabase
import com.radzhabov.moodtracker.data.db.dao.MoodDao
import com.radzhabov.moodtracker.data.network.service.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNetworkService() = NetworkService.getInstance()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getInstance(appContext)

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
}
