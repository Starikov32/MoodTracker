package com.radzhabov.moodtracker.ui.authorization

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.util.Routes
import com.radzhabov.moodtracker.ui.utils.showToast

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

            TextField(
                value = enteredUserNameState.value,
                onValueChange = { enteredUserNameState.value = it },
                label = { Text(text = stringResource(R.string.login)) },
                placeholder = { Text(text = stringResource(R.string.enter_login)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.padding(16.dp))

            TextField(
                value = enteredPasswordState.value,
                onValueChange = { enteredPasswordState.value = it },
                label = { Text(text = stringResource(R.string.password)) },
                placeholder = { Text(text = stringResource(R.string.enter_password)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Button(onClick = {
                val enteredUserName = enteredUserNameState.value.text
                val enteredPassword = enteredPasswordState.value.text
                viewModel.readUserData { userPreferences ->
                    if ( userPreferences != null && userPreferences.userName == enteredUserName &&
                        userPreferences.password == enteredPassword ) {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) {
                                inclusive = true
                            }
                        }
                    } else {
                        navController.navigate(Routes.REGISTRATION)
                        context.showToast(R.string.account_not_found_please_register)
                    }
                }

            }) {
                Text(text = stringResource(R.string.enter))
            }
        }
    }
}
