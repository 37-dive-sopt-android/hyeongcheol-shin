package com.sopt.dive.ui.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.User
import com.sopt.dive.data.dataStore.MyProfileRepository
import com.sopt.dive.network.factory.ServicePool
import com.sopt.dive.network.model.signup.SignUpRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

class SignUpViewModel(
    private val repository: MyProfileRepository
) : ViewModel() {
    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState: StateFlow<SignUpUiState> = _signUpUiState.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent.asSharedFlow()

    val koreanRegex = Regex("^[ㄱ-ㅎㅏ-ㅣ가-힣]*$")

    fun updateInputUserId(inputUserId: String) {
        _signUpUiState.update {
            it.copy(inputUserId = inputUserId)
        }
    }

    fun updateInputUserPw(inputUserPw: String) {
        _signUpUiState.update {
            it.copy(inputUserPw = inputUserPw)
        }
    }

    fun updateInputUserDrinking(inputUserDrinking: String) {
        _signUpUiState.update {
            it.copy(inputUserDrinking = inputUserDrinking.filter { drinking -> drinking.isDigit() })
        }
    }

    fun updateInputUserNickname(inputUserNickname: String) {
        _signUpUiState.update {
            it.copy(inputUserNickname = inputUserNickname)
        }
    }

    fun updateInputUserName(inputUserName: String) {
        _signUpUiState.update {
            if (koreanRegex.matches(inputUserName)) {
                it.copy(inputUserName = inputUserName)
            } else {
                it
            }
        }
    }

    fun setToastEvent(event: String) {
        viewModelScope.launch {
            _toastEvent.emit(event)
        }
    }

    fun getSignUpUser(): User {
        val inputUserData = _signUpUiState.value
        return User(
            id = inputUserData.inputUserId,
            pw = inputUserData.inputUserPw,
            nickname = inputUserData.inputUserNickname,
            drinking = inputUserData.inputUserDrinking,
            name = inputUserData.inputUserName
        )
    }

    fun isSignUpCheck(): Boolean {
        val inputUserData = _signUpUiState.value
        return inputUserData.inputUserId.length in 6..10 &&
                inputUserData.inputUserPw.length in 8..12 &&
                inputUserData.inputUserNickname.isNotBlank() &&
                (inputUserData.inputUserDrinking.toIntOrNull() != null && inputUserData.inputUserDrinking.toInt() >= 0) &&
                inputUserData.inputUserName.isNotBlank()
    }


    fun signUp(onSignUpSuccess: () -> Unit) {
        if (isSignUpCheck()) {
            viewModelScope.launch {

                try {
                    _signUpUiState.update {
                        it.copy(isLoading = true)
                    }
                    val signUpUser = getSignUpUser()
                    val signUpRequest = SignUpRequest(
                        userName = "testUser_sas",
                        password = "Asdasdasd1!",
                        name = "TestShc",
                        email = "hcshin0717@naver.com",
                        age = 25
                    )

                    val response = ServicePool.signUpService.createAccount(signUpRequest)
                    if (response.success) {
                        repository.saveMyProfile(signUpUser)
                        repository.setSignInStatus(false)
                        setToastEvent("회원가입에 성공했습니다.")
                        onSignUpSuccess()
                    } else {
                        setToastEvent("회원가입에 실패하였습니다.")
                    }
                } catch (e: Exception) {
                    _signUpUiState.update {
                        Log.d("SHC", "${e.message}")
                        setToastEvent("네트워크 오류가 발생했습니다: ${e.message}")
                        it.copy(isLoading = false)
                    }
                } finally {
                    _signUpUiState.update {
                        it.copy(isLoading = false)
                    }
                }

            }
        } else {
            viewModelScope.launch {
                setToastEvent("조건을 확인해주세요")
            }
        }
    }
}