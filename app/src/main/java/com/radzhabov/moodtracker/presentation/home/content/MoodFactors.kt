package com.radzhabov.moodtracker.presentation.home.content

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.R

@Composable
fun MoodFactors(
    nameOfCard: String,
    context: Context,
    isExpend:Boolean,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(size = 15.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fontSize = 24.sp,
                text = "1",
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(start = 4.dp)
                    .clickable {
                        Toast
                            .makeText(
                                context,
                                "Click first button",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                textAlign = TextAlign.Center,
            )
            Text(
                text = nameOfCard,
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoodFactorsPreview() {
    val nameOfCard = "Еда"
    val isExpanded by remember { mutableStateOf(false) }
    val painterUpIcon = painterResource(id = R.drawable.ic_up)
    val painterDownIcon = painterResource(id = R.drawable.ic_down)

    MoodFactors(nameOfCard, LocalContext.current, isExpanded)
}