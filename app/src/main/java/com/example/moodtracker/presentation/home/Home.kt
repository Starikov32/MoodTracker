package com.example.moodtracker.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodtracker.presentation.ui.theme.DarkOrange
import com.example.moodtracker.presentation.weather.WeatherState
import com.example.moodtracker.presentation.weather.WeatherStatus

@Composable
fun Home(
    weatherState: WeatherState
) {
//    val viewModel: WeatherViewModel = viewModel()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.background(DarkOrange)
    ) {
        Text(
            text = "MoodTracker",
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.padding(8.dp))

        WeatherStatus(weatherState)

        Spacer(modifier = Modifier.padding(8.dp))

        HomeCard()
    }


}
