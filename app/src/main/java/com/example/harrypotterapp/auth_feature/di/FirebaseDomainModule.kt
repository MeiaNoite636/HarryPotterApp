package com.example.harrypotterapp.auth_feature.di

import com.example.harrypotterapp.auth_feature.data.repository.FirebaseAuthRepositoryImpl
import com.example.harrypotterapp.auth_feature.domain.repository.FirebaseAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseDomainModule {

    @Binds
    abstract fun bindFirebaseAuthRepository(
        firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl
    ): FirebaseAuthRepository

}