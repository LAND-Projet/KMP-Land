package com.kmp.idea.domain.use_cases.dao.user

import com.kmp.idea.data.local.dao.IUserDAO
import com.kmp.idea.domain.model.User

class InsertUserUseCase(
    private val dao : IUserDAO
) {
    suspend operator fun invoke(landUser: User){
        dao.insertUser(landUser)
    }
}