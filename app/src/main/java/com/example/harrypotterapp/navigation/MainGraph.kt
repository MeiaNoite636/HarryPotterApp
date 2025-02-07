package com.example.harrypotterapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.harrypotterapp.navigation.auth_graph.AuthGraph
import com.example.harrypotterapp.navigation.auth_graph.authGraph

@Composable
fun MainGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AuthGraph
    ) {
        authGraph()
    }
}