package com.example.harrypotterapp.navigation.auth_graph.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.harrypotterapp.auth_feature.presentation.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object LoginDestination

fun NavGraphBuilder.loginScreenDestination() {
    composable<LoginDestination> {
        LoginScreen(
            onNavigateToRegister = {},
            onNavigateToForgotPassword = {},
            loginToHomeGraph = {}
        )
    }
}