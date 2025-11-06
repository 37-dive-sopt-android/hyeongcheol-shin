package com.sopt.dive.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.data.BoardGame
import com.sopt.dive.data.UserData
import java.time.LocalDate

val localDate: LocalDate = LocalDate.now()

@Composable
fun HomeItem(
    user: UserData,
    modifier: Modifier = Modifier,
    painterSize: Int = 40,
) {
    val isBirth = user.birth != null && user.birth == localDate

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        HomeItemImage(user.image, user.imageDescription, painterSize)

        HomeItemUserText(user = user, isBirth = isBirth)

        Spacer(Modifier.weight(1f))

        if (isBirth) {
            HomeItemBirth()
        } else if (user.favoriteBoardGame != null) {
            HomeItemBoardGame(user.favoriteBoardGame)
        }
    }
}

@Composable
fun HomeItemImage(
    userImage: Int?,
    userImageDescription: String?,
    painterSize: Int,
    modifier: Modifier = Modifier,
) {
    if (userImage != null) {
        Image(
            painter = painterResource(userImage),
            contentDescription = userImageDescription,
            modifier = modifier.size(painterSize.dp)
        )
    } else {
        Surface(
            color = Color.Gray,
            shape = CircleShape,
            modifier = modifier.size(painterSize.dp)
        ) {
        }
    }
}

@Composable
fun HomeItemUserText(
    user: UserData,
    modifier: Modifier = Modifier,
    isBirth: Boolean = false,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .then(
                if (isBirth || user.favoriteBoardGame != null) {
                    Modifier.fillMaxWidth(0.6f)
                } else {
                    Modifier.fillMaxWidth()
                }
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = user.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = if (isBirth) "생축!! ${user.nickname}" else user.nickname,
                fontWeight = FontWeight.Normal,
                style = if (isBirth) {
                    TextStyle(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Cyan, Color.Blue, Color.Magenta)
                        )
                    )
                } else {
                    TextStyle(color = Color.Gray)
                },
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (user.comment != null) {
            Text(
                text = user.comment,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun HomeItemBoardGame(
    boardGame: BoardGame,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth(0.9f)
            .border(
                width = 2.dp,
                color = boardGame.difficulty.color,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = if (boardGame.description != null) "${boardGame.title} - ${boardGame.description}" else boardGame.title,
            color = Color.DarkGray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false),
        )
    }
}

@Composable
fun HomeItemBirth(
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.fillMaxWidth(),
    ) {
        repeat(3) {
            Image(
                painter = painterResource(R.drawable.img_birth),
                contentDescription = "생일",
                modifier = Modifier.weight(0.3f)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeItem() {
    HomeItem(
        user = UserData(
            name = "Test",
            nickname = "test",
            comment = null,
            birth = null,
            favoriteBoardGame = null,
            image = null,
            imageDescription = null,
        )
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeItemWithOtter() {
    HomeItem(
        user = UserData(
            name = "SHC",
            nickname = "Fe",
            comment = "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTest",
            birth = null,
            favoriteBoardGame = BoardGame(
                title = "T",
                description = null,
                difficulty = com.sopt.dive.data.Difficulty.HARD
            ),
            image = R.drawable.img_otter,
            imageDescription = "달수",
        ),
        painterSize = 40
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeItemWithBirth() {
    HomeItem(
        user = UserData(
            name = "SHC",
            nickname = "Fe",
            comment = "TestTestTestTestTest",
            birth = localDate,
            favoriteBoardGame = BoardGame(
                title = "Test",
                description = "test",
                difficulty = com.sopt.dive.data.Difficulty.HARD
            ),
            image = R.drawable.img_otter,
            imageDescription = "달수",
        ),
        painterSize = 40
    )
}