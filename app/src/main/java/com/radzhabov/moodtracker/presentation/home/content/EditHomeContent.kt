package com.radzhabov.moodtracker.presentation.home.content

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.R

@Composable
fun EditHomeContent(
    painterDownIcon: Painter,
    painterUpIcon: Painter,
    context: Context
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .shadow(elevation = 8.dp),
        shape = RoundedCornerShape(size = 15.dp),
    ) {
        Column {
            Button(
                onClick = {  },
                modifier = Modifier
                    .padding(end = 16.dp, top = 8.dp)
                    .align(Alignment.End),
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null
                )
            }

            ExpandableCard("Еда", painterDownIcon, painterUpIcon, context)

            ExpandableCard("Сон", painterDownIcon, painterUpIcon, context)

            ExpandableCard("Здоровье", painterDownIcon, painterUpIcon, context)

            Button(
                onClick = {  },
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                Text(text = "Save")

                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditHomeContentPreview() {
    val painterUpIcon = painterResource(id = R.drawable.ic_up)
    val painterDownIcon = painterResource(id = R.drawable.ic_down)

    EditHomeContent(painterDownIcon, painterUpIcon, LocalContext.current)
}