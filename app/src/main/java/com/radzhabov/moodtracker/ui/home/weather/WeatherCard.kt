package com.radzhabov.moodtracker.ui.home.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel

@Composable
fun WeatherCard(
    weatherState: CurrentWeatherCardModel?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .shadow(elevation = Dp.Unspecified, shape = RoundedCornerShape(size = 16.dp))
            .fillMaxWidth(),
    ) {
        Row(
            modifier = modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            weatherState?.let { data ->
                Column {
                    data.dateTime?.let {
                        Text(
                            style = MaterialTheme.typography.titleSmall,
                            text = it,
                        )
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    data.nameCity?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = rememberImagePainter(
                        data = "https:" + data.imageUrl,
                        builder = {
                            placeholder(R.drawable.ic_placeholder)
                            error(R.drawable.ic_error)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .height(44.dp)
                        .width(52.dp)
                        .padding(end = 4.dp),
                )


                Spacer(modifier = Modifier.weight(1f))

                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "${data.currentTemp}°C",
                    fontSize = 24.sp,
                )
            }
        }
    }
}