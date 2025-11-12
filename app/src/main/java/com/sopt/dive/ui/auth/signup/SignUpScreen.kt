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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.data.User
import com.sopt.dive.ui.auth.AuthViewModel
import com.sopt.dive.ui.components.CustomButton
import com.sopt.dive.ui.components.CustomTextField

@Composable
fun SignUpRoute(
    authViewModel: AuthViewModel,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpScreen(
        onSignUpClick = {
            authViewModel.signUp(it)
            onSignUpClick()
        },
        modifier = modifier,
    )
}

@Composable
fun SignUpScreen(
    onSignUpClick: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    var inputUser by remember {
        mutableStateOf(
            User(
                id = "asdasd",
                pw = "asdasdasd",
                nickname = "GodBroderIron",
                drinking = "1000000",
                name = "신형철",
            )
        )
    }

    val context = LocalContext.current

    val koreanRegex = Regex("^[ㄱ-ㅎㅏ-ㅣ가-힣]*$")

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
                value = inputUser.id,
                onValueChange = {
                    inputUser = inputUser.copy(id = it)
                },
                label = "아이디",
                placeholder = "6 ~ 10 자리 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "PW",
                value = inputUser.pw,
                onValueChange = {
                    inputUser = inputUser.copy(pw = it)
                },
                label = "비밀번호",
                placeholder = "8 ~ 12 자리 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "NICKNAME",
                value = inputUser.nickname,
                onValueChange = {
                    inputUser = inputUser.copy(nickname = it)
                },
                label = "별명",
                placeholder = "한 글자 이상 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "주량",
                value = inputUser.drinking,
                onValueChange = {
                    inputUser = inputUser.copy(drinking = it.filter { it.isDigit() })
                },
                label = "소주 몇병?",
                placeholder = "숫자만 입력",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "NAME",
                value = inputUser.name,
                onValueChange = {
                    if (koreanRegex.matches(it)) {
                        inputUser = inputUser.copy(name = it)
                    }
                },
                label = "이름",
                placeholder = "한글로 입력",
            )
        }
        Spacer(Modifier.weight(1f))
        CustomButton(
            text = "Sign Up",
            onClick = {
                if (
                    inputUser.id.length in 6..10 &&
                    inputUser.pw.length in 8..12 &&
                    inputUser.nickname.isNotBlank() &&
                    (inputUser.drinking.toIntOrNull() != null && inputUser.drinking.toInt() >= 0) &&
                    inputUser.name.isNotBlank()
                ) {
                    Toast.makeText(
                        context,
                        "회원가입에 성공하셨습니다",
                        Toast.LENGTH_SHORT
                    ).show()
                    onSignUpClick(
                        inputUser
                    )
                } else {
                    Toast.makeText(
                        context,
                        "조건을 확인해주세요",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.padding(vertical = 16.dp),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(
        modifier = Modifier,
        onSignUpClick = { user -> }
    )
}