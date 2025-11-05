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
        UserData("SHC", "Fe", image = R.drawable.img_lion, comment = "Test"),
        UserData("SHC", "Fe", birth = LocalDate.now()),
        //생일 확인용 더미 데이터
        UserData("SHC", "Fe", image = R.drawable.img_fox, comment = "TestTestTestTestTestTestTestTestTestTestTestTest"),
        UserData("SHC", "Fe", favoriteBoardGame = BoardGame("Test", "Test", Difficulty.EASY)),
        UserData("SHC", "Fe", image = R.drawable.img_otter, imageDescription = "test"),
        UserData("SHC", "Fe", image = R.drawable.img_duck, comment = "TestTestTestTestTestTestTestTestTest", favoriteBoardGame = BoardGame("Test", "Test", Difficulty.NORMAL)),
        UserData("SHC", "Fe", comment = "TestTestTestTestTestTestTestTestTest", favoriteBoardGame = BoardGame(title = "Test", difficulty = Difficulty.HARD)),
        UserData("SHC", "Fe", birth = LocalDate.now(), comment = "TestTestTestTestTestTestTestTestTest", favoriteBoardGame = BoardGame(title = "Test", description = "Test", difficulty = Difficulty.HARD)),
    )
}
