package com.kmp.idea.data.service

import com.kmp.idea.domain.model.User
import com.kmp.idea.domain.remote.IPostAPI
import com.kmp.idea.domain.remote.IUserAPI

class UserAPIService(
    private val userAPI: IUserAPI
) {
    suspend fun getUserData(userGuid: String,jwtToken: String): User? {
        return userAPI.getUserById(userGuid,jwtToken)
    }

}