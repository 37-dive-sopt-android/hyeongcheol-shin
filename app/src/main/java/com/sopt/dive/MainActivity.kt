package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId  = intent.getStringExtra("USER_ID") ?: ""
        val userPw = intent.getStringExtra("USER_PW") ?: ""
        val userNickName = intent.getStringExtra("USER_NICKNAME") ?: ""
        val userDrinking = intent.getStringExtra("USER_DRINKING") ?: ""
        val userName = intent.getStringExtra("USER_NAME") ?: ""

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        userId = userId,
                        userPw = userPw,
                        userNickName = userNickName,
                        userDrinking = userDrinking,
                        userName = userName,
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
    userName: String,
    modifier: Modifier,
    ){
    Column(
        verticalArrangement = Arrangement.spacedBy(40.dp),
        modifier = modifier
            .padding(horizontal = 24.dp)
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(100),
                    color = Color.Unspecified,
                    modifier = Modifier
                        .size(52.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_otter),
                        contentDescription = "달수",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Text(
                    text = userName,
                    fontSize = 28.sp
                )
            }
            Text(
                text = "$userName 님 SOPT에 오신 것을 환영합니다",
                fontSize = 16.sp
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
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
                info = "$userDrinking 병",
                uniqueText = (userDrinking.toInt() > 3)
            )
        }
    }
}

@Composable
fun UserDetail(
    title: String,
    info: String,
    uniqueText: Boolean = false
){

    val gradientColor = listOf(Color.Cyan, Color.Blue, Color.Magenta)

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = title,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = info,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            style = if(uniqueText){
                TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColor
                    ) //1셈때 자료 보다가 그냥 넣어보고 싶었어요 ㅎㅎ
                )
            }else{
                TextStyle(
                    color = Color.DarkGray
                )
            }
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
        userName = "SHC"
    )
}