package com.radzhabov.moodtracker.ui.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.domain.screen.Screen
import com.radzhabov.moodtracker.domain.screen.Screens
import com.radzhabov.moodtracker.ui.home.content.HomeScreen
import com.radzhabov.moodtracker.ui.settings.SettingsScreen
import com.radzhabov.moodtracker.ui.stats.StatsScreen
import com.radzhabov.moodtracker.ui.home.content.EditHomeContentScreen

@Composable
fun AppNavigation(
    snackBarHostState: SnackbarHostState,
    screens: List<Screen>,
    navController: NavController,
    weatherState: CurrentWeatherCardModel?,
    painterDownIcon: Painter,
    painterUpIcon: Painter,
    padding: PaddingValues,
    context: Context,

    ){
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.BottomNavBar.route
    ){

        composable(route = Screens.Home.route){
            HomeScreen(navController, weatherState, context)
        }

        composable(route = Screens.Stats.route ){ StatsScreen() }

        composable(route = Screens.Settings.route ){ SettingsScreen(padding) }

        composable(route = Screens.BottomNavBar.route ){
            BottomNavBar(snackBarHostState, screens, navController, weatherState, context)
        }

        composable(route = Screens.EditHomeContentScreen.route) {
            EditHomeContentScreen(navController, painterDownIcon, painterUpIcon, context)
        }

    }
}