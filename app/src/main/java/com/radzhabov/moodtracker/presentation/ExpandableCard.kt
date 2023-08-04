package com.radzhabov.moodtracker.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.presentation.theme.TransparenOrange

@Composable
fun ExpandableCard() {
    var isExpanded by remember { mutableStateOf(false) }
    val painterDownIcon = painterResource(id = R.drawable.ic_down)
    val painterUpIcon = painterResource(id = R.drawable.ic_up)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
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
                    text = "Заголовок карточки",
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
            AnimatedVisibility(
                visible = isExpanded,
                modifier = Modifier.padding(start = 5.dp),
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = "Раскрывающееся содержимое карточки",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        }
    }
}
