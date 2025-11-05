package com.sopt.dive.data

import androidx.compose.ui.graphics.Color

enum class Difficulty(
    val value: String,
    val color: Color,
) {
    EASY("Easy", Color.Green),
    NORMAL("Normal", Color.Yellow),
    HARD("Hard", Color.Red),
}

data class BoardGame(
    val title: String,
    val description: String? = null,
    val difficulty: Difficulty,
)