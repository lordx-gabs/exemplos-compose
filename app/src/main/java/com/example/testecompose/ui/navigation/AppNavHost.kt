package com.example.testecompose.ui.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.testecompose.ui.features.login.LoginScreen
import com.example.testecompose.ui.features.main.MainScreen

@Composable
fun AppNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Login
    ) {
        composable<Login>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = EaseInOut
                    )
                )
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            LoginScreen(onNavigationToMain = { userTeste ->
                navHostController.navigate(Main(userTeste))
            })
        }

        composable<Main>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = EaseInOut
                    )
                )
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = EaseInOut
                    )
                )
            }
        ) { entry ->
            entry.toRoute<Main>().name?.let {
                Log.d("teste", it)
                MainScreen(onNavigationToLogin = {
                    navHostController.popBackStack()
                })
            } ?: LaunchedEffect(null) {
                navHostController.popBackStack()
            }
        }
    }
}