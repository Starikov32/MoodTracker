package com.radzhabov.moodtracker.authorization.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserPreferences(val userName: String, val password: String)

class UserPreferencesManager(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUserData(username: String, password: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = username
            preferences[USER_PASSWORD_KEY] = password
        }
    }

    fun readUserData(): Flow<UserPreferences?> {
        return dataStore.data.map { preferences ->
            UserPreferences(
                preferences[USER_NAME_KEY] ?: "",
                preferences[USER_PASSWORD_KEY] ?: ""
            )
        }
    }

    // Create a name flow to retrieve name from the preferences
    val userNameFlow: Flow<String> = dataStore.data.map {
        it[USER_NAME_KEY] ?: ""
    }

    // Create a password flow to retrieve password from the preferences
    val userPasswordFlow: Flow<String> = dataStore.data.map {
        it[USER_PASSWORD_KEY] ?: ""
    }

    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
    }
}
