package com.sopt.dive.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.dive.data.User
import com.sopt.dive.ui.main.HomeScreen
import com.sopt.dive.ui.main.MyPageScreen
import com.sopt.dive.ui.main.SearchScreen
import com.sopt.dive.ui.signin.SignInScreen
import com.sopt.dive.ui.signup.SignUpScreen

enum class Screen() {
    Root,
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
    startDestination: String = Screen.Root.name,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Screen.Root.name,
            startDestination = Screen.SignIn.name
        ) {
            fun appHandle(): SavedStateHandle =
                navController.getBackStackEntry(Screen.Root.name).savedStateHandle

            composable(Screen.SignIn.name) {
                val registeredUserId = appHandle().get<String>("user_id").orEmpty()
                val registeredUserPw = appHandle().get<String>("user_pw").orEmpty()

                SignInScreen(
                    registeredUserId = registeredUserId,
                    registeredUserPw = registeredUserPw,
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
                )
            }
            composable(Screen.SignUp.name) {
                SignUpScreen(
                    onSignUpClick = { user ->
                        appHandle().set("user_id", user.id)
                        appHandle().set("user_pw", user.pw)
                        appHandle().set("user_nickname", user.nickname)
                        appHandle().set("user_drinking", user.drinking)
                        appHandle().set("user_name", user.name)
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
                    user = User(
                        id = appHandle().get<String>("user_id").orEmpty(),
                        pw = appHandle().get<String>("user_pw").orEmpty(),
                        nickname = appHandle().get<String>("user_nickname").orEmpty(),
                        drinking = appHandle().get<String>("user_drinking").orEmpty(),
                        name = appHandle().get<String>("user_name").orEmpty(),
                    ),
                    modifier = modifier,
                )
            }
        }
    }
}