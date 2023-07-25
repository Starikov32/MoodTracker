package com.radzhabov.moodtracker.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.presentation.theme.DarkOrange
import com.radzhabov.moodtracker.presentation.theme.TransparentWhite
import com.radzhabov.moodtracker.presentation.weather.WeatherState
import org.threeten.bp.LocalDateTime

@Composable
fun HomeScreen(
    weatherState: WeatherState
) {
    val currentDate = LocalDateTime.now()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkOrange),
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            reverseLayout = true,
        ) {

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

        Spacer(modifier = Modifier.padding(5.dp))

        HomeContentCard(backgroundColor = Color.White)

    }
}