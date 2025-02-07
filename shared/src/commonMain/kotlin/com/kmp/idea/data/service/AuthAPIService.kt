package com.kmp.idea.data.service

import com.kmp.idea.domain.model.SignInData
import com.kmp.idea.domain.model.SignUpData
import com.kmp.idea.domain.model.TokenConnexion
import com.kmp.idea.domain.remote.IAuthAPI

class AuthAPIService(
    private val authAPI: IAuthAPI
) {
    suspend fun signUp(signUpData: SignUpData): TokenConnexion? {
        return authAPI.signUpToApp(signUpData)
    }

    suspend fun signIn(signInData: SignInData): TokenConnexion?? {
        return authAPI.signInToApp(signInData)
    }

    suspend fun refreshToken(token: String,userId: String): TokenConnexion? {
        return authAPI.refreshJWTToken(token,userId)
    }

}