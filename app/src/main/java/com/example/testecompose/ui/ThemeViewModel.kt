package com.example.testecompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testecompose.domain.repository.ThemeRepository
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val themeRepository: ThemeRepository
) : ViewModel() {
    val darkTheme = themeRepository.isDarkMode

    fun toggleTheme() {
        viewModelScope.launch {
            themeRepository.toggleTheme()
        }
    }
}