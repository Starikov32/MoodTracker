package com.radzhabov.moodtracker.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.presentation.ExpandableCard

@Composable
fun HomeContentCard(
    painterDownIcon: Painter,
    painterUpIcon: Painter,
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(size = 15.dp),
    ) {
        ExpandableCard(painterDownIcon, painterUpIcon)
    }
}