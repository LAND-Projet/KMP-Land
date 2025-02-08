package com.kmp.idea.android.navigation

import com.kmp.idea.android.util.Constants.DESCRIPTION_POST_ARGUMENT_KEY
import com.kmp.idea.android.util.Constants.DETAILS_POST_ARGUMENT_KEY
import com.kmp.idea.android.util.Constants.PROFIL_USERID_ARGUMENT_KEY

sealed class Screen(val route: String, val iconScreen: Int) {
    object SplashPage : Screen("splash_screen", 0)

    object MenuAuthPage : Screen("menu_auth_screen", 0)

    object SignInPage : Screen("sign_in_screen", 0)

    object SignUpPage : Screen("sign_up_screen", 0)

    object OnboardingPage : Screen("onboarding_screen", 0)

    object HomePage : Screen("home_screen", com.kmp.idea.android.R.drawable.searchicondark)

    object PostDetailPage : Screen("post_detail_screen/{$DETAILS_POST_ARGUMENT_KEY}", 0) {
        fun passPostId(postId: String): String {
            return "post_detail_screen/$postId"
        }
    }

    object CameraPage : Screen(
        "camera_screen/" +
                "{$DESCRIPTION_POST_ARGUMENT_KEY}",
        0
    ) {
        fun passDataLocation(
            description: String
        ): String {
            return "camera_screen/$description"
        }
        fun passNothing(): String {
            return "camera_screen/ "
        }
    }

    object ResearchPage : Screen("research_screen", com.kmp.idea.android.R.drawable.searchicondark)

    object ProfilPage : Screen(
        "profil_screen/" +
                "{$PROFIL_USERID_ARGUMENT_KEY}",
        com.kmp.idea.android.R.drawable.usericondark
    ) {
        fun passDataForProfil(userId: String): String {
            return "profil_screen/$userId"
        }
    }

    object NotificationPage : Screen("notification_screen", com.kmp.idea.android.R.drawable.notificationicondark)

    object ParameterPage : Screen("parameter_screen", com.kmp.idea.android.R.drawable.dotsmore)

    object SwipePage : Screen("swipe_screen", 0)

}