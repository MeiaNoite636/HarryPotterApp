package com.example.harrypotterapp.navigation.auth_graph.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.harrypotterapp.auth_feature.presentation.register.RegisterScreen
import kotlinx.serialization.Serializable

@Serializable
object RegisterDestination

fun NavGraphBuilder.registerScreenDestination() {
    composable<RegisterDestination> {
        RegisterScreen()
    }
}