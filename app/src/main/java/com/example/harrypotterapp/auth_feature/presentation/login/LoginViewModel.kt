package com.example.harrypotterapp.auth_feature.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.auth_feature.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private fun updateState(update: LoginState.() -> LoginState) {
        _loginState.value = _loginState.value.update()
    }

    private fun login(email: String, password: String) {
        _loginState.value = _loginState.value.copy(isLoading = true)
        viewModelScope.launch {
            loginUseCase.invoke(email, password).onSuccess {
                _loginState.value = _loginState.value.copy(
                    isSuccessful = true,
                    isLoading = false
                )
            }.onFailure {
                _loginState.value = _loginState.value.copy(
                    error = it.message,
                    isLoading = false
                )
            }
        }
    }

    fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> {
                login(loginState.value.email, loginState.value.password)
            }

            is LoginEvent.ClearEmail -> {
                updateState { copy(email = "") }
            }

            is LoginEvent.EmailChanged -> {
                updateState { copy(email = event.email) }
            }

            is LoginEvent.PasswordChanged -> {
                updateState { copy(password = event.password) }
            }
        }
    }
}