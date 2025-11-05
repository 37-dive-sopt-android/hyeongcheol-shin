package com.sopt.dive.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.data.UserData
import com.sopt.dive.data.getUserDummyData
import com.sopt.dive.ui.components.HomeItem

@Composable
fun HomeScreen(
    userName: String,
    userNickname: String,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            HomeItem(
                UserData(
                    name = userName,
                    nickname = userNickname,
                    image = R.drawable.img_otter,
                    imageDescription = "달수",
                ),
                painterSize = 60,
            )
        }
        items(getUserDummyData() + getUserDummyData()) { user ->
            HomeItem(
                user = user,
                painterSize = 40,
            )
        }
        // LazyColumn 사용을 위해 DummyData를 2번 넣었습니다.
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen(
        userName = "Test",
        userNickname = "Test",
        modifier = Modifier
    )
}