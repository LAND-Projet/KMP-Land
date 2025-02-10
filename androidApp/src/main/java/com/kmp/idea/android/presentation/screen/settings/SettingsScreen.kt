package com.kmp.idea.android.presentation.screen.settings

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SettingsScreen(
    navController: NavController,
    context: Context,
) {

    val options = BitmapFactory.Options().apply {
        inSampleSize = 2
    }
    val bitmap = BitmapFactory.decodeResource(
        context.resources,
        com.dardev.land.R.drawable.reliefbleu,
        options
    )
    val imageBitmap = bitmap.asImageBitmap()
    val coroutineScope = rememberCoroutineScope()
    val userId = remember {
        mutableStateOf("")
    }
    val removeAccountSuccess = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = userId) {
        userId.value = viewModel.getUserID()
    }

    Scaffold(
        topBar = {
            LandParameterTopBar(navController = navController, userId = userId.value)
        }
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
                modifier = Modifier.fillMaxHeight().alpha(0.5f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LandParameterInfoSection(
                    usernameEventClick = { it, result, callback ->
                        coroutineScope.launch {
                            viewModel.applyNewUsername(it, result, callback)
                            viewModel.analyticsEvent("Interaction_With_Username_TextField")
                            viewModel.analyticsEvent("Click_Modify_Username")
                        }
                    },
                    usernameValidateChange = {
                        viewModel.verifyNewUsername(it)
                    },
                    profilPictureEventClick = { it, result, callback ->
                        viewModel.analyticsEvent("Click_Select_New_Profil_Picture")
                        coroutineScope.launch {
                            viewModel.applyNewProfilPicture(it, result, callback)
                        }
                    },
                    context = context
                )
                LandParameterPrivateInfoSection(
                    navController = navController,
                    emailEventClick = {
                        coroutineScope.launch {}
                    },
                    passwordEventClick = { old, new, result, callback ->
                        viewModel.analyticsEvent("Click_Modify_Password")
                        coroutineScope.launch {
                            viewModel.applyNewPassword(old, new, result, callback)
                        }
                    },
                    typeAccountEventClick = { it, result, callback ->
                        viewModel.analyticsEvent("Click_Modify_Type_Account")
                        coroutineScope.launch {
                            viewModel.applyNewTypeAccount(it, result, callback)
                        }
                    },
                    emailValidateChange = {
                        viewModel.verifyNewEmail(it)
                    },
                    passwordValidateChange = {
                        viewModel.verifyNewPassword(it)
                    },
                    repeatPasswordValidateChange = { newPassword, repeatPassword ->
                        viewModel.verifyNewRepeatedPassword(newPassword, repeatPassword)
                    },
                    removeAccountClick = {
                        viewModel.analyticsEvent("Click_Remove_Account")
                        coroutineScope.launch {
                            removeAccountSuccess.value = viewModel.removeAccount()
                        }
                    },
                    removeAccountSuccess = removeAccountSuccess
                )
            }
        }
    }
}
