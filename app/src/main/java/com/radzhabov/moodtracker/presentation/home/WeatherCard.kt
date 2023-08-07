package com.radzhabov.moodtracker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.weather.WeatherState

@Composable
fun WeatherCard(
    state: WeatherState,
    date: String,
    modifier: Modifier = Modifier,
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium),
            shape = RoundedCornerShape(size = 15.dp),
        ) {
            Row(
                modifier = modifier
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Column {
                    Text(
                        style = MaterialTheme.typography.titleSmall,
                        text = date,
                        color = Color.Black,
                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    Text(
                        text = stringResource(R.string.weather_city),
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .height(44.dp)
                        .width(52.dp)
                        .padding(end = 4.dp),
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 24.sp,
                    color = Color.Black,
                )
            }
        }
    }
}
