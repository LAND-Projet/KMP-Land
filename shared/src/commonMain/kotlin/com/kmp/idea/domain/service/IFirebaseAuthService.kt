package com.kmp.idea.domain.repository

import com.kmp.idea.domain.model.SignInData
import com.kmp.idea.domain.model.SignUpData

interface IFirebaseAuthService {
    suspend fun signInFirebaseService(signInData: SignInData)
    suspend fun signUpFirebaseService(signUpData: SignUpData)
}