package com.example.moodtracker.di

import com.example.moodtracker.data.repository.WeatherRepositoryImpl
import com.example.moodtracker.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepositoryWeather(
        weatherRepositoryImpl: WeatherRepositoryImpl,
    ): WeatherRepository
}
