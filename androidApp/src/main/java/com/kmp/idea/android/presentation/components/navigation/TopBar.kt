package com.kmp.idea.android.presentation.components.navigation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dardev.land.android.ui.theme.textColor

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    haveNotification: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 41.dp, bottom = 25.dp, end = 20.dp)
                .align(Alignment.End),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            // Title
            Text(
                text = "My Land",
                color = MaterialTheme.colorScheme.textColor,
                style = MaterialTheme.typography.titleLarge
            )
            /*Spacer(modifier = Modifier.width(45.dp))
            Icon Parameter et Notification
            LandIconWithoutButton(
                event = { navController.navigate(Screen.Parameter.route) },
                content = if (isSystemInDarkTheme()) {
                    com.dardev.land.R.drawable.gearicondark
                } else {
                    com.dardev.land.R.drawable.geariconlight
                },
                isActive = false
            )
            LandIconNotificationButton(
                event = { navController.navigate(Screen.Notification.route) },
                content = if (isSystemInDarkTheme()) {
                    com.dardev.land.R.drawable.notificationicondark
                } else {
                    com.dardev.land.R.drawable.notificationiconlight
                },
                isActive = haveNotification.value
            )*/
        }
    }
}