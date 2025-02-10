package com.kmp.idea.android.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current

    val userId = remember {
        mutableStateOf("")
    }

    val haveNotification = remember {
        mutableStateOf(false)
    }

    val showAppIntro = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = true) {
        userId.value = viewModel.getUserID()
        viewModel.analyticsSetUserId(userId.value)
        // haveNotification.value = viewModel.userHaveARequest(userId.value)
        viewModel.startTimer(context)
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.stopTimer()
        }
    }

    Scaffold(
        bottomBar = {
            if (viewModel.verifyOnBoardingMapCheck()) {
                BottomBar(
                    navController = navController,
                    userID = userId,
                    reloadPostFunc = viewModel::reloadDataMap
                )
            } else {
                IntroShowcase(
                    showIntroShowCase = showAppIntro.value,
                    dismissOnClickOutside = false,
                    onShowCaseCompleted = {
                        showAppIntro.value = false
                        viewModel.setOnBoardingMapToCheck()
                    }
                ) {
                    FloatingActionButton(
                        onClick = {},
                        modifier = Modifier.introShowCaseTarget(
                            index = 0,
                            style = ShowcaseStyle.Default.copy(
                                backgroundColor = Color(0xFF1C0A00),
                                backgroundAlpha = 0.98f,
                                targetCircleColor = Color.White
                            ),
                            content = {
                                Column {
                                    Text(
                                        text = AndroidStringResource(
                                            id = SharedRes.strings.onboarding_tuto_map
                                        ),
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = AndroidStringResource(
                                            id = SharedRes.strings.onboarding_tuto_ticketmaster
                                        ),
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = AndroidStringResource(
                                            id = SharedRes.strings.onboarding_tuto_place
                                        ),
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = AndroidStringResource(
                                            id = SharedRes.strings.onboarding_tuto_bottom_bar
                                        ),
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                            }
                        ),
                        backgroundColor = Color.Transparent,
                        contentColor = Color.White,
                        elevation = FloatingActionButtonDefaults.elevation(6.dp)
                    ) {
                        BottomBar(
                            navController = navController,
                            userID = userId,
                            reloadPostFunc = viewModel::reloadDataMap
                        )
                    }
                }
            }
        },
        topBar = {
            Column(
                modifier = Modifier
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(
                            bottomStart = 50.dp,
                            bottomEnd = 50.dp
                        )
                    )
            ) {
                HomeTopBar(
                    navController = navController,
                    haveNotification = haveNotification
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LandMaps(
                viewModel.listPost.value,
                viewModel.listEvent.value,
                viewModel.listTicketMaster.value,
                viewModel.listSwipy.value,
                navController,
                false,
                null
            )
        }
    }
}
