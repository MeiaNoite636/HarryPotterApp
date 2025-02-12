package com.example.harrypotterapp.navigation.auth_graph


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import com.example.harrypotterapp.navigation.auth_graph.destination.LoginDestination
import com.example.harrypotterapp.navigation.auth_graph.destination.forgotPasswordScreenDestination
import com.example.harrypotterapp.navigation.auth_graph.destination.loginScreenDestination
import com.example.harrypotterapp.navigation.auth_graph.destination.navigateToForgotPasswordScreen
import com.example.harrypotterapp.navigation.auth_graph.destination.navigateToRegisterScreen
import com.example.harrypotterapp.navigation.auth_graph.destination.registerScreenDestination
import kotlinx.serialization.Serializable


@Serializable
object AuthGraph

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<AuthGraph>(startDestination = LoginDestination) {

        loginScreenDestination(
            onNavigateToRegister = { navController.navigateToRegisterScreen() },
            onNavigateToForgotPassword = { navController.navigateToForgotPasswordScreen() },
            loginToHomeGraph = {  }
        )

        registerScreenDestination()

        forgotPasswordScreenDestination()

    }
}



