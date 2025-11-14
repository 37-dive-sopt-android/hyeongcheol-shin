package com.sopt.dive.ui.main.home

import com.sopt.dive.data.User
import com.sopt.dive.data.UserData

data class HomeUiState(
    val myProfile: User = User(
        id = "",
        pw = "",
        nickname = "",
        drinking = "",
        name = ""
    ),
    val userDataList: List<UserData> = emptyList(),
    val isLoading: Boolean = false
)