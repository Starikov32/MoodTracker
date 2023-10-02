package com.radzhabov.moodtracker.presentation.home.content

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmotionQuotient(
    quotient: String,
    startPadding: Int,
    endPadding: Int,
    context: Context,
    toastCount: String
) {
    Text(
        text = quotient,
        modifier = Modifier
            .height(44.dp)
            .width(52.dp)
            .padding(start = startPadding.dp, end = endPadding.dp)
            .clickable {
                Toast
                    .makeText(
                        context,
                        "Click $toastCount button",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            },
    )
}