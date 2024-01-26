package com.radzhabov.moodtracker.ui.authorization

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.data.user.UserPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Register(
    context: Context,
    userPreferencesManager: UserPreferencesManager,
    onRegistrationComplete: () -> Unit
) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
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
            value = password,
            onValueChange = { password = it },
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
            if (userName.isNotEmpty() && password.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    userPreferencesManager.saveUserData(userName, password)
                }
            }
        }) {
            Text(text = "Ok")
        }
    }

}