package com.radzhabov.moodtracker.ui.home.content.edit

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.screen.Screens
import com.radzhabov.moodtracker.ui.home.content.ExpandableCard
import com.radzhabov.moodtracker.ui.viewmodel.MoodViewModel

@Composable
fun EditHomeContentScreen(
    navController: NavController,
    moodViewModel: MoodViewModel,
    painterDownIcon: Painter,
    painterUpIcon: Painter,
    context: Context,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            item {
                Text(
                    fontSize = 24.sp,
                    text = stringResource(R.string.criteria_editing),
                )

            }

            item {
                ExpandableCard(stringResource(R.string.eat), moodViewModel, painterDownIcon, painterUpIcon, context)

                ExpandableCard(stringResource(R.string.sleep), moodViewModel, painterDownIcon, painterUpIcon, context)

                ExpandableCard(stringResource(R.string.health), moodViewModel, painterDownIcon, painterUpIcon, context)

                ExpandableCard(stringResource(R.string.music), moodViewModel, painterDownIcon, painterUpIcon, context)

            }

            item {
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
}
