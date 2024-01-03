package com.radzhabov.moodtracker.ui.home.content

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.ui.home.weather.WeatherCard

@Composable
fun HomeScreen(
    navController: NavController,
    weatherState: CurrentWeatherCardModel?,
    context: Context,
) {
    val weatherCard = WeatherCard(weatherState)
    val homeContentCard = HomeContentCard(navController, context)

    Box (
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column {
            weatherCard.ContentCard()

            Spacer(modifier = Modifier.padding(5.dp))

            homeContentCard.ContentCard()

        }
    }
}
