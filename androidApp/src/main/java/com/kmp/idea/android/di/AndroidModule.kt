package com.kmp.idea.android.di

import com.kmp.idea.android.cache.ISessionCache
import com.kmp.idea.android.cache.session.SessionCache
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val androidModule = module {
    single<ISessionCache> { SessionCache(get()) }
}