package com.example.harrypotterapp.auth_feature.presentation.register


sealed interface RegisterEvent {
    data object Register : RegisterEvent
    data object ClearUserName : RegisterEvent
    data object ClearEmail : RegisterEvent
    data class UserNameChanged(val username: String) : RegisterEvent
    data class EmailChanged(val email: String) : RegisterEvent
    data class PasswordChanged(val password: String) : RegisterEvent
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterEvent
}