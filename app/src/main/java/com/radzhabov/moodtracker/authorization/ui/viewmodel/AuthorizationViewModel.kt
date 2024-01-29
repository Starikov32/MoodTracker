package com.radzhabov.moodtracker.authorization.ui.viewmodel

import android.app.Application
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.moodtracker.authorization.data.UserPreferencesRepository
import com.radzhabov.moodtracker.authorization.data.datastore.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val application: Application
) : ViewModel() {

    val userNameFlow: Flow<String> = userPreferencesRepository.userNameFlow
    val userPasswordFlow: Flow<String> = userPreferencesRepository.userPasswordFlow
    private val preferences = PreferenceManager.getDefaultSharedPreferences(application)

    var isLoggedIn: Boolean
        get() = preferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = preferences.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    suspend fun saveUserData(username: String, password: String) {
        userPreferencesRepository.saveUserData(username, password)
    }

    fun readUserData(callback: (UserPreferences?) -> Unit) {
        viewModelScope.launch {
            userPreferencesRepository.readUserData().collect {
                callback(it)
            }
        }
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }
}
