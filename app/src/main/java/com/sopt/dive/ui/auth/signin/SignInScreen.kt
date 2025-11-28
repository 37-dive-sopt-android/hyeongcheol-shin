package com.sopt.dive.ui.auth.signin

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.components.CustomButton
import com.sopt.dive.ui.components.CustomTextField
import com.sopt.dive.util.clickableWithoutRipple

@Composable
fun SignInRoute(
    signInViewModel: SignInViewModel,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val authUiState by signInViewModel.signInUiState.collectAsState()
    val toastEvent by signInViewModel.toastEvent.collectAsState(initial = "")
    val context = LocalContext.current


    LaunchedEffect(toastEvent) {
        if (toastEvent.isNotEmpty()) {
            Toast.makeText(context, toastEvent, Toast.LENGTH_SHORT).show()
            signInViewModel.setToastEvent("")
        }
    }

    SignInScreen(
        uiState = authUiState,
        updateInputUserId = {
            signInViewModel.updateInputUserId(it)
        },
        updateInputUserPw = {
            signInViewModel.updateInputUserPw(it)
        },
        onSignInClick = {
            signInViewModel.signIn(onSignInClick)
        },
        onSignUpClick = onSignUpClick,
        modifier = modifier,
    )
}

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    updateInputUserId: (String) -> Unit,
    updateInputUserPw: (String) -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .imePadding()
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            text = "Welcome To Sopt",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            CustomTextField(
                title = "ID",
                value = uiState.inputUserId,
                onValueChange = {
                    updateInputUserId(it)
                },
                label = "아이디",
                placeholder = "아이디를 입력해주세요",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "PW",
                value = uiState.inputUserPw,
                onValueChange = {
                    updateInputUserPw(it)
                },
                label = "비밀번호",
                placeholder = "비밀번호를 입력하세요",
                isTextHidden = true,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            CustomButton(
                text = "Welcome to Sopt",
                isEnabled = (uiState.inputUserId.isNotEmpty() && uiState.inputUserPw.isNotEmpty() && !uiState.isLoading),
                onClick = {
                    onSignInClick()
                },
            )
            Text(
                text = "회원가입하기",
                modifier = Modifier
                    .clickableWithoutRipple(
                        onClick = {
                            onSignUpClick()
                        }
                    )
            )
            Spacer(Modifier.height(20.dp))
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(
        modifier = Modifier,
        registeredUserId = "Test Id",
        registeredUserPw = "Test Pw",
        onSignInClick = { },
        onSignUpClick = { }
    )
}*/