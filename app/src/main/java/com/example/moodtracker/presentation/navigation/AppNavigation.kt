package com.example.moodtracker.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moodtracker.domain.screen.Screens
import com.example.moodtracker.presentation.home.Home
import com.example.moodtracker.presentation.settings.Settings
import com.example.moodtracker.presentation.stats.Stats
import com.example.moodtracker.presentation.weather.Weather
import com.example.moodtracker.presentation.weather.WeatherState

@Composable
fun AppNavigation(
    navController: NavController,
    weatherState: WeatherState
){
    val padding = PaddingValues()

    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.BottomNavBar.route
    ){
        composable(route = Screens.Home.route){ Home(weatherState) }

        composable(route = Screens.Stats.route ){ Stats(padding) }

        composable(route = Screens.Weather.route ){ Weather() }

        composable(route = Screens.Settings.route ){ Settings() }

        composable(route = Screens.BottomNavBar.route ){ BottomNavBar(weatherState) }

    }
}