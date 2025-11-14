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
import com.sopt.dive.ui.auth.signin.AutoSignInRoute
import com.sopt.dive.ui.auth.signin.SignInRoute
import com.sopt.dive.ui.auth.signin.SignInViewModel
import com.sopt.dive.ui.auth.signin.SignInViewModelFactory
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
    AutoSignIn,
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
            startDestination = Screen.AutoSignIn.name
        ) {
            composable(Screen.AutoSignIn.name) { backStackEntry ->
                val rootEntry =
                    remember(backStackEntry) { navController.getBackStackEntry(Screen.Root.name) }
                val context = LocalContext.current
                val repository = remember { MyProfileRepository(context) }

                val authViewModel: SignInViewModel =
                    viewModel(rootEntry, factory = SignInViewModelFactory(repository))

                AutoSignInRoute(
                    signInViewModel = authViewModel,
                    autoSignInSuccess = {
                        navController.navigate(Screen.Home.name) {
                            popUpTo(Screen.AutoSignIn.name) {
                                inclusive = true
                            }
                        }
                    },
                    autoSignInFail = {
                        navController.navigate(Screen.SignIn.name) {
                            popUpTo(Screen.AutoSignIn.name) {
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

                val authViewModel: SignInViewModel =
                    viewModel(rootEntry, factory = SignInViewModelFactory(repository))

                SignInRoute(
                    signInViewModel = authViewModel,
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
                    onSignOut = {
                        navController.navigate(Screen.SignIn.name)
                        {
                            popUpTo(Screen.MyPage.name) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    modifier = modifier,
                )
            }
        }
    }
}