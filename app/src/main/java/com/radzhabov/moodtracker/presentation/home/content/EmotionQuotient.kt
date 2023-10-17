package com.radzhabov.moodtracker.presentation.home.content

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmotionQuotient() {
    val languages = listOf("1", "2", "3", "4", "5")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(languages[0]) }

    Row(Modifier
        .padding(5.dp),
    ) {
        languages.forEach {text ->
            Column( Modifier.fillMaxWidth().height(56.dp), horizontalAlignment = Alignment.CenterHorizontally)
            {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text( text = text, fontSize = 22.sp )
            }
        }
    }
}