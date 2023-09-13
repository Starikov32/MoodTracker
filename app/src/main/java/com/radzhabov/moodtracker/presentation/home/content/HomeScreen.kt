package com.radzhabov.moodtracker.presentation.home.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.domain.weather.WeatherState
import com.radzhabov.moodtracker.presentation.home.weather.WeatherCard

@Composable
fun HomeScreen(
    weatherState: WeatherState,
    date: String,
    painterDownIcon: Painter,
    painterUpIcon: Painter,
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

                HomeContentCard(painterDownIcon, painterUpIcon)

            }
        }
    }
}
