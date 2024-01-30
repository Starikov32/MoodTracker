package com.radzhabov.moodtracker.authorization.ui.login

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.authorization.ui.viewmodel.AuthorizationViewModel
import com.radzhabov.moodtracker.home.domain.util.Routes
import com.radzhabov.moodtracker.main.ui.utils.showToast

@Composable
fun LoginButton(
    context: Context,
    enteredUserNameState: TextFieldValue,
    enteredPasswordState: TextFieldValue,
    viewModel: AuthorizationViewModel,
    navController: NavController,
) {
    Button(onClick = {
        val enteredUserName = enteredUserNameState.text
        val enteredPassword = enteredPasswordState.text
        viewModel.readUserData { userPreferences ->
            if ( userPreferences != null && userPreferences.userName == enteredUserName &&
                userPreferences.password == enteredPassword ) {
                viewModel.isLoggedIn = true
                navController.navigate(Routes.BOTTOM) {
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
