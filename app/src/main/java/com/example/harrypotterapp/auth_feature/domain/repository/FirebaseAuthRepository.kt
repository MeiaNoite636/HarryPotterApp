package com.example.harrypotterapp.auth_feature.domain.repository

interface FirebaseAuthRepository {

    suspend fun createUser(email: String, password: String)

    suspend fun logIn(email: String, password: String)

    suspend fun recoverPassword(email: String)
}