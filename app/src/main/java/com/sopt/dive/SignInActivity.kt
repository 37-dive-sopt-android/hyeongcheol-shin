package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                ) { innerPadding ->
                    SignInScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                        //찾아보기
                        //TODO("innerPadding이 왜 있는 것인가?")
                    )
                }
            }
        }
    }
}


@Composable
fun SignInScreen(
    modifier: Modifier = Modifier
){

    var inputId by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("") }

    val context = LocalContext.current
    val intent = Intent(context, SignUpActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Welcome To Sopt",
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
                    text = "Welcome To Sopt",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }
            Text(
                text = "회원가입하기",
                modifier = Modifier
                    .clickable(
                        onClick = {
                            context.startActivity(intent)
                        }
                    )
            )
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen()
}