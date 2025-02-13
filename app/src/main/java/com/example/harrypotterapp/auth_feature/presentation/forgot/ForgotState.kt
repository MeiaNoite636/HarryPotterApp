package com.example.harrypotterapp.auth_feature.presentation.forgot

data class ForgotState(
    val email: String = "",
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val error: String? = null
)
