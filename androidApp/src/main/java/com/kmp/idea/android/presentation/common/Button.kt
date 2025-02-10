package com.kmp.idea.android.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kmp.idea.android.ui.theme.buttonBackground
import com.kmp.idea.android.ui.theme.buttonContent
import com.kmp.idea.android.ui.theme.errorColor
import com.kmp.idea.android.ui.theme.textColor
import com.kmp.idea.android.ui.theme.poppins_Regular
import com.kmp.idea.android.ui.theme.lightForeground
import com.kmp.idea.android.ui.theme.buttonBackground
import com.kmp.idea.android.ui.theme.buttonContent
import com.kmp.idea.android.ui.theme.lightBackground

@Composable
fun Button(eventClick: () -> Unit, content: String) {

    Button(
        onClick = eventClick,
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .width(322.dp)
                .height(75.dp)
                .fillMaxWidth()
                .shadow(3.dp, RoundedCornerShape(50.dp))
                .background(
                    color = MaterialTheme.colorScheme.buttonBackground,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = content,
                color = MaterialTheme.colorScheme.buttonContent,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun GoogleButton(eventClick: () -> Unit, content: String) {
    Button(
        onClick = eventClick,
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .width(322.dp)
                .height(75.dp)
                .fillMaxWidth()
                .shadow(3.dp, RoundedCornerShape(50.dp))
                .border(5.dp, Color.Blue, RoundedCornerShape(50.dp))
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp)
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = com.dardev.land.R.drawable.googlelogo),
                    contentDescription = "googleLogo",
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = content,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins_Regular,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
            }
        }
    }
}

@Composable
fun TextIconButton(eventClick: () -> Unit, content: String, iconContent: Int) {
    Button(
        onClick = eventClick,
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .width(149.dp)
                .height(48.dp)
                .fillMaxWidth()
                .shadow(3.dp, RoundedCornerShape(50.dp))
                .background(MaterialTheme.colorScheme.buttonBackground),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = iconContent),
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.buttonContent
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = content,
                    color = MaterialTheme.colorScheme.buttonContent,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun RemoveAccountButton(eventClick: () -> Unit, content: String) {
    val gradientColor = listOf(errorColor, lightForeground)

    Button(
        onClick = eventClick,
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .width(322.dp)
                .height(75.dp)
                .fillMaxWidth()
                .shadow(3.dp, RoundedCornerShape(50.dp))
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColor),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content,
                    color = lightForeground,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun TextButton(eventClick: () -> Unit, content: String) {
    Button(
        onClick = eventClick,
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .width(149.dp)
                .height(48.dp)
                .fillMaxWidth()
                .shadow(3.dp, RoundedCornerShape(50.dp))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content,
                    color = MaterialTheme.colorScheme.textColor,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
fun TextIconCancelRequestButton(eventClick: () -> Unit, content: String, iconContent: Int) {
    Button(
        onClick = eventClick,
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .width(149.dp)
                .height(48.dp)
                .fillMaxWidth()
                .shadow(3.dp, RoundedCornerShape(50.dp))
                .background(errorColor),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = content,
                    color = lightBackground,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(id = iconContent),
                    contentDescription = "",
                    tint = lightBackground
                )
            }
        }
    }
}

@Composable
fun RemoveAccountValidateButton(eventClick: () -> Unit, content: String) {
    val gradientColor = listOf(errorColor, lightForeground)

    Button(
        onClick = eventClick,
        contentPadding = ButtonDefaults.TextButtonContentPadding,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .width(155.dp)
                .height(40.dp)
                .fillMaxWidth()
                .shadow(3.dp, RoundedCornerShape(50.dp))
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColor),
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = content,
                color = lightBackground,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}