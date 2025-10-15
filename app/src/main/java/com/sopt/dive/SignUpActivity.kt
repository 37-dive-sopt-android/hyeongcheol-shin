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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "SIGN UP",
            style = TextStyle(
                fontSize = 36.sp,
            ),
            modifier = Modifier
                .padding(top = 32.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column {
                Text(
                    text = "ID",
                    style = TextStyle(
                        fontSize = 16.sp
                    )

                )
                CustomTextField(
                    value = inputId,
                    onValueChange = {
                        inputId = it
                    },
                    label = "아이디를 입력해주세요",
                    placeholder = "ID를 입력해주세요",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column {
                Text(
                    text = "PW",
                    style = TextStyle(
                        fontSize = 16.sp
                    )

                )
                CustomTextField(
                    value = inputPw,
                    onValueChange = {
                        inputPw = it
                    },
                    label = "비밀번호를 입력하세요",
                    placeholder = "비밀번호를 입력해주세요",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column {
                Text(
                    text = "NICKNAME",
                    style = TextStyle(
                        fontSize = 16.sp
                    )

                )
                CustomTextField(
                    value = inputNickname,
                    onValueChange = {
                        inputNickname = it
                    },
                    label = "닉네임을 입력하세요",
                    placeholder = "닉네임을 입력해주세요",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column {
                Text(
                    text = "주량",
                    style = TextStyle(
                        fontSize = 16.sp
                    )

                )
                CustomTextField(
                    value = inputDrinking,
                    onValueChange = {
                        inputDrinking = it
                    },
                    label = "소주 주량을 입력하세요(숫자만 입력해주세요)",
                    placeholder = "소주 주량을 입력해주세요",
                    keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column {
                Text(
                    text = "이름",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
                CustomTextField(
                    value = inputName,
                    onValueChange = {
                        inputName = it
                    },
                    label = "이름을 입력하세요",
                    placeholder = "이름을 입력해주세요",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Button(
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
                        "회원가입에 성공하셨습니다.",
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
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Unspecified,
            )
        ) {
            Text(
                text = "회원가입하기",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen(){
    SignUpScreen(
        modifier = Modifier,
        onClick = { }
    )
}
*/