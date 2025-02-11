package com.kmp.idea.android.presentation.screen.auth.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun AuthSignInScreen(
    navController: NavController,
    context: Context,
) {
    val options =
        BitmapFactory.Options().apply {
            inSampleSize = 2
        }
    val bitmap =
        BitmapFactory.decodeResource(
            context.resources,
            com.dardev.land.R.drawable.reliefbleu,
            options,
        )
    val imageBitmap = bitmap.asImageBitmap()
    val coroutineScope = rememberCoroutineScope()
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    val textToast = remember { mutableStateOf("") }
    val resourceId = remember { mutableStateOf<StringResource?>(null) }
    var passwordVisibility = remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(false) }

    val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestId()
            .requestProfile()
            .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val account = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                viewModel.handleSignInResult(account)
                navController.navigate(Screen.Home.route)
            }
        }

    val icon =
        if (passwordVisibility.value) {
            painterResource(id = R.drawable.ic_show_password)
        } else {
            painterResource(id = R.drawable.ic_unshow_password)
        }

    val OnClickSignIn: () -> Unit = {
        isLoading.value = true
        coroutineScope.launch {
            resourceId.value =
                viewModel.applySignIn(
                    LandSignInData(
                        email = email.value,
                        password = password.value,
                        token = NotificationToken.getToken(),
                    ),
                )
            viewModel.automateWorker(context)
            isLoading.value = false
        }
    }

    Scaffold(
        bottomBar = { LandCopyrightText() },
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
        ) {
            // Image de fond
            Image(
                bitmap = imageBitmap,
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().alpha(0.5f),
            )

            Column(
                modifier =
                    Modifier
                        .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painterResource(id = com.dardev.land.R.drawable.logoauth),
                    modifier = Modifier.width(100.dp).height(120.dp),
                    alignment = Alignment.TopCenter,
                    contentDescription = AndroidStringResource(id = SharedRes.strings.logo_land),
                )
                Text(
                    text = AndroidStringResource(id = SharedRes.strings.signin_text_label),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayMedium,
                )
                Spacer(modifier = Modifier.height(50.dp))

                // Email
                Row(
                    modifier = Modifier.width(322.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = AndroidStringResource(id = SharedRes.strings.email_text_label),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                LandOutlinedTextField(
                    email,
                    AndroidStringResource(id = SharedRes.strings.email_text_label),
                    eventChange = { viewModel.verifyEmailField(email.value) },
                )
                Spacer(modifier = Modifier.height(15.dp))

                // Password
                Row(
                    modifier = Modifier.width(322.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = AndroidStringResource(id = SharedRes.strings.password_text_label),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                LandOutlinedTextField(
                    password,
                    AndroidStringResource(id = SharedRes.strings.password_text_label),
                    true,
                    passwordVisibility,
                    icon,
                    eventChange = { viewModel.verifyPasswordField(password.value) },
                )
                Spacer(modifier = Modifier.height(25.dp))

                /*Text(
                    text = AndroidStringResource(id = SharedRes.strings.forgot_password_text_label),
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.ForgotPassword.route)
                        }
                        .padding(start = 190.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelSmall,
                    color = blueShade
                )
                Spacer(modifier = Modifier.height(10.dp))*/

                if (isLoading.value) {
                    LandLoadingButton()
                } else {
                    LandButton(
                        eventClick = OnClickSignIn,
                        content =
                            AndroidStringResource(
                                id = SharedRes.strings.connexion_text_button,
                            ),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LandGoogleButton(
                        eventClick = {
                            val signInIntent = googleSignInClient.signInIntent
                            launcher.launch(signInIntent)
                        },
                        content =
                            AndroidStringResource(
                                id = SharedRes.strings.connexion_google_text_button,
                            ),
                    )
                }

                if (resourceId.value != null) {
                    textToast.value = AndroidStringResource(id = resourceId.value!!)
                    if (textToast.value ==
                        AndroidStringResource(
                            id = SharedRes.strings.connexion_success_message,
                        )
                    ) {
                        SweetSuccess(
                            message = textToast.value,
                            padding = PaddingValues(bottom = 16.dp),
                        )
                        textToast.value = ""
                        navController.navigate(Screen.Home.route)
                    } else {
                        SweetError(
                            message = textToast.value,
                            padding = PaddingValues(bottom = 16.dp),
                        )
                        textToast.value = ""
                    }
                    resourceId.value = null
                }

                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier.padding(15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text =
                            AndroidStringResource(
                                id = SharedRes.strings.navigate_to_enroll_text_label,
                            ),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.textColor,
                    )
                    Text(
                        text = AndroidStringResource(id = SharedRes.strings.enroll_text_button),
                        modifier =
                            Modifier
                                .clickable {
                                    navController.navigate(Screen.SignUp.route)
                                }
                                .padding(start = 5.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall,
                        color = blueShade,
                    )
                }
            }
        }
    }
}
