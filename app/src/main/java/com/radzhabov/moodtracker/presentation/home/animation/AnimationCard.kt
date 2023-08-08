package com.radzhabov.moodtracker.presentation.home.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.R

@Composable
fun AnimationCard(
    isExpanded: Boolean,
) {
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
            Image(
                painter = painterResource(id = R.drawable.ic_sad),
                contentDescription = null,
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp),
            )

            Image(
                painter = painterResource(id = R.drawable.ic_neutral),
                contentDescription = null,
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp),
            )

            Image(
                painter = painterResource(id = R.drawable.ic_slight),
                contentDescription = null,
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp),
            )

            Image(
                painter = painterResource(id = R.drawable.ic_smile),
                contentDescription = null,
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp),
            )

            Image(
                painter = painterResource(id = R.drawable.ic_laugh),
                contentDescription = null,
                modifier = Modifier
                    .height(44.dp)
                    .width(52.dp)
                    .padding(end = 4.dp),
            )
        }

    }
}