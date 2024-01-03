package com.radzhabov.moodtracker.ui.stats

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StatsScreen() {
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Stats screen")
    }

}
