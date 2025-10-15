package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var userId by mutableStateOf("")
        var userPw by  mutableStateOf("")
        var userNickName by mutableStateOf("")
        var userDrinking by mutableStateOf("")

        val activityLauncherSignInToMain = registerForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ){ result ->
            if(result.resultCode == RESULT_OK){
                val intentData = result.data
                userId = intentData?.getStringExtra("USER_ID") ?: ""
                userPw = intentData?.getStringExtra("USER_PW") ?: ""
                userNickName = intentData?.getStringExtra("USER_NICKNAME") ?: ""
                userDrinking = intentData?.getStringExtra("USER_DRINKING") ?: ""
            }
        }

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        userId = userId,
                        userPw = userPw,
                        userNickName = userNickName,
                        userDrinking = userDrinking,
                        modifier = Modifier.padding(innerPadding),
                        )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    userId: String,
    userPw: String,
    userNickName: String,
    userDrinking: String,
    modifier: Modifier,
    ){
    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        Column {
            Row {
                Surface(
                    color = Color.Blue,
                    modifier = Modifier
                        .size(20.dp),
                ) {
                }
                Text("MyName")
            }
            Text("안녕하세요")
        }
        UserDetail(
            title = "ID",
            info = userId
        )
        UserDetail(
            title = "PW",
            info = userPw
        )
        UserDetail(
            title = "NICKNAME",
            info = userNickName
        )
        UserDetail(
            title = "주량",
            info = userDrinking
        )
    }
}

@Composable
fun UserDetail(
    title: String,
    info: String
){
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = title,
            fontSize = 24.sp
        )
        Text(
            text = info,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainActivity(){
    MainScreen(
        modifier = Modifier,
        userId = "test",
        userPw = "test",
        userNickName = "Fe",
        userDrinking = "0",
    )
}