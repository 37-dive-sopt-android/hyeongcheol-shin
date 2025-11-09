package com.sopt.dive.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.dive.data.User
import com.sopt.dive.ui.main.home.HomeViewModel
import com.sopt.dive.ui.auth.signin.SignInScreen
import com.sopt.dive.ui.auth.signup.SignUpScreen
import com.sopt.dive.ui.main.home.HomeRoute
import com.sopt.dive.ui.main.mypage.MyPageRoute
import com.sopt.dive.ui.main.search.SearchRoute

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
    val homeViewModel: HomeViewModel? = null

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

            composable(Screen.SignIn.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }
                val homeViewModel: HomeViewModel = homeViewModel ?: viewModel(rootEntry)

                val registeredUserId = appHandle().get<String>("user_id").orEmpty()
                val registeredUserPw = appHandle().get<String>("user_pw").orEmpty()

                SignInScreen(
                    registeredUserId = registeredUserId,
                    registeredUserPw = registeredUserPw,
                    onSignUpClick = { navController.navigate(Screen.SignUp.name) },
                    onSignInClick = {
                        homeViewModel.setMyProfile(
                            User(
                                id = appHandle().get<String>("user_id").orEmpty(),
                                pw = appHandle().get<String>("user_pw").orEmpty(),
                                nickname = appHandle().get<String>("user_nickname").orEmpty(),
                                drinking = appHandle().get<String>("user_drinking").orEmpty(),
                                name = appHandle().get<String>("user_name").orEmpty(),
                            )
                        )
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

            composable(Screen.Home.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }
                val homeViewModel: HomeViewModel = homeViewModel ?: viewModel(rootEntry)

                HomeRoute(
                    homeViewModel = homeViewModel,
                    modifier = modifier,
                )
            }
            composable(Screen.Search.name) {
                SearchRoute(
                    modifier = modifier,
                )
            }
            composable(Screen.MyPage.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }
                val homeViewModel: HomeViewModel = homeViewModel ?: viewModel(rootEntry)

                MyPageRoute(
                    homeViewModel = homeViewModel,
                    modifier = modifier,
                )
            }
        }
    }
}