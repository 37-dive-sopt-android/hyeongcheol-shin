package com.sopt.dive.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.User
import com.sopt.dive.data.dataStore.MyProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: MyProfileRepository
) : ViewModel() {
    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    init {
        viewModelScope.launch {
            launch {
                repository.getIsSignedIn().collect { isSignedIn ->
                    _authUiState.update {
                        it.copy(isSignedIn = isSignedIn)
                    }
                }
            }
            launch {
                repository.getMyProfile().collect { myProfile ->
                    _authUiState.update {
                        it.copy(myProfile = myProfile)
                    }
                }
            }
        }
    }

    fun updateInputUserId(inputUserId: String) {
        _authUiState.update {
            it.copy(inputUserId = inputUserId)
        }
    }

    fun updateInputUserPw(inputUserPw: String) {
        _authUiState.update {
            it.copy(inputUserPw = inputUserPw)
        }
    }

    fun signUp(user: User) {
        viewModelScope.launch {
            repository.saveMyProfile(user)
            repository.setSignInStatus(false)
            _authUiState.update {
                it.copy(
                    myProfile = user
                )
            }
        }
    }

    fun signIn(onSignInSuccess: () -> Unit) {
        val inputUserId = _authUiState.value.inputUserId
        val inputUserPw = _authUiState.value.inputUserPw
        viewModelScope.launch {
            val registeredUser = repository.getMyProfile().first()
            if (inputUserId == registeredUser.id && inputUserPw == registeredUser.pw) {
                repository.setSignInStatus(true)
                onSignInSuccess()
            }
        }
    }
}