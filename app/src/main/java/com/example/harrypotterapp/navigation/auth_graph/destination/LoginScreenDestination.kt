package com.example.harrypotterapp.navigation.auth_graph.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.harrypotterapp.auth_feature.presentation.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object LoginDestination

fun NavGraphBuilder.loginScreenDestination(
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    loginToHomeGraph: () -> Unit
) {
    composable<LoginDestination> {
        LoginScreen(
            onNavigateToRegister = { onNavigateToRegister() },
            onNavigateToForgotPassword = { onNavigateToForgotPassword() },
            loginToHomeGraph = { loginToHomeGraph() }
        )
    }
}

fun NavHostController.navigateToRegisterScreen(){
    navigate(RegisterDestination)
}

fun NavHostController.navigateToForgotPasswordScreen(){
    navigate(ForgotPasswordDestination)
}

fun NavHostController.navigateToHomeGraph(){
    //navigate(HomeGraph)
}

