package com.radzhabov.moodtracker.presentation.home.weather

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.R

class WeatherCard(
    private val date: String,
    private val painter: Int,
    private val temperatureCelsius: Double,
    private val modifier: Modifier = Modifier,
) {
    @Composable
    fun ContentCard() {
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

                Column {
                    Text(
                        style = MaterialTheme.typography.titleSmall,
                        text = date,
                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    Text(
                        text = stringResource(R.string.weather_city),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = painter),
                    contentDescription = null,
                    modifier = Modifier
                        .height(44.dp)
                        .width(52.dp)
                        .padding(end = 4.dp),
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = "$temperatureCelsius°C",
                    fontSize = 24.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherCardPreview(){
    val date = "16.10.2023, 11:00"

}