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
import com.sopt.dive.ui.components.CustomButton
import com.sopt.dive.ui.components.CustomTextField

@Composable
fun SignUpRoute(
    signUpViewModel: SignUpViewModel,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val signUpUiState by signUpViewModel.signUpUiState.collectAsState()
    val toastEvent by signUpViewModel.toastEvent.collectAsState(initial = "")
    val context = LocalContext.current


    LaunchedEffect(toastEvent) {
        if (toastEvent.isNotEmpty()) {
            Toast.makeText(context, toastEvent, Toast.LENGTH_SHORT).show()
            signUpViewModel.setToastEvent("")
        }
    }

    SignUpScreen(
        inputUserId = signUpUiState.inputUserId,
        inputUserPw = signUpUiState.inputUserPw,
        inputUserNickname = signUpUiState.inputUserNickname,
        inputUserDrinking = signUpUiState.inputUserDrinking,
        inputUserName = signUpUiState.inputUserName,
        updateInputUserId = {
            signUpViewModel.updateInputUserId(it)
        },
        updateInputUserPw = {
            signUpViewModel.updateInputUserPw(it)
        },
        updateInputUserNickname = {
            signUpViewModel.updateInputUserNickname(it)
        },
        updateInputUserDrinking = {
            signUpViewModel.updateInputUserDrinking(it)
        },
        updateInputUserName = {
            signUpViewModel.updateInputUserName(it)
        },
        onSignUpClick = {
            signUpViewModel.signUp(onSignUpClick)
        },
        modifier = modifier,
    )
}

@Composable
fun SignUpScreen(
    inputUserId: String,
    inputUserPw: String,
    inputUserNickname: String,
    inputUserDrinking: String,
    inputUserName: String,
    updateInputUserId: (String) -> Unit,
    updateInputUserPw: (String) -> Unit,
    updateInputUserNickname: (String) -> Unit,
    updateInputUserDrinking: (String) -> Unit,
    updateInputUserName: (String) -> Unit,
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
                title = "ID",
                value = inputUserId,
                onValueChange = { updateInputUserId(it) },
                label = "아이디",
                placeholder = "6 ~ 10 자리 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "PW",
                value = inputUserPw,
                onValueChange = { updateInputUserPw(it) },
                label = "비밀번호",
                placeholder = "8 ~ 12 자리 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "NICKNAME",
                value = inputUserNickname,
                onValueChange = { updateInputUserNickname(it) },
                label = "별명",
                placeholder = "한 글자 이상 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "주량",
                value = inputUserDrinking,
                onValueChange = { updateInputUserDrinking(it) },
                label = "소주 몇병?",
                placeholder = "숫자만 입력",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "NAME",
                value = inputUserName,
                onValueChange = { updateInputUserName(it) },
                label = "이름",
                placeholder = "한글로 입력",
            )
        }
        Spacer(Modifier.weight(1f))
        CustomButton(
            text = "Sign Up",
            onClick = {
                onSignUpClick()
            },
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