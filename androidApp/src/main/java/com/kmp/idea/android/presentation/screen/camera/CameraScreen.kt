package com.kmp.idea.android.presentation.screen.camera

import android.annotation.SuppressLint
import android.net.Uri
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import java.io.File
import java.util.*
import java.util.concurrent.Executor

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun CameraScreen(
    navController: NavController,
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit,
) {
}
