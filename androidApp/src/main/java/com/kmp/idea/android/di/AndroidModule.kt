package com.kmp.idea.android.di

import com.kmp.idea.android.cache.ISessionCache
import com.kmp.idea.android.cache.session.SessionCache
import org.koin.dsl.module

val androidModule =
    module {
        single<ISessionCache> { SessionCache(get()) }
    }
