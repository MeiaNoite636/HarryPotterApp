package com.example.harrypotterapp.auth_feature.presentation.forgot

sealed interface ForgotEvent {
    data object Forgot : ForgotEvent
    data object ClearEmail : ForgotEvent
    data class EmailChanged(val email: String) : ForgotEvent
}