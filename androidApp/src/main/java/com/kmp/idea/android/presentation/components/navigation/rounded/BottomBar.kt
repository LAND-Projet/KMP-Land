package com.kmp.idea.android.presentation.components.navigation.rounded

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kmp.idea.android.R
import com.kmp.idea.android.navigation.Screen
import com.kmp.idea.android.presentation.common.NavigationIconButton
import com.kmp.idea.android.ui.theme.bottomBarBackground

@Composable
fun RoundedBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    userID: MutableState<String>,
    reloadPostFunc: (() -> Unit)? = null
) {
    var popupControl = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(
                bottom = 50.dp,
                start = 36.dp,
                end = 36.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(81.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = modifier
                    .width(322.dp)
                    .height(81.dp)
                    .background(
                        color = MaterialTheme.colorScheme.bottomBarBackground,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .padding(start = 30.dp, top = 15.dp, bottom = 15.dp, end = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NavigationIconButton(
                    event = { navController.navigate(Screen.HomePage.route) },
                    content = com.dardev.land.R.drawable.homeiconlight,
                    isActive = true
                )
                Spacer(modifier = Modifier.width(15.dp))
                NavigationIconButton(
                    event = { navController.navigate(Screen.HomePage.route) },
                    content = com.dardev.land.R.drawable.homeiconlight,
                    isActive = true
                )
                Spacer(modifier = Modifier.width(15.dp))
                NavigationIconButton(
                    event = {
                        navController.navigate(
                            Screen.ProfilPage.passDataForProfil(
                                userId = userID.value
                            )
                        )
                    },
                    content = if (isSystemInDarkTheme()) {
                        com.dardev.land.R.drawable.usericondark
                    } else {
                        com.dardev.land.R.drawable.usericonlight
                    },
                    isActive = false
                )
            }
        }
    }
}