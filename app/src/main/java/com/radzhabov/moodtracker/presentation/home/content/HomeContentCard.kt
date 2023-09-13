package com.radzhabov.moodtracker.presentation.home.content

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radzhabov.moodtracker.presentation.home.ExpandableCard

@Composable
fun HomeContentCard(
    painterDownIcon: Painter,
    painterUpIcon: Painter,
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier 
            .fillMaxSize()
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(size = 15.dp),
    ) {
        Column {
            Row (
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(top = 24.dp, bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ){
                Text(
                    fontSize = 64.sp,
                    text = "1",
                    modifier = Modifier
                        .height(44.dp)
                        .width(52.dp)
                        .padding(start = 4.dp, end = 4.dp)
                        .clickable {
                            Toast.makeText(
                                context,
                                "Click 1 button",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                )
            }

            ExpandableCard("Еда", painterDownIcon, painterUpIcon)

            ExpandableCard("Сон", painterDownIcon, painterUpIcon)

            ExpandableCard("Здоровье", painterDownIcon, painterUpIcon)

        }
    }
}