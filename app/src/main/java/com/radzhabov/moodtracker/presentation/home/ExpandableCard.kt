package com.radzhabov.moodtracker.presentation.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.presentation.home.animation.AnimationCard

@Composable
fun ExpandableCard(
    nameOfCard: String,
    painterDownIcon: Painter,
    painterUpIcon: Painter,
    context: Context
) {
    var isExpanded by remember { mutableStateOf(false) }

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
                            Toast.makeText(
                                context,
                                "Click first button",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = nameOfCard,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 5.dp),
                    color = Color.Black
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
            AnimationCard(isExpanded = isExpanded)
        }
    }
}
