package com.sopt.dive.ui.auth.signup

data class SignUpUiState(
    var inputUserId: String = "asdasd",
    var inputUserPw: String = "asdasdasd",
    var inputUserDrinking: String = "1",
    var inputUserNickname: String = "Fe",
    var inputUserName: String = "신",
    val isLoading: Boolean = false
)