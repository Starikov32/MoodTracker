package com.radzhabov.moodtracker.main.ui.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(stringRes: Int, isLong: Boolean = false) {
    Toast.makeText(
        this,
        stringRes,
        if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    ).show()
}

fun Context.showToast(text: String, isLong: Boolean = false) {
    Toast.makeText(
        this,
        text,
        if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    ).show()
}
