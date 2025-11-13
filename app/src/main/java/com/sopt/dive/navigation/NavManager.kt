package com.sopt.dive.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sopt.dive.data.dataStore.MyProfileRepository
import com.sopt.dive.ui.auth.AuthViewModel
import com.sopt.dive.ui.auth.AuthViewModelFactory
import com.sopt.dive.ui.auth.loading.LoadingRoute
import com.sopt.dive.ui.auth.signin.SignInRoute
import com.sopt.dive.ui.main.home.HomeViewModel
import com.sopt.dive.ui.auth.signup.SignUpRoute
import com.sopt.dive.ui.auth.signup.SignUpViewModel
import com.sopt.dive.ui.auth.signup.SignUpViewModelFactory
import com.sopt.dive.ui.main.home.HomeRoute
import com.sopt.dive.ui.main.home.HomeViewModelFactory
import com.sopt.dive.ui.main.mypage.MyPageRoute
import com.sopt.dive.ui.main.search.SearchRoute

enum class Screen() {
    Root,
    Home,
    Search,
    MyPage,
    SignIn,
    SignUp,
    Loading,
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
            startDestination = Screen.Loading.name
        ) {
            composable(Screen.Loading.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }
                val context = LocalContext.current
                val repository = remember { MyProfileRepository(context) }

                val authViewModel: AuthViewModel =
                    viewModel(rootEntry, factory = AuthViewModelFactory(repository))

                LoadingRoute(
                    authViewModel = authViewModel,
                    authSingInSuccess = {
                        navController.navigate(Screen.Home.name) {
                            popUpTo(Screen.Loading.name) {
                                inclusive = true
                            }
                        }
                    },
                    authSingInFail = {
                        navController.navigate(Screen.SignIn.name) {
                            popUpTo(Screen.Loading.name) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = modifier,
                )
            }

            composable(Screen.SignIn.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }
                val context = LocalContext.current
                val repository = remember { MyProfileRepository(context) }

                val authViewModel: AuthViewModel =
                    viewModel(rootEntry, factory = AuthViewModelFactory(repository))

                SignInRoute(
                    authViewModel = authViewModel,
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
                val context = LocalContext.current
                val repository = remember { MyProfileRepository(context) }

                val signUpViewModel: SignUpViewModel =
                    viewModel(factory = SignUpViewModelFactory(repository))

                SignUpRoute(
                    signUpViewModel = signUpViewModel,
                    onSignUpClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier,
                )
            }

            composable(Screen.Home.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }

                val context = LocalContext.current
                val repository = remember { MyProfileRepository(context) }

                val homeViewModel: HomeViewModel =
                    viewModel(rootEntry, factory = HomeViewModelFactory(repository))

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

                val context = LocalContext.current
                val repository = remember { MyProfileRepository(context) }

                val homeViewModel: HomeViewModel =
                    viewModel(rootEntry, factory = HomeViewModelFactory(repository))

                MyPageRoute(
                    homeViewModel = homeViewModel,
                    modifier = modifier,
                )
            }
        }
    }
}