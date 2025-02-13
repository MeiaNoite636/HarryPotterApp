package com.example.harrypotterapp.auth_feature.di

import com.example.harrypotterapp.auth_feature.domain.repository.FirebaseAuthRepository
import com.example.harrypotterapp.auth_feature.domain.use_case.CreateUserUseCase
import com.example.harrypotterapp.auth_feature.domain.use_case.LoginUseCase
import com.example.harrypotterapp.auth_feature.domain.use_case.ForgotUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseUseCaseModule {

    @Provides
    fun provideLoginUseCase(firebaseAuthRepository: FirebaseAuthRepository): LoginUseCase {
        return LoginUseCase(firebaseAuthRepository)
    }

    @Provides
    fun provideRecoverUseCase(firebaseAuthRepository: FirebaseAuthRepository): ForgotUseCase {
        return ForgotUseCase(firebaseAuthRepository)
    }

    @Provides
    fun provideRegisterUseCase(firebaseAuthRepository: FirebaseAuthRepository): CreateUserUseCase {
        return CreateUserUseCase(firebaseAuthRepository)
    }
}