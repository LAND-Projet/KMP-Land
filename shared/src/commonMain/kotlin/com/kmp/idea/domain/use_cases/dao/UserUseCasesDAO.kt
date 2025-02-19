package com.kmp.idea.domain.use_cases.dao

import com.kmp.idea.domain.use_cases.dao.user.DeleteUserByIdUseCase
import com.kmp.idea.domain.use_cases.dao.user.GetUserByIdUseCase
import com.kmp.idea.domain.use_cases.dao.user.InsertUserUseCase

data class UserUseCasesDAO (
    val deleteUserByIdUseCase: DeleteUserByIdUseCase,
    val getUserByIdUseCase: GetUserByIdUseCase,
    val insertUserUseCase: InsertUserUseCase
)