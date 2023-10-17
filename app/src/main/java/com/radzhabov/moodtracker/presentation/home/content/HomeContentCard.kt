package com.radzhabov.moodtracker.presentation.home.content

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.radzhabov.moodtracker.domain.screen.Screens

class HomeContentCard(
    private val navController: NavController,
    private val context: Context,
    private val isExpend: Boolean,
){
    @Composable
    fun ContentCard() {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    onClick = { navController.navigate(Screens.EditHomeContentScreen.route) },
                    modifier = Modifier
                        .padding(end = 16.dp, top = 8.dp)
                        .align(Alignment.End),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = null
                    )
                }

                Text(
                    fontSize = 64.sp,
                    text = "1",
                    modifier = Modifier
                        .height(44.dp)
                        .align(Alignment.CenterHorizontally)
                        .width(52.dp)
                        .padding(start = 4.dp, end = 4.dp)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Click 1 button",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                    textAlign = TextAlign.Center,
                )

                MoodFactors("Еда", context)

                MoodFactors("Сон", context)

                MoodFactors("Здоровье", context)

            }
        }
    }
}