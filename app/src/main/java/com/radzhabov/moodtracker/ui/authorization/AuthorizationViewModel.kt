package com.radzhabov.moodtracker.ui.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.data.user.UserPreferences
import com.radzhabov.moodtracker.data.user.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager,
) : ViewModel() {

    val userNameFlow: Flow<String> = userPreferencesManager.userNameFlow
    val userPasswordFlow: Flow<String> = userPreferencesManager.userPasswordFlow

    fun saveUserData(username: String, password: String) {
        viewModelScope.launch {
            userPreferencesManager.saveUserData(username, password)
        }
    }

    fun readUserData(callback: (UserPreferences?) -> Unit) {
        viewModelScope.launch {
            userPreferencesManager.readUserData().collect {
                callback(it)
            }
        }
    }
}