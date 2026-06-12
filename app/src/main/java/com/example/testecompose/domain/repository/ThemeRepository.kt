package com.example.testecompose.domain.repository

import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    val isDarkMode: Flow<Boolean>
    suspend fun toggleTheme()
}