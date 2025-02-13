package com.example.harrypotterapp.auth_feature.presentation.forgot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypotterapp.auth_feature.domain.use_case.ForgotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val forgotUseCase: ForgotUseCase
) : ViewModel() {

    private val _forgotState = MutableStateFlow(ForgotState())
    val forgotState: StateFlow<ForgotState> = _forgotState.asStateFlow()

    private fun updateState(update: ForgotState.() -> ForgotState) {
        _forgotState.value = _forgotState.value.update()
    }

    private fun forgotPassword(email: String) {
        _forgotState.value = _forgotState.value.copy(isLoading = true)
        viewModelScope.launch {
            forgotUseCase.invoke(email).onSuccess {
                _forgotState.value = _forgotState.value.copy(
                    isSuccessful = true,
                    isLoading = false
                )
            }.onFailure {
                _forgotState.value = _forgotState.value.copy(
                    error = it.message,
                    isLoading = false
                )
            }

        }
    }

    fun handleEvent(event: ForgotEvent) {
        when (event) {
            is ForgotEvent.Forgot -> forgotPassword(forgotState.value.email)
            is ForgotEvent.ClearEmail -> updateState { copy(email = "") }
            is ForgotEvent.EmailChanged -> updateState { copy(email = event.email) }
        }
    }
}