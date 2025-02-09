package com.kmp.idea.android.presentation.components.navigation


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
import com.dardev.land.android.R
import com.dardev.land.android.navigation.Screen
import com.dardev.land.android.presentation.common.LandInfoButton
import com.dardev.land.android.presentation.common.LandNavigationIconButton
import com.dardev.land.android.ui.theme.profilContentTop
import com.dardev.land.android.ui.theme.textColor

@Composable
fun BottomBar(
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
                    elevation = 4.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = modifier
                    .width(322.dp)
                    .height(81.dp)
                    .background(
                        color = MaterialTheme.colorScheme.profilContentTop,
                    )
                    .padding(start = 30.dp, top = 15.dp, bottom = 15.dp, end = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                LandNavigationIconButton(
                    event = { navController.navigate(Screen.Home.route) },
                    content = com.dardev.land.R.drawable.homeiconlight,
                    isActive = true
                )
                Spacer(modifier = Modifier.width(15.dp))
                LandInfoButton(
                    icon = painterResource(id = com.dardev.land.R.drawable.pinlocation),
                    smallText = "Swipy",
                    textColor = MaterialTheme.colorScheme.textColor,
                    clickEvent = { navController.navigate(Screen.SwipePlace.route) }
                )
                Spacer(modifier = Modifier.width(15.dp))
                LandNavigationIconButton(
                    event = {
                        navController.navigate(
                            Screen.Profil.passDataForProfil(
                                userId = userID.value,
                                isYourProfil = true
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

                // Show refresh button only on HomeScreen
                /*if (reloadPostFunc != null) {
                    Spacer(modifier = Modifier.width(10.dp))
                    LandIconButton(reloadPostFunc, R.drawable.ic_refresh)
                }*/
            }
        }
        if (popupControl.value) {
            LandPopUpAddNavigation(
                navController,
                onCreatePostClick = {
                    navController.navigate(Screen.Camera.passNothing())
                },
                onCreateEventClick = {
                    navController.navigate(Screen.AddPart.AddEvent.passNothing())
                },
                openState = popupControl
            )
        }
    }
}