package com.kmp.idea.domain.remote

import com.kmp.idea.domain.model.SignInData
import com.kmp.idea.domain.model.SignUpData

interface IFirebaseAuthService {
    suspend fun signInFirebaseService(signInData: SignInData)
    suspend fun signUpFirebaseService(signUpData: SignUpData)
}