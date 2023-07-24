package com.radzhabov.moodtracker.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.presentation.theme.DarkOrange
import com.radzhabov.moodtracker.presentation.weather.WeatherState
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

@Composable
fun WeatherCard(
    state: WeatherState,
    date: LocalDateTime,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    state.weatherInfo?.currentWeatherData?.let { data ->
        Card(
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            contentColor = DarkOrange,
            modifier = modifier.padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    fontSize = 15.sp,
                    text = date.format(DateTimeFormatter.ofPattern("dd.LL.yyyy\nHH:mm")),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 4.dp),
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Text(
                    text = stringResource(R.string.weather_city),
                    fontSize = 15.sp,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(16.dp))

//                Image(
//                    painter = painterResource(id = data.weatherType.iconRes),
//                    contentDescription = null,
//                    modifier = Modifier.width(24.dp),
//                )

                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 25.sp,
                    color = Color.Black,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = data.weatherType.weatherDescription,
                    fontSize = 15.sp,
                    color = Color.Black,
                )
            }
        }
    }
}
