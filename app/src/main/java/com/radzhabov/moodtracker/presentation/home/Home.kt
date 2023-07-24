package com.radzhabov.moodtracker.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.radzhabov.moodtracker.presentation.theme.DarkOrange
import com.radzhabov.moodtracker.presentation.theme.TransparentWhite
import com.radzhabov.moodtracker.presentation.weather.WeatherState
import org.threeten.bp.LocalDateTime

@Composable
fun Home(
    weatherState: WeatherState
) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkOrange),
    ) {

        val currentDate = LocalDateTime.now()

        items(count = 5) {index ->
            val daysToSubtract = index * -1
            val dateToDisplay = currentDate.plusDays(daysToSubtract.toLong())

            WeatherCard(
                state = weatherState,
                date = dateToDisplay,
                backgroundColor = TransparentWhite,
            )
        }

    }
}