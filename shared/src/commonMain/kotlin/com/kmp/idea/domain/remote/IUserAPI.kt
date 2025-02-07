package com.kmp.idea.domain.remote

import com.kmp.idea.domain.model.User

interface IUserAPI {
    suspend fun getUserById(userGuid:String,jwtToken: String): User?
}