package com.sopt.dive.ui.auth.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sopt.dive.ui.auth.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun LoadingRoute(
    authViewModel: AuthViewModel,
    authSingInSuccess: () -> Unit,
    authSingInFail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val authUiState by authViewModel.authUiState.collectAsState()

    LaunchedEffect(Unit){
        delay(500)
        if (authUiState.isSignedIn) {
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
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}