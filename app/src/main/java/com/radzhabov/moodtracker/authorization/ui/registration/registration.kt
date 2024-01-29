package com.radzhabov.moodtracker.authorization.ui.registration

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.authorization.ui.viewmodel.AuthorizationViewModel

@Composable
fun registration(
    context: Context,
    navController: NavController,
    viewModel: AuthorizationViewModel = hiltViewModel(),
) {
    val newUserNameState = remember { mutableStateOf(TextFieldValue()) }
    val newPasswordState = remember { mutableStateOf(TextFieldValue()) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.registration)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            registrationTextField(
                value = newUserNameState.value,
                onValueChange = { newUserNameState.value = it },
                label = { Text(text = stringResource(R.string.login)) },
                placeholder = { Text(text = stringResource(R.string.enter_login)) },
            )

            registrationTextField(
                value = newPasswordState.value,
                onValueChange = { newPasswordState.value = it },
                label = { Text(text = stringResource(R.string.password)) },
                placeholder = { Text(text = stringResource(R.string.enter_password)) },
            )

            registrationButton(
                context = context,
                newUserNameState = newUserNameState.value,
                newPasswordState = newPasswordState.value,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}
