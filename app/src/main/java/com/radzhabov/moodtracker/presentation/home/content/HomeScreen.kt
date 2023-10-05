package com.radzhabov.moodtracker.presentation.home.content

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
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
        Card {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                WeatherCard(
                    date = date,
                    painter = data.weatherType.iconRes,
                    temperatureCelsius = data.temperatureCelsius,
                )

                Spacer(modifier = Modifier.padding(5.dp))

                HomeContentCard(navController, context, isExpend)

            }
        }
    }
}
