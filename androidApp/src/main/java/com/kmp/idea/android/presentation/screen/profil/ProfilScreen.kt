package com.kmp.idea.android.presentation.screen.profil

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun ProfilScreen(
    navController: NavController,
    context: Context,
) {
    val uriHandler = LocalUriHandler.current
    val options = BitmapFactory.Options().apply {
        inSampleSize = 2
    }
    val bitmap = BitmapFactory.decodeResource(
        context.resources,
        com.dardev.land.R.drawable.reliefbleu,
        options
    )
    val imageBitmap = bitmap.asImageBitmap()
    val dataUserIdNavigation by viewModel.userId.collectAsState()
    val dataConfirmProfilNavigation by viewModel.yourProfile.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val list: List<LandPost> = emptyList()
    val listPin = remember {
        mutableStateOf(list)
    }

    var user = remember {
        mutableStateOf(LandUser("", "", "", "", true))
    }

    val connectedUserId = remember {
        mutableStateOf("")
    }

    val isYourProfil = remember {
        mutableStateOf(false)
    }

    val imageUrl = remember {
        mutableStateOf("")
    }

    val followingCount = remember {
        mutableStateOf<Long>(0)
    }

    val followerCount = remember {
        mutableStateOf<Long>(0)
    }

    val isFollow = remember {
        mutableStateOf(false)
    }

    val isRequestPending = remember {
        mutableStateOf(false)
    }
    val isLoading = remember {
        mutableStateOf(false)
    }

    var showFollowDialog by rememberSaveable {
        mutableStateOf(false)
    }
    var showFollowers by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        isLoading.value = true
        dataConfirmProfilNavigation?.let {
            isYourProfil.value = it
        }
        connectedUserId.value = viewModel.getActiveUserId()
        if (isYourProfil.value) {
            user.value = viewModel.getLandUser(connectedUserId.value)
            // followingCount.value = viewModel.getFollowingUserCount(userId = connectedUserId.value)
            // followerCount.value = viewModel.getFollowerUserCount(userId = connectedUserId.value)
            user.value.IsPrivate = viewModel.getAccountType(connectedUserId.value) == true
            // listPin.value = viewModel.getAllPostFromUser(connectedUserId.value)
            isFollow.value = true
            isRequestPending.value = false
        } else {
            dataUserIdNavigation?.let {
                user.value = viewModel.getLandUser(it)
                // followingCount.value = viewModel.getFollowingUserCount(userId = it)
                // followerCount.value = viewModel.getFollowerUserCount(userId = it)
                user.value.IsPrivate = viewModel.getAccountType(it) == true
                // listPin.value = viewModel.getAllPostFromUser(it)
                isFollow.value = viewModel.isUserAlreadyFollow(it, connectedUserId.value) == true
                isRequestPending.value = viewModel.isFollowRequestSend(connectedUserId.value, it)
                isYourProfil.value = connectedUserId.value == it
            }
        }

        if (user.value.ProfilPicture.isNotEmpty()) {
            val ref = Firebase.storage.reference
                .child(user.value.ProfilPicture)
            ref.downloadUrl
                .addOnSuccessListener { uri ->
                    imageUrl.value = uri.toString()
                }
        }
        isLoading.value = false
    }

    // Will need to incorporate followers and following list when api is built
    if (showFollowDialog) {
        FollowerDialog(
            onDismissRequest = { showFollowDialog = false },
            listOfUsers = listOf<LandUser>(),
            showFollowers = showFollowers
        )
    }

    Scaffold(
        topBar = {}
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Image de fond
            Image(
                bitmap = imageBitmap,
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .alpha(0.5f)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 50.dp,
                                bottomEnd = 50.dp
                            )
                        )
                        .background(
                            MaterialTheme.colorScheme.profilContent
                        )
                        .border(
                            width = 0.5.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(
                                bottomStart = 50.dp,
                                bottomEnd = 50.dp
                            )
                        )

                ) {
                    if (isLoading.value) {
                        LandLoadingIcon()
                    } else {
                        LandProfileTopBar(
                            navController = navController,
                            signOutEvent = {
                                coroutineScope.launch {
                                    viewModel.userSignOut()
                                    viewModel.automateWorker(context)
                                }
                                viewModel.analyticsEvent("Click_Disconnect_Button")
                            },
                            isYourProfile = isYourProfil.value,
                            idVerification = connectedUserId.value != user.value.UserId
                        )
                        LandUserInfoItem(
                            landUser = user.value,
                            eventFollow = {
                                coroutineScope.launch {
                                    if (!user.value.IsPrivate) {
                                        viewModel.setFollowingUser(
                                            userId = connectedUserId.value,
                                            userIdFollow = user.value.UserId
                                        )
                                        viewModel.setFollowerUser(
                                            userId = user.value.UserId,
                                            userIdFollower = connectedUserId.value
                                        )
                                        isFollow.value = true
                                        viewModel.analyticsEvent("Click_Follow_Button")
                                    } else {
                                        viewModel.sendRequestAccount(
                                            connectedUserId.value,
                                            user.value.UserId
                                        )
                                        isRequestPending.value = true
                                        isFollow.value = false
                                        viewModel.analyticsEvent("Click_Send_Request_Follow_Button")
                                    }
                                }
                            },
                            eventUnFollow = {
                                coroutineScope.launch {
                                    if (isFollow.value) {
                                        viewModel.setUnFollowingUser(
                                            userId = connectedUserId.value,
                                            userIdFollow = user.value.UserId
                                        )
                                        viewModel.setUnFollowerUser(
                                            userId = user.value.UserId,
                                            userIdFollower = connectedUserId.value
                                        )
                                        isFollow.value = false
                                        viewModel.analyticsEvent("Click_UnFollow_Button")
                                    }
                                }
                            },
                            eventCancelFollowRequest = {
                                coroutineScope.launch {
                                    if (isRequestPending.value) {
                                        viewModel.deleteRequestfollow(
                                            connectedUserId.value,
                                            user.value.UserId
                                        )
                                        isRequestPending.value = false
                                        viewModel.analyticsEvent(
                                            "Click_Cancel_Request_Follow_Button"
                                        )
                                    }
                                }
                            },
                            isYourProfil = isYourProfil.value,
                            connectedUserId = connectedUserId.value,
                            imageUrl = imageUrl.value,
                            followersCount = followerCount.value,
                            followingCount = followingCount.value,
                            isUserFollow = isFollow,
                            isRequestFollowSend = isRequestPending
//                            eventShowFollowers = {
                            /* commenting out for now until api for followers
                            and following is completed */
//                                showFollowers = it
//                                showFollowDialog = true
//                            }
                        )
                    }
                }
                FeedbackCard(
                    onFeedbackClick = {
                        uriHandler
                            .openUri(
                                "https://forms.gle/gPNMZ7NcommuQLT4A"
                            )
                    }
                )
                if (isLoading.value) {
                    LandLoadingIconScreen()
                } else {
                    LandCarouselPostUser(
                        listPin.value,
                        navController
                    )
                }
                /*LandMaps(listPin.value, navController, false, null)*/

                // Non Utilisé Pour V1 Land
                // LandProfilFilterFeed()
            }
        }
    }
}
