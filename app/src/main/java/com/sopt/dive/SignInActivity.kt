package com.sopt.dive

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var signUpId by mutableStateOf("")
        var signUpPw by  mutableStateOf("")
        var signUpNickName by mutableStateOf("")
        var signUpDrinking by mutableStateOf("")
        var signUpName by mutableStateOf("")

        val activityLauncherSignUpToSignIn = registerForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ){ result ->
            if(result.resultCode == RESULT_OK){
                val intentData = result.data
                signUpId = intentData?.getStringExtra("USER_ID") ?: ""
                signUpPw = intentData?.getStringExtra("USER_PW") ?: ""
                signUpNickName = intentData?.getStringExtra("USER_NICKNAME") ?: ""
                signUpDrinking = intentData?.getStringExtra("USER_DRINKING") ?: ""
                signUpName = intentData?.getStringExtra("USER_NAME") ?: ""
                 //TODO("비어 있으면 로그인 안 되도록 설정")
            }
        }

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                ) { innerPadding ->
                    SignInScreen(
                        signUpId = signUpId,
                        signUpPw = signUpPw,
                        signUpButton = {
                            activityLauncherSignUpToSignIn.launch(
                                Intent(this, SignUpActivity::class.java)
                            )
                        },
                        signInButton = { signInId, signInPw ->
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("USER_ID",signInId)
                            intent.putExtra("USER_PW", signInPw)
                            intent.putExtra("USER_NICKNAME", signUpNickName)
                            intent.putExtra("USER_DRINKING", signUpDrinking)
                            intent.putExtra("USER_NAME", signUpName)
                            setResult(Activity.RESULT_OK, intent)
                            startActivity(intent)
                            finish()
                        },
                        modifier = Modifier
                            .padding(innerPadding),
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
    modifier: Modifier = Modifier,
    signUpId: String,
    signUpPw: String,
    signInButton: (String, String) -> Unit,
    signUpButton: () -> Unit,
){

    var inputId by remember { mutableStateOf("") }
    var inputPw by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
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
                enabled = (inputId.isNotEmpty() && inputPw.isNotEmpty()),
                onClick = {
                    if(signUpId == inputId && signUpPw == inputPw){
                        Toast.makeText(
                            context,
                            "Success",
                            Toast.LENGTH_SHORT
                        ).show()
                        signInButton(inputId,inputPw)
                    }
                    else{
                        Toast.makeText(
                            context,
                            "Fail",
                            Toast.LENGTH_SHORT
                        ).show()
                        //TODO("argument 썼을때는 왜 안되는가")
                    }
                },
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
                            signUpButton()
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

/*
@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(
        modifier = TODO(),
        testText = TODO(),
        signUpClick = TODO()
    )
}

 */