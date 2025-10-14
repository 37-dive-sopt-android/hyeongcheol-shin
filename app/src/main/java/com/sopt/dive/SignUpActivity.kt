package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
                        onClick = { }
                    )
                }
            }
        }
    }
}



@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){

    var inputId by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("") }
    var inputNickname by remember { mutableStateOf("") }
    var inputDrinkingCapacity by remember { mutableStateOf("") }


    val context = LocalContext.current
    val intent = Intent(context, SignInActivity::class.java).apply {
        //flags =
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
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
                    value = inputPw,
                    onValueChange = {
                        inputPw = it
                    },
                    label = "소주 주량을 입력하세요",
                    placeholder = "소주 주량을 입력해주세요",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 24.dp)
        ) {
            Button(
                onClick = {},
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
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen(){
    SignUpScreen(
        modifier = Modifier,
        onClick = {}
    )
}