package com.example.tea_task.util

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tea_task.R

@Composable
fun LoadingIndicator(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color.Red,
            strokeWidth = 4.dp
        )
    }
}

@Composable
fun BackButton(
    modifier: Modifier,
    onBackClicked: () -> Unit,
    isTintEnabled: Boolean = false
) {
    Image(
        modifier = modifier
            .clickable {
                onBackClicked()
            },
        painter = painterResource(R.drawable.baseline_arrow_back),
        contentDescription = "Back button",
        colorFilter = if (isTintEnabled) ColorFilter.tint(Color.White) else null
    )
}

fun Context.toast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(
        this,
        message,
        duration
    ).show()
}