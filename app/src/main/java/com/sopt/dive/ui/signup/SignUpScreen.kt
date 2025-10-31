package com.sopt.dive.ui.signup

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
import com.sopt.dive.ui.components.CustomButton
import com.sopt.dive.ui.components.CustomTextField


@Composable
fun SignUpScreen(
    onSignUpClick: (String, String, String, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {

    var inputUserId by remember { mutableStateOf("") }
    var inputUserPw by remember { mutableStateOf("") }
    var inputUserNickname by remember { mutableStateOf("") }
    var inputUserDrinking by remember { mutableStateOf("") }
    var inputUserName by remember { mutableStateOf("") }

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
                value = inputUserId,
                onValueChange = {
                    inputUserId = it
                },
                label = "아이디",
                placeholder = "6 ~ 10 자리 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "PW",
                value = inputUserPw,
                onValueChange = {
                    inputUserPw = it
                },
                label = "비밀번호",
                placeholder = "8 ~ 12 자리 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "NICKNAME",
                value = inputUserNickname,
                onValueChange = {
                    inputUserNickname = it
                },
                label = "별명",
                placeholder = "한 글자 이상 입력",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "주량",
                value = inputUserDrinking,
                onValueChange = {
                    inputUserDrinking = it
                },
                label = "소주 몇병?",
                placeholder = "숫자만 입력",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "NAME",
                value = inputUserName,
                onValueChange = {
                    if (koreanRegex.matches(it)) {
                        inputUserName = it
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
                    inputUserId.length in 6..10 &&
                    inputUserPw.length in 8..12 &&
                    inputUserNickname.isNotBlank() &&
                    (inputUserDrinking.toIntOrNull() != null && inputUserDrinking.toInt() >= 0) &&
                    inputUserName.isNotBlank()
                ) {
                    Toast.makeText(
                        context,
                        "회원가입에 성공하셨습니다",
                        Toast.LENGTH_SHORT
                    ).show()
                    onSignUpClick(
                        inputUserId,
                        inputUserPw,
                        inputUserNickname,
                        inputUserDrinking,
                        inputUserName,
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
        onSignUpClick = { id, pw, nickname, drinking, name -> }
    )
}