package com.example.testecompose.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.testecompose.ui.features.main.MainScreen

fun NavGraphBuilder.mainGraph(
    onNavigateToAuthGraph: () -> Unit,
    navController: NavController
) {
    navigation<MainGraph>(
        startDestination = Main
    ) {
        composable<Main> {
            MainScreen(
                onNavigationToLogin = onNavigateToAuthGraph,
            )
        }
    }
}