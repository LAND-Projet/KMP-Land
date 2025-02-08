package com.kmp.idea.android.navigation

import com.kmp.idea.android.util.Constants.DESCRIPTION_POST_ARGUMENT_KEY
import com.kmp.idea.android.util.Constants.DETAILS_POST_ARGUMENT_KEY
import com.kmp.idea.android.util.Constants.PROFIL_USERID_ARGUMENT_KEY

sealed class Screen(val route: String) {
    object SplashPage : Screen("splash_screen")

    object MenuAuthPage : Screen("menu_auth_screen")

    object SignInPage : Screen("sign_in_screen")

    object SignUpPage : Screen("sign_up_screen")

    object OnboardingPage : Screen("onboarding_screen")

    object HomePage : Screen("home_screen")

    object PostDetailPage : Screen("post_detail_screen/{$DETAILS_POST_ARGUMENT_KEY}") {
        fun passPostId(postId: String): String {
            return "post_detail_screen/$postId"
        }
    }

    object CameraPage : Screen(
        "camera_screen/" +
            "{$DESCRIPTION_POST_ARGUMENT_KEY}",
    ) {
        fun passDataLocation(description: String): String {
            return "camera_screen/$description"
        }

        fun passNothing(): String {
            return "camera_screen/ "
        }
    }

    object ResearchPage : Screen("research_screen")

    object ProfilPage : Screen(
        "profil_screen/" +
            "{$PROFIL_USERID_ARGUMENT_KEY}",
    ) {
        fun passDataForProfil(userId: String): String {
            return "profil_screen/$userId"
        }
    }

    object NotificationPage : Screen("notification_screen")

    object ParameterPage : Screen("parameter_screen")

    object SwipePage : Screen("swipe_screen")
}
