package com.sopt.dive.ui.auth.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sopt.dive.data.dataStore.MyProfileRepository
import kotlinx.coroutines.flow.first

@Composable
fun LoadingRoute(
    authSingInSuccess: () -> Unit,
    authSingInFail: () -> Unit,
    modifier: Modifier = Modifier,
){
    val context = LocalContext.current
    val myProfileRepository = remember { MyProfileRepository(context) }

    LaunchedEffect(Unit) {
        val isSignedIn = myProfileRepository.getIsSignedIn().first()
        if (isSignedIn) {
            authSingInSuccess()
        } else {
            authSingInFail()
        }
    }

    LoadingScreen(
        modifier = modifier
    )
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}