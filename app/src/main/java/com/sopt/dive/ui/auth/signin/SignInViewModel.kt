package com.sopt.dive.ui.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.User
import com.sopt.dive.data.dataStore.MyProfileRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val repository: MyProfileRepository
) : ViewModel() {
    private val _signInUiState = MutableStateFlow(SignInUiState())
    val signInUiState: StateFlow<SignInUiState> = _signInUiState.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            launch {
                repository.getIsSignedIn().collect { isSignedIn ->
                    setIsSignedIn(isSignedIn)
                }
            }
            launch {
                repository.getMyProfile().collect { myProfile ->
                    setMyProfile(myProfile)
                }
            }
        }
    }

    fun setIsSignedIn(isSignedIn: Boolean) {
        _signInUiState.update {
            it.copy(isSignedIn = isSignedIn)
        }
    }

    fun setMyProfile(myProfile: User) {
        _signInUiState.update {
            it.copy(myProfile = myProfile)
        }
    }

    fun updateInputUserId(inputUserId: String) {
        _signInUiState.update {
            it.copy(inputUserId = inputUserId)
        }
    }

    fun updateInputUserPw(inputUserPw: String) {
        _signInUiState.update {
            it.copy(inputUserPw = inputUserPw)
        }
    }

    fun setToastEvent(event: String) {
        viewModelScope.launch {
            _toastEvent.emit(event)
        }
    }

    fun isSignedInCheck(autoSignInSuccess: () -> Unit, autoSignInFail: () -> Unit) {
        if (_signInUiState.value.isSignedIn) {
            autoSignInSuccess()
            setToastEvent("자동 로그인에 성공했습니다.")
        } else {
            autoSignInFail()
        }
    }

    fun signIn(onSignInSuccess: () -> Unit) {
        val inputUserId = _signInUiState.value.inputUserId
        val inputUserPw = _signInUiState.value.inputUserPw
        viewModelScope.launch {
            val registeredUser = repository.getMyProfile().first()
            if (inputUserId == registeredUser.id && inputUserPw == registeredUser.pw) {
                repository.setSignInStatus(true)
                onSignInSuccess()
                setToastEvent("로그인에 성공했습니다.")
            } else {
                setToastEvent("아이디와 비밀번호를 확인해주세요")
            }
        }
    }
}