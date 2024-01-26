package com.radzhabov.moodtracker.ui.authorization

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radzhabov.moodtracker.data.user.UserPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Register(
    context: Context,
    viewModel: AuthorizationViewModel = hiltViewModel(),
) {
    val userName by viewModel.userNameFlow.collectAsState("")
    val userPassword by viewModel.userPasswordFlow.collectAsState("")

    val newUserNameState = remember { mutableStateOf(TextFieldValue(userName)) }
    val newPasswordState = remember { mutableStateOf(TextFieldValue(userPassword)) }

    Column {
        OutlinedTextField(
            value = newUserNameState.value,
            onValueChange = { newUserNameState.value = it },
            label = { Text(text = "Логин") },
            placeholder = { Text(text = "Введите логин") },
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
            value = newPasswordState.value,
            onValueChange = { newPasswordState.value = it },
            label = { Text(text = "Пароль") },
            placeholder = { Text(text = "Введите пароль") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 13.dp),
            shape = RoundedCornerShape(16.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            )
        )

        Button(onClick = {
            if (newUserNameState.value.text.isNotEmpty() && newPasswordState.value.text.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.saveUserData(newUserNameState.value.text, newPasswordState.value.text)
                }
            } else {
                Toast.makeText(
                    context,
                    "Введите логин и пароль",
                    Toast.LENGTH_LONG
                ).show()
            }
        }) {
            Text(text = "Ok")
        }
    }

}