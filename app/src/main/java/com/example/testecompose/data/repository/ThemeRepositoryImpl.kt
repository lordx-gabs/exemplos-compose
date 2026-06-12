package com.example.testecompose.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.testecompose.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.map

class ThemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : ThemeRepository {
    private val isDarkModePreferences = booleanPreferencesKey("is_dark_mode")

    override val isDarkMode = dataStore.data
        .map {
            it[isDarkModePreferences] ?: false
        }

    override suspend fun toggleTheme() {
        dataStore.edit {
            it[isDarkModePreferences] = !(it[isDarkModePreferences] ?: false)
        }
    }
}