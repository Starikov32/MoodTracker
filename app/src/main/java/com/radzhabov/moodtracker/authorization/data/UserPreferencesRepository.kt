package com.radzhabov.moodtracker.authorization.data

import com.radzhabov.moodtracker.authorization.data.datastore.UserPreferences
import com.radzhabov.moodtracker.authorization.data.datastore.UserPreferencesManager
import kotlinx.coroutines.flow.Flow

class UserPreferencesRepository(
    private val userPreferencesManager: UserPreferencesManager,
) {

    val userNameFlow: Flow<String> = userPreferencesManager.userNameFlow
    val userPasswordFlow: Flow<String> = userPreferencesManager.userPasswordFlow

    suspend fun saveUserData(username: String, password: String) {
        userPreferencesManager.saveUserData(username, password)
    }
    fun readUserData(): Flow<UserPreferences?> = userPreferencesManager.readUserData()

}