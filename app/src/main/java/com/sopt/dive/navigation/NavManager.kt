package com.sopt.dive.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.dive.ui.main.HomeScreen
import com.sopt.dive.ui.main.MyPageScreen
import com.sopt.dive.ui.main.SearchScreen
import com.sopt.dive.ui.signin.SignInScreen
import com.sopt.dive.ui.signup.SignUpScreen

private const val Root = "Root"

enum class Screen() {
    Home,
    Search,
    MyPage,
    SignIn,
    SignUp,
}

@Composable
fun NavigationMainScreen(
    navController: NavHostController,
    modifier: Modifier,
    startDestination: String = Root,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Root,
            startDestination = Screen.SignIn.name
        ) {
            fun appHandle(): SavedStateHandle =
                navController.getBackStackEntry(Root).savedStateHandle

            composable(Screen.SignIn.name) {
                SignInScreen(
                    onSignUpClick = { navController.navigate(Screen.SignUp.name) },
                    onSignInClick = {
                        navController.navigate(Screen.Home.name) {
                            popUpTo(Screen.SignIn.name) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    modifier = modifier,
                    savedStateHandle = appHandle(),
                )
            }
            composable(Screen.SignUp.name) {
                SignUpScreen(
                    onSignUpClick = { id, pw, nickname, drinking, name ->
                        appHandle().set("user_id", id)
                        appHandle().set("user_pw", pw)
                        appHandle().set("user_nickname", nickname)
                        appHandle().set("user_drinking", drinking)
                        appHandle().set("user_name", name)
                        navController.popBackStack()
                    },
                    modifier = modifier,
                )
            }

            composable(Screen.Home.name) {
                HomeScreen(
                    userName = appHandle().get<String>("user_name").orEmpty(),
                    modifier = modifier,
                )
            }
            composable(Screen.Search.name) {
                SearchScreen(
                    modifier = modifier,
                )
            }
            composable(Screen.MyPage.name) {
                MyPageScreen(
                    userId = appHandle().get<String>("user_id").orEmpty(),
                    userPw = appHandle().get<String>("user_pw").orEmpty(),
                    userNickName = appHandle().get<String>("user_nickname").orEmpty(),
                    userDrinking = appHandle().get<String>("user_drinking").orEmpty(),
                    userName = appHandle().get<String>("user_name").orEmpty(),
                    modifier = modifier,
                )
            }
        }
    }
}