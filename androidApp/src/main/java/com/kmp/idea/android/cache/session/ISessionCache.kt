package com.kmp.idea.android.cache

import com.kmp.idea.android.cache.session.Session

interface ISessionCache {
    fun saveSession(session: Session)

    fun getActiveSession(): Session?

    fun clearSession()
}
