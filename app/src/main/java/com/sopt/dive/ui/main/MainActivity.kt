package com.sopt.dive.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigation.NavigationMainScreen
import com.sopt.dive.navigation.Screen
import com.sopt.dive.ui.components.BottomNavigationBar
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DiveTheme {
                val navController = rememberNavController()
                val entry by navController.currentBackStackEntryAsState()
                val currentRoute = entry?.destination?.route

                val mainScreen = listOf(
                    Screen.Home.name,
                    Screen.Search.name,
                    Screen.MyPage.name
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if(currentRoute in mainScreen)BottomNavigationBar(
                            navController = navController,
                        )
                    },
                ) { innerPadding ->
                    NavigationMainScreen(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}