package com.kmp.idea.domain.use_cases.dao.user

import com.kmp.idea.data.local.dao.IUserDAO
import com.kmp.idea.domain.model.User

class GetUserByIdUseCase(
    private val dao : IUserDAO
) {
    suspend operator fun invoke(id:String):User?{
        return dao.getUserById(id)
    }
}