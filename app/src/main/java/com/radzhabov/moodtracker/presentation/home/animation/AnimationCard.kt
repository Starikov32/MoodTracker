package com.radzhabov.moodtracker.presentation.home.animation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AnimationCard(
    isExpanded: Boolean,
) {
    val context = LocalContext.current

    AnimatedVisibility(
        visible = isExpanded,
        modifier = Modifier.padding(start = 5.dp),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
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
                    },
            )

            Text(
                text = "2",
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        Toast.makeText(
                            context,
                            "Click 2 button",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
            )

            Text(
                text = "3",
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        Toast.makeText(
                            context,
                            "Click 3 button",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
            )

            Text(
                text = "4",
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        Toast.makeText(
                            context,
                            "Click 4 button",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
            )

            Text(
                text = "5",
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp)
                    .clickable {
                        Toast.makeText(
                            context,
                            "Click 5 button",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
            )

        }
    }
}