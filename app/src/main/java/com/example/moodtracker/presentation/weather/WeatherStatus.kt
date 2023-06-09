package com.example.moodtracker.presentation.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moodtracker.presentation.ui.theme.DarkOrange
import com.example.moodtracker.presentation.ui.theme.TransparentWhite

@Composable
fun WeatherStatus(weatherState: WeatherState) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkOrange),
    ) {
        items(count = 4) {
            WeatherCard(
                state = weatherState,
                backgroundColor = TransparentWhite,
            )
        }
    }
}
