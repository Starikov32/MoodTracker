package com.radzhabov.moodtracker.ui.authorization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel

//@Composable
//fun UserPreferencesScreen(userPreferencesViewModel: AuthorizationViewModel = hiltViewModel()) {
//    val userName by userPreferencesViewModel.userNameFlow.collectAsState("")
//    val userPassword by userPreferencesViewModel.userPasswordFlow.collectAsState("")
//
//    val newUserNameState = remember { mutableStateOf(TextFieldValue(userName)) }
//    val newPasswordState = remember { mutableStateOf(TextFieldValue(userPassword)) }
//
//    Column(
//        modifier = Modifier.padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        OutlinedTextField(
//            value = newUserNameState.value,
//            onValueChange = { newUserNameState.value = it },
//            label = { Text("Username") }
//        )
//        OutlinedTextField(
//            value = newPasswordState.value,
//            onValueChange = { newPasswordState.value = it },
//            label = { Text("Password") }
//        )
//        Button(onClick = {
//            userPreferencesViewModel.saveUserData(newUserNameState.value.text, newPasswordState.value.text)
//        }) {
//            Text("Save")
//        }
//    }
//}