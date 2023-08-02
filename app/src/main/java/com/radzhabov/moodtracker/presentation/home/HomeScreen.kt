package com.radzhabov.moodtracker.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.presentation.theme.DarkOrange
import com.radzhabov.moodtracker.presentation.theme.TransparentWhite
import com.radzhabov.moodtracker.domain.weather.WeatherState

@Composable
fun HomeScreen(
    weatherState: WeatherState,
    date: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkOrange),
    ) {
        WeatherCard(
            state = weatherState,
            date = date,
            backgroundColor = TransparentWhite,
        )

        Spacer(modifier = Modifier.padding(5.dp))

        HomeContentCard(backgroundColor = Color.White)

    }
}
