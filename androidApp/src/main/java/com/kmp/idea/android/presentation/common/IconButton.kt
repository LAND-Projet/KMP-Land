package com.kmp.idea.android.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kmp.idea.android.ui.theme.errorColor
import com.kmp.idea.android.ui.theme.iconbuttonBackground
import com.kmp.idea.android.ui.theme.iconbuttonContent
import com.kmp.idea.android.ui.theme.successColor
import com.kmp.idea.android.ui.theme.infoColor
import com.kmp.idea.android.ui.theme.lightBackground

@Composable
fun IconButton(event: () -> Unit, content: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .width(35.dp)
            .height(35.dp),
        shape = RoundedCornerShape(10),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.iconbuttonBackground)
    ) {
        IconButton(onClick = event) {
            Icon(
                painter = painterResource(id = content),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.iconbuttonContent
            )
        }
    }
}

@Composable
fun IconWithoutButton(event: () -> Unit, content: Int, isActive: Boolean) {
    if (isActive) {
        IconButton(onClick = event) {
            Icon(
                painter = painterResource(id = content),
                contentDescription = "",
                tint = successColor
            )
        }
    } else {
        IconButton(onClick = event) {
            Icon(
                painter = painterResource(id = content),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun IconNotificationButton(event: () -> Unit, content: Int, isActive: Boolean) {
    IconButton(onClick = event) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                painter = painterResource(id = content),
                contentDescription = ""
            )
            if (isActive) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(errorColor, CircleShape)
                        .align(Alignment.TopEnd)
                )
            }
        }
    }
}

@Composable
fun NavigationIconButton(event: () -> Unit, content: Int, isActive: Boolean) {
    if (isActive) {
        Card(
            modifier = Modifier
                .width(38.dp)
                .height(39.dp),
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(infoColor)
        ) {
            IconButton(onClick = event) {
                Icon(
                    painter = painterResource(id = content),
                    contentDescription = "",
                    tint = lightBackground
                )
            }
        }
    } else {
        IconButton(onClick = event) {
            Icon(
                painter = painterResource(id = content),
                contentDescription = ""
            )
        }
    }
}