package com.example.moodtracker.presentation.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moodtracker.presentation.theme.DarkOrange
import com.example.moodtracker.presentation.theme.TransparentWhite
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun Weather(weatherState: WeatherState) {

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkOrange),
    ) {
        items(count = 5) {
            WeatherCard(
                state = weatherState,
                dateTimeFormatter = DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm"),
                backgroundColor = TransparentWhite,
            )
        }
    }
}
