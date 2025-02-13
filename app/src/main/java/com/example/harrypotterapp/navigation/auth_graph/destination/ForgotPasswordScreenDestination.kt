package com.example.harrypotterapp.navigation.auth_graph.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.harrypotterapp.auth_feature.presentation.forgot.ForgotScreen
import kotlinx.serialization.Serializable

@Serializable
object ForgotPasswordDestination

fun NavGraphBuilder.forgotPasswordScreenDestination() {
    composable<ForgotPasswordDestination> {
        ForgotScreen()
    }
}