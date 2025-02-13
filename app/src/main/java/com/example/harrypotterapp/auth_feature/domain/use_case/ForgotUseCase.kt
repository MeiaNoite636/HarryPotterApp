package com.example.harrypotterapp.auth_feature.domain.use_case

import com.example.harrypotterapp.auth_feature.domain.repository.FirebaseAuthRepository
import javax.inject.Inject

class ForgotUseCase @Inject constructor(
    private val authRepository: FirebaseAuthRepository
) {
    suspend operator fun invoke(email: String) :Result<Unit>{
        return authRepository.forgotPassword(email)
    }
}