package com.kmp.idea.data.service

import com.kmp.idea.domain.model.User
import com.kmp.idea.domain.remote.IUserAPI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserAPIServiceHelper: KoinComponent {
    private val service: UserAPIService by inject()

    suspend fun getUserData(userGuid: String,jwtToken: String): User? {
        return service.getUserData(userGuid,jwtToken)
    }

}