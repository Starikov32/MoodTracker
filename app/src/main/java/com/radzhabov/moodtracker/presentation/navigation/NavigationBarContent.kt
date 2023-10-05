package com.radzhabov.moodtracker.presentation.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.domain.screen.Screen

@Composable
fun NavigationBarContent(
    screens: List<Screen>,
    selectedScreen: Int,
    onScreenSelected: (Int) -> Unit
) {
    NavigationBar(
        contentColor = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
    ) {
        screens.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedScreen == index,
                onClick = { onScreenSelected(index) },
                icon = { Icon(screen.icon, contentDescription = screen.label) },
            )
        }
    }
}