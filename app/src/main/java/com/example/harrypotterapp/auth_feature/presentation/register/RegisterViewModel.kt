package com.example.harrypotterapp.auth_feature.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.auth_feature.domain.use_case.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    private fun updateState(update: RegisterState.() -> RegisterState) {
        _registerState.value = _registerState.value.update()
    }

    private fun createUser(email: String, password: String) {
        _registerState.value = _registerState.value.copy(isLoading = true)
        viewModelScope.launch {
            createUserUseCase.invoke(email, password).onSuccess {
                _registerState.value = _registerState.value.copy(
                    isSuccessful = true,
                    isLoading = false
                )
            }.onFailure {
                _registerState.value = _registerState.value.copy(
                    error = it.message,
                    isLoading = false
                )
            }
        }
    }

    fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Register -> {
                createUser(registerState.value.email, registerState.value.password)
            }

            is RegisterEvent.ClearUserName -> {
                updateState { copy(username = "") }
            }

            is RegisterEvent.ClearEmail -> {
                updateState { copy(email = "") }
            }

            is RegisterEvent.EmailChanged -> {
                updateState { copy(email = event.email) }
            }

            is RegisterEvent.PasswordChanged -> {
                updateState { copy(password = event.password) }
            }

            is RegisterEvent.ConfirmPasswordChanged -> {
                updateState { copy(confirmPassword = event.confirmPassword) }
            }

            is RegisterEvent.UserNameChanged -> {
                updateState { copy(username = event.username) }
            }
        }
    }
}