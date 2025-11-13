package com.sopt.dive.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.R
import com.sopt.dive.data.BoardGame
import com.sopt.dive.data.Difficulty
import com.sopt.dive.data.User
import com.sopt.dive.data.UserData
import com.sopt.dive.data.dataStore.MyProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel(private val repository: MyProfileRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    //val uiState get() = _uiState
    //TODO("전에 다른 곳에서는 uiStateTest방법을 사용했는데, 둘이 차이점 알아보기")

    init {
        viewModelScope.launch {
            launch {
                repository.getMyProfile().collect { myProfile ->
                    setMyProfile(myProfile)
                }
            }
            launch {
                if (_uiState.value.userDataList.isEmpty()) {
                    setDummyUserDataList()
                }
            }
        }
    }

    fun setMyProfile(user: User) {
        _uiState.update {
            it.copy(myProfile = user)
        }
    }

    fun onSignOut(onSignOut: () -> Unit) {
        viewModelScope.launch {
            repository.setSignInStatus(false)
            onSignOut()
        }
    }

    fun setDummyUserDataList() {
        _uiState.update {
            it.copy(
                userDataList = listOf(
                    UserData("SHC", "Fe"),
                    UserData("Lion", "동물의 왕국", image = R.drawable.img_lion, comment = "사자로 만든 탕"),
                    UserData("Coffee", "코피", birth = LocalDate.now()),
                    //생일 확인용 더미 데이터
                    UserData(
                        "Fox",
                        "여우는 멍멍",
                        image = R.drawable.img_fox,
                        comment = "여우는 여우여우하고 우나여우"
                    ),
                    UserData(
                        "누룽지",
                        "바비 브라운",
                        favoriteBoardGame = BoardGame("Bang", "서부의 무법자", Difficulty.EASY)
                    ),
                    UserData(
                        "달수",
                        "보노보노",
                        image = R.drawable.img_otter,
                        imageDescription = "otter"
                    ),
                    UserData(
                        "바나나",
                        "바나나킥",
                        image = R.drawable.img_duck,
                        comment = "바나나가 어디를 보고 바나나 방금 웃었죠? 바나나킥",
                        favoriteBoardGame = BoardGame("테라포밍마스", "화성개척", Difficulty.NORMAL)
                    ),
                    UserData(
                        "화상전화",
                        "Fire",
                        comment = "세상에서 가장 뜨겁고도 뜨거운 전화",
                        favoriteBoardGame = BoardGame(title = "할리갈리", difficulty = Difficulty.HARD)
                    ),
                    UserData(
                        "우왕좌왕",
                        "KingKing",
                        birth = LocalDate.now(),
                        comment = "오른쪽에도 왕이 있고 왼쪽에도 왕이 있으니 온 사방이 왕이네",
                        favoriteBoardGame = BoardGame(
                            title = "젠가",
                            description = "피지컬 게임",
                            difficulty = Difficulty.HARD
                        )
                    ),
                )
            )
        }
    }
}