package com.kmp.idea.android.presentation.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kmp.idea.android.ui.theme.textColor
import com.kmp.idea.android.ui.theme.topBarBackground

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    haveNotification: MutableState<Boolean>,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(106.dp)
                .background(MaterialTheme.colorScheme.topBarBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 41.dp, bottom = 25.dp, end = 20.dp)
                    .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                text = "[Your App]",
                color = MaterialTheme.colorScheme.textColor,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
