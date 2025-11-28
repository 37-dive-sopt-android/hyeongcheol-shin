package com.sopt.dive.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.User
import com.sopt.dive.data.dataStore.MyProfileRepository
import com.sopt.dive.network.factory.ServicePool
import com.sopt.dive.network.model.request.SignUpRequest
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

    private val emailRegex = Regex(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    )

    fun updateInputUserName(inputUserName: String) {
        _signUpUiState.update {
            it.copy(inputUserName = inputUserName)
        }
    }

    fun updateInputUserPw(inputUserPw: String) {
        _signUpUiState.update {
            it.copy(inputUserPw = inputUserPw)
        }
    }

    fun updateInputUserNickname(inputUserNickname: String) {
        _signUpUiState.update {
            it.copy(inputUserNickname = inputUserNickname)
        }
    }

    fun updateInputUserEmail(inputUserEmail: String) {
        _signUpUiState.update {
            it.copy(inputUserEmail = inputUserEmail)
        }
    }

    fun updateInputUserAge(inputUserAge: String) {
        _signUpUiState.update {
            it.copy(inputUserAge = inputUserAge.filter { it.isDigit() }.toInt())
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
            name = inputUserData.inputUserName,
            pw = inputUserData.inputUserPw,
            nickname = inputUserData.inputUserNickname,
            email = inputUserData.inputUserEmail,
            age = inputUserData.inputUserAge
        )
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length !in 8..64) return false

        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar
    }

    fun signUpAvailable(): Boolean {
        val inputUserData = _signUpUiState.value
        return (inputUserData.inputUserName.length <= 50 && inputUserData.inputUserName.isNotBlank()) &&
                isValidPassword(inputUserData.inputUserPw) &&
                (inputUserData.inputUserNickname.isNotBlank() && inputUserData.inputUserNickname.length <= 100)&&
                (inputUserData.inputUserEmail.isNotBlank() && emailRegex.matches(inputUserData.inputUserEmail)) &&
                (inputUserData.inputUserAge >= 0)
    }


    fun signUp(onSignUpSuccess: () -> Unit) {
        if (signUpAvailable()) {
            viewModelScope.launch {

                try {
                    _signUpUiState.update {
                        it.copy(isLoading = true)
                    }
                    val signUpUser = getSignUpUser()
                    val signUpRequest = SignUpRequest(
                        userName = signUpUser.name,
                        password = signUpUser.pw,
                        name = signUpUser.nickname,
                        email = signUpUser.email,
                        age = signUpUser.age
                    )

                    val response = ServicePool.signUpService.createAccount(signUpRequest)
                    if (response.success) {
                        repository.saveMyProfile(signUpUser)
                        setToastEvent("회원가입에 성공했습니다.")
                        onSignUpSuccess()
                    }
                } catch (e: Exception) {
                    setToastEvent("회원가입에 실패했습니다.: ${e.message}")
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