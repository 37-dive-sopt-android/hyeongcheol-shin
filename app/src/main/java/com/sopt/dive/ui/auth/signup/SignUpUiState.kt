package com.sopt.dive.ui.auth.signup

data class SignUpUiState(
    var inputUserName: String = "asdasd",
    var inputUserPw: String = "asdasdasd",
    var inputUserNickname: String = "Fe",
    var inputUserEmail: String = "hcshin0717@naver.com",
    var inputUserAge: Int = 25,
    val isLoading: Boolean = false
)