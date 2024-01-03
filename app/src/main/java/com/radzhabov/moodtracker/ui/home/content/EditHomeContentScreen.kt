package com.radzhabov.moodtracker.ui.home.content

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.radzhabov.moodtracker.domain.screen.Screens

@Composable
fun EditHomeContentScreen(
    navController: NavController,
    painterDownIcon: Painter,
    painterUpIcon: Painter,
    context: Context,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Button(
                onClick = { navController.navigate(Screens.BottomNavBar.route) },
                modifier = Modifier
                    .padding(end = 16.dp, top = 8.dp)
                    .align(Alignment.End),
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null
                )
            }

            ExpandableCard("Еда", painterDownIcon, painterUpIcon, context)

            ExpandableCard("Сон", painterDownIcon, painterUpIcon, context)

            ExpandableCard("Здоровье", painterDownIcon, painterUpIcon, context)

            Button(
                onClick = {
                    Toast.makeText(
                        context,
                        "Save button is worked",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(text = "Save")

                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )

            }
        }
    }
}
