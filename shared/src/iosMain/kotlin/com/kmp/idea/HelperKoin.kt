package com.kmp.idea

import com.kmp.idea.di.appModule
import com.kmp.idea.di.platformModule
import com.kmp.idea.domain.remote.IAuthAPI
import com.kmp.idea.domain.remote.IFirebaseAuthService
import com.kmp.idea.domain.remote.IFirebasePostService
import com.kmp.idea.domain.remote.IFirebaseUserService
import com.kmp.idea.domain.remote.IParameterAPI
import com.kmp.idea.domain.remote.IPostAPI
import com.kmp.idea.domain.remote.IUserAPI
import com.kmp.idea.domain.use_cases.post.PostUseCases
import com.kmp.idea.domain.use_cases.user.UserUseCases
import org.koin.core.context.startKoin

fun initKoin() {
    val koinApp = startKoin {
        modules(
            appModule(),
            platformModule()
        )
    }.koin

    koinApp.get<IFirebaseAuthService>()
    koinApp.get<IFirebaseUserService>()
    koinApp.get<IFirebasePostService>()

    koinApp.get<IAuthAPI>()
    koinApp.get<IUserAPI>()
    koinApp.get<IPostAPI>()
    koinApp.get<IParameterAPI>()

    koinApp.get<UserUseCases>()
    koinApp.get<PostUseCases>()
    koinApp.get<UserUseCasesDAO>()
}