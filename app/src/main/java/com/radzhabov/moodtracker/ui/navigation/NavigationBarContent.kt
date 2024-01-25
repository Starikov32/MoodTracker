package com.radzhabov.moodtracker.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.model.Screen

@Composable
fun NavigationBarContent(
    screens: List<Screen>,
    selectedScreen: Int,
    onScreenSelected: (Int) -> Unit,
) {
    NavigationBar(
        contentColor = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        screens.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedScreen == index,
                onClick = {
                    onScreenSelected(index)
                },
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(text = screen.label) },
            )
        }
    }
}
