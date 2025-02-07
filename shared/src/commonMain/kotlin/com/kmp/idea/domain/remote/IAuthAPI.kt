package com.kmp.idea.domain.remote

import com.kmp.idea.domain.model.SignInData
import com.kmp.idea.domain.model.SignUpData
import com.kmp.idea.domain.model.TokenConnexion

interface IAuthAPI {
    suspend fun signUpToLandApp(landSignUpData: SignUpData): TokenConnexion?
    suspend fun signInToLandApp(landSignInData: SignInData): TokenConnexion?
    suspend fun refreshLandJWTToken(token: String,userId: String): TokenConnexion?
}