package com.kmp.idea.di

import com.kmp.idea.core.helper.NetworkCaller
import com.kmp.idea.core.helper.NetworkCallerImpl
import com.kmp.idea.data.remote.AuthAPI
import com.kmp.idea.data.remote.ParameterAPI
import com.kmp.idea.data.remote.PostAPI
import com.kmp.idea.data.remote.UserAPI
import com.kmp.idea.data.repository.PostRepository
import com.kmp.idea.data.repository.UserRepository
import com.kmp.idea.data.service.AuthAPIService
import com.kmp.idea.data.service.ParameterAPIService
import com.kmp.idea.data.service.PostAPIService
import com.kmp.idea.data.service.UserAPIService
import com.kmp.idea.data.service.firebase.FirebaseAuthService
import com.kmp.idea.data.service.firebase.FirebasePostService
import com.kmp.idea.data.service.firebase.FirebaseUserService
import com.kmp.idea.domain.remote.IAuthAPI
import com.kmp.idea.domain.remote.IFirebaseAuthService
import com.kmp.idea.domain.remote.IFirebasePostService
import com.kmp.idea.domain.remote.IFirebaseUserService
import com.kmp.idea.domain.remote.IParameterAPI
import com.kmp.idea.domain.remote.IPostAPI
import com.kmp.idea.domain.remote.IUserAPI
import com.kmp.idea.domain.use_cases.ValidateResult
import com.kmp.idea.domain.use_cases.post.PostUseCases
import com.kmp.idea.domain.use_cases.post.ValidateDescription
import com.kmp.idea.domain.use_cases.user.UserUseCases
import com.kmp.idea.domain.use_cases.user.ValidateEmail
import com.kmp.idea.domain.use_cases.user.ValidatePassword
import com.kmp.idea.domain.use_cases.user.ValidateRepeatedPassword
import com.kmp.idea.domain.use_cases.user.ValidateUsername
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

    // API
    single<IAuthAPI> { AuthAPI(get()) }
    single<IUserAPI> { UserAPI(get()) }
    single<IPostAPI> { PostAPI(get()) }
    single<IParameterAPI> { ParameterAPI(get()) }

    // Use Case
    single { UserUseCases(ValidateEmail(), ValidateUsername(), ValidatePassword(), ValidateRepeatedPassword(), ValidateResult(true)) }
    single { PostUseCases( ValidateDescription(), ValidateResult(true)) }

    // Repository
    factory { UserRepository(get()) }
    factory { PostRepository(get()) }

    // Service
    factory { AuthAPIService(get()) }
    factory { UserAPIService(get()) }
    factory { PostAPIService(get()) }
    factory { ParameterAPIService(get()) }


}