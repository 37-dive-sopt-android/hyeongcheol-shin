package com.sopt.dive.ui.auth.signin

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun AutoSignInRoute(
    signInViewModel: SignInViewModel,
    autoSignInSuccess: () -> Unit,
    autoSignInFail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val toastEvent by signInViewModel.toastEvent.collectAsState(initial = "")
    val context = LocalContext.current

    LaunchedEffect(toastEvent) {
        if (toastEvent.isNotEmpty()) {
            Toast.makeText(context, toastEvent, Toast.LENGTH_SHORT).show()
            signInViewModel.setToastEvent("")
        }
    }

    LaunchedEffect(Unit) {
        delay(500)
        signInViewModel.isSignedInCheck(
            autoSignInSuccess = autoSignInSuccess,
            autoSignInFail = autoSignInFail
        )
    }

    AutoSignInScreen(
        modifier = modifier
    )
}

@Composable
fun AutoSignInScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}