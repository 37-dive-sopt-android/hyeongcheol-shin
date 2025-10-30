package com.sopt.dive.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigation.MainScreen
import com.sopt.dive.navigation.NavigationMainScreen
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.IntentKeys

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId = intent.getStringExtra(IntentKeys.USER_ID) ?: ""
        val userPw = intent.getStringExtra(IntentKeys.USER_PW) ?: ""
        val userNickName = intent.getStringExtra(IntentKeys.USER_NICKNAME) ?: ""
        val userDrinking = intent.getStringExtra(IntentKeys.USER_DRINKING) ?: ""
        val userName = intent.getStringExtra(IntentKeys.USER_NAME) ?: ""

        setContent {
            DiveTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {},
                ) { innerPadding ->
                    NavigationMainScreen(
                        navController = navController,
                        userId = userId,
                        userPw = userPw,
                        userNickName = userNickName,
                        userDrinking = userDrinking,
                        userName = userName,
                        modifier = Modifier.padding(innerPadding),
                        startDestination = MainScreen.MyPage.name,
                    )
                }
            }
        }
    }
}