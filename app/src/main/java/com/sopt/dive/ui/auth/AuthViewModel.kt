package com.sopt.dive.ui.auth

import androidx.lifecycle.ViewModel
import com.sopt.dive.ui.main.home.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel() {
    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    fun setInputUserId(inputUserId: String) {
        _authUiState.value = _authUiState.value.copy(
            inputUserId = inputUserId
        )
    }
}