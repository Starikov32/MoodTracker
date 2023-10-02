package com.radzhabov.moodtracker.presentation.navigation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.screen.Screen
import com.radzhabov.moodtracker.presentation.home.content.HomeScreen
import com.radzhabov.moodtracker.presentation.settings.SettingsScreen
import com.radzhabov.moodtracker.presentation.stats.StatsScreen
import com.radzhabov.moodtracker.domain.weather.WeatherState

@Composable
fun BottomNavBar(
    navController: NavController,
    weatherState: WeatherState,
    date: String,
    painterDownIcon: Painter,
    painterUpIcon: Painter,
    context: Context,
) {
    var selectedScreen by remember { mutableIntStateOf(0) }
    val snackBarHostState = remember { SnackbarHostState() }

    val screens = listOf(
        Screen(label = "Home", icon = painterResource(R.drawable.ic_home)),
        Screen(label = "Stats", icon = painterResource(R.drawable.ic_stats)),
        Screen(label = "Settings", icon = painterResource(R.drawable.ic_settings))
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            NavigationBar(
                contentColor = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
            ) {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedScreen == index,
                        onClick = { selectedScreen = index },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                    )
                }
            }
        }
    ) { innerPadding ->
        when (selectedScreen) {
            0 -> HomeScreen(weatherState, date, painterDownIcon, painterUpIcon, context)
            1 -> StatsScreen()
            2 -> SettingsScreen(innerPadding)
        }
    }
}