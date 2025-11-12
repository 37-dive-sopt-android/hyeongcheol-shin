package com.sopt.dive.ui.auth

import com.sopt.dive.data.User

data class AuthUiState(
    var inputUserId: String = "",
    var inputUserPw: String = "",
    val registeredUser: User? = null,
    val isSignIn: Boolean = false,
)