package com.kmp.idea.android.presentation.screen.auth.menu

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kmp.idea.android.navigation.Screen
import com.kmp.idea.android.presentation.common.Button
import com.kmp.idea.android.presentation.common.CopyrightText
import com.kmp.idea.android.util.AndroidStringResource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun AuthMenuScreen(
    navController: NavController,
    context: Context,
) {

    Scaffold(
        bottomBar = { CopyrightText() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(id = com.dardev.land.R.drawable.logoauth),
                    modifier = Modifier.width(100.dp).height(120.dp),
                    alignment = Alignment.TopCenter,
                    contentDescription = AndroidStringResource(id = SharedRes.strings.logo_land)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    eventClick = { navController.navigate(Screen.SignInPage.route) },
                    content = AndroidStringResource(id = SharedRes.strings.connexion_text_button)
                )
                Spacer(modifier = Modifier.height(50.dp))
                Button(
                    eventClick = { navController.navigate(Screen.SignUpPage.route) },
                    content = AndroidStringResource(id = SharedRes.strings.enroll_text_button)
                )
            }
        }
    }
}
