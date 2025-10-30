package com.sopt.dive.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sopt.dive.navigation.Screen

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier,
) {
    val screens = listOf(
        Screen.Home,
        Screen.Search,
        Screen.MyPage
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = screen.name
                    )
                },
                selected = currentRoute == screen.name,
                onClick = {
                    navController.navigate(screen.name){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                        //TODO("각각의 State 역할 정리하기")
                    }
                }
            )
        }
    }
}
