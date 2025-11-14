package com.sopt.dive.ui.main.home

import com.sopt.dive.data.User
import com.sopt.dive.data.UserData

data class HomeUiState(
    val myProfile: User = User(
        name = "",
        pw = "",
        nickname = "",
        email = "",
        age = 0
    ),
    val userDataList: List<UserData> = emptyList(),
    val isLoading: Boolean = false
)