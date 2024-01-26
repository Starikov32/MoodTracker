package com.radzhabov.moodtracker.ui.navigation

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.domain.util.Routes
import com.radzhabov.moodtracker.ui.authorization.Login
import com.radzhabov.moodtracker.ui.authorization.Registration
import com.radzhabov.moodtracker.ui.home.HomeScreen
import com.radzhabov.moodtracker.ui.settings.SettingsScreen
import com.radzhabov.moodtracker.ui.stats.StatsScreen
import com.radzhabov.moodtracker.ui.home.edit.MoodEdit

@Composable
fun AppNavigation(
    modifier: Modifier,
    context: Context,
    snackBarHostState: SnackbarHostState,
    navController: NavController,
    weatherState: CurrentWeatherCardModel?,
    padding: PaddingValues,
){
    var selectedScreen by remember { mutableIntStateOf(0) }

    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.LOGIN
    ){

        composable(route = Routes.REGISTRATION ){ Registration(context, navController) }

        composable(route = Routes.LOGIN ){ Login(context, navController) }

        composable(route = Routes.HOME){
            HomeScreen(
                modifier = modifier,
                onNavigate = { navController.navigate(it.route) },
                weatherState = weatherState,

            )
        }

        composable(route = Routes.STATS ){ StatsScreen() }

        composable(route = Routes.SETTINGS ){ SettingsScreen(padding) }

        composable(route = Routes.BOTTOM ){
            BottomNavBar(
                snackBarHostState,
                navController,
                selectedScreen,
                {
                    selectedScreen = it
                },
                weatherState
            )
        }

        composable(
            route = Routes.MOOD_EDIT + "?moodId={moodId}",
            arguments = listOf(
                navArgument(name = "moodId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            MoodEdit(
                onPopBackStack = { navController.popBackStack() },
            )
        }

    }
}