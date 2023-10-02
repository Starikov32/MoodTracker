package com.radzhabov.moodtracker.presentation.home.animation

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radzhabov.moodtracker.presentation.home.content.EmotionQuotient

@Composable
fun AnimationCard(
    isExpanded: Boolean,
    context: Context,
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

            EmotionQuotient(
                "1",
                4,
                4,
                context,
                "1"
            )

            EmotionQuotient(
                "2",
                0,
                4,
                context,
                "2"
            )

            EmotionQuotient(
                "3",
                0,
                4,
                context,
                "3"
            )

            EmotionQuotient(
                "4",
                0,
                4,
                context,
                "4"
            )

            EmotionQuotient(
                "5",
                0,
                4,
                context,
                "5"
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationCardPreview(){
    val isExpanded by remember { mutableStateOf(false) }

    AnimationCard(isExpanded, LocalContext.current)
}