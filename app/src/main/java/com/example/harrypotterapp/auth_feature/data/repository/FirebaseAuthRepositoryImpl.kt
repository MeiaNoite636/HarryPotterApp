package com.example.harrypotterapp.auth_feature.data.repository

import com.example.harrypotterapp.auth_feature.domain.repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): FirebaseAuthRepository {
    override suspend fun createUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun logIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun recoverPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }
}