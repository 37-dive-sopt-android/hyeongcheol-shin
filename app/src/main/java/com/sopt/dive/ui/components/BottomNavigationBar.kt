package com.sopt.dive.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigation.Screen

data class BottomNavItem(
    val screen: Screen,
    val icon: ImageVector,
)


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val bottomNavItems = listOf(
        BottomNavItem(Screen.Home, Icons.Filled.Home),
        BottomNavItem(Screen.Search, Icons.Filled.Search),
        BottomNavItem(Screen.MyPage, Icons.Filled.AccountCircle),
    )

    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItems.forEach { navItem ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.screen.name
                    )
                },
                selected = currentRoute == navItem.screen.name,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color.LightGray,
                ),
                onClick = {
                    navController.navigate(navItem.screen) {
                        popUpTo(navController.graph.startDestinationId) {
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

@Composable
@Preview(showBackground = true)
fun PreviewBottomBar(){
    BottomNavigationBar(
        navController = rememberNavController()
    )
}