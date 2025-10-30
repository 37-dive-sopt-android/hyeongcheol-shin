package com.sopt.dive.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.R

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "여기는 \nSearch!"
        )
        Image(
            painter = painterResource(R.drawable.img_otter),
            contentDescription = "달수"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSearchScreen(){
    SearchScreen()
}