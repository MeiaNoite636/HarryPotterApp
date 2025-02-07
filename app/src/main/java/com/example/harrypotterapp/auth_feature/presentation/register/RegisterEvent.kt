package com.example.harrypotterapp.auth_feature.presentation.register


sealed interface RegisterEvent {
    data object Register : RegisterEvent
    data class ClearEmail(val email: String) : RegisterEvent
    data class EmailChanged(val email: String) : RegisterEvent
    data class PasswordChanged(val password: String) : RegisterEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterEvent
}