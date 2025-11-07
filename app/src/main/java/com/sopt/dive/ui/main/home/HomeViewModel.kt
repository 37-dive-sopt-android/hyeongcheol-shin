package com.sopt.dive.ui.main.home

import androidx.lifecycle.ViewModel
import com.sopt.dive.data.User
import com.sopt.dive.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun setMyProfile(user: User){
        _uiState.value = _uiState.value.copy(
            myData = user
        )
    }

    fun getMyProfile(): User{
        return _uiState.value.myData!!
    }

    fun getUserList(): List<UserData> {
        return _uiState.value.userDataList
    }
}