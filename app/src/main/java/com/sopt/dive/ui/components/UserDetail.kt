package com.sopt.dive.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun UserDetail(
    title: String,
    info: String,
    modifier: Modifier = Modifier,
    isUniqueText: Boolean = false,
) {
    val gradientColor = listOf(Color.Cyan, Color.Blue, Color.Magenta)

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = info,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            style = if (isUniqueText) {
                TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColor
                    ) //1셈때 자료 보다가 그냥 넣어보고 싶었어요 ㅎㅎ
                )
            } else {
                TextStyle(
                    color = Color.DarkGray
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserDetail() {
    UserDetail(
        title = "Test",
        info = "Test"
    )
}