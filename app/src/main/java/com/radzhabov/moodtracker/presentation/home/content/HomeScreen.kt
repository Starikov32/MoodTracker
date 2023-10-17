package com.radzhabov.moodtracker.presentation.home.content

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.radzhabov.moodtracker.domain.weather.WeatherState
import com.radzhabov.moodtracker.presentation.home.weather.WeatherCard

@Composable
fun HomeScreen(
    navController: NavController,
    weatherState: WeatherState,
    date: String,
    context: Context,
    isExpend: Boolean,
) {
    weatherState.weatherInfo?.currentWeatherData?.let { data ->
        val weatherCard = WeatherCard(
            date = date,
            painter = data.weatherType.iconRes,
            temperatureCelsius = data.temperatureCelsius,
        )
        val homeContentCard = HomeContentCard(navController, context, isExpend)

        Box (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) {
            Column {
                weatherCard.ContentCard()

                Spacer(modifier = Modifier.padding(5.dp))

                homeContentCard.ContentCard()

            }
        }
    }
}
