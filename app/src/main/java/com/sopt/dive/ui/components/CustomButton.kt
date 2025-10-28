package com.sopt.dive.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
) {
    Button(
        enabled = isEnabled,
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Unspecified,
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomButton() {
    CustomButton(
        text = "Test Button",
        onClick = {}
    )
}