package com.example.moodtracker.presentation.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moodtracker.R
import com.example.moodtracker.data.model.Screen
import com.example.moodtracker.presentation.home.Home
import com.example.moodtracker.presentation.settings.Settings
import com.example.moodtracker.presentation.stats.Stats
import com.example.moodtracker.presentation.ui.theme.DarkOrange
import com.example.moodtracker.presentation.weather.Weather
import com.example.moodtracker.presentation.weather.WeatherState

@Composable
fun BottomNavBar(
    weatherState: WeatherState
) {
    var selectedScreen by remember { mutableStateOf(0) }
    val scaffoldState = rememberScaffoldState()

    val screens = listOf(
        Screen(label = "Home", icon = painterResource(R.drawable.ic_home)),
        Screen(label = "Stats", icon = painterResource(R.drawable.ic_stats)),
        Screen(label = "Weather", icon = painterResource(R.drawable.ic_weather)),
        Screen(label = "Settings", icon = painterResource(R.drawable.ic_settings))
    )

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigation(
                backgroundColor = DarkOrange,
                contentColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            ) {
                screens.forEachIndexed { index, screen ->
                    BottomNavigationItem(
                        selected = selectedScreen == index,
                        onClick = { selectedScreen = index },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedScreen) {
            0 -> Home(weatherState)
            1 -> Stats(innerPadding)
            2 -> Weather()
            3 -> Settings()
        }
    }
}