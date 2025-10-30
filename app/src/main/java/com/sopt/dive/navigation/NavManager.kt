package com.sopt.dive.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.ui.main.HomeScreen
import com.sopt.dive.ui.main.MyPageScreen
import com.sopt.dive.ui.main.SearchScreen

enum class MainScreen() {
    Home,
    Search,
    MyPage
}

@Composable
fun NavigationMainScreen(
    navController: NavHostController,
    userId: String,
    userPw: String,
    userNickName: String,
    userDrinking: String,
    userName: String,
    modifier: Modifier,
    startDestination: String = MainScreen.MyPage.name,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainScreen.Home.name) {
            HomeScreen(
                userName = userName,
                modifier = modifier,
            )
        }
        composable(MainScreen.Search.name) {
            SearchScreen(
                modifier = modifier,
            )
        }
        composable(MainScreen.MyPage.name) {
            MyPageScreen(
                userId = userId,
                userPw = userPw,
                userNickName = userNickName,
                userDrinking = userDrinking,
                userName = userName,
                modifier = Modifier,
            )
        }
    }
}