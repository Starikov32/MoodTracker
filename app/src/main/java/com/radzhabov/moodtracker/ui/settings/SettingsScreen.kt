package com.radzhabov.moodtracker.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    innerPadding: PaddingValues
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Settings Screen")
    }
}