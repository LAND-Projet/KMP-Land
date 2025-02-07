package com.kmp.idea.data.service

import com.kmp.idea.domain.remote.IParameterAPI
import com.kmp.idea.domain.use_cases.ValidateResult

class ParameterAPIService(
    private val parameterAPI: IParameterAPI
) {
    suspend fun removeAccount(userGuid:String,jwtToken: String): ValidateResult? {
        return parameterAPI.deleteUser(userGuid,jwtToken)
    }
}