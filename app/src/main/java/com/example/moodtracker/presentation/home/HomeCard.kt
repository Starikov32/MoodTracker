package com.example.moodtracker.presentation.home

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
import com.example.moodtracker.presentation.ui.theme.DarkOrange

@Composable
fun HomeCard() {
    Card(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(size = 20.dp),
    ) {
        Card(
            backgroundColor = Color.White,
            modifier = Modifier
                .padding(15.dp)
                .shadow(
                    elevation = 8.dp,
                    ambientColor = Color.Black,
                    spotColor = Color.White
                ),
            shape = RoundedCornerShape(size = 15.dp),
        ) {
            Text(text = "Home Screen")
        }
    }

}
