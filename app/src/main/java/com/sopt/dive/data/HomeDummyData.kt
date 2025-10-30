package com.sopt.dive.data

import androidx.compose.ui.graphics.painter.Painter

data class BoardGame(
    val title: String,
    val description: String,
    val painter: Painter?,
)

fun getBoardGameDummyData(): List<BoardGame> {
    return listOf(
        BoardGame("title1", "description1", null),
        BoardGame("title2", "description2", null),
        BoardGame("title3", "description3", null),
        BoardGame("title4", "description4", null),
        BoardGame("title5", "description5", null),
        BoardGame("title6", "description6", null),
        BoardGame("title7", "description7", null),
        BoardGame("title8", "description8", null),
        BoardGame("title9", "description9", null),
        BoardGame("title10", "description10", null),
    )
}
