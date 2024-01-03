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
import com.radzhabov.moodtracker.domain.screen.Screen

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

@Preview(showBackground = true)
@Composable
fun NavigationBarContentPreview() {
    var selectedScreen by remember { mutableIntStateOf(0) }
    val screens: List<Screen> = listOf(
        Screen(label = "Home", icon = painterResource(R.drawable.ic_home)),
        Screen(label = "Stats", icon = painterResource(R.drawable.ic_stats)),
        Screen(label = "Settings", icon = painterResource(R.drawable.ic_settings))
    )

    NavigationBarContent(
        screens = screens,
        selectedScreen = selectedScreen,
        onScreenSelected = {
                newIndex ->
            selectedScreen = newIndex
        }
    )
}