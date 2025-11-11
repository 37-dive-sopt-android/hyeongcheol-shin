package com.sopt.dive.ui.main.search

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.util.clickableWithoutRipple

enum class ImgState { Collapsed, Expanded }

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier
) {
    SearchScreen(
        modifier = modifier
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    var isEnabled by remember { mutableStateOf(ImgState.Collapsed) }

    val animateFloatAsState: Float by animateFloatAsState(
        targetValue = if (isEnabled == ImgState.Collapsed) 0f else 180f,
        animationSpec = tween(durationMillis = 2000, easing = EaseInOut)
    )

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            if (animateFloatAsState > 90) {
                painterResource(id = R.drawable.img_king)
            } else {
                painterResource(id = R.drawable.img_card_back3)
            },
            contentDescription = "card",
            modifier = Modifier
                .graphicsLayer(
                    rotationY = animateFloatAsState,
                    cameraDistance = 12f,
                )
                .size(width = 300.dp, height = 500.dp)
                .clickableWithoutRipple(
                    onClick = {
                        isEnabled = when (isEnabled) {
                            ImgState.Collapsed -> ImgState.Expanded
                            ImgState.Expanded -> ImgState.Collapsed
                        }
                    }
                )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSearchScreen() {
    SearchScreen()
}