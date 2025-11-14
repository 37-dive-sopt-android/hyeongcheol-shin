package com.sopt.dive.ui.main.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.R
import com.sopt.dive.data.User
import com.sopt.dive.ui.components.CustomButton
import com.sopt.dive.ui.components.UserDetail
import com.sopt.dive.ui.main.home.HomeViewModel

@Composable
fun MyPageRoute(
    homeViewModel: HomeViewModel,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    MyPageScreen(
        user = uiState.myProfile,
        onSignOut = { homeViewModel.onSignOut(onSignOut) },
        modifier = modifier,
    )
}

@Composable
fun MyPageScreen(
    user: User,
    onSignOut: () -> Unit,
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
                Image(
                    painter = painterResource(R.drawable.img_otter),
                    contentDescription = "달수",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = user.name,
                    fontSize = 28.sp
                )
            }
            Text(
                text = "${user.name} 님 SOPT에 오신 것을 환영합니다",
                fontSize = 16.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            UserDetail(
                title = "ID",
                info = user.id
            )
            UserDetail(
                title = "PW",
                info = user.pw
            )
            UserDetail(
                title = "NICKNAME",
                info = user.nickname
            )
            UserDetail(
                title = "주량",
                info = "${user.drinking} 병",
                isUniqueText = (user.drinking.toInt() > 3)
            )
        }
        CustomButton(
            text = "로그아웃",
            onClick = { onSignOut() }
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    MyPageScreen(
        user = User(
            id = "test",
            pw = "test",
            nickname = "Fe",
            drinking = "0",
            name = "SHC"
        ),
    )
}
*/