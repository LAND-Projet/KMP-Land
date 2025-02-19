package com.kmp.idea.domain.use_cases.dao.user

import com.kmp.idea.data.local.dao.IUserDAO

class DeleteUserByIdUseCase(
    private val dao : IUserDAO
) {
    suspend operator fun invoke(id:String){
        dao.deleteUserById(id)
    }
}