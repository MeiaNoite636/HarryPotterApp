package com.example.harrypotterapp.auth_feature.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.auth_feature.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    private fun updateEmail(email: String) {
        _loginState.value = _loginState.value.copy(email = email)
    }

    private fun updatePassword(password: String) {
        _loginState.value = _loginState.value.copy(password = password)
    }

    private fun clearEmail() {
        _loginState.value = _loginState.value.copy(email = "")
    }

    private fun login(email: String, password: String) {
        _loginState.value = _loginState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                loginUseCase.invoke(email, password)
                _loginState.value = _loginState.value.copy(isSuccessful = true)
            } catch (ioException: IOException) {
                _loginState.value = _loginState.value.copy(error = ioException.message)
                Log.e("LoginViewModel", ioException.message, ioException)
            } catch (e: Exception) {
                _loginState.value = _loginState.value.copy(error = e.message)
                Log.e("LoginViewModel", e.message, e)
            } finally {
                _loginState.value = _loginState.value.copy(isLoading = false)
            }
        }
    }

    fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> {
                login(loginState.value.email, loginState.value.password)
            }

            is LoginEvent.ClearEmail -> {
                clearEmail()
            }

            is LoginEvent.EmailChanged -> {
                updateEmail(event.email)
            }
            is LoginEvent.PasswordChanged -> {
                updatePassword(event.password)
            }
        }
    }
}