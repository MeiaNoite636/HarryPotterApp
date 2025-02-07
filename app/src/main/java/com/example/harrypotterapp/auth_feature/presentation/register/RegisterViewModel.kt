package com.example.harrypotterapp.auth_feature.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.auth_feature.domain.use_case.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    private fun updateEmail(email: String) {
        _registerState.value = _registerState.value.copy(email = email)
    }

    private fun updatePassword(password: String) {
        _registerState.value = _registerState.value.copy(password = password)
    }

    private fun updateConfirmPassword(confirmPassword: String) {
        _registerState.value = _registerState.value.copy(confirmPassword = confirmPassword)
    }

    private fun clearEmail() {
        _registerState.value = _registerState.value.copy(email = "")
    }

    private fun createUser(email: String, password: String) {
        _registerState.value = _registerState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                createUserUseCase.invoke(email, password)
                _registerState.value = _registerState.value.copy(isSuccessful = true)
            } catch (ioException: IOException) {
                _registerState.value = _registerState.value.copy(error = ioException.message)
                Log.e("LoginViewModel", ioException.message, ioException)
            } catch (e: Exception) {
                _registerState.value = _registerState.value.copy(error = e.message)
                Log.e("LoginViewModel", e.message, e)
            } finally {
                _registerState.value = _registerState.value.copy(isLoading = false)
            }
        }
    }

    fun handleEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.Register -> {
                createUser(registerState.value.email, registerState.value.password)
            }

            is RegisterEvent.ClearEmail -> {
                clearEmail()
            }

            is RegisterEvent.EmailChanged -> {
                updateEmail(event.email)
            }

            is RegisterEvent.PasswordChanged -> {
                updatePassword(event.password)
            }

            is RegisterEvent.ConfirmPasswordChanged -> {
                updateConfirmPassword(event.confirmPassword)
            }
        }
    }
}