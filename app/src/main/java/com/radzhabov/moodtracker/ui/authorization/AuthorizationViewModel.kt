package com.radzhabov.moodtracker.ui.authorization

import android.app.Application
import android.preference.PreferenceManager
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
    private val application: Application
) : ViewModel() {

    val userNameFlow: Flow<String> = userPreferencesManager.userNameFlow
    val userPasswordFlow: Flow<String> = userPreferencesManager.userPasswordFlow
    private val preferences = PreferenceManager.getDefaultSharedPreferences(application)

    var isLoggedIn: Boolean
        get() = preferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = preferences.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    suspend fun saveUserData(username: String, password: String) {
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

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
