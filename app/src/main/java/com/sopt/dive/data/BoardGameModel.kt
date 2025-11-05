package com.sopt.dive.data

import androidx.annotation.DrawableRes

enum class Difficulty {
    EASY,
    NORMAL,
    HARD
}

data class BoardGame(
    val title: String,
    val description: String,
    val difficulty: Difficulty,
    @DrawableRes val image: Int?,
)