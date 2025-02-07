package com.kmp.idea.data.service

import com.kmp.idea.domain.remote.IParameterAPI
import com.kmp.idea.domain.use_cases.ValidateResult
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ParameterAPIServiceHelper: KoinComponent {
    private val service: ParameterAPIService by inject()

    suspend fun removeAccount(userGuid:String,jwtToken: String): ValidateResult? {
        return service.removeAccount(userGuid,jwtToken)
    }
}