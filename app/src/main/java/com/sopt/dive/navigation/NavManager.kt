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
import com.sopt.dive.util.AppHandleKey

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

            composable(Screen.SignIn.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }
                val homeViewModel: HomeViewModel = viewModel(rootEntry)

                val registeredUserId = appHandle().get<String>(AppHandleKey.USER_ID).orEmpty()
                val registeredUserPw = appHandle().get<String>(AppHandleKey.USER_PW).orEmpty()

                SignInScreen(
                    registeredUserId = registeredUserId,
                    registeredUserPw = registeredUserPw,
                    onSignUpClick = { navController.navigate(Screen.SignUp.name) },
                    onSignInClick = {
                        homeViewModel.setMyProfile(
                            User(
                                id = appHandle().get<String>(AppHandleKey.USER_ID).orEmpty(),
                                pw = appHandle().get<String>(AppHandleKey.USER_PW).orEmpty(),
                                nickname = appHandle().get<String>(AppHandleKey.USER_NICKNAME).orEmpty(),
                                drinking = appHandle().get<String>(AppHandleKey.USER_DRINKING).orEmpty(),
                                name = appHandle().get<String>(AppHandleKey.USER_NAME).orEmpty(),
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
                        appHandle().set(AppHandleKey.USER_ID, user.id)
                        appHandle().set(AppHandleKey.USER_PW, user.pw)
                        appHandle().set(AppHandleKey.USER_NICKNAME, user.nickname)
                        appHandle().set(AppHandleKey.USER_DRINKING, user.drinking)
                        appHandle().set(AppHandleKey.USER_NAME, user.name)
                        navController.popBackStack()
                    },
                    modifier = modifier,
                )
            }

            composable(Screen.Home.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }
                val homeViewModel: HomeViewModel = viewModel(rootEntry)

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
                val homeViewModel: HomeViewModel = viewModel(rootEntry)

                MyPageRoute(
                    homeViewModel = homeViewModel,
                    modifier = modifier,
                )
            }
        }
    }
}