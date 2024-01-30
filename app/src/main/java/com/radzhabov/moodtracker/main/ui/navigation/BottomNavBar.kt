package com.radzhabov.moodtracker.main.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.home.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.main.domain.Screen
import com.radzhabov.moodtracker.home.ui.HomeScreen
import com.radzhabov.moodtracker.settings.ui.SettingsScreen
import com.radzhabov.moodtracker.stats.ui.StatsScreen

@Composable
fun BottomNavBar(
    snackBarHostState: SnackbarHostState,
    navController: NavController,
    selectedScreen: Int,
    onScreenSelected: (Int) -> Unit,
    weatherState: CurrentWeatherCardModel?,
) {
    val screens: List<Screen> = listOf(
        Screen(label = stringResource(R.string.home), icon = painterResource(R.drawable.ic_home)),
        Screen(label = stringResource(R.string.statistics), icon = painterResource(R.drawable.ic_stats)),
        Screen(label = stringResource(R.string.settings), icon = painterResource(R.drawable.ic_settings))
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            NavigationBar(
                contentColor = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            ) {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedScreen == index,
                        onClick = {
                            onScreenSelected(index)
                        },
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(text = screen.label) },
                    )
                }
            }

        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (selectedScreen) {
            0 -> HomeScreen(
                modifier,
                { navController.navigate(it.route) },
                weatherState = weatherState
            )
            1 -> StatsScreen()
            2 -> SettingsScreen(innerPadding)
        }
    }
}
