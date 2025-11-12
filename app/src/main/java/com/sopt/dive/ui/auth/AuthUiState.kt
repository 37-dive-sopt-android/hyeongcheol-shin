package com.sopt.dive.ui.auth

import com.sopt.dive.data.User

data class AuthUiState(
    var inputUserId: String = "",
    var inputUserPw: String = "",
    val myProfile: User? = null,
    val isSignedIn: Boolean = false,
)