package com.sopt.dive.ui.auth.signin

data class SignInUiState(
    var inputUserId: String = "",
    var inputUserPw: String = "",
    val isSignedIn: Boolean = false,
    val isLoading: Boolean = false
)