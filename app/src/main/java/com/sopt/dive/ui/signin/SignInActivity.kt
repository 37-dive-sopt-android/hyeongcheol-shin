package com.sopt.dive.ui.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.components.CustomButton
import com.sopt.dive.ui.components.CustomTextField
import com.sopt.dive.ui.main.MainActivity
import com.sopt.dive.ui.signup.SignUpActivity
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.IntentKeys
import com.sopt.dive.util.clickableWithoutRipple

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
                registeredUserId = intentData?.getStringExtra(IntentKeys.USER_ID) ?: ""
                registeredUserPw = intentData?.getStringExtra(IntentKeys.USER_PW) ?: ""
                registeredUserNickname = intentData?.getStringExtra(IntentKeys.USER_NICKNAME) ?: ""
                registeredUserDrinking = intentData?.getStringExtra(IntentKeys.USER_DRINKING) ?: ""
                registeredUserName = intentData?.getStringExtra(IntentKeys.USER_NAME) ?: ""
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
                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra(IntentKeys.USER_ID, inputUserId)
                                putExtra(IntentKeys.USER_PW, inputUserPw)
                                putExtra(IntentKeys.USER_NICKNAME, registeredUserNickname)
                                putExtra(IntentKeys.USER_DRINKING, registeredUserDrinking)
                                putExtra(IntentKeys.USER_NAME, registeredUserName)
                            }
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