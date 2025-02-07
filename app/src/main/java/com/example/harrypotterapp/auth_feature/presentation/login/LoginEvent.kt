package com.example.harrypotterapp.auth_feature.presentation.login


sealed interface LoginEvent {
    data object Login : LoginEvent
    data object ClearEmail : LoginEvent
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
}