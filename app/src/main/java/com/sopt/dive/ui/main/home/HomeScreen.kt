package com.sopt.dive.ui.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.R
import com.sopt.dive.data.UserData
import com.sopt.dive.data.getUserDummyData
import com.sopt.dive.ui.components.HomeItem

@Composable
fun HomeRoute(
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        userName = uiState.myProfile?.name ?: "",
        userNickname = uiState.myProfile?.nickname ?: "",
        users = uiState.userDataList,
        modifier = modifier,
    )
}

@Composable
fun HomeScreen(
    userName: String,
    userNickname: String,
    users: List<UserData>,
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
        items(users) { user ->
            HomeItem(
                user = user,
                painterSize = 40,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen(
        userName = "Test",
        userNickname = "Test",
        users = getUserDummyData(),
        modifier = Modifier
    )
}