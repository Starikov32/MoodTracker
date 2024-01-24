package com.radzhabov.moodtracker.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.domain.screen.Screen
import com.radzhabov.moodtracker.ui.home.HomeScreen
import com.radzhabov.moodtracker.ui.settings.SettingsScreen
import com.radzhabov.moodtracker.ui.stats.StatsScreen
import com.radzhabov.moodtracker.ui.viewmodel.MoodListViewModel

@Composable
fun BottomNavBar(
    snackBarHostState: SnackbarHostState,
    viewModel: MoodListViewModel,
    navController: NavController,
    weatherState: CurrentWeatherCardModel?,
) {
    var selectedScreen by remember { mutableIntStateOf(0) }
    val screens: List<Screen> = listOf(
        Screen(label = "Home", icon = painterResource(R.drawable.ic_home)),
        Screen(label = "Stats", icon = painterResource(R.drawable.ic_stats)),
        Screen(label = "Settings", icon = painterResource(R.drawable.ic_settings))
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            NavigationBarContent(
                screens = screens,
                selectedScreen = selectedScreen,
                onScreenSelected = { newIndex ->
                    selectedScreen = newIndex
                }
            )
        }
    ) { innerPadding ->
        when (selectedScreen) {
            0 -> HomeScreen({ navController.navigate(it.route) }, viewModel, weatherState)
            1 -> StatsScreen()
            2 -> SettingsScreen(innerPadding)
        }
    }
}
