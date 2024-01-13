package com.radzhabov.moodtracker.ui.home.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AnimationCard(
    isExpanded: Boolean,
) {
    var email by remember { mutableStateOf("") }

    AnimatedVisibility(
        visible = isExpanded,
        modifier = Modifier.padding(start = 5.dp),
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.Email,
                        contentDescription = "Email",
                        tint = Color.Black
                    )
                },
                placeholder = { Text(text = "Enter your email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 13.dp),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
        }
    }
}
