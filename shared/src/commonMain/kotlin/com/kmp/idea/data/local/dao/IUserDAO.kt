package com.kmp.idea.data.local.dao

import com.kmp.idea.domain.model.User

interface IUserDAO {
    suspend fun getUserById(userId:String):User?
    suspend fun insertUser(user: User)
    suspend fun deleteUserById(userId:String)
}