package com.kmp.idea.android.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.idea.android.ui.theme.textColor

@Composable
fun IconTextButton(
    icon: Painter,
    smallText: String,
    clickEvent: () -> Unit = {}
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            modifier = Modifier.clickable { clickEvent() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = icon,
                contentDescription = "Info_Button"
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = smallText,
                color = MaterialTheme.colorScheme.textColor,
                fontSize = 13.sp
            )
        }
    }
}