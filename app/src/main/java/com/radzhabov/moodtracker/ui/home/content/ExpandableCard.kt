package com.radzhabov.moodtracker.ui.home.content

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.ui.viewmodel.MoodViewModel

@Composable
fun ExpandableCard(
    nameOfCard: String,
    moodViewModel: MoodViewModel,
    painterDownIcon: Painter,
    painterUpIcon: Painter,
    context: Context
) {
    var isExpanded by remember { mutableStateOf(false) }
    var state by remember { mutableIntStateOf(1) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(size = 15.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    fontSize = 24.sp,
                    text = "1",
                    modifier = Modifier
                        .height(44.dp)
                        .width(52.dp)
                        .padding(start = 4.dp, end = 4.dp)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Click first button",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = nameOfCard,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 5.dp),
                )
                Button(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.padding(end = 5.dp),
                ) {
                    Icon(
                        painter = if (isExpanded) painterUpIcon else painterDownIcon,
                        contentDescription = null
                    )
                }
            }

            AnimatedVisibility(
                visible = isExpanded,
                modifier = Modifier.padding(start = 5.dp),
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedTextField(
                        value = state.toString(),
                        onValueChange = { newValue ->
                            state = newValue.toIntOrNull() ?: 0
                        },
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
                }
            }

        }
    }
}
