package com.radzhabov.moodtracker.authorization.ui.login

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun Login(
    context: Context,
    navController: NavController,
    viewModel: AuthorizationViewModel = hiltViewModel(),
) {
    val userName by viewModel.userNameFlow.collectAsState("")
    val userPassword by viewModel.userPasswordFlow.collectAsState("")

    val enteredUserNameState = remember { mutableStateOf(TextFieldValue(userName)) }
    val enteredPasswordState = remember { mutableStateOf(TextFieldValue(userPassword)) }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.login)
            )
            
            Spacer(modifier = Modifier.padding(16.dp))

            LoginTextField(
                value = enteredUserNameState.value,
                onValueChange = { enteredUserNameState.value = it },
                label = { Text(text = stringResource(R.string.login)) },
                placeholder = { Text(text = stringResource(R.string.enter_login)) },
            )

            Spacer(modifier = Modifier.padding(16.dp))

            LoginTextField(
                value = enteredPasswordState.value,
                onValueChange = { enteredPasswordState.value = it },
                label = { Text(text = stringResource(R.string.password)) },
                placeholder = { Text(text = stringResource(R.string.enter_password)) },
            )

            Spacer(modifier = Modifier.padding(16.dp))

            LoginButton(
                context = context,
                enteredUserNameState = enteredUserNameState.value,
                enteredPasswordState = enteredPasswordState.value,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}
