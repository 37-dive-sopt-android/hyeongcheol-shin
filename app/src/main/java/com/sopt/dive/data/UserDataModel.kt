package com.sopt.dive.data

import androidx.compose.ui.graphics.painter.Painter

data class UserData(
    val id: String,
    val pw: String,
    val nickname: String,
    val drinking: String,
    val name: String,
    val image: Painter?,
)

fun getUserDummyData(): List<UserData> {
    return listOf(
        UserData("id1", "pw1", "Fe", "drinking1", "신형철", null),
        UserData("id2", "pw2", "흠", "drinking2", "누구쓰지", null),
        UserData("id3", "pw3", "잠꼬대", "drinking3", "잠만보", null),
        UserData("id4", "pw4", "피카", "drinking4", "피카츄", null),
        UserData("id5", "pw5", "바비브라운", "drinking5", "누룽지", null),
        UserData("id6", "pw6", "웃는 바나나", "drinking6", "바나나킥", null),
        UserData("id7", "pw7", "비의 자기소개", "drinking7", "나비야", null),
        UserData("id8", "pw8", "미국이 싫어", "drinking8", "아메리카노", null),
        UserData("id9", "pw9", "가장 뜨거운 전화", "drinking9", "화상전화", null),
        UserData("id10", "pw10", "오른쪽에도 왼쪽에도 왕이?", "drinking10", "우왕좌왕", null),
    )
}
