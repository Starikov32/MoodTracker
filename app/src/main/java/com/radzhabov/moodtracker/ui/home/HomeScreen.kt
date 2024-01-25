package com.radzhabov.moodtracker.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.domain.model.CurrentWeatherCardModel
import com.radzhabov.moodtracker.domain.util.UiEvent
import com.radzhabov.moodtracker.ui.home.moodlist.MoodItem
import com.radzhabov.moodtracker.ui.home.weather.WeatherCard
import com.radzhabov.moodtracker.ui.viewmodel.MoodListViewModel

@Composable
fun HomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: MoodListViewModel,
    weatherState: CurrentWeatherCardModel?,
) {
    val moods = viewModel.mood.collectAsState(initial = emptyList())
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    val result = snackBarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action,
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(MoodListEvent.OnUndoDeleteClick)
                    }
                }
                is UiEvent.Navigate -> { onNavigate(event) }
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MoodListEvent.OnAddMoodClick)
            }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        Column {
            WeatherCard(weatherState)

            Spacer(modifier = Modifier.padding(5.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(moods.value) {mood ->
                    MoodItem(
                        mood = mood,
                        onEvent = viewModel::onEvent,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                viewModel.onEvent(MoodListEvent.OnMoodClick(mood))
                            }
                            .padding(8.dp),
                    )
                }
            }
        }
    }
}
