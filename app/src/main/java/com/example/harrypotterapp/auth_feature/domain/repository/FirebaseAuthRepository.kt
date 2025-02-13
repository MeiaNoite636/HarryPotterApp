package com.example.harrypotterapp.auth_feature.domain.repository

interface FirebaseAuthRepository {

    suspend fun createUser(email: String, password: String) : Result<Unit>

    suspend fun logIn(email: String, password: String) : Result<Unit>

    suspend fun forgotPassword(email: String) : Result<Unit>
}