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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Registration(
    context: Context,
    navController: NavController,
    viewModel: AuthorizationViewModel = hiltViewModel(),
) {
    val userName by viewModel.userNameFlow.collectAsState("")
    val userPassword by viewModel.userPasswordFlow.collectAsState("")

    val newUserNameState = remember { mutableStateOf(TextFieldValue(userName)) }
    val newPasswordState = remember { mutableStateOf(TextFieldValue(userPassword)) }

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
                text = stringResource(R.string.registration)
            )
            
            Spacer(modifier = Modifier.padding(16.dp))

            TextField(
                value = newUserNameState.value,
                onValueChange = { newUserNameState.value = it },
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
                value = newPasswordState.value,
                onValueChange = { newPasswordState.value = it },
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
                if (newUserNameState.value.text.isNotEmpty() && newPasswordState.value.text.isNotEmpty()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.saveUserData(newUserNameState.value.text, newPasswordState.value.text)
                    }
                    navController.navigate(Routes.LOGIN)
                } else {
                    context.showToast(R.string.enter_registration_data)
                }
            }) {
                Text(text = stringResource(R.string.ok))
            }
        }
    }
}