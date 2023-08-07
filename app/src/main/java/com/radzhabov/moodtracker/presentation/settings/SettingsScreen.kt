package com.radzhabov.moodtracker.presentation.settings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    innerPadding: PaddingValues
) {
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Settings Screen")
    }
}