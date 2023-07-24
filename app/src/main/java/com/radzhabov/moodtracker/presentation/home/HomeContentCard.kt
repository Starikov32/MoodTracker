package com.radzhabov.moodtracker.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.presentation.theme.DarkOrange

@Composable
fun HomeContentCard(
    backgroundColor: Color
) {
    Card(
        backgroundColor = backgroundColor,
        contentColor = DarkOrange,
        modifier = Modifier
            .fillMaxSize()
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(size = 15.dp),
    ) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = "Home Content"
        )
    }
}