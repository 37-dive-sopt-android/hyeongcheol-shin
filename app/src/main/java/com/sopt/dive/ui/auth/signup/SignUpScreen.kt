package com.sopt.dive.ui.auth.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.ui.components.CustomButton
import com.sopt.dive.ui.components.CustomTextField

@Composable
fun SignUpRoute(
    signUpViewModel: SignUpViewModel,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val signUpUiState by signUpViewModel.signUpUiState.collectAsStateWithLifecycle()
    val toastEvent by signUpViewModel.toastEvent.collectAsState(initial = "")
    val context = LocalContext.current


    LaunchedEffect(toastEvent) {
        if (toastEvent.isNotEmpty()) {
            Toast.makeText(context, toastEvent, Toast.LENGTH_SHORT).show()
            signUpViewModel.setToastEvent("")
        }
    }

    SignUpScreen(
        uiState = signUpUiState,
        updateInputUserName = {
            signUpViewModel.updateInputUserName(it)
        },
        updateInputUserPw = {
            signUpViewModel.updateInputUserPw(it)
        },
        updateInputUserNickname = {
            signUpViewModel.updateInputUserNickname(it)
        },
        updateInputUserEmail = {
            signUpViewModel.updateInputUserEmail(it)
        },
        updateInputUserAge = {
            signUpViewModel.updateInputUserAge(it)
        },
        onSignUpClick = {
            signUpViewModel.signUp(onSignUpClick)
        },
        modifier = modifier,
    )
}

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    updateInputUserName: (String) -> Unit,
    updateInputUserPw: (String) -> Unit,
    updateInputUserNickname: (String) -> Unit,
    updateInputUserEmail: (String) -> Unit,
    updateInputUserAge: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(32.dp))
        Text(
            text = "SIGN UP",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(Modifier.height(40.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CustomTextField(
                title = "username",
                value = uiState.inputUserName,
                onValueChange = { updateInputUserName(it) },
                label = "username",
                placeholder = "아이디 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "PW",
                value = uiState.inputUserPw,
                onValueChange = { updateInputUserPw(it) },
                label = "password",
                placeholder = "8 ~ 12 자리 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "NICKNAME",
                value = uiState.inputUserNickname,
                onValueChange = { updateInputUserNickname(it) },
                label = "name",
                placeholder = "이름 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "email",
                value = uiState.inputUserEmail,
                onValueChange = { updateInputUserEmail(it) },
                label = "email",
                placeholder = "이메일 형태로 입력",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "age",
                value = uiState.inputUserAge.toString(),
                onValueChange = { updateInputUserAge(it) },
                label = "age",
                placeholder = "숫자로 입력",
                keyboardType = KeyboardType.Number,
            )
        }
        Spacer(Modifier.weight(1f))
        CustomButton(
            text = "Sign Up",
            onClick = {
                onSignUpClick()
            },
            isEnabled = !uiState.isLoading,
            modifier = Modifier.padding(vertical = 16.dp),
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(
        modifier = Modifier,
        onSignUpClick = { user -> }
    )
}*/