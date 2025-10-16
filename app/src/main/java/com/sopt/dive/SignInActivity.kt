package com.sopt.dive

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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var registeredUserId by mutableStateOf("")
        var registeredUserPw by mutableStateOf("")
        var registeredUserNickname by mutableStateOf("")
        var registeredUserDrinking by mutableStateOf("")
        var registeredUserName by mutableStateOf("")

        val signUpResultLauncher = registerForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val intentData = result.data
                registeredUserId = intentData?.getStringExtra("USER_ID") ?: ""
                registeredUserPw = intentData?.getStringExtra("USER_PW") ?: ""
                registeredUserNickname = intentData?.getStringExtra("USER_NICKNAME") ?: ""
                registeredUserDrinking = intentData?.getStringExtra("USER_DRINKING") ?: ""
                registeredUserName = intentData?.getStringExtra("USER_NAME") ?: ""
                //TODO("비어 있으면 로그인 안 되도록 설정")
            }
        }

        setContent {
            DiveTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    SignInScreen(
                        registeredUserId = registeredUserId,
                        registeredUserPw = registeredUserPw,
                        onSignInClick = { inputUserId, inputUserPw ->
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("USER_ID", inputUserId)
                            intent.putExtra("USER_PW", inputUserPw)
                            intent.putExtra("USER_NICKNAME", registeredUserNickname)
                            intent.putExtra("USER_DRINKING", registeredUserDrinking)
                            intent.putExtra("USER_NAME", registeredUserName)
                            setResult(RESULT_OK, intent)
                            startActivity(intent)
                            finish()
                        },
                        onSignUpClick = {
                            signUpResultLauncher.launch(
                                Intent(this, SignUpActivity::class.java)
                            )
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
    registeredUserId: String,
    registeredUserPw: String,
    onSignInClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
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
    ) {
        Text(
            text = "Welcome To Sopt",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(top = 32.dp)
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
                label = "아이디를 입력해주세요",
                placeholder = "아이디",
            )
            CustomTextField(
                title = "PW",
                value = inputUserPw,
                onValueChange = {
                    inputUserPw = it
                },
                label = "비밀번호를 입력하세요",
                placeholder = "비밀번호",
                isTextHidden = true,
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(bottom = 24.dp)
        ) {
            CustomButton(
                text = "Welcome to Sopt",
                onClick = {
                    if (registeredUserId == inputUserId && registeredUserPw == inputUserPw) {
                        Toast.makeText(
                            context,
                            "로그인 성공",
                            Toast.LENGTH_SHORT
                        ).show()
                        onSignInClick(inputUserId, inputUserPw)
                    } else {
                        Toast.makeText(
                            context,
                            "로그인 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                        //TODO("argument 썼을때는 왜 안되는가")
                    }
                },
            )
            Text(
                text = "회원가입하기",
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onSignUpClick()
                        }
                    )
            )
        }
    }
}


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