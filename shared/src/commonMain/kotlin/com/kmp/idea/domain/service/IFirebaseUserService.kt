package com.kmp.idea.domain.repository

import com.dardev.land.domain.model.User

interface IFirebaseUserService {
    suspend fun getUserDataById(id:String): User
    suspend fun getUserCurrentUID():String
    suspend fun modifyUsername(username:String)
    suspend fun modifyEmail(email:String)
    suspend fun modifyPassword(password:String)
    suspend fun userSignOut()
}