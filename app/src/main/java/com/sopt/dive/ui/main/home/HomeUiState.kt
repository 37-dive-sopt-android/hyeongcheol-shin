package com.sopt.dive.ui.main.home

import com.sopt.dive.data.User
import com.sopt.dive.data.UserData

data class HomeUiState(
    val myProfile: User? = null,
    val userDataList: List<UserData> = emptyList(),
)