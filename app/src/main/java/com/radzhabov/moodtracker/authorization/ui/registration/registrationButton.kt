package com.radzhabov.moodtracker.authorization.ui.registration

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.authorization.ui.viewmodel.AuthorizationViewModel
import com.radzhabov.moodtracker.home.domain.util.Routes
import com.radzhabov.moodtracker.main.ui.utils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun registrationButton(
    context: Context,
    newUserNameState: TextFieldValue,
    newPasswordState: TextFieldValue,
    viewModel: AuthorizationViewModel,
    navController: NavController,
) {
    Button(
        onClick = {
            if (newUserNameState.text.isNotEmpty() && newPasswordState.text.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.saveUserData(newUserNameState.text, newPasswordState.text)
                }
                navController.navigate(Routes.LOGIN)
            } else {
                context.showToast(R.string.enter_registration_data)
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.ok))
    }
}
