package com.example.testecompose.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Main

@Serializable
data class Details(
    val name: String?
)

@Serializable
object AuthGraph

@Serializable
object MainGraph