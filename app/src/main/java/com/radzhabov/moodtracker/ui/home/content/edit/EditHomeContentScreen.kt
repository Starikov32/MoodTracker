package com.radzhabov.moodtracker.ui.home.content.edit

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.screen.Screens
import com.radzhabov.moodtracker.ui.viewmodel.MoodViewModel

@Composable
fun EditHomeContentScreen(
    navController: NavController,
    moodViewModel: MoodViewModel,
    context: Context,
) {
    var name by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                fontSize = 24.sp,
                color = Color.Gray,
                text = stringResource(R.string.criteria_editing),
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(R.string.condition_name)) },
                placeholder = { Text(text = stringResource(R.string.enter_condition_name)) },
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
                value = state,
                onValueChange = { state = it },
                label = { Text(text = stringResource(R.string.condition_rating)) },
                placeholder = { Text(text = stringResource(R.string.enter_condition)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 13.dp),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )


            Row {
                Button(
                    onClick = { navController.navigate(Screens.BottomNavBar.route) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Button(
                    onClick = {
                        moodViewModel.insertMood(name, state.toInt())

                        Toast.makeText(
                            context,
                            "Save button is worked",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                }
            }
        }
    }
}
