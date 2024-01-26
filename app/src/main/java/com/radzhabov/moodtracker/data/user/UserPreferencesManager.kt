package com.radzhabov.moodtracker.data.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserPreferences(val userName:String, val password:String)

object UserPreferencesManager {
    private val USER_NAME_KEY = stringPreferencesKey("user_name")
    private val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    suspend fun saveUserData(context: Context, username: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = username
            preferences[USER_PASSWORD_KEY] = password
        }
    }

    fun readUserData(context: Context): Flow<UserPreferences?> {
        return context.dataStore.data.map { preferences ->
            UserPreferences(
                preferences[USER_NAME_KEY] ?: "",
                preferences[USER_PASSWORD_KEY] ?: ""
            )
        }
    }

    suspend fun clearUserData(context: Context) {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
