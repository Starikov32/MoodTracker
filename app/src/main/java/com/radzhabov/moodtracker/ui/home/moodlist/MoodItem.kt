package com.radzhabov.moodtracker.ui.home.moodlist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.data.db.entities.Mood
import com.radzhabov.moodtracker.ui.home.MoodListEvent
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun MoodItem(
    mood: Mood,
    onEvent: (MoodListEvent) -> Unit,
    modifier: Modifier,
) {
    Card(
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text =  mood.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text =  mood.stateNumber,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            IconButton(
                modifier = Modifier.padding(end = 8.dp),
                onClick = {
                    onEvent(MoodListEvent.OnDeleteMoodClick(mood))
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                )
            }
        }
        mood.stateNumber
    }
}

@Preview(showBackground = true)
@Composable
fun MoodItemPreview() {
    val mood = Mood(
        id = 1,
        name = "Пример",
        stateNumber = "1",
    )

    MoodItem(
        mood = mood,
        onEvent = {},
        modifier = Modifier.fillMaxWidth()
    )
}
