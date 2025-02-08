package com.kmp.idea.android.navigation

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kmp.idea.android.presentation.screen.auth.login.AuthSignInScreen
import com.kmp.idea.android.presentation.screen.auth.menu.AuthMenuScreen
import com.kmp.idea.android.presentation.screen.auth.register.AuthSignUpScreen
import com.kmp.idea.android.presentation.screen.camera.CameraScreen
import com.kmp.idea.android.presentation.screen.home.HomeScreen
import com.kmp.idea.android.presentation.screen.profil.ProfilScreen
import com.kmp.idea.android.presentation.screen.settings.SettingsScreen
import java.io.File
import java.util.concurrent.Executor
import com.kmp.idea.android.util.Constants

@ExperimentalMaterial3Api
@Composable
fun NavModel(
    navController: NavHostController,
    context: Context,
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MenuAuthPage.route
    ) {
        composable(route = Screen.MenuAuthPage.route) {
            AuthMenuScreen(navController = navController, context)
        }
        composable(route = Screen.SignInPage.route) {
            AuthSignInScreen(navController = navController, context)
        }
        composable(route = Screen.SignUpPage.route) {
            AuthSignUpScreen(navController = navController, context)
        }
        composable(route = Screen.OnboardingPage.route) {

        }
        composable(route = Screen.HomePage.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.SwipePage.route) {

        }
        composable(
            route = Screen.PostDetailPage.route,
            arguments = listOf(
                navArgument(Constants.DETAILS_POST_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) {

        }
        composable(
            route = Screen.CameraPage.route,
            arguments = listOf(
                navArgument(Constants.DESCRIPTION_POST_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            CameraScreen(
                navController = navController,
                outputDirectory = outputDirectory,
                executor = executor,
                onImageCaptured = onImageCaptured,
                onError = { }
            )
        }
        composable(
            route = Screen.ProfilPage.route,
            arguments = listOf(
                navArgument(Constants.PROFIL_USERID_ARGUMENT_KEY) {
                    type = NavType.StringType
                }
            )
        ) {
            ProfilScreen(navController = navController, context)
        }
        composable(route = Screen.ResearchPage.route) {

        }
        composable(route = Screen.ParameterPage.route) {
            SettingsScreen(navController = navController, context = context)
        }
        composable(route = Screen.NotificationPage.route) {

        }
    }
}