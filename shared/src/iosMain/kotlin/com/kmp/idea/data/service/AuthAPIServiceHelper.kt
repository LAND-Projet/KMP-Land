package com.kmp.idea.data.service

import com.kmp.idea.data.repository.UserRepository
import com.kmp.idea.domain.model.SignInData
import com.kmp.idea.domain.model.SignUpData
import com.kmp.idea.domain.model.TokenConnexion
import com.kmp.idea.domain.remote.IAuthAPI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthAPIServiceHelper: KoinComponent {
    private val service: AuthAPIService by inject()

    suspend fun signUp(signUpData: SignUpData): TokenConnexion? {
        return service.signUp(signUpData)
    }

    suspend fun signIn(signInData: SignInData): TokenConnexion?? {
        return service.signIn(signInData)
    }

    suspend fun refreshToken(token: String,userId: String): TokenConnexion? {
        return service.refreshToken(token,userId)
    }

}