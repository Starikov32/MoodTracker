package com.radzhabov.moodtracker.home.ui.edit

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.radzhabov.moodtracker.home.domain.util.UiEvent
import com.radzhabov.moodtracker.home.ui.edit.content.moodEditScaffold
import com.radzhabov.moodtracker.home.ui.viewmodel.MoodEditViewModel

@Composable
fun moodEdit(
    onPopBackStack: () -> Unit,
    viewModel: MoodEditViewModel = hiltViewModel(),
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action,
                    )
                }
                else -> Unit
            }
        }
    }
    
    moodEditScaffold(viewModel = viewModel)
}
