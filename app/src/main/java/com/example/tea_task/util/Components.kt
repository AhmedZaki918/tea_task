package com.example.tea_task.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tea_task.R
import com.example.tea_task.ui.theme.LARGE_MARGIN
import com.example.tea_task.ui.theme.MEDIUM_MARGIN
import com.example.tea_task.ui.theme.OffWhite
import com.example.tea_task.ui.theme.OffWhiteLight

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.White,
            strokeWidth = 4.dp
        )
    }
}


@Composable
fun BackButton(
    modifier: Modifier,
    onBackClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(40.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary,
        shadowElevation = 4.dp
    ) {
        Image(
            modifier = modifier
                .clickable {
                    onBackClicked()
                },
            painter = painterResource(R.drawable.baseline_arrow_back),
            contentDescription = "Back button",
            colorFilter =  ColorFilter.tint(Color.White)
        )
    }
}


@Composable
fun ErrorUi(
    isNetworkError: Boolean,
    modifier: Modifier = Modifier,
    onRetryClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.padding(bottom = LARGE_MARGIN),
            contentDescription = "",
            painter = painterResource(
                id = if (isNetworkError) R.drawable.no_signal else R.drawable.error_api
            )
        )

        Text(
            color = OffWhiteLight,
            modifier = Modifier.padding(bottom = MEDIUM_MARGIN),
            text = if (isNetworkError) stringResource(R.string.connection_lost)
            else stringResource(R.string.ops_something_went_wrong)
        )

        androidx.compose.material3.Button(
            onClick = { onRetryClicked.invoke() }
        ) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun Title(
    color: Color = OffWhite.copy(alpha = 0.6f),
    fontSize : TextUnit = 16.sp,
    modifier: Modifier,
    description: String,
    fontFamily : FontFamily? = null
) {
    Text(
        fontFamily = fontFamily,
        fontSize = fontSize,
        color = color,
        text = description,
        modifier = modifier
    )
}

@Composable
fun SubTitle(
    modifier: Modifier,
    description: String
) {
    Text(
        color = OffWhiteLight,
        text = description,
        modifier = modifier
    )
}






