package com.kmp.idea.domain.use_cases.dao

import com.kmp.idea.domain.model.LandUser
import kotlinx.coroutines.flow.toList
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserUseCasesDAOHelper: KoinComponent {
    val usecase: UserUseCasesDAO by inject()

    suspend fun deleteUserById(userId: String) {
        usecase.deleteUserByIdUseCase.invoke(userId)
    }

    suspend fun getUserById(userId: String): LandUser? {
        return usecase.getUserByIdUseCase.invoke(userId)
    }

    suspend fun insertUser(user: LandUser) {
        usecase.insertUserUseCase.invoke(user)
    }
}