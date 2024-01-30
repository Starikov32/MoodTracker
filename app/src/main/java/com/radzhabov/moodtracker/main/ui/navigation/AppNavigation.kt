package com.radzhabov.moodtracker.main.ui.navigation

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.home.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.home.domain.util.Routes
import com.radzhabov.moodtracker.authorization.ui.login.Login
import com.radzhabov.moodtracker.authorization.ui.registration.Registration
import com.radzhabov.moodtracker.home.ui.HomeScreen
import com.radzhabov.moodtracker.settings.ui.SettingsScreen
import com.radzhabov.moodtracker.stats.ui.StatsScreen
import com.radzhabov.moodtracker.home.ui.edit.MoodEdit
import com.radzhabov.moodtracker.main.ui.utils.showToast

@Composable
fun AppNavigation(
    modifier: Modifier,
    snackBarHostState: SnackbarHostState,
    navController: NavController,
    weatherState: CurrentWeatherCardModel?,
) {
    val context = LocalContext.current
    val padding = PaddingValues()
    var selectedScreen by remember { mutableIntStateOf(0) }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    backDispatcher?.let {
        BackHandler(it) {
            if (navController.currentBackStackEntry?.destination?.route == Routes.BOTTOM) {
                context.showToast(R.string.correct_screen)
            } else {
                navController.popBackStack()
            }
        }
    }

    NavHost(
        navController = navController as NavHostController,
        startDestination = Routes.LOGIN
    ) {

        composable(route = Routes.REGISTRATION) { Registration(context, navController) }

        composable(route = Routes.LOGIN) { Login(context, navController) }

        composable(route = Routes.HOME) {
            HomeScreen(
                modifier = modifier,
                onNavigate = { navController.navigate(it.route) },
                weatherState = weatherState,
            )
        }

        composable(route = Routes.STATS ) { StatsScreen() }

        composable(route = Routes.SETTINGS ) { SettingsScreen(padding) }

        composable(route = Routes.BOTTOM ) {
            BottomNavBar(
                snackBarHostState,
                navController,
                selectedScreen,
                { selectedScreen = it },
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

@Composable
fun BackHandler(
    dispatcher: OnBackPressedDispatcher,
    onBackEvent: () -> Unit
) {
    DisposableEffect(key1 = dispatcher) {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackEvent()
            }
        }
        dispatcher.addCallback(callback)
        onDispose {
            callback.remove()
        }
    }
}
