package com.radzhabov.moodtracker.ui.authorization

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.data.user.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val application: Application
): ViewModel() {
    private val userPreferencesManager = UserPreferencesManager

    fun getUserPreferences() {
        viewModelScope.launch {
            userPreferencesManager.saveUserData()
        }
    }
}