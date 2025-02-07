package com.kmp.idea.domain.remote

import com.kmp.idea.domain.use_cases.ValidateResult

interface IParameterAPI {
    suspend fun deleteUser(userGuid:String,jwtToken: String): ValidateResult?
}