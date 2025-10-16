package com.sopt.dive

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        onClick = { userId, userPw,userNickname, userDrinking, userName ->
                            val resultIntent = Intent(this, SignInActivity::class.java)
                            resultIntent.putExtra("USER_ID", userId)
                            resultIntent.putExtra("USER_PW",userPw)
                            resultIntent.putExtra("USER_NICKNAME",userNickname)
                            resultIntent.putExtra("USER_DRINKING",userDrinking)
                            resultIntent.putExtra("USER_NAME", userName)
                            setResult(Activity.RESULT_OK, resultIntent)
                            //TODO("Activity 생략 가능 왜?")
                            finish()
                        }
                    )
                }
            }
        }
    }
}



@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onClick: (String, String, String, String, String) -> Unit
){

    var inputId by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("") }
    var inputNickname by remember { mutableStateOf("") }
    var inputDrinking by remember { mutableStateOf("") }
    var inputName by remember { mutableStateOf("") }

    val context = LocalContext.current

    val koreanRegex = Regex("^[ㄱ-ㅎㅏ-ㅣ가-힣\\s]*$")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "SIGN UP",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 32.dp, bottom = 40.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CustomTextField(
                title = "ID",
                value = inputId,
                onValueChange = {
                    inputId = it
                },
                label = "ID를 입력하세요",
                placeholder = "6 ~ 10 자리 입력",
            )
            CustomTextField(
                title = "PW",
                value = inputPw,
                onValueChange = {
                    inputPw = it
                },
                label = "비밀번호를 입력하세요",
                placeholder = "8 ~ 12 자리 입력",
            )
            CustomTextField(
                title = "NICKNAME",
                value = inputNickname,
                onValueChange = {
                    inputNickname = it
                },
                label = "닉네임을 입력하세요",
                placeholder = "한 글자 이상 입력",
            )
            CustomTextField(
                title = "주량",
                value = inputDrinking,
                onValueChange = {
                    inputDrinking = it
                },
                label = "소주 주량을 입력하세요",
                placeholder = "숫자만 입력",
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            CustomTextField(
                title = "NAME",
                value = inputName,
                onValueChange = {
                    if(koreanRegex.matches(it)) {
                        inputName = it
                    }
                },
                label = "이름을 입력하세요",
                placeholder = "한글로 입력",
                )
        }
        Spacer(Modifier.weight(1f))
        CustomButton(
            buttonText = "Sign Up",
            modifier = Modifier.padding(vertical = 16.dp),
            onClick = {
                if (
                    inputId.length in 6..10 &&
                    inputPw.length in 8..12 &&
                    (inputNickname.isNotEmpty() && inputNickname.isNotBlank()) &&
                    (inputDrinking.toIntOrNull() != null && inputDrinking.toInt() >= 0) &&
                    (inputName.isNotEmpty() && inputName.isNotBlank())
                ){
                    Toast.makeText(
                        context,
                        "회원가입에 성공하셨습니다",
                        Toast.LENGTH_SHORT
                    ).show()
                    onClick(inputId, inputPw, inputNickname, inputDrinking, inputName)
                } else{
                    Toast.makeText(
                        context,
                        "조건을 확인해주세요",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen(){
    SignUpScreen(
        modifier = Modifier,
        onClick = {id, pw, nickName, drinking, name ->}
    )
}