package com.kmp.idea.di

import com.kmp.idea.data.dao.UserDAO
import com.kmp.idea.data.local.dao.IUserDAO
import com.kmp.idea.domain.use_cases.dao.UserUseCasesDAO
import com.kmp.idea.domain.use_cases.dao.user.DeleteUserByIdUseCase
import com.kmp.idea.domain.use_cases.dao.user.GetUserByIdUseCase
import com.kmp.idea.domain.use_cases.dao.user.InsertUserUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    //single { DatabaseDriverFactory(get()) }

    /*factory<IdeaDatabase> {
        IdeaDatabase(get<DatabaseDriverFactory>().createDriver())
    }*/

    // DAO
    factory<IUserDAO> { UserDAO(get()) }

    // Use Case
    factory {
        UserUseCasesDAO(
            DeleteUserByIdUseCase(get()),
            GetUserByIdUseCase(get()),
            InsertUserUseCase(get())
        )
    }
}