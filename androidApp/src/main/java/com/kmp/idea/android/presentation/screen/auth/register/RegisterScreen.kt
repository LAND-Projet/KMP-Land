package com.kmp.idea.android.presentation.screen.auth.register

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.kmp.idea.android.presentation.common.CopyrightText

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun AuthSignUpScreen(
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
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    val coroutineScope = rememberCoroutineScope()
    var email = remember { mutableStateOf("") }
    var username = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var repeatedPassword = remember { mutableStateOf("") }
    var accountType = remember { mutableStateOf(false) }
    var passwordVisibility = remember { mutableStateOf(false) }
    var repeatedPasswordVisibility = remember { mutableStateOf(false) }
    val textToast = remember { mutableStateOf("") }
    val resourceId = remember { mutableStateOf<StringResource?>(null) }
    val isLoading = remember { mutableStateOf(false) }
    val termsText = AndroidStringResource(id = SharedRes.strings.terms_of_use_text_label)
    val annotatedText = buildAnnotatedString {
        pushStyle(SpanStyle(color = MaterialTheme.colorScheme.textColor))
        append(AndroidStringResource(id = SharedRes.strings.accept_use_condition))
        pushStyle(SpanStyle(color = blueShade))
        addStringAnnotation(
            tag = "TermsLink",
            annotation = "TermsLink",
            start = length - termsText.length,
            end = length
        )
        append(" " + AndroidStringResource(id = SharedRes.strings.terms_of_use_text_label))
        pop()
    }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestId()
        .requestProfile()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            viewModel.handleSignUpResult(account)
            navController.navigate(Screen.Home.route)
        }
    }

    fun iconVisibility(visibility: Boolean): Int {
        return if (visibility) {
            R.drawable.ic_show_password
        } else {
            R.drawable.ic_unshow_password
        }
    }

    val OnClickSignUp: () -> Unit = {
        isLoading.value = true
        coroutineScope.launch {
            if (
                viewModel.verifyEmailField(email.value).successful &&
                viewModel.verifyUsernameField(username.value).successful &&
                viewModel.verifyPasswordField(password.value).successful &&
                viewModel.verifyRepeatPasswordField(
                    password.value,
                    repeatedPassword.value
                ).successful
            ) {
                resourceId.value = viewModel.applySignUp(
                    LandSignUpData(
                        username.value,
                        email.value,
                        password.value,
                        repeatedPassword.value,
                        accountType.value,
                        NotificationToken.getToken()
                    )
                )
                viewModel.automateWorker(context)
            } else {
                resourceId.value = SharedRes.strings.enroll_error_validation_message
            }
            isLoading.value = false
        }
    }

    Scaffold(
        bottomBar = { CopyrightText() }
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
                modifier = Modifier.fillMaxSize().alpha(0.5f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(id = com.dardev.land.R.drawable.logoauth),
                    modifier = Modifier.width(100.dp).height(120.dp),
                    alignment = Alignment.TopCenter,
                    contentDescription = ""
                )
                Text(
                    text = AndroidStringResource(id = SharedRes.strings.signup_text_label),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.height(50.dp))

                // Email
                Row(
                    modifier = Modifier.width(322.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = AndroidStringResource(id = SharedRes.strings.email_text_label),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                LandOutlinedTextField(
                    email,
                    AndroidStringResource(id = SharedRes.strings.email_text_label),
                    eventChange = { viewModel.verifyEmailField(email.value) }
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Username
                Row(
                    modifier = Modifier.width(322.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = AndroidStringResource(id = SharedRes.strings.username_text_label),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                LandOutlinedTextField(
                    username,
                    AndroidStringResource(id = SharedRes.strings.username_text_label),
                    eventChange = {
                        viewModel.verifyUsernameField(username.value)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Password
                Row(
                    modifier = Modifier.width(322.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = AndroidStringResource(id = SharedRes.strings.password_text_label),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                LandOutlinedTextField(
                    password,
                    AndroidStringResource(id = SharedRes.strings.password_text_label),
                    true,
                    passwordVisibility,
                    painterResource(id = iconVisibility(passwordVisibility.value)),
                    eventChange = { viewModel.verifyPasswordField(password.value) }
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Repeated Password
                Row(
                    modifier = Modifier.width(322.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = AndroidStringResource(
                            id = SharedRes.strings.repeated_password_text_label
                        ),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                LandOutlinedTextField(
                    repeatedPassword,
                    AndroidStringResource(id = SharedRes.strings.repeated_password_text_label),
                    true,
                    repeatedPasswordVisibility,
                    painterResource(id = iconVisibility(repeatedPasswordVisibility.value)),
                    eventChange = {
                        viewModel.verifyRepeatPasswordField(password.value, repeatedPassword.value)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = AndroidStringResource(
                            id = SharedRes.strings.type_account_text_label
                        ),
                        style = MaterialTheme.typography.labelMedium
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Switch(
                        checked = accountType.value,
                        onCheckedChange = { accountType.value = it },
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = lightGrey,
                            checkedThumbColor = lightGrey,
                            checkedTrackColor = successColor
                        )
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    if (accountType.value) {
                        Text(
                            text = AndroidStringResource(id = SharedRes.strings.private_text_label),
                            style = MaterialTheme.typography.labelMedium
                        )
                    } else {
                        Text(
                            text = AndroidStringResource(id = SharedRes.strings.public_text_label),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                ClickableText(
                    text = annotatedText,
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(
                            tag = "TermsLink",
                            start = offset,
                            end = offset
                        ).firstOrNull()?.let {
                            if (it.item == "TermsLink") {
                                uriHandler
                                    .openUri(
                                        "https://www.termsfeed.com/live/" +
                                                "ff8a726a-3f98-40b1-9987-ce642a96e5c0"
                                    )
                            }
                        }
                    },
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                if (isLoading.value) {
                    LandLoadingButton()
                } else {
                    LandButton(
                        eventClick = OnClickSignUp,
                        content = AndroidStringResource(id = SharedRes.strings.enroll_text_button)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LandGoogleButton(
                        eventClick = {
                            val signInIntent = googleSignInClient.signInIntent
                            launcher.launch(signInIntent)
                        },
                        content = AndroidStringResource(
                            id = SharedRes.strings.enroll_google_text_button
                        )
                    )
                }
                if (resourceId.value != null) {
                    textToast.value = AndroidStringResource(id = resourceId.value!!)
                    if (textToast.value == AndroidStringResource(
                            id = SharedRes.strings.enroll_success_message
                        )
                    ) {
                        SweetSuccess(
                            message = textToast.value,
                            padding = PaddingValues(bottom = 16.dp)
                        )
                        textToast.value = ""
                        navController.navigate(Screen.Home.route)
                    } else {
                        SweetError(
                            message = textToast.value,
                            padding = PaddingValues(bottom = 16.dp)
                        )
                        textToast.value = ""
                    }
                    resourceId.value = null
                }

                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.padding(5.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = AndroidStringResource(
                            id = SharedRes.strings.navigate_to_connexion_text_label
                        ),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.textColor
                    )
                    Text(
                        text = AndroidStringResource(id = SharedRes.strings.connexion_text_button),
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.SignIn.route)
                            }
                            .padding(start = 5.dp),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.labelSmall,
                        color = blueShade
                    )
                }
            }
        }
    }
}
