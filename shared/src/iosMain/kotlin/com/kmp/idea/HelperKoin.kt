package com.kmp.idea

import com.kmp.idea.di.appModule
import com.kmp.idea.di.platformModule
import com.kmp.idea.domain.remote.IFirebaseAuthService
import com.kmp.idea.domain.remote.IFirebasePostService
import com.kmp.idea.domain.remote.IFirebaseUserService
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

}