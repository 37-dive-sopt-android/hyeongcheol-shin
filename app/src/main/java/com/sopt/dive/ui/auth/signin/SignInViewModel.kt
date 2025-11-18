package com.sopt.dive.ui.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dataStore.MyProfileRepository
import com.sopt.dive.network.factory.ServicePool
import com.sopt.dive.network.model.request.SignInRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val repository: MyProfileRepository,
) : ViewModel() {
    private val _signInUiState = MutableStateFlow(SignInUiState())
    val signInUiState: StateFlow<SignInUiState> = _signInUiState.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.getIsSignedIn().collect { isSignedIn ->
                setIsSignedIn(isSignedIn)
            }
        }
    }

    fun setIsSignedIn(isSignedIn: Boolean) {
        _signInUiState.update {
            it.copy(isSignedIn = isSignedIn)
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

    fun setIsLoading(state: Boolean) {
        _signInUiState.update {
            it.copy(isLoading = state)
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
            try {
                setIsLoading(true)
                val signInRequest = SignInRequest(
                    userName = inputUserId,
                    password = inputUserPw
                )
                val signInResponse = ServicePool.signInService.signIn(signInRequest)
                if (signInResponse.success) {
                    repository.setSignInStatus(true)
                    repository.saveMyId(signInResponse.data.userId)
                    updateInputUserId("")
                    updateInputUserPw("")
                    setToastEvent("로그인에 성공했습니다.")
                    onSignInSuccess()
                } else {
                    setToastEvent("로그인에 실패했습니다.")
                }
            } catch (e: Exception) {
                setToastEvent("네트워크 오류가 발생했습니다")
                Log.e("SHC", "${e.message}")
            } finally {
                setIsLoading(false)
            }
        }
    }
}