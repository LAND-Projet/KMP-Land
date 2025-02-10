package com.kmp.idea.android.presentation.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kmp.idea.android.R
import com.kmp.idea.android.ui.theme.buttonBackground
import com.kmp.idea.android.ui.theme.errorColor
import com.kmp.idea.android.ui.theme.lightForeground

@Composable
fun LoadingButton() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .width(322.dp)
            .height(75.dp)
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(50.dp))
            .background(
                color = MaterialTheme.colorScheme.buttonBackground,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable(
                onClick = { },
                enabled = false,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = "Chargement",
            modifier = Modifier.rotate(rotation)
        )
    }
}

@Composable
fun LoadingNotificationButton() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .width(160.dp)
            .height(40.dp)
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(50.dp))
            .background(
                color = MaterialTheme.colorScheme.buttonBackground,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable(
                onClick = { },
                enabled = false,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = "Chargement",
            modifier = Modifier.rotate(rotation)
        )
    }
}

@Composable
fun LoadingNotificationRefuseButton() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .width(160.dp)
            .height(40.dp)
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(50.dp))
            .background(
                color = errorColor,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable(
                onClick = { },
                enabled = false,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = "Chargement",
            modifier = Modifier.rotate(rotation),
            tint = lightForeground
        )
    }
}

@Composable
fun LoadingParameterButton() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .width(160.dp)
            .height(40.dp)
            .fillMaxWidth()
            .shadow(3.dp, RoundedCornerShape(50.dp))
            .background(
                color = MaterialTheme.colorScheme.buttonBackground,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable(
                onClick = { },
                enabled = false,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = "Chargement",
            modifier = Modifier.rotate(rotation)
        )
    }
}

@Composable
fun LoadingParameterIcon() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Icon(
        painter = painterResource(id = R.drawable.ic_refresh),
        contentDescription = "Chargement",
        modifier = Modifier.rotate(rotation)
    )
}

@Composable
fun LoadingIcon() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable(
                onClick = { },
                enabled = false,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = "Chargement",
            modifier = Modifier.rotate(rotation)
        )
    }
}

@Composable
fun LoadingIconScreen() {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = { },
                enabled = false,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_refresh),
            contentDescription = "Chargement",
            modifier = Modifier.rotate(rotation)
        )
    }
}