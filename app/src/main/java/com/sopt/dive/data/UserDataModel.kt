package com.sopt.dive.data

import androidx.annotation.DrawableRes
import java.util.Date

data class UserData(
    val name: String,
    val nickname: String,
    val comment: String,
    val birth: Date?,
    val favoriteBoardGame: BoardGame,
    @DrawableRes val image: Int?,
)

fun getUserDummyData(): List<UserData> {
    return listOf(

    )
}
