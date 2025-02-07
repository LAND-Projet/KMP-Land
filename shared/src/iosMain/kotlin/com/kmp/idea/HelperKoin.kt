package com.kmp.idea

import com.kmp.idea.di.appModule
import com.kmp.idea.di.platformModule
import com.kmp.idea.domain.repository.IFirebaseAuthService
import com.kmp.idea.domain.repository.IFirebasePostService
import com.kmp.idea.domain.repository.IFirebaseUserService
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

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