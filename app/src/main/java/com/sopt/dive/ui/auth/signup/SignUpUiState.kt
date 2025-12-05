package com.sopt.dive.ui.auth.signup

data class SignUpUiState(
    var inputUserName: String = "",
    var inputUserPw: String = "",
    var inputUserNickname: String = "",
    var inputUserEmail: String = "",
    var inputUserAge: Int = 0,
    val isLoading: Boolean = false
)