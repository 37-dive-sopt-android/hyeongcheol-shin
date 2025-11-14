package com.sopt.dive.ui.auth.signin

import com.sopt.dive.data.User

data class SignInUiState(
    var inputUserId: String = "",
    var inputUserPw: String = "",
    val myProfile: User? = null,
    val isSignedIn: Boolean = false,
)