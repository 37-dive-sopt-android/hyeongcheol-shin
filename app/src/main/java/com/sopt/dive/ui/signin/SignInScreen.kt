package com.sopt.dive.ui.signin

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import com.sopt.dive.ui.components.CustomButton
import com.sopt.dive.ui.components.CustomTextField
import com.sopt.dive.util.clickableWithoutRipple

@Composable
fun SignInScreen(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    savedStateHandle: SavedStateHandle,
) {

    var inputUserId by remember { mutableStateOf("") }
    var inputUserPw by remember { mutableStateOf("") }

    val context = LocalContext.current

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
                value = inputUserId,
                onValueChange = {
                    inputUserId = it
                },
                label = "아이디",
                placeholder = "아이디를 입력해주세요",
                imeAction = ImeAction.Next,
            )
            CustomTextField(
                title = "PW",
                value = inputUserPw,
                onValueChange = {
                    inputUserPw = it
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
                isEnabled = (inputUserId.isNotEmpty() && inputUserPw.isNotEmpty()),
                onClick = {
                    val authHandleId = savedStateHandle.get<String>("user_id")
                    val authHandlePw = savedStateHandle.get<String>("user_pw")
                    if (authHandleId == inputUserId && authHandlePw == inputUserPw) {
                        Toast.makeText(
                            context,
                            "로그인 성공",
                            Toast.LENGTH_SHORT
                        ).show()
                        onSignInClick()
                    } else {
                        Toast.makeText(
                            context,
                            "로그인 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
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
        onSignInClick = { id, pw -> },
        onSignUpClick = {}
    )
}
 */