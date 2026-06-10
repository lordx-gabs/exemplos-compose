package com.example.testecompose.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.testecompose.ui.features.login.LoginScreen

fun NavGraphBuilder.authGraph(
    onNavigateToMainGraph: (user: String) -> Unit,
    navController: NavController
) {
    navigation<AuthGraph>(
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(onNavigationToMain = { userTeste ->
                onNavigateToMainGraph(userTeste)
            })
        }
    }
}