package com.radzhabov.moodtracker.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.presentation.home.animation.AnimationCard

@Composable
fun ExpandableCard(
    painterDownIcon: Painter,
    painterUpIcon: Painter,
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
                    text = "Еда",
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
