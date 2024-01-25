package com.radzhabov.moodtracker.ui.home.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.util.UiEvent
import com.radzhabov.moodtracker.ui.viewmodel.MoodEditViewModel

@Composable
fun MoodEdit(
    onPopBackStack: () -> Unit,
    viewModel: MoodEditViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action,
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MoodEditEvent.OnSaveMoodClick)
            }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
             .padding(innerPadding)
        ) {
            Text(
                fontSize = 24.sp,
                color = Color.Gray,
                text = stringResource(R.string.criteria_editing),
            )

            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.onEvent(MoodEditEvent.OnNameChange(it)) },
                label = { Text(text = stringResource(R.string.condition_name)) },
                placeholder = { Text(text = stringResource(R.string.enter_condition_name)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 13.dp),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            OutlinedTextField(
                value = viewModel.stateNumber,
                onValueChange = { viewModel.onEvent(MoodEditEvent.OnStateNumberChange(it)) },
                label = { Text(text = stringResource(R.string.condition_rating)) },
                placeholder = { Text(text = stringResource(R.string.enter_condition)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 13.dp),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )

        }

    }
}
