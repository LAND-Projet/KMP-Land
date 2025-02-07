package com.kmp.idea.domain.remote

import com.kmp.idea.domain.model.SignInData
import com.kmp.idea.domain.model.SignUpData
import com.kmp.idea.domain.model.TokenConnexion

interface IAuthAPI {
    suspend fun signUpToApp(signUpData: SignUpData): TokenConnexion?
    suspend fun signInToApp(signInData: SignInData): TokenConnexion?
    suspend fun refreshJWTToken(token: String,userId: String): TokenConnexion?
}