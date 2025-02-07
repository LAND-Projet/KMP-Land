package com.kmp.idea.di

import com.kmp.idea.core.helper.NetworkCaller
import com.kmp.idea.core.helper.NetworkCallerImpl
import com.kmp.idea.data.service.firebase.FirebaseAuthService
import com.kmp.idea.data.service.firebase.FirebasePostService
import com.kmp.idea.data.service.firebase.FirebaseUserService
import com.kmp.idea.domain.repository.IFirebaseAuthService
import com.kmp.idea.domain.repository.IFirebasePostService
import com.kmp.idea.domain.repository.IFirebaseUserService
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun appModule() = module {
    single<HttpClient> {
        HttpClient {
            // Configure your HttpClient here, including base URL, headers, and other settings.
        }
    }
    single<NetworkCaller> { NetworkCallerImpl(get()) }

    platformModule()

    // Firebase
    single<IFirebaseAuthService> { FirebaseAuthService() }
    single<IFirebaseUserService> { FirebaseUserService() }
    single<IFirebasePostService> { FirebasePostService() }
}