package com.example.harrypotterapp.navigation.auth_graph


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.harrypotterapp.navigation.auth_graph.destination.LoginDestination
import com.example.harrypotterapp.navigation.auth_graph.destination.forgotPasswordScreenDestination
import com.example.harrypotterapp.navigation.auth_graph.destination.loginScreenDestination
import com.example.harrypotterapp.navigation.auth_graph.destination.registerScreenDestination
import kotlinx.serialization.Serializable


@Serializable
object AuthGraph

fun NavGraphBuilder.authGraph() {
    navigation<AuthGraph>(startDestination = LoginDestination) {

        loginScreenDestination()

        registerScreenDestination()

        forgotPasswordScreenDestination()

    }
}



