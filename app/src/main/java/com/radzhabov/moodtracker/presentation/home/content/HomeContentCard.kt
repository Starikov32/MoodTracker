package com.radzhabov.moodtracker.presentation.home.content

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.radzhabov.moodtracker.R
import com.radzhabov.moodtracker.domain.screen.Screens

@Composable
fun HomeContentCard(
    navController: NavController,
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
                onClick = { navController.navigate(Screens.EditHomeContentScreen.route) },
                modifier = Modifier
                    .padding(end = 16.dp, top = 8.dp)
                    .align(Alignment.End),
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )
            }

            Text(
                fontSize = 64.sp,
                text = "1",
                modifier = Modifier
                    .height(44.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(52.dp)
                    .padding(start = 4.dp, end = 4.dp)
                    .clickable {
                        Toast
                            .makeText(
                                context,
                                "Click 1 button",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                textAlign = TextAlign.Center,
            )

            MoodFactors("Еда", context)

            MoodFactors("Сон", context)

            MoodFactors("Здоровье", context)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentCardPreview() {
    val navController = rememberNavController()
    val painterUpIcon = painterResource(id = R.drawable.ic_up)
    val painterDownIcon = painterResource(id = R.drawable.ic_down)

    HomeContentCard(navController, painterDownIcon, painterUpIcon, LocalContext.current)
}