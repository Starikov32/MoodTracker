package com.radzhabov.moodtracker.ui.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.data.user.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager
): ViewModel() {

    fun saveUserData(userName: String, password: String) {
        viewModelScope.launch {
            userPreferencesManager.saveUserData(userName, password)
        }
    }
}