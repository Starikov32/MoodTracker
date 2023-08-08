package com.radzhabov.moodtracker.presentation.home

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.presentation.ExpandableCard

@Composable
fun HomeContentCard(
    painterDownIcon: Painter,
    painterUpIcon: Painter,
) {
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
                Image(
                    painter = painterResource(id = R.drawable.ic_sad),
                    contentDescription = null,
                    modifier = Modifier
                        .height(104.dp)
                        .width(104.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_laugh),
                    contentDescription = null,
                    modifier = Modifier
                        .height(104.dp)
                        .width(104.dp)
                )
            }
            ExpandableCard("Еда", painterDownIcon, painterUpIcon)

            ExpandableCard("Сон", painterDownIcon, painterUpIcon)

            ExpandableCard("Здоровье", painterDownIcon, painterUpIcon)

        }
    }
}