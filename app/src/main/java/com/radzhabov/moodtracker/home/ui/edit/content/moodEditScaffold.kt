package com.radzhabov.moodtracker.home.ui.edit.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.home.ui.edit.MoodEditEvent
import com.radzhabov.moodtracker.home.ui.viewmodel.MoodEditViewModel

@Composable
fun moodEditScaffold(
    viewModel: MoodEditViewModel
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(MoodEditEvent.OnSaveMoodClick)
            }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(R.string.save)
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

            customOutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.onEvent(MoodEditEvent.OnNameChange(it)) },
                label = { Text(text = stringResource(R.string.condition_name)) },
                placeholder = { Text(text = stringResource(R.string.enter_condition_name)) },
                keyboardOptions = KeyboardType.Text
            )

            customOutlinedTextField(
                value = viewModel.stateNumber,
                onValueChange = { viewModel.onEvent(MoodEditEvent.OnStateNumberChange(it)) },
                label = { Text(text = stringResource(R.string.condition_rating)) },
                placeholder = { Text(text = stringResource(R.string.enter_condition)) },
                keyboardOptions = KeyboardType.Number
            )
        }
    }
}
