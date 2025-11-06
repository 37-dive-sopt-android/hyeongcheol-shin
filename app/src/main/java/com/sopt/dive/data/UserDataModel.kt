package com.sopt.dive.data

import androidx.annotation.DrawableRes
import com.sopt.dive.R
import java.time.LocalDate

data class UserData(
    val name: String,
    val nickname: String,
    val comment: String? = null,
    val birth: LocalDate? = null,
    val favoriteBoardGame: BoardGame? = null,
    @DrawableRes val image: Int? = null,
    val imageDescription: String? = null,
)

fun getUserDummyData(): List<UserData> {
    return listOf(
        UserData("SHC", "Fe"),
        UserData("Lion", "동물의 왕국", image = R.drawable.img_lion, comment = "사자로 만든 탕"),
        UserData("Coffee", "코피", birth = LocalDate.now()),
        //생일 확인용 더미 데이터
        UserData("Fox", "여우는 멍멍", image = R.drawable.img_fox, comment = "여우는 여우여우하고 우나여우"),
        UserData("누룽지", "바비 브라운", favoriteBoardGame = BoardGame("Bang", "서부의 무법자", Difficulty.EASY)),
        UserData("달수", "보노보노", image = R.drawable.img_otter, imageDescription = "otter"),
        UserData("바나나", "바나나킥", image = R.drawable.img_duck, comment = "바나나가 어디를 보고 바나나 방금 웃었죠? 바나나킥", favoriteBoardGame = BoardGame("테라포밍마스", "화성개척", Difficulty.NORMAL)),
        UserData("화상전화", "Fire", comment = "세상에서 가장 뜨겁고도 뜨거운 전화", favoriteBoardGame = BoardGame(title = "할리갈리", difficulty = Difficulty.HARD)),
        UserData("우왕좌왕", "KingKing", birth = LocalDate.now(), comment = "오른쪽에도 왕이 있고 왼쪽에도 왕이 있으니 온 사방이 왕이네", favoriteBoardGame = BoardGame(title = "젠가", description = "피지컬 게임", difficulty = Difficulty.HARD)),
    )
}
