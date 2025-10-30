package com.sopt.dive.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.components.UserDetail

@Composable
fun MyPageScreen(
    userId: String,
    userPw: String,
    userNickName: String,
    userDrinking: String,
    userName: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(40.dp),
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(100),
                    color = Color.Unspecified,
                    modifier = Modifier
                        .size(52.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_otter),
                        contentDescription = "달수",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = userName,
                    fontSize = 28.sp
                )
            }
            Text(
                text = "$userName 님 SOPT에 오신 것을 환영합니다",
                fontSize = 16.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            UserDetail(
                title = "ID",
                info = userId
            )
            UserDetail(
                title = "PW",
                info = userPw
            )
            UserDetail(
                title = "NICKNAME",
                info = userNickName
            )
            UserDetail(
                title = "주량",
                info = "$userDrinking 병",
                isUniqueText = (userDrinking.toInt() > 3)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    MyPageScreen(
        modifier = Modifier,
        userId = "test",
        userPw = "test",
        userNickName = "Fe",
        userDrinking = "0",
        userName = "SHC"
    )
}