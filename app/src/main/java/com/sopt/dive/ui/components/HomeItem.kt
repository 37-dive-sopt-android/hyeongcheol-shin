package com.sopt.dive.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R

@Composable
fun HomeItem(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    @DrawableRes painter: Int? = null,
    painterDescription: String? = null,
    painterSize: Int = 40,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if(painter != null){
                Image(
                    painter = painterResource(painter),
                    contentDescription = painterDescription,
                    modifier = Modifier.size(painterSize.dp)
                )
            }
            else{
                Surface(
                    color = Color.Gray,
                    shape = CircleShape,
                    modifier = Modifier.size(painterSize.dp)
                ) {

                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier,
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    text = description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeItem() {
    HomeItem(
        title = "Test",
        description = "Test",
        painter = null,
        painterDescription = null
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeItemWithOtter() {
    HomeItem(
        title = "Test",
        description = "Test",
        painter = R.drawable.img_otter,
        painterDescription = null,
        painterSize = 60,
    )
}